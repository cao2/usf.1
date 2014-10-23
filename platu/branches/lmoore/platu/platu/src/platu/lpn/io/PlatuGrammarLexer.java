// $ANTLR 3.3 Nov 30, 2010 12:50:56 /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g 2011-07-29 15:01:20

    package platu.lpn.io;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class PlatuGrammarLexer extends Lexer {
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

    public PlatuGrammarLexer() {;} 
    public PlatuGrammarLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public PlatuGrammarLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g"; }

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:11:7: ( 'main' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:11:9: 'main'
            {
            match("main"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException {
        try {
            int _type = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:12:7: ( 'const' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:12:9: 'const'
            {
            match("const"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:13:7: ( '[' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:13:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:14:7: ( ']' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:14:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:15:7: ( 'inst' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:15:9: 'inst'
            {
            match("inst"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:16:7: ( 'assert' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:16:9: 'assert'
            {
            match("assert"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:17:7: ( 'inf' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:17:9: 'inf'
            {
            match("inf"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__63"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1022:7: ( '(' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1022:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1023:7: ( ')' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1023:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "QMARK"
    public final void mQMARK() throws RecognitionException {
        try {
            int _type = QMARK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1024:6: ( '?' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1024:8: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QMARK"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1025:6: ( ':' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1025:8: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "SEMICOLON"
    public final void mSEMICOLON() throws RecognitionException {
        try {
            int _type = SEMICOLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1026:10: ( ';' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1026:12: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEMICOLON"

    // $ANTLR start "PERIOD"
    public final void mPERIOD() throws RecognitionException {
        try {
            int _type = PERIOD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1027:7: ( '.' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1027:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PERIOD"

    // $ANTLR start "UNDERSCORE"
    public final void mUNDERSCORE() throws RecognitionException {
        try {
            int _type = UNDERSCORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1028:11: ( '_' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1028:13: '_'
            {
            match('_'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UNDERSCORE"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1029:6: ( ',' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1029:8: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "QUOTE"
    public final void mQUOTE() throws RecognitionException {
        try {
            int _type = QUOTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1030:6: ( '\"' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1030:8: '\"'
            {
            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUOTE"

    // $ANTLR start "MODULE"
    public final void mMODULE() throws RecognitionException {
        try {
            int _type = MODULE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1033:7: ( 'mod' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1033:9: 'mod'
            {
            match("mod"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MODULE"

    // $ANTLR start "NAME"
    public final void mNAME() throws RecognitionException {
        try {
            int _type = NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1034:5: ( 'name' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1034:7: 'name'
            {
            match("name"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NAME"

    // $ANTLR start "INPUT"
    public final void mINPUT() throws RecognitionException {
        try {
            int _type = INPUT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1035:6: ( 'input' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1035:8: 'input'
            {
            match("input"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INPUT"

    // $ANTLR start "OUTPUT"
    public final void mOUTPUT() throws RecognitionException {
        try {
            int _type = OUTPUT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1036:7: ( 'output' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1036:9: 'output'
            {
            match("output"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OUTPUT"

    // $ANTLR start "INTERNAL"
    public final void mINTERNAL() throws RecognitionException {
        try {
            int _type = INTERNAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1037:9: ( 'var' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1037:11: 'var'
            {
            match("var"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTERNAL"

    // $ANTLR start "MARKING"
    public final void mMARKING() throws RecognitionException {
        try {
            int _type = MARKING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1038:8: ( 'marking' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1038:10: 'marking'
            {
            match("marking"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MARKING"

    // $ANTLR start "STATE_VECTOR"
    public final void mSTATE_VECTOR() throws RecognitionException {
        try {
            int _type = STATE_VECTOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1039:13: ( 'statevector' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1039:15: 'statevector'
            {
            match("statevector"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STATE_VECTOR"

    // $ANTLR start "TRANSITION"
    public final void mTRANSITION() throws RecognitionException {
        try {
            int _type = TRANSITION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1040:11: ( 'transition' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1040:13: 'transition'
            {
            match("transition"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRANSITION"

    // $ANTLR start "LABEL"
    public final void mLABEL() throws RecognitionException {
        try {
            int _type = LABEL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1041:6: ( 'label' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1041:8: 'label'
            {
            match("label"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LABEL"

    // $ANTLR start "PRESET"
    public final void mPRESET() throws RecognitionException {
        try {
            int _type = PRESET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1042:7: ( 'preset' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1042:9: 'preset'
            {
            match("preset"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PRESET"

    // $ANTLR start "POSTSET"
    public final void mPOSTSET() throws RecognitionException {
        try {
            int _type = POSTSET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1043:8: ( 'postset' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1043:10: 'postset'
            {
            match("postset"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "POSTSET"

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1044:5: ( 'true' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1044:7: 'true'
            {
            match("true"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRUE"

    // $ANTLR start "FALSE"
    public final void mFALSE() throws RecognitionException {
        try {
            int _type = FALSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1045:6: ( 'false' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1045:8: 'false'
            {
            match("false"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FALSE"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1048:5: ( '+' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1048:7: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1049:6: ( '-' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1049:8: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "TIMES"
    public final void mTIMES() throws RecognitionException {
        try {
            int _type = TIMES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1050:6: ( '*' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1050:8: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TIMES"

    // $ANTLR start "DIV"
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1051:4: ( '/' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1051:6: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIV"

    // $ANTLR start "MOD"
    public final void mMOD() throws RecognitionException {
        try {
            int _type = MOD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1052:4: ( '%' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1052:6: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MOD"

    // $ANTLR start "EQUALS"
    public final void mEQUALS() throws RecognitionException {
        try {
            int _type = EQUALS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1053:7: ( '=' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1053:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQUALS"

    // $ANTLR start "GREATER"
    public final void mGREATER() throws RecognitionException {
        try {
            int _type = GREATER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1056:8: ( '>' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1056:10: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GREATER"

    // $ANTLR start "LESS"
    public final void mLESS() throws RecognitionException {
        try {
            int _type = LESS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1057:5: ( '<' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1057:7: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LESS"

    // $ANTLR start "GREATER_EQUAL"
    public final void mGREATER_EQUAL() throws RecognitionException {
        try {
            int _type = GREATER_EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1058:14: ( '>=' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1058:16: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GREATER_EQUAL"

    // $ANTLR start "LESS_EQUAL"
    public final void mLESS_EQUAL() throws RecognitionException {
        try {
            int _type = LESS_EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1059:11: ( '<=' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1059:13: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LESS_EQUAL"

    // $ANTLR start "EQUIV"
    public final void mEQUIV() throws RecognitionException {
        try {
            int _type = EQUIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1060:6: ( '==' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1060:8: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQUIV"

    // $ANTLR start "NOT_EQUIV"
    public final void mNOT_EQUIV() throws RecognitionException {
        try {
            int _type = NOT_EQUIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1061:10: ( '!=' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1061:12: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT_EQUIV"

    // $ANTLR start "NEGATION"
    public final void mNEGATION() throws RecognitionException {
        try {
            int _type = NEGATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1064:9: ( '!' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1064:11: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEGATION"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1065:4: ( '&&' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1065:6: '&&'
            {
            match("&&"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1066:3: ( '||' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1066:5: '||'
            {
            match("||"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "IMPLICATION"
    public final void mIMPLICATION() throws RecognitionException {
        try {
            int _type = IMPLICATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1067:12: ( '->' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1067:14: '->'
            {
            match("->"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IMPLICATION"

    // $ANTLR start "BITWISE_NEGATION"
    public final void mBITWISE_NEGATION() throws RecognitionException {
        try {
            int _type = BITWISE_NEGATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1070:17: ( '~' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1070:19: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BITWISE_NEGATION"

    // $ANTLR start "BITWISE_AND"
    public final void mBITWISE_AND() throws RecognitionException {
        try {
            int _type = BITWISE_AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1071:12: ( '&' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1071:14: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BITWISE_AND"

    // $ANTLR start "BITWISE_OR"
    public final void mBITWISE_OR() throws RecognitionException {
        try {
            int _type = BITWISE_OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1072:11: ( '|' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1072:13: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BITWISE_OR"

    // $ANTLR start "BITWISE_XOR"
    public final void mBITWISE_XOR() throws RecognitionException {
        try {
            int _type = BITWISE_XOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1073:12: ( '^' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1073:14: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BITWISE_XOR"

    // $ANTLR start "BITWISE_LSHIFT"
    public final void mBITWISE_LSHIFT() throws RecognitionException {
        try {
            int _type = BITWISE_LSHIFT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1074:15: ( '<<' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1074:17: '<<'
            {
            match("<<"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BITWISE_LSHIFT"

    // $ANTLR start "BITWISE_RSHIFT"
    public final void mBITWISE_RSHIFT() throws RecognitionException {
        try {
            int _type = BITWISE_RSHIFT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1075:15: ( '>>' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1075:17: '>>'
            {
            match(">>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BITWISE_RSHIFT"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1077:16: ( ( 'a' .. 'z' | 'A' .. 'Z' ) )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1077:18: ( 'a' .. 'z' | 'A' .. 'Z' )
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "LETTER"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1078:15: ( '0' .. '9' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1078:17: '0' .. '9'
            {
            matchRange('0','9'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1079:4: ( ( '-' )? ( DIGIT )+ )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1079:6: ( '-' )? ( DIGIT )+
            {
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1079:6: ( '-' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='-') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1079:6: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1079:11: ( DIGIT )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1079:11: DIGIT
            	    {
            	    mDIGIT(); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1080:3: ( LETTER ( ( UNDERSCORE | PERIOD )? ( LETTER | DIGIT ) )* )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1080:5: LETTER ( ( UNDERSCORE | PERIOD )? ( LETTER | DIGIT ) )*
            {
            mLETTER(); 
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1080:12: ( ( UNDERSCORE | PERIOD )? ( LETTER | DIGIT ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='.'||(LA4_0>='0' && LA4_0<='9')||(LA4_0>='A' && LA4_0<='Z')||LA4_0=='_'||(LA4_0>='a' && LA4_0<='z')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1080:13: ( UNDERSCORE | PERIOD )? ( LETTER | DIGIT )
            	    {
            	    // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1080:13: ( UNDERSCORE | PERIOD )?
            	    int alt3=2;
            	    int LA3_0 = input.LA(1);

            	    if ( (LA3_0=='.'||LA3_0=='_') ) {
            	        alt3=1;
            	    }
            	    switch (alt3) {
            	        case 1 :
            	            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:
            	            {
            	            if ( input.LA(1)=='.'||input.LA(1)=='_' ) {
            	                input.consume();

            	            }
            	            else {
            	                MismatchedSetException mse = new MismatchedSetException(null,input);
            	                recover(mse);
            	                throw mse;}


            	            }
            	            break;

            	    }

            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1081:3: ( ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+ )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1081:5: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
            {
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1081:5: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='\t' && LA5_0<='\n')||(LA5_0>='\f' && LA5_0<='\r')||LA5_0==' ') ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);

            _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1082:8: ( '//' ( . )* ( '\\n' | '\\r' ) )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1082:10: '//' ( . )* ( '\\n' | '\\r' )
            {
            match("//"); 

            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1082:15: ( . )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='\n'||LA6_0=='\r') ) {
                    alt6=2;
                }
                else if ( ((LA6_0>='\u0000' && LA6_0<='\t')||(LA6_0>='\u000B' && LA6_0<='\f')||(LA6_0>='\u000E' && LA6_0<='\uFFFF')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1082:15: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            if ( input.LA(1)=='\n'||input.LA(1)=='\r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "MULTILINECOMMENT"
    public final void mMULTILINECOMMENT() throws RecognitionException {
        try {
            int _type = MULTILINECOMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1083:17: ( '/*' ( . )* '*/' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1083:19: '/*' ( . )* '*/'
            {
            match("/*"); 

            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1083:24: ( . )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='*') ) {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1=='/') ) {
                        alt7=2;
                    }
                    else if ( ((LA7_1>='\u0000' && LA7_1<='.')||(LA7_1>='0' && LA7_1<='\uFFFF')) ) {
                        alt7=1;
                    }


                }
                else if ( ((LA7_0>='\u0000' && LA7_0<=')')||(LA7_0>='+' && LA7_0<='\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1083:24: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            match("*/"); 

            _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MULTILINECOMMENT"

    // $ANTLR start "XMLCOMMENT"
    public final void mXMLCOMMENT() throws RecognitionException {
        try {
            int _type = XMLCOMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1084:11: ( ( '<' '!' '-' '-' ) ( . )* ( '-' '-' '>' ) )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1084:13: ( '<' '!' '-' '-' ) ( . )* ( '-' '-' '>' )
            {
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1084:13: ( '<' '!' '-' '-' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1084:14: '<' '!' '-' '-'
            {
            match('<'); 
            match('!'); 
            match('-'); 
            match('-'); 

            }

            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1084:31: ( . )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='-') ) {
                    int LA8_1 = input.LA(2);

                    if ( (LA8_1=='-') ) {
                        int LA8_3 = input.LA(3);

                        if ( (LA8_3=='>') ) {
                            alt8=2;
                        }
                        else if ( ((LA8_3>='\u0000' && LA8_3<='=')||(LA8_3>='?' && LA8_3<='\uFFFF')) ) {
                            alt8=1;
                        }


                    }
                    else if ( ((LA8_1>='\u0000' && LA8_1<=',')||(LA8_1>='.' && LA8_1<='\uFFFF')) ) {
                        alt8=1;
                    }


                }
                else if ( ((LA8_0>='\u0000' && LA8_0<=',')||(LA8_0>='.' && LA8_0<='\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1084:31: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1084:34: ( '-' '-' '>' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1084:35: '-' '-' '>'
            {
            match('-'); 
            match('-'); 
            match('>'); 

            }

            _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XMLCOMMENT"

    // $ANTLR start "IGNORE"
    public final void mIGNORE() throws RecognitionException {
        try {
            int _type = IGNORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1085:7: ( '<' '?' ( . )* '?' '>' )
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1085:9: '<' '?' ( . )* '?' '>'
            {
            match('<'); 
            match('?'); 
            // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1085:17: ( . )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='?') ) {
                    int LA9_1 = input.LA(2);

                    if ( (LA9_1=='>') ) {
                        alt9=2;
                    }
                    else if ( ((LA9_1>='\u0000' && LA9_1<='=')||(LA9_1>='?' && LA9_1<='\uFFFF')) ) {
                        alt9=1;
                    }


                }
                else if ( ((LA9_0>='\u0000' && LA9_0<='>')||(LA9_0>='@' && LA9_0<='\uFFFF')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1085:17: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            match('?'); 
            match('>'); 
            _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IGNORE"

    public void mTokens() throws RecognitionException {
        // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:8: ( T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | LPAREN | RPAREN | QMARK | COLON | SEMICOLON | PERIOD | UNDERSCORE | COMMA | QUOTE | MODULE | NAME | INPUT | OUTPUT | INTERNAL | MARKING | STATE_VECTOR | TRANSITION | LABEL | PRESET | POSTSET | TRUE | FALSE | PLUS | MINUS | TIMES | DIV | MOD | EQUALS | GREATER | LESS | GREATER_EQUAL | LESS_EQUAL | EQUIV | NOT_EQUIV | NEGATION | AND | OR | IMPLICATION | BITWISE_NEGATION | BITWISE_AND | BITWISE_OR | BITWISE_XOR | BITWISE_LSHIFT | BITWISE_RSHIFT | INT | ID | WS | COMMENT | MULTILINECOMMENT | XMLCOMMENT | IGNORE )
        int alt10=58;
        alt10 = dfa10.predict(input);
        switch (alt10) {
            case 1 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:10: T__57
                {
                mT__57(); 

                }
                break;
            case 2 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:16: T__58
                {
                mT__58(); 

                }
                break;
            case 3 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:22: T__59
                {
                mT__59(); 

                }
                break;
            case 4 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:28: T__60
                {
                mT__60(); 

                }
                break;
            case 5 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:34: T__61
                {
                mT__61(); 

                }
                break;
            case 6 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:40: T__62
                {
                mT__62(); 

                }
                break;
            case 7 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:46: T__63
                {
                mT__63(); 

                }
                break;
            case 8 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:52: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 9 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:59: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 10 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:66: QMARK
                {
                mQMARK(); 

                }
                break;
            case 11 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:72: COLON
                {
                mCOLON(); 

                }
                break;
            case 12 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:78: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 13 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:88: PERIOD
                {
                mPERIOD(); 

                }
                break;
            case 14 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:95: UNDERSCORE
                {
                mUNDERSCORE(); 

                }
                break;
            case 15 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:106: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 16 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:112: QUOTE
                {
                mQUOTE(); 

                }
                break;
            case 17 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:118: MODULE
                {
                mMODULE(); 

                }
                break;
            case 18 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:125: NAME
                {
                mNAME(); 

                }
                break;
            case 19 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:130: INPUT
                {
                mINPUT(); 

                }
                break;
            case 20 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:136: OUTPUT
                {
                mOUTPUT(); 

                }
                break;
            case 21 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:143: INTERNAL
                {
                mINTERNAL(); 

                }
                break;
            case 22 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:152: MARKING
                {
                mMARKING(); 

                }
                break;
            case 23 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:160: STATE_VECTOR
                {
                mSTATE_VECTOR(); 

                }
                break;
            case 24 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:173: TRANSITION
                {
                mTRANSITION(); 

                }
                break;
            case 25 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:184: LABEL
                {
                mLABEL(); 

                }
                break;
            case 26 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:190: PRESET
                {
                mPRESET(); 

                }
                break;
            case 27 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:197: POSTSET
                {
                mPOSTSET(); 

                }
                break;
            case 28 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:205: TRUE
                {
                mTRUE(); 

                }
                break;
            case 29 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:210: FALSE
                {
                mFALSE(); 

                }
                break;
            case 30 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:216: PLUS
                {
                mPLUS(); 

                }
                break;
            case 31 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:221: MINUS
                {
                mMINUS(); 

                }
                break;
            case 32 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:227: TIMES
                {
                mTIMES(); 

                }
                break;
            case 33 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:233: DIV
                {
                mDIV(); 

                }
                break;
            case 34 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:237: MOD
                {
                mMOD(); 

                }
                break;
            case 35 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:241: EQUALS
                {
                mEQUALS(); 

                }
                break;
            case 36 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:248: GREATER
                {
                mGREATER(); 

                }
                break;
            case 37 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:256: LESS
                {
                mLESS(); 

                }
                break;
            case 38 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:261: GREATER_EQUAL
                {
                mGREATER_EQUAL(); 

                }
                break;
            case 39 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:275: LESS_EQUAL
                {
                mLESS_EQUAL(); 

                }
                break;
            case 40 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:286: EQUIV
                {
                mEQUIV(); 

                }
                break;
            case 41 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:292: NOT_EQUIV
                {
                mNOT_EQUIV(); 

                }
                break;
            case 42 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:302: NEGATION
                {
                mNEGATION(); 

                }
                break;
            case 43 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:311: AND
                {
                mAND(); 

                }
                break;
            case 44 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:315: OR
                {
                mOR(); 

                }
                break;
            case 45 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:318: IMPLICATION
                {
                mIMPLICATION(); 

                }
                break;
            case 46 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:330: BITWISE_NEGATION
                {
                mBITWISE_NEGATION(); 

                }
                break;
            case 47 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:347: BITWISE_AND
                {
                mBITWISE_AND(); 

                }
                break;
            case 48 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:359: BITWISE_OR
                {
                mBITWISE_OR(); 

                }
                break;
            case 49 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:370: BITWISE_XOR
                {
                mBITWISE_XOR(); 

                }
                break;
            case 50 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:382: BITWISE_LSHIFT
                {
                mBITWISE_LSHIFT(); 

                }
                break;
            case 51 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:397: BITWISE_RSHIFT
                {
                mBITWISE_RSHIFT(); 

                }
                break;
            case 52 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:412: INT
                {
                mINT(); 

                }
                break;
            case 53 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:416: ID
                {
                mID(); 

                }
                break;
            case 54 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:419: WS
                {
                mWS(); 

                }
                break;
            case 55 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:422: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 56 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:430: MULTILINECOMMENT
                {
                mMULTILINECOMMENT(); 

                }
                break;
            case 57 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:447: XMLCOMMENT
                {
                mXMLCOMMENT(); 

                }
                break;
            case 58 :
                // /Users/erodrig9/workspace/platu/src/platu/lpn/io/PlatuGrammar.g:1:458: IGNORE
                {
                mIGNORE(); 

                }
                break;

        }

    }


    protected DFA10 dfa10 = new DFA10(this);
    static final String DFA10_eotS =
        "\1\uffff\2\46\2\uffff\2\46\11\uffff\10\46\1\uffff\1\67\1\uffff\1"+
        "\72\1\uffff\1\74\1\77\1\104\1\106\1\110\1\112\5\uffff\16\46\25\uffff"+
        "\2\46\1\137\2\46\1\142\4\46\1\147\7\46\1\157\1\46\1\uffff\1\46\1"+
        "\162\1\uffff\2\46\1\165\1\46\1\uffff\2\46\1\171\4\46\1\uffff\1\46"+
        "\1\177\1\uffff\1\u0080\1\46\1\uffff\3\46\1\uffff\1\u0085\2\46\1"+
        "\u0088\1\46\2\uffff\1\u008a\1\u008b\2\46\1\uffff\1\u008e\1\46\1"+
        "\uffff\1\u0090\2\uffff\2\46\1\uffff\1\u0093\1\uffff\2\46\1\uffff"+
        "\3\46\1\u0099\1\u009a\2\uffff";
    static final String DFA10_eofS =
        "\u009b\uffff";
    static final String DFA10_minS =
        "\1\11\1\141\1\157\2\uffff\1\156\1\163\11\uffff\1\141\1\165\1\141"+
        "\1\164\1\162\1\141\1\157\1\141\1\uffff\1\60\1\uffff\1\52\1\uffff"+
        "\2\75\1\41\1\75\1\46\1\174\5\uffff\1\151\1\144\1\156\1\146\1\163"+
        "\1\155\1\164\1\162\2\141\1\142\1\145\1\163\1\154\25\uffff\1\156"+
        "\1\153\1\56\1\163\1\164\1\56\1\165\2\145\1\160\1\56\1\164\1\156"+
        "\2\145\1\163\1\164\1\163\1\56\1\151\1\uffff\1\164\1\56\1\uffff\1"+
        "\164\1\162\1\56\1\165\1\uffff\1\145\1\163\1\56\1\154\1\145\1\163"+
        "\1\145\1\uffff\1\156\1\56\1\uffff\1\56\1\164\1\uffff\1\164\1\166"+
        "\1\151\1\uffff\1\56\1\164\1\145\1\56\1\147\2\uffff\2\56\1\145\1"+
        "\164\1\uffff\1\56\1\164\1\uffff\1\56\2\uffff\1\143\1\151\1\uffff"+
        "\1\56\1\uffff\1\164\1\157\1\uffff\1\157\1\156\1\162\2\56\2\uffff";
    static final String DFA10_maxS =
        "\1\176\2\157\2\uffff\1\156\1\163\11\uffff\1\141\1\165\1\141\1\164"+
        "\1\162\1\141\1\162\1\141\1\uffff\1\76\1\uffff\1\57\1\uffff\1\75"+
        "\1\76\1\77\1\75\1\46\1\174\5\uffff\1\162\1\144\1\156\2\163\1\155"+
        "\1\164\1\162\1\141\1\165\1\142\1\145\1\163\1\154\25\uffff\1\156"+
        "\1\153\1\172\1\163\1\164\1\172\1\165\2\145\1\160\1\172\1\164\1\156"+
        "\2\145\1\163\1\164\1\163\1\172\1\151\1\uffff\1\164\1\172\1\uffff"+
        "\1\164\1\162\1\172\1\165\1\uffff\1\145\1\163\1\172\1\154\1\145\1"+
        "\163\1\145\1\uffff\1\156\1\172\1\uffff\1\172\1\164\1\uffff\1\164"+
        "\1\166\1\151\1\uffff\1\172\1\164\1\145\1\172\1\147\2\uffff\2\172"+
        "\1\145\1\164\1\uffff\1\172\1\164\1\uffff\1\172\2\uffff\1\143\1\151"+
        "\1\uffff\1\172\1\uffff\1\164\1\157\1\uffff\1\157\1\156\1\162\2\172"+
        "\2\uffff";
    static final String DFA10_acceptS =
        "\3\uffff\1\3\1\4\2\uffff\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17"+
        "\1\20\10\uffff\1\36\1\uffff\1\40\1\uffff\1\42\6\uffff\1\56\1\61"+
        "\1\64\1\65\1\66\16\uffff\1\55\1\37\1\67\1\70\1\41\1\50\1\43\1\46"+
        "\1\63\1\44\1\47\1\62\1\71\1\72\1\45\1\51\1\52\1\53\1\57\1\54\1\60"+
        "\24\uffff\1\21\2\uffff\1\7\4\uffff\1\25\7\uffff\1\1\2\uffff\1\5"+
        "\2\uffff\1\22\3\uffff\1\34\5\uffff\1\2\1\23\4\uffff\1\31\2\uffff"+
        "\1\35\1\uffff\1\6\1\24\2\uffff\1\32\1\uffff\1\26\2\uffff\1\33\5"+
        "\uffff\1\30\1\27";
    static final String DFA10_specialS =
        "\u009b\uffff}>";
    static final String[] DFA10_transitionS = {
            "\2\47\1\uffff\2\47\22\uffff\1\47\1\40\1\17\2\uffff\1\34\1\41"+
            "\1\uffff\1\7\1\10\1\32\1\30\1\16\1\31\1\14\1\33\12\45\1\12\1"+
            "\13\1\37\1\35\1\36\1\11\1\uffff\32\46\1\3\1\uffff\1\4\1\44\1"+
            "\15\1\uffff\1\6\1\46\1\2\2\46\1\27\2\46\1\5\2\46\1\25\1\1\1"+
            "\20\1\21\1\26\2\46\1\23\1\24\1\46\1\22\4\46\1\uffff\1\42\1\uffff"+
            "\1\43",
            "\1\50\15\uffff\1\51",
            "\1\52",
            "",
            "",
            "\1\53",
            "\1\54",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\55",
            "\1\56",
            "\1\57",
            "\1\60",
            "\1\61",
            "\1\62",
            "\1\64\2\uffff\1\63",
            "\1\65",
            "",
            "\12\45\4\uffff\1\66",
            "",
            "\1\71\4\uffff\1\70",
            "",
            "\1\73",
            "\1\75\1\76",
            "\1\102\32\uffff\1\101\1\100\1\uffff\1\103",
            "\1\105",
            "\1\107",
            "\1\111",
            "",
            "",
            "",
            "",
            "",
            "\1\113\10\uffff\1\114",
            "\1\115",
            "\1\116",
            "\1\120\11\uffff\1\121\2\uffff\1\117",
            "\1\122",
            "\1\123",
            "\1\124",
            "\1\125",
            "\1\126",
            "\1\127\23\uffff\1\130",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\135",
            "\1\136",
            "\1\46\1\uffff\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\140",
            "\1\141",
            "\1\46\1\uffff\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\143",
            "\1\144",
            "\1\145",
            "\1\146",
            "\1\46\1\uffff\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\154",
            "\1\155",
            "\1\156",
            "\1\46\1\uffff\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\160",
            "",
            "\1\161",
            "\1\46\1\uffff\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "\1\163",
            "\1\164",
            "\1\46\1\uffff\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\166",
            "",
            "\1\167",
            "\1\170",
            "\1\46\1\uffff\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\172",
            "\1\173",
            "\1\174",
            "\1\175",
            "",
            "\1\176",
            "\1\46\1\uffff\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "\1\46\1\uffff\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u0081",
            "",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "",
            "\1\46\1\uffff\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u0086",
            "\1\u0087",
            "\1\46\1\uffff\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u0089",
            "",
            "",
            "\1\46\1\uffff\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\46\1\uffff\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u008c",
            "\1\u008d",
            "",
            "\1\46\1\uffff\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\u008f",
            "",
            "\1\46\1\uffff\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "",
            "\1\u0091",
            "\1\u0092",
            "",
            "\1\46\1\uffff\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            "\1\u0094",
            "\1\u0095",
            "",
            "\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "\1\46\1\uffff\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "\1\46\1\uffff\12\46\7\uffff\32\46\4\uffff\1\46\1\uffff\32\46",
            "",
            ""
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | LPAREN | RPAREN | QMARK | COLON | SEMICOLON | PERIOD | UNDERSCORE | COMMA | QUOTE | MODULE | NAME | INPUT | OUTPUT | INTERNAL | MARKING | STATE_VECTOR | TRANSITION | LABEL | PRESET | POSTSET | TRUE | FALSE | PLUS | MINUS | TIMES | DIV | MOD | EQUALS | GREATER | LESS | GREATER_EQUAL | LESS_EQUAL | EQUIV | NOT_EQUIV | NEGATION | AND | OR | IMPLICATION | BITWISE_NEGATION | BITWISE_AND | BITWISE_OR | BITWISE_XOR | BITWISE_LSHIFT | BITWISE_RSHIFT | INT | ID | WS | COMMENT | MULTILINECOMMENT | XMLCOMMENT | IGNORE );";
        }
    }
 

}