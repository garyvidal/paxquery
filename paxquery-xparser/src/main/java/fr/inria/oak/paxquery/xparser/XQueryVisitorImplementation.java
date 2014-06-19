package fr.inria.oak.paxquery.xparser;

import fr.inria.oak.paxquery.algebra.operators.*;
import fr.inria.oak.paxquery.algebra.operators.border.*;
import fr.inria.oak.paxquery.common.xml.construction.ApplyConstruct;
import fr.inria.oak.paxquery.algebra.operators.binary.*;
import fr.inria.oak.paxquery.common.xml.treepattern.core.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;


public class XQueryVisitorImplementation extends XQueryBaseVisitor<Void> {

	public enum StatementType { NONE, LET, FOR };
	
	/**
	 * Operators
	 */
	public ArrayList<XMLScan> scans;			//the leaves of the algebraic operators tree
	public XMLConstruct construct;				//the root of the algebraic operators tree
	public BaseLogicalOperator constructChild;	//pointer to the immediate descendant of the XMLconstruct operator

	/**
	 * Data structures
	 */
	public HashMap<String, Variable> varsPos;	//for use when creating the XMLConstruct operator in return clause
	public HashMap<String, PatternNode> patternNodeMap;	//each tuple <String, PatternNode> stores the name of a variable and the PatternNode it addresses
	public ArrayList<TreePattern> treePatterns;	//the list of all TreePattern objects built for a given query
	public ArrayList<String> applyEach;			//holds a String array for ApplyConstruct.each
	public ArrayList<Integer> applyFields;		//holds a Integer array for ApplyConstruct.fields
	public HashMap<String, ApplyConstruct> mappedApplys;	//stores all ApplyConstruct objects instantiated during query processing (lets and nested sub-queries). The mapping is <variable_name, associated_applyconstruc>
	public ArrayList<ApplyConstruct> nestedApplys;	//this is XMLConstruct.NestedApplyConstruc[]
	
	/**
	 * State variables for parsing
	 */
	private TreePattern lastTreePattern;
	private PatternNode lastNode;
	private String lastVarLeftSide;
	private int lastSlashToken;
	private boolean nextNodeIsAttribute;
	private StringBuilder returnXMLTags;		//accumulates XML constant text from the return construction. This text is eventually inserted into XMLConstruct.apply.each
	private boolean insideReturn;
	private int whereHits;						//number of times a where statement is entered
	private int groupByHits;					//number of times a group-by statement is entered
	private BaseLogicalOperator lastOp = null;	//for algebraic operators tree construction
	private BaseLogicalOperator thisOp = null;	//for algebraic operators tree construction
	private ArrayList<Boolean> treePatternVisited = null; //for algebraic operators tree construction
	private StatementType currentStatement = StatementType.NONE;	//for tree pattern construction (so we can decide on nested and optional edges)
	private boolean specialEdgeFlag = false;	//set to true for edges VAR/whatever, so we can decided whether the edge is nested and optional or not
	
	/**
	 * Others
	 */
	public String outputPath;					//file to be printed out 

	public XQueryVisitorImplementation(String outputPath) {
		this.outputPath = outputPath;
		patternNodeMap = new HashMap<String, PatternNode>();
		treePatterns = new ArrayList<TreePattern>();
		lastSlashToken = 0;
		nextNodeIsAttribute = false;
		insideReturn = false;
		scans = new ArrayList<XMLScan>();
		varsPos = new HashMap<String, Variable>();
		applyEach = new ArrayList<String>();
		applyFields = new ArrayList<Integer>();
		mappedApplys = new HashMap<String, ApplyConstruct>();
		nestedApplys = new ArrayList<ApplyConstruct>();
		treePatternVisited = new ArrayList<Boolean>();
		whereHits = 0;
		groupByHits = 0;
	}
	
