// $ANTLR 3.3 Nov 30, 2010 12:50:56 C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g 2012-07-17 11:50:35

    package platu.lpn.io;
    
    import java.util.StringTokenizer;
    import java.io.File;
    import platu.lpn.io.Instance;
    import java.util.Map.Entry;
    import java.util.HashMap;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.LinkedList;
    import platu.lpn.LPN;
    import platu.lpn.VarSet;
    import platu.lpn.LpnTranList;
    import platu.lpn.LPNTran;
    import platu.common.DualHashMap;
    import platu.lpn.VarExpr;
    import platu.lpn.VarExprList;
    import platu.project.Project;
    import platu.expression.*;   


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class PlatuInstParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PATH", "ID", "INT", "MEMBER", "LABELSTRING", "LPAREN", "RPAREN", "TRUE", "FALSE", "LBRACK", "RBRACK", "QMARK", "COLON", "SEMICOLON", "PERIOD", "UNDERSCORE", "COMMA", "QUOTE", "MODULE", "MAIN", "NAME", "INPUT", "OUTPUT", "INTERNAL", "MARKING", "STATE_VECTOR", "TRANSITION", "LABEL", "PRESET", "POSTSET", "PLUS", "MINUS", "TIMES", "DIV", "MOD", "EQUALS", "GREATER", "LESS", "GREATER_EQUAL", "LESS_EQUAL", "EQUIV", "NOT_EQUIV", "NEGATION", "AND", "OR", "IMPLICATION", "BITWISE_NEGATION", "BITWISE_AND", "BITWISE_OR", "BITWISE_XOR", "BITWISE_LSHIFT", "BITWISE_RSHIFT", "LETTER", "DIGIT", "FILE", "WS", "COMMENT", "MULTILINECOMMENT", "XMLCOMMENT", "IGNORE", "'include'", "'/include'", "'/main'", "'class'", "'arg'", "'/class'", "'['", "']'", "'const'", "'/const'", "'/var'", "'m'", "'/m'", "'tr'", "'/tr'", "'assert'", "'guard'", "'delay'", "'inf'"
    };
    public static final int EOF=-1;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__73=73;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int PATH=4;
    public static final int ID=5;
    public static final int INT=6;
    public static final int MEMBER=7;
    public static final int LABELSTRING=8;
    public static final int LPAREN=9;
    public static final int RPAREN=10;
    public static final int TRUE=11;
    public static final int FALSE=12;
    public static final int LBRACK=13;
    public static final int RBRACK=14;
    public static final int QMARK=15;
    public static final int COLON=16;
    public static final int SEMICOLON=17;
    public static final int PERIOD=18;
    public static final int UNDERSCORE=19;
    public static final int COMMA=20;
    public static final int QUOTE=21;
    public static final int MODULE=22;
    public static final int MAIN=23;
    public static final int NAME=24;
    public static final int INPUT=25;
    public static final int OUTPUT=26;
    public static final int INTERNAL=27;
    public static final int MARKING=28;
    public static final int STATE_VECTOR=29;
    public static final int TRANSITION=30;
    public static final int LABEL=31;
    public static final int PRESET=32;
    public static final int POSTSET=33;
    public static final int PLUS=34;
    public static final int MINUS=35;
    public static final int TIMES=36;
    public static final int DIV=37;
    public static final int MOD=38;
    public static final int EQUALS=39;
    public static final int GREATER=40;
    public static final int LESS=41;
    public static final int GREATER_EQUAL=42;
    public static final int LESS_EQUAL=43;
    public static final int EQUIV=44;
    public static final int NOT_EQUIV=45;
    public static final int NEGATION=46;
    public static final int AND=47;
    public static final int OR=48;
    public static final int IMPLICATION=49;
    public static final int BITWISE_NEGATION=50;
    public static final int BITWISE_AND=51;
    public static final int BITWISE_OR=52;
    public static final int BITWISE_XOR=53;
    public static final int BITWISE_LSHIFT=54;
    public static final int BITWISE_RSHIFT=55;
    public static final int LETTER=56;
    public static final int DIGIT=57;
    public static final int FILE=58;
    public static final int WS=59;
    public static final int COMMENT=60;
    public static final int MULTILINECOMMENT=61;
    public static final int XMLCOMMENT=62;
    public static final int IGNORE=63;

    // delegates
    // delegators


        public PlatuInstParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public PlatuInstParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return PlatuInstParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g"; }


    	// static variables
        static private int INFINITY = Integer.MAX_VALUE;
        static private ExpressionNode ZERO = new ConstNode("FALSE", 0);  // constant false node
        static private ExpressionNode ONE = new ConstNode("TRUE", 1);  // constant true node
        static private Expression TrueExpr = new Expression(ONE); // constant true expression
        
        // static variables used to contain information for all the parsed module classes
        private boolean main = false;  // true if main module has been parsed
        public HashMap<String, LPN> LpnMap = new HashMap<String, LPN>();  // all modules parsed, keyed by module name
        public HashMap<String, Integer> GlobalVarHashMap = new HashMap<String, Integer>();  // global variables and associated values
        public HashMap<String, VarNode> GlobalVarNodeMap = new HashMap<String, VarNode>();
        public HashMap<String, Integer> GlobalConstHashMap = new HashMap<String, Integer>();  // global constants within a single lpn file
        public List<Instance> InstanceList = new ArrayList<Instance>();
        public HashSet<String> IncludeSet = new HashSet<String>();
        public HashMap<String, HashMap<String, List<String>>> ClassInputInitMap = new HashMap<String, HashMap<String, List<String>>>();
        public HashMap<String, Set<String>> ClassReadMap = new HashMap<String, Set<String>>();
        public HashMap<String, Set<String>> ClassWriteMap = new HashMap<String, Set<String>>();
        
        // non-static variables used during the parsing of each module class
        private HashMap<String, VarNode> VarNodeMap = null; // maps variable name to variable object
    	private DualHashMap<String, Integer> VarIndexMap = null;  // maps variables to an array index
        private HashMap<String, Integer> ConstHashMap = null;  // constants within a single module
        private HashMap<String, Integer> StatevectorMap = null;  // module variables mapped to initial values
        private List<String> ArgumentList = null; // list of class arguments
        private VarSet Inputs = null;  // module inputs
        private VarSet Internals = null; // module internal variables
        private int VariableIndex = 0;  // count of index assigned to module variables
        private int TransitionIndex = 0; // count of index assigned to lpn transitions
        private HashMap<String, List<String>> InputInitializationMap = null; // maps input variables to the internal they initialize
        private Set<String> ClassReadSet = null; // set of class variables that are read
        private Set<String> ClassWriteSet = null; // set of class variables that are modified
        private Set<String> TransitionLabelSet = null; // set of LPN transition labels
        
        // ************************** Methods **********************************
    	// outputs specified error message to the standard error stream and exits the program
    	private void error(String errorMessage){
    		System.err.println(errorMessage);
    		System.exit(1);
    	}
    	
    	// Creates the array node and element nodes for an input array variable
    	private void createInputArray(String var, List<Integer> dimensionList){
    		// calculate the number of elements in the array
    		int numElements = 1;
    		for(int i = 0; i < dimensionList.size(); i++){
    			numElements = numElements * dimensionList.get(i);
    		}
    		
    		// sequential list of element's associated VarNode object
    		List<VarNode> varList = new ArrayList<VarNode>();
    		
    		// list containing the element's indices, initialized to the first element
    		// used to name the element's VarNode object
    		ArrayList<Integer> indexList = new ArrayList<Integer>();
    		for(int i = 0; i < dimensionList.size(); i++){
    			indexList.add(0);
    		}
    		
    		// create element's associated VarNode
    		for(int i = 0; i < numElements; i++){
    			// build the element's VarNode name from the array name and indices
    			String name = var;
    			for(int j = 0; j < indexList.size(); j++){
    				name = name + "[" + indexList.get(j) + "]";
    			}
    			
    			// statevector index
    			int vectorIndex = VariableIndex++;
    			
    			// create element's VarNode and set type
    			VarNode element = new VarNode(name, vectorIndex);
    			element.setType(platu.lpn.VarType.INPUT);
    			
    			// add element to sequential list of array's elemntal VarNodes
    			varList.add(element);
    			
    			// add element's initial value to the statevector
    			StatevectorMap.put(name, 0);
    			
    			// add element's statevector index to the VarIndexMap
       			VarIndexMap.insert(name, vectorIndex);
       			
       			// add element's VarNode to the VarNodeMap
       			VarNodeMap.put(name, element);
    			
    			// increment the element indices for the next element
    			incrementElementIndex(indexList, dimensionList);
    		}

    		ArrayNode newArray = new ArrayNode(var, dimensionList.size(), dimensionList, varList);
    		newArray.setType(platu.lpn.VarType.INPUT);
    		VarNodeMap.put(var, newArray);
    		Inputs.add(var);
    		ArgumentList.add(var);
    	}
    	
    	// increments a list of array index values according to an array's dimensions
    	private void incrementElementIndex(List<Integer> indexList, List<Integer> dimensionList){
    		if(indexList.size() != dimensionList.size()){
    			// error
    			System.exit(1);
    		}
    		
    		for(int i = dimensionList.size()-1; i >= 0; i--){
    			int indexValue = indexList.get(i);
    			int dimValue = dimensionList.get(i);
    			
    			indexValue++;
    			if(indexValue >= dimValue){
    				indexValue = 0;
    				indexList.set(i, indexValue);
    			}
    			else{
    				indexList.set(i, indexValue);
    				break;
    			}
    		}
    	}



    // $ANTLR start "parseLpnFile"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:163:1: parseLpnFile[Project prj] : ( include )? ( globalConstants )? ( main[prj] )? ( moduleClass[prj] )* EOF ;
    public final void parseLpnFile(Project prj) throws RecognitionException {
        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:164:5: ( ( include )? ( globalConstants )? ( main[prj] )? ( moduleClass[prj] )* EOF )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:164:7: ( include )? ( globalConstants )? ( main[prj] )? ( moduleClass[prj] )* EOF
            {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:164:7: ( include )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==LESS) ) {
                int LA1_1 = input.LA(2);

                if ( (LA1_1==64) ) {
                    alt1=1;
                }
            }
            switch (alt1) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:164:7: include
                    {
                    pushFollow(FOLLOW_include_in_parseLpnFile52);
                    include();

                    state._fsp--;


                    }
                    break;

            }

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:164:16: ( globalConstants )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==LESS) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==72) ) {
                    alt2=1;
                }
            }
            switch (alt2) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:164:16: globalConstants
                    {
                    pushFollow(FOLLOW_globalConstants_in_parseLpnFile55);
                    globalConstants();

                    state._fsp--;


                    }
                    break;

            }

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:164:33: ( main[prj] )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==LESS) ) {
                int LA3_1 = input.LA(2);

                if ( (LA3_1==MAIN) ) {
                    alt3=1;
                }
            }
            switch (alt3) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:164:33: main[prj]
                    {
                    pushFollow(FOLLOW_main_in_parseLpnFile58);
                    main(prj);

                    state._fsp--;


                    }
                    break;

            }

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:164:44: ( moduleClass[prj] )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==LESS) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:164:45: moduleClass[prj]
            	    {
            	    pushFollow(FOLLOW_moduleClass_in_parseLpnFile63);
            	    moduleClass(prj);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            match(input,EOF,FOLLOW_EOF_in_parseLpnFile68); 

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
    // $ANTLR end "parseLpnFile"


    // $ANTLR start "include"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:167:1: include : '<' 'include' '>' ( PATH ';' )* '<' '/include' '>' ;
    public final void include() throws RecognitionException {
        Token PATH1=null;

        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:168:2: ( '<' 'include' '>' ( PATH ';' )* '<' '/include' '>' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:168:4: '<' 'include' '>' ( PATH ';' )* '<' '/include' '>'
            {
            match(input,LESS,FOLLOW_LESS_in_include86); 
            match(input,64,FOLLOW_64_in_include88); 
            match(input,GREATER,FOLLOW_GREATER_in_include90); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:168:22: ( PATH ';' )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==PATH) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:168:23: PATH ';'
            	    {
            	    PATH1=(Token)match(input,PATH,FOLLOW_PATH_in_include93); 
            	    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_include95); 

            	    				File f = new File((PATH1!=null?PATH1.getText():null));
            	            		IncludeSet.add(f.getAbsolutePath());
            	    			

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            match(input,LESS,FOLLOW_LESS_in_include107); 
            match(input,65,FOLLOW_65_in_include109); 
            match(input,GREATER,FOLLOW_GREATER_in_include111); 

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
    // $ANTLR end "include"


    // $ANTLR start "main"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:176:1: main[Project prj] : '<' 'main' '>' ( constants )? ( mainVariables )? instantiation '<' '/main' '>' ;
    public final void main(Project prj) throws RecognitionException {
        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:177:2: ( '<' 'main' '>' ( constants )? ( mainVariables )? instantiation '<' '/main' '>' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:177:4: '<' 'main' '>' ( constants )? ( mainVariables )? instantiation '<' '/main' '>'
            {
            match(input,LESS,FOLLOW_LESS_in_main124); 
            match(input,MAIN,FOLLOW_MAIN_in_main126); 
            match(input,GREATER,FOLLOW_GREATER_in_main128); 

            				if(main == true){
            					error("error: multiple main modules defined");
            				}
            				
            				main = true;
            				
            				// initialize hash map holding symolic constants
            				ConstHashMap = new HashMap<String, Integer>();
            				VarNodeMap = new HashMap<String, VarNode>();
            			
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:189:3: ( constants )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==LESS) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==72) ) {
                    alt6=1;
                }
            }
            switch (alt6) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:189:3: constants
                    {
                    pushFollow(FOLLOW_constants_in_main137);
                    constants();

                    state._fsp--;


                    }
                    break;

            }

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:189:14: ( mainVariables )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==LESS) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:189:14: mainVariables
                    {
                    pushFollow(FOLLOW_mainVariables_in_main140);
                    mainVariables();

                    state._fsp--;


                    }
                    break;

            }

            pushFollow(FOLLOW_instantiation_in_main144);
            instantiation();

            state._fsp--;

            match(input,LESS,FOLLOW_LESS_in_main146); 
            match(input,66,FOLLOW_66_in_main148); 
            match(input,GREATER,FOLLOW_GREATER_in_main150); 

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


    // $ANTLR start "moduleClass"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:192:1: moduleClass[Project prj] returns [LPN lpn] : ( '<' 'class' 'name' '=' '\"' modName= ID '\"' 'arg' '=' '\"' ( inputs )? '\"' '>' ( constants )? ( variables )? logic '<' '/class' '>' ) ;
    public final LPN moduleClass(Project prj) throws RecognitionException {
        LPN lpn = null;

        Token modName=null;
        PlatuInstParser.logic_return logic2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:193:5: ( ( '<' 'class' 'name' '=' '\"' modName= ID '\"' 'arg' '=' '\"' ( inputs )? '\"' '>' ( constants )? ( variables )? logic '<' '/class' '>' ) )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:193:7: ( '<' 'class' 'name' '=' '\"' modName= ID '\"' 'arg' '=' '\"' ( inputs )? '\"' '>' ( constants )? ( variables )? logic '<' '/class' '>' )
            {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:193:7: ( '<' 'class' 'name' '=' '\"' modName= ID '\"' 'arg' '=' '\"' ( inputs )? '\"' '>' ( constants )? ( variables )? logic '<' '/class' '>' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:193:9: '<' 'class' 'name' '=' '\"' modName= ID '\"' 'arg' '=' '\"' ( inputs )? '\"' '>' ( constants )? ( variables )? logic '<' '/class' '>'
            {
            match(input,LESS,FOLLOW_LESS_in_moduleClass171); 
            match(input,67,FOLLOW_67_in_moduleClass173); 
            match(input,NAME,FOLLOW_NAME_in_moduleClass175); 
            match(input,EQUALS,FOLLOW_EQUALS_in_moduleClass177); 
            match(input,QUOTE,FOLLOW_QUOTE_in_moduleClass179); 
            modName=(Token)match(input,ID,FOLLOW_ID_in_moduleClass183); 

                			// module names must be unique
                			if(LpnMap.containsKey((modName!=null?modName.getText():null))){
                				error("error on line " + modName.getLine() + ": module " + (modName!=null?modName.getText():null) + " already exists");
                			}
                			else if((modName!=null?modName.getText():null).equals("main")){
                				error("error on line " + modName.getLine() + ": main is reserved");
                			}
                			
                			// initialize non static variables for new module
                    	    VarIndexMap = new DualHashMap<String, Integer>();
            			    ConstHashMap = new HashMap<String, Integer>();
            			    VarNodeMap = new HashMap<String, VarNode>();
            			    Inputs = new VarSet();
            			    Internals = new VarSet();
            			    ArgumentList = new ArrayList<String>();
            			    StatevectorMap = new HashMap<String, Integer>();
            			    InputInitializationMap = new HashMap<String, List<String>>();
            			    ClassReadSet = new HashSet<String>();
            			    ClassWriteSet = new HashSet<String>();
            			    TransitionLabelSet = new HashSet<String>();
            			    VariableIndex = 0;
            			    TransitionIndex = 0;
                		
            match(input,QUOTE,FOLLOW_QUOTE_in_moduleClass198); 
            match(input,68,FOLLOW_68_in_moduleClass200); 
            match(input,EQUALS,FOLLOW_EQUALS_in_moduleClass202); 
            match(input,QUOTE,FOLLOW_QUOTE_in_moduleClass204); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:218:24: ( inputs )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==ID) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:218:24: inputs
                    {
                    pushFollow(FOLLOW_inputs_in_moduleClass206);
                    inputs();

                    state._fsp--;


                    }
                    break;

            }

            match(input,QUOTE,FOLLOW_QUOTE_in_moduleClass209); 
            match(input,GREATER,FOLLOW_GREATER_in_moduleClass211); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:218:40: ( constants )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==LESS) ) {
                int LA9_1 = input.LA(2);

                if ( (LA9_1==72) ) {
                    alt9=1;
                }
            }
            switch (alt9) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:218:40: constants
                    {
                    pushFollow(FOLLOW_constants_in_moduleClass213);
                    constants();

                    state._fsp--;


                    }
                    break;

            }

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:218:51: ( variables )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==LESS) ) {
                int LA10_1 = input.LA(2);

                if ( (LA10_1==INTERNAL) ) {
                    alt10=1;
                }
            }
            switch (alt10) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:218:51: variables
                    {
                    pushFollow(FOLLOW_variables_in_moduleClass216);
                    variables();

                    state._fsp--;


                    }
                    break;

            }

            pushFollow(FOLLOW_logic_in_moduleClass219);
            logic2=logic();

            state._fsp--;

            match(input,LESS,FOLLOW_LESS_in_moduleClass221); 
            match(input,69,FOLLOW_69_in_moduleClass223); 
            match(input,GREATER,FOLLOW_GREATER_in_moduleClass225); 

            				// create new LPN
            	            int[] initialMarking = new int[(logic2!=null?logic2.initMarking:null).size()];
            	            
            	            int i = 0;
            	            for(Integer mark : (logic2!=null?logic2.initMarking:null)){
            	            	initialMarking[i++] = mark;
            	            }
            	            
            				lpn = new LPN(prj, (modName!=null?modName.getText():null), Inputs, Internals, VarNodeMap, (logic2!=null?logic2.lpnTranSet:null), 
            	         			StatevectorMap, initialMarking);
            				
            	            lpn.setVarIndexMap(VarIndexMap);         
            	            (logic2!=null?logic2.lpnTranSet:null).setLPN(lpn);     

            	            LpnMap.put((modName!=null?modName.getText():null), lpn);
            	            lpn.setArgumentList(ArgumentList);
            	            
            	            ClassInputInitMap.put((modName!=null?modName.getText():null), InputInitializationMap);
            	            ClassReadMap.put((modName!=null?modName.getText():null), ClassReadSet);
            	            ClassWriteMap.put((modName!=null?modName.getText():null), ClassWriteSet);
            			

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
    // $ANTLR end "moduleClass"


    // $ANTLR start "inputs"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:245:1: inputs : ( (arrayArg2= ID ( '[' arrayVal= constIntValue ']' )+ ) | (arg2= ID ) ) ( ( ',' arrayArg= ID ( '[' arrayVal= constIntValue ']' )+ ) | ( ',' arg= ID ) )* ;
    public final void inputs() throws RecognitionException {
        Token arrayArg2=null;
        Token arg2=null;
        Token arrayArg=null;
        Token arg=null;
        int arrayVal = 0;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:246:2: ( ( (arrayArg2= ID ( '[' arrayVal= constIntValue ']' )+ ) | (arg2= ID ) ) ( ( ',' arrayArg= ID ( '[' arrayVal= constIntValue ']' )+ ) | ( ',' arg= ID ) )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:246:4: ( (arrayArg2= ID ( '[' arrayVal= constIntValue ']' )+ ) | (arg2= ID ) ) ( ( ',' arrayArg= ID ( '[' arrayVal= constIntValue ']' )+ ) | ( ',' arg= ID ) )*
            {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:246:4: ( (arrayArg2= ID ( '[' arrayVal= constIntValue ']' )+ ) | (arg2= ID ) )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==ID) ) {
                int LA12_1 = input.LA(2);

                if ( (LA12_1==70) ) {
                    alt12=1;
                }
                else if ( ((LA12_1>=COMMA && LA12_1<=QUOTE)) ) {
                    alt12=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:246:5: (arrayArg2= ID ( '[' arrayVal= constIntValue ']' )+ )
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:246:5: (arrayArg2= ID ( '[' arrayVal= constIntValue ']' )+ )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:246:6: arrayArg2= ID ( '[' arrayVal= constIntValue ']' )+
                    {
                    arrayArg2=(Token)match(input,ID,FOLLOW_ID_in_inputs257); 

                        			// check aginst globals
                    	   			if(GlobalConstHashMap.containsKey((arrayArg2!=null?arrayArg2.getText():null))){
                    	   				error("error on line " + arrayArg2.getLine() + ": variable " + (arrayArg2!=null?arrayArg2.getText():null) + " is already defined as a global constant");
                    	   			}
                    	   			
                    	   			List<Integer> dimensionList = new ArrayList<Integer>();
                        		
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:255:6: ( '[' arrayVal= constIntValue ']' )+
                    int cnt11=0;
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==70) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:255:7: '[' arrayVal= constIntValue ']'
                    	    {
                    	    match(input,70,FOLLOW_70_in_inputs274); 
                    	    pushFollow(FOLLOW_constIntValue_in_inputs278);
                    	    arrayVal=constIntValue();

                    	    state._fsp--;


                    	        			dimensionList.add(arrayVal);
                    	        		
                    	    match(input,71,FOLLOW_71_in_inputs294); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt11 >= 1 ) break loop11;
                                EarlyExitException eee =
                                    new EarlyExitException(11, input);
                                throw eee;
                        }
                        cnt11++;
                    } while (true);


                        			createInputArray((arrayArg2!=null?arrayArg2.getText():null), dimensionList);
                        		

                    }


                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:263:10: (arg2= ID )
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:263:10: (arg2= ID )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:263:11: arg2= ID
                    {
                    arg2=(Token)match(input,ID,FOLLOW_ID_in_inputs318); 

                        			// check against globals
                        			if(GlobalConstHashMap.containsKey((arg2!=null?arg2.getText():null))){
                        				error("error on line " + arg2.getLine() + ": variable " + (arg2!=null?arg2.getText():null) + " is already defined as a global constant");
                        			}
                        			
                        			// add variable and value to state vector
                    				StatevectorMap.put(arg2.getText(), 0);
                    				
                    				// generate variable index and create new var node  
                    				int index = VariableIndex++;
                       				VarIndexMap.insert(arg2.getText(), index);
                       				
                       				VarNode inputVarNode =  new VarNode(arg2.getText(), index);
                       				inputVarNode.setType(platu.lpn.VarType.INPUT);
                       				VarNodeMap.put(arg2.getText(), inputVarNode);
                        			
                        			ArgumentList.add(arg2.getText());
                    				Inputs.add(arg2.getText());
                        		

                    }


                    }
                    break;

            }

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:284:9: ( ( ',' arrayArg= ID ( '[' arrayVal= constIntValue ']' )+ ) | ( ',' arg= ID ) )*
            loop14:
            do {
                int alt14=3;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==COMMA) ) {
                    int LA14_2 = input.LA(2);

                    if ( (LA14_2==ID) ) {
                        int LA14_3 = input.LA(3);

                        if ( (LA14_3==70) ) {
                            alt14=1;
                        }
                        else if ( ((LA14_3>=COMMA && LA14_3<=QUOTE)) ) {
                            alt14=2;
                        }


                    }


                }


                switch (alt14) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:284:10: ( ',' arrayArg= ID ( '[' arrayVal= constIntValue ']' )+ )
            	    {
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:284:10: ( ',' arrayArg= ID ( '[' arrayVal= constIntValue ']' )+ )
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:284:11: ',' arrayArg= ID ( '[' arrayVal= constIntValue ']' )+
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_inputs338); 
            	    arrayArg=(Token)match(input,ID,FOLLOW_ID_in_inputs342); 

            	    	    		// check aginst globals and other inputs
            	    	   			if(GlobalConstHashMap.containsKey((arrayArg!=null?arrayArg.getText():null))){
            	    	   				error("error on line " + arrayArg.getLine() + ": variable " + (arrayArg!=null?arrayArg.getText():null) + " is already defined as a global constant");
            	    	   			}
            	    	   			else if(VarNodeMap.containsKey((arrayArg!=null?arrayArg.getText():null))){
            	    	   				error("error on line " + arrayArg.getLine() + ": variable " + (arrayArg!=null?arrayArg.getText():null) + " is already defined");
            	    	   			}
            	    	   			
            	    	   			List<Integer> dimensionList = new ArrayList<Integer>();
            	    	   		
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:296:6: ( '[' arrayVal= constIntValue ']' )+
            	    int cnt13=0;
            	    loop13:
            	    do {
            	        int alt13=2;
            	        int LA13_0 = input.LA(1);

            	        if ( (LA13_0==70) ) {
            	            alt13=1;
            	        }


            	        switch (alt13) {
            	    	case 1 :
            	    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:296:7: '[' arrayVal= constIntValue ']'
            	    	    {
            	    	    match(input,70,FOLLOW_70_in_inputs358); 
            	    	    pushFollow(FOLLOW_constIntValue_in_inputs362);
            	    	    arrayVal=constIntValue();

            	    	    state._fsp--;


            	    	        			dimensionList.add(arrayVal);
            	    	        		
            	    	    match(input,71,FOLLOW_71_in_inputs377); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt13 >= 1 ) break loop13;
            	                EarlyExitException eee =
            	                    new EarlyExitException(13, input);
            	                throw eee;
            	        }
            	        cnt13++;
            	    } while (true);


            	        			createInputArray((arrayArg!=null?arrayArg.getText():null), dimensionList);
            	        		

            	    }


            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:304:10: ( ',' arg= ID )
            	    {
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:304:10: ( ',' arg= ID )
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:304:11: ',' arg= ID
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_inputs400); 
            	    arg=(Token)match(input,ID,FOLLOW_ID_in_inputs404); 

            	        			// check aginst globals and other inputs
            	        			if(GlobalConstHashMap.containsKey((arg!=null?arg.getText():null))){
            	        				error("error on line " + arg.getLine() + ": variable " + (arg!=null?arg.getText():null) + " is already defined as a global constant");
            	        			}
            	        			else if(VarNodeMap.containsKey((arg!=null?arg.getText():null))){
            	        				error("error on line " + arg.getLine() + ": variable " + (arg!=null?arg.getText():null) + " is already defined");
            	        			}
            	        			
            	        			// add variable and value to state vector
            	    				StatevectorMap.put(arg.getText(), 0);
            	    				
            	    				// generate variable index and create new var node  
            	    				int index = VariableIndex++;
            	       				VarIndexMap.insert(arg.getText(), index);
            	       				
            	       				VarNode inputVarNode = new VarNode(arg.getText(), index);
            	       				inputVarNode.setType(platu.lpn.VarType.INPUT);
            	       				VarNodeMap.put(arg.getText(), inputVarNode);
            	        			ArgumentList.add(arg.getText());
            	    				Inputs.add(arg.getText());
            	        		

            	    }


            	    }
            	    break;

            	default :
            	    break loop14;
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
        return ;
    }
    // $ANTLR end "inputs"


    // $ANTLR start "constants"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:331:1: constants : '<' 'const' '>' (const1= ID '=' intValue ';' )* '<' '/const' '>' ;
    public final void constants() throws RecognitionException {
        Token const1=null;
        int intValue3 = 0;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:332:2: ( '<' 'const' '>' (const1= ID '=' intValue ';' )* '<' '/const' '>' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:332:4: '<' 'const' '>' (const1= ID '=' intValue ';' )* '<' '/const' '>'
            {
            match(input,LESS,FOLLOW_LESS_in_constants435); 
            match(input,72,FOLLOW_72_in_constants437); 
            match(input,GREATER,FOLLOW_GREATER_in_constants439); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:332:20: (const1= ID '=' intValue ';' )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==ID) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:332:21: const1= ID '=' intValue ';'
            	    {
            	    const1=(Token)match(input,ID,FOLLOW_ID_in_constants444); 
            	    match(input,EQUALS,FOLLOW_EQUALS_in_constants446); 
            	    pushFollow(FOLLOW_intValue_in_constants448);
            	    intValue3=intValue();

            	    state._fsp--;


            	    				// make sure constant is not defined as something else
            	    				if(VarNodeMap.containsKey((const1!=null?const1.getText():null))){
            	    					error("error on line " + const1.getLine() + ": " + (const1!=null?const1.getText():null) + " already exists as a variable"); 
            	    				}
            	    				else if(GlobalConstHashMap.containsKey((const1!=null?const1.getText():null))){
            	    				    error("error on line " + const1.getLine() + ": " + (const1!=null?const1.getText():null) + " already exists as a global constant");
            	    				}
            	    				
            	    				// put will override previous value
            	    				Integer result = ConstHashMap.put((const1!=null?const1.getText():null), intValue3);
            	    				
            	    				// make sure constant hasn't already been defined
            	    				if(result != null){
            	    					error("error on line " + const1.getLine() + ": " + (const1!=null?const1.getText():null) + " has already been defined");
            	    				}
            	    			
            	    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_constants458); 

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            match(input,LESS,FOLLOW_LESS_in_constants462); 
            match(input,73,FOLLOW_73_in_constants464); 
            match(input,GREATER,FOLLOW_GREATER_in_constants466); 

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
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:354:1: globalConstants : '<' 'const' '>' (const1= ID '=' intValue ';' )* '<' '/const' '>' ;
    public final void globalConstants() throws RecognitionException {
        Token const1=null;
        int intValue4 = 0;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:355:5: ( '<' 'const' '>' (const1= ID '=' intValue ';' )* '<' '/const' '>' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:355:9: '<' 'const' '>' (const1= ID '=' intValue ';' )* '<' '/const' '>'
            {
            match(input,LESS,FOLLOW_LESS_in_globalConstants484); 
            match(input,72,FOLLOW_72_in_globalConstants486); 
            match(input,GREATER,FOLLOW_GREATER_in_globalConstants488); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:355:25: (const1= ID '=' intValue ';' )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==ID) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:355:26: const1= ID '=' intValue ';'
            	    {
            	    const1=(Token)match(input,ID,FOLLOW_ID_in_globalConstants493); 
            	    match(input,EQUALS,FOLLOW_EQUALS_in_globalConstants495); 
            	    pushFollow(FOLLOW_intValue_in_globalConstants497);
            	    intValue4=intValue();

            	    state._fsp--;


            	             
            	                	// put will override previous value
            	                    Integer result = GlobalConstHashMap.put((const1!=null?const1.getText():null), intValue4);
            	                    
            	                    // make sure constant hasn't already been defined
            	                    if(result != null){
            	                        error("error on line " + const1.getLine() + ": " + (const1!=null?const1.getText():null) + " has already been defined");
            	                    }
            	                
            	    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_globalConstants523); 

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            match(input,LESS,FOLLOW_LESS_in_globalConstants527); 
            match(input,73,FOLLOW_73_in_globalConstants529); 
            match(input,GREATER,FOLLOW_GREATER_in_globalConstants531); 

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


    // $ANTLR start "variables"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:370:1: variables : '<' 'var' '>' ( varDecl | arrayDecl )* '<' '/var' '>' ;
    public final void variables() throws RecognitionException {
        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:371:2: ( '<' 'var' '>' ( varDecl | arrayDecl )* '<' '/var' '>' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:371:4: '<' 'var' '>' ( varDecl | arrayDecl )* '<' '/var' '>'
            {
            match(input,LESS,FOLLOW_LESS_in_variables547); 
            match(input,INTERNAL,FOLLOW_INTERNAL_in_variables549); 
            match(input,GREATER,FOLLOW_GREATER_in_variables551); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:371:18: ( varDecl | arrayDecl )*
            loop17:
            do {
                int alt17=3;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==ID) ) {
                    int LA17_2 = input.LA(2);

                    if ( (LA17_2==SEMICOLON||LA17_2==EQUALS) ) {
                        alt17=1;
                    }
                    else if ( (LA17_2==70) ) {
                        alt17=2;
                    }


                }


                switch (alt17) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:371:20: varDecl
            	    {
            	    pushFollow(FOLLOW_varDecl_in_variables555);
            	    varDecl();

            	    state._fsp--;


            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:371:30: arrayDecl
            	    {
            	    pushFollow(FOLLOW_arrayDecl_in_variables559);
            	    arrayDecl();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            match(input,LESS,FOLLOW_LESS_in_variables563); 
            match(input,74,FOLLOW_74_in_variables565); 
            match(input,GREATER,FOLLOW_GREATER_in_variables567); 

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


    // $ANTLR start "mainVariables"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:375:1: mainVariables : '<' 'var' '>' ( mainVarDecl | mainArrayDecl )* '<' '/var' '>' ;
    public final void mainVariables() throws RecognitionException {
        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:376:2: ( '<' 'var' '>' ( mainVarDecl | mainArrayDecl )* '<' '/var' '>' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:376:4: '<' 'var' '>' ( mainVarDecl | mainArrayDecl )* '<' '/var' '>'
            {
            match(input,LESS,FOLLOW_LESS_in_mainVariables579); 
            match(input,INTERNAL,FOLLOW_INTERNAL_in_mainVariables581); 
            match(input,GREATER,FOLLOW_GREATER_in_mainVariables583); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:376:18: ( mainVarDecl | mainArrayDecl )*
            loop18:
            do {
                int alt18=3;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==ID) ) {
                    int LA18_2 = input.LA(2);

                    if ( (LA18_2==SEMICOLON||LA18_2==EQUALS) ) {
                        alt18=1;
                    }
                    else if ( (LA18_2==70) ) {
                        alt18=2;
                    }


                }


                switch (alt18) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:376:20: mainVarDecl
            	    {
            	    pushFollow(FOLLOW_mainVarDecl_in_mainVariables587);
            	    mainVarDecl();

            	    state._fsp--;


            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:376:34: mainArrayDecl
            	    {
            	    pushFollow(FOLLOW_mainArrayDecl_in_mainVariables591);
            	    mainArrayDecl();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

            match(input,LESS,FOLLOW_LESS_in_mainVariables595); 
            match(input,74,FOLLOW_74_in_mainVariables597); 
            match(input,GREATER,FOLLOW_GREATER_in_mainVariables599); 

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
    // $ANTLR end "mainVariables"


    // $ANTLR start "varDecl"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:380:1: varDecl : var= ID ( '=' val= assignmentIntValue[$var.text] )? ';' ;
    public final void varDecl() throws RecognitionException {
        Token var=null;
        PlatuInstParser.assignmentIntValue_return val = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:381:2: (var= ID ( '=' val= assignmentIntValue[$var.text] )? ';' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:381:4: var= ID ( '=' val= assignmentIntValue[$var.text] )? ';'
            {
            var=(Token)match(input,ID,FOLLOW_ID_in_varDecl613); 

            				// check variable is unique in scope
            				if(GlobalConstHashMap.containsKey((var!=null?var.getText():null))){
            					error("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " is a global constant"); 
            				}
            				else if(ConstHashMap.containsKey((var!=null?var.getText():null))){
            					error("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " is a constant"); 
            				}
            				else if(VarNodeMap.containsKey((var!=null?var.getText():null))){
            					error("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " has already been defined");
            				}
            				
            				// add variable and value to state vector
            				StatevectorMap.put((var!=null?var.getText():null), 0);
            				
            				// generate variable index and create new var node  
            				int index = VariableIndex++;
               				VarIndexMap.insert((var!=null?var.getText():null), index);
               				
               				VarNode internalVar = new VarNode((var!=null?var.getText():null), index);
               				internalVar.setType(platu.lpn.VarType.INTERNAL);
               				VarNodeMap.put((var!=null?var.getText():null), internalVar);
            				Internals.add((var!=null?var.getText():null));
            			
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:406:3: ( '=' val= assignmentIntValue[$var.text] )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==EQUALS) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:406:4: '=' val= assignmentIntValue[$var.text]
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_varDecl624); 
                    pushFollow(FOLLOW_assignmentIntValue_in_varDecl628);
                    val=assignmentIntValue((var!=null?var.getText():null));

                    state._fsp--;


                    				// add variable and value to state vector
                    				StatevectorMap.put((var!=null?var.getText():null), (val!=null?val.value:0));
                    				
                    				if((val!=null?val.atomic:false))
                    					internalVar.setAtomic();
                    			

                    }
                    break;

            }

            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_varDecl641); 

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
    // $ANTLR end "varDecl"


    // $ANTLR start "mainVarDecl"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:418:1: mainVarDecl : var= ID ( '=' mainIntValue )? ';' ;
    public final void mainVarDecl() throws RecognitionException {
        Token var=null;
        int mainIntValue5 = 0;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:419:2: (var= ID ( '=' mainIntValue )? ';' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:419:4: var= ID ( '=' mainIntValue )? ';'
            {
            var=(Token)match(input,ID,FOLLOW_ID_in_mainVarDecl656); 

            				// check variable is unique in scope
            				if(GlobalConstHashMap.containsKey((var!=null?var.getText():null))){
            					error("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " is a global constant"); 
            				}
            				else if(ConstHashMap.containsKey((var!=null?var.getText():null))){
            					error("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " is a constant");
            				}
            				else if(GlobalVarHashMap.containsKey((var!=null?var.getText():null))){
            					error("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " is a global variable"); 
            				}
            				else if(GlobalVarNodeMap.containsKey((var!=null?var.getText():null))){
            					error("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " is a global array"); 
            				}
            				
            				// add variable and value to global hash map
            				GlobalVarHashMap.put((var!=null?var.getText():null), 0);
            			
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:438:3: ( '=' mainIntValue )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==EQUALS) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:438:4: '=' mainIntValue
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_mainVarDecl667); 
                    pushFollow(FOLLOW_mainIntValue_in_mainVarDecl669);
                    mainIntValue5=mainIntValue();

                    state._fsp--;


                    				// replace initial value
                    				GlobalVarHashMap.put((var!=null?var.getText():null), mainIntValue5);
                    			

                    }
                    break;

            }

            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_mainVarDecl681); 

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
    // $ANTLR end "mainVarDecl"


    // $ANTLR start "arrayDecl"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:447:1: arrayDecl : var= ID ( '[' (arrayExpr= expression ) ']' )+ ( '=' ( '{' ( ( arrayInit1[startIndex, lastIndex] ) | (nest1= arrayInit[2, dimensionList, startIndex, lastIndex] ( ',' nest2= arrayInit[2, dimensionList, currentIndex, lastIndex] )* ) ) '}' ) | (var2= ID ) )? ';' ;
    public final void arrayDecl() throws RecognitionException {
        Token var=null;
        Token var2=null;
        PlatuInstParser.expression_return arrayExpr = null;

        int nest1 = 0;

        int nest2 = 0;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:448:2: (var= ID ( '[' (arrayExpr= expression ) ']' )+ ( '=' ( '{' ( ( arrayInit1[startIndex, lastIndex] ) | (nest1= arrayInit[2, dimensionList, startIndex, lastIndex] ( ',' nest2= arrayInit[2, dimensionList, currentIndex, lastIndex] )* ) ) '}' ) | (var2= ID ) )? ';' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:448:4: var= ID ( '[' (arrayExpr= expression ) ']' )+ ( '=' ( '{' ( ( arrayInit1[startIndex, lastIndex] ) | (nest1= arrayInit[2, dimensionList, startIndex, lastIndex] ( ',' nest2= arrayInit[2, dimensionList, currentIndex, lastIndex] )* ) ) '}' ) | (var2= ID ) )? ';'
            {
            var=(Token)match(input,ID,FOLLOW_ID_in_arrayDecl696); 

            				// check variable is unique in scope
            				if(GlobalConstHashMap.containsKey((var!=null?var.getText():null))){
            					error("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " is a global constant"); 
            				}
            				else if(ConstHashMap.containsKey((var!=null?var.getText():null))){
            					error("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " is a constant");
            				}
            				else if(VarNodeMap.containsKey((var!=null?var.getText():null))){
            					error("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " has already been defined");
            				}
            				
            				List<Integer> dimensionList = new ArrayList<Integer>();
            				int startIndex = VariableIndex;
            				int numElements = 1;
            				int currentIndex = 0;
            				int lastIndex = 0;
            				boolean atomic = true;
            			
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:468:3: ( '[' (arrayExpr= expression ) ']' )+
            int cnt21=0;
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==70) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:468:4: '[' (arrayExpr= expression ) ']'
            	    {
            	    match(input,70,FOLLOW_70_in_arrayDecl707); 
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:468:8: (arrayExpr= expression )
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:468:9: arrayExpr= expression
            	    {
            	    pushFollow(FOLLOW_expression_in_arrayDecl712);
            	    arrayExpr=expression();

            	    state._fsp--;


            	    				dimensionList.add((arrayExpr!=null?arrayExpr.value:0));
            	    			

            	    }

            	    match(input,71,FOLLOW_71_in_arrayDecl723); 

            	    }
            	    break;

            	default :
            	    if ( cnt21 >= 1 ) break loop21;
                        EarlyExitException eee =
                            new EarlyExitException(21, input);
                        throw eee;
                }
                cnt21++;
            } while (true);


            				// calculate the number of elements in the array
            				numElements = 1;
            				for(int i = 0; i < dimensionList.size(); i++){
            					numElements = numElements * dimensionList.get(i);
            				}
            				
            				// sequential list of element's associated VarNode object
            				List<VarNode> varList = new ArrayList<VarNode>();
            				
            				// list containing the element's indices, initialized to the first element
            				// used to name the element's VarNode object
            				ArrayList<Integer> indexList = new ArrayList<Integer>();
            				for(int i = 0; i < dimensionList.size(); i++){
            					indexList.add(0);
            				}
            				
            				// create element's associated VarNode
            				for(int i = 0; i < numElements; i++){
            					// build the element's VarNode name from the array name and indices
            					String name = (var!=null?var.getText():null);
            					for(int j = 0; j < indexList.size(); j++){
            						name = name + "[" + indexList.get(j) + "]";
            					}
            					
            					// statevector index
            					int vectorIndex = VariableIndex++;
            					
            					// create element's VarNode and set type
            					VarNode element = new VarNode(name, vectorIndex);
            					element.setType(platu.lpn.VarType.INTERNAL);
            					
            					// add element to sequential list of array's elemntal VarNodes
            					varList.add(element);
            					
            					// add element's initial value to the statevector
            					StatevectorMap.put(name, 0);
            					
            					// add element's statevector index to the VarIndexMap
            		   			VarIndexMap.insert(name, vectorIndex);
            		   			
            		   			// add element's VarNode to the VarNodeMap
            		   			VarNodeMap.put(name, element);
            					
            					// increment the element indices for the next element
            					incrementElementIndex(indexList, dimensionList);
            				}
            				
            				lastIndex = VariableIndex - 1;
            			
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:523:3: ( '=' ( '{' ( ( arrayInit1[startIndex, lastIndex] ) | (nest1= arrayInit[2, dimensionList, startIndex, lastIndex] ( ',' nest2= arrayInit[2, dimensionList, currentIndex, lastIndex] )* ) ) '}' ) | (var2= ID ) )?
            int alt24=3;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==EQUALS) ) {
                alt24=1;
            }
            else if ( (LA24_0==ID) ) {
                alt24=2;
            }
            switch (alt24) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:523:4: '=' ( '{' ( ( arrayInit1[startIndex, lastIndex] ) | (nest1= arrayInit[2, dimensionList, startIndex, lastIndex] ( ',' nest2= arrayInit[2, dimensionList, currentIndex, lastIndex] )* ) ) '}' )
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_arrayDecl735); 
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:523:8: ( '{' ( ( arrayInit1[startIndex, lastIndex] ) | (nest1= arrayInit[2, dimensionList, startIndex, lastIndex] ( ',' nest2= arrayInit[2, dimensionList, currentIndex, lastIndex] )* ) ) '}' )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:523:9: '{' ( ( arrayInit1[startIndex, lastIndex] ) | (nest1= arrayInit[2, dimensionList, startIndex, lastIndex] ( ',' nest2= arrayInit[2, dimensionList, currentIndex, lastIndex] )* ) ) '}'
                    {
                    match(input,LBRACK,FOLLOW_LBRACK_in_arrayDecl738); 
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:523:13: ( ( arrayInit1[startIndex, lastIndex] ) | (nest1= arrayInit[2, dimensionList, startIndex, lastIndex] ( ',' nest2= arrayInit[2, dimensionList, currentIndex, lastIndex] )* ) )
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( ((LA23_0>=ID && LA23_0<=INT)||LA23_0==MINUS) ) {
                        alt23=1;
                    }
                    else if ( (LA23_0==LBRACK) ) {
                        alt23=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 23, 0, input);

                        throw nvae;
                    }
                    switch (alt23) {
                        case 1 :
                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:523:15: ( arrayInit1[startIndex, lastIndex] )
                            {
                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:523:15: ( arrayInit1[startIndex, lastIndex] )
                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:523:16: arrayInit1[startIndex, lastIndex]
                            {
                            pushFollow(FOLLOW_arrayInit1_in_arrayDecl743);
                            arrayInit1(startIndex, lastIndex);

                            state._fsp--;


                            }


                            }
                            break;
                        case 2 :
                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:523:53: (nest1= arrayInit[2, dimensionList, startIndex, lastIndex] ( ',' nest2= arrayInit[2, dimensionList, currentIndex, lastIndex] )* )
                            {
                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:523:53: (nest1= arrayInit[2, dimensionList, startIndex, lastIndex] ( ',' nest2= arrayInit[2, dimensionList, currentIndex, lastIndex] )* )
                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:523:54: nest1= arrayInit[2, dimensionList, startIndex, lastIndex] ( ',' nest2= arrayInit[2, dimensionList, currentIndex, lastIndex] )*
                            {
                            pushFollow(FOLLOW_arrayInit_in_arrayDecl752);
                            nest1=arrayInit(2, dimensionList, startIndex, lastIndex);

                            state._fsp--;


                            				int count = 1;
                            				currentIndex = nest1;
                            			
                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:528:3: ( ',' nest2= arrayInit[2, dimensionList, currentIndex, lastIndex] )*
                            loop22:
                            do {
                                int alt22=2;
                                int LA22_0 = input.LA(1);

                                if ( (LA22_0==COMMA) ) {
                                    alt22=1;
                                }


                                switch (alt22) {
                            	case 1 :
                            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:528:4: ',' nest2= arrayInit[2, dimensionList, currentIndex, lastIndex]
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_arrayDecl764); 
                            	    pushFollow(FOLLOW_arrayInit_in_arrayDecl768);
                            	    nest2=arrayInit(2, dimensionList, currentIndex, lastIndex);

                            	    state._fsp--;


                            	    				count++;
                            	    				currentIndex = nest2;
                            	    			

                            	    }
                            	    break;

                            	default :
                            	    break loop22;
                                }
                            } while (true);


                            				// check dimensions
                            				if(count != dimensionList.get(0)){
                            					error("error on line ??: invalid array initialization");
                            				}
                            				else if(currentIndex != lastIndex+1){
                            					error("error on line ??: invalid array initialization");
                            				}
                            			

                            }


                            }
                            break;

                    }

                    match(input,RBRACK,FOLLOW_RBRACK_in_arrayDecl792); 

                    }


                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:543:13: (var2= ID )
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:543:13: (var2= ID )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:543:14: var2= ID
                    {
                    var2=(Token)match(input,ID,FOLLOW_ID_in_arrayDecl800); 

                    				if(!VarNodeMap.containsKey((var2!=null?var2.getText():null))){
                    					error("error on line " + var2.getLine() + ": " + (var2!=null?var2.getText():null) + " is not an array");
                    				}
                    				
                    				ArrayNode node2 = (ArrayNode) VarNodeMap.get((var2!=null?var2.getText():null));
                    				
                    				// make sure the number of dimesnsions match
                    				List<Integer> dimList2 = node2.getDimensionList();
                    				if(dimList2.size() != dimensionList.size()){
                    					error("error on line " + var2.getLine() + ": incompatible array assignment");
                    				}
                    				
                    				// make sure that each dimension is the same size
                    				for(int i = 0; i < dimList2.size(); i++){
                    					if(dimList2.get(i) != dimensionList.get(i)){
                    						error("error on line " + var2.getLine() + ": incompatible array assignment");
                    					}
                    				}
                    				
                    				boolean isInput = false;
                    				if(Inputs.contains((var2!=null?var2.getText():null))){
                    					isInput = true;
                    				}
                    				
                    				List<VarNode> elementList2 = node2.getElementList();
                    				for(int i = 0; i < numElements; i++){
                    					String element2 = elementList2.get(i).getName();
                    					String element1 = varList.get(i).getName();
                    					
                    					int val = StatevectorMap.get(element2);
                    					StatevectorMap.put(element1, val);
                    					
                    					if(isInput){
                    						List<String> assignmentList = InputInitializationMap.get(element2);
                    						if(assignmentList == null){
                    							assignmentList = new LinkedList<String>();
                    							InputInitializationMap.put(element2, assignmentList);
                    						}
                    						
                    						assignmentList.add(element1);
                    					}
                    				}
                    				
                    				atomic = false;
                    			

                    }


                    }
                    break;

            }


            				// create new array node
            				ArrayNode newArray = new ArrayNode((var!=null?var.getText():null), dimensionList.size(), dimensionList, varList);
            				newArray.setType(platu.lpn.VarType.INTERNAL);
            				VarNodeMap.put((var!=null?var.getText():null), newArray);
            				Internals.add((var!=null?var.getText():null));
            				
            				if(atomic){
            					newArray.setAtomic();
            					
            					for(VarNode element : varList){
            						element.setAtomic();
            					}
            				}
            			
            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_arrayDecl823); 

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
    // $ANTLR end "arrayDecl"


    // $ANTLR start "mainArrayDecl"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:610:1: mainArrayDecl : var= ID ( '[' (dimVal= mainIntValue ) ']' )+ ( '=' '{' ( mainArrayInit1[startIndex, lastIndex, varList] | (nest1= mainArrayInit[2, dimensionList, startIndex, lastIndex, varList] ( ',' nest2= mainArrayInit[2, dimensionList, currentIndex, lastIndex, varList] )* ) ) '}' )? ';' ;
    public final void mainArrayDecl() throws RecognitionException {
        Token var=null;
        int dimVal = 0;

        int nest1 = 0;

        int nest2 = 0;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:611:2: (var= ID ( '[' (dimVal= mainIntValue ) ']' )+ ( '=' '{' ( mainArrayInit1[startIndex, lastIndex, varList] | (nest1= mainArrayInit[2, dimensionList, startIndex, lastIndex, varList] ( ',' nest2= mainArrayInit[2, dimensionList, currentIndex, lastIndex, varList] )* ) ) '}' )? ';' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:611:4: var= ID ( '[' (dimVal= mainIntValue ) ']' )+ ( '=' '{' ( mainArrayInit1[startIndex, lastIndex, varList] | (nest1= mainArrayInit[2, dimensionList, startIndex, lastIndex, varList] ( ',' nest2= mainArrayInit[2, dimensionList, currentIndex, lastIndex, varList] )* ) ) '}' )? ';'
            {
            var=(Token)match(input,ID,FOLLOW_ID_in_mainArrayDecl838); 

            				// check variable is unique in scope
            				if(GlobalConstHashMap.containsKey((var!=null?var.getText():null))){
            					error("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " is a global constant"); 
            				}
            				else if(ConstHashMap.containsKey((var!=null?var.getText():null))){
            					error("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " is a constant"); 
            				}
            				else if(GlobalVarHashMap.containsKey((var!=null?var.getText():null))){
            					error("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " is a global variable"); 
            				}
            				else if(GlobalVarNodeMap.containsKey((var!=null?var.getText():null))){
            					error("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " is a global array"); 
            				}
            				
            				List<Integer> dimensionList = new ArrayList<Integer>();
            				int startIndex = 0;
            				int numElements = 1;
            				int currentIndex = 0;
            				int lastIndex = 0;
            			
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:633:3: ( '[' (dimVal= mainIntValue ) ']' )+
            int cnt25=0;
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==70) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:633:4: '[' (dimVal= mainIntValue ) ']'
            	    {
            	    match(input,70,FOLLOW_70_in_mainArrayDecl849); 
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:633:8: (dimVal= mainIntValue )
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:633:10: dimVal= mainIntValue
            	    {
            	    pushFollow(FOLLOW_mainIntValue_in_mainArrayDecl855);
            	    dimVal=mainIntValue();

            	    state._fsp--;


            	    				dimensionList.add(dimVal);
            	    			

            	    }

            	    match(input,71,FOLLOW_71_in_mainArrayDecl866); 

            	    }
            	    break;

            	default :
            	    if ( cnt25 >= 1 ) break loop25;
                        EarlyExitException eee =
                            new EarlyExitException(25, input);
                        throw eee;
                }
                cnt25++;
            } while (true);


            				// calculate the number of elements in the array
            				numElements = 1;
            				for(int i = 0; i < dimensionList.size(); i++){
            					numElements = numElements * dimensionList.get(i);
            				}
            				
            				// sequential list of element's associated VarNode object
            				List<VarNode> varList = new ArrayList<VarNode>();
            				
            				// list containing the element's indices, initialized to the first element
            				// used to name the element's VarNode object
            				ArrayList<Integer> indexList = new ArrayList<Integer>();
            				for(int i = 0; i < dimensionList.size(); i++){
            					indexList.add(0);
            				}
            				
            				// create element's associated VarNode
            				for(int i = 0; i < numElements; i++){
            					// build the element's VarNode name from the array name and indices
            					String name = (var!=null?var.getText():null);
            					for(int j = 0; j < indexList.size(); j++){
            						name = name + "[" + indexList.get(j) + "]";
            					}
            					
            					// statevector index
            					int vectorIndex = VariableIndex++;
            					
            					// create element's VarNode and set type
            					VarNode element = new VarNode(name, vectorIndex);
            					element.setType(platu.lpn.VarType.GLOBAL);
            					
            					// add element to sequential list of array's elemntal VarNodes
            					varList.add(element);
            					
            					// add element's initial value to the GlobalVarHashMap
            					GlobalVarHashMap.put(name, 0);
            					
            					// increment the element indices for the next element
            					incrementElementIndex(indexList, dimensionList);
            				}
            				
            				lastIndex = startIndex + numElements - 1;
            			
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:682:3: ( '=' '{' ( mainArrayInit1[startIndex, lastIndex, varList] | (nest1= mainArrayInit[2, dimensionList, startIndex, lastIndex, varList] ( ',' nest2= mainArrayInit[2, dimensionList, currentIndex, lastIndex, varList] )* ) ) '}' )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==EQUALS) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:682:4: '=' '{' ( mainArrayInit1[startIndex, lastIndex, varList] | (nest1= mainArrayInit[2, dimensionList, startIndex, lastIndex, varList] ( ',' nest2= mainArrayInit[2, dimensionList, currentIndex, lastIndex, varList] )* ) ) '}'
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_mainArrayDecl878); 
                    match(input,LBRACK,FOLLOW_LBRACK_in_mainArrayDecl880); 
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:682:12: ( mainArrayInit1[startIndex, lastIndex, varList] | (nest1= mainArrayInit[2, dimensionList, startIndex, lastIndex, varList] ( ',' nest2= mainArrayInit[2, dimensionList, currentIndex, lastIndex, varList] )* ) )
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( ((LA27_0>=ID && LA27_0<=INT)||LA27_0==MINUS) ) {
                        alt27=1;
                    }
                    else if ( (LA27_0==LBRACK) ) {
                        alt27=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 27, 0, input);

                        throw nvae;
                    }
                    switch (alt27) {
                        case 1 :
                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:682:13: mainArrayInit1[startIndex, lastIndex, varList]
                            {
                            pushFollow(FOLLOW_mainArrayInit1_in_mainArrayDecl883);
                            mainArrayInit1(startIndex, lastIndex, varList);

                            state._fsp--;


                            }
                            break;
                        case 2 :
                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:682:62: (nest1= mainArrayInit[2, dimensionList, startIndex, lastIndex, varList] ( ',' nest2= mainArrayInit[2, dimensionList, currentIndex, lastIndex, varList] )* )
                            {
                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:682:62: (nest1= mainArrayInit[2, dimensionList, startIndex, lastIndex, varList] ( ',' nest2= mainArrayInit[2, dimensionList, currentIndex, lastIndex, varList] )* )
                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:682:63: nest1= mainArrayInit[2, dimensionList, startIndex, lastIndex, varList] ( ',' nest2= mainArrayInit[2, dimensionList, currentIndex, lastIndex, varList] )*
                            {
                            pushFollow(FOLLOW_mainArrayInit_in_mainArrayDecl891);
                            nest1=mainArrayInit(2, dimensionList, startIndex, lastIndex, varList);

                            state._fsp--;


                            				int count = 1;
                            				currentIndex = nest1;
                            			
                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:687:3: ( ',' nest2= mainArrayInit[2, dimensionList, currentIndex, lastIndex, varList] )*
                            loop26:
                            do {
                                int alt26=2;
                                int LA26_0 = input.LA(1);

                                if ( (LA26_0==COMMA) ) {
                                    alt26=1;
                                }


                                switch (alt26) {
                            	case 1 :
                            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:687:4: ',' nest2= mainArrayInit[2, dimensionList, currentIndex, lastIndex, varList]
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_mainArrayDecl903); 
                            	    pushFollow(FOLLOW_mainArrayInit_in_mainArrayDecl907);
                            	    nest2=mainArrayInit(2, dimensionList, currentIndex, lastIndex, varList);

                            	    state._fsp--;


                            	    				count++;
                            	    				currentIndex = nest2;
                            	    			

                            	    }
                            	    break;

                            	default :
                            	    break loop26;
                                }
                            } while (true);


                            				// check dimension size
                            				// make sure the number of dimensions match
                            				if(count != dimensionList.get(0)){
                            					error("error on line ??: invalid array initialization");
                            				}
                            				else if(currentIndex != lastIndex+1){
                            					error("error on line ??: invalid array initialization");
                            				}
                            			

                            }


                            }
                            break;

                    }

                    match(input,RBRACK,FOLLOW_RBRACK_in_mainArrayDecl931); 

                    }
                    break;

            }


            				// create new array node
            				ArrayNode newArray = new ArrayNode((var!=null?var.getText():null), dimensionList.size(), dimensionList, varList);
            				newArray.setType(platu.lpn.VarType.GLOBAL);
            				GlobalVarNodeMap.put((var!=null?var.getText():null), newArray);
            				GlobalVarHashMap.put((var!=null?var.getText():null), null);
            			
            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_mainArrayDecl944); 

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
    // $ANTLR end "mainArrayDecl"


    // $ANTLR start "arrayInit"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:717:1: arrayInit[int dimension, List<Integer> dimensionList, int index, int lastIndex] returns [int indexValue] : ( ( '{' val1= assignmentIntValue[varName] ( ',' val2= assignmentIntValue[varName2] )* '}' ) | ( '{' nest1= arrayInit[$dimension+1, $dimensionList, $index, $lastIndex] ( ',' nest2= arrayInit[$dimension+1, $dimensionList, $indexValue, $lastIndex] )* '}' ) );
    public final int arrayInit(int dimension, List<Integer> dimensionList, int index, int lastIndex) throws RecognitionException {
        int indexValue = 0;

        PlatuInstParser.assignmentIntValue_return val1 = null;

        PlatuInstParser.assignmentIntValue_return val2 = null;

        int nest1 = 0;

        int nest2 = 0;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:718:2: ( ( '{' val1= assignmentIntValue[varName] ( ',' val2= assignmentIntValue[varName2] )* '}' ) | ( '{' nest1= arrayInit[$dimension+1, $dimensionList, $index, $lastIndex] ( ',' nest2= arrayInit[$dimension+1, $dimensionList, $indexValue, $lastIndex] )* '}' ) )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==LBRACK) ) {
                int LA31_1 = input.LA(2);

                if ( ((LA31_1>=ID && LA31_1<=INT)||LA31_1==MINUS) ) {
                    alt31=1;
                }
                else if ( (LA31_1==LBRACK) ) {
                    alt31=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 31, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:718:4: ( '{' val1= assignmentIntValue[varName] ( ',' val2= assignmentIntValue[varName2] )* '}' )
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:718:4: ( '{' val1= assignmentIntValue[varName] ( ',' val2= assignmentIntValue[varName2] )* '}' )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:718:5: '{' val1= assignmentIntValue[varName] ( ',' val2= assignmentIntValue[varName2] )* '}'
                    {
                    match(input,LBRACK,FOLLOW_LBRACK_in_arrayInit964); 
                    				
                    				int count = 1;
                    				String varName = VarIndexMap.getKey(index);
                    			
                    pushFollow(FOLLOW_assignmentIntValue_in_arrayInit977);
                    val1=assignmentIntValue(varName);

                    state._fsp--;


                    				// override default value
                    				StatevectorMap.put(varName, (val1!=null?val1.value:0));
                    				
                    				index++;
                    				indexValue = index;
                    			
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:731:3: ( ',' val2= assignmentIntValue[varName2] )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==COMMA) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:731:4: ',' val2= assignmentIntValue[varName2]
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_arrayInit988); 

                    	    				if(index > lastIndex){
                    	    					error("error on line ??: value out of bounds");
                    	    				}
                    	    				
                    	    				String varName2 = VarIndexMap.getKey(index);				
                    	    			
                    	    pushFollow(FOLLOW_assignmentIntValue_in_arrayInit1001);
                    	    val2=assignmentIntValue(varName2);

                    	    state._fsp--;


                    	    				// override default value
                    	    				StatevectorMap.put(varName2, (val2!=null?val2.value:0));
                    	    				
                    	    				index++;
                    	    				indexValue = index;
                    	    				count++;
                    	    			

                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);

                    match(input,RBRACK,FOLLOW_RBRACK_in_arrayInit1014); 

                    				if(count != dimensionList.get(dimension-1)){
                    					error("error on line ??: invalid array initialization");
                    				}
                    				else if(dimension > dimensionList.size()){
                    					error("error on line ??: invalid array initialization");
                    				}
                    			

                    }


                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:759:3: ( '{' nest1= arrayInit[$dimension+1, $dimensionList, $index, $lastIndex] ( ',' nest2= arrayInit[$dimension+1, $dimensionList, $indexValue, $lastIndex] )* '}' )
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:759:3: ( '{' nest1= arrayInit[$dimension+1, $dimensionList, $index, $lastIndex] ( ',' nest2= arrayInit[$dimension+1, $dimensionList, $indexValue, $lastIndex] )* '}' )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:759:5: '{' nest1= arrayInit[$dimension+1, $dimensionList, $index, $lastIndex] ( ',' nest2= arrayInit[$dimension+1, $dimensionList, $indexValue, $lastIndex] )* '}'
                    {
                    match(input,LBRACK,FOLLOW_LBRACK_in_arrayInit1033); 
                    pushFollow(FOLLOW_arrayInit_in_arrayInit1037);
                    nest1=arrayInit(dimension+1, dimensionList, index, lastIndex);

                    state._fsp--;


                    				indexValue = nest1;
                    				int numLists = 1;
                    				int count = 1;
                    			
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:765:3: ( ',' nest2= arrayInit[$dimension+1, $dimensionList, $indexValue, $lastIndex] )*
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==COMMA) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:765:4: ',' nest2= arrayInit[$dimension+1, $dimensionList, $indexValue, $lastIndex]
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_arrayInit1048); 
                    	    pushFollow(FOLLOW_arrayInit_in_arrayInit1052);
                    	    nest2=arrayInit(dimension+1, dimensionList, indexValue, lastIndex);

                    	    state._fsp--;


                    	    				indexValue = nest2;
                    	    				numLists++;
                    	    				count++;
                    	    			

                    	    }
                    	    break;

                    	default :
                    	    break loop30;
                        }
                    } while (true);

                    match(input,RBRACK,FOLLOW_RBRACK_in_arrayInit1064); 

                    				// make sure the number of lists maches the # of index value at current dimension
                    				if(count != dimensionList.get(dimension-1)){
                    					error("error on line ??: invalid array initialization");
                    				}
                    				else if(dimension > dimensionList.size()){
                    					error("error on line ??: invalid array initialization");
                    				}
                    			

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
        return indexValue;
    }
    // $ANTLR end "arrayInit"


    // $ANTLR start "arrayInit1"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:787:1: arrayInit1[int startIndex, int lastIndex] : val1= assignmentIntValue[varName] ( ',' val2= assignmentIntValue[varName2] )* ;
    public final void arrayInit1(int startIndex, int lastIndex) throws RecognitionException {
        PlatuInstParser.assignmentIntValue_return val1 = null;

        PlatuInstParser.assignmentIntValue_return val2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:788:2: (val1= assignmentIntValue[varName] ( ',' val2= assignmentIntValue[varName2] )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:788:5: val1= assignmentIntValue[varName] ( ',' val2= assignmentIntValue[varName2] )*
            {

            				int index = startIndex;
            				boolean singleValue = true;
            				String varName = VarIndexMap.getKey(index);
            			
            pushFollow(FOLLOW_assignmentIntValue_in_arrayInit11095);
            val1=assignmentIntValue(varName);

            state._fsp--;


            				// override default value
            				StatevectorMap.put(varName, (val1!=null?val1.value:0));
            				
            				index++;
            			
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:800:3: ( ',' val2= assignmentIntValue[varName2] )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==COMMA) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:800:4: ',' val2= assignmentIntValue[varName2]
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_arrayInit11107); 

            	    				singleValue = false;
            	    				if(index > lastIndex){
            	    					error("error on line ??: value out of bounds");
            	    				}
            	    				
            	    				String varName2 = VarIndexMap.getKey(index);
            	    			
            	    pushFollow(FOLLOW_assignmentIntValue_in_arrayInit11119);
            	    val2=assignmentIntValue(varName2);

            	    state._fsp--;


            	    				// override default value
            	    				StatevectorMap.put(varName2, (val2!=null?val2.value:0));
            	    				
            	    				index++;
            	    			

            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);


            				if(singleValue){
            					// assign value to all elements from nextIndex to nextIndex+numElements-1
            					int elementValue = (val1!=null?val1.value:0);
            					for(int i = index; i <= lastIndex; i++){
            						String name = VarIndexMap.getKey(i);
            						StatevectorMap.put(name, elementValue);
            					}
            				}
            				else{
            					// make sure the number of values matches the dimension size
            					if(index != lastIndex+1){
            						error("error on line ??: invalid array initialization");
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
        return ;
    }
    // $ANTLR end "arrayInit1"


    // $ANTLR start "mainArrayInit"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:838:1: mainArrayInit[int dimension, List<Integer> dimensionList, int index, int lastIndex, List<VarNode> varList] returns [int indexValue] : ( ( '{' val1= mainIntValue ( ',' val2= mainIntValue )* '}' ) | ( '{' nest1= mainArrayInit[$dimension+1, $dimensionList, $index, $lastIndex, $varList] ( ',' nest2= mainArrayInit[$dimension+1, $dimensionList, $indexValue, $lastIndex, $varList] )* '}' ) );
    public final int mainArrayInit(int dimension, List<Integer> dimensionList, int index, int lastIndex, List<VarNode> varList) throws RecognitionException {
        int indexValue = 0;

        int val1 = 0;

        int val2 = 0;

        int nest1 = 0;

        int nest2 = 0;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:839:2: ( ( '{' val1= mainIntValue ( ',' val2= mainIntValue )* '}' ) | ( '{' nest1= mainArrayInit[$dimension+1, $dimensionList, $index, $lastIndex, $varList] ( ',' nest2= mainArrayInit[$dimension+1, $dimensionList, $indexValue, $lastIndex, $varList] )* '}' ) )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==LBRACK) ) {
                int LA35_1 = input.LA(2);

                if ( ((LA35_1>=ID && LA35_1<=INT)||LA35_1==MINUS) ) {
                    alt35=1;
                }
                else if ( (LA35_1==LBRACK) ) {
                    alt35=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 35, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:839:4: ( '{' val1= mainIntValue ( ',' val2= mainIntValue )* '}' )
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:839:4: ( '{' val1= mainIntValue ( ',' val2= mainIntValue )* '}' )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:839:5: '{' val1= mainIntValue ( ',' val2= mainIntValue )* '}'
                    {
                    match(input,LBRACK,FOLLOW_LBRACK_in_mainArrayInit1156); 
                    pushFollow(FOLLOW_mainIntValue_in_mainArrayInit1160);
                    val1=mainIntValue();

                    state._fsp--;

                    				
                    				int count = 1;
                    				int val = val1;
                    				
                    				// override default value
                    				String varName = varList.get(index).getName();
                    				GlobalVarHashMap.put(varName, val);
                    				
                    				index++;
                    				indexValue = index;
                    			
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:851:3: ( ',' val2= mainIntValue )*
                    loop33:
                    do {
                        int alt33=2;
                        int LA33_0 = input.LA(1);

                        if ( (LA33_0==COMMA) ) {
                            alt33=1;
                        }


                        switch (alt33) {
                    	case 1 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:851:4: ',' val2= mainIntValue
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_mainArrayInit1171); 
                    	    pushFollow(FOLLOW_mainIntValue_in_mainArrayInit1175);
                    	    val2=mainIntValue();

                    	    state._fsp--;


                    	    				if(index > lastIndex){
                    	    					error("error on line ??: value out of bounds");
                    	    				}
                    	    				
                    	    				val = val2;
                    	    				
                    	    				// override default value
                    	    				String varName2 = varList.get(index).getName();				
                    	    				GlobalVarHashMap.put(varName2, val);
                    	    				
                    	    				index++;
                    	    				indexValue = index;
                    	    				count++;
                    	    			

                    	    }
                    	    break;

                    	default :
                    	    break loop33;
                        }
                    } while (true);

                    match(input,RBRACK,FOLLOW_RBRACK_in_mainArrayInit1188); 

                    				// make sure the number of values matches the size of the dimension
                    				// make sure the number of dimensions match
                    				if(count != dimensionList.get(dimension-1)){
                    					error("error on line ??: invalid array initialization");
                    				}
                    				else if(dimension > dimensionList.size()){
                    					error("error on line ??: invalid array initialization");
                    				}
                    			

                    }


                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:880:3: ( '{' nest1= mainArrayInit[$dimension+1, $dimensionList, $index, $lastIndex, $varList] ( ',' nest2= mainArrayInit[$dimension+1, $dimensionList, $indexValue, $lastIndex, $varList] )* '}' )
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:880:3: ( '{' nest1= mainArrayInit[$dimension+1, $dimensionList, $index, $lastIndex, $varList] ( ',' nest2= mainArrayInit[$dimension+1, $dimensionList, $indexValue, $lastIndex, $varList] )* '}' )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:880:5: '{' nest1= mainArrayInit[$dimension+1, $dimensionList, $index, $lastIndex, $varList] ( ',' nest2= mainArrayInit[$dimension+1, $dimensionList, $indexValue, $lastIndex, $varList] )* '}'
                    {
                    match(input,LBRACK,FOLLOW_LBRACK_in_mainArrayInit1207); 
                    pushFollow(FOLLOW_mainArrayInit_in_mainArrayInit1211);
                    nest1=mainArrayInit(dimension+1, dimensionList, index, lastIndex, varList);

                    state._fsp--;


                    				indexValue = nest1;
                    				int numLists = 1;
                    				int count = 1;
                    			
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:886:3: ( ',' nest2= mainArrayInit[$dimension+1, $dimensionList, $indexValue, $lastIndex, $varList] )*
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==COMMA) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:886:4: ',' nest2= mainArrayInit[$dimension+1, $dimensionList, $indexValue, $lastIndex, $varList]
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_mainArrayInit1222); 
                    	    pushFollow(FOLLOW_mainArrayInit_in_mainArrayInit1226);
                    	    nest2=mainArrayInit(dimension+1, dimensionList, indexValue, lastIndex, varList);

                    	    state._fsp--;


                    	    				indexValue = nest2;
                    	    				numLists++;
                    	    				count++;
                    	    			

                    	    }
                    	    break;

                    	default :
                    	    break loop34;
                        }
                    } while (true);

                    match(input,RBRACK,FOLLOW_RBRACK_in_mainArrayInit1238); 

                    				// make sure the number of lists maches the # of index value at current dimension
                    				// make sure the number of dimensions match
                    				if(count != dimensionList.get(dimension-1)){
                    					error("error on line ??: invalid array initialization");
                    				}
                    				else if(dimension > dimensionList.size()){
                    					error("error on line ??: invalid array initialization");
                    				}
                    			

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
        return indexValue;
    }
    // $ANTLR end "mainArrayInit"


    // $ANTLR start "mainArrayInit1"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:909:1: mainArrayInit1[int startIndex, int lastIndex, List<VarNode> varList] : val1= mainIntValue ( ',' val2= mainIntValue )* ;
    public final void mainArrayInit1(int startIndex, int lastIndex, List<VarNode> varList) throws RecognitionException {
        int val1 = 0;

        int val2 = 0;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:910:2: (val1= mainIntValue ( ',' val2= mainIntValue )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:910:4: val1= mainIntValue ( ',' val2= mainIntValue )*
            {
            pushFollow(FOLLOW_mainIntValue_in_mainArrayInit11264);
            val1=mainIntValue();

            state._fsp--;


            				int index = startIndex;
            				boolean singleValue = true;
            				int val = val1;
            				
            				// override default value
            				String varName = varList.get(index).getName();
            				GlobalVarHashMap.put(varName, val);
            				
            				index++;
            			
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:922:3: ( ',' val2= mainIntValue )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==COMMA) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:922:4: ',' val2= mainIntValue
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_mainArrayInit11275); 
            	    pushFollow(FOLLOW_mainIntValue_in_mainArrayInit11279);
            	    val2=mainIntValue();

            	    state._fsp--;


            	    				singleValue = false;
            	    				if(index > lastIndex){
            	    					error("error on line ??: value out of bounds");
            	    				}
            	    				
            	    				val = val2;
            	    				
            	    				// override default value
            	    				String varName2 = varList.get(index).getName();
            	    				GlobalVarHashMap.put(varName2, val);
            	    				
            	    				index++;
            	    			

            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);


            				if(singleValue){
            					// assign value to all elements from nextIndex to nextIndex+numElements-1
            					int elementValue = val1;
            					for(int i = index; i <= lastIndex; i++){
            						String name = varList.get(i).getName();
            						GlobalVarHashMap.put(name, elementValue);
            					}
            				}
            				else{
            					// make sure the number of values matches the dimension size
            					if(index != lastIndex+1){
            						error("error on line ??: invalid array initialization");
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
        return ;
    }
    // $ANTLR end "mainArrayInit1"


    // $ANTLR start "intValue"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:957:1: intValue returns [int value] : ( ( '-' )* intVal= INT | var= ID | (arrayVar= ID ( '[' expression ']' )+ ) );
    public final int intValue() throws RecognitionException {
        int value = 0;

        Token intVal=null;
        Token var=null;
        Token arrayVar=null;
        PlatuInstParser.expression_return expression6 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:958:2: ( ( '-' )* intVal= INT | var= ID | (arrayVar= ID ( '[' expression ']' )+ ) )
            int alt39=3;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==INT||LA39_0==MINUS) ) {
                alt39=1;
            }
            else if ( (LA39_0==ID) ) {
                int LA39_2 = input.LA(2);

                if ( (LA39_2==RPAREN||LA39_2==SEMICOLON||LA39_2==COMMA||LA39_2==LESS) ) {
                    alt39=2;
                }
                else if ( (LA39_2==70) ) {
                    alt39=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 39, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }
            switch (alt39) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:958:5: ( '-' )* intVal= INT
                    {
                    boolean negative = false;
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:959:3: ( '-' )*
                    loop37:
                    do {
                        int alt37=2;
                        int LA37_0 = input.LA(1);

                        if ( (LA37_0==MINUS) ) {
                            alt37=1;
                        }


                        switch (alt37) {
                    	case 1 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:959:4: '-'
                    	    {
                    	    match(input,MINUS,FOLLOW_MINUS_in_intValue1318); 
                    	    negative = !negative;

                    	    }
                    	    break;

                    	default :
                    	    break loop37;
                        }
                    } while (true);

                    intVal=(Token)match(input,INT,FOLLOW_INT_in_intValue1326); 

                    				value = Integer.parseInt((intVal!=null?intVal.getText():null));
                    				if(negative)
                    					value = -value;
                    			

                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:965:5: var= ID
                    {
                    var=(Token)match(input,ID,FOLLOW_ID_in_intValue1340); 

                    				if(GlobalConstHashMap.containsKey((var!=null?var.getText():null))){
                    					value = GlobalConstHashMap.get((var!=null?var.getText():null));
                    				}
                    				else if(ConstHashMap.containsKey((var!=null?var.getText():null))){
                    					value = ConstHashMap.get((var!=null?var.getText():null));
                    				}
                    				else if(StatevectorMap.containsKey((var!=null?var.getText():null))){
                    					value = StatevectorMap.get((var!=null?var.getText():null));
                    					ClassReadSet.add((var!=null?var.getText():null));
                    				}
                    				else if(VarNodeMap.containsKey((var!=null?var.getText():null))){
                    					error("error on line " + var.getLine() + ": incorrect usage of array variable " + (var!=null?var.getText():null));
                    				}
                    				else{
                    					error("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " is not defined"); 
                    				}
                    			

                    }
                    break;
                case 3 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:984:5: (arrayVar= ID ( '[' expression ']' )+ )
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:984:5: (arrayVar= ID ( '[' expression ']' )+ )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:984:6: arrayVar= ID ( '[' expression ']' )+
                    {
                    arrayVar=(Token)match(input,ID,FOLLOW_ID_in_intValue1354); 

                    				List<Integer> indexList = new ArrayList<Integer>();
                    			
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:988:3: ( '[' expression ']' )+
                    int cnt38=0;
                    loop38:
                    do {
                        int alt38=2;
                        int LA38_0 = input.LA(1);

                        if ( (LA38_0==70) ) {
                            alt38=1;
                        }


                        switch (alt38) {
                    	case 1 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:988:4: '[' expression ']'
                    	    {
                    	    match(input,70,FOLLOW_70_in_intValue1365); 
                    	    pushFollow(FOLLOW_expression_in_intValue1367);
                    	    expression6=expression();

                    	    state._fsp--;


                    	    				indexList.add((expression6!=null?expression6.value:0));
                    	    			
                    	    match(input,71,FOLLOW_71_in_intValue1377); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt38 >= 1 ) break loop38;
                                EarlyExitException eee =
                                    new EarlyExitException(38, input);
                                throw eee;
                        }
                        cnt38++;
                    } while (true);


                    				// check for valid ID (make sure its an array variable)
                    				if(GlobalConstHashMap.containsKey((arrayVar!=null?arrayVar.getText():null))){
                    					System.err.println("error on line " + arrayVar.getLine() + ": " + (arrayVar!=null?arrayVar.getText():null) + " is not an array"); 
                        				System.exit(1);
                    				}
                    				else if(ConstHashMap.containsKey((arrayVar!=null?arrayVar.getText():null))){
                    					System.err.println("error on line " + arrayVar.getLine() + ": " + (arrayVar!=null?arrayVar.getText():null) + " is not an array"); 
                        				System.exit(1);
                    				}
                    				else if(VarNodeMap.containsKey((arrayVar!=null?arrayVar.getText():null))){
                    					VarNode node = VarNodeMap.get((arrayVar!=null?arrayVar.getText():null));
                    					if(!ArrayNode.class.isAssignableFrom(node.getClass())){
                    						error("error on line " + arrayVar.getLine() + ": " + (arrayVar!=null?arrayVar.getText():null) + " is not an array"); 
                    					}
                    					
                    					ArrayNode array = (ArrayNode) node;
                    					if(indexList.size() != array.numDimensions()){
                    						error("error on line " + arrayVar.getLine() + ": invalid number of dimensions");
                    					}
                    				}
                    				else if(StatevectorMap.containsKey((arrayVar!=null?arrayVar.getText():null))){
                    					error("error on line " + arrayVar.getLine() + ": " + (arrayVar!=null?arrayVar.getText():null) + " is not an array");
                    				}
                    				else{
                    					error("error on line " + arrayVar.getLine() + ": " + (arrayVar!=null?arrayVar.getText():null) + " is not defined"); 
                    				}
                    				
                    				// build name
                    				String arrayName = (arrayVar!=null?arrayVar.getText():null);
                    				for(int i = 0; i < indexList.size(); i++){
                    					arrayName = arrayName + "[" + indexList.get(i) + "]";
                    				}
                    				
                    				// get value from StatevectorMap
                    				Integer val = StatevectorMap.get(arrayName);
                    				
                    				if(val == null){
                    					System.err.println("error on line " + arrayVar.getLine() + ": invalid bounds"); 
                        				System.exit(1);
                    				}
                    				
                    				value = val;
                    				ClassReadSet.add((arrayVar!=null?arrayVar.getText():null));
                    			

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
        return value;
    }
    // $ANTLR end "intValue"


    // $ANTLR start "constIntValue"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1042:1: constIntValue returns [int value] : ( ( '-' )* intVal= INT | var= ID );
    public final int constIntValue() throws RecognitionException {
        int value = 0;

        Token intVal=null;
        Token var=null;

        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1043:2: ( ( '-' )* intVal= INT | var= ID )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==INT||LA41_0==MINUS) ) {
                alt41=1;
            }
            else if ( (LA41_0==ID) ) {
                alt41=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }
            switch (alt41) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1043:5: ( '-' )* intVal= INT
                    {
                    boolean negative = false;
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1044:3: ( '-' )*
                    loop40:
                    do {
                        int alt40=2;
                        int LA40_0 = input.LA(1);

                        if ( (LA40_0==MINUS) ) {
                            alt40=1;
                        }


                        switch (alt40) {
                    	case 1 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1044:4: '-'
                    	    {
                    	    match(input,MINUS,FOLLOW_MINUS_in_constIntValue1411); 
                    	    negative = !negative;

                    	    }
                    	    break;

                    	default :
                    	    break loop40;
                        }
                    } while (true);

                    intVal=(Token)match(input,INT,FOLLOW_INT_in_constIntValue1419); 

                    				value = Integer.parseInt((intVal!=null?intVal.getText():null));
                    				if(negative)
                    					value = -value;
                    			

                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1050:5: var= ID
                    {
                    var=(Token)match(input,ID,FOLLOW_ID_in_constIntValue1433); 

                    				if(GlobalConstHashMap.containsKey((var!=null?var.getText():null))){
                    					value = GlobalConstHashMap.get((var!=null?var.getText():null));
                    				}
                    				else if(ConstHashMap.containsKey((var!=null?var.getText():null))){
                    					value = ConstHashMap.get((var!=null?var.getText():null));
                    				}
                    				else{
                    					error("error on line " + var.getLine() + ": constant " + (var!=null?var.getText():null) + " is not defined"); 
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
        return value;
    }
    // $ANTLR end "constIntValue"


    // $ANTLR start "mainIntValue"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1065:1: mainIntValue returns [int value] : ( ( '-' )* intVal= INT | var= ID );
    public final int mainIntValue() throws RecognitionException {
        int value = 0;

        Token intVal=null;
        Token var=null;

        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1066:2: ( ( '-' )* intVal= INT | var= ID )
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==INT||LA43_0==MINUS) ) {
                alt43=1;
            }
            else if ( (LA43_0==ID) ) {
                alt43=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }
            switch (alt43) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1066:5: ( '-' )* intVal= INT
                    {
                    boolean negative = false;
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1067:3: ( '-' )*
                    loop42:
                    do {
                        int alt42=2;
                        int LA42_0 = input.LA(1);

                        if ( (LA42_0==MINUS) ) {
                            alt42=1;
                        }


                        switch (alt42) {
                    	case 1 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1067:4: '-'
                    	    {
                    	    match(input,MINUS,FOLLOW_MINUS_in_mainIntValue1461); 
                    	    negative = !negative;

                    	    }
                    	    break;

                    	default :
                    	    break loop42;
                        }
                    } while (true);

                    intVal=(Token)match(input,INT,FOLLOW_INT_in_mainIntValue1469); 

                    				value = Integer.parseInt((intVal!=null?intVal.getText():null));
                    				if(negative)
                    					value = -value;
                    			

                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1073:5: var= ID
                    {
                    var=(Token)match(input,ID,FOLLOW_ID_in_mainIntValue1483); 

                    				if(GlobalConstHashMap.containsKey((var!=null?var.getText():null))){
                    					value = GlobalConstHashMap.get((var!=null?var.getText():null));
                    				}
                    				else if(ConstHashMap.containsKey((var!=null?var.getText():null))){
                    					value = ConstHashMap.get((var!=null?var.getText():null));
                    				}
                    				else if(GlobalVarHashMap.containsKey((var!=null?var.getText():null))){
                    					error("error on line " + var.getLine() + ": variables can only be used in instantiation"); 
                    				}
                    				else if(GlobalVarNodeMap.containsKey((var!=null?var.getText():null))){
                    					error("error on line " + var.getLine() + ": variables can only be used in instantiation"); 
                    				}
                    				else{
                    					error("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " is not defined"); 
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
        return value;
    }
    // $ANTLR end "mainIntValue"

    public static class assignmentIntValue_return extends ParserRuleReturnScope {
        public int value;
        public boolean atomic;
    };

    // $ANTLR start "assignmentIntValue"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1097:1: assignmentIntValue[String lhs] returns [int value, boolean atomic] : ( ( ( '-' )* intVal= INT ) | var= ID | (arrayVar= ID ( '[' expression ']' )+ ) );
    public final PlatuInstParser.assignmentIntValue_return assignmentIntValue(String lhs) throws RecognitionException {
        PlatuInstParser.assignmentIntValue_return retval = new PlatuInstParser.assignmentIntValue_return();
        retval.start = input.LT(1);

        Token intVal=null;
        Token var=null;
        Token arrayVar=null;
        PlatuInstParser.expression_return expression7 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1098:2: ( ( ( '-' )* intVal= INT ) | var= ID | (arrayVar= ID ( '[' expression ']' )+ ) )
            int alt46=3;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==INT||LA46_0==MINUS) ) {
                alt46=1;
            }
            else if ( (LA46_0==ID) ) {
                int LA46_2 = input.LA(2);

                if ( (LA46_2==RBRACK||LA46_2==SEMICOLON||LA46_2==COMMA) ) {
                    alt46=2;
                }
                else if ( (LA46_2==70) ) {
                    alt46=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 46, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;
            }
            switch (alt46) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1098:5: ( ( '-' )* intVal= INT )
                    {
                    boolean negative = false;
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1099:3: ( ( '-' )* intVal= INT )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1099:5: ( '-' )* intVal= INT
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1099:5: ( '-' )*
                    loop44:
                    do {
                        int alt44=2;
                        int LA44_0 = input.LA(1);

                        if ( (LA44_0==MINUS) ) {
                            alt44=1;
                        }


                        switch (alt44) {
                    	case 1 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1099:6: '-'
                    	    {
                    	    match(input,MINUS,FOLLOW_MINUS_in_assignmentIntValue1514); 
                    	    negative = !negative;

                    	    }
                    	    break;

                    	default :
                    	    break loop44;
                        }
                    } while (true);

                    intVal=(Token)match(input,INT,FOLLOW_INT_in_assignmentIntValue1522); 

                    }


                    				retval.value = Integer.parseInt((intVal!=null?intVal.getText():null));
                    				if(negative)
                    					retval.value = -retval.value;
                    					
                    				retval.atomic = true;
                    			

                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1107:5: var= ID
                    {
                    var=(Token)match(input,ID,FOLLOW_ID_in_assignmentIntValue1538); 

                    				if(GlobalConstHashMap.containsKey((var!=null?var.getText():null))){
                    					retval.value = GlobalConstHashMap.get((var!=null?var.getText():null));
                    					retval.atomic = true;
                    				}
                    				else if(ConstHashMap.containsKey((var!=null?var.getText():null))){
                    					retval.value = ConstHashMap.get((var!=null?var.getText():null));
                    					retval.atomic = true;
                    				}
                    				else if(StatevectorMap.containsKey((var!=null?var.getText():null))){
                    					retval.value = StatevectorMap.get((var!=null?var.getText():null));
                    					
                    					if(Inputs.contains((var!=null?var.getText():null))){
                    						List<String> assignmentVars = InputInitializationMap.get((var!=null?var.getText():null));
                    						if(assignmentVars == null){
                    							assignmentVars = new LinkedList<String>();
                    							InputInitializationMap.put((var!=null?var.getText():null), assignmentVars);
                    						}
                    						
                    						assignmentVars.add(lhs);
                    						
                    						retval.atomic = false;
                    					}
                    					else{
                    						error("error on line " + var.getLine() + ": only input varaibles can be used for initialization");
                    					}
                    					
                    					ClassReadSet.add((var!=null?var.getText():null));
                    				}
                    				else if(VarNodeMap.containsKey((var!=null?var.getText():null))){
                    					error("error on line " + var.getLine() + ": incorrect usage of array variable " + (var!=null?var.getText():null));
                    				}
                    				else{
                    					error("error on line " + var.getLine() + ": " + (var!=null?var.getText():null) + " is not defined"); 
                    				}
                    			

                    }
                    break;
                case 3 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1144:5: (arrayVar= ID ( '[' expression ']' )+ )
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1144:5: (arrayVar= ID ( '[' expression ']' )+ )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1144:6: arrayVar= ID ( '[' expression ']' )+
                    {
                    arrayVar=(Token)match(input,ID,FOLLOW_ID_in_assignmentIntValue1552); 

                    				List<Integer> indexList = new ArrayList<Integer>();
                    			
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1148:3: ( '[' expression ']' )+
                    int cnt45=0;
                    loop45:
                    do {
                        int alt45=2;
                        int LA45_0 = input.LA(1);

                        if ( (LA45_0==70) ) {
                            alt45=1;
                        }


                        switch (alt45) {
                    	case 1 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1148:4: '[' expression ']'
                    	    {
                    	    match(input,70,FOLLOW_70_in_assignmentIntValue1563); 
                    	    pushFollow(FOLLOW_expression_in_assignmentIntValue1565);
                    	    expression7=expression();

                    	    state._fsp--;


                    	    				indexList.add((expression7!=null?expression7.value:0));
                    	    			
                    	    match(input,71,FOLLOW_71_in_assignmentIntValue1575); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt45 >= 1 ) break loop45;
                                EarlyExitException eee =
                                    new EarlyExitException(45, input);
                                throw eee;
                        }
                        cnt45++;
                    } while (true);


                    				// check for valid ID (make sure its an array variable)
                    				if(GlobalConstHashMap.containsKey((arrayVar!=null?arrayVar.getText():null))){
                    					error("error on line " + arrayVar.getLine() + ": " + (arrayVar!=null?arrayVar.getText():null) + " is not an array"); 
                    				}
                    				else if(ConstHashMap.containsKey((arrayVar!=null?arrayVar.getText():null))){
                    					error("error on line " + arrayVar.getLine() + ": " + (arrayVar!=null?arrayVar.getText():null) + " is not an array"); 
                    				}
                    				else if(VarNodeMap.containsKey((arrayVar!=null?arrayVar.getText():null))){
                    					VarNode node = VarNodeMap.get((arrayVar!=null?arrayVar.getText():null));
                    					if(!ArrayNode.class.isAssignableFrom(node.getClass())){
                    						error("error on line " + arrayVar.getLine() + ": " + (arrayVar!=null?arrayVar.getText():null) + " is not an array"); 
                    					}
                    					
                    					ArrayNode array = (ArrayNode) node;
                    					if(indexList.size() != array.numDimensions()){
                    						error("error on line " + arrayVar.getLine() + ": invalid number of dimensions");
                    					}
                    				}
                    				else if(StatevectorMap.containsKey((arrayVar!=null?arrayVar.getText():null))){
                    					error("error on line " + arrayVar.getLine() + ": " + (arrayVar!=null?arrayVar.getText():null) + " is not an array");
                    				}
                    				else{
                    					error("error on line " + arrayVar.getLine() + ": " + (arrayVar!=null?arrayVar.getText():null) + " is not defined"); 
                    				}
                    				
                    				// build array element's name
                    				String arrayElement = (arrayVar!=null?arrayVar.getText():null);
                    				for(int i = 0; i < indexList.size(); i++){
                    					arrayElement = arrayElement + "[" + indexList.get(i) + "]";
                    				}
                    				
                    				// get value from StatevectorMap
                    				Integer val = StatevectorMap.get(arrayElement);
                    				
                    				if(val == null){
                    					System.err.println("error on line " + arrayVar.getLine() + ": invalid bounds"); 
                        				System.exit(1);
                    				}
                    				
                    				// check if the array is an input
                    				if(Inputs.contains(arrayVar)){
                    					List<String> assignmentVars = InputInitializationMap.get(arrayElement);
                    					if(assignmentVars == null){
                    						assignmentVars = new LinkedList<String>();
                    						InputInitializationMap.put(arrayElement, assignmentVars);
                    					}
                    					
                    					assignmentVars.add(lhs);
                    				}
                    				else{
                    					error("error on line " + arrayVar.getLine() + ": only input varaibles can be used for initialization");
                    				}
                    				
                    				retval.value = val;
                    				retval.atomic = false;
                    				
                    				ClassReadSet.add((arrayVar!=null?arrayVar.getText():null));
                    			

                    }


                    }
                    break;

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
    // $ANTLR end "assignmentIntValue"


    // $ANTLR start "instantiation"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1216:1: instantiation : (modName= ID instName= ID '(' ( arguments[$modName.text, argList, moduleList] )? ')' ';' )+ ;
    public final void instantiation() throws RecognitionException {
        Token modName=null;
        Token instName=null;

        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1217:5: ( (modName= ID instName= ID '(' ( arguments[$modName.text, argList, moduleList] )? ')' ';' )+ )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1217:7: (modName= ID instName= ID '(' ( arguments[$modName.text, argList, moduleList] )? ')' ';' )+
            {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1217:7: (modName= ID instName= ID '(' ( arguments[$modName.text, argList, moduleList] )? ')' ';' )+
            int cnt48=0;
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( (LA48_0==ID) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1217:8: modName= ID instName= ID '(' ( arguments[$modName.text, argList, moduleList] )? ')' ';'
            	    {
            	    modName=(Token)match(input,ID,FOLLOW_ID_in_instantiation1605); 
            	    instName=(Token)match(input,ID,FOLLOW_ID_in_instantiation1609); 

            	        			List<String> argList = new ArrayList<String>();
            	        			List<String> moduleList = new ArrayList<String>();
            	        		
            	    match(input,LPAREN,FOLLOW_LPAREN_in_instantiation1625); 
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1222:10: ( arguments[$modName.text, argList, moduleList] )?
            	    int alt47=2;
            	    int LA47_0 = input.LA(1);

            	    if ( ((LA47_0>=ID && LA47_0<=MEMBER)||LA47_0==MINUS) ) {
            	        alt47=1;
            	    }
            	    switch (alt47) {
            	        case 1 :
            	            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1222:10: arguments[$modName.text, argList, moduleList]
            	            {
            	            pushFollow(FOLLOW_arguments_in_instantiation1627);
            	            arguments((modName!=null?modName.getText():null), argList, moduleList);

            	            state._fsp--;


            	            }
            	            break;

            	    }

            	    match(input,RPAREN,FOLLOW_RPAREN_in_instantiation1631); 
            	    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_instantiation1633); 

            	        			Instance inst = new Instance((modName!=null?modName.getText():null), (instName!=null?instName.getText():null), argList, moduleList);
            	        			inst.setLineNumber(modName.getLine());
            	        			InstanceList.add(inst);
            	        		

            	    }
            	    break;

            	default :
            	    if ( cnt48 >= 1 ) break loop48;
                        EarlyExitException eee =
                            new EarlyExitException(48, input);
                        throw eee;
                }
                cnt48++;
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
        return ;
    }
    // $ANTLR end "instantiation"


    // $ANTLR start "arguments"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1232:1: arguments[String modName, List<String> argList, List<String> moduleList] : (id1= ID | ( ( '-' )* int1= INT ) | var= MEMBER ) ( ',' (id2= ID | ( ( '-' )* int2= INT ) | var2= MEMBER ) )* ;
    public final void arguments(String modName, List<String> argList, List<String> moduleList) throws RecognitionException {
        Token id1=null;
        Token int1=null;
        Token var=null;
        Token id2=null;
        Token int2=null;
        Token var2=null;

        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1233:2: ( (id1= ID | ( ( '-' )* int1= INT ) | var= MEMBER ) ( ',' (id2= ID | ( ( '-' )* int2= INT ) | var2= MEMBER ) )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1233:4: (id1= ID | ( ( '-' )* int1= INT ) | var= MEMBER ) ( ',' (id2= ID | ( ( '-' )* int2= INT ) | var2= MEMBER ) )*
            {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1233:4: (id1= ID | ( ( '-' )* int1= INT ) | var= MEMBER )
            int alt50=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt50=1;
                }
                break;
            case INT:
            case MINUS:
                {
                alt50=2;
                }
                break;
            case MEMBER:
                {
                alt50=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;
            }

            switch (alt50) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1233:5: id1= ID
                    {
                    id1=(Token)match(input,ID,FOLLOW_ID_in_arguments1672); 

                        			if(GlobalConstHashMap.containsKey((id1!=null?id1.getText():null))){
                        				moduleList.add(null);
                        				argList.add(Integer.toString(GlobalConstHashMap.get((id1!=null?id1.getText():null))));
                        			}
                        			else if(ConstHashMap.containsKey((id1!=null?id1.getText():null))){
                        				moduleList.add(null);
                        				argList.add(Integer.toString(ConstHashMap.get((id1!=null?id1.getText():null))));
                        			}
                        			else if(GlobalVarHashMap.containsKey((id1!=null?id1.getText():null))){
                        				moduleList.add(null);
                        				argList.add((id1!=null?id1.getText():null));
                        			}
                        			else if(GlobalVarNodeMap.containsKey((id1!=null?id1.getText():null))){
                        				moduleList.add(null);
                        				argList.add((id1!=null?id1.getText():null));
                        			}
                        			else{
                        				error("error on line " + id1.getLine() + ": variable " + (id1!=null?id1.getText():null) + " is not declared");
                        			}
                        			
                        		

                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1256:8: ( ( '-' )* int1= INT )
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1256:8: ( ( '-' )* int1= INT )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1256:9: ( '-' )* int1= INT
                    {
                    boolean negative = false;
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1256:37: ( '-' )*
                    loop49:
                    do {
                        int alt49=2;
                        int LA49_0 = input.LA(1);

                        if ( (LA49_0==MINUS) ) {
                            alt49=1;
                        }


                        switch (alt49) {
                    	case 1 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1256:38: '-'
                    	    {
                    	    match(input,MINUS,FOLLOW_MINUS_in_arguments1694); 
                    	    negative = !negative;

                    	    }
                    	    break;

                    	default :
                    	    break loop49;
                        }
                    } while (true);

                    int1=(Token)match(input,INT,FOLLOW_INT_in_arguments1702); 

                        			int value = Integer.parseInt((int1!=null?int1.getText():null));
                        			if(negative)
                        				value = -value;
                        				
                        			moduleList.add(null);
                        			argList.add(Integer.toString(value));
                        		

                    }


                    }
                    break;
                case 3 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1265:10: var= MEMBER
                    {
                    var=(Token)match(input,MEMBER,FOLLOW_MEMBER_in_arguments1723); 

                        			// get module variable belongs to
                        			String buffer = (var!=null?var.getText():null);
                            		StringTokenizer tk = new StringTokenizer(buffer, ".");
                        			String module = tk.nextToken();
                        			
                        			if(modName.equals(module)){
                        				error("error on line " + var.getLine() + ": variable from the same module cannot be used as an input");
                        			}
                        			
                        			moduleList.add(module);
                        			argList.add((var!=null?var.getText():null));
                        		

                    }
                    break;

            }

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1279:7: ( ',' (id2= ID | ( ( '-' )* int2= INT ) | var2= MEMBER ) )*
            loop53:
            do {
                int alt53=2;
                int LA53_0 = input.LA(1);

                if ( (LA53_0==COMMA) ) {
                    alt53=1;
                }


                switch (alt53) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1279:9: ',' (id2= ID | ( ( '-' )* int2= INT ) | var2= MEMBER )
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_arguments1742); 
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1279:13: (id2= ID | ( ( '-' )* int2= INT ) | var2= MEMBER )
            	    int alt52=3;
            	    switch ( input.LA(1) ) {
            	    case ID:
            	        {
            	        alt52=1;
            	        }
            	        break;
            	    case INT:
            	    case MINUS:
            	        {
            	        alt52=2;
            	        }
            	        break;
            	    case MEMBER:
            	        {
            	        alt52=3;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 52, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt52) {
            	        case 1 :
            	            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1279:15: id2= ID
            	            {
            	            id2=(Token)match(input,ID,FOLLOW_ID_in_arguments1748); 

            	                			if(GlobalConstHashMap.containsKey((id2!=null?id2.getText():null))){
            	                				moduleList.add(null);
            	                				argList.add(Integer.toString(GlobalConstHashMap.get((id2!=null?id2.getText():null))));
            	                			}
            	                			else if(ConstHashMap.containsKey((id2!=null?id2.getText():null))){
            	                				moduleList.add(null);
            	                				argList.add(Integer.toString(ConstHashMap.get((id2!=null?id2.getText():null))));
            	                			}
            	                			else if(GlobalVarHashMap.containsKey((id2!=null?id2.getText():null))){
            	                				moduleList.add(null);
            	                				argList.add((id2!=null?id2.getText():null));
            	                			}
            	                			else if(GlobalVarNodeMap.containsKey((id2!=null?id2.getText():null))){
            	                				moduleList.add(null);
            	                				argList.add((id2!=null?id2.getText():null));
            	                			}
            	                			else{
            	                				error("error on line " + id2.getLine() + ": variable " + (id2!=null?id2.getText():null) + " is not declared");
            	                			};
            	                		

            	            }
            	            break;
            	        case 2 :
            	            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1301:8: ( ( '-' )* int2= INT )
            	            {
            	            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1301:8: ( ( '-' )* int2= INT )
            	            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1301:9: ( '-' )* int2= INT
            	            {
            	            boolean negative = false;
            	            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1301:37: ( '-' )*
            	            loop51:
            	            do {
            	                int alt51=2;
            	                int LA51_0 = input.LA(1);

            	                if ( (LA51_0==MINUS) ) {
            	                    alt51=1;
            	                }


            	                switch (alt51) {
            	            	case 1 :
            	            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1301:38: '-'
            	            	    {
            	            	    match(input,MINUS,FOLLOW_MINUS_in_arguments1770); 
            	            	    negative = !negative;

            	            	    }
            	            	    break;

            	            	default :
            	            	    break loop51;
            	                }
            	            } while (true);

            	            int2=(Token)match(input,INT,FOLLOW_INT_in_arguments1778); 

            	                			int value = Integer.parseInt((int2!=null?int2.getText():null));
            	                			if(negative)
            	                				value = -value;
            	                				
            	                			moduleList.add(null);
            	                			argList.add(Integer.toString(value));
            	                		

            	            }


            	            }
            	            break;
            	        case 3 :
            	            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1310:10: var2= MEMBER
            	            {
            	            var2=(Token)match(input,MEMBER,FOLLOW_MEMBER_in_arguments1799); 

            	                			// get module variable belongs to
            	                			String buffer2 = (var2!=null?var2.getText():null);
            	                    		StringTokenizer tk2 = new StringTokenizer(buffer2, ".");
            	                    		
            	                			String module2 = tk2.nextToken();
            	                			String variable2 = tk2.nextToken();
            	                			
            	                			if(modName.equals(module2)){
            	                				error("error on line " + var2.getLine() + ": variable from the same module cannot be used as an input");
            	                			}
            	                			
            	                			moduleList.add(module2);
            	                			argList.add(module2 + "." + variable2);	
            	                		

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop53;
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
        return ;
    }
    // $ANTLR end "arguments"

    public static class logic_return extends ParserRuleReturnScope {
        public List<Integer> initMarking;
        public LpnTranList lpnTranSet;
    };

    // $ANTLR start "logic"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1330:1: logic returns [List<Integer> initMarking, LpnTranList lpnTranSet] : marking ( transition )+ ;
    public final PlatuInstParser.logic_return logic() throws RecognitionException {
        PlatuInstParser.logic_return retval = new PlatuInstParser.logic_return();
        retval.start = input.LT(1);

        LPNTran transition8 = null;

        List<Integer> marking9 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1331:5: ( marking ( transition )+ )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1331:9: marking ( transition )+
            {
            retval.lpnTranSet = new LpnTranList();
            pushFollow(FOLLOW_marking_in_logic1846);
            marking9=marking();

            state._fsp--;

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1332:14: ( transition )+
            int cnt54=0;
            loop54:
            do {
                int alt54=2;
                int LA54_0 = input.LA(1);

                if ( (LA54_0==LESS) ) {
                    int LA54_1 = input.LA(2);

                    if ( (LA54_1==77) ) {
                        alt54=1;
                    }


                }


                switch (alt54) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1332:15: transition
            	    {
            	    pushFollow(FOLLOW_transition_in_logic1849);
            	    transition8=transition();

            	    state._fsp--;

            	    retval.lpnTranSet.add(transition8);

            	    }
            	    break;

            	default :
            	    if ( cnt54 >= 1 ) break loop54;
                        EarlyExitException eee =
                            new EarlyExitException(54, input);
                        throw eee;
                }
                cnt54++;
            } while (true);


                        retval.initMarking = marking9;
                    

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
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1339:1: marking returns [List<Integer> mark] : ( '<' 'm' '>' (m1= intValue ( ',' m2= intValue )* )? '<' '/m' '>' )? ;
    public final List<Integer> marking() throws RecognitionException {
        List<Integer> mark = null;

        int m1 = 0;

        int m2 = 0;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1340:5: ( ( '<' 'm' '>' (m1= intValue ( ',' m2= intValue )* )? '<' '/m' '>' )? )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1340:9: ( '<' 'm' '>' (m1= intValue ( ',' m2= intValue )* )? '<' '/m' '>' )?
            {
            mark = new LinkedList<Integer>();
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1341:9: ( '<' 'm' '>' (m1= intValue ( ',' m2= intValue )* )? '<' '/m' '>' )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==LESS) ) {
                int LA57_1 = input.LA(2);

                if ( (LA57_1==75) ) {
                    alt57=1;
                }
            }
            switch (alt57) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1341:10: '<' 'm' '>' (m1= intValue ( ',' m2= intValue )* )? '<' '/m' '>'
                    {
                    match(input,LESS,FOLLOW_LESS_in_marking1903); 
                    match(input,75,FOLLOW_75_in_marking1905); 
                    match(input,GREATER,FOLLOW_GREATER_in_marking1907); 
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1341:22: (m1= intValue ( ',' m2= intValue )* )?
                    int alt56=2;
                    int LA56_0 = input.LA(1);

                    if ( ((LA56_0>=ID && LA56_0<=INT)||LA56_0==MINUS) ) {
                        alt56=1;
                    }
                    switch (alt56) {
                        case 1 :
                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1341:23: m1= intValue ( ',' m2= intValue )*
                            {
                            pushFollow(FOLLOW_intValue_in_marking1912);
                            m1=intValue();

                            state._fsp--;


                                    		mark.add(m1);
                                    	
                            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1345:9: ( ',' m2= intValue )*
                            loop55:
                            do {
                                int alt55=2;
                                int LA55_0 = input.LA(1);

                                if ( (LA55_0==COMMA) ) {
                                    alt55=1;
                                }


                                switch (alt55) {
                            	case 1 :
                            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1345:10: ',' m2= intValue
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_marking1935); 
                            	    pushFollow(FOLLOW_intValue_in_marking1939);
                            	    m2=intValue();

                            	    state._fsp--;


                            	            		mark.add(m2);
                            	            	

                            	    }
                            	    break;

                            	default :
                            	    break loop55;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,LESS,FOLLOW_LESS_in_marking1965); 
                    match(input,76,FOLLOW_76_in_marking1967); 
                    match(input,GREATER,FOLLOW_GREATER_in_marking1969); 

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
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1353:1: transition returns [LPNTran lpnTran] : '<' 'tr' lbl= 'label' '=' '\"' (lblInt= INT | lblString= LABELSTRING | lblID= ID ) '\"' preset[presetList] postset[postsetList] '>' ( guard )? ( delay )? ( ( assertion ) | ( assignment ) )* '<' '/tr' '>' ;
    public final LPNTran transition() throws RecognitionException {
        LPNTran lpnTran = null;

        Token lbl=null;
        Token lblInt=null;
        Token lblString=null;
        Token lblID=null;
        Expression guard10 = null;

        PlatuInstParser.delay_return delay11 = null;

        Expression assertion12 = null;

        List<VarExpr> assignment13 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1354:5: ( '<' 'tr' lbl= 'label' '=' '\"' (lblInt= INT | lblString= LABELSTRING | lblID= ID ) '\"' preset[presetList] postset[postsetList] '>' ( guard )? ( delay )? ( ( assertion ) | ( assignment ) )* '<' '/tr' '>' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1354:10: '<' 'tr' lbl= 'label' '=' '\"' (lblInt= INT | lblString= LABELSTRING | lblID= ID ) '\"' preset[presetList] postset[postsetList] '>' ( guard )? ( delay )? ( ( assertion ) | ( assignment ) )* '<' '/tr' '>'
            {

            	    		ArrayList<Integer> presetList = new ArrayList<Integer>();  
            	    		ArrayList<Integer> postsetList = new ArrayList<Integer>(); 
            	    		VarExprList assignmentList = new VarExprList();
            	    		ArrayList<Expression> assertionList = new ArrayList<Expression>();
            	    		Expression guardExpr = new Expression(new TrueNode()); 
            	    		String label = "";
            	    		int delayLB = 0; 
            	    		int delayUB = INFINITY;
            	    		boolean local = true;
            	    	
            match(input,LESS,FOLLOW_LESS_in_transition2004); 
            match(input,77,FOLLOW_77_in_transition2006); 
            lbl=(Token)match(input,LABEL,FOLLOW_LABEL_in_transition2010); 
            match(input,EQUALS,FOLLOW_EQUALS_in_transition2012); 
            match(input,QUOTE,FOLLOW_QUOTE_in_transition2014); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1365:35: (lblInt= INT | lblString= LABELSTRING | lblID= ID )
            int alt58=3;
            switch ( input.LA(1) ) {
            case INT:
                {
                alt58=1;
                }
                break;
            case LABELSTRING:
                {
                alt58=2;
                }
                break;
            case ID:
                {
                alt58=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;
            }

            switch (alt58) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1365:36: lblInt= INT
                    {
                    lblInt=(Token)match(input,INT,FOLLOW_INT_in_transition2019); 

                        			label = (lblInt!=null?lblInt.getText():null);
                        			if(!TransitionLabelSet.add(label)){
                        				error("error on line " + lbl.getLine() + ": duplicate label " + label);
                        			}
                        		

                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1372:8: lblString= LABELSTRING
                    {
                    lblString=(Token)match(input,LABELSTRING,FOLLOW_LABELSTRING_in_transition2039); 

                        			label = (lblString!=null?lblString.getText():null);
                        			if(!TransitionLabelSet.add(label)){
                        				error("error on line " + lbl.getLine() + ": duplicate label " + label);
                        			}
                        		

                    }
                    break;
                case 3 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1379:8: lblID= ID
                    {
                    lblID=(Token)match(input,ID,FOLLOW_ID_in_transition2058); 

                        			label = (lblID!=null?lblID.getText():null);
                        			if(!TransitionLabelSet.add(label)){
                        				error("error on line " + lbl.getLine() + ": duplicate label " + label);
                        			}
                        		

                    }
                    break;

            }

            match(input,QUOTE,FOLLOW_QUOTE_in_transition2075); 
            pushFollow(FOLLOW_preset_in_transition2077);
            preset(presetList);

            state._fsp--;

            pushFollow(FOLLOW_postset_in_transition2080);
            postset(postsetList);

            state._fsp--;

            match(input,GREATER,FOLLOW_GREATER_in_transition2083); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1386:56: ( guard )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==80) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1386:57: guard
                    {
                    pushFollow(FOLLOW_guard_in_transition2086);
                    guard10=guard();

                    state._fsp--;


                        			guardExpr = guard10;
                        		

                    }
                    break;

            }

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1390:9: ( delay )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==81) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1390:10: delay
                    {
                    pushFollow(FOLLOW_delay_in_transition2106);
                    delay11=delay();

                    state._fsp--;


                        			delayLB = (delay11!=null?delay11.delayLB:0); 
                        			delayUB = (delay11!=null?delay11.delayUB:0);
                        		

                    }
                    break;

            }

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1395:9: ( ( assertion ) | ( assignment ) )*
            loop61:
            do {
                int alt61=3;
                int LA61_0 = input.LA(1);

                if ( (LA61_0==79) ) {
                    alt61=1;
                }
                else if ( (LA61_0==ID) ) {
                    alt61=2;
                }


                switch (alt61) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1395:10: ( assertion )
            	    {
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1395:10: ( assertion )
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1395:11: assertion
            	    {
            	    pushFollow(FOLLOW_assertion_in_transition2127);
            	    assertion12=assertion();

            	    state._fsp--;


            	        			if(assertion12 != null){		
            	      					assertionList.add(assertion12);
            	      				}
            	        		

            	    }


            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1401:10: ( assignment )
            	    {
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1401:10: ( assignment )
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1401:11: assignment
            	    {
            	    pushFollow(FOLLOW_assignment_in_transition2147);
            	    assignment13=assignment();

            	    state._fsp--;


            	        			assignmentList.addAll(assignment13);
            	        		

            	    }


            	    }
            	    break;

            	default :
            	    break loop61;
                }
            } while (true);

            match(input,LESS,FOLLOW_LESS_in_transition2167); 
            match(input,78,FOLLOW_78_in_transition2169); 
            match(input,GREATER,FOLLOW_GREATER_in_transition2171); 

                    	// create new lpn transitions and add assertions
                    	lpnTran = new LPNTran(label, TransitionIndex++, presetList, postsetList, guardExpr, assignmentList, delayLB, delayUB, local);
                    	if(assertionList.size() > 0){
                    		lpnTran.addAllAssertions(assertionList);
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


    // $ANTLR start "preset"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1416:1: preset[List<Integer> presetList] : 'preset' '=' ( '\"' '\"' | ( '\"' pre= constIntValue ( ',' pre2= constIntValue )* '\"' ) ) ;
    public final void preset(List<Integer> presetList) throws RecognitionException {
        int pre = 0;

        int pre2 = 0;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1417:2: ( 'preset' '=' ( '\"' '\"' | ( '\"' pre= constIntValue ( ',' pre2= constIntValue )* '\"' ) ) )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1417:4: 'preset' '=' ( '\"' '\"' | ( '\"' pre= constIntValue ( ',' pre2= constIntValue )* '\"' ) )
            {
            match(input,PRESET,FOLLOW_PRESET_in_preset2200); 
            match(input,EQUALS,FOLLOW_EQUALS_in_preset2202); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1417:17: ( '\"' '\"' | ( '\"' pre= constIntValue ( ',' pre2= constIntValue )* '\"' ) )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==QUOTE) ) {
                int LA63_1 = input.LA(2);

                if ( (LA63_1==QUOTE) ) {
                    alt63=1;
                }
                else if ( ((LA63_1>=ID && LA63_1<=INT)||LA63_1==MINUS) ) {
                    alt63=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 63, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }
            switch (alt63) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1417:18: '\"' '\"'
                    {
                    match(input,QUOTE,FOLLOW_QUOTE_in_preset2205); 
                    match(input,QUOTE,FOLLOW_QUOTE_in_preset2207); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1417:28: ( '\"' pre= constIntValue ( ',' pre2= constIntValue )* '\"' )
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1417:28: ( '\"' pre= constIntValue ( ',' pre2= constIntValue )* '\"' )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1417:29: '\"' pre= constIntValue ( ',' pre2= constIntValue )* '\"'
                    {
                    match(input,QUOTE,FOLLOW_QUOTE_in_preset2212); 
                    pushFollow(FOLLOW_constIntValue_in_preset2216);
                    pre=constIntValue();

                    state._fsp--;


                        			presetList.add(pre);
                       			
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1421:4: ( ',' pre2= constIntValue )*
                    loop62:
                    do {
                        int alt62=2;
                        int LA62_0 = input.LA(1);

                        if ( (LA62_0==COMMA) ) {
                            alt62=1;
                        }


                        switch (alt62) {
                    	case 1 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1421:6: ',' pre2= constIntValue
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_preset2232); 
                    	    pushFollow(FOLLOW_constIntValue_in_preset2236);
                    	    pre2=constIntValue();

                    	    state._fsp--;


                    	        			presetList.add(pre2);
                    	       			

                    	    }
                    	    break;

                    	default :
                    	    break loop62;
                        }
                    } while (true);

                    match(input,QUOTE,FOLLOW_QUOTE_in_preset2253); 

                    }


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
        return ;
    }
    // $ANTLR end "preset"


    // $ANTLR start "postset"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1429:1: postset[List<Integer> postsetList] : 'postset' '=' ( '\"' '\"' | ( '\"' post= constIntValue ( ',' post2= constIntValue )* '\"' ) ) ;
    public final void postset(List<Integer> postsetList) throws RecognitionException {
        int post = 0;

        int post2 = 0;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1430:2: ( 'postset' '=' ( '\"' '\"' | ( '\"' post= constIntValue ( ',' post2= constIntValue )* '\"' ) ) )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1430:4: 'postset' '=' ( '\"' '\"' | ( '\"' post= constIntValue ( ',' post2= constIntValue )* '\"' ) )
            {
            match(input,POSTSET,FOLLOW_POSTSET_in_postset2269); 
            match(input,EQUALS,FOLLOW_EQUALS_in_postset2271); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1430:18: ( '\"' '\"' | ( '\"' post= constIntValue ( ',' post2= constIntValue )* '\"' ) )
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==QUOTE) ) {
                int LA65_1 = input.LA(2);

                if ( (LA65_1==QUOTE) ) {
                    alt65=1;
                }
                else if ( ((LA65_1>=ID && LA65_1<=INT)||LA65_1==MINUS) ) {
                    alt65=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 65, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 65, 0, input);

                throw nvae;
            }
            switch (alt65) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1430:20: '\"' '\"'
                    {
                    match(input,QUOTE,FOLLOW_QUOTE_in_postset2275); 
                    match(input,QUOTE,FOLLOW_QUOTE_in_postset2277); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1430:30: ( '\"' post= constIntValue ( ',' post2= constIntValue )* '\"' )
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1430:30: ( '\"' post= constIntValue ( ',' post2= constIntValue )* '\"' )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1430:31: '\"' post= constIntValue ( ',' post2= constIntValue )* '\"'
                    {
                    match(input,QUOTE,FOLLOW_QUOTE_in_postset2282); 
                    pushFollow(FOLLOW_constIntValue_in_postset2286);
                    post=constIntValue();

                    state._fsp--;


                        			postsetList.add(post);
                        		
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1434:6: ( ',' post2= constIntValue )*
                    loop64:
                    do {
                        int alt64=2;
                        int LA64_0 = input.LA(1);

                        if ( (LA64_0==COMMA) ) {
                            alt64=1;
                        }


                        switch (alt64) {
                    	case 1 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1434:8: ',' post2= constIntValue
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_postset2304); 
                    	    pushFollow(FOLLOW_constIntValue_in_postset2308);
                    	    post2=constIntValue();

                    	    state._fsp--;


                    	        			postsetList.add(post2);
                    	        		

                    	    }
                    	    break;

                    	default :
                    	    break loop64;
                        }
                    } while (true);

                    match(input,QUOTE,FOLLOW_QUOTE_in_postset2327); 

                    }


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
        return ;
    }
    // $ANTLR end "postset"


    // $ANTLR start "assertion"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1442:1: assertion returns [Expression booleanExpr] : 'assert' '(' expression ')' ';' ;
    public final Expression assertion() throws RecognitionException {
        Expression booleanExpr = null;

        PlatuInstParser.expression_return expression14 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1443:2: ( 'assert' '(' expression ')' ';' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1443:4: 'assert' '(' expression ')' ';'
            {
            booleanExpr = null;
            match(input,79,FOLLOW_79_in_assertion2352); 
            match(input,LPAREN,FOLLOW_LPAREN_in_assertion2354); 
            pushFollow(FOLLOW_expression_in_assertion2356);
            expression14=expression();

            state._fsp--;

            match(input,RPAREN,FOLLOW_RPAREN_in_assertion2358); 
            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_assertion2360); 

            				booleanExpr = new Expression((expression14!=null?expression14.expr:null));
            			

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
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1451:1: guard returns [Expression expr] : 'guard' '(' expression ')' ';' ;
    public final Expression guard() throws RecognitionException {
        Expression expr = null;

        PlatuInstParser.expression_return expression15 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1452:5: ( 'guard' '(' expression ')' ';' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1452:8: 'guard' '(' expression ')' ';'
            {
            match(input,80,FOLLOW_80_in_guard2386); 
            match(input,LPAREN,FOLLOW_LPAREN_in_guard2388); 
            pushFollow(FOLLOW_expression_in_guard2390);
            expression15=expression();

            state._fsp--;

            match(input,RPAREN,FOLLOW_RPAREN_in_guard2392); 
            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_guard2394); 

               				expr = new Expression((expression15!=null?expression15.expr:null));
                		

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
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1459:1: delay returns [int delayLB, int delayUB] : 'delay' '(' lb= intValue ',' (ub= intValue | 'inf' ) ')' ';' ;
    public final PlatuInstParser.delay_return delay() throws RecognitionException {
        PlatuInstParser.delay_return retval = new PlatuInstParser.delay_return();
        retval.start = input.LT(1);

        int lb = 0;

        int ub = 0;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1460:5: ( 'delay' '(' lb= intValue ',' (ub= intValue | 'inf' ) ')' ';' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1460:8: 'delay' '(' lb= intValue ',' (ub= intValue | 'inf' ) ')' ';'
            {
            match(input,81,FOLLOW_81_in_delay2428); 
            match(input,LPAREN,FOLLOW_LPAREN_in_delay2430); 
            pushFollow(FOLLOW_intValue_in_delay2434);
            lb=intValue();

            state._fsp--;


                			retval.delayLB = lb;
               			
            match(input,COMMA,FOLLOW_COMMA_in_delay2448); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1464:8: (ub= intValue | 'inf' )
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( ((LA66_0>=ID && LA66_0<=INT)||LA66_0==MINUS) ) {
                alt66=1;
            }
            else if ( (LA66_0==82) ) {
                alt66=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;
            }
            switch (alt66) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1464:9: ub= intValue
                    {
                    pushFollow(FOLLOW_intValue_in_delay2453);
                    ub=intValue();

                    state._fsp--;


                     				retval.delayUB = ub;
                     				// make sure delays are >= 0 and upper bound is >= lower bound
                     				if(retval.delayLB < 0){
                     					error("error on line ??: lower bound " + retval.delayLB + " must be >= 0");
                     				}
                     				else if(retval.delayLB == INFINITY){
                     					error("error on line ??: lower bound " + retval.delayUB + " must be a non-negative finite number");
                     				}
                     				else if(retval.delayUB < retval.delayLB){
                     					error("error on line ??: upper bound " + retval.delayUB + " < lower bound " + retval.delayLB);
                     				} 
                     			

                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1478:6: 'inf'
                    {
                    match(input,82,FOLLOW_82_in_delay2467); 

                     				retval.delayUB = INFINITY;
                    			

                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_delay2480); 
            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_delay2482); 

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
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1486:1: assignment returns [List<VarExpr> assign] : (var1= ID '=' var2= ID | (arrayVar= ID ( '[' (arrayExpr= expression ) ']' )+ '=' assignExpr= expression ';' ) | (var= ID '=' varExpr= expression ';' ) );
    public final List<VarExpr> assignment() throws RecognitionException {
        List<VarExpr> assign = null;

        Token var1=null;
        Token var2=null;
        Token arrayVar=null;
        Token var=null;
        PlatuInstParser.expression_return arrayExpr = null;

        PlatuInstParser.expression_return assignExpr = null;

        PlatuInstParser.expression_return varExpr = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1487:5: (var1= ID '=' var2= ID | (arrayVar= ID ( '[' (arrayExpr= expression ) ']' )+ '=' assignExpr= expression ';' ) | (var= ID '=' varExpr= expression ';' ) )
            int alt68=3;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==ID) ) {
                int LA68_1 = input.LA(2);

                if ( (LA68_1==EQUALS) ) {
                    int LA68_2 = input.LA(3);

                    if ( (LA68_2==ID) ) {
                        switch ( input.LA(4) ) {
                        case LESS:
                            {
                            int LA68_6 = input.LA(5);

                            if ( (LA68_6==78) ) {
                                alt68=1;
                            }
                            else if ( ((LA68_6>=ID && LA68_6<=INT)||LA68_6==LPAREN||(LA68_6>=TRUE && LA68_6<=FALSE)||(LA68_6>=PLUS && LA68_6<=MINUS)||LA68_6==NEGATION||LA68_6==BITWISE_NEGATION) ) {
                                alt68=3;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 68, 6, input);

                                throw nvae;
                            }
                            }
                            break;
                        case ID:
                        case 79:
                            {
                            alt68=1;
                            }
                            break;
                        case QMARK:
                        case SEMICOLON:
                        case PLUS:
                        case MINUS:
                        case TIMES:
                        case DIV:
                        case MOD:
                        case GREATER:
                        case GREATER_EQUAL:
                        case LESS_EQUAL:
                        case EQUIV:
                        case NOT_EQUIV:
                        case AND:
                        case OR:
                        case IMPLICATION:
                        case BITWISE_AND:
                        case BITWISE_OR:
                        case BITWISE_XOR:
                        case BITWISE_LSHIFT:
                        case BITWISE_RSHIFT:
                        case 70:
                            {
                            alt68=3;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("", 68, 4, input);

                            throw nvae;
                        }

                    }
                    else if ( (LA68_2==INT||LA68_2==LPAREN||(LA68_2>=TRUE && LA68_2<=FALSE)||(LA68_2>=PLUS && LA68_2<=MINUS)||LA68_2==NEGATION||LA68_2==BITWISE_NEGATION) ) {
                        alt68=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 68, 2, input);

                        throw nvae;
                    }
                }
                else if ( (LA68_1==70) ) {
                    alt68=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 68, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 68, 0, input);

                throw nvae;
            }
            switch (alt68) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1487:10: var1= ID '=' var2= ID
                    {

                        			assign = new LinkedList<VarExpr>();
                        		
                    var1=(Token)match(input,ID,FOLLOW_ID_in_assignment2516); 
                    match(input,EQUALS,FOLLOW_EQUALS_in_assignment2518); 
                    var2=(Token)match(input,ID,FOLLOW_ID_in_assignment2522); 

                        			// make sure only global, internal and output variables are assigned
                        			if(GlobalConstHashMap.containsKey((var1!=null?var1.getText():null))){
                        				error("error on line " + var1.getLine() + ": global constant " + (var1!=null?var1.getText():null) + " cannot be assigned");
                        			}
                        			else if(ConstHashMap.containsKey((var1!=null?var1.getText():null))){
                        				error("error on line " + var1.getLine() + ": constant " + (var1!=null?var1.getText():null) + " cannot be assigned");
                        			}
                        			else if(!VarNodeMap.containsKey((var1!=null?var1.getText():null))){
                        				error("error on line " + var1.getLine() + ": variable " + (var1!=null?var1.getText():null) + " was not declared");
                        			}
                        			else if(!Internals.contains((var!=null?var.getText():null))){
                    					VarNode node = VarNodeMap.get((var1!=null?var1.getText():null));
                    					node.setShared();
                        			}
                        			
                    				ExpressionNode node2 = null;
                        			if(GlobalConstHashMap.containsKey((var2!=null?var2.getText():null))){
                        				node2 = new ConstNode((var2!=null?var2.getText():null), GlobalConstHashMap.get((var2!=null?var2.getText():null)));
                        			}
                        			else if(ConstHashMap.containsKey((var2!=null?var2.getText():null))){
                        				node2 = new ConstNode((var2!=null?var2.getText():null), ConstHashMap.get((var2!=null?var2.getText():null)));
                        			}
                        			else if(VarNodeMap.containsKey((var2!=null?var2.getText():null))){
                        				node2 = VarNodeMap.get((var2!=null?var2.getText():null));
                        			}
                        			else{
                        				error("error on line " + var2.getLine() + ": variable " + (var2!=null?var2.getText():null) + " was not declared");
                        			}
                    	    		
                    	    		VarNode node1 = VarNodeMap.get((var1!=null?var1.getText():null));
                    	    		
                    	    		// make sure var2 can be assigned to var1
                    	    		if(ArrayNode.class.isAssignableFrom(node1.getClass())){
                    	    			if(!ArrayNode.class.isAssignableFrom(node2.getClass())){
                    	   					error("aaerror on line " + var2.getLine() + ": variable " + (var2!=null?var2.getText():null) + " is an array");
                       					}
                       					
                       					ArrayNode arrayNode1 = (ArrayNode) node1;
                       					ArrayNode arrayNode2 = (ArrayNode) node2;
                       					
                       					List<Integer> dimensionList1 = arrayNode1.getDimensionList();
                       					List<Integer> dimensionList2 = arrayNode2.getDimensionList();
                       					
                       					// make sure the number of dimensions match
                       					if(dimensionList1.size() != dimensionList2.size()){
                       						error("error on line " + var1.getLine() + ": incompatible array dimensions");
                       					}
                       					
                       					// make sure each dimension size matches
                       					for(int i = 0; i < dimensionList1.size(); i++){
                       						if(dimensionList1.get(i) != dimensionList2.get(i)){
                       							error("error on line " + var1.getLine() + ": incompatible array dimensions");
                       						}
                       					}
                       					
                       					List<VarNode> elementNodes1 = arrayNode1.getElementList();
                       					List<VarNode> elementNodes2 = arrayNode2.getElementList();
                       					
                       					// assign each associated array element
                       					for(int i = 0; i < elementNodes1.size(); i++){
                       						VarNode element1 = elementNodes1.get(i);
                       						VarNode element2 = elementNodes2.get(i);
                       						
                       						Expression expr = new Expression(element2);
                    	    				assign.add(new VarExpr(element1, expr));
                       					}
                       				}
                       				else if(ArrayNode.class.isAssignableFrom(node2.getClass())){
                       					error("bberror on line " + var2.getLine() + ": variable " + (var2!=null?var2.getText():null) + " is an array");
                       				}
                       				else{
                       					// non-array variable assignment
                       					Expression expr = new Expression(node2);
                    	    			assign.add(new VarExpr(node1, expr));
                       				}
                    	    		
                    	    		ClassReadSet.add((var2!=null?var2.getText():null));
                    	    		ClassWriteSet.add((var1!=null?var1.getText():null));
                        		

                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1572:7: (arrayVar= ID ( '[' (arrayExpr= expression ) ']' )+ '=' assignExpr= expression ';' )
                    {

                        			assign = new LinkedList<VarExpr>();
                        		
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1575:3: (arrayVar= ID ( '[' (arrayExpr= expression ) ']' )+ '=' assignExpr= expression ';' )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1575:4: arrayVar= ID ( '[' (arrayExpr= expression ) ']' )+ '=' assignExpr= expression ';'
                    {
                    arrayVar=(Token)match(input,ID,FOLLOW_ID_in_assignment2554); 

                    	   			List<Expression> indexList = new ArrayList<Expression>();
                    	   			
                        			if(GlobalConstHashMap.containsKey((arrayVar!=null?arrayVar.getText():null))){
                        				error("error on line " + arrayVar.getLine() + ": global constant " + (arrayVar!=null?arrayVar.getText():null) + " cannot be assigned");
                        			}
                        			else if(ConstHashMap.containsKey((arrayVar!=null?arrayVar.getText():null))){
                        				error("error on line " + arrayVar.getLine() + ": constant " + (arrayVar!=null?arrayVar.getText():null) + " cannot be assigned");
                        			}
                        			else if(!VarNodeMap.containsKey((arrayVar!=null?arrayVar.getText():null))){
                        				error("error on line " + arrayVar.getLine() + ": variable " + (arrayVar!=null?arrayVar.getText():null) + " was not declared");
                        			}
                        			else if(!Internals.contains((arrayVar!=null?arrayVar.getText():null))){
                    					VarNode node = VarNodeMap.get((arrayVar!=null?arrayVar.getText():null));
                    					node.setShared();
                        			}
                    	   		
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1593:6: ( '[' (arrayExpr= expression ) ']' )+
                    int cnt67=0;
                    loop67:
                    do {
                        int alt67=2;
                        int LA67_0 = input.LA(1);

                        if ( (LA67_0==70) ) {
                            alt67=1;
                        }


                        switch (alt67) {
                    	case 1 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1593:7: '[' (arrayExpr= expression ) ']'
                    	    {
                    	    match(input,70,FOLLOW_70_in_assignment2571); 
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1593:11: (arrayExpr= expression )
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1593:12: arrayExpr= expression
                    	    {
                    	    pushFollow(FOLLOW_expression_in_assignment2576);
                    	    arrayExpr=expression();

                    	    state._fsp--;


                    	        			ExpressionNode node = (arrayExpr!=null?arrayExpr.expr:null);
                    	    				indexList.add(new Expression(node));
                    	    	   		

                    	    }

                    	    match(input,71,FOLLOW_71_in_assignment2593); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt67 >= 1 ) break loop67;
                                EarlyExitException eee =
                                    new EarlyExitException(67, input);
                                throw eee;
                        }
                        cnt67++;
                    } while (true);

                    match(input,EQUALS,FOLLOW_EQUALS_in_assignment2597); 
                    pushFollow(FOLLOW_expression_in_assignment2601);
                    assignExpr=expression();

                    state._fsp--;


                    	    		Expression expr = new Expression((assignExpr!=null?assignExpr.expr:null));
                    	    		VarNode arrayNode = VarNodeMap.get((arrayVar!=null?arrayVar.getText():null));
                    	    		if(!ArrayNode.class.isAssignableFrom(arrayNode.getClass())){
                    	    			error("dderror on line " + arrayVar.getLine() + ": " + (arrayVar!=null?arrayVar.getText():null) + " is not an array");
                    	    		}
                    	    		
                    	    		assign.add(new VarExpr(new ArrayElement((ArrayNode) arrayNode, indexList), expr));
                    	    		
                    	    		ClassWriteSet.add((arrayVar!=null?arrayVar.getText():null));
                    	   		
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_assignment2616); 

                    }


                    }
                    break;
                case 3 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1611:5: (var= ID '=' varExpr= expression ';' )
                    {

                        			assign = new LinkedList<VarExpr>();
                        		
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1614:6: (var= ID '=' varExpr= expression ';' )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1614:7: var= ID '=' varExpr= expression ';'
                    {
                    var=(Token)match(input,ID,FOLLOW_ID_in_assignment2633); 
                    match(input,EQUALS,FOLLOW_EQUALS_in_assignment2635); 
                    	
                        			if(GlobalConstHashMap.containsKey((var!=null?var.getText():null))){
                        				error("error on line " + var.getLine() + ": global constant " + (var!=null?var.getText():null) + " cannot be assigned");
                        			}
                        			else if(ConstHashMap.containsKey((var!=null?var.getText():null))){
                        				error("error on line " + var.getLine() + ": constant " + (var!=null?var.getText():null) + " cannot be assigned");
                        			}
                        			else if(!VarNodeMap.containsKey((var!=null?var.getText():null))){
                        				error("error on line " + var.getLine() + ": variable " + (var!=null?var.getText():null) + " was not declared");
                        			}
                        			else if(!Internals.contains((var!=null?var.getText():null))){
                    					VarNode node = VarNodeMap.get((var!=null?var.getText():null));
                    					node.setShared();
                        			}
                        		
                    pushFollow(FOLLOW_expression_in_assignment2653);
                    varExpr=expression();

                    state._fsp--;


                    	    		Expression expr = new Expression((varExpr!=null?varExpr.expr:null));
                    	    		VarNode node = VarNodeMap.get((var!=null?var.getText():null));
                    	    		if(ArrayNode.class.isAssignableFrom(node.getClass())){
                       					error("ccerror on line " + var.getLine() + ": variable " + (var!=null?var.getText():null) + " is an array");
                       				}
                       				
                    	    		assign.add(new VarExpr(node, expr));
                    	    		ClassWriteSet.add((var!=null?var.getText():null));
                    	   		
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_assignment2668); 

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

    public static class term_return extends ParserRuleReturnScope {
        public ExpressionNode expr;
        public int value;
    };

    // $ANTLR start "term"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1647:1: term returns [ExpressionNode expr, int value] : (var= ID | (array= ID ( '[' (arrayExpr= expression ) ']' )+ ) | LPAREN expression RPAREN | INT | TRUE | FALSE );
    public final PlatuInstParser.term_return term() throws RecognitionException {
        PlatuInstParser.term_return retval = new PlatuInstParser.term_return();
        retval.start = input.LT(1);

        Token var=null;
        Token array=null;
        Token INT17=null;
        PlatuInstParser.expression_return arrayExpr = null;

        PlatuInstParser.expression_return expression16 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1648:5: (var= ID | (array= ID ( '[' (arrayExpr= expression ) ']' )+ ) | LPAREN expression RPAREN | INT | TRUE | FALSE )
            int alt70=6;
            switch ( input.LA(1) ) {
            case ID:
                {
                int LA70_1 = input.LA(2);

                if ( (LA70_1==RPAREN||(LA70_1>=QMARK && LA70_1<=SEMICOLON)||(LA70_1>=PLUS && LA70_1<=MOD)||(LA70_1>=GREATER && LA70_1<=NOT_EQUIV)||(LA70_1>=AND && LA70_1<=IMPLICATION)||(LA70_1>=BITWISE_AND && LA70_1<=BITWISE_RSHIFT)||LA70_1==71) ) {
                    alt70=1;
                }
                else if ( (LA70_1==70) ) {
                    alt70=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 70, 1, input);

                    throw nvae;
                }
                }
                break;
            case LPAREN:
                {
                alt70=3;
                }
                break;
            case INT:
                {
                alt70=4;
                }
                break;
            case TRUE:
                {
                alt70=5;
                }
                break;
            case FALSE:
                {
                alt70=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 70, 0, input);

                throw nvae;
            }

            switch (alt70) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1648:9: var= ID
                    {
                    var=(Token)match(input,ID,FOLLOW_ID_in_term2700); 

                        			if(ConstHashMap.containsKey((var!=null?var.getText():null))){
                        				retval.value = ConstHashMap.get((var!=null?var.getText():null));
                        				retval.expr = new ConstNode((var!=null?var.getText():null), retval.value);
                        			}
                        			else if(GlobalConstHashMap.containsKey((var!=null?var.getText():null))){
                        				retval.value = GlobalConstHashMap.get((var!=null?var.getText():null));
                        				retval.expr = new ConstNode((var!=null?var.getText():null), retval.value);
                        			}
                        			else if(StatevectorMap.containsKey((var!=null?var.getText():null))){ 
                        				retval.value = StatevectorMap.get((var!=null?var.getText():null));
                    					retval.expr = VarNodeMap.get((var!=null?var.getText():null));
                        			}
                        			else if(VarNodeMap.containsKey((var!=null?var.getText():null))){
                       					error("error on line " + var.getLine() + ": variable " + (var!=null?var.getText():null) + " is an array");
                        			}
                        			else{
                    					error("error on line " + var.getLine() + ": variable " + (var!=null?var.getText():null) + " is not valid");
                        			}
                        			
                        			ClassReadSet.add((var!=null?var.getText():null));
                       			

                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1671:6: (array= ID ( '[' (arrayExpr= expression ) ']' )+ )
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1671:6: (array= ID ( '[' (arrayExpr= expression ) ']' )+ )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1671:7: array= ID ( '[' (arrayExpr= expression ) ']' )+
                    {
                    array=(Token)match(input,ID,FOLLOW_ID_in_term2719); 

                      				List<Expression> indexList = new ArrayList<Expression>();
                      				List<Integer> valueList = new ArrayList<Integer>();
                      				VarNode arrayNode = null;
                      				
                      				if(!VarNodeMap.containsKey((array!=null?array.getText():null))){
                      					error("error on line " + array.getLine() + ": " + (array!=null?array.getText():null) + " is not a valid array");
                      				}
                      				
                      				arrayNode = VarNodeMap.get((array!=null?array.getText():null));
                      				if(!ArrayNode.class.isAssignableFrom(arrayNode.getClass())){
                       					error("error on line " + array.getLine() + ": " + (array!=null?array.getText():null) + " is not a valid array");
                       				}
                       			
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1686:6: ( '[' (arrayExpr= expression ) ']' )+
                    int cnt69=0;
                    loop69:
                    do {
                        int alt69=2;
                        int LA69_0 = input.LA(1);

                        if ( (LA69_0==70) ) {
                            alt69=1;
                        }


                        switch (alt69) {
                    	case 1 :
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1686:7: '[' (arrayExpr= expression ) ']'
                    	    {
                    	    match(input,70,FOLLOW_70_in_term2735); 
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1686:11: (arrayExpr= expression )
                    	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1686:12: arrayExpr= expression
                    	    {
                    	    pushFollow(FOLLOW_expression_in_term2740);
                    	    arrayExpr=expression();

                    	    state._fsp--;


                    	      				ExpressionNode node = (arrayExpr!=null?arrayExpr.expr:null);
                    	      				indexList.add(new Expression(node));
                    	      				valueList.add((arrayExpr!=null?arrayExpr.value:0));
                    	      			

                    	    }

                    	    match(input,71,FOLLOW_71_in_term2755); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt69 >= 1 ) break loop69;
                                EarlyExitException eee =
                                    new EarlyExitException(69, input);
                                throw eee;
                        }
                        cnt69++;
                    } while (true);


                      				ArrayNode arrayVar = (ArrayNode) arrayNode;
                      				if(valueList.size() != arrayVar.numDimensions()){
                      					error("error on line " + array.getLine() + ": invalid number of dimensions");
                      				}
                      				
                      				ExpressionError errorCode = ExpressionError.NONE;
                      				String name = ((ArrayNode) arrayNode).getElement(valueList, errorCode).getName();
                      				retval.value = StatevectorMap.get(name);
                      				retval.expr = new ArrayElement((ArrayNode) arrayNode, indexList);
                      				ClassReadSet.add((array!=null?array.getText():null));
                      			

                    }


                    }
                    break;
                case 3 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1706:9: LPAREN expression RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_term2780); 
                    pushFollow(FOLLOW_expression_in_term2782);
                    expression16=expression();

                    state._fsp--;

                    match(input,RPAREN,FOLLOW_RPAREN_in_term2784); 
                    retval.expr = new ParenNode((expression16!=null?expression16.expr:null)); retval.value = (expression16!=null?expression16.value:0);

                    }
                    break;
                case 4 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1707:9: INT
                    {
                    INT17=(Token)match(input,INT,FOLLOW_INT_in_term2796); 
                    retval.value = Integer.parseInt((INT17!=null?INT17.getText():null)); retval.expr = new ConstNode("name", retval.value);

                    }
                    break;
                case 5 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1708:9: TRUE
                    {
                    match(input,TRUE,FOLLOW_TRUE_in_term2808); 
                    retval.expr = new TrueNode(); retval.value = 1;

                    }
                    break;
                case 6 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1709:9: FALSE
                    {
                    match(input,FALSE,FOLLOW_FALSE_in_term2820); 
                    retval.expr = new FalseNode(); retval.value = 0;

                    }
                    break;

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
    // $ANTLR end "term"

    public static class unary_return extends ParserRuleReturnScope {
        public ExpressionNode expr;
        public int value;
    };

    // $ANTLR start "unary"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1712:1: unary returns [ExpressionNode expr, int value] : ( '+' | ( '-' ) )* term ;
    public final PlatuInstParser.unary_return unary() throws RecognitionException {
        PlatuInstParser.unary_return retval = new PlatuInstParser.unary_return();
        retval.start = input.LT(1);

        PlatuInstParser.term_return term18 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1713:5: ( ( '+' | ( '-' ) )* term )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1713:9: ( '+' | ( '-' ) )* term
            {
            boolean positive = true;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1714:6: ( '+' | ( '-' ) )*
            loop71:
            do {
                int alt71=3;
                int LA71_0 = input.LA(1);

                if ( (LA71_0==PLUS) ) {
                    alt71=1;
                }
                else if ( (LA71_0==MINUS) ) {
                    alt71=2;
                }


                switch (alt71) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1714:7: '+'
            	    {
            	    match(input,PLUS,FOLLOW_PLUS_in_unary2857); 

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1714:13: ( '-' )
            	    {
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1714:13: ( '-' )
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1714:14: '-'
            	    {
            	    match(input,MINUS,FOLLOW_MINUS_in_unary2862); 
            	    if(positive){ positive = false;} else {positive = true;}

            	    }


            	    }
            	    break;

            	default :
            	    break loop71;
                }
            } while (true);

            pushFollow(FOLLOW_term_in_unary2869);
            term18=term();

            state._fsp--;


            	    		if(!positive){
            	    			retval.expr = new MinNode((term18!=null?term18.expr:null));
            	    			retval.value = -(term18!=null?term18.value:0);
            	    		}
            	    		else{
            	    			retval.expr = (term18!=null?term18.expr:null);
            	    			retval.value = (term18!=null?term18.value:0);
            	   			}
            	    	

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
    // $ANTLR end "unary"

    public static class bitwiseNegation_return extends ParserRuleReturnScope {
        public ExpressionNode expr;
        public int value;
    };

    // $ANTLR start "bitwiseNegation"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1727:1: bitwiseNegation returns [ExpressionNode expr, int value] : ( '~' )* unary ;
    public final PlatuInstParser.bitwiseNegation_return bitwiseNegation() throws RecognitionException {
        PlatuInstParser.bitwiseNegation_return retval = new PlatuInstParser.bitwiseNegation_return();
        retval.start = input.LT(1);

        PlatuInstParser.unary_return unary19 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1728:2: ( ( '~' )* unary )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1728:5: ( '~' )* unary
            {
            boolean neg = false;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1729:3: ( '~' )*
            loop72:
            do {
                int alt72=2;
                int LA72_0 = input.LA(1);

                if ( (LA72_0==BITWISE_NEGATION) ) {
                    alt72=1;
                }


                switch (alt72) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1729:4: '~'
            	    {
            	    match(input,BITWISE_NEGATION,FOLLOW_BITWISE_NEGATION_in_bitwiseNegation2901); 
            	    if(neg){neg = false;} else{neg = true;}

            	    }
            	    break;

            	default :
            	    break loop72;
                }
            } while (true);

            pushFollow(FOLLOW_unary_in_bitwiseNegation2907);
            unary19=unary();

            state._fsp--;


            				if(neg){
            					retval.expr = new BitNegNode((unary19!=null?unary19.expr:null));
            					retval.value = ~(unary19!=null?unary19.value:0);
            				}
            				else{
            					retval.expr = (unary19!=null?unary19.expr:null);
            					retval.value = (unary19!=null?unary19.value:0);
            				}
            			

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
    // $ANTLR end "bitwiseNegation"

    public static class negation_return extends ParserRuleReturnScope {
        public ExpressionNode expr;
        public int value;
    };

    // $ANTLR start "negation"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1742:1: negation returns [ExpressionNode expr, int value] : ( '!' )* bitwiseNegation ;
    public final PlatuInstParser.negation_return negation() throws RecognitionException {
        PlatuInstParser.negation_return retval = new PlatuInstParser.negation_return();
        retval.start = input.LT(1);

        PlatuInstParser.bitwiseNegation_return bitwiseNegation20 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1743:2: ( ( '!' )* bitwiseNegation )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1743:4: ( '!' )* bitwiseNegation
            {
            boolean neg = false;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1744:3: ( '!' )*
            loop73:
            do {
                int alt73=2;
                int LA73_0 = input.LA(1);

                if ( (LA73_0==NEGATION) ) {
                    alt73=1;
                }


                switch (alt73) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1744:4: '!'
            	    {
            	    match(input,NEGATION,FOLLOW_NEGATION_in_negation2933); 
            	    if(neg){neg = false;} else{neg = true;}

            	    }
            	    break;

            	default :
            	    break loop73;
                }
            } while (true);

            pushFollow(FOLLOW_bitwiseNegation_in_negation2939);
            bitwiseNegation20=bitwiseNegation();

            state._fsp--;


            				if(neg){
            					retval.expr = new NegNode((bitwiseNegation20!=null?bitwiseNegation20.expr:null));
            					retval.value = (bitwiseNegation20!=null?bitwiseNegation20.value:0) == 0 ? 1 : 0;
            				}
            				else{
            					retval.expr = (bitwiseNegation20!=null?bitwiseNegation20.expr:null);
            					retval.value = (bitwiseNegation20!=null?bitwiseNegation20.value:0);
            				}
            			

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
    // $ANTLR end "negation"

    public static class mult_return extends ParserRuleReturnScope {
        public ExpressionNode expr;
        public int value;
    };

    // $ANTLR start "mult"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1757:1: mult returns [ExpressionNode expr, int value] : op1= negation ( '*' op2= negation | '/' op2= negation | '%' op2= negation )* ;
    public final PlatuInstParser.mult_return mult() throws RecognitionException {
        PlatuInstParser.mult_return retval = new PlatuInstParser.mult_return();
        retval.start = input.LT(1);

        PlatuInstParser.negation_return op1 = null;

        PlatuInstParser.negation_return op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1758:5: (op1= negation ( '*' op2= negation | '/' op2= negation | '%' op2= negation )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1758:9: op1= negation ( '*' op2= negation | '/' op2= negation | '%' op2= negation )*
            {
            pushFollow(FOLLOW_negation_in_mult2967);
            op1=negation();

            state._fsp--;

            retval.expr = (op1!=null?op1.expr:null); retval.value = (op1!=null?op1.value:0);
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1759:6: ( '*' op2= negation | '/' op2= negation | '%' op2= negation )*
            loop74:
            do {
                int alt74=4;
                switch ( input.LA(1) ) {
                case TIMES:
                    {
                    alt74=1;
                    }
                    break;
                case DIV:
                    {
                    alt74=2;
                    }
                    break;
                case MOD:
                    {
                    alt74=3;
                    }
                    break;

                }

                switch (alt74) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1759:8: '*' op2= negation
            	    {
            	    match(input,TIMES,FOLLOW_TIMES_in_mult2979); 
            	    pushFollow(FOLLOW_negation_in_mult2983);
            	    op2=negation();

            	    state._fsp--;

            	    retval.expr = new MultNode(retval.expr, (op2!=null?op2.expr:null)); retval.value = retval.value * (op2!=null?op2.value:0);

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1760:8: '/' op2= negation
            	    {
            	    match(input,DIV,FOLLOW_DIV_in_mult2994); 
            	    pushFollow(FOLLOW_negation_in_mult2998);
            	    op2=negation();

            	    state._fsp--;

            	    retval.expr = new DivNode(retval.expr, (op2!=null?op2.expr:null)); retval.value = retval.value / (op2!=null?op2.value:0);

            	    }
            	    break;
            	case 3 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1761:8: '%' op2= negation
            	    {
            	    match(input,MOD,FOLLOW_MOD_in_mult3009); 
            	    pushFollow(FOLLOW_negation_in_mult3013);
            	    op2=negation();

            	    state._fsp--;

            	    retval.expr = new ModNode(retval.expr, (op2!=null?op2.expr:null)); retval.value = retval.value % (op2!=null?op2.value:0);

            	    }
            	    break;

            	default :
            	    break loop74;
                }
            } while (true);


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
    // $ANTLR end "mult"

    public static class add_return extends ParserRuleReturnScope {
        public ExpressionNode expr;
        public int value;
    };

    // $ANTLR start "add"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1765:1: add returns [ExpressionNode expr, int value] : op1= mult ( '+' op2= mult | '-' op2= mult )* ;
    public final PlatuInstParser.add_return add() throws RecognitionException {
        PlatuInstParser.add_return retval = new PlatuInstParser.add_return();
        retval.start = input.LT(1);

        PlatuInstParser.mult_return op1 = null;

        PlatuInstParser.mult_return op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1766:5: (op1= mult ( '+' op2= mult | '-' op2= mult )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1766:9: op1= mult ( '+' op2= mult | '-' op2= mult )*
            {
            pushFollow(FOLLOW_mult_in_add3052);
            op1=mult();

            state._fsp--;

            retval.expr = (op1!=null?op1.expr:null); retval.value = (op1!=null?op1.value:0);
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1767:6: ( '+' op2= mult | '-' op2= mult )*
            loop75:
            do {
                int alt75=3;
                int LA75_0 = input.LA(1);

                if ( (LA75_0==PLUS) ) {
                    alt75=1;
                }
                else if ( (LA75_0==MINUS) ) {
                    alt75=2;
                }


                switch (alt75) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1767:8: '+' op2= mult
            	    {
            	    match(input,PLUS,FOLLOW_PLUS_in_add3063); 
            	    pushFollow(FOLLOW_mult_in_add3067);
            	    op2=mult();

            	    state._fsp--;

            	    retval.expr = new AddNode(retval.expr, (op2!=null?op2.expr:null)); retval.value = retval.value + (op2!=null?op2.value:0);

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1768:9: '-' op2= mult
            	    {
            	    match(input,MINUS,FOLLOW_MINUS_in_add3079); 
            	    pushFollow(FOLLOW_mult_in_add3083);
            	    op2=mult();

            	    state._fsp--;

            	    retval.expr = new SubNode(retval.expr, (op2!=null?op2.expr:null)); retval.value = retval.value - (op2!=null?op2.value:0);

            	    }
            	    break;

            	default :
            	    break loop75;
                }
            } while (true);


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
    // $ANTLR end "add"

    public static class shift_return extends ParserRuleReturnScope {
        public ExpressionNode expr;
        public int value;
    };

    // $ANTLR start "shift"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1772:1: shift returns [ExpressionNode expr, int value] : op1= add ( '<<' op2= add | '>>' op2= add )* ;
    public final PlatuInstParser.shift_return shift() throws RecognitionException {
        PlatuInstParser.shift_return retval = new PlatuInstParser.shift_return();
        retval.start = input.LT(1);

        PlatuInstParser.add_return op1 = null;

        PlatuInstParser.add_return op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1773:5: (op1= add ( '<<' op2= add | '>>' op2= add )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1773:9: op1= add ( '<<' op2= add | '>>' op2= add )*
            {
            pushFollow(FOLLOW_add_in_shift3122);
            op1=add();

            state._fsp--;

            retval.expr = (op1!=null?op1.expr:null); retval.value = (op1!=null?op1.value:0);
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1774:6: ( '<<' op2= add | '>>' op2= add )*
            loop76:
            do {
                int alt76=3;
                int LA76_0 = input.LA(1);

                if ( (LA76_0==BITWISE_LSHIFT) ) {
                    alt76=1;
                }
                else if ( (LA76_0==BITWISE_RSHIFT) ) {
                    alt76=2;
                }


                switch (alt76) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1774:8: '<<' op2= add
            	    {
            	    match(input,BITWISE_LSHIFT,FOLLOW_BITWISE_LSHIFT_in_shift3133); 
            	    pushFollow(FOLLOW_add_in_shift3137);
            	    op2=add();

            	    state._fsp--;

            	    retval.expr = new LeftShiftNode(retval.expr, (op2!=null?op2.expr:null)); retval.value = retval.value << (op2!=null?op2.value:0);

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1775:9: '>>' op2= add
            	    {
            	    match(input,BITWISE_RSHIFT,FOLLOW_BITWISE_RSHIFT_in_shift3149); 
            	    pushFollow(FOLLOW_add_in_shift3153);
            	    op2=add();

            	    state._fsp--;

            	    retval.expr = new RightShiftNode(retval.expr, (op2!=null?op2.expr:null)); retval.value = retval.value >> (op2!=null?op2.value:0);

            	    }
            	    break;

            	default :
            	    break loop76;
                }
            } while (true);


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
    // $ANTLR end "shift"

    public static class relation_return extends ParserRuleReturnScope {
        public ExpressionNode expr;
        public int value;
    };

    // $ANTLR start "relation"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1779:1: relation returns [ExpressionNode expr, int value] : op1= shift ( '<' op2= shift | '<=' op2= shift | '>=' op2= shift | '>' op2= shift )* ;
    public final PlatuInstParser.relation_return relation() throws RecognitionException {
        PlatuInstParser.relation_return retval = new PlatuInstParser.relation_return();
        retval.start = input.LT(1);

        PlatuInstParser.shift_return op1 = null;

        PlatuInstParser.shift_return op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1780:5: (op1= shift ( '<' op2= shift | '<=' op2= shift | '>=' op2= shift | '>' op2= shift )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1780:9: op1= shift ( '<' op2= shift | '<=' op2= shift | '>=' op2= shift | '>' op2= shift )*
            {
            pushFollow(FOLLOW_shift_in_relation3188);
            op1=shift();

            state._fsp--;

            retval.expr = (op1!=null?op1.expr:null); retval.value = (op1!=null?op1.value:0);
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1781:6: ( '<' op2= shift | '<=' op2= shift | '>=' op2= shift | '>' op2= shift )*
            loop77:
            do {
                int alt77=5;
                switch ( input.LA(1) ) {
                case LESS:
                    {
                    alt77=1;
                    }
                    break;
                case LESS_EQUAL:
                    {
                    alt77=2;
                    }
                    break;
                case GREATER_EQUAL:
                    {
                    alt77=3;
                    }
                    break;
                case GREATER:
                    {
                    alt77=4;
                    }
                    break;

                }

                switch (alt77) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1781:8: '<' op2= shift
            	    {
            	    match(input,LESS,FOLLOW_LESS_in_relation3199); 
            	    pushFollow(FOLLOW_shift_in_relation3203);
            	    op2=shift();

            	    state._fsp--;

            	    retval.expr = new LessNode(retval.expr, (op2!=null?op2.expr:null)); retval.value = (retval.value < (op2!=null?op2.value:0)) ? 1 : 0;

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1782:9: '<=' op2= shift
            	    {
            	    match(input,LESS_EQUAL,FOLLOW_LESS_EQUAL_in_relation3215); 
            	    pushFollow(FOLLOW_shift_in_relation3219);
            	    op2=shift();

            	    state._fsp--;

            	    retval.expr = new LessEqualNode(retval.expr, (op2!=null?op2.expr:null)); retval.value = (retval.value <= (op2!=null?op2.value:0)) ? 1 : 0;

            	    }
            	    break;
            	case 3 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1783:9: '>=' op2= shift
            	    {
            	    match(input,GREATER_EQUAL,FOLLOW_GREATER_EQUAL_in_relation3231); 
            	    pushFollow(FOLLOW_shift_in_relation3235);
            	    op2=shift();

            	    state._fsp--;

            	    retval.expr = new GreatEqualNode(retval.expr, (op2!=null?op2.expr:null)); retval.value = (retval.value >= (op2!=null?op2.value:0)) ? 1 : 0;

            	    }
            	    break;
            	case 4 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1784:9: '>' op2= shift
            	    {
            	    match(input,GREATER,FOLLOW_GREATER_in_relation3247); 
            	    pushFollow(FOLLOW_shift_in_relation3251);
            	    op2=shift();

            	    state._fsp--;

            	    retval.expr = new GreatNode(retval.expr, (op2!=null?op2.expr:null)); retval.value = (retval.value > (op2!=null?op2.value:0)) ? 1 : 0;

            	    }
            	    break;

            	default :
            	    break loop77;
                }
            } while (true);


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
    // $ANTLR end "relation"

    public static class equivalence_return extends ParserRuleReturnScope {
        public ExpressionNode expr;
        public int value;
    };

    // $ANTLR start "equivalence"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1788:1: equivalence returns [ExpressionNode expr, int value] : op1= relation ( '==' op2= relation | '!=' op2= relation )* ;
    public final PlatuInstParser.equivalence_return equivalence() throws RecognitionException {
        PlatuInstParser.equivalence_return retval = new PlatuInstParser.equivalence_return();
        retval.start = input.LT(1);

        PlatuInstParser.relation_return op1 = null;

        PlatuInstParser.relation_return op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1789:5: (op1= relation ( '==' op2= relation | '!=' op2= relation )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1789:9: op1= relation ( '==' op2= relation | '!=' op2= relation )*
            {
            pushFollow(FOLLOW_relation_in_equivalence3290);
            op1=relation();

            state._fsp--;

            retval.expr = (op1!=null?op1.expr:null); retval.value = (op1!=null?op1.value:0);
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1790:6: ( '==' op2= relation | '!=' op2= relation )*
            loop78:
            do {
                int alt78=3;
                int LA78_0 = input.LA(1);

                if ( (LA78_0==EQUIV) ) {
                    alt78=1;
                }
                else if ( (LA78_0==NOT_EQUIV) ) {
                    alt78=2;
                }


                switch (alt78) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1790:8: '==' op2= relation
            	    {
            	    match(input,EQUIV,FOLLOW_EQUIV_in_equivalence3301); 
            	    pushFollow(FOLLOW_relation_in_equivalence3305);
            	    op2=relation();

            	    state._fsp--;

            	    retval.expr = new EquivNode(retval.expr, (op2!=null?op2.expr:null)); retval.value = (retval.value == (op2!=null?op2.value:0)) ? 1 : 0;

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1791:8: '!=' op2= relation
            	    {
            	    match(input,NOT_EQUIV,FOLLOW_NOT_EQUIV_in_equivalence3316); 
            	    pushFollow(FOLLOW_relation_in_equivalence3320);
            	    op2=relation();

            	    state._fsp--;

            	    retval.expr = new NotEquivNode(retval.expr, (op2!=null?op2.expr:null)); retval.value = (retval.value != (op2!=null?op2.value:0)) ? 1 : 0;

            	    }
            	    break;

            	default :
            	    break loop78;
                }
            } while (true);


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
    // $ANTLR end "equivalence"

    public static class bitwiseAnd_return extends ParserRuleReturnScope {
        public ExpressionNode expr;
        public int value;
    };

    // $ANTLR start "bitwiseAnd"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1795:1: bitwiseAnd returns [ExpressionNode expr, int value] : op1= equivalence ( '&' op2= equivalence )* ;
    public final PlatuInstParser.bitwiseAnd_return bitwiseAnd() throws RecognitionException {
        PlatuInstParser.bitwiseAnd_return retval = new PlatuInstParser.bitwiseAnd_return();
        retval.start = input.LT(1);

        PlatuInstParser.equivalence_return op1 = null;

        PlatuInstParser.equivalence_return op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1796:5: (op1= equivalence ( '&' op2= equivalence )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1796:9: op1= equivalence ( '&' op2= equivalence )*
            {
            pushFollow(FOLLOW_equivalence_in_bitwiseAnd3359);
            op1=equivalence();

            state._fsp--;

            retval.expr = (op1!=null?op1.expr:null); retval.value = (op1!=null?op1.value:0);
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1797:6: ( '&' op2= equivalence )*
            loop79:
            do {
                int alt79=2;
                int LA79_0 = input.LA(1);

                if ( (LA79_0==BITWISE_AND) ) {
                    alt79=1;
                }


                switch (alt79) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1797:8: '&' op2= equivalence
            	    {
            	    match(input,BITWISE_AND,FOLLOW_BITWISE_AND_in_bitwiseAnd3371); 
            	    pushFollow(FOLLOW_equivalence_in_bitwiseAnd3375);
            	    op2=equivalence();

            	    state._fsp--;

            	    retval.expr = new BitAndNode(retval.expr, (op2!=null?op2.expr:null)); retval.value = retval.value & (op2!=null?op2.value:0);

            	    }
            	    break;

            	default :
            	    break loop79;
                }
            } while (true);


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
    // $ANTLR end "bitwiseAnd"

    public static class bitwiseXor_return extends ParserRuleReturnScope {
        public ExpressionNode expr;
        public int value;
    };

    // $ANTLR start "bitwiseXor"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1801:1: bitwiseXor returns [ExpressionNode expr, int value] : op1= bitwiseAnd ( '^' op2= bitwiseAnd )* ;
    public final PlatuInstParser.bitwiseXor_return bitwiseXor() throws RecognitionException {
        PlatuInstParser.bitwiseXor_return retval = new PlatuInstParser.bitwiseXor_return();
        retval.start = input.LT(1);

        PlatuInstParser.bitwiseAnd_return op1 = null;

        PlatuInstParser.bitwiseAnd_return op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1802:5: (op1= bitwiseAnd ( '^' op2= bitwiseAnd )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1802:9: op1= bitwiseAnd ( '^' op2= bitwiseAnd )*
            {
            pushFollow(FOLLOW_bitwiseAnd_in_bitwiseXor3414);
            op1=bitwiseAnd();

            state._fsp--;

            retval.expr = (op1!=null?op1.expr:null); retval.value = (op1!=null?op1.value:0);
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1803:6: ( '^' op2= bitwiseAnd )*
            loop80:
            do {
                int alt80=2;
                int LA80_0 = input.LA(1);

                if ( (LA80_0==BITWISE_XOR) ) {
                    alt80=1;
                }


                switch (alt80) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1803:8: '^' op2= bitwiseAnd
            	    {
            	    match(input,BITWISE_XOR,FOLLOW_BITWISE_XOR_in_bitwiseXor3425); 
            	    pushFollow(FOLLOW_bitwiseAnd_in_bitwiseXor3429);
            	    op2=bitwiseAnd();

            	    state._fsp--;

            	    retval.expr = new BitXorNode(retval.expr, (op2!=null?op2.expr:null)); retval.value = retval.value ^ (op2!=null?op2.value:0);

            	    }
            	    break;

            	default :
            	    break loop80;
                }
            } while (true);


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
    // $ANTLR end "bitwiseXor"

    public static class bitwiseOr_return extends ParserRuleReturnScope {
        public ExpressionNode expr;
        public int value;
    };

    // $ANTLR start "bitwiseOr"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1807:1: bitwiseOr returns [ExpressionNode expr, int value] : op1= bitwiseXor ( '|' op2= bitwiseXor )* ;
    public final PlatuInstParser.bitwiseOr_return bitwiseOr() throws RecognitionException {
        PlatuInstParser.bitwiseOr_return retval = new PlatuInstParser.bitwiseOr_return();
        retval.start = input.LT(1);

        PlatuInstParser.bitwiseXor_return op1 = null;

        PlatuInstParser.bitwiseXor_return op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1808:5: (op1= bitwiseXor ( '|' op2= bitwiseXor )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1808:9: op1= bitwiseXor ( '|' op2= bitwiseXor )*
            {
            pushFollow(FOLLOW_bitwiseXor_in_bitwiseOr3468);
            op1=bitwiseXor();

            state._fsp--;

            retval.expr = (op1!=null?op1.expr:null); retval.value = (op1!=null?op1.value:0);
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1809:6: ( '|' op2= bitwiseXor )*
            loop81:
            do {
                int alt81=2;
                int LA81_0 = input.LA(1);

                if ( (LA81_0==BITWISE_OR) ) {
                    alt81=1;
                }


                switch (alt81) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1809:8: '|' op2= bitwiseXor
            	    {
            	    match(input,BITWISE_OR,FOLLOW_BITWISE_OR_in_bitwiseOr3479); 
            	    pushFollow(FOLLOW_bitwiseXor_in_bitwiseOr3483);
            	    op2=bitwiseXor();

            	    state._fsp--;

            	    retval.expr = new BitOrNode(retval.expr, (op2!=null?op2.expr:null)); retval.value = retval.value | (op2!=null?op2.value:0);

            	    }
            	    break;

            	default :
            	    break loop81;
                }
            } while (true);


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
    // $ANTLR end "bitwiseOr"

    public static class and_return extends ParserRuleReturnScope {
        public ExpressionNode expr;
        public int value;
    };

    // $ANTLR start "and"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1813:1: and returns [ExpressionNode expr, int value] : op1= bitwiseOr ( '&&' op2= bitwiseOr )* ;
    public final PlatuInstParser.and_return and() throws RecognitionException {
        PlatuInstParser.and_return retval = new PlatuInstParser.and_return();
        retval.start = input.LT(1);

        PlatuInstParser.bitwiseOr_return op1 = null;

        PlatuInstParser.bitwiseOr_return op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1814:5: (op1= bitwiseOr ( '&&' op2= bitwiseOr )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1814:9: op1= bitwiseOr ( '&&' op2= bitwiseOr )*
            {
            pushFollow(FOLLOW_bitwiseOr_in_and3522);
            op1=bitwiseOr();

            state._fsp--;

            retval.expr = (op1!=null?op1.expr:null); retval.value = (op1!=null?op1.value:0);
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1815:6: ( '&&' op2= bitwiseOr )*
            loop82:
            do {
                int alt82=2;
                int LA82_0 = input.LA(1);

                if ( (LA82_0==AND) ) {
                    alt82=1;
                }


                switch (alt82) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1815:8: '&&' op2= bitwiseOr
            	    {
            	    match(input,AND,FOLLOW_AND_in_and3533); 
            	    pushFollow(FOLLOW_bitwiseOr_in_and3537);
            	    op2=bitwiseOr();

            	    state._fsp--;

            	    retval.expr = new AndNode(retval.expr, (op2!=null?op2.expr:null)); retval.value = (retval.value != 0 && (op2!=null?op2.value:0) != 0) ? 1 : 0;

            	    }
            	    break;

            	default :
            	    break loop82;
                }
            } while (true);


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
    // $ANTLR end "and"

    public static class or_return extends ParserRuleReturnScope {
        public ExpressionNode expr;
        public int value;
    };

    // $ANTLR start "or"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1819:1: or returns [ExpressionNode expr, int value] : op1= and ( '||' op2= and )* ;
    public final PlatuInstParser.or_return or() throws RecognitionException {
        PlatuInstParser.or_return retval = new PlatuInstParser.or_return();
        retval.start = input.LT(1);

        PlatuInstParser.and_return op1 = null;

        PlatuInstParser.and_return op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1820:5: (op1= and ( '||' op2= and )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1820:9: op1= and ( '||' op2= and )*
            {
            pushFollow(FOLLOW_and_in_or3576);
            op1=and();

            state._fsp--;

            retval.expr = (op1!=null?op1.expr:null); retval.value = (op1!=null?op1.value:0);
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1821:6: ( '||' op2= and )*
            loop83:
            do {
                int alt83=2;
                int LA83_0 = input.LA(1);

                if ( (LA83_0==OR) ) {
                    alt83=1;
                }


                switch (alt83) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1821:8: '||' op2= and
            	    {
            	    match(input,OR,FOLLOW_OR_in_or3587); 
            	    pushFollow(FOLLOW_and_in_or3591);
            	    op2=and();

            	    state._fsp--;

            	    retval.expr = new OrNode(retval.expr, (op2!=null?op2.expr:null)); retval.value = (retval.value != 0 || (op2!=null?op2.value:0) != 0) ? 1 : 0;

            	    }
            	    break;

            	default :
            	    break loop83;
                }
            } while (true);


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
    // $ANTLR end "or"

    public static class implication_return extends ParserRuleReturnScope {
        public ExpressionNode expr;
        public int value;
    };

    // $ANTLR start "implication"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1825:1: implication returns [ExpressionNode expr, int value] : op1= or ( '->' op2= or )* ;
    public final PlatuInstParser.implication_return implication() throws RecognitionException {
        PlatuInstParser.implication_return retval = new PlatuInstParser.implication_return();
        retval.start = input.LT(1);

        PlatuInstParser.or_return op1 = null;

        PlatuInstParser.or_return op2 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1826:5: (op1= or ( '->' op2= or )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1826:7: op1= or ( '->' op2= or )*
            {
            pushFollow(FOLLOW_or_in_implication3628);
            op1=or();

            state._fsp--;

            retval.expr = (op1!=null?op1.expr:null); retval.value = (op1!=null?op1.value:0);
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1827:6: ( '->' op2= or )*
            loop84:
            do {
                int alt84=2;
                int LA84_0 = input.LA(1);

                if ( (LA84_0==IMPLICATION) ) {
                    alt84=1;
                }


                switch (alt84) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1827:8: '->' op2= or
            	    {
            	    match(input,IMPLICATION,FOLLOW_IMPLICATION_in_implication3639); 
            	    pushFollow(FOLLOW_or_in_implication3643);
            	    op2=or();

            	    state._fsp--;

            	    retval.expr = new ImplicationNode(retval.expr, (op2!=null?op2.expr:null)); retval.value = (retval.value == 0 || (op2!=null?op2.value:0) != 0) ? 1 : 0;

            	    }
            	    break;

            	default :
            	    break loop84;
                }
            } while (true);


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
    // $ANTLR end "implication"

    public static class expression_return extends ParserRuleReturnScope {
        public ExpressionNode expr;
        public int value;
    };

    // $ANTLR start "expression"
    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1831:1: expression returns [ExpressionNode expr, int value] : op1= implication ( '?' op2= expression ':' op3= expression )? ;
    public final PlatuInstParser.expression_return expression() throws RecognitionException {
        PlatuInstParser.expression_return retval = new PlatuInstParser.expression_return();
        retval.start = input.LT(1);

        PlatuInstParser.implication_return op1 = null;

        PlatuInstParser.expression_return op2 = null;

        PlatuInstParser.expression_return op3 = null;


        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1832:5: (op1= implication ( '?' op2= expression ':' op3= expression )? )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1832:9: op1= implication ( '?' op2= expression ':' op3= expression )?
            {
            pushFollow(FOLLOW_implication_in_expression3683);
            op1=implication();

            state._fsp--;

            retval.expr = (op1!=null?op1.expr:null); retval.value = (op1!=null?op1.value:0);
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1833:6: ( '?' op2= expression ':' op3= expression )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==QMARK) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1833:7: '?' op2= expression ':' op3= expression
                    {
                    match(input,QMARK,FOLLOW_QMARK_in_expression3693); 
                    pushFollow(FOLLOW_expression_in_expression3697);
                    op2=expression();

                    state._fsp--;

                    match(input,COLON,FOLLOW_COLON_in_expression3699); 
                    pushFollow(FOLLOW_expression_in_expression3703);
                    op3=expression();

                    state._fsp--;


                        			retval.value = (retval.value != 0) ? (op2!=null?op2.value:0) : (op3!=null?op3.value:0);
                        			retval.expr = new TernaryNode(retval.expr, (op2!=null?op2.expr:null), (op3!=null?op3.expr:null));
                        		

                    }
                    break;

            }


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
    // $ANTLR end "expression"

    // Delegated rules


 

    public static final BitSet FOLLOW_include_in_parseLpnFile52 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_globalConstants_in_parseLpnFile55 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_main_in_parseLpnFile58 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_moduleClass_in_parseLpnFile63 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_EOF_in_parseLpnFile68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_include86 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_include88 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_GREATER_in_include90 = new BitSet(new long[]{0x0000020000000010L});
    public static final BitSet FOLLOW_PATH_in_include93 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_include95 = new BitSet(new long[]{0x0000020000000010L});
    public static final BitSet FOLLOW_LESS_in_include107 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_include109 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_GREATER_in_include111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_main124 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_MAIN_in_main126 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_GREATER_in_main128 = new BitSet(new long[]{0x0000020000000020L});
    public static final BitSet FOLLOW_constants_in_main137 = new BitSet(new long[]{0x0000020000000020L});
    public static final BitSet FOLLOW_mainVariables_in_main140 = new BitSet(new long[]{0x0000020000000020L});
    public static final BitSet FOLLOW_instantiation_in_main144 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_LESS_in_main146 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_main148 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_GREATER_in_main150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_moduleClass171 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_moduleClass173 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_NAME_in_moduleClass175 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_EQUALS_in_moduleClass177 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_QUOTE_in_moduleClass179 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_moduleClass183 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_QUOTE_in_moduleClass198 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_moduleClass200 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_EQUALS_in_moduleClass202 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_QUOTE_in_moduleClass204 = new BitSet(new long[]{0x0000000000200020L});
    public static final BitSet FOLLOW_inputs_in_moduleClass206 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_QUOTE_in_moduleClass209 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_GREATER_in_moduleClass211 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_constants_in_moduleClass213 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_variables_in_moduleClass216 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_logic_in_moduleClass219 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_LESS_in_moduleClass221 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_moduleClass223 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_GREATER_in_moduleClass225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_inputs257 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_inputs274 = new BitSet(new long[]{0x0000000800000060L});
    public static final BitSet FOLLOW_constIntValue_in_inputs278 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_inputs294 = new BitSet(new long[]{0x0000000000100002L,0x0000000000000040L});
    public static final BitSet FOLLOW_ID_in_inputs318 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_COMMA_in_inputs338 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_inputs342 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_inputs358 = new BitSet(new long[]{0x0000000800000060L});
    public static final BitSet FOLLOW_constIntValue_in_inputs362 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_inputs377 = new BitSet(new long[]{0x0000000000100002L,0x0000000000000040L});
    public static final BitSet FOLLOW_COMMA_in_inputs400 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_inputs404 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_LESS_in_constants435 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_constants437 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_GREATER_in_constants439 = new BitSet(new long[]{0x0000020000000020L});
    public static final BitSet FOLLOW_ID_in_constants444 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_EQUALS_in_constants446 = new BitSet(new long[]{0x0000000800000060L});
    public static final BitSet FOLLOW_intValue_in_constants448 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_constants458 = new BitSet(new long[]{0x0000020000000020L});
    public static final BitSet FOLLOW_LESS_in_constants462 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_73_in_constants464 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_GREATER_in_constants466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_globalConstants484 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_globalConstants486 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_GREATER_in_globalConstants488 = new BitSet(new long[]{0x0000020000000020L});
    public static final BitSet FOLLOW_ID_in_globalConstants493 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_EQUALS_in_globalConstants495 = new BitSet(new long[]{0x0000000800000060L});
    public static final BitSet FOLLOW_intValue_in_globalConstants497 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_globalConstants523 = new BitSet(new long[]{0x0000020000000020L});
    public static final BitSet FOLLOW_LESS_in_globalConstants527 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_73_in_globalConstants529 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_GREATER_in_globalConstants531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_variables547 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_INTERNAL_in_variables549 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_GREATER_in_variables551 = new BitSet(new long[]{0x0000020000000020L});
    public static final BitSet FOLLOW_varDecl_in_variables555 = new BitSet(new long[]{0x0000020000000020L});
    public static final BitSet FOLLOW_arrayDecl_in_variables559 = new BitSet(new long[]{0x0000020000000020L});
    public static final BitSet FOLLOW_LESS_in_variables563 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_variables565 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_GREATER_in_variables567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_mainVariables579 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_INTERNAL_in_mainVariables581 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_GREATER_in_mainVariables583 = new BitSet(new long[]{0x0000020000000020L});
    public static final BitSet FOLLOW_mainVarDecl_in_mainVariables587 = new BitSet(new long[]{0x0000020000000020L});
    public static final BitSet FOLLOW_mainArrayDecl_in_mainVariables591 = new BitSet(new long[]{0x0000020000000020L});
    public static final BitSet FOLLOW_LESS_in_mainVariables595 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_mainVariables597 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_GREATER_in_mainVariables599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_varDecl613 = new BitSet(new long[]{0x0000008000020000L});
    public static final BitSet FOLLOW_EQUALS_in_varDecl624 = new BitSet(new long[]{0x0000000800000060L});
    public static final BitSet FOLLOW_assignmentIntValue_in_varDecl628 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_varDecl641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_mainVarDecl656 = new BitSet(new long[]{0x0000008000020000L});
    public static final BitSet FOLLOW_EQUALS_in_mainVarDecl667 = new BitSet(new long[]{0x0000000800000060L});
    public static final BitSet FOLLOW_mainIntValue_in_mainVarDecl669 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_mainVarDecl681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_arrayDecl696 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_arrayDecl707 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_expression_in_arrayDecl712 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_arrayDecl723 = new BitSet(new long[]{0x0000008000020020L,0x0000000000000040L});
    public static final BitSet FOLLOW_EQUALS_in_arrayDecl735 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_LBRACK_in_arrayDecl738 = new BitSet(new long[]{0x0000000800002060L});
    public static final BitSet FOLLOW_arrayInit1_in_arrayDecl743 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_arrayInit_in_arrayDecl752 = new BitSet(new long[]{0x0000000000104000L});
    public static final BitSet FOLLOW_COMMA_in_arrayDecl764 = new BitSet(new long[]{0x0000000800002060L});
    public static final BitSet FOLLOW_arrayInit_in_arrayDecl768 = new BitSet(new long[]{0x0000000000104000L});
    public static final BitSet FOLLOW_RBRACK_in_arrayDecl792 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_ID_in_arrayDecl800 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_arrayDecl823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_mainArrayDecl838 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_mainArrayDecl849 = new BitSet(new long[]{0x0000000800000060L});
    public static final BitSet FOLLOW_mainIntValue_in_mainArrayDecl855 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_mainArrayDecl866 = new BitSet(new long[]{0x0000008000020000L,0x0000000000000040L});
    public static final BitSet FOLLOW_EQUALS_in_mainArrayDecl878 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_LBRACK_in_mainArrayDecl880 = new BitSet(new long[]{0x0000000800002060L});
    public static final BitSet FOLLOW_mainArrayInit1_in_mainArrayDecl883 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_mainArrayInit_in_mainArrayDecl891 = new BitSet(new long[]{0x0000000000104000L});
    public static final BitSet FOLLOW_COMMA_in_mainArrayDecl903 = new BitSet(new long[]{0x0000000800002060L});
    public static final BitSet FOLLOW_mainArrayInit_in_mainArrayDecl907 = new BitSet(new long[]{0x0000000000104000L});
    public static final BitSet FOLLOW_RBRACK_in_mainArrayDecl931 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_mainArrayDecl944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_arrayInit964 = new BitSet(new long[]{0x0000000800000060L});
    public static final BitSet FOLLOW_assignmentIntValue_in_arrayInit977 = new BitSet(new long[]{0x0000000000104000L});
    public static final BitSet FOLLOW_COMMA_in_arrayInit988 = new BitSet(new long[]{0x0000000800000060L});
    public static final BitSet FOLLOW_assignmentIntValue_in_arrayInit1001 = new BitSet(new long[]{0x0000000000104000L});
    public static final BitSet FOLLOW_RBRACK_in_arrayInit1014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_arrayInit1033 = new BitSet(new long[]{0x0000000800002060L});
    public static final BitSet FOLLOW_arrayInit_in_arrayInit1037 = new BitSet(new long[]{0x0000000000104000L});
    public static final BitSet FOLLOW_COMMA_in_arrayInit1048 = new BitSet(new long[]{0x0000000800002060L});
    public static final BitSet FOLLOW_arrayInit_in_arrayInit1052 = new BitSet(new long[]{0x0000000000104000L});
    public static final BitSet FOLLOW_RBRACK_in_arrayInit1064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignmentIntValue_in_arrayInit11095 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_COMMA_in_arrayInit11107 = new BitSet(new long[]{0x0000000800000060L});
    public static final BitSet FOLLOW_assignmentIntValue_in_arrayInit11119 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_LBRACK_in_mainArrayInit1156 = new BitSet(new long[]{0x0000000800000060L});
    public static final BitSet FOLLOW_mainIntValue_in_mainArrayInit1160 = new BitSet(new long[]{0x0000000000104000L});
    public static final BitSet FOLLOW_COMMA_in_mainArrayInit1171 = new BitSet(new long[]{0x0000000800000060L});
    public static final BitSet FOLLOW_mainIntValue_in_mainArrayInit1175 = new BitSet(new long[]{0x0000000000104000L});
    public static final BitSet FOLLOW_RBRACK_in_mainArrayInit1188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_mainArrayInit1207 = new BitSet(new long[]{0x0000000800002060L});
    public static final BitSet FOLLOW_mainArrayInit_in_mainArrayInit1211 = new BitSet(new long[]{0x0000000000104000L});
    public static final BitSet FOLLOW_COMMA_in_mainArrayInit1222 = new BitSet(new long[]{0x0000000800002060L});
    public static final BitSet FOLLOW_mainArrayInit_in_mainArrayInit1226 = new BitSet(new long[]{0x0000000000104000L});
    public static final BitSet FOLLOW_RBRACK_in_mainArrayInit1238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mainIntValue_in_mainArrayInit11264 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_COMMA_in_mainArrayInit11275 = new BitSet(new long[]{0x0000000800000060L});
    public static final BitSet FOLLOW_mainIntValue_in_mainArrayInit11279 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_MINUS_in_intValue1318 = new BitSet(new long[]{0x0000000800000040L});
    public static final BitSet FOLLOW_INT_in_intValue1326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_intValue1340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_intValue1354 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_intValue1365 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_expression_in_intValue1367 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_intValue1377 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000040L});
    public static final BitSet FOLLOW_MINUS_in_constIntValue1411 = new BitSet(new long[]{0x0000000800000040L});
    public static final BitSet FOLLOW_INT_in_constIntValue1419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_constIntValue1433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_mainIntValue1461 = new BitSet(new long[]{0x0000000800000040L});
    public static final BitSet FOLLOW_INT_in_mainIntValue1469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_mainIntValue1483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_assignmentIntValue1514 = new BitSet(new long[]{0x0000000800000040L});
    public static final BitSet FOLLOW_INT_in_assignmentIntValue1522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_assignmentIntValue1538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_assignmentIntValue1552 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_assignmentIntValue1563 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_expression_in_assignmentIntValue1565 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_assignmentIntValue1575 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000040L});
    public static final BitSet FOLLOW_ID_in_instantiation1605 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_instantiation1609 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_LPAREN_in_instantiation1625 = new BitSet(new long[]{0x00000008000004E0L});
    public static final BitSet FOLLOW_arguments_in_instantiation1627 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_RPAREN_in_instantiation1631 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_instantiation1633 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_ID_in_arguments1672 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_MINUS_in_arguments1694 = new BitSet(new long[]{0x0000000800000040L});
    public static final BitSet FOLLOW_INT_in_arguments1702 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_MEMBER_in_arguments1723 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_COMMA_in_arguments1742 = new BitSet(new long[]{0x00000008000000E0L});
    public static final BitSet FOLLOW_ID_in_arguments1748 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_MINUS_in_arguments1770 = new BitSet(new long[]{0x0000000800000040L});
    public static final BitSet FOLLOW_INT_in_arguments1778 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_MEMBER_in_arguments1799 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_marking_in_logic1846 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_transition_in_logic1849 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_LESS_in_marking1903 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_marking1905 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_GREATER_in_marking1907 = new BitSet(new long[]{0x0000020800000060L});
    public static final BitSet FOLLOW_intValue_in_marking1912 = new BitSet(new long[]{0x0000020000100000L});
    public static final BitSet FOLLOW_COMMA_in_marking1935 = new BitSet(new long[]{0x0000000800000060L});
    public static final BitSet FOLLOW_intValue_in_marking1939 = new BitSet(new long[]{0x0000020000100000L});
    public static final BitSet FOLLOW_LESS_in_marking1965 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_marking1967 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_GREATER_in_marking1969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_transition2004 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_transition2006 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_LABEL_in_transition2010 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_EQUALS_in_transition2012 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_QUOTE_in_transition2014 = new BitSet(new long[]{0x0000000000000160L});
    public static final BitSet FOLLOW_INT_in_transition2019 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_LABELSTRING_in_transition2039 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_ID_in_transition2058 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_QUOTE_in_transition2075 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_preset_in_transition2077 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_postset_in_transition2080 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_GREATER_in_transition2083 = new BitSet(new long[]{0x0000020000000020L,0x0000000000038000L});
    public static final BitSet FOLLOW_guard_in_transition2086 = new BitSet(new long[]{0x0000020000000020L,0x0000000000028000L});
    public static final BitSet FOLLOW_delay_in_transition2106 = new BitSet(new long[]{0x0000020000000020L,0x0000000000008000L});
    public static final BitSet FOLLOW_assertion_in_transition2127 = new BitSet(new long[]{0x0000020000000020L,0x0000000000008000L});
    public static final BitSet FOLLOW_assignment_in_transition2147 = new BitSet(new long[]{0x0000020000000020L,0x0000000000008000L});
    public static final BitSet FOLLOW_LESS_in_transition2167 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_78_in_transition2169 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_GREATER_in_transition2171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRESET_in_preset2200 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_EQUALS_in_preset2202 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_QUOTE_in_preset2205 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_QUOTE_in_preset2207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTE_in_preset2212 = new BitSet(new long[]{0x0000000800000060L});
    public static final BitSet FOLLOW_constIntValue_in_preset2216 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_COMMA_in_preset2232 = new BitSet(new long[]{0x0000000800000060L});
    public static final BitSet FOLLOW_constIntValue_in_preset2236 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_QUOTE_in_preset2253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POSTSET_in_postset2269 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_EQUALS_in_postset2271 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_QUOTE_in_postset2275 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_QUOTE_in_postset2277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTE_in_postset2282 = new BitSet(new long[]{0x0000000800000060L});
    public static final BitSet FOLLOW_constIntValue_in_postset2286 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_COMMA_in_postset2304 = new BitSet(new long[]{0x0000000800000060L});
    public static final BitSet FOLLOW_constIntValue_in_postset2308 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_QUOTE_in_postset2327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_assertion2352 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_LPAREN_in_assertion2354 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_expression_in_assertion2356 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_RPAREN_in_assertion2358 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_assertion2360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_guard2386 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_LPAREN_in_guard2388 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_expression_in_guard2390 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_RPAREN_in_guard2392 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_guard2394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_delay2428 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_LPAREN_in_delay2430 = new BitSet(new long[]{0x0000000800000060L});
    public static final BitSet FOLLOW_intValue_in_delay2434 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_COMMA_in_delay2448 = new BitSet(new long[]{0x0000000800000060L,0x0000000000040000L});
    public static final BitSet FOLLOW_intValue_in_delay2453 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_82_in_delay2467 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_RPAREN_in_delay2480 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_delay2482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_assignment2516 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_EQUALS_in_assignment2518 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ID_in_assignment2522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_assignment2554 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_assignment2571 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_expression_in_assignment2576 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_assignment2593 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_EQUALS_in_assignment2597 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_expression_in_assignment2601 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_assignment2616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_assignment2633 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_EQUALS_in_assignment2635 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_expression_in_assignment2653 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_assignment2668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_term2700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_term2719 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_term2735 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_expression_in_term2740 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_term2755 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000040L});
    public static final BitSet FOLLOW_LPAREN_in_term2780 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_expression_in_term2782 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_RPAREN_in_term2784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_term2796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_term2808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_term2820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_unary2857 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_MINUS_in_unary2862 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_term_in_unary2869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BITWISE_NEGATION_in_bitwiseNegation2901 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_unary_in_bitwiseNegation2907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEGATION_in_negation2933 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_bitwiseNegation_in_negation2939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_negation_in_mult2967 = new BitSet(new long[]{0x0000007000000002L});
    public static final BitSet FOLLOW_TIMES_in_mult2979 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_negation_in_mult2983 = new BitSet(new long[]{0x0000007000000002L});
    public static final BitSet FOLLOW_DIV_in_mult2994 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_negation_in_mult2998 = new BitSet(new long[]{0x0000007000000002L});
    public static final BitSet FOLLOW_MOD_in_mult3009 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_negation_in_mult3013 = new BitSet(new long[]{0x0000007000000002L});
    public static final BitSet FOLLOW_mult_in_add3052 = new BitSet(new long[]{0x0000000C00000002L});
    public static final BitSet FOLLOW_PLUS_in_add3063 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_mult_in_add3067 = new BitSet(new long[]{0x0000000C00000002L});
    public static final BitSet FOLLOW_MINUS_in_add3079 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_mult_in_add3083 = new BitSet(new long[]{0x0000000C00000002L});
    public static final BitSet FOLLOW_add_in_shift3122 = new BitSet(new long[]{0x00C0000000000002L});
    public static final BitSet FOLLOW_BITWISE_LSHIFT_in_shift3133 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_add_in_shift3137 = new BitSet(new long[]{0x00C0000000000002L});
    public static final BitSet FOLLOW_BITWISE_RSHIFT_in_shift3149 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_add_in_shift3153 = new BitSet(new long[]{0x00C0000000000002L});
    public static final BitSet FOLLOW_shift_in_relation3188 = new BitSet(new long[]{0x00000F0000000002L});
    public static final BitSet FOLLOW_LESS_in_relation3199 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_shift_in_relation3203 = new BitSet(new long[]{0x00000F0000000002L});
    public static final BitSet FOLLOW_LESS_EQUAL_in_relation3215 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_shift_in_relation3219 = new BitSet(new long[]{0x00000F0000000002L});
    public static final BitSet FOLLOW_GREATER_EQUAL_in_relation3231 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_shift_in_relation3235 = new BitSet(new long[]{0x00000F0000000002L});
    public static final BitSet FOLLOW_GREATER_in_relation3247 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_shift_in_relation3251 = new BitSet(new long[]{0x00000F0000000002L});
    public static final BitSet FOLLOW_relation_in_equivalence3290 = new BitSet(new long[]{0x0000300000000002L});
    public static final BitSet FOLLOW_EQUIV_in_equivalence3301 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_relation_in_equivalence3305 = new BitSet(new long[]{0x0000300000000002L});
    public static final BitSet FOLLOW_NOT_EQUIV_in_equivalence3316 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_relation_in_equivalence3320 = new BitSet(new long[]{0x0000300000000002L});
    public static final BitSet FOLLOW_equivalence_in_bitwiseAnd3359 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_BITWISE_AND_in_bitwiseAnd3371 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_equivalence_in_bitwiseAnd3375 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_bitwiseAnd_in_bitwiseXor3414 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_BITWISE_XOR_in_bitwiseXor3425 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_bitwiseAnd_in_bitwiseXor3429 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_bitwiseXor_in_bitwiseOr3468 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_BITWISE_OR_in_bitwiseOr3479 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_bitwiseXor_in_bitwiseOr3483 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_bitwiseOr_in_and3522 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_AND_in_and3533 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_bitwiseOr_in_and3537 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_and_in_or3576 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_OR_in_or3587 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_and_in_or3591 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_or_in_implication3628 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_IMPLICATION_in_implication3639 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_or_in_implication3643 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_implication_in_expression3683 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_QMARK_in_expression3693 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_expression_in_expression3697 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_COLON_in_expression3699 = new BitSet(new long[]{0x0004400C00001A60L});
    public static final BitSet FOLLOW_expression_in_expression3703 = new BitSet(new long[]{0x0000000000000002L});

}