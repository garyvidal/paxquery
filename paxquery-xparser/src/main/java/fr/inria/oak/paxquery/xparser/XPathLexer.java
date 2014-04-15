// Generated from XPath.g4 by ANTLR 4.2
package fr.inria.oak.paxquery.xparser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XPathLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__17=1, T__16=2, T__15=3, T__14=4, T__13=5, T__12=6, T__11=7, T__10=8, 
		T__9=9, T__8=10, T__7=11, T__6=12, T__5=13, T__4=14, T__3=15, T__2=16, 
		T__1=17, T__0=18, TEXTFUNCTION=19, SLASH=20, SLASHSLASH=21, OR=22, AND=23, 
		NOT=24, EQ=25, EQ_S=26, NE=27, NE_S=28, LT=29, LT_S=30, LE=31, LE_S=32, 
		GT=33, GT_S=34, GE=35, GE_S=36, VAR=37, LEFTCURL=38, RIGHTCURL=39, OPEN_TAG=40, 
		CLOSE_TAG=41, QNAME_TOKEN=42, STRING_LITERAL=43, REFERENCE=44, ENTITY_REF=45, 
		CHAR_REF=46, INTEGER_LITERAL=47, DECIMAL_LITERAL=48, DIGITS=49, WS=50;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'substring'", "'true'", "'ceiling'", "'mod'", "'['", "'|'", "'concat'", 
		"']'", "'@'", "'div'", "'('", "'floor'", "')'", "'*'", "'+'", "','", "'-'", 
		"'false'", "'text()'", "'/'", "'//'", "'or'", "'and'", "'not'", "'eq'", 
		"'='", "'ne'", "'!='", "'lt'", "'<'", "'le'", "'<='", "'gt'", "'>'", "'ge'", 
		"'>='", "VAR", "'{'", "'}'", "OPEN_TAG", "CLOSE_TAG", "QNAME_TOKEN", "STRING_LITERAL", 
		"REFERENCE", "ENTITY_REF", "CHAR_REF", "INTEGER_LITERAL", "DECIMAL_LITERAL", 
		"DIGITS", "WS"
	};
	public static final String[] ruleNames = {
		"T__17", "T__16", "T__15", "T__14", "T__13", "T__12", "T__11", "T__10", 
		"T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", 
		"T__0", "TEXTFUNCTION", "SLASH", "SLASHSLASH", "OR", "AND", "NOT", "EQ", 
		"EQ_S", "NE", "NE_S", "LT", "LT_S", "LE", "LE_S", "GT", "GT_S", "GE", 
		"GE_S", "VAR", "LEFTCURL", "RIGHTCURL", "OPEN_TAG", "CLOSE_TAG", "QNAME_TOKEN", 
		"STRING_LITERAL", "REFERENCE", "ENTITY_REF", "CHAR_REF", "INTEGER_LITERAL", 
		"DECIMAL_LITERAL", "DIGITS", "NCNAME_TOK", "NMSTART", "NMCHAR", "LETTER", 
		"BASE_CHAR", "IDEOGRAPHIC", "COMBINING_CHAR", "DIGIT", "EXTENDER", "LOCAL_PART", 
		"WS"
	};


	public XPathLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "XPath.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\64\u017a\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t"+
		"=\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3"+
		"\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3"+
		"\32\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3"+
		"\37\3\37\3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\3$\3$\3$\3%\3%\3%\3&\3&\3"+
		"&\3\'\3\'\3(\3(\3)\3)\3)\3*\3*\3*\3+\3+\3+\5+\u010a\n+\3+\3+\3,\3,\3,"+
		"\7,\u0111\n,\f,\16,\u0114\13,\3,\3,\3,\3,\7,\u011a\n,\f,\16,\u011d\13"+
		",\3,\5,\u0120\n,\3-\3-\5-\u0124\n-\3.\3.\3.\3.\3/\3/\3/\3/\6/\u012e\n"+
		"/\r/\16/\u012f\3/\3/\3/\3/\3/\3/\6/\u0138\n/\r/\16/\u0139\3/\5/\u013d"+
		"\n/\3\60\3\60\3\61\3\61\3\61\3\61\3\61\7\61\u0146\n\61\f\61\16\61\u0149"+
		"\13\61\5\61\u014b\n\61\3\62\6\62\u014e\n\62\r\62\16\62\u014f\3\63\3\63"+
		"\7\63\u0154\n\63\f\63\16\63\u0157\13\63\3\64\3\64\5\64\u015b\n\64\3\65"+
		"\3\65\3\65\3\65\3\65\5\65\u0162\n\65\3\66\3\66\5\66\u0166\n\66\3\67\3"+
		"\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\6=\u0175\n=\r=\16=\u0176\3=\3=\2"+
		"\2>\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\2g\2i\2k\2m\2"+
		"o\2q\2s\2u\2w\2y\64\3\2\r\5\2$$}}\177\177\5\2))}}\177\177\3\2\62;\5\2"+
		"\62;CHch\4\2/\60aa\u00cb\2C\\c|\u00c2\u00d8\u00da\u00f8\u00fa\u0133\u0136"+
		"\u0140\u0143\u014a\u014c\u0180\u0182\u01c5\u01cf\u01f2\u01f6\u01f7\u01fc"+
		"\u0219\u0252\u02aa\u02bd\u02c3\u0388\u0388\u038a\u038c\u038e\u038e\u0390"+
		"\u03a3\u03a5\u03d0\u03d2\u03d8\u03dc\u03dc\u03de\u03de\u03e0\u03e0\u03e2"+
		"\u03e2\u03e4\u03f5\u0403\u040e\u0410\u0451\u0453\u045e\u0460\u0483\u0492"+
		"\u04c6\u04c9\u04ca\u04cd\u04ce\u04d2\u04ed\u04f0\u04f7\u04fa\u04fb\u0533"+
		"\u0558\u055b\u055b\u0563\u0588\u05d2\u05ec\u05f2\u05f4\u0623\u063c\u0643"+
		"\u064c\u0673\u06b9\u06bc\u06c0\u06c2\u06d0\u06d2\u06d5\u06d7\u06d7\u06e7"+
		"\u06e8\u0907\u093b\u093f\u093f\u095a\u0963\u0987\u098e\u0991\u0992\u0995"+
		"\u09aa\u09ac\u09b2\u09b4\u09b4\u09b8\u09bb\u09de\u09df\u09e1\u09e3\u09f2"+
		"\u09f3\u0a07\u0a0c\u0a11\u0a12\u0a15\u0a2a\u0a2c\u0a32\u0a34\u0a35\u0a37"+
		"\u0a38\u0a3a\u0a3b\u0a5b\u0a5e\u0a60\u0a60\u0a74\u0a76\u0a87\u0a8d\u0a8f"+
		"\u0a8f\u0a91\u0a93\u0a95\u0aaa\u0aac\u0ab2\u0ab4\u0ab5\u0ab7\u0abb\u0abf"+
		"\u0abf\u0ae2\u0ae2\u0b07\u0b0e\u0b11\u0b12\u0b15\u0b2a\u0b2c\u0b32\u0b34"+
		"\u0b35\u0b38\u0b3b\u0b3f\u0b3f\u0b5e\u0b5f\u0b61\u0b63\u0b87\u0b8c\u0b90"+
		"\u0b92\u0b94\u0b97\u0b9b\u0b9c\u0b9e\u0b9e\u0ba0\u0ba1\u0ba5\u0ba6\u0baa"+
		"\u0bac\u0bb0\u0bb7\u0bb9\u0bbb\u0c07\u0c0e\u0c10\u0c12\u0c14\u0c2a\u0c2c"+
		"\u0c35\u0c37\u0c3b\u0c62\u0c63\u0c87\u0c8e\u0c90\u0c92\u0c94\u0caa\u0cac"+
		"\u0cb5\u0cb7\u0cbb\u0ce0\u0ce0\u0ce2\u0ce3\u0d07\u0d0e\u0d10\u0d12\u0d14"+
		"\u0d2a\u0d2c\u0d3b\u0d62\u0d63\u0e03\u0e30\u0e32\u0e32\u0e34\u0e35\u0e42"+
		"\u0e47\u0e83\u0e84\u0e86\u0e86\u0e89\u0e8a\u0e8c\u0e8c\u0e8f\u0e8f\u0e96"+
		"\u0e99\u0e9b\u0ea1\u0ea3\u0ea5\u0ea7\u0ea7\u0ea9\u0ea9\u0eac\u0ead\u0eaf"+
		"\u0eb0\u0eb2\u0eb2\u0eb4\u0eb5\u0ebf\u0ebf\u0ec2\u0ec6\u0f42\u0f49\u0f4b"+
		"\u0f6b\u10a2\u10c7\u10d2\u10f8\u1102\u1102\u1104\u1105\u1107\u1109\u110b"+
		"\u110b\u110d\u110e\u1110\u1114\u113e\u113e\u1140\u1140\u1142\u1142\u114e"+
		"\u114e\u1150\u1150\u1152\u1152\u1156\u1157\u115b\u115b\u1161\u1163\u1165"+
		"\u1165\u1167\u1167\u1169\u1169\u116b\u116b\u116f\u1170\u1174\u1175\u1177"+
		"\u1177\u11a0\u11a0\u11aa\u11aa\u11ad\u11ad\u11b0\u11b1\u11b9\u11ba\u11bc"+
		"\u11bc\u11be\u11c4\u11ed\u11ed\u11f2\u11f2\u11fb\u11fb\u1e02\u1e9d\u1ea2"+
		"\u1efb\u1f02\u1f17\u1f1a\u1f1f\u1f22\u1f47\u1f4a\u1f4f\u1f52\u1f59\u1f5b"+
		"\u1f5b\u1f5d\u1f5d\u1f5f\u1f5f\u1f61\u1f7f\u1f82\u1fb6\u1fb8\u1fbe\u1fc0"+
		"\u1fc0\u1fc4\u1fc6\u1fc8\u1fce\u1fd2\u1fd5\u1fd8\u1fdd\u1fe2\u1fee\u1ff4"+
		"\u1ff6\u1ff8\u1ffe\u2128\u2128\u212c\u212d\u2130\u2130\u2182\u2184\u3043"+
		"\u3096\u30a3\u30fc\u3107\u312e\uac02\ud7a5\5\2\u3009\u3009\u3023\u302b"+
		"\u4e02\u9fa7X\2\u0302\u0347\u0362\u0363\u0485\u0488\u0593\u05a3\u05a5"+
		"\u05bb\u05bd\u05bf\u05c1\u05c1\u05c3\u05c4\u05c6\u05c6\u064d\u0654\u0672"+
		"\u0672\u06d8\u06e6\u06e9\u06ea\u06ec\u06ef\u0903\u0905\u093e\u093e\u0940"+
		"\u094f\u0953\u0956\u0964\u0965\u0983\u0985\u09be\u09be\u09c0\u09c6\u09c9"+
		"\u09ca\u09cd\u09cf\u09d9\u09d9\u09e4\u09e5\u0a04\u0a04\u0a3e\u0a3e\u0a40"+
		"\u0a44\u0a49\u0a4a\u0a4d\u0a4f\u0a72\u0a73\u0a83\u0a85\u0abe\u0abe\u0ac0"+
		"\u0ac7\u0ac9\u0acb\u0acd\u0acf\u0b03\u0b05\u0b3e\u0b3e\u0b40\u0b45\u0b49"+
		"\u0b4a\u0b4d\u0b4f\u0b58\u0b59\u0b84\u0b85\u0bc0\u0bc4\u0bc8\u0bca\u0bcc"+
		"\u0bcf\u0bd9\u0bd9\u0c03\u0c05\u0c40\u0c46\u0c48\u0c4a\u0c4c\u0c4f\u0c57"+
		"\u0c58\u0c84\u0c85\u0cc0\u0cc6\u0cc8\u0cca\u0ccc\u0ccf\u0cd7\u0cd8\u0d04"+
		"\u0d05\u0d40\u0d45\u0d48\u0d4a\u0d4c\u0d4f\u0d59\u0d59\u0e33\u0e33\u0e36"+
		"\u0e3c\u0e49\u0e50\u0eb3\u0eb3\u0eb6\u0ebb\u0ebd\u0ebe\u0eca\u0ecf\u0f1a"+
		"\u0f1b\u0f37\u0f37\u0f39\u0f39\u0f3b\u0f3b\u0f40\u0f41\u0f73\u0f86\u0f88"+
		"\u0f8d\u0f92\u0f97\u0f99\u0f99\u0f9b\u0faf\u0fb3\u0fb9\u0fbb\u0fbb\u20d2"+
		"\u20de\u20e3\u20e3\u302c\u3031\u309b\u309c\21\2\62;\u0662\u066b\u06f2"+
		"\u06fb\u0968\u0971\u09e8\u09f1\u0a68\u0a71\u0ae8\u0af1\u0b68\u0b71\u0be9"+
		"\u0bf1\u0c68\u0c71\u0ce8\u0cf1\u0d68\u0d71\u0e52\u0e5b\u0ed2\u0edb\u0f22"+
		"\u0f2b\f\2\u00b9\u00b9\u02d2\u02d3\u0389\u0389\u0642\u0642\u0e48\u0e48"+
		"\u0ec8\u0ec8\u3007\u3007\u3033\u3037\u309f\u30a0\u30fe\u3100\5\2\13\f"+
		"\17\17\"\"\u0184\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E"+
		"\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2"+
		"\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2"+
		"\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2y\3\2\2\2\3{\3\2\2\2\5\u0085\3\2\2"+
		"\2\7\u008a\3\2\2\2\t\u0092\3\2\2\2\13\u0096\3\2\2\2\r\u0098\3\2\2\2\17"+
		"\u009a\3\2\2\2\21\u00a1\3\2\2\2\23\u00a3\3\2\2\2\25\u00a5\3\2\2\2\27\u00a9"+
		"\3\2\2\2\31\u00ab\3\2\2\2\33\u00b1\3\2\2\2\35\u00b3\3\2\2\2\37\u00b5\3"+
		"\2\2\2!\u00b7\3\2\2\2#\u00b9\3\2\2\2%\u00bb\3\2\2\2\'\u00c1\3\2\2\2)\u00c8"+
		"\3\2\2\2+\u00ca\3\2\2\2-\u00cd\3\2\2\2/\u00d0\3\2\2\2\61\u00d4\3\2\2\2"+
		"\63\u00d8\3\2\2\2\65\u00db\3\2\2\2\67\u00dd\3\2\2\29\u00e0\3\2\2\2;\u00e3"+
		"\3\2\2\2=\u00e6\3\2\2\2?\u00e8\3\2\2\2A\u00eb\3\2\2\2C\u00ee\3\2\2\2E"+
		"\u00f1\3\2\2\2G\u00f3\3\2\2\2I\u00f6\3\2\2\2K\u00f9\3\2\2\2M\u00fc\3\2"+
		"\2\2O\u00fe\3\2\2\2Q\u0100\3\2\2\2S\u0103\3\2\2\2U\u0109\3\2\2\2W\u011f"+
		"\3\2\2\2Y\u0123\3\2\2\2[\u0125\3\2\2\2]\u013c\3\2\2\2_\u013e\3\2\2\2a"+
		"\u014a\3\2\2\2c\u014d\3\2\2\2e\u0151\3\2\2\2g\u015a\3\2\2\2i\u0161\3\2"+
		"\2\2k\u0165\3\2\2\2m\u0167\3\2\2\2o\u0169\3\2\2\2q\u016b\3\2\2\2s\u016d"+
		"\3\2\2\2u\u016f\3\2\2\2w\u0171\3\2\2\2y\u0174\3\2\2\2{|\7u\2\2|}\7w\2"+
		"\2}~\7d\2\2~\177\7u\2\2\177\u0080\7v\2\2\u0080\u0081\7t\2\2\u0081\u0082"+
		"\7k\2\2\u0082\u0083\7p\2\2\u0083\u0084\7i\2\2\u0084\4\3\2\2\2\u0085\u0086"+
		"\7v\2\2\u0086\u0087\7t\2\2\u0087\u0088\7w\2\2\u0088\u0089\7g\2\2\u0089"+
		"\6\3\2\2\2\u008a\u008b\7e\2\2\u008b\u008c\7g\2\2\u008c\u008d\7k\2\2\u008d"+
		"\u008e\7n\2\2\u008e\u008f\7k\2\2\u008f\u0090\7p\2\2\u0090\u0091\7i\2\2"+
		"\u0091\b\3\2\2\2\u0092\u0093\7o\2\2\u0093\u0094\7q\2\2\u0094\u0095\7f"+
		"\2\2\u0095\n\3\2\2\2\u0096\u0097\7]\2\2\u0097\f\3\2\2\2\u0098\u0099\7"+
		"~\2\2\u0099\16\3\2\2\2\u009a\u009b\7e\2\2\u009b\u009c\7q\2\2\u009c\u009d"+
		"\7p\2\2\u009d\u009e\7e\2\2\u009e\u009f\7c\2\2\u009f\u00a0\7v\2\2\u00a0"+
		"\20\3\2\2\2\u00a1\u00a2\7_\2\2\u00a2\22\3\2\2\2\u00a3\u00a4\7B\2\2\u00a4"+
		"\24\3\2\2\2\u00a5\u00a6\7f\2\2\u00a6\u00a7\7k\2\2\u00a7\u00a8\7x\2\2\u00a8"+
		"\26\3\2\2\2\u00a9\u00aa\7*\2\2\u00aa\30\3\2\2\2\u00ab\u00ac\7h\2\2\u00ac"+
		"\u00ad\7n\2\2\u00ad\u00ae\7q\2\2\u00ae\u00af\7q\2\2\u00af\u00b0\7t\2\2"+
		"\u00b0\32\3\2\2\2\u00b1\u00b2\7+\2\2\u00b2\34\3\2\2\2\u00b3\u00b4\7,\2"+
		"\2\u00b4\36\3\2\2\2\u00b5\u00b6\7-\2\2\u00b6 \3\2\2\2\u00b7\u00b8\7.\2"+
		"\2\u00b8\"\3\2\2\2\u00b9\u00ba\7/\2\2\u00ba$\3\2\2\2\u00bb\u00bc\7h\2"+
		"\2\u00bc\u00bd\7c\2\2\u00bd\u00be\7n\2\2\u00be\u00bf\7u\2\2\u00bf\u00c0"+
		"\7g\2\2\u00c0&\3\2\2\2\u00c1\u00c2\7v\2\2\u00c2\u00c3\7g\2\2\u00c3\u00c4"+
		"\7z\2\2\u00c4\u00c5\7v\2\2\u00c5\u00c6\7*\2\2\u00c6\u00c7\7+\2\2\u00c7"+
		"(\3\2\2\2\u00c8\u00c9\7\61\2\2\u00c9*\3\2\2\2\u00ca\u00cb\7\61\2\2\u00cb"+
		"\u00cc\7\61\2\2\u00cc,\3\2\2\2\u00cd\u00ce\7q\2\2\u00ce\u00cf\7t\2\2\u00cf"+
		".\3\2\2\2\u00d0\u00d1\7c\2\2\u00d1\u00d2\7p\2\2\u00d2\u00d3\7f\2\2\u00d3"+
		"\60\3\2\2\2\u00d4\u00d5\7p\2\2\u00d5\u00d6\7q\2\2\u00d6\u00d7\7v\2\2\u00d7"+
		"\62\3\2\2\2\u00d8\u00d9\7g\2\2\u00d9\u00da\7s\2\2\u00da\64\3\2\2\2\u00db"+
		"\u00dc\7?\2\2\u00dc\66\3\2\2\2\u00dd\u00de\7p\2\2\u00de\u00df\7g\2\2\u00df"+
		"8\3\2\2\2\u00e0\u00e1\7#\2\2\u00e1\u00e2\7?\2\2\u00e2:\3\2\2\2\u00e3\u00e4"+
		"\7n\2\2\u00e4\u00e5\7v\2\2\u00e5<\3\2\2\2\u00e6\u00e7\7>\2\2\u00e7>\3"+
		"\2\2\2\u00e8\u00e9\7n\2\2\u00e9\u00ea\7g\2\2\u00ea@\3\2\2\2\u00eb\u00ec"+
		"\7>\2\2\u00ec\u00ed\7?\2\2\u00edB\3\2\2\2\u00ee\u00ef\7i\2\2\u00ef\u00f0"+
		"\7v\2\2\u00f0D\3\2\2\2\u00f1\u00f2\7@\2\2\u00f2F\3\2\2\2\u00f3\u00f4\7"+
		"i\2\2\u00f4\u00f5\7g\2\2\u00f5H\3\2\2\2\u00f6\u00f7\7@\2\2\u00f7\u00f8"+
		"\7?\2\2\u00f8J\3\2\2\2\u00f9\u00fa\7&\2\2\u00fa\u00fb\5e\63\2\u00fbL\3"+
		"\2\2\2\u00fc\u00fd\7}\2\2\u00fdN\3\2\2\2\u00fe\u00ff\7\177\2\2\u00ffP"+
		"\3\2\2\2\u0100\u0101\5=\37\2\u0101\u0102\7\61\2\2\u0102R\3\2\2\2\u0103"+
		"\u0104\7\61\2\2\u0104\u0105\5E#\2\u0105T\3\2\2\2\u0106\u0107\5e\63\2\u0107"+
		"\u0108\7<\2\2\u0108\u010a\3\2\2\2\u0109\u0106\3\2\2\2\u0109\u010a\3\2"+
		"\2\2\u010a\u010b\3\2\2\2\u010b\u010c\5w<\2\u010cV\3\2\2\2\u010d\u0112"+
		"\7$\2\2\u010e\u0111\5y=\2\u010f\u0111\n\2\2\2\u0110\u010e\3\2\2\2\u0110"+
		"\u010f\3\2\2\2\u0111\u0114\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2"+
		"\2\2\u0113\u0115\3\2\2\2\u0114\u0112\3\2\2\2\u0115\u0120\7$\2\2\u0116"+
		"\u011b\7)\2\2\u0117\u011a\5y=\2\u0118\u011a\n\3\2\2\u0119\u0117\3\2\2"+
		"\2\u0119\u0118\3\2\2\2\u011a\u011d\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c"+
		"\3\2\2\2\u011c\u011e\3\2\2\2\u011d\u011b\3\2\2\2\u011e\u0120\7)\2\2\u011f"+
		"\u010d\3\2\2\2\u011f\u0116\3\2\2\2\u0120X\3\2\2\2\u0121\u0124\5[.\2\u0122"+
		"\u0124\5]/\2\u0123\u0121\3\2\2\2\u0123\u0122\3\2\2\2\u0124Z\3\2\2\2\u0125"+
		"\u0126\7(\2\2\u0126\u0127\5e\63\2\u0127\u0128\7=\2\2\u0128\\\3\2\2\2\u0129"+
		"\u012a\7(\2\2\u012a\u012b\7%\2\2\u012b\u012d\3\2\2\2\u012c\u012e\t\4\2"+
		"\2\u012d\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130"+
		"\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u013d\7=\2\2\u0132\u0133\7(\2\2\u0133"+
		"\u0134\7%\2\2\u0134\u0135\7z\2\2\u0135\u0137\3\2\2\2\u0136\u0138\t\5\2"+
		"\2\u0137\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u0137\3\2\2\2\u0139\u013a"+
		"\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013d\7=\2\2\u013c\u0129\3\2\2\2\u013c"+
		"\u0132\3\2\2\2\u013d^\3\2\2\2\u013e\u013f\5c\62\2\u013f`\3\2\2\2\u0140"+
		"\u0141\7\60\2\2\u0141\u014b\5c\62\2\u0142\u0143\5c\62\2\u0143\u0147\7"+
		"\60\2\2\u0144\u0146\t\4\2\2\u0145\u0144\3\2\2\2\u0146\u0149\3\2\2\2\u0147"+
		"\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148\u014b\3\2\2\2\u0149\u0147\3\2"+
		"\2\2\u014a\u0140\3\2\2\2\u014a\u0142\3\2\2\2\u014bb\3\2\2\2\u014c\u014e"+
		"\5s:\2\u014d\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u014d\3\2\2\2\u014f"+
		"\u0150\3\2\2\2\u0150d\3\2\2\2\u0151\u0155\5g\64\2\u0152\u0154\5i\65\2"+
		"\u0153\u0152\3\2\2\2\u0154\u0157\3\2\2\2\u0155\u0153\3\2\2\2\u0155\u0156"+
		"\3\2\2\2\u0156f\3\2\2\2\u0157\u0155\3\2\2\2\u0158\u015b\5k\66\2\u0159"+
		"\u015b\7a\2\2\u015a\u0158\3\2\2\2\u015a\u0159\3\2\2\2\u015bh\3\2\2\2\u015c"+
		"\u0162\5k\66\2\u015d\u0162\5q9\2\u015e\u0162\5u;\2\u015f\u0162\5s:\2\u0160"+
		"\u0162\t\6\2\2\u0161\u015c\3\2\2\2\u0161\u015d\3\2\2\2\u0161\u015e\3\2"+
		"\2\2\u0161\u015f\3\2\2\2\u0161\u0160\3\2\2\2\u0162j\3\2\2\2\u0163\u0166"+
		"\5m\67\2\u0164\u0166\5o8\2\u0165\u0163\3\2\2\2\u0165\u0164\3\2\2\2\u0166"+
		"l\3\2\2\2\u0167\u0168\t\7\2\2\u0168n\3\2\2\2\u0169\u016a\t\b\2\2\u016a"+
		"p\3\2\2\2\u016b\u016c\t\t\2\2\u016cr\3\2\2\2\u016d\u016e\t\n\2\2\u016e"+
		"t\3\2\2\2\u016f\u0170\t\13\2\2\u0170v\3\2\2\2\u0171\u0172\5e\63\2\u0172"+
		"x\3\2\2\2\u0173\u0175\t\f\2\2\u0174\u0173\3\2\2\2\u0175\u0176\3\2\2\2"+
		"\u0176\u0174\3\2\2\2\u0176\u0177\3\2\2\2\u0177\u0178\3\2\2\2\u0178\u0179"+
		"\b=\2\2\u0179z\3\2\2\2\25\2\u0109\u0110\u0112\u0119\u011b\u011f\u0123"+
		"\u012f\u0139\u013c\u0147\u014a\u014f\u0155\u015a\u0161\u0165\u0176\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}