	/**
	 * xquery
	 * Starting rule. We build the XMLConstruct object here, after having processed the query
	 */
	public Void visitXquery(XQueryParser.XqueryContext ctx) { 
		visitChildren(ctx);
		
		//the variables appearing in return statements are already inside varsPos, now include the the rest
		for(int i = 0; i < treePatternVisited.size(); i++) {
			if(treePatternVisited.get(i) == false)
				XQueryUtils.buildVarsPos(varsPos, scans.get(i).getNavigationTreePattern(), varsPos.size());
		}	
		
		//we instantiate the XMLConstruct operator here rather than in exitReturnStat since we can have several return clauses but just one XMLConstruct operator.
		//we need to convert applyEach (ArrayList<String> to String[]
		String[] each_array = applyEach.toArray(new String[0]);
		//we need to convert applyFields (ArrayList<Integer>) to int[], it's ugly but necessary
		int[] fields_array = new int[applyFields.size()];
		int i = 0;
		for(Integer integer : applyFields)
			fields_array[i++] = integer.intValue();		
		ApplyConstruct apply = new ApplyConstruct("", each_array, "", fields_array, nestedApplys.toArray(new ApplyConstruct[0]));
		//instantiate the XMLConstruct operator and set "constructChild" as immediate descendant
		//no algebraic op was set as child of XMLConstruct, we just plug the first XMLScan as a bail out solution, simply plug the first tree pattern to the XMLConstruct object
		if(constructChild == null && scans.size() > 0)
			constructChild = scans.get(0);
		construct = new XMLConstruct(constructChild, apply, outputPath);

		return null;
	}

	/**
	 * letBinding
	 * store the left-side var so we can assign the xpath tree to it
	 * VAR := whatever
	 */
	public Void visitLetBinding(XQueryParser.LetBindingContext ctx) {
		
		currentStatement = StatementType.LET;
		
		//store the left-side var so we can assign the xpath tree to it
		lastVarLeftSide = ctx.VAR().getText();		
		//do visit, ctx.getChild(2) can be (pathExpr_xq | flwrexpr | aggrExpr | arithmeticExpr_xq | literal)
		visit(ctx.getChild(2));
		//reset last used TreePattern, var and node
		lastTreePattern = null;
		lastVarLeftSide = null;
		lastNode = null;
		lastSlashToken = 0;
		
		currentStatement = StatementType.LET;
		
		return null;	//// Java says must return something even when Void
	}

	/**
	 * forBinding
	 * store the left-side var so we can assign the xpath tree to it
	 */	
	public Void visitForBinding(XQueryParser.ForBindingContext ctx) { 
		
		currentStatement = StatementType.FOR;
		
		//store the left-side var so we can assign the xpath tree to it
		lastVarLeftSide = ctx.VAR().getText();
		//do visit
		visit(ctx.pathExpr_xq());
		//reset last used TreePattern, var and node
		lastTreePattern = null;
		lastVarLeftSide = null;
		lastNode = null;
		lastSlashToken = 0;
		
		currentStatement = StatementType.FOR;
		
		return null;
	}
	
	/**
	 * pathExprInner_xq_collection - enter
	 * Creates a XMLScanOperator, an associated tree pattern and a root node for the tree
	 */
	public Void visitPathExprInner_xq_collection(XQueryParser.PathExprInner_xq_collectionContext ctx) {
		//root node construction
		String rootTag = "";
		lastTreePattern = new TreePattern();
		int rootCode = PatternNode.globalNodeCounter.getAndIncrement();
		PatternNode rootNode = new PatternNode("", rootTag, rootCode, "", "", lastTreePattern);
		rootNode.addMatchingVariables(new Variable(lastVarLeftSide, Variable.VariableDataType.Content, rootNode));
		lastNode = rootNode;
		patternNodeMap.put(lastVarLeftSide, rootNode);		
		//tree pattern construction
		lastTreePattern.setRoot(rootNode);
		treePatterns.add(lastTreePattern);
		
		//XMLScan operator construction
		String pathDocuments = ctx.STRING_LITERAL().getText().substring(1, ctx.STRING_LITERAL().getText().length()-1);
		//add a new XMLScan object
		scans.add(new XMLScan(false, lastTreePattern, pathDocuments));
		treePatternVisited.add(false);
		//nothing to visit
		
		return null;
	}
	
