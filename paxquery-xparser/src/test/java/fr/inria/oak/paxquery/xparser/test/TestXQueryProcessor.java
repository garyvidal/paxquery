package fr.inria.oak.paxquery.xparser.test;


import static org.junit.Assert.*;

//import javax.xml.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
//import org.junit.runners.Parameterized.Parameters;
import org.w3c.dom.Document;
//import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.inria.oak.paxquery.xparser.XQueryMain;

@RunWith(Parameterized.class)
public class TestXQueryProcessor 
{
	public String query;
	public String output;
	
	public TestXQueryProcessor(String query, String output)
	{
		this.query = query;
		this.output = output;
	}
	
	@Parameters 
	public static Collection<String[]> dataSets() throws SAXException, IOException, ParserConfigurationException 
	{
		ArrayList<String[]> testCases = new ArrayList<String[]>();
		
		String testCaseFilePath = "src/test/resources/treepatt-xquery-tests.xml";
		
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse (new File(testCaseFilePath));
        
        // normalize text representation
        doc.getDocumentElement().normalize();
        System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());
		
        NodeList nodeList = doc.getElementsByTagName("test");
        System.out.println("Number of tests: " + nodeList.getLength());

        for(int i=0; i<nodeList.getLength(); i++) {
        //	testCases.add(new String[] {nodeList.item(i).getAttributes().getNamedItem("value").getNodeValue(), 
        //			nodeList.item(i).getAttributes().getNamedItem("output").getNodeValue()});
/*        	String[] testcase = new String[2];
        	testcase[0] = nodeList.item(i).getAttributes().getNamedItem("value").getNodeValue();
        	testcase[1] = nodeList.item(i).getAttributes().getNamedItem("answer").getNodeValue();
        	testCases.add(testcase);*/
        	String[] testcase = new String[2];
        	Element element = (Element)nodeList.item(i);
        	testcase[0] = element.getElementsByTagName("value").item(0).getTextContent();
        	testcase[1] = element.getElementsByTagName("output").item(0).getTextContent();
        	testCases.add(testcase);
        }
        return testCases;        
	}

	@Test
	public void test() 
	{
		System.out.println("query: "+query);
		System.out.println("output: "+output);
		//assertEquals(output.trim(), XQueryMain.test_processor(query).trim());
		assertEquals(output.replaceAll("\\s",""), XQueryMain.test_processor(query).replaceAll("\\s",""));
	}

}