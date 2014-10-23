// $ANTLR 3.3 Nov 30, 2010 12:50:56 C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g 2012-07-17 03:26:49

    package platu.lpn.io;
    
    import java.util.Map.Entry;
    import java.util.HashMap;
    import java.util.LinkedList;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.Arrays;
    import platu.lpn.LPN;
    import platu.lpn.VarSet;
    import platu.lpn.LpnTranList;
    import platu.lpn.LPNTran;
    import platu.common.DualHashMap;
    import platu.lpn.VarExpr;
    import platu.lpn.VarExprList;
    import platu.stategraph.StateGraph;
    import platu.project.Project;
    import platu.expression.*; 


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class PlatuGrammarParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ID", "INT", "LPAREN", "RPAREN", "TRUE", "FALSE", "QMARK", "COLON", "SEMICOLON", "PERIOD", "UNDERSCORE", "COMMA", "QUOTE", "MODULE", "NAME", "INPUT", "OUTPUT", "INTERNAL", "MARKING", "STATE_VECTOR", "TRANSITION", "LABEL", "PRESET", "POSTSET", "PLUS", "MINUS", "TIMES", "DIV", "MOD", "EQUALS", "GREATER", "LESS", "GREATER_EQUAL", "LESS_EQUAL", "EQUIV", "NOT_EQUIV", "NEGATION", "AND", "OR", "IMPLICATION", "BITWISE_NEGATION", "BITWISE_AND", "BITWISE_OR", "BITWISE_XOR", "BITWISE_LSHIFT", "BITWISE_RSHIFT", "LETTER", "DIGIT", "WS", "COMMENT", "MULTILINECOMMENT", "XMLCOMMENT", "IGNORE", "'main'", "'const'", "'['", "']'", "'inst'", "'assert'", "'inf'"
    };
    public static final int EOF=-1;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__59=59;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int ID=4;
    public static final int INT=5;
    public static final int LPAREN=6;
    public static final int RPAREN=7;
    public static final int TRUE=8;
    public static final int FALSE=9;
    public static final int QMARK=10;
    public static final int COLON=11;
    public static final int SEMICOLON=12;
    public static final int PERIOD=13;
    public static final int UNDERSCORE=14;
    public static final int COMMA=15;
    public static final int QUOTE=16;
    public static final int MODULE=17;
    public static final int NAME=18;
    public static final int INPUT=19;
    public static final int OUTPUT=20;
    public static final int INTERNAL=21;
    public static final int MARKING=22;
    public static final int STATE_VECTOR=23;
    public static final int TRANSITION=24;
    public static final int LABEL=25;
    public static final int PRESET=26;
    public static final int POSTSET=27;
    public static final int PLUS=28;
    public static final int MINUS=29;
    public static final int TIMES=30;
    public static final int DIV=31;
    public static final int MOD=32;
    public static final int EQUALS=33;
    public static final int GREATER=34;
    public static final int LESS=35;
    public static final int GREATER_EQUAL=36;
    public static final int LESS_EQUAL=37;
    public static final int EQUIV=38;
    public static final int NOT_EQUIV=39;
    public static final int NEGATION=40;
    public static final int AND=41;
    public static final int OR=42;
    public static final int IMPLICATION=43;
    public static final int BITWISE_NEGATION=44;
    public static final int BITWISE_AND=45;
    public static final int BITWISE_OR=46;
    public static final int BITWISE_XOR=47;
    public static final int BITWISE_LSHIFT=48;
    public static final int BITWISE_RSHIFT=49;
    public static final int LETTER=50;
    public static final int DIGIT=51;
    public static final int WS=52;
    public static final int COMMENT=53;
    public static final int MULTILINECOMMENT=54;
    public static final int XMLCOMMENT=55;
    public static final int IGNORE=56;

    // delegates
    // delegators


        public PlatuGrammarParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public PlatuGrammarParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return PlatuGrammarParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g"; }


    	// static variables
        static private int INFINITY = Integer.MAX_VALUE;
        static private boolean main = false;
        static private ExpressionNode ZERO = new ConstNode("FALSE", 0);  // constant false node
        static private ExpressionNode ONE = new ConstNode("TRUE", 1);  // constant true node
        static private Expression TrueExpr = new Expression(ONE); // constant true expression
        static private HashMap<String, LPN> LpnMap = new HashMap<String, LPN>();  // all modules parsed, keyed by module name
        static private HashMap<String, Integer> GlobalVarHashMap = new HashMap<String, Integer>();  // global variables and associated values
        static private HashMap<String, LPN> GlobalOutputMap = new HashMap<String, LPN>();  // maps potential output variables to associated lpn
        static private HashMap<String, Integer> GlobalInterfaceMap = new HashMap<String, Integer>();  // maps variables to initial values, input have null value until associated output is found
        static private HashMap<String, List<LPN>> GlobalInputMap = new HashMap<String, List<LPN>>(); // maps input variables to associated lpn
        static private HashMap<String, List<LPNTran>> GlobalTranMap = new HashMap<String, List<LPNTran>>();  // maps potential output variables to lpn transitions which affect it
        static private HashMap<String, VarNode> GlobalVarNodeMap = new HashMap<String, VarNode>(); // maps global variable name to variable object
        
        // non-static variables
        private HashMap<String, VarNode> VarNodeMap = null; // maps variable name to variable object
        private HashMap<String, ArrayNode> ArrayNodeMap = null; // maps array variable name to variable object
    	private DualHashMap<String, Integer> VarIndexMap = null;  // maps variables to an array index
        private HashMap<String, Integer> GlobalConstHashMap = new HashMap<String, Integer>();  // global constants within a single lpn file
        private HashMap<String, Integer> ConstHashMap = null;  // constants within a single module
        private HashMap<String, Integer> StatevectorMap = null;  // module variables mapped to initial values
        private HashMap<String, Integer> VarCountMap = null; // count of the references to each module variable
        private List<LPNTran> inputTranList = null;  // list of lpn transitions which affect a modules input
        private List<LPNTran> outputTranList = null; // list of lpn transitions which affect a modules output
        private VarSet Inputs = null;  // module inputs
        private VarSet Internals = null; // module internal variables
        private VarSet Outputs = null;  // module outputs
        private int VariableIndex = 0;  // count of index assigned to module variables
        private int TransitionIndex = 0;
        private int GlobalCount = 0;  // number of global variables defined in this lpn file
        private int GlobalSize = 0;  // number of global variables defined



    // $ANTLR start "parseLpnFile"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:75:1: parseLpnFile[Project prj, boolean instance, HashMap<String, String> portMap] returns [Set<LPN> lpnSet] : ;
    public final Set<LPN> parseLpnFile(Project prj, boolean instance, HashMap<String, String> portMap) throws RecognitionException {
        Set<LPN> lpnSet = null;

        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:76:2: ()
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:77:2: 
            {
            }

        }
        finally {
        }
        return lpnSet;
    }
    // $ANTLR end "parseLpnFile"


    // $ANTLR start "lpn"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:79:1: lpn[Project prj] returns [Set<LPN> lpnSet] : ( globalConstants )? ( globalVariables )? ( module[prj] )+ EOF ;
    public final Set<LPN> lpn(Project prj) throws RecognitionException {
        Set<LPN> lpnSet = null;

        LPN module1 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:80:5: ( ( globalConstants )? ( globalVariables )? ( module[prj] )+ EOF )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:80:10: ( globalConstants )? ( globalVariables )? ( module[prj] )+ EOF
            {
            lpnSet = new HashSet<LPN>();
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:81:9: ( globalConstants )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==LESS) ) {
                int LA1_1 = input.LA(2);

                if ( (LA1_1==58) ) {
                    alt1=1;
                }
            }
            switch (alt1) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:81:9: globalConstants
                    {
                    pushFollow(FOLLOW_globalConstants_in_lpn85);
                    globalConstants();

                    state._fsp--;


                    }
                    break;

            }

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:81:26: ( globalVariables )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==LESS) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==INTERNAL) ) {
                    alt2=1;
                }
            }
            switch (alt2) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:81:26: globalVariables
                    {
                    pushFollow(FOLLOW_globalVariables_in_lpn88);
                    globalVariables();

                    state._fsp--;


                    }
                    break;

            }


                    		// check that global constants are consistently defined in each lpn file
                    		if(GlobalSize > 0 && GlobalCount != GlobalSize){
                    			System.err.println("error: global variable definitions are inconsistent");
                    			System.exit(1);
                    		}
                    	
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:89:8: ( module[prj] )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==LESS) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:89:9: module[prj]
            	    {
            	    pushFollow(FOLLOW_module_in_lpn111);
            	    module1=module(prj);

            	    state._fsp--;


            	                	lpnSet.add(module1);
            	                

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

            match(input,EOF,FOLLOW_EOF_in_lpn139); 

            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return lpnSet;
    }
    // $ANTLR end "lpn"


    // $ANTLR start "main"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:96:1: main[Project prj] : '<' 'mod' 'name' '=' '\"' 'main' '\"' '>' ( instantiation )+ '<' '/' 'mod' '>' ;
    public final void main(Project prj) throws RecognitionException {
        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:97:2: ( '<' 'mod' 'name' '=' '\"' 'main' '\"' '>' ( instantiation )+ '<' '/' 'mod' '>' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:97:4: '<' 'mod' 'name' '=' '\"' 'main' '\"' '>' ( instantiation )+ '<' '/' 'mod' '>'
            {
            match(input,LESS,FOLLOW_LESS_in_main158); 
            match(input,MODULE,FOLLOW_MODULE_in_main160); 
            match(input,NAME,FOLLOW_NAME_in_main162); 
            match(input,EQUALS,FOLLOW_EQUALS_in_main164); 
            match(input,QUOTE,FOLLOW_QUOTE_in_main166); 
            match(input,57,FOLLOW_57_in_main168); 
            match(input,QUOTE,FOLLOW_QUOTE_in_main170); 
            match(input,GREATER,FOLLOW_GREATER_in_main172); 

            				if(main == true){
            					System.err.println("error");
            					System.exit(1);
            				}
            				
            				main = true;
            			
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:106:3: ( instantiation )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==LESS) ) {
                    int LA4_1 = input.LA(2);

                    if ( (LA4_1==61) ) {
                        alt4=1;
                    }


                }


                switch (alt4) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:106:3: instantiation
            	    {
            	    pushFollow(FOLLOW_instantiation_in_main181);
            	    instantiation();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

            match(input,LESS,FOLLOW_LESS_in_main184); 
            match(input,DIV,FOLLOW_DIV_in_main186); 
            match(input,MODULE,FOLLOW_MODULE_in_main188); 
            match(input,GREATER,FOLLOW_GREATER_in_main190); 

            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return ;
    }
    // $ANTLR end "main"


    // $ANTLR start "module"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:110:1: module[Project prj] returns [LPN lpn] : ( '<' 'mod' 'name' '=' '\"' ID '\"' '>' ( constants )? variables ( instantiation )? ( logic )? '<' '/' 'mod' '>' ) ;
    public final LPN module(Project prj) throws RecognitionException {
        LPN lpn = null;

        Token ID2=null;
        PlatuGrammarParser.logic_return logic3 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:111:5: ( ( '<' 'mod' 'name' '=' '\"' ID '\"' '>' ( constants )? variables ( instantiation )? ( logic )? '<' '/' 'mod' '>' ) )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:111:7: ( '<' 'mod' 'name' '=' '\"' ID '\"' '>' ( constants )? variables ( instantiation )? ( logic )? '<' '/' 'mod' '>' )
            {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:111:7: ( '<' 'mod' 'name' '=' '\"' ID '\"' '>' ( constants )? variables ( instantiation )? ( logic )? '<' '/' 'mod' '>' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:111:9: '<' 'mod' 'name' '=' '\"' ID '\"' '>' ( constants )? variables ( instantiation )? ( logic )? '<' '/' 'mod' '>'
            {
            match(input,LESS,FOLLOW_LESS_in_module216); 
            match(input,MODULE,FOLLOW_MODULE_in_module218); 
            match(input,NAME,FOLLOW_NAME_in_module220); 
            match(input,EQUALS,FOLLOW_EQUALS_in_module222); 
            match(input,QUOTE,FOLLOW_QUOTE_in_module224); 
            ID2=(Token)match(input,ID,FOLLOW_ID_in_module226); 

                			// module names must be unique
                			if(LpnMap.containsKey((ID2!=null?ID2.getText():null))){
                				System.err.println("error on line " + ID2.getLine() + ": module " + (ID2!=null?ID2.getText():null) + " already exists");
                				System.exit(1);
                			}
                			
                			// initialize non static variables for new module
                    	    VarIndexMap = new DualHashMap<String, Integer>();
            			    ConstHashMap = new HashMap<String, Integer>();
            			    VarNodeMap = new HashMap<String, VarNode>();
            			    ArrayNodeMap = new HashMap<String, ArrayNode>();
            			    VarCountMap = new HashMap<String, Integer>();
            			    Inputs = new VarSet();
            			    Internals = new VarSet();
            			    Outputs = new VarSet();
            			    inputTranList = new ArrayList<LPNTran>();
            			    outputTranList = new ArrayList<LPNTran>();
            			    StatevectorMap = new HashMap<String, Integer>();
            			    VariableIndex = 0;
                		
            match(input,QUOTE,FOLLOW_QUOTE_in_module242); 
            match(input,GREATER,FOLLOW_GREATER_in_module244); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:133:14: ( constants )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==LESS) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==58) ) {
                    alt5=1;
                }
            }
            switch (alt5) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:133:14: constants
                    {
                    pushFollow(FOLLOW_constants_in_module246);
                    constants();

                    state._fsp--;


                    }
                    break;

            }

            pushFollow(FOLLOW_variables_in_module249);
            variables();

            state._fsp--;

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:133:35: ( instantiation )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==LESS) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==61) ) {
                    alt6=1;
                }
            }
            switch (alt6) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:133:35: instantiation
                    {
                    pushFollow(FOLLOW_instantiation_in_module251);
                    instantiation();

                    state._fsp--;


                    }
                    break;

            }

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:133:50: ( logic )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==LESS) ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==MARKING||LA7_1==TRANSITION) ) {
                    alt7=1;
                }
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:133:50: logic
                    {
                    pushFollow(FOLLOW_logic_in_module254);
                    logic3=logic();

                    state._fsp--;


                    }
                    break;

            }

            match(input,LESS,FOLLOW_LESS_in_module257); 
            match(input,DIV,FOLLOW_DIV_in_module259); 
            match(input,MODULE,FOLLOW_MODULE_in_module261); 
            match(input,GREATER,FOLLOW_GREATER_in_module263); 

            				for(Entry<String, Integer> e : VarCountMap.entrySet()){
            					if(e.getValue() == 0){
            						System.out.println("Warning: variable '" + e.getKey() + "' is never assigned");
            					}
            				}
            				
            				// create new lpn	            
            	            int i = 0;
            	            int[] initialMarking = new int[(logic3!=null?logic3.initMarking:null).size()];
            	            for(Integer mark : (logic3!=null?logic3.initMarking:null)){
            	            	initialMarking[i++] = mark;
            	            }

            				VarSet globals = new VarSet();
            				for(String input : Inputs){
            					globals.add(input);
            				}
            				
            				for(String output : Outputs){
            					if(!globals.contains(output)){
            						globals.add(output);
            					}
            				}
            				
            				lpn = new LPN(prj, (ID2!=null?ID2.getText():null), globals, Internals, VarNodeMap, (logic3!=null?logic3.lpnTranSet:null), 
            	         			StatevectorMap, initialMarking);
            				
            				for(LPNTran tran : inputTranList){
            					tran.addDstLpn(lpn);
            				}
            				
            				lpn.addAllInputTrans(inputTranList);
            				lpn.addAllOutputTrans(outputTranList);
            	            lpn.setVarIndexMap(VarIndexMap);         
            	            (logic3!=null?logic3.lpnTranSet:null).setLPN(lpn);     
            	            prj.getDesignUnitSet().add(lpn.getStateGraph());
            	            
            	            LpnMap.put(lpn.getLabel(), lpn);
            	            
            	            // map outputs to lpn object
            	            for(String output : Outputs){
            	            	GlobalOutputMap.put(output, lpn);
            	            }
            	            
            	            // map potential output to lpn object
            	            for(String internal : Internals){
            	            	GlobalOutputMap.put(internal, lpn);
            	            }
            	            
            	            // map input variable to lpn objectsetArgumentList
            	            for(String input : Inputs){
            	            	if(GlobalInputMap.containsKey(input)){
            	    				GlobalInputMap.get(input).add(lpn);
            	    			}
            	    			else{
            	    				List<LPN> lpnList = new ArrayList<LPN>();
            	    				lpnList.add(lpn);
            	    				GlobalInputMap.put(input, lpnList);
            	    			}
            	            }
            			

            }


            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return lpn;
    }
    // $ANTLR end "module"


    // $ANTLR start "constants"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:199:1: constants : '<' 'const' '>' (const1= ID '=' val1= INT ';' )* '<' '/' 'const' '>' ;
    public final void constants() throws RecognitionException {
        Token const1=null;
        Token val1=null;

        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:200:2: ( '<' 'const' '>' (const1= ID '=' val1= INT ';' )* '<' '/' 'const' '>' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:200:4: '<' 'const' '>' (const1= ID '=' val1= INT ';' )* '<' '/' 'const' '>'
            {
            match(input,LESS,FOLLOW_LESS_in_constants290); 
            match(input,58,FOLLOW_58_in_constants292); 
            match(input,GREATER,FOLLOW_GREATER_in_constants294); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:200:20: (const1= ID '=' val1= INT ';' )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==ID) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:200:21: const1= ID '=' val1= INT ';'
            	    {
            	    const1=(Token)match(input,ID,FOLLOW_ID_in_constants299); 
            	    match(input,EQUALS,FOLLOW_EQUALS_in_constants301); 
            	    val1=(Token)match(input,INT,FOLLOW_INT_in_constants305); 

            	    				// make sure constant is not defined as something else
            	    				if(StatevectorMap.containsKey((const1!=null?const1.getText():null))){
            	    					System.err.println("error on line " + const1.getLine() + ": " + (const1!=null?const1.getText():null) + " already exists as a variable"); 
            	    					System.exit(1);
            	    				}
            	    				else if(GlobalConstHashMap.containsKey((const1!=null?const1.getText():null))){
            	    				    System.err.println("error on line " + const1.getLine() + ": " + (const1!=null?const1.getText():null) + " already exists as a global constant");
            	    				    System.exit(1);
            	    				}
            	    				else if(GlobalVarHashMap.containsKey((const1!=null?const1.getText():null))){
            	                		System.err.println("error on line " + const1.getLine() + ": " + (const1!=null?const1.getText():null) + " is already defined as a global variable");
            	                		System.exit(1);
            	                	}
            	    				
            	    				// put will override previous value
            	    				Integer result = ConstHashMap.put((const1!=null?const1.getText():null), Integer.parseInt((val1!=null?val1.getText():null)));
            	    				if(result != null){
            	    					System.err.println("Warning on line " + const1.getLine() + ": " + (const1!=null?const1.getText():null) + " will be overwritten");
            	    				}
            	    			
            	    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_constants316); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            match(input,LESS,FOLLOW_LESS_in_constants320); 
            match(input,DIV,FOLLOW_DIV_in_constants322); 
            match(input,58,FOLLOW_58_in_constants324); 
            match(input,GREATER,FOLLOW_GREATER_in_constants326); 

            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return ;
    }
    // $ANTLR end "constants"


    // $ANTLR start "globalConstants"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:225:1: globalConstants : '<' 'const' '>' (const1= ID '=' val1= INT ';' )* '<' '/' 'const' '>' ;
    public final void globalConstants() throws RecognitionException {
        Token const1=null;
        Token val1=null;

        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:226:5: ( '<' 'const' '>' (const1= ID '=' val1= INT ';' )* '<' '/' 'const' '>' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:226:9: '<' 'const' '>' (const1= ID '=' val1= INT ';' )* '<' '/' 'const' '>'
            {
            match(input,LESS,FOLLOW_LESS_in_globalConstants343); 
            match(input,58,FOLLOW_58_in_globalConstants345); 
            match(input,GREATER,FOLLOW_GREATER_in_globalConstants347); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:226:25: (const1= ID '=' val1= INT ';' )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==ID) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:226:26: const1= ID '=' val1= INT ';'
            	    {
            	    const1=(Token)match(input,ID,FOLLOW_ID_in_globalConstants352); 
            	    match(input,EQUALS,FOLLOW_EQUALS_in_globalConstants354); 
            	    val1=(Token)match(input,INT,FOLLOW_INT_in_globalConstants358); 

            	                	// make sure constant has not been defined already
            	                	if(GlobalVarHashMap.containsKey((const1!=null?const1.getText():null))){
            	                		System.err.println("error on line " + const1.getLine() + ": " + (const1!=null?const1.getText():null) + " is already defined as a global variable");
            	                		System.exit(1);
            	                	}
            	                	
            	                	// put will override previous value
            	                    Integer result = GlobalConstHashMap.put((const1!=null?const1.getText():null), Integer.parseInt((val1!=null?val1.getText():null)));
            	                    if(result != null){
            	                        System.err.println("Warning on line " + const1.getLine() + ": " + (const1!=null?const1.getText():null) + " will be overwritten");
            	                    }
            	                
            	    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_globalConstants384); 

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            match(input,LESS,FOLLOW_LESS_in_globalConstants388); 
            match(input,DIV,FOLLOW_DIV_in_globalConstants390); 
            match(input,58,FOLLOW_58_in_globalConstants392); 
            match(input,GREATER,FOLLOW_GREATER_in_globalConstants394); 

            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return ;
    }
    // $ANTLR end "globalConstants"


    // $ANTLR start "globalVariables"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:244:1: globalVariables : '<' 'var' '>' (var= ID '=' (val= INT | var2= ID ) ';' )* '<' '/' 'var' '>' ;
    public final void globalVariables() throws RecognitionException {
        Token var=null;
        Token val=null;
        Token var2=null;

        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:245:2: ( '<' 'var' '>' (var= ID '=' (val= INT | var2= ID ) ';' )* '<' '/' 'var' '>' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:245:4: '<' 'var' '>' (var= ID '=' (val= INT | var2= ID ) ';' )* '<' '/' 'var' '>'
            {
            match(input,LESS,FOLLOW_LESS_in_globalVariables409); 
            match(input,INTERNAL,FOLLOW_INTERNAL_in_globalVariables411); 
            match(input,GREATER,FOLLOW_GREATER_in_globalVariables413); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:245:18: (var= ID '=' (val= INT | var2= ID ) ';' )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==ID) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:245:19: var= ID '=' (val= INT | var2= ID ) ';'
            	    {
            	    var=(Token)match(input,ID,FOLLOW_ID_in_globalVariables418); 

            	    				// make sure global variables are consistently defined in each lpn file
            	    				if(GlobalSize == 0){
            	    					if(GlobalConstHashMap.containsKey((var!=null?var.getText():null))){
            	    						System.err.println("error on line" + var.getLine() + ": " + (var!=null?var.getText():null) + "already exists as a constant"); 
            	    	                    System.exit(1);
            	    					}
            	    					else if(GlobalVarHashMap.containsKey((var!=null?var.getText():null))){
            	    						System.err.println("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " has already been defined");
            	    						System.exit(1);
            	    					}
            	    				}
            	    				else{
            	    					if(!GlobalVarHashMap.containsKey((var!=null?var.getText():null))){
            	    						System.err.println("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " is inconsistently defined");
            	    						System.exit(1);
            	    					}
            	    				}
            	    				
            	    				GlobalCount++;
            	    			
            	    match(input,EQUALS,FOLLOW_EQUALS_in_globalVariables428); 
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:267:7: (val= INT | var2= ID )
            	    int alt10=2;
            	    int LA10_0 = input.LA(1);

            	    if ( (LA10_0==INT) ) {
            	        alt10=1;
            	    }
            	    else if ( (LA10_0==ID) ) {
            	        alt10=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 10, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt10) {
            	        case 1 :
            	            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:267:8: val= INT
            	            {
            	            val=(Token)match(input,INT,FOLLOW_INT_in_globalVariables433); 

            	            				// make sure global variables are consistently initialized
            	            				int value = Integer.parseInt((val!=null?val.getText():null));
            	            				if(GlobalSize == 0){
            	            					GlobalVarHashMap.put((var!=null?var.getText():null), value);
            	            				}
            	            				else{
            	            					int globalVal = GlobalVarHashMap.get((var!=null?var.getText():null));
            	            					if(globalVal != value){
            	            						System.err.println("error on line " + val.getLine() + ": " + (var!=null?var.getText():null) + " is inconsistently assigned");
            	            						System.exit(1);
            	            					}
            	            				}
            	            			

            	            }
            	            break;
            	        case 2 :
            	            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:282:5: var2= ID
            	            {
            	            var2=(Token)match(input,ID,FOLLOW_ID_in_globalVariables447); 

            	            				// get value of variable
            	            				Integer value = null;
            	            				if(GlobalConstHashMap.containsKey((var2!=null?var2.getText():null))){
            	            					value = GlobalConstHashMap.get((var2!=null?var2.getText():null));
            	            				}
            	            				else if(GlobalVarHashMap.containsKey((var2!=null?var2.getText():null))){
            	            					System.err.println("error on line " + var2.getLine() + ": global variable " + (var2!=null?var2.getText():null) + " cannot be assigned to global variable " + (var!=null?var.getText():null)); 
            	                				System.exit(1);
            	            				}
            	            				else{
            	            					System.err.println("error on line " + var2.getLine() + ": " + (var2!=null?var2.getText():null) + " is not defined"); 
            	                				System.exit(1);
            	            				}
            	            				
            	            				// make sure global variable is consistently initialized
            	            				if(GlobalSize == 0){
            	            					GlobalVarHashMap.put((var!=null?var.getText():null), value);
            	            				}
            	            				else{
            	            					int globalVal = GlobalVarHashMap.get((var!=null?var.getText():null));
            	            					if(globalVal != value){
            	            						System.err.println("error on line " + val.getLine() + ": " + (var!=null?var.getText():null) + " is inconsistently assigned");
            	            						System.exit(1);
            	            					}
            	            				}
            	            				
            	            				
            	            			

            	            }
            	            break;

            	    }

            	    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_globalVariables457); 

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            match(input,LESS,FOLLOW_LESS_in_globalVariables461); 
            match(input,DIV,FOLLOW_DIV_in_globalVariables463); 
            match(input,INTERNAL,FOLLOW_INTERNAL_in_globalVariables465); 
            match(input,GREATER,FOLLOW_GREATER_in_globalVariables467); 

            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return ;
    }
    // $ANTLR end "globalVariables"


    // $ANTLR start "variables"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:316:1: variables : '<' 'var' '>' ( (var= ID '=' (val= INT | var2= ID ) ';' ) | (arrayName= ID ( '[' (dim= ID | val2= INT ) ']' )+ '=' ( '(' (val3= INT ',' )* val4= INT ')' )+ ';' ) )* '<' '/' 'var' '>' ;
    public final void variables() throws RecognitionException {
        Token var=null;
        Token val=null;
        Token var2=null;
        Token arrayName=null;
        Token dim=null;
        Token val2=null;
        Token val3=null;
        Token val4=null;

        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:317:2: ( '<' 'var' '>' ( (var= ID '=' (val= INT | var2= ID ) ';' ) | (arrayName= ID ( '[' (dim= ID | val2= INT ) ']' )+ '=' ( '(' (val3= INT ',' )* val4= INT ')' )+ ';' ) )* '<' '/' 'var' '>' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:317:4: '<' 'var' '>' ( (var= ID '=' (val= INT | var2= ID ) ';' ) | (arrayName= ID ( '[' (dim= ID | val2= INT ) ']' )+ '=' ( '(' (val3= INT ',' )* val4= INT ')' )+ ';' ) )* '<' '/' 'var' '>'
            {
            Integer value = null; Token varNode = null;
            match(input,LESS,FOLLOW_LESS_in_variables483); 
            match(input,INTERNAL,FOLLOW_INTERNAL_in_variables485); 
            match(input,GREATER,FOLLOW_GREATER_in_variables487); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:318:17: ( (var= ID '=' (val= INT | var2= ID ) ';' ) | (arrayName= ID ( '[' (dim= ID | val2= INT ) ']' )+ '=' ( '(' (val3= INT ',' )* val4= INT ')' )+ ';' ) )*
            loop17:
            do {
                int alt17=3;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==ID) ) {
                    int LA17_2 = input.LA(2);

                    if ( (LA17_2==EQUALS) ) {
                        alt17=1;
                    }
                    else if ( (LA17_2==59) ) {
                        alt17=2;
                    }


                }


                switch (alt17) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:318:18: (var= ID '=' (val= INT | var2= ID ) ';' )
            	    {
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:318:18: (var= ID '=' (val= INT | var2= ID ) ';' )
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:318:19: var= ID '=' (val= INT | var2= ID ) ';'
            	    {
            	    var=(Token)match(input,ID,FOLLOW_ID_in_variables493); 

            	    				// check variable is unique in scope
            	    				if(GlobalConstHashMap.containsKey((var!=null?var.getText():null))){
            	    					System.err.println("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " is a global constant"); 
            	        				System.exit(1);
            	    				}
            	    				else if(GlobalVarHashMap.containsKey((var!=null?var.getText():null))){
            	    					System.err.println("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " is a global variable"); 
            	        				System.exit(1);
            	    				}
            	    				else if(StatevectorMap.containsKey((var!=null?var.getText():null))){
            	    					System.err.println("Warning on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " will be overwritten");
            	    				}
            	    				
            	    				varNode = var;
            	    			
            	    match(input,EQUALS,FOLLOW_EQUALS_in_variables503); 
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:335:7: (val= INT | var2= ID )
            	    int alt12=2;
            	    int LA12_0 = input.LA(1);

            	    if ( (LA12_0==INT) ) {
            	        alt12=1;
            	    }
            	    else if ( (LA12_0==ID) ) {
            	        alt12=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 12, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt12) {
            	        case 1 :
            	            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:335:8: val= INT
            	            {
            	            val=(Token)match(input,INT,FOLLOW_INT_in_variables508); 

            	            				// get variable initial value
            	            				value = Integer.parseInt((val!=null?val.getText():null));
            	            			

            	            }
            	            break;
            	        case 2 :
            	            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:340:5: var2= ID
            	            {
            	            var2=(Token)match(input,ID,FOLLOW_ID_in_variables522); 

            	            				// get variable initial value
            	            				if(GlobalConstHashMap.containsKey((var2!=null?var2.getText():null))){
            	            					value = GlobalConstHashMap.get((var2!=null?var2.getText():null));
            	            				}
            	            				else if(GlobalVarHashMap.containsKey((var2!=null?var2.getText():null))){
            	            					value = GlobalVarHashMap.get((var2!=null?var2.getText():null));
            	            				}
            	            				else if(ConstHashMap.containsKey((var2!=null?var2.getText():null))){
            	            					value = ConstHashMap.get((var2!=null?var2.getText():null));
            	            				}
            	            				else if(StatevectorMap.containsKey((var2!=null?var2.getText():null))){ // Should var be allowed to assign a var?
            	            					value = StatevectorMap.get((var2!=null?var2.getText():null));
            	            				}
            	            				else{
            	            					System.err.println("error on line " + var2.getLine() + ": " + (var2!=null?var2.getText():null) + " is not defined"); 
            	                				System.exit(1);
            	            				}
            	            				
            	            				varNode = var2;
            	            			

            	            }
            	            break;

            	    }

            	    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_variables532); 

            	    }


            	    				// add variable and value to state vector
            	    				StatevectorMap.put(varNode.getText(), value);
            	    				
            	    				// generate variable index and create new var node  
            	    				int index = VariableIndex++;
            	       				VarIndexMap.insert(varNode.getText(), index);
            	       				VarNode variableNode = new VarNode(varNode.getText(), index);
            	       				VarNodeMap.put(varNode.getText(), variableNode);
            	        			
            	        			// if associated input variable has been defined label as output, else label as internal
            	    				if(!GlobalInterfaceMap.containsKey(varNode.getText())){
            	    					Internals.add(varNode.getText());
            	    				}
            	    				else{
            	    					if(GlobalInterfaceMap.get(varNode.getText()) != null){
            	    						System.err.println("error on line " + varNode.getLine() + ": variable '" + varNode.getText() + "' has already been declared in another module");
            	    						System.exit(1);
            	    					}
            	    					
            	    					variableNode.setType(platu.lpn.VarType.OUTPUT);
            	    					Outputs.add(varNode.getText());
            	    					
            	    					// initialize associated input variables with output value
            	    					List<LPN> lpnList = GlobalInputMap.get(varNode.getText());
            	    					if(lpnList != null){
            	    						for(LPN lpn : lpnList){
            	    							lpn.getInitVector().put(varNode.getText(), value);
            	    						}
            	    					}
            	    				}
            	    				
            	    				GlobalInterfaceMap.put(varNode.getText(), value);
            	    				VarCountMap.put(varNode.getText(), 0);
            	    			

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:398:5: (arrayName= ID ( '[' (dim= ID | val2= INT ) ']' )+ '=' ( '(' (val3= INT ',' )* val4= INT ')' )+ ';' )
            	    {
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:398:5: (arrayName= ID ( '[' (dim= ID | val2= INT ) ']' )+ '=' ( '(' (val3= INT ',' )* val4= INT ')' )+ ';' )
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:398:6: arrayName= ID ( '[' (dim= ID | val2= INT ) ']' )+ '=' ( '(' (val3= INT ',' )* val4= INT ')' )+ ';'
            	    {
            	    arrayName=(Token)match(input,ID,FOLLOW_ID_in_variables547); 

            	    				List<Integer> dimensionsList = new ArrayList<Integer>();
            	    			
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:402:3: ( '[' (dim= ID | val2= INT ) ']' )+
            	    int cnt14=0;
            	    loop14:
            	    do {
            	        int alt14=2;
            	        int LA14_0 = input.LA(1);

            	        if ( (LA14_0==59) ) {
            	            alt14=1;
            	        }


            	        switch (alt14) {
            	    	case 1 :
            	    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:402:4: '[' (dim= ID | val2= INT ) ']'
            	    	    {
            	    	    match(input,59,FOLLOW_59_in_variables557); 
            	    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:402:8: (dim= ID | val2= INT )
            	    	    int alt13=2;
            	    	    int LA13_0 = input.LA(1);

            	    	    if ( (LA13_0==ID) ) {
            	    	        alt13=1;
            	    	    }
            	    	    else if ( (LA13_0==INT) ) {
            	    	        alt13=2;
            	    	    }
            	    	    else {
            	    	        NoViableAltException nvae =
            	    	            new NoViableAltException("", 13, 0, input);

            	    	        throw nvae;
            	    	    }
            	    	    switch (alt13) {
            	    	        case 1 :
            	    	            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:402:9: dim= ID
            	    	            {
            	    	            dim=(Token)match(input,ID,FOLLOW_ID_in_variables562); 

            	    	            				// get variable value
            	    	            				if(GlobalConstHashMap.containsKey((dim!=null?dim.getText():null))){
            	    	            					value = GlobalConstHashMap.get((dim!=null?dim.getText():null));
            	    	            				}
            	    	            				else if(GlobalVarHashMap.containsKey((dim!=null?dim.getText():null))){
            	    	            					value = GlobalVarHashMap.get((dim!=null?dim.getText():null));
            	    	            				}
            	    	            				else if(ConstHashMap.containsKey((dim!=null?dim.getText():null))){
            	    	            					value = ConstHashMap.get((dim!=null?dim.getText():null));
            	    	            				}
            	    	            				else if(StatevectorMap.containsKey((dim!=null?dim.getText():null))){ // Should var be allowed to assign a var?
            	    	            					value = StatevectorMap.get((dim!=null?dim.getText():null));
            	    	            				}
            	    	            				else{
            	    	            					System.err.println("error on line " + dim.getLine() + ": " + (dim!=null?dim.getText():null) + " is not defined"); 
            	    	                				System.exit(1);
            	    	            				}
            	    	            				
            	    	            				dimensionsList.add(value);
            	    	            			

            	    	            }
            	    	            break;
            	    	        case 2 :
            	    	            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:424:5: val2= INT
            	    	            {
            	    	            val2=(Token)match(input,INT,FOLLOW_INT_in_variables576); 

            	    	            				dimensionsList.add(Integer.parseInt((val2!=null?val2.getText():null)));
            	    	            			

            	    	            }
            	    	            break;

            	    	    }

            	    	    match(input,60,FOLLOW_60_in_variables587); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt14 >= 1 ) break loop14;
            	                EarlyExitException eee =
            	                    new EarlyExitException(14, input);
            	                throw eee;
            	        }
            	        cnt14++;
            	    } while (true);

            	    match(input,EQUALS,FOLLOW_EQUALS_in_variables592); 

            	    				List<Integer> valueList = new ArrayList<Integer>();
            	    			
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:432:3: ( '(' (val3= INT ',' )* val4= INT ')' )+
            	    int cnt16=0;
            	    loop16:
            	    do {
            	        int alt16=2;
            	        int LA16_0 = input.LA(1);

            	        if ( (LA16_0==LPAREN) ) {
            	            alt16=1;
            	        }


            	        switch (alt16) {
            	    	case 1 :
            	    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:432:4: '(' (val3= INT ',' )* val4= INT ')'
            	    	    {
            	    	    match(input,LPAREN,FOLLOW_LPAREN_in_variables603); 
            	    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:432:8: (val3= INT ',' )*
            	    	    loop15:
            	    	    do {
            	    	        int alt15=2;
            	    	        int LA15_0 = input.LA(1);

            	    	        if ( (LA15_0==INT) ) {
            	    	            int LA15_1 = input.LA(2);

            	    	            if ( (LA15_1==COMMA) ) {
            	    	                alt15=1;
            	    	            }


            	    	        }


            	    	        switch (alt15) {
            	    	    	case 1 :
            	    	    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:432:9: val3= INT ','
            	    	    	    {
            	    	    	    val3=(Token)match(input,INT,FOLLOW_INT_in_variables608); 
            	    	    	    match(input,COMMA,FOLLOW_COMMA_in_variables610); 

            	    	    	    				valueList.add(Integer.parseInt((val3!=null?val3.getText():null)));
            	    	    	    			

            	    	    	    }
            	    	    	    break;

            	    	    	default :
            	    	    	    break loop15;
            	    	        }
            	    	    } while (true);

            	    	    val4=(Token)match(input,INT,FOLLOW_INT_in_variables624); 

            	    	    				valueList.add(Integer.parseInt((val4!=null?val4.getText():null)));
            	    	    			
            	    	    match(input,RPAREN,FOLLOW_RPAREN_in_variables634); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt16 >= 1 ) break loop16;
            	                EarlyExitException eee =
            	                    new EarlyExitException(16, input);
            	                throw eee;
            	        }
            	        cnt16++;
            	    } while (true);

            	    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_variables641); 

            	    				if(valueList.size() != dimensionsList.get(0)){
            	    					System.err.println("error: incompatible number of elements in " + (arrayName!=null?arrayName.getText():null));
            	    					System.exit(1);
            	    				}
            	    				
            	    				if(dimensionsList.size() == 1){
            	    					int varCount = 0;
            	    					int arraySize = dimensionsList.get(0);
            	    					List<Object> array = new ArrayList<Object>(arraySize);
            	    					for(int i = 0; i < arraySize; i++){
            	    						String name = (arrayName!=null?arrayName.getText():null) + varCount++;
            	    						int index = VariableIndex++;
            	    						VarNode v = new VarNode(name, index);
            	    						array.add(v);
            	    						
            	    						// add variable and value to state vector
            	    						StatevectorMap.put(name, 0);
            	    						
            	    						// generate variable index and create new var node  
            	    		   				VarIndexMap.insert(name, index);
            	    					}
            	    					
            	    //					ArrayNodeMap.put((arrayName!=null?arrayName.getText():null), new ArrayNode((arrayName!=null?arrayName.getText():null), array, 1));
            	    					VarCountMap.put((arrayName!=null?arrayName.getText():null), 0);
            	    				}
            	    				else{
            	    				}
            	    			

            	    }


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            match(input,LESS,FOLLOW_LESS_in_variables655); 
            match(input,DIV,FOLLOW_DIV_in_variables657); 
            match(input,INTERNAL,FOLLOW_INTERNAL_in_variables659); 
            match(input,GREATER,FOLLOW_GREATER_in_variables661); 

            				// add global variables to initial state vector and label as an input & output
            				for(Entry<String, Integer> e : GlobalVarHashMap.entrySet()){
            					String globalVar = e.getKey();
            					StatevectorMap.put(globalVar, e.getValue());
            					
            					Integer index =  VariableIndex++;
            	    			VarIndexMap.insert(globalVar, index);
            	    			VarNode globalVarNode = new VarNode(globalVar, index);
            	    			globalVarNode.setType(platu.lpn.VarType.GLOBAL);
            	    			VarNodeMap.put(globalVar, globalVarNode);
            	    			
            	    			Inputs.add(globalVar);
            	    			Outputs.add(globalVar);
            	    		}
            			

            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return ;
    }
    // $ANTLR end "variables"


    // $ANTLR start "instantiation"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:490:1: instantiation : '<' 'inst' '>' modName= ID instName= ID '(' (mod= ID '.' var= ID )+ ')' '<' '/' 'inst' '>' ;
    public final void instantiation() throws RecognitionException {
        Token modName=null;
        Token instName=null;
        Token mod=null;
        Token var=null;

        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:491:5: ( '<' 'inst' '>' modName= ID instName= ID '(' (mod= ID '.' var= ID )+ ')' '<' '/' 'inst' '>' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:491:9: '<' 'inst' '>' modName= ID instName= ID '(' (mod= ID '.' var= ID )+ ')' '<' '/' 'inst' '>'
            {
            HashMap<String, String> portMap = new HashMap<String, String>();
            match(input,LESS,FOLLOW_LESS_in_instantiation690); 
            match(input,61,FOLLOW_61_in_instantiation692); 
            match(input,GREATER,FOLLOW_GREATER_in_instantiation694); 
            modName=(Token)match(input,ID,FOLLOW_ID_in_instantiation703); 
            instName=(Token)match(input,ID,FOLLOW_ID_in_instantiation707); 
            match(input,LPAREN,FOLLOW_LPAREN_in_instantiation709); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:493:32: (mod= ID '.' var= ID )+
            int cnt18=0;
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==ID) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:493:33: mod= ID '.' var= ID
            	    {
            	    mod=(Token)match(input,ID,FOLLOW_ID_in_instantiation713); 
            	    match(input,PERIOD,FOLLOW_PERIOD_in_instantiation715); 
            	    var=(Token)match(input,ID,FOLLOW_ID_in_instantiation719); 

            	    }
            	    break;

            	default :
            	    if ( cnt18 >= 1 ) break loop18;
                        EarlyExitException eee =
                            new EarlyExitException(18, input);
                        throw eee;
                }
                cnt18++;
            } while (true);

            match(input,RPAREN,FOLLOW_RPAREN_in_instantiation723); 
            match(input,LESS,FOLLOW_LESS_in_instantiation730); 
            match(input,DIV,FOLLOW_DIV_in_instantiation732); 
            match(input,61,FOLLOW_61_in_instantiation734); 
            match(input,GREATER,FOLLOW_GREATER_in_instantiation736); 

            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return ;
    }
    // $ANTLR end "instantiation"

    public static class logic_return extends ParserRuleReturnScope {
        public List<Integer> initMarking;
        public LpnTranList lpnTranSet;
    };

    // $ANTLR start "logic"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:497:1: logic returns [List<Integer> initMarking, LpnTranList lpnTranSet] : marking ( transition )+ ;
    public final PlatuGrammarParser.logic_return logic() throws RecognitionException {
        PlatuGrammarParser.logic_return retval = new PlatuGrammarParser.logic_return();
        retval.start = input.LT(1);

        LPNTran transition4 = null;

        List marking5 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:498:5: ( marking ( transition )+ )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:498:9: marking ( transition )+
            {
            retval.lpnTranSet = new LpnTranList();
            pushFollow(FOLLOW_marking_in_logic766);
            marking5=marking();

            state._fsp--;

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:499:14: ( transition )+
            int cnt19=0;
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==LESS) ) {
                    int LA19_1 = input.LA(2);

                    if ( (LA19_1==TRANSITION) ) {
                        alt19=1;
                    }


                }


                switch (alt19) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:499:15: transition
            	    {
            	    pushFollow(FOLLOW_transition_in_logic769);
            	    transition4=transition();

            	    state._fsp--;

            	    retval.lpnTranSet.add(transition4);

            	    }
            	    break;

            	default :
            	    if ( cnt19 >= 1 ) break loop19;
                        EarlyExitException eee =
                            new EarlyExitException(19, input);
                        throw eee;
                }
                cnt19++;
            } while (true);


                        retval.initMarking = marking5;
                    

            }

            retval.stop = input.LT(-1);

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "logic"


    // $ANTLR start "marking"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:505:1: marking returns [List mark] : ( '<' 'marking' '>' ( (m1= INT | c1= ID ) ( ',' (m2= INT | c2= ID ) )* )? '<' '/' 'marking' '>' )? ;
    public final List marking() throws RecognitionException {
        List mark = null;

        Token m1=null;
        Token c1=null;
        Token m2=null;
        Token c2=null;

        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:506:5: ( ( '<' 'marking' '>' ( (m1= INT | c1= ID ) ( ',' (m2= INT | c2= ID ) )* )? '<' '/' 'marking' '>' )? )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:506:9: ( '<' 'marking' '>' ( (m1= INT | c1= ID ) ( ',' (m2= INT | c2= ID ) )* )? '<' '/' 'marking' '>' )?
            {
            mark = new LinkedList<Integer>(); Integer result;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:507:9: ( '<' 'marking' '>' ( (m1= INT | c1= ID ) ( ',' (m2= INT | c2= ID ) )* )? '<' '/' 'marking' '>' )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==LESS) ) {
                int LA24_1 = input.LA(2);

                if ( (LA24_1==MARKING) ) {
                    alt24=1;
                }
            }
            switch (alt24) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:507:10: '<' 'marking' '>' ( (m1= INT | c1= ID ) ( ',' (m2= INT | c2= ID ) )* )? '<' '/' 'marking' '>'
                    {
                    match(input,LESS,FOLLOW_LESS_in_marking822); 
                    match(input,MARKING,FOLLOW_MARKING_in_marking824); 
                    match(input,GREATER,FOLLOW_GREATER_in_marking826); 
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:507:28: ( (m1= INT | c1= ID ) ( ',' (m2= INT | c2= ID ) )* )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( ((LA23_0>=ID && LA23_0<=INT)) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:507:29: (m1= INT | c1= ID ) ( ',' (m2= INT | c2= ID ) )*
                            {
                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:507:29: (m1= INT | c1= ID )
                            int alt20=2;
                            int LA20_0 = input.LA(1);

                            if ( (LA20_0==INT) ) {
                                alt20=1;
                            }
                            else if ( (LA20_0==ID) ) {
                                alt20=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 20, 0, input);

                                throw nvae;
                            }
                            switch (alt20) {
                                case 1 :
                                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:507:30: m1= INT
                                    {
                                    m1=(Token)match(input,INT,FOLLOW_INT_in_marking832); 

                                            		mark.add(Integer.parseInt((m1!=null?m1.getText():null)));
                                            	

                                    }
                                    break;
                                case 2 :
                                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:511:11: c1= ID
                                    {
                                    c1=(Token)match(input,ID,FOLLOW_ID_in_marking858); 

                                            		result = ConstHashMap.get((c1!=null?c1.getText():null));
                                            		if(result == null){
                                            			System.err.println("error on line " + c1.getLine() + ": " + (c1!=null?c1.getText():null) + " is not a valid constant");
                                            			System.exit(1);
                                            		}
                                            		
                                            		mark.add(result);
                                            	

                                    }
                                    break;

                            }

                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:521:11: ( ',' (m2= INT | c2= ID ) )*
                            loop22:
                            do {
                                int alt22=2;
                                int LA22_0 = input.LA(1);

                                if ( (LA22_0==COMMA) ) {
                                    alt22=1;
                                }


                                switch (alt22) {
                            	case 1 :
                            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:521:12: ',' (m2= INT | c2= ID )
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_marking882); 
                            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:521:16: (m2= INT | c2= ID )
                            	    int alt21=2;
                            	    int LA21_0 = input.LA(1);

                            	    if ( (LA21_0==INT) ) {
                            	        alt21=1;
                            	    }
                            	    else if ( (LA21_0==ID) ) {
                            	        alt21=2;
                            	    }
                            	    else {
                            	        NoViableAltException nvae =
                            	            new NoViableAltException("", 21, 0, input);

                            	        throw nvae;
                            	    }
                            	    switch (alt21) {
                            	        case 1 :
                            	            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:521:17: m2= INT
                            	            {
                            	            m2=(Token)match(input,INT,FOLLOW_INT_in_marking887); 

                            	                    		mark.add(Integer.parseInt((m2!=null?m2.getText():null)));
                            	                    	

                            	            }
                            	            break;
                            	        case 2 :
                            	            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:525:11: c2= ID
                            	            {
                            	            c2=(Token)match(input,ID,FOLLOW_ID_in_marking913); 

                            	                    		result = ConstHashMap.get((c2!=null?c2.getText():null));
                            	                    		if(result == null){
                            	                    			System.err.println("error on line " + c2.getLine() + ": " + (c2!=null?c2.getText():null) + " is not a valid constant");
                            	                    			System.exit(1);
                            	                    		}
                            	                    		
                            	                    		mark.add(result);
                            	                    	

                            	            }
                            	            break;

                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop22;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,LESS,FOLLOW_LESS_in_marking940); 
                    match(input,DIV,FOLLOW_DIV_in_marking942); 
                    match(input,MARKING,FOLLOW_MARKING_in_marking944); 
                    match(input,GREATER,FOLLOW_GREATER_in_marking946); 

                    }
                    break;

            }


            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return mark;
    }
    // $ANTLR end "marking"


    // $ANTLR start "transition"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:538:1: transition returns [LPNTran lpnTran] : '<' 'transition' 'label' '=' '\"' lbl= ( ID | INT ) '\"' 'preset' '=' ( '\"' '\"' | ( '\"' (pre= INT | pre1= ID ) ( ',' pre2= INT | ',' pre3= ID )* '\"' ) ) 'postset' '=' ( '\"' '\"' | ( '\"' (post= INT | post1= ID ) ( ( ',' post2= INT ) | ( ',' post3= ID ) )* '\"' ) ) '>' ( guard )? ( delay )? ( ( assertion ) | ( assignment ) )* '<' '/' 'transition' '>' ;
    public final LPNTran transition() throws RecognitionException {
        LPNTran lpnTran = null;

        Token lbl=null;
        Token pre=null;
        Token pre1=null;
        Token pre2=null;
        Token pre3=null;
        Token post=null;
        Token post1=null;
        Token post2=null;
        Token post3=null;
        Expression guard6 = null;

        PlatuGrammarParser.delay_return delay7 = null;

        Expression assertion8 = null;

        VarExpr assignment9 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:539:5: ( '<' 'transition' 'label' '=' '\"' lbl= ( ID | INT ) '\"' 'preset' '=' ( '\"' '\"' | ( '\"' (pre= INT | pre1= ID ) ( ',' pre2= INT | ',' pre3= ID )* '\"' ) ) 'postset' '=' ( '\"' '\"' | ( '\"' (post= INT | post1= ID ) ( ( ',' post2= INT ) | ( ',' post3= ID ) )* '\"' ) ) '>' ( guard )? ( delay )? ( ( assertion ) | ( assignment ) )* '<' '/' 'transition' '>' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:539:10: '<' 'transition' 'label' '=' '\"' lbl= ( ID | INT ) '\"' 'preset' '=' ( '\"' '\"' | ( '\"' (pre= INT | pre1= ID ) ( ',' pre2= INT | ',' pre3= ID )* '\"' ) ) 'postset' '=' ( '\"' '\"' | ( '\"' (post= INT | post1= ID ) ( ( ',' post2= INT ) | ( ',' post3= ID ) )* '\"' ) ) '>' ( guard )? ( delay )? ( ( assertion ) | ( assignment ) )* '<' '/' 'transition' '>'
            {

            	    		Integer result = null;
            	    		ArrayList presetList = new ArrayList();  
            	    		ArrayList postsetList = new ArrayList(); 
            	    		VarExprList assignmentList = new VarExprList();
            	    		ArrayList<Expression> assertionList = new ArrayList<Expression>();
            	    		Expression guardExpr = TrueExpr; 
            	    		int delayLB = 0; 
            	    		int delayUB = INFINITY;
            	    		boolean local = true;
            	    	
            match(input,LESS,FOLLOW_LESS_in_transition980); 
            match(input,TRANSITION,FOLLOW_TRANSITION_in_transition982); 
            match(input,LABEL,FOLLOW_LABEL_in_transition984); 
            match(input,EQUALS,FOLLOW_EQUALS_in_transition986); 
            match(input,QUOTE,FOLLOW_QUOTE_in_transition988); 
            lbl=(Token)input.LT(1);
            if ( (input.LA(1)>=ID && input.LA(1)<=INT) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            match(input,QUOTE,FOLLOW_QUOTE_in_transition998); 
            match(input,PRESET,FOLLOW_PRESET_in_transition1000); 
            match(input,EQUALS,FOLLOW_EQUALS_in_transition1002); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:550:69: ( '\"' '\"' | ( '\"' (pre= INT | pre1= ID ) ( ',' pre2= INT | ',' pre3= ID )* '\"' ) )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==QUOTE) ) {
                int LA27_1 = input.LA(2);

                if ( (LA27_1==QUOTE) ) {
                    alt27=1;
                }
                else if ( ((LA27_1>=ID && LA27_1<=INT)) ) {
                    alt27=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 27, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:550:70: '\"' '\"'
                    {
                    match(input,QUOTE,FOLLOW_QUOTE_in_transition1005); 
                    match(input,QUOTE,FOLLOW_QUOTE_in_transition1007); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:550:80: ( '\"' (pre= INT | pre1= ID ) ( ',' pre2= INT | ',' pre3= ID )* '\"' )
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:550:80: ( '\"' (pre= INT | pre1= ID ) ( ',' pre2= INT | ',' pre3= ID )* '\"' )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:550:81: '\"' (pre= INT | pre1= ID ) ( ',' pre2= INT | ',' pre3= ID )* '\"'
                    {
                    match(input,QUOTE,FOLLOW_QUOTE_in_transition1012); 
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:550:85: (pre= INT | pre1= ID )
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( (LA25_0==INT) ) {
                        alt25=1;
                    }
                    else if ( (LA25_0==ID) ) {
                        alt25=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 25, 0, input);

                        throw nvae;
                    }
                    switch (alt25) {
                        case 1 :
                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:550:86: pre= INT
                            {
                            pre=(Token)match(input,INT,FOLLOW_INT_in_transition1017); 

                                			presetList.add(Integer.parseInt((pre!=null?pre.getText():null)));
                               			

                            }
                            break;
                        case 2 :
                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:554:7: pre1= ID
                            {
                            pre1=(Token)match(input,ID,FOLLOW_ID_in_transition1037); 

                              				result = ConstHashMap.get((pre1!=null?pre1.getText():null)); 
                              				if(result == null){
                              					System.err.println("error on line " + pre1.getLine() + ": " + (pre1!=null?pre1.getText():null) + " is not a constant");
                              					System.exit(1);
                              				}
                              				
                              				presetList.add(result);
                              			

                            }
                            break;

                    }

                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:564:6: ( ',' pre2= INT | ',' pre3= ID )*
                    loop26:
                    do {
                        int alt26=3;
                        int LA26_0 = input.LA(1);

                        if ( (LA26_0==COMMA) ) {
                            int LA26_2 = input.LA(2);

                            if ( (LA26_2==INT) ) {
                                alt26=1;
                            }
                            else if ( (LA26_2==ID) ) {
                                alt26=2;
                            }


                        }


                        switch (alt26) {
                    	case 1 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:564:8: ',' pre2= INT
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_transition1053); 
                    	    pre2=(Token)match(input,INT,FOLLOW_INT_in_transition1057); 

                    	        			presetList.add(Integer.parseInt((pre2!=null?pre2.getText():null)));
                    	       			

                    	    }
                    	    break;
                    	case 2 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:568:7: ',' pre3= ID
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_transition1075); 
                    	    pre3=(Token)match(input,ID,FOLLOW_ID_in_transition1079); 

                    	      				result = ConstHashMap.get((pre3!=null?pre3.getText():null)); 
                    	      				if(result == null){
                    	      					System.err.println("error on line " + pre3.getLine() + ": " + (pre3!=null?pre3.getText():null) + " is not a constant");
                    	      					System.exit(1);
                    	      				}
                    	      				
                    	      				presetList.add(result);
                    	      			

                    	    }
                    	    break;

                    	default :
                    	    break loop26;
                        }
                    } while (true);

                    match(input,QUOTE,FOLLOW_QUOTE_in_transition1094); 

                    }


                    }
                    break;

            }

            match(input,POSTSET,FOLLOW_POSTSET_in_transition1098); 
            match(input,EQUALS,FOLLOW_EQUALS_in_transition1100); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:578:27: ( '\"' '\"' | ( '\"' (post= INT | post1= ID ) ( ( ',' post2= INT ) | ( ',' post3= ID ) )* '\"' ) )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==QUOTE) ) {
                int LA30_1 = input.LA(2);

                if ( (LA30_1==QUOTE) ) {
                    alt30=1;
                }
                else if ( ((LA30_1>=ID && LA30_1<=INT)) ) {
                    alt30=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 30, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:578:29: '\"' '\"'
                    {
                    match(input,QUOTE,FOLLOW_QUOTE_in_transition1104); 
                    match(input,QUOTE,FOLLOW_QUOTE_in_transition1106); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:578:39: ( '\"' (post= INT | post1= ID ) ( ( ',' post2= INT ) | ( ',' post3= ID ) )* '\"' )
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:578:39: ( '\"' (post= INT | post1= ID ) ( ( ',' post2= INT ) | ( ',' post3= ID ) )* '\"' )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:578:40: '\"' (post= INT | post1= ID ) ( ( ',' post2= INT ) | ( ',' post3= ID ) )* '\"'
                    {
                    match(input,QUOTE,FOLLOW_QUOTE_in_transition1111); 
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:578:44: (post= INT | post1= ID )
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==INT) ) {
                        alt28=1;
                    }
                    else if ( (LA28_0==ID) ) {
                        alt28=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 28, 0, input);

                        throw nvae;
                    }
                    switch (alt28) {
                        case 1 :
                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:578:46: post= INT
                            {
                            post=(Token)match(input,INT,FOLLOW_INT_in_transition1117); 

                                			postsetList.add(Integer.parseInt((post!=null?post.getText():null)));
                                		

                            }
                            break;
                        case 2 :
                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:582:8: post1= ID
                            {
                            post1=(Token)match(input,ID,FOLLOW_ID_in_transition1137); 

                                			result = ConstHashMap.get((post1!=null?post1.getText():null)); 
                              				if(result == null){
                              					System.err.println("error on line " + post1.getLine() + ": " + (post1!=null?post1.getText():null) + " is not a constant");
                              					System.exit(1);
                              				}
                              				
                              				postsetList.add(result);
                                		

                            }
                            break;

                    }

                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:593:6: ( ( ',' post2= INT ) | ( ',' post3= ID ) )*
                    loop29:
                    do {
                        int alt29=3;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==COMMA) ) {
                            int LA29_2 = input.LA(2);

                            if ( (LA29_2==INT) ) {
                                alt29=1;
                            }
                            else if ( (LA29_2==ID) ) {
                                alt29=2;
                            }


                        }


                        switch (alt29) {
                    	case 1 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:593:8: ( ',' post2= INT )
                    	    {
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:593:8: ( ',' post2= INT )
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:593:9: ',' post2= INT
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_transition1162); 
                    	    post2=(Token)match(input,INT,FOLLOW_INT_in_transition1166); 

                    	    }


                    	        			postsetList.add(Integer.parseInt((post2!=null?post2.getText():null)));
                    	        		

                    	    }
                    	    break;
                    	case 2 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:597:8: ( ',' post3= ID )
                    	    {
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:597:8: ( ',' post3= ID )
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:597:9: ',' post3= ID
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_transition1186); 
                    	    post3=(Token)match(input,ID,FOLLOW_ID_in_transition1189); 

                    	    }


                    	        			result = ConstHashMap.get((post3!=null?post3.getText():null)); 
                    	      				if(result == null){
                    	      					System.err.println("error on line " + post3.getLine() + ": " + (post3!=null?post3.getText():null) + " is not a constant");
                    	      					System.exit(1);
                    	      				}
                    	      				
                    	      				postsetList.add(result);
                    	        		

                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);

                    match(input,QUOTE,FOLLOW_QUOTE_in_transition1208); 

                    }


                    }
                    break;

            }

            match(input,GREATER,FOLLOW_GREATER_in_transition1213); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:607:20: ( guard )?
            int alt31=2;
            switch ( input.LA(1) ) {
                case INT:
                case TRUE:
                case FALSE:
                case PLUS:
                case MINUS:
                case NEGATION:
                case BITWISE_NEGATION:
                    {
                    alt31=1;
                    }
                    break;
                case ID:
                    {
                    int LA31_2 = input.LA(2);

                    if ( (LA31_2==QMARK||LA31_2==SEMICOLON||(LA31_2>=PLUS && LA31_2<=MOD)||(LA31_2>=GREATER && LA31_2<=NOT_EQUIV)||(LA31_2>=AND && LA31_2<=IMPLICATION)||(LA31_2>=BITWISE_AND && LA31_2<=BITWISE_RSHIFT)) ) {
                        alt31=1;
                    }
                    }
                    break;
                case LPAREN:
                    {
                    int LA31_3 = input.LA(2);

                    if ( (LA31_3==INT) ) {
                        int LA31_5 = input.LA(3);

                        if ( (LA31_5==RPAREN||LA31_5==QMARK||(LA31_5>=PLUS && LA31_5<=MOD)||(LA31_5>=GREATER && LA31_5<=NOT_EQUIV)||(LA31_5>=AND && LA31_5<=IMPLICATION)||(LA31_5>=BITWISE_AND && LA31_5<=BITWISE_RSHIFT)) ) {
                            alt31=1;
                        }
                    }
                    else if ( (LA31_3==ID||LA31_3==LPAREN||(LA31_3>=TRUE && LA31_3<=FALSE)||(LA31_3>=PLUS && LA31_3<=MINUS)||LA31_3==NEGATION||LA31_3==BITWISE_NEGATION) ) {
                        alt31=1;
                    }
                    }
                    break;
            }

            switch (alt31) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:607:21: guard
                    {
                    pushFollow(FOLLOW_guard_in_transition1216);
                    guard6=guard();

                    state._fsp--;


                        			guardExpr = guard6;
                        		

                    }
                    break;

            }

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:611:9: ( delay )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==LPAREN) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:611:10: delay
                    {
                    pushFollow(FOLLOW_delay_in_transition1236);
                    delay7=delay();

                    state._fsp--;


                        			delayLB = (delay7!=null?delay7.delayLB:0); 
                        			delayUB = (delay7!=null?delay7.delayUB:0);
                        		

                    }
                    break;

            }

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:616:9: ( ( assertion ) | ( assignment ) )*
            loop33:
            do {
                int alt33=3;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==62) ) {
                    alt33=1;
                }
                else if ( (LA33_0==ID) ) {
                    alt33=2;
                }


                switch (alt33) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:616:10: ( assertion )
            	    {
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:616:10: ( assertion )
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:616:11: assertion
            	    {
            	    pushFollow(FOLLOW_assertion_in_transition1257);
            	    assertion8=assertion();

            	    state._fsp--;


            	        			if(assertion8 != null){		
            	      					assertionList.add(assertion8);
            	      				}
            	        		

            	    }


            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:622:10: ( assignment )
            	    {
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:622:10: ( assignment )
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:622:11: assignment
            	    {
            	    pushFollow(FOLLOW_assignment_in_transition1277);
            	    assignment9=assignment();

            	    state._fsp--;


            	        			assignmentList.add(assignment9);
            	        		

            	    }


            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);

            match(input,LESS,FOLLOW_LESS_in_transition1296); 
            match(input,DIV,FOLLOW_DIV_in_transition1298); 
            match(input,TRANSITION,FOLLOW_TRANSITION_in_transition1300); 
            match(input,GREATER,FOLLOW_GREATER_in_transition1302); 

                    	// create new lpn transitions and add assertions
                    	lpnTran = new LPNTran((lbl!=null?lbl.getText():null), TransitionIndex++, presetList, postsetList, guardExpr, assignmentList, delayLB, delayUB, local);
                    	if(assertionList.size() > 0){
                    		lpnTran.addAllAssertions(assertionList);
                    	}
                    	
                    	// add non-local transition to associated LPNs
                    	for(VarExpr e : assignmentList){
                    		VarNode var = e.getVar();
                    		if(Outputs.contains(var.getName())){
                    			local = false;
                    			
                    			if(GlobalInputMap.containsKey(var.getName())){
            	        			for(LPN lpn : GlobalInputMap.get(var.getName())){
            	        				lpn.addInputTran(lpnTran);
            	        				lpnTran.addDstLpn(lpn);
            	        			}
                    			}
                    		}
                    		
                    		// map lpn transition with output and potential outuput variables
                    		if(GlobalTranMap.containsKey(var.getName())){
                   				GlobalTranMap.get(var.getName()).add(lpnTran);
                   			}
                   			else{
                   				List<LPNTran> tranList = new ArrayList<LPNTran>();
                   				tranList.add(lpnTran);
                   				GlobalTranMap.put(var.getName(), tranList);
                   			}
                    	}
                    	
                   		lpnTran.setLocalFlag(local);
                   		if(local == false){
                   			outputTranList.add(lpnTran);
                   		}
                    

            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return lpnTran;
    }
    // $ANTLR end "transition"


    // $ANTLR start "assertion"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:666:1: assertion returns [Expression booleanExpr] : 'assert' '(' expression ')' ';' ;
    public final Expression assertion() throws RecognitionException {
        Expression booleanExpr = null;

        ExpressionNode expression10 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:667:2: ( 'assert' '(' expression ')' ';' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:667:4: 'assert' '(' expression ')' ';'
            {
            booleanExpr = null;
            match(input,62,FOLLOW_62_in_assertion1336); 
            match(input,LPAREN,FOLLOW_LPAREN_in_assertion1338); 
            pushFollow(FOLLOW_expression_in_assertion1340);
            expression10=expression();

            state._fsp--;

            match(input,RPAREN,FOLLOW_RPAREN_in_assertion1342); 
            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_assertion1344); 

            				booleanExpr = new Expression(expression10);
            			

            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return booleanExpr;
    }
    // $ANTLR end "assertion"


    // $ANTLR start "guard"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:674:1: guard returns [Expression expr] : expression ';' ;
    public final Expression guard() throws RecognitionException {
        Expression expr = null;

        ExpressionNode expression11 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:675:5: ( expression ';' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:675:9: expression ';'
            {
            pushFollow(FOLLOW_expression_in_guard1370);
            expression11=expression();

            state._fsp--;

            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_guard1372); 

               				expr = new Expression(expression11);
                		

            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return expr;
    }
    // $ANTLR end "guard"

    public static class delay_return extends ParserRuleReturnScope {
        public int delayLB;
        public int delayUB;
    };

    // $ANTLR start "delay"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:681:1: delay returns [int delayLB, int delayUB] : '(' lb= INT ',' (ub= INT | 'inf' ) ')' ';' ;
    public final PlatuGrammarParser.delay_return delay() throws RecognitionException {
        PlatuGrammarParser.delay_return retval = new PlatuGrammarParser.delay_return();
        retval.start = input.LT(1);

        Token lb=null;
        Token ub=null;

        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:682:5: ( '(' lb= INT ',' (ub= INT | 'inf' ) ')' ';' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:682:8: '(' lb= INT ',' (ub= INT | 'inf' ) ')' ';'
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_delay1406); 
            lb=(Token)match(input,INT,FOLLOW_INT_in_delay1410); 

                			retval.delayLB = Integer.parseInt((lb!=null?lb.getText():null));
               			
            match(input,COMMA,FOLLOW_COMMA_in_delay1424); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:686:8: (ub= INT | 'inf' )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==INT) ) {
                alt34=1;
            }
            else if ( (LA34_0==63) ) {
                alt34=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }
            switch (alt34) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:686:9: ub= INT
                    {
                    ub=(Token)match(input,INT,FOLLOW_INT_in_delay1429); 

                     				retval.delayUB = Integer.parseInt((ub!=null?ub.getText():null));
                     				// make sure delays are >= 0 and upper bound is >= lower bound
                     				if(retval.delayLB < 0){
                     					System.err.println("error on line " + lb.getLine() + ": lower bound " + retval.delayLB + " must be >= 0");
                      					System.exit(1);
                     				}
                     				else if(retval.delayLB == INFINITY){
                     					System.err.println("error on line " + ub.getLine() + ": lower bound " + retval.delayUB + " must be a non-negative finite number");
                      					System.exit(1);
                     				}
                     				else if(retval.delayUB < retval.delayLB){
                     					System.err.println("error on line " + ub.getLine() + ": upper bound " + retval.delayUB + " < lower bound " + retval.delayLB);
                      					System.exit(1);
                     				} 
                     			

                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:703:6: 'inf'
                    {
                    match(input,63,FOLLOW_63_in_delay1444); 

                     				retval.delayUB = INFINITY;
                    			

                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_delay1457); 
            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_delay1459); 

            }

            retval.stop = input.LT(-1);

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "delay"


    // $ANTLR start "assignment"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:710:1: assignment returns [VarExpr assign] : ( (var= ID '=' expression ';' ) | (var2= ID ( '[' ( INT | ID ) ']' )+ '=' expression ';' ) );
    public final VarExpr assignment() throws RecognitionException {
        VarExpr assign = null;

        Token var=null;
        Token var2=null;
        ExpressionNode expression12 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:711:5: ( (var= ID '=' expression ';' ) | (var2= ID ( '[' ( INT | ID ) ']' )+ '=' expression ';' ) )
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==ID) ) {
                int LA36_1 = input.LA(2);

                if ( (LA36_1==EQUALS) ) {
                    alt36=1;
                }
                else if ( (LA36_1==59) ) {
                    alt36=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 36, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }
            switch (alt36) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:711:9: (var= ID '=' expression ';' )
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:711:9: (var= ID '=' expression ';' )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:711:10: var= ID '=' expression ';'
                    {
                    var=(Token)match(input,ID,FOLLOW_ID_in_assignment1485); 
                    match(input,EQUALS,FOLLOW_EQUALS_in_assignment1487); 
                    	
                        			// make sure only global, internal and output variables are assigned
                        			if(GlobalConstHashMap.containsKey((var!=null?var.getText():null))){
                        				System.err.println("error on line " + var.getLine() + ": global constant " + (var!=null?var.getText():null) + " cannot be assigned");
                        				System.exit(1);
                        			}
                        			else if(ConstHashMap.containsKey((var!=null?var.getText():null))){
                        				System.err.println("error on line " + var.getLine() + ": constant " + (var!=null?var.getText():null) + " cannot be assigned");
                        				System.exit(1);
                        			}
                        			else if(!Outputs.contains((var!=null?var.getText():null)) && !Internals.contains((var!=null?var.getText():null))){
                        				System.err.println("error on line " + var.getLine() + ": input variable " + (var!=null?var.getText():null) + " cannot be assigned");
                        				System.exit(1);
                        			}
                        		
                    pushFollow(FOLLOW_expression_in_assignment1503);
                    expression12=expression();

                    state._fsp--;

                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_assignment1505); 

                    	    		Integer varCount = VarCountMap.get((var!=null?var.getText():null));
                    	    		if(varCount != null){
                    	    			VarCountMap.put((var!=null?var.getText():null), ++varCount);
                    	    		}
                    	    		
                    	    		Expression expr = new Expression(expression12);
                    	    		assign = new VarExpr(VarNodeMap.get((var!=null?var.getText():null)), expr);
                    	   		

                    }


                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:737:10: (var2= ID ( '[' ( INT | ID ) ']' )+ '=' expression ';' )
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:737:10: (var2= ID ( '[' ( INT | ID ) ']' )+ '=' expression ';' )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:737:11: var2= ID ( '[' ( INT | ID ) ']' )+ '=' expression ';'
                    {
                    var2=(Token)match(input,ID,FOLLOW_ID_in_assignment1527); 

                    	   			List<Integer> indexList = new ArrayList<Integer>();
                    	   			
                    	   		
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:742:6: ( '[' ( INT | ID ) ']' )+
                    int cnt35=0;
                    loop35:
                    do {
                        int alt35=2;
                        int LA35_0 = input.LA(1);

                        if ( (LA35_0==59) ) {
                            alt35=1;
                        }


                        switch (alt35) {
                    	case 1 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:742:7: '[' ( INT | ID ) ']'
                    	    {
                    	    match(input,59,FOLLOW_59_in_assignment1544); 
                    	    if ( (input.LA(1)>=ID && input.LA(1)<=INT) ) {
                    	        input.consume();
                    	        state.errorRecovery=false;
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        throw mse;
                    	    }

                    	    match(input,60,FOLLOW_60_in_assignment1554); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt35 >= 1 ) break loop35;
                                EarlyExitException eee =
                                    new EarlyExitException(35, input);
                                throw eee;
                        }
                        cnt35++;
                    } while (true);

                    match(input,EQUALS,FOLLOW_EQUALS_in_assignment1558); 
                    pushFollow(FOLLOW_expression_in_assignment1560);
                    expression();

                    state._fsp--;

                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_assignment1562); 

                    	   		
                    	   		

                    }


                    }
                    break;

            }
        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return assign;
    }
    // $ANTLR end "assignment"


    // $ANTLR start "term"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:750:1: term returns [ExpressionNode expr] : ( ID | LPAREN expression RPAREN | INT | TRUE | FALSE );
    public final ExpressionNode term() throws RecognitionException {
        ExpressionNode expr = null;

        Token ID13=null;
        Token INT15=null;
        ExpressionNode expression14 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:751:5: ( ID | LPAREN expression RPAREN | INT | TRUE | FALSE )
            int alt37=5;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt37=1;
                }
                break;
            case LPAREN:
                {
                alt37=2;
                }
                break;
            case INT:
                {
                alt37=3;
                }
                break;
            case TRUE:
                {
                alt37=4;
                }
                break;
            case FALSE:
                {
                alt37=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }

            switch (alt37) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:751:9: ID
                    {
                    ID13=(Token)match(input,ID,FOLLOW_ID_in_term1605); 

                        			if(ConstHashMap.containsKey((ID13!=null?ID13.getText():null))){
                        				expr = new ConstNode((ID13!=null?ID13.getText():null), ConstHashMap.get((ID13!=null?ID13.getText():null)));
                        			}
                        			else if(GlobalConstHashMap.containsKey((ID13!=null?ID13.getText():null))){
                        			 expr = new ConstNode((ID13!=null?ID13.getText():null), GlobalConstHashMap.get((ID13!=null?ID13.getText():null)));
                        			}
                        			else if(StatevectorMap.containsKey((ID13!=null?ID13.getText():null))){ 
                    //    				expr = new platu.lpn.io.expression.VarNode((ID13!=null?ID13.getText():null));
                    					expr = VarNodeMap.get((ID13!=null?ID13.getText():null));
                        			}
                        			else{ // identify new input variable
                    //    				// create expression
                    //					expr = new platu.lpn.io.expression.VarNode((ID13!=null?ID13.getText():null));
                    					
                    					// label as input and initialize to 0
                    					StatevectorMap.put((ID13!=null?ID13.getText():null), 0);
                    					Inputs.add((ID13!=null?ID13.getText():null));
                    					
                    					// generate a varaible index and create new variable object
                    					int index = VariableIndex++;
                    	    			VarIndexMap.insert((ID13!=null?ID13.getText():null), index);
                    	    			VarNode newVarNode = new VarNode((ID13!=null?ID13.getText():null), index);
                    	    			newVarNode.setType(platu.lpn.VarType.INPUT);
                    	    			VarNodeMap.put((ID13!=null?ID13.getText():null), newVarNode);
                    	    			expr = newVarNode;
                    	    			
                    	    			// if associated output variable has not been defined insert with null value,
                    	    			// otherwise get output variable and relabel from internal to output, 
                    	    			// get output value and initialize input statevector, label lpn transitions associated with output as non-local
                    	    			// and add to current lpn's inputTranList
                    	    			if(!GlobalInterfaceMap.containsKey((ID13!=null?ID13.getText():null))){
                    	    				GlobalInterfaceMap.put((ID13!=null?ID13.getText():null), null);
                    	    			}
                    	    			else{
                    	    				Integer value = GlobalInterfaceMap.get((ID13!=null?ID13.getText():null));
                    	    				if(value != null){
                    	    					StatevectorMap.put((ID13!=null?ID13.getText():null), value);
                    	    					
                    	    					LPN outputLPN = GlobalOutputMap.get((ID13!=null?ID13.getText():null));
                    	    					
                    	    					VarSet internals = outputLPN.getInternals();
                    	    					if(internals.contains((ID13!=null?ID13.getText():null))){
                    	    						internals.remove((ID13!=null?ID13.getText():null));
                    	    						outputLPN.getGlobals().add((ID13!=null?ID13.getText():null));
                    	    						VarNode outputNode = outputLPN.getVarNode((ID13!=null?ID13.getText():null));
                    	    						outputNode.setType(platu.lpn.VarType.OUTPUT);
                    	    					}
                    	    					
                    	    					
                    	    					for(LPNTran tran : GlobalTranMap.get((ID13!=null?ID13.getText():null))){
                    	    						tran.setLocalFlag(false);
                    	    						outputLPN.addOutputTran(tran);
                    	    						
                    	    						inputTranList.add(tran);
                    	    					}
                    	    				}
                    	    			}
                        			}
                       			

                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:812:9: LPAREN expression RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_term1623); 
                    pushFollow(FOLLOW_expression_in_term1625);
                    expression14=expression();

                    state._fsp--;

                    match(input,RPAREN,FOLLOW_RPAREN_in_term1627); 
                    expr = expression14;

                    }
                    break;
                case 3 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:813:9: INT
                    {
                    INT15=(Token)match(input,INT,FOLLOW_INT_in_term1639); 
                    expr = new ConstNode("name", Integer.parseInt((INT15!=null?INT15.getText():null)));

                    }
                    break;
                case 4 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:814:9: TRUE
                    {
                    match(input,TRUE,FOLLOW_TRUE_in_term1651); 
                    expr = ONE;

                    }
                    break;
                case 5 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:815:9: FALSE
                    {
                    match(input,FALSE,FOLLOW_FALSE_in_term1663); 
                    expr = ZERO;

                    }
                    break;

            }
        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return expr;
    }
    // $ANTLR end "term"


    // $ANTLR start "unary"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:818:1: unary returns [ExpressionNode expr] : ( '+' | ( '-' ) )* term ;
    public final ExpressionNode unary() throws RecognitionException {
        ExpressionNode expr = null;

        ExpressionNode term16 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:819:5: ( ( '+' | ( '-' ) )* term )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:819:9: ( '+' | ( '-' ) )* term
            {
            boolean positive = true;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:820:6: ( '+' | ( '-' ) )*
            loop38:
            do {
                int alt38=3;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==PLUS) ) {
                    alt38=1;
                }
                else if ( (LA38_0==MINUS) ) {
                    alt38=2;
                }


                switch (alt38) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:820:7: '+'
            	    {
            	    match(input,PLUS,FOLLOW_PLUS_in_unary1700); 

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:820:13: ( '-' )
            	    {
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:820:13: ( '-' )
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:820:14: '-'
            	    {
            	    match(input,MINUS,FOLLOW_MINUS_in_unary1705); 
            	    if(positive){ positive = false;} else {positive = true;}

            	    }


            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);

            pushFollow(FOLLOW_term_in_unary1712);
            term16=term();

            state._fsp--;


                		if(!positive){
                			expr = new MinNode(term16);
                		}
                		else{
                			expr = term16;
               			}
                	

            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return expr;
    }
    // $ANTLR end "unary"


    // $ANTLR start "bitwiseNegation"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:831:1: bitwiseNegation returns [ExpressionNode expr] : ( '~' )* unary ;
    public final ExpressionNode bitwiseNegation() throws RecognitionException {
        ExpressionNode expr = null;

        ExpressionNode unary17 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:832:2: ( ( '~' )* unary )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:832:5: ( '~' )* unary
            {
            boolean neg = false;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:833:3: ( '~' )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==BITWISE_NEGATION) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:833:4: '~'
            	    {
            	    match(input,BITWISE_NEGATION,FOLLOW_BITWISE_NEGATION_in_bitwiseNegation1743); 
            	    if(neg){neg = false;} else{neg = true;}

            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);

            pushFollow(FOLLOW_unary_in_bitwiseNegation1749);
            unary17=unary();

            state._fsp--;


            				if(neg){
            					expr = new BitNegNode(unary17);
            				}
            				else{
            					expr = unary17;
            				}
            			

            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return expr;
    }
    // $ANTLR end "bitwiseNegation"


    // $ANTLR start "negation"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:844:1: negation returns [ExpressionNode expr] : ( '!' )* bitwiseNegation ;
    public final ExpressionNode negation() throws RecognitionException {
        ExpressionNode expr = null;

        ExpressionNode bitwiseNegation18 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:845:2: ( ( '!' )* bitwiseNegation )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:845:4: ( '!' )* bitwiseNegation
            {
            boolean neg = false;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:846:3: ( '!' )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==NEGATION) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:846:4: '!'
            	    {
            	    match(input,NEGATION,FOLLOW_NEGATION_in_negation1775); 
            	    if(neg){neg = false;} else{neg = true;}

            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);

            pushFollow(FOLLOW_bitwiseNegation_in_negation1781);
            bitwiseNegation18=bitwiseNegation();

            state._fsp--;


            				if(neg){
            					expr = new NegNode(bitwiseNegation18);
            				}
            				else{
            					expr = bitwiseNegation18;
            				}
            			

            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return expr;
    }
    // $ANTLR end "negation"


    // $ANTLR start "mult"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:857:1: mult returns [ExpressionNode expr] : op1= negation ( '*' op2= negation | '/' op2= negation | '%' op2= negation )* ;
    public final ExpressionNode mult() throws RecognitionException {
        ExpressionNode expr = null;

        ExpressionNode op1 = null;

        ExpressionNode op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:858:5: (op1= negation ( '*' op2= negation | '/' op2= negation | '%' op2= negation )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:858:9: op1= negation ( '*' op2= negation | '/' op2= negation | '%' op2= negation )*
            {
            pushFollow(FOLLOW_negation_in_mult1809);
            op1=negation();

            state._fsp--;

            expr = op1;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:859:6: ( '*' op2= negation | '/' op2= negation | '%' op2= negation )*
            loop41:
            do {
                int alt41=4;
                switch ( input.LA(1) ) {
                case TIMES:
                    {
                    alt41=1;
                    }
                    break;
                case DIV:
                    {
                    alt41=2;
                    }
                    break;
                case MOD:
                    {
                    alt41=3;
                    }
                    break;

                }

                switch (alt41) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:859:8: '*' op2= negation
            	    {
            	    match(input,TIMES,FOLLOW_TIMES_in_mult1821); 
            	    pushFollow(FOLLOW_negation_in_mult1825);
            	    op2=negation();

            	    state._fsp--;

            	    expr = new MultNode(expr, op2);

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:860:8: '/' op2= negation
            	    {
            	    match(input,DIV,FOLLOW_DIV_in_mult1836); 
            	    pushFollow(FOLLOW_negation_in_mult1840);
            	    op2=negation();

            	    state._fsp--;

            	    expr = new DivNode(expr, op2);

            	    }
            	    break;
            	case 3 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:861:8: '%' op2= negation
            	    {
            	    match(input,MOD,FOLLOW_MOD_in_mult1851); 
            	    pushFollow(FOLLOW_negation_in_mult1855);
            	    op2=negation();

            	    state._fsp--;

            	    expr = new ModNode(expr, op2);

            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);


            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return expr;
    }
    // $ANTLR end "mult"


    // $ANTLR start "add"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:865:1: add returns [ExpressionNode expr] : op1= mult ( '+' op2= mult | '-' op2= mult )* ;
    public final ExpressionNode add() throws RecognitionException {
        ExpressionNode expr = null;

        ExpressionNode op1 = null;

        ExpressionNode op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:866:5: (op1= mult ( '+' op2= mult | '-' op2= mult )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:866:9: op1= mult ( '+' op2= mult | '-' op2= mult )*
            {
            pushFollow(FOLLOW_mult_in_add1894);
            op1=mult();

            state._fsp--;

            expr = op1;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:867:6: ( '+' op2= mult | '-' op2= mult )*
            loop42:
            do {
                int alt42=3;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==PLUS) ) {
                    alt42=1;
                }
                else if ( (LA42_0==MINUS) ) {
                    alt42=2;
                }


                switch (alt42) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:867:8: '+' op2= mult
            	    {
            	    match(input,PLUS,FOLLOW_PLUS_in_add1905); 
            	    pushFollow(FOLLOW_mult_in_add1909);
            	    op2=mult();

            	    state._fsp--;

            	    expr = new AddNode(expr, op2);

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:868:9: '-' op2= mult
            	    {
            	    match(input,MINUS,FOLLOW_MINUS_in_add1921); 
            	    pushFollow(FOLLOW_mult_in_add1925);
            	    op2=mult();

            	    state._fsp--;

            	    expr = new SubNode(expr, op2);

            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);


            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return expr;
    }
    // $ANTLR end "add"


    // $ANTLR start "shift"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:872:1: shift returns [ExpressionNode expr] : op1= add ( '<<' op2= add | '>>' op2= add )* ;
    public final ExpressionNode shift() throws RecognitionException {
        ExpressionNode expr = null;

        ExpressionNode op1 = null;

        ExpressionNode op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:873:5: (op1= add ( '<<' op2= add | '>>' op2= add )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:873:9: op1= add ( '<<' op2= add | '>>' op2= add )*
            {
            pushFollow(FOLLOW_add_in_shift1964);
            op1=add();

            state._fsp--;

            expr = op1;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:874:6: ( '<<' op2= add | '>>' op2= add )*
            loop43:
            do {
                int alt43=3;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==BITWISE_LSHIFT) ) {
                    alt43=1;
                }
                else if ( (LA43_0==BITWISE_RSHIFT) ) {
                    alt43=2;
                }


                switch (alt43) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:874:8: '<<' op2= add
            	    {
            	    match(input,BITWISE_LSHIFT,FOLLOW_BITWISE_LSHIFT_in_shift1975); 
            	    pushFollow(FOLLOW_add_in_shift1979);
            	    op2=add();

            	    state._fsp--;

            	    expr = new LeftShiftNode(expr, op2);

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:875:9: '>>' op2= add
            	    {
            	    match(input,BITWISE_RSHIFT,FOLLOW_BITWISE_RSHIFT_in_shift1991); 
            	    pushFollow(FOLLOW_add_in_shift1995);
            	    op2=add();

            	    state._fsp--;

            	    expr = new RightShiftNode(expr, op2);

            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);


            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return expr;
    }
    // $ANTLR end "shift"


    // $ANTLR start "relation"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:879:1: relation returns [ExpressionNode expr] : op1= shift ( '<' op2= shift | '<=' op2= shift | '>=' op2= shift | '>' op2= shift )* ;
    public final ExpressionNode relation() throws RecognitionException {
        ExpressionNode expr = null;

        ExpressionNode op1 = null;

        ExpressionNode op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:880:5: (op1= shift ( '<' op2= shift | '<=' op2= shift | '>=' op2= shift | '>' op2= shift )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:880:9: op1= shift ( '<' op2= shift | '<=' op2= shift | '>=' op2= shift | '>' op2= shift )*
            {
            pushFollow(FOLLOW_shift_in_relation2030);
            op1=shift();

            state._fsp--;

            expr = op1;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:881:6: ( '<' op2= shift | '<=' op2= shift | '>=' op2= shift | '>' op2= shift )*
            loop44:
            do {
                int alt44=5;
                switch ( input.LA(1) ) {
                case LESS:
                    {
                    alt44=1;
                    }
                    break;
                case LESS_EQUAL:
                    {
                    alt44=2;
                    }
                    break;
                case GREATER_EQUAL:
                    {
                    alt44=3;
                    }
                    break;
                case GREATER:
                    {
                    alt44=4;
                    }
                    break;

                }

                switch (alt44) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:881:8: '<' op2= shift
            	    {
            	    match(input,LESS,FOLLOW_LESS_in_relation2041); 
            	    pushFollow(FOLLOW_shift_in_relation2045);
            	    op2=shift();

            	    state._fsp--;

            	    expr = new LessNode(expr, op2);

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:882:9: '<=' op2= shift
            	    {
            	    match(input,LESS_EQUAL,FOLLOW_LESS_EQUAL_in_relation2057); 
            	    pushFollow(FOLLOW_shift_in_relation2061);
            	    op2=shift();

            	    state._fsp--;

            	    expr = new LessEqualNode(expr, op2);

            	    }
            	    break;
            	case 3 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:883:9: '>=' op2= shift
            	    {
            	    match(input,GREATER_EQUAL,FOLLOW_GREATER_EQUAL_in_relation2073); 
            	    pushFollow(FOLLOW_shift_in_relation2077);
            	    op2=shift();

            	    state._fsp--;

            	    expr = new GreatEqualNode(expr, op2);

            	    }
            	    break;
            	case 4 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:884:9: '>' op2= shift
            	    {
            	    match(input,GREATER,FOLLOW_GREATER_in_relation2089); 
            	    pushFollow(FOLLOW_shift_in_relation2093);
            	    op2=shift();

            	    state._fsp--;

            	    expr = new GreatNode(expr, op2);

            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);


            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return expr;
    }
    // $ANTLR end "relation"


    // $ANTLR start "equivalence"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:888:1: equivalence returns [ExpressionNode expr] : op1= relation ( '==' op2= relation | '!=' op2= relation )* ;
    public final ExpressionNode equivalence() throws RecognitionException {
        ExpressionNode expr = null;

        ExpressionNode op1 = null;

        ExpressionNode op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:889:5: (op1= relation ( '==' op2= relation | '!=' op2= relation )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:889:9: op1= relation ( '==' op2= relation | '!=' op2= relation )*
            {
            pushFollow(FOLLOW_relation_in_equivalence2132);
            op1=relation();

            state._fsp--;

            expr = op1;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:890:6: ( '==' op2= relation | '!=' op2= relation )*
            loop45:
            do {
                int alt45=3;
                int LA45_0 = input.LA(1);

                if ( (LA45_0==EQUIV) ) {
                    alt45=1;
                }
                else if ( (LA45_0==NOT_EQUIV) ) {
                    alt45=2;
                }


                switch (alt45) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:890:8: '==' op2= relation
            	    {
            	    match(input,EQUIV,FOLLOW_EQUIV_in_equivalence2143); 
            	    pushFollow(FOLLOW_relation_in_equivalence2147);
            	    op2=relation();

            	    state._fsp--;

            	    expr = new EquivNode(expr, op2);

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:891:8: '!=' op2= relation
            	    {
            	    match(input,NOT_EQUIV,FOLLOW_NOT_EQUIV_in_equivalence2158); 
            	    pushFollow(FOLLOW_relation_in_equivalence2162);
            	    op2=relation();

            	    state._fsp--;

            	    expr = new NotEquivNode(expr, op2);

            	    }
            	    break;

            	default :
            	    break loop45;
                }
            } while (true);


            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return expr;
    }
    // $ANTLR end "equivalence"


    // $ANTLR start "bitwiseAnd"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:895:1: bitwiseAnd returns [ExpressionNode expr] : op1= equivalence ( '&' op2= equivalence )* ;
    public final ExpressionNode bitwiseAnd() throws RecognitionException {
        ExpressionNode expr = null;

        ExpressionNode op1 = null;

        ExpressionNode op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:896:5: (op1= equivalence ( '&' op2= equivalence )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:896:9: op1= equivalence ( '&' op2= equivalence )*
            {
            pushFollow(FOLLOW_equivalence_in_bitwiseAnd2201);
            op1=equivalence();

            state._fsp--;

            expr = op1;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:897:6: ( '&' op2= equivalence )*
            loop46:
            do {
                int alt46=2;
                int LA46_0 = input.LA(1);

                if ( (LA46_0==BITWISE_AND) ) {
                    alt46=1;
                }


                switch (alt46) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:897:8: '&' op2= equivalence
            	    {
            	    match(input,BITWISE_AND,FOLLOW_BITWISE_AND_in_bitwiseAnd2213); 
            	    pushFollow(FOLLOW_equivalence_in_bitwiseAnd2217);
            	    op2=equivalence();

            	    state._fsp--;

            	    expr = new BitAndNode(expr, op2);

            	    }
            	    break;

            	default :
            	    break loop46;
                }
            } while (true);


            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return expr;
    }
    // $ANTLR end "bitwiseAnd"


    // $ANTLR start "bitwiseXor"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:901:1: bitwiseXor returns [ExpressionNode expr] : op1= bitwiseAnd ( '^' op2= bitwiseAnd )* ;
    public final ExpressionNode bitwiseXor() throws RecognitionException {
        ExpressionNode expr = null;

        ExpressionNode op1 = null;

        ExpressionNode op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:902:5: (op1= bitwiseAnd ( '^' op2= bitwiseAnd )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:902:9: op1= bitwiseAnd ( '^' op2= bitwiseAnd )*
            {
            pushFollow(FOLLOW_bitwiseAnd_in_bitwiseXor2256);
            op1=bitwiseAnd();

            state._fsp--;

            expr = op1;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:903:6: ( '^' op2= bitwiseAnd )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==BITWISE_XOR) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:903:8: '^' op2= bitwiseAnd
            	    {
            	    match(input,BITWISE_XOR,FOLLOW_BITWISE_XOR_in_bitwiseXor2267); 
            	    pushFollow(FOLLOW_bitwiseAnd_in_bitwiseXor2271);
            	    op2=bitwiseAnd();

            	    state._fsp--;

            	    expr = new BitXorNode(expr, op2);

            	    }
            	    break;

            	default :
            	    break loop47;
                }
            } while (true);


            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return expr;
    }
    // $ANTLR end "bitwiseXor"


    // $ANTLR start "bitwiseOr"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:907:1: bitwiseOr returns [ExpressionNode expr] : op1= bitwiseXor ( '|' op2= bitwiseXor )* ;
    public final ExpressionNode bitwiseOr() throws RecognitionException {
        ExpressionNode expr = null;

        ExpressionNode op1 = null;

        ExpressionNode op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:908:5: (op1= bitwiseXor ( '|' op2= bitwiseXor )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:908:9: op1= bitwiseXor ( '|' op2= bitwiseXor )*
            {
            pushFollow(FOLLOW_bitwiseXor_in_bitwiseOr2310);
            op1=bitwiseXor();

            state._fsp--;

            expr = op1;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:909:6: ( '|' op2= bitwiseXor )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( (LA48_0==BITWISE_OR) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:909:8: '|' op2= bitwiseXor
            	    {
            	    match(input,BITWISE_OR,FOLLOW_BITWISE_OR_in_bitwiseOr2321); 
            	    pushFollow(FOLLOW_bitwiseXor_in_bitwiseOr2325);
            	    op2=bitwiseXor();

            	    state._fsp--;

            	    expr = new BitOrNode(expr, op2);

            	    }
            	    break;

            	default :
            	    break loop48;
                }
            } while (true);


            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return expr;
    }
    // $ANTLR end "bitwiseOr"


    // $ANTLR start "and"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:913:1: and returns [ExpressionNode expr] : op1= bitwiseOr ( '&&' op2= bitwiseOr )* ;
    public final ExpressionNode and() throws RecognitionException {
        ExpressionNode expr = null;

        ExpressionNode op1 = null;

        ExpressionNode op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:914:5: (op1= bitwiseOr ( '&&' op2= bitwiseOr )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:914:9: op1= bitwiseOr ( '&&' op2= bitwiseOr )*
            {
            pushFollow(FOLLOW_bitwiseOr_in_and2364);
            op1=bitwiseOr();

            state._fsp--;

            expr = op1;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:915:6: ( '&&' op2= bitwiseOr )*
            loop49:
            do {
                int alt49=2;
                int LA49_0 = input.LA(1);

                if ( (LA49_0==AND) ) {
                    alt49=1;
                }


                switch (alt49) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:915:8: '&&' op2= bitwiseOr
            	    {
            	    match(input,AND,FOLLOW_AND_in_and2375); 
            	    pushFollow(FOLLOW_bitwiseOr_in_and2379);
            	    op2=bitwiseOr();

            	    state._fsp--;

            	    expr = new AndNode(expr, op2);

            	    }
            	    break;

            	default :
            	    break loop49;
                }
            } while (true);


            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return expr;
    }
    // $ANTLR end "and"


    // $ANTLR start "or"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:919:1: or returns [ExpressionNode expr] : op1= and ( '||' op2= and )* ;
    public final ExpressionNode or() throws RecognitionException {
        ExpressionNode expr = null;

        ExpressionNode op1 = null;

        ExpressionNode op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:920:5: (op1= and ( '||' op2= and )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:920:9: op1= and ( '||' op2= and )*
            {
            pushFollow(FOLLOW_and_in_or2418);
            op1=and();

            state._fsp--;

            expr = op1;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:921:6: ( '||' op2= and )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==OR) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:921:8: '||' op2= and
            	    {
            	    match(input,OR,FOLLOW_OR_in_or2429); 
            	    pushFollow(FOLLOW_and_in_or2433);
            	    op2=and();

            	    state._fsp--;

            	    expr = new OrNode(expr, op2);

            	    }
            	    break;

            	default :
            	    break loop50;
                }
            } while (true);


            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return expr;
    }
    // $ANTLR end "or"


    // $ANTLR start "implication"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:925:1: implication returns [ExpressionNode expr] : op1= or ( '->' op2= or )* ;
    public final ExpressionNode implication() throws RecognitionException {
        ExpressionNode expr = null;

        ExpressionNode op1 = null;

        ExpressionNode op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:926:5: (op1= or ( '->' op2= or )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:926:7: op1= or ( '->' op2= or )*
            {
            pushFollow(FOLLOW_or_in_implication2466);
            op1=or();

            state._fsp--;

            expr = op1;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:927:6: ( '->' op2= or )*
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==IMPLICATION) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:927:8: '->' op2= or
            	    {
            	    match(input,IMPLICATION,FOLLOW_IMPLICATION_in_implication2477); 
            	    pushFollow(FOLLOW_or_in_implication2481);
            	    op2=or();

            	    state._fsp--;

            	    expr = new ImplicationNode(expr, op2);

            	    }
            	    break;

            	default :
            	    break loop51;
                }
            } while (true);


            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return expr;
    }
    // $ANTLR end "implication"


    // $ANTLR start "expression"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:931:1: expression returns [ExpressionNode expr] : op1= implication ( '?' op2= expression ':' op3= expression )? ;
    public final ExpressionNode expression() throws RecognitionException {
        ExpressionNode expr = null;

        ExpressionNode op1 = null;

        ExpressionNode op2 = null;

        ExpressionNode op3 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:932:5: (op1= implication ( '?' op2= expression ':' op3= expression )? )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:932:9: op1= implication ( '?' op2= expression ':' op3= expression )?
            {
            pushFollow(FOLLOW_implication_in_expression2521);
            op1=implication();

            state._fsp--;

            expr = op1;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:933:6: ( '?' op2= expression ':' op3= expression )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==QMARK) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuGrammar.g:933:7: '?' op2= expression ':' op3= expression
                    {
                    match(input,QMARK,FOLLOW_QMARK_in_expression2531); 
                    pushFollow(FOLLOW_expression_in_expression2535);
                    op2=expression();

                    state._fsp--;

                    match(input,COLON,FOLLOW_COLON_in_expression2537); 
                    pushFollow(FOLLOW_expression_in_expression2541);
                    op3=expression();

                    state._fsp--;


                        			expr = new TernaryNode(expr, op2, op3);
                        		

                    }
                    break;

            }


            }

        }

            catch (RecognitionException e){
            	System.err.println(e.input.getSourceName() + ":");
            	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
            	System.err.println();
            	System.exit(1);
            }
        finally {
        }
        return expr;
    }
    // $ANTLR end "expression"

    // Delegated rules


 

    public static final BitSet FOLLOW_globalConstants_in_lpn85 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_globalVariables_in_lpn88 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_module_in_lpn111 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_EOF_in_lpn139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_main158 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_MODULE_in_main160 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_NAME_in_main162 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_EQUALS_in_main164 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_QUOTE_in_main166 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_main168 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_QUOTE_in_main170 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_GREATER_in_main172 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_instantiation_in_main181 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LESS_in_main184 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_DIV_in_main186 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_MODULE_in_main188 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_GREATER_in_main190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_module216 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_MODULE_in_module218 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_NAME_in_module220 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_EQUALS_in_module222 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_QUOTE_in_module224 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_module226 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_QUOTE_in_module242 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_GREATER_in_module244 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_constants_in_module246 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_variables_in_module249 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_instantiation_in_module251 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_logic_in_module254 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LESS_in_module257 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_DIV_in_module259 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_MODULE_in_module261 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_GREATER_in_module263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_constants290 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_constants292 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_GREATER_in_constants294 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_ID_in_constants299 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_EQUALS_in_constants301 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INT_in_constants305 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_SEMICOLON_in_constants316 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_LESS_in_constants320 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_DIV_in_constants322 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_constants324 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_GREATER_in_constants326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_globalConstants343 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_globalConstants345 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_GREATER_in_globalConstants347 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_ID_in_globalConstants352 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_EQUALS_in_globalConstants354 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INT_in_globalConstants358 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_SEMICOLON_in_globalConstants384 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_LESS_in_globalConstants388 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_DIV_in_globalConstants390 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_globalConstants392 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_GREATER_in_globalConstants394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_globalVariables409 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_INTERNAL_in_globalVariables411 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_GREATER_in_globalVariables413 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_ID_in_globalVariables418 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_EQUALS_in_globalVariables428 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_INT_in_globalVariables433 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_globalVariables447 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_SEMICOLON_in_globalVariables457 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_LESS_in_globalVariables461 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_DIV_in_globalVariables463 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_INTERNAL_in_globalVariables465 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_GREATER_in_globalVariables467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_variables483 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_INTERNAL_in_variables485 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_GREATER_in_variables487 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_ID_in_variables493 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_EQUALS_in_variables503 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_INT_in_variables508 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_variables522 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_SEMICOLON_in_variables532 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_ID_in_variables547 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_variables557 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ID_in_variables562 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_INT_in_variables576 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_variables587 = new BitSet(new long[]{0x0800000200000000L});
    public static final BitSet FOLLOW_EQUALS_in_variables592 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_LPAREN_in_variables603 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INT_in_variables608 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_variables610 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INT_in_variables624 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RPAREN_in_variables634 = new BitSet(new long[]{0x0000000000001040L});
    public static final BitSet FOLLOW_SEMICOLON_in_variables641 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_LESS_in_variables655 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_DIV_in_variables657 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_INTERNAL_in_variables659 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_GREATER_in_variables661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_instantiation690 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_instantiation692 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_GREATER_in_instantiation694 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_instantiation703 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_instantiation707 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_LPAREN_in_instantiation709 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_instantiation713 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_PERIOD_in_instantiation715 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_instantiation719 = new BitSet(new long[]{0x0000000000000090L});
    public static final BitSet FOLLOW_RPAREN_in_instantiation723 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LESS_in_instantiation730 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_DIV_in_instantiation732 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_instantiation734 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_GREATER_in_instantiation736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_marking_in_logic766 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_transition_in_logic769 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_LESS_in_marking822 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_MARKING_in_marking824 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_GREATER_in_marking826 = new BitSet(new long[]{0x0000000800000030L});
    public static final BitSet FOLLOW_INT_in_marking832 = new BitSet(new long[]{0x0000000800008000L});
    public static final BitSet FOLLOW_ID_in_marking858 = new BitSet(new long[]{0x0000000800008000L});
    public static final BitSet FOLLOW_COMMA_in_marking882 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_INT_in_marking887 = new BitSet(new long[]{0x0000000800008000L});
    public static final BitSet FOLLOW_ID_in_marking913 = new BitSet(new long[]{0x0000000800008000L});
    public static final BitSet FOLLOW_LESS_in_marking940 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_DIV_in_marking942 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_MARKING_in_marking944 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_GREATER_in_marking946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_transition980 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_TRANSITION_in_transition982 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_LABEL_in_transition984 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_EQUALS_in_transition986 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_QUOTE_in_transition988 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_set_in_transition992 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_QUOTE_in_transition998 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_PRESET_in_transition1000 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_EQUALS_in_transition1002 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_QUOTE_in_transition1005 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_QUOTE_in_transition1007 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_QUOTE_in_transition1012 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_INT_in_transition1017 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_ID_in_transition1037 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_COMMA_in_transition1053 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INT_in_transition1057 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_COMMA_in_transition1075 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_transition1079 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_QUOTE_in_transition1094 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_POSTSET_in_transition1098 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_EQUALS_in_transition1100 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_QUOTE_in_transition1104 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_QUOTE_in_transition1106 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_QUOTE_in_transition1111 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_INT_in_transition1117 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_ID_in_transition1137 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_COMMA_in_transition1162 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INT_in_transition1166 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_COMMA_in_transition1186 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_transition1189 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_QUOTE_in_transition1208 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_GREATER_in_transition1213 = new BitSet(new long[]{0x4000110830000370L});
    public static final BitSet FOLLOW_guard_in_transition1216 = new BitSet(new long[]{0x4000000800000050L});
    public static final BitSet FOLLOW_delay_in_transition1236 = new BitSet(new long[]{0x4000000800000010L});
    public static final BitSet FOLLOW_assertion_in_transition1257 = new BitSet(new long[]{0x4000000800000010L});
    public static final BitSet FOLLOW_assignment_in_transition1277 = new BitSet(new long[]{0x4000000800000010L});
    public static final BitSet FOLLOW_LESS_in_transition1296 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_DIV_in_transition1298 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_TRANSITION_in_transition1300 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_GREATER_in_transition1302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_assertion1336 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_LPAREN_in_assertion1338 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_expression_in_assertion1340 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RPAREN_in_assertion1342 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_SEMICOLON_in_assertion1344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_guard1370 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_SEMICOLON_in_guard1372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_delay1406 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INT_in_delay1410 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_delay1424 = new BitSet(new long[]{0x8000000000000020L});
    public static final BitSet FOLLOW_INT_in_delay1429 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_63_in_delay1444 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RPAREN_in_delay1457 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_SEMICOLON_in_delay1459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_assignment1485 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_EQUALS_in_assignment1487 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_expression_in_assignment1503 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_SEMICOLON_in_assignment1505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_assignment1527 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_assignment1544 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_set_in_assignment1546 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_assignment1554 = new BitSet(new long[]{0x0800000200000000L});
    public static final BitSet FOLLOW_EQUALS_in_assignment1558 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_expression_in_assignment1560 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_SEMICOLON_in_assignment1562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_term1605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_term1623 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_expression_in_term1625 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RPAREN_in_term1627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_term1639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_term1651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_term1663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_unary1700 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_MINUS_in_unary1705 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_term_in_unary1712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BITWISE_NEGATION_in_bitwiseNegation1743 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_unary_in_bitwiseNegation1749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEGATION_in_negation1775 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_bitwiseNegation_in_negation1781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_negation_in_mult1809 = new BitSet(new long[]{0x00000001C0000002L});
    public static final BitSet FOLLOW_TIMES_in_mult1821 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_negation_in_mult1825 = new BitSet(new long[]{0x00000001C0000002L});
    public static final BitSet FOLLOW_DIV_in_mult1836 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_negation_in_mult1840 = new BitSet(new long[]{0x00000001C0000002L});
    public static final BitSet FOLLOW_MOD_in_mult1851 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_negation_in_mult1855 = new BitSet(new long[]{0x00000001C0000002L});
    public static final BitSet FOLLOW_mult_in_add1894 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_PLUS_in_add1905 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_mult_in_add1909 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_MINUS_in_add1921 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_mult_in_add1925 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_add_in_shift1964 = new BitSet(new long[]{0x0003000000000002L});
    public static final BitSet FOLLOW_BITWISE_LSHIFT_in_shift1975 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_add_in_shift1979 = new BitSet(new long[]{0x0003000000000002L});
    public static final BitSet FOLLOW_BITWISE_RSHIFT_in_shift1991 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_add_in_shift1995 = new BitSet(new long[]{0x0003000000000002L});
    public static final BitSet FOLLOW_shift_in_relation2030 = new BitSet(new long[]{0x0000003C00000002L});
    public static final BitSet FOLLOW_LESS_in_relation2041 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_shift_in_relation2045 = new BitSet(new long[]{0x0000003C00000002L});
    public static final BitSet FOLLOW_LESS_EQUAL_in_relation2057 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_shift_in_relation2061 = new BitSet(new long[]{0x0000003C00000002L});
    public static final BitSet FOLLOW_GREATER_EQUAL_in_relation2073 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_shift_in_relation2077 = new BitSet(new long[]{0x0000003C00000002L});
    public static final BitSet FOLLOW_GREATER_in_relation2089 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_shift_in_relation2093 = new BitSet(new long[]{0x0000003C00000002L});
    public static final BitSet FOLLOW_relation_in_equivalence2132 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_EQUIV_in_equivalence2143 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_relation_in_equivalence2147 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_NOT_EQUIV_in_equivalence2158 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_relation_in_equivalence2162 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_equivalence_in_bitwiseAnd2201 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_BITWISE_AND_in_bitwiseAnd2213 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_equivalence_in_bitwiseAnd2217 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_bitwiseAnd_in_bitwiseXor2256 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_BITWISE_XOR_in_bitwiseXor2267 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_bitwiseAnd_in_bitwiseXor2271 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_bitwiseXor_in_bitwiseOr2310 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_BITWISE_OR_in_bitwiseOr2321 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_bitwiseXor_in_bitwiseOr2325 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_bitwiseOr_in_and2364 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_AND_in_and2375 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_bitwiseOr_in_and2379 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_and_in_or2418 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_OR_in_or2429 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_and_in_or2433 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_or_in_implication2466 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_IMPLICATION_in_implication2477 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_or_in_implication2481 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_implication_in_expression2521 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_QMARK_in_expression2531 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_expression_in_expression2535 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_expression2537 = new BitSet(new long[]{0x0000110030000370L});
    public static final BitSet FOLLOW_expression_in_expression2541 = new BitSet(new long[]{0x0000000000000002L});

}