	/**
	 * pathExprInner_xq_doc
	 * Creates a XMLScanOperator, an associated tree pattern and a root node for the tree
	 */
	public Void visitPathExprInner_xq_doc(XQueryParser.PathExprInner_xq_docContext ctx) { 
		//root node construction
		String rootTag = "";
		lastTreePattern = new TreePattern();
		int rootCode = PatternNode.globalNodeCounter.getAndIncrement();
		PatternNode rootNode = new PatternNode("", rootTag, rootCode, "", "", lastTreePattern);
		rootNode.addMatchingVariables(new Variable(lastVarLeftSide, Variable.VariableDataType.Content, rootNode));
		lastNode = rootNode;
		patternNodeMap.put(lastVarLeftSide, rootNode);
		//tree pattern construction
		lastTreePattern.setRoot(rootNode);
		treePatterns.add(lastTreePattern);
		
		//XMLScan operator construction
		String pathDocuments = ctx.STRING_LITERAL().getText().substring(1, ctx.STRING_LITERAL().getText().length()-1);
		//add a new XMLScan object
		scans.add(new XMLScan(false, lastTreePattern, pathDocuments));
		treePatternVisited.add(false);
		//nothing to visit
		
		return null;
	}	
	
	/**
	 * pathExpr slash (xpath)
	 * Update lastSlashToken with a slash ('/')
	 */
	public Void visitPathExpr_slash(XQueryParser.PathExpr_slashContext ctx) {
		lastSlashToken = XQueryLexer.SLASH;
		visit(ctx.relativePathExpr());
		lastSlashToken = 0;
		
		return null;
	}
	
	/**
	 * pathExpr slashslash (xpath)
	 * Update lastSlashToken with a slashslash ('//')
	 */
	public Void visitPathExpr_slashslash(XQueryParser.PathExpr_slashslashContext ctx) {
		lastSlashToken = XQueryLexer.SLASHSLASH;
		visit(ctx.relativePathExpr());
		lastSlashToken = 0;
		
		return null;
	}
	
	/**
	 * pathExpr_relativePathExpr (xpath)
	 */
	public Void visitPathExpr_relativePathExpr(XQueryParser.PathExpr_relativePathExprContext ctx) {
		visit(ctx.relativePathExpr());

		return null;
	}
	
	
	/**
	 * relativePathExpr2_slash (xpath)
	 * Decides that the next edge is parent-child
	 */
	public Void visitRelativePathExpr2_slash(XQueryParser.RelativePathExpr2_slashContext ctx) { 
		lastSlashToken = XQueryLexer.SLASH;	
		visit(ctx.stepExpr());

		return null;
	}

	/**
	 * relativePathExpr2_slashslash
	 * Decides that the next edge is ancestor-descendant
	 */
	public Void visitRelativePathExpr2_slashslash(XQueryParser.RelativePathExpr2_slashslashContext ctx) {
		lastSlashToken = XQueryLexer.SLASHSLASH;		
		visit(ctx.stepExpr());
		
		return null;
	}

	/**
	 * pathExprInner_xq_VAR
	 * Retrieve the appropriate tree and node for further expansion in the following case: another_var := VAR/whatever
	 */
	public Void visitPathExprInner_xq_VAR(XQueryParser.PathExprInner_xq_VARContext ctx) {
		String var_to_expand = ctx.VAR().getText();
		lastNode = patternNodeMap.get(var_to_expand);
		lastTreePattern = lastNode.getTreePattern();
		specialEdgeFlag = true;
		
		return null;
	}
	
	/**
	 * where
	 * Increase whereHits by one.
	 */
	public Void visitWhere(XQueryParser.WhereContext ctx) { 
		whereHits++;
		return visitChildren(ctx); 
	}
	
	/**
	 * groupBy
	 * Increase groupByHits by one.
	 */
	public Void visitGroupBy(XQueryParser.GroupByContext ctx) { 
		groupByHits++;
		return visitChildren(ctx); 
	}

	/**
	 * abbrevForwardStep (xpath)
	 * Decide whether the next qName represents an xml element (e.g. whatever/element) or attribute (e.g. whatever/@attribute)
	 */
	public Void visitAbbrevForwardStep(XQueryParser.AbbrevForwardStepContext ctx) {
		if(ctx.getText().startsWith("@")) 
			nextNodeIsAttribute = true;	//must exist another way of detecting tokens
		visit(ctx.nodeTest());
		
		return null;
	}

	/**
	 * nameTest_qName (in XPath)
	 * Create a pattern node, set it as content storage, attach it to the appropriate tree and store it in patternNodeMap
	 * Xpath: whatever/node/whatever
	 */
	public Void visitNameTest(XQueryParser.NameTestContext ctx) { 
		String tag = "";
		if(ctx.qName() != null)
			tag = ctx.qName().QNAME_TOKEN().getText();
		else if(ctx.getChild(0).getText().compareTo("div") == 0)
			tag = "div";
		else if(ctx.getChild(0).getText().compareTo("mod") == 0)
			tag = "mod";
		
		int nodeCode = PatternNode.globalNodeCounter.getAndIncrement();
		PatternNode node = new PatternNode("", tag, nodeCode, "", "", lastTreePattern);
		boolean parent = lastSlashToken == XQueryLexer.SLASH ? true : false;
		//handle let-nesting
		if(specialEdgeFlag && currentStatement == StatementType.LET) {
			lastNode.addEdge(node, parent, true, true);
			specialEdgeFlag = false;
			mappedApplys.put(lastVarLeftSide, (new ApplyConstruct("", new String[] {""}, "", null, new ApplyConstruct[0])));	//the null fields must be filled after varsPos is built
		}
		else
			lastNode.addEdge(node, parent, false, false); 		//parameters: addEdge(PatternNode child, boolean parent, boolean nested, boolean optional)

		lastNode.removeMatchingVariables(lastVarLeftSide);
		if(lastNode.checkAnyMatchingVariableStoresValue() == false)
			lastNode.setStoresValue(false);
		if(lastNode.checkAnyMatchingVariableStoresContent() == false)
			lastNode.setStoresContent(false);
		Variable newVar = new Variable(lastVarLeftSide, Variable.VariableDataType.Content, node);
		node.addMatchingVariables(newVar);
		node.setStoresContent(true);
		if(nextNodeIsAttribute) {
			//attributes store values
			newVar.dataType = Variable.VariableDataType.Value;
			node.setAttribute(true);
			node.setStoresValue(true);
			node.setStoresContent(false);
			nextNodeIsAttribute = false;
		}
		//update the affected variable so it points to this node
		patternNodeMap.put(lastVarLeftSide, node);
		lastNode = node;	//for further expansion

		return null;
	}


	/**
	 * textTest (in XPath)
	 * Set the last pattern node in the tree as value storage when the following situation is found in XPath: whatever/text() or whatever//text()
	 * NOTE: we currently treat both whatever/text() and whatever//text() as whatever/text(), this is something we need to change in the future (see Github's issue stratosphere/paxquery#1)
	 * TODO: treat whatever/text() and whatever//text() differently (will need to consider grammar rules pathExpr and relativePathExpr2 too)
	 */
	public Void visitTextTest(XQueryParser.TextTestContext ctx) {
		Variable matchingVar = lastNode.getMatchingVariable(lastVarLeftSide);
	
		if(matchingVar != null) {
			matchingVar.dataType = Variable.VariableDataType.Value;
			if(lastNode.checkAnyMatchingVariableStoresContent() == false)
				lastNode.setStoresContent(false);	//no vars storing content anymore
			lastNode.setStoresValue(true);
		}
		else {
			//lastVarLeftSide does not exist in lastNode.matchingVariables
			Variable newVar = new Variable(lastVarLeftSide, Variable.VariableDataType.Value, lastNode);
			lastNode.addMatchingVariables(newVar);
			lastNode.setStoresValue(true);
			patternNodeMap.put(lastVarLeftSide, lastNode);
		}

		return null;
	}
	
	/**
	 * returnStat
	 * Builds varsPos before parsing on. After that it visits the rules within the return
	 * and builds the data structures needed by XMLConstruct.
	 * 
	 * Terminates the program when an attribute is used as an XML value in the input query
	 * i.e.: <item>@attrib</item> rather than <item attrib="@attrib"></item>
	 */
	public Void visitReturnStat(XQueryParser.ReturnStatContext ctx) {
		insideReturn = true;
		returnXMLTags = new StringBuilder();
		//manually visit the next rule
		if(ctx.eleConst()!=null) {
			try {
				visitEleConst(ctx.eleConst());
			}
			catch(Exception e) {
				System.out.println("Exception: "+e.getMessage());
				e.printStackTrace();
				System.exit(-1);
			}
		}
		//either it is an aggrExpr rule
		else if(ctx.aggrExpr() != null)
			visitAggrExpr(ctx.aggrExpr());
		//or a VAR
		else if(ctx.getChild(1).getText().startsWith("$")) {
			int patternTreeIndex = XQueryUtils.findVarInPatternTree(scans, patternNodeMap, ctx.getChild(1).getText());
			if(patternTreeIndex != -1) {
				//for the case treePatternVisited.get(patternTreeIndex)==true the XMLScan associated to the returned variable is already in the algebraic tree, so we do nothing
				//for the case treePatternVisited.get(patternTreeIndex)==false the XMLScan associated to the returned variable has not been included in the algebraic tree, hence we plug the XMLScan to constructChild
				if(treePatternVisited.get(patternTreeIndex) == false) {
					constructChild = scans.get(patternTreeIndex);
					//add varsPos for this tree
					XQueryUtils.buildVarsPos(varsPos, scans.get(patternTreeIndex).getNavigationTreePattern(), varsPos.size());
					treePatternVisited.set(patternTreeIndex, true);
				}
				storeVarAndXMLText(ctx.getChild(1).getText());	
				//if the variable contains nested tuples we also add the corresponding ApplyConstruct object
				if(mappedApplys.containsKey(ctx.getChild(1).getText())) {
					ApplyConstruct ac = mappedApplys.get(ctx.getChild(1).getText());
					//now that varsPos has been updated we can check the varible's position
					ac.setFields(new int[] {0});
					nestedApplys.add(ac);
				}
			}
		}
		
		storeXMLText();		
		insideReturn = false;

		return null;
	}
	
	/**
	 * visitEleConst
	 * TODO: for the case eleConst : LT_S eaName att* CLOSE_OPENING_TAG we just plug the first XMLScan object to the XMLConstruct object. ANY BETTER SOLUTION?
	 */
	public Void visitEleConst(XQueryParser.EleConstContext ctx) {
		//append '<' and the element name
		returnXMLTags.append(ctx.LT_S()).append(ctx.eaName(0).getText());

		//manually visit all attributes
		for(int i = 0; i < ctx.children.size(); i++) {
			if(ctx.children.get(i).getPayload().getClass() == XQueryParser.AttContext.class) {
				visitAtt((XQueryParser.AttContext)ctx.children.get(i).getPayload());
			}
		}
		//eleConst : LT_S eaName att* CLOSE_OPENING_TAG
		if(ctx.CLOSE_OPENING_TAG()!=null) {
			returnXMLTags.append(ctx.CLOSE_OPENING_TAG());	
		}
		//eleConst : LT_S eaName att* (GT_S (eleConst | LEFTCURL eleConstInner RIGHTCURL )* OPEN_CLOSING_TAG (eaName) GT_S ) ;
		else if(ctx.GT_S().size() > 0) {
			returnXMLTags.append(ctx.GT_S(0).getText());

			for(int i = 0; i < ctx.children.size(); i++) {
				if(ctx.getChild(i).getPayload().getClass() == XQueryParser.EleConstContext.class) 
					visitEleConst((XQueryParser.EleConstContext)ctx.getChild(i).getPayload());
				else if(ctx.getChild(i).getText().startsWith("{")) {
					if(i+1 < ctx.children.size())
						visitEleConstInner((XQueryParser.EleConstInnerContext)ctx.getChild(i+1).getPayload());
				}					
			}
			//append the rest of the rule
			if(ctx.OPEN_CLOSING_TAG() != null) 
				returnXMLTags.append(ctx.OPEN_CLOSING_TAG().getText());
			if(ctx.eaName().size() > 1) 
				returnXMLTags.append(ctx.eaName(1).getText());
			if(ctx.GT_S().size() > 1) 
				returnXMLTags.append(ctx.GT_S(1).getText());			
		}
		return null;
	}

	/**
	 * att
	 */
	public Void visitAtt(XQueryParser.AttContext ctx) {
		returnXMLTags.append(" ").append(ctx.eaName().getText()).append('=');
		visitAttInner(ctx.attInner());
		return null;
	}

	/**
	 * visitAttInner - custom enter
	 */
	public Void visitAttInner(XQueryParser.AttInnerContext ctx) {
		//attInner : STRING_LITERAL
		if(ctx.STRING_LITERAL()!=null)
			returnXMLTags.append(ctx.STRING_LITERAL());
		//attInner : DOUBLE_QUOTE LEFTCURL attInner2 RIGHTCURL DOUBLE_QUOTE 
		else if(ctx.OPEN_ATTR_VAR_DOUBLE() != null && ctx.CLOSE_ATTR_VAR_DOUBLE() != null) {
			returnXMLTags.append("\"");
			//invoke attInner2
			visitAttInner2(ctx.attInner2());
			returnXMLTags.append("\"");
		}
		//attInner : SINGLE_QUOTE LEFTCURL attInner2 RIGHTCURL SINGLE_QUOTE
		else if(ctx.OPEN_ATTR_VAR_SINGLE() != null && ctx.CLOSE_ATTR_VAR_SINGLE() != null) {
			returnXMLTags.append("'");
			//invoke attInner2
			visitAttInner2(ctx.attInner2());
			returnXMLTags.append("'");
		}	
		return null;
	}	

	/**
	 * visitAttInner2
	 */
	public Void visitAttInner2(XQueryParser.AttInner2Context ctx) {
		//attInner2 : VAR
		if(ctx.VAR() != null) {
			int patternTreeIndex = XQueryUtils.findVarInPatternTree(scans, patternNodeMap, ctx.VAR().getText());
			if(patternTreeIndex != -1) {
				if(treePatternVisited.get(patternTreeIndex) == false) {
					treePatternVisited.set(patternTreeIndex, true);
					lastOp = thisOp;
					thisOp = scans.get(patternTreeIndex);
					//add varsPos for this tree
					XQueryUtils.buildVarsPos(varsPos, scans.get(patternTreeIndex).getNavigationTreePattern(), varsPos.size());
					
					//instantiate a new cartesian product if needed
					if(lastOp != null && thisOp != null) {
						//constructChild = scans.get(patternTreeIndex);
						constructChild = new CartesianProduct(lastOp, thisOp);
						thisOp = constructChild;
					}
					//or just plug the current operator to constructChild
					else if(lastOp == null && thisOp != null) {
						constructChild = thisOp;
					}
				}
				//store the var's position and the XML text so far
				storeVarAndXMLText(ctx.VAR().getText());
				//if the variable contais nested tuples we also add the corresponding ApplyConstruct object
				if(mappedApplys.containsKey(ctx.VAR().getText())) {
					ApplyConstruct ac = mappedApplys.get(ctx.VAR().getText());
					ac.setFields(new int[] {0});
					nestedApplys.add(ac);	
				}
			}
		}
		//attInner2 : aggrExpr
		else if(ctx.aggrExpr() != null) {
			//invoke aggrExpr
			visitAggrExpr(ctx.aggrExpr());
		}
		return null;
	}

	/**
	 * aggrExpr
	 * TODO: currently we store the aggregation expression as XML text: obviously we need to treat this differently
	 */
	public Void visitAggrExpr(XQueryParser.AggrExprContext ctx) {
		if(insideReturn) {
			//add the function name and the left parenthesis
			String aggrfunct = ctx.AGGR_FUNCT().getText();
			returnXMLTags.append(aggrfunct).append('(');
			
			int patternTreeIndex = XQueryUtils.findVarInPatternTree(scans, patternNodeMap, ctx.VAR().getText());
			if(patternTreeIndex != -1) {
				if(treePatternVisited.get(patternTreeIndex) == false) {
					treePatternVisited.set(patternTreeIndex, true);
					lastOp = thisOp;
					thisOp = scans.get(patternTreeIndex);
					//add varsPos for this tree
					XQueryUtils.buildVarsPos(varsPos, scans.get(patternTreeIndex).getNavigationTreePattern(), varsPos.size());

					//instantiate a new cartesian product if needed
					if(lastOp != null && thisOp != null) {
						constructChild = new CartesianProduct(lastOp, thisOp);
						thisOp = constructChild;
					}
					//or just plug the current operator to constructChild
					else if(lastOp == null && thisOp != null) {
						constructChild = thisOp;
					}
				}
				//store the var's position and the XML text so far
				storeVarAndXMLText(ctx.VAR().getText());
				//if the variable contais nested tuples we also add the corresponding ApplyConstruct object
				if(mappedApplys.containsKey(ctx.VAR().getText())) {
					ApplyConstruct ac = mappedApplys.get(ctx.VAR().getText());
					ac.setFields(new int[] {0});
					nestedApplys.add(ac);
				}
			}
			//add the right parenthesis
			returnXMLTags.append(')');
		}
		return null;
	}
	
	/**
	 * eleConstInner
	 * Terminates the execution if an attribute is used in the input query as an XML value
	 * i.e.: <item>@attrib</item> rather than <item attrib="@attrib"></item>
	 * 
	 * TODO: if a variable within an XML element stores an attribute, then we currently terminate the execution
	 * (since that is illegal). What we really should do is including it in the element's XML tag as an attribute
	 * e.g: <item>@attrib</item> --> <item attrib="@attrib"></item>
	 */
	public Void visitEleConstInner(XQueryParser.EleConstInnerContext ctx) {
		//////////////////////////////////////////////////////////////////////////////////
		//check if Cartesian Product is needed
		int lastTreePatternVisited = -1;
		//if(ctx.VAR().size() > 0 && whereHits == 0 && groupByHits == 0) {
		if(ctx.VAR().size() > 0) {
			for(int i = 0; i < ctx.VAR().size(); i++) {
				String varName = ctx.VAR().get(i).getText();
				//get which tree ctx.VAR().get(i) is in
				int thisVarTreeIndex = XQueryUtils.findVarInPatternTree(scans, patternNodeMap, varName);
				if(thisVarTreeIndex != -1 && treePatternVisited.get(thisVarTreeIndex) == false) {
					treePatternVisited.set(thisVarTreeIndex, true);
					lastTreePatternVisited = thisVarTreeIndex;
					lastOp = thisOp;
					thisOp = scans.get(thisVarTreeIndex);
					//add varsPos for this tree
					XQueryUtils.buildVarsPos(varsPos, scans.get(thisVarTreeIndex).getNavigationTreePattern(), varsPos.size());
					
					//instantiate a new cartesian product if needed
					if(lastOp!=null && thisOp!=null) {
						constructChild = new CartesianProduct(lastOp, thisOp);
						thisOp = constructChild;
					}
				}
			}
		}
		
		//one variable in tree pattern was found, no cartesian product was built, we just plug the appropriate tree pattern to the XMLConstruct
		if(lastOp==null && thisOp!=null && lastTreePatternVisited != -1)
			//constructChild = scans.get(lastTreePatternVisited);
			constructChild = thisOp;
		//no variable in tree pattern was found, simply plug the first tree pattern to the XMLConstruct object as a bail out solution
		else if(lastOp==null && thisOp == null && scans.size() > 0)
			constructChild = scans.get(0);
		//////////////////////////////////////////////////////////////////////////////////		
		//////////////////////////////////////////////////////////////////////////////////
		for(int i = 0; i < ctx.children.size(); i++) {
			ParseTree child = ctx.getChild(i);
			//an aggrExpr rule
			if(child.getPayload().getClass() == XQueryParser.AggrExprContext.class)
				visitAggrExpr((XQueryParser.AggrExprContext)child.getPayload());
			//a variable
			else if(child.getText().startsWith("$")) {
				//check that the variable is not an attribute, otherwise throw exception
				PatternNode node = patternNodeMap.get(child.getText());
				if(node != null && node.isAttribute())
				{
					System.out.println("It is illegal to use an attribute as an XML value");
					System.exit(-1);
				}
				storeVarAndXMLText(child.getText());					
				//if the variable contais nested tuples we also add the corresponding ApplyConstruct object
				if(mappedApplys.containsKey(child.getText())) {
					ApplyConstruct ac = mappedApplys.get(child.getText());
					ac.setFields(new int[] {0});
					nestedApplys.add(ac);					
				}
			}
			//we don't care about the commas
		}
		//////////////////////////////////////////////////////////////////////////////////
		
		return null;
	}	
	
	/**
	 * Appends a new variable position to the applyFields data structure
	 * @param var_text the name of the variable
	 */
	private void storeVarAndXMLText(String var_text) {
		//store the current piece of XML string in applyEach
		storeXMLText();
		//store the variable overall position position in the trees into applyFields
		applyFields.add(varsPos.get(var_text).positionInForest);
	}

	/**
	 * Stores the text in returnXMLTags in the applyEach data structure
	 */
	private void storeXMLText() {
		//store the current piece of XML string in applyEach (even if it's empty)
		applyEach.add(returnXMLTags.toString());
		//clear the StringBuilder object (faster than instantiating a new one)
		returnXMLTags.setLength(0);
	}
}