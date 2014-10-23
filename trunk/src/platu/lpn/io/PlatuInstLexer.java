// $ANTLR 3.3 Nov 30, 2010 12:50:56 C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g 2012-07-17 11:50:37

    package platu.lpn.io;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class PlatuInstLexer extends Lexer {
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

    public PlatuInstLexer() {;} 
    public PlatuInstLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public PlatuInstLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g"; }

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:11:7: ( 'include' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:11:9: 'include'
            {
            match("include"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:12:7: ( '/include' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:12:9: '/include'
            {
            match("/include"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:13:7: ( '/main' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:13:9: '/main'
            {
            match("/main"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:14:7: ( 'class' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:14:9: 'class'
            {
            match("class"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:15:7: ( 'arg' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:15:9: 'arg'
            {
            match("arg"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:16:7: ( '/class' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:16:9: '/class'
            {
            match("/class"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:17:7: ( '[' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:17:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:18:7: ( ']' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:18:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:19:7: ( 'const' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:19:9: 'const'
            {
            match("const"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:20:7: ( '/const' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:20:9: '/const'
            {
            match("/const"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:21:7: ( '/var' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:21:9: '/var'
            {
            match("/var"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:22:7: ( 'm' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:22:9: 'm'
            {
            match('m'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:23:7: ( '/m' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:23:9: '/m'
            {
            match("/m"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:24:7: ( 'tr' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:24:9: 'tr'
            {
            match("tr"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:25:7: ( '/tr' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:25:9: '/tr'
            {
            match("/tr"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:26:7: ( 'assert' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:26:9: 'assert'
            {
            match("assert"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:27:7: ( 'guard' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:27:9: 'guard'
            {
            match("guard"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:28:7: ( 'delay' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:28:9: 'delay'
            {
            match("delay"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:29:7: ( 'inf' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:29:9: 'inf'
            {
            match("inf"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__82"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1845:7: ( '(' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1845:9: '('
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1846:7: ( ')' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1846:9: ')'
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

    // $ANTLR start "LBRACK"
    public final void mLBRACK() throws RecognitionException {
        try {
            int _type = LBRACK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1847:7: ( '{' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1847:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LBRACK"

    // $ANTLR start "RBRACK"
    public final void mRBRACK() throws RecognitionException {
        try {
            int _type = RBRACK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1848:7: ( '}' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1848:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RBRACK"

    // $ANTLR start "QMARK"
    public final void mQMARK() throws RecognitionException {
        try {
            int _type = QMARK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1849:6: ( '?' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1849:8: '?'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1850:6: ( ':' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1850:8: ':'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1851:10: ( ';' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1851:12: ';'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1852:7: ( '.' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1852:9: '.'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1853:11: ( '_' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1853:13: '_'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1854:6: ( ',' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1854:8: ','
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1855:6: ( '\"' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1855:8: '\"'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1858:7: ( 'mod' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1858:9: 'mod'
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

    // $ANTLR start "MAIN"
    public final void mMAIN() throws RecognitionException {
        try {
            int _type = MAIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1859:5: ( 'main' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1859:7: 'main'
            {
            match("main"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MAIN"

    // $ANTLR start "NAME"
    public final void mNAME() throws RecognitionException {
        try {
            int _type = NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1860:5: ( 'name' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1860:7: 'name'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1861:6: ( 'input' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1861:8: 'input'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1862:7: ( 'output' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1862:9: 'output'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1863:9: ( 'var' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1863:11: 'var'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1864:8: ( 'marking' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1864:10: 'marking'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1865:13: ( 'statevector' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1865:15: 'statevector'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1866:11: ( 'transition' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1866:13: 'transition'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1867:6: ( 'label' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1867:8: 'label'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1868:7: ( 'preset' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1868:9: 'preset'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1869:8: ( 'postset' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1869:10: 'postset'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1870:5: ( 'true' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1870:7: 'true'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1871:6: ( 'false' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1871:8: 'false'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1874:5: ( '+' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1874:7: '+'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1875:6: ( '-' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1875:8: '-'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1876:6: ( '*' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1876:8: '*'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1877:4: ( '/' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1877:6: '/'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1878:4: ( '%' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1878:6: '%'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1879:7: ( '=' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1879:9: '='
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1882:8: ( '>' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1882:10: '>'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1883:5: ( '<' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1883:7: '<'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1884:14: ( '>=' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1884:16: '>='
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1885:11: ( '<=' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1885:13: '<='
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1886:6: ( '==' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1886:8: '=='
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1887:10: ( '!=' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1887:12: '!='
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1890:9: ( '!' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1890:11: '!'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1891:4: ( '&&' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1891:6: '&&'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1892:3: ( '||' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1892:5: '||'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1893:12: ( '->' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1893:14: '->'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1896:17: ( '~' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1896:19: '~'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1897:12: ( '&' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1897:14: '&'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1898:11: ( '|' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1898:13: '|'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1899:12: ( '^' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1899:14: '^'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1900:15: ( '<<' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1900:17: '<<'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1901:15: ( '>>' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1901:17: '>>'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1911:16: ( ( 'a' .. 'z' | 'A' .. 'Z' ) )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1911:18: ( 'a' .. 'z' | 'A' .. 'Z' )
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1912:15: ( '0' .. '9' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1912:17: '0' .. '9'
            {
            matchRange('0','9'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "FILE"
    public final void mFILE() throws RecognitionException {
        try {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1913:14: ( ( LETTER | DIGIT ) ( ( '_' )? ( LETTER | DIGIT ) )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1913:16: ( LETTER | DIGIT ) ( ( '_' )? ( LETTER | DIGIT ) )*
            {
            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1913:33: ( ( '_' )? ( LETTER | DIGIT ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1913:34: ( '_' )? ( LETTER | DIGIT )
            	    {
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1913:34: ( '_' )?
            	    int alt1=2;
            	    int LA1_0 = input.LA(1);

            	    if ( (LA1_0=='_') ) {
            	        alt1=1;
            	    }
            	    switch (alt1) {
            	        case 1 :
            	            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1913:34: '_'
            	            {
            	            match('_'); 

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
            	    break loop2;
                }
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "FILE"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1914:4: ( ( DIGIT )+ )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1914:6: ( DIGIT )+
            {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1914:6: ( DIGIT )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1914:6: DIGIT
            	    {
            	    mDIGIT(); 

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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1915:3: ( LETTER ( ( UNDERSCORE )? ( LETTER | DIGIT ) )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1915:5: LETTER ( ( UNDERSCORE )? ( LETTER | DIGIT ) )*
            {
            mLETTER(); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1915:12: ( ( UNDERSCORE )? ( LETTER | DIGIT ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='0' && LA5_0<='9')||(LA5_0>='A' && LA5_0<='Z')||LA5_0=='_'||(LA5_0>='a' && LA5_0<='z')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1915:13: ( UNDERSCORE )? ( LETTER | DIGIT )
            	    {
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1915:13: ( UNDERSCORE )?
            	    int alt4=2;
            	    int LA4_0 = input.LA(1);

            	    if ( (LA4_0=='_') ) {
            	        alt4=1;
            	    }
            	    switch (alt4) {
            	        case 1 :
            	            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1915:13: UNDERSCORE
            	            {
            	            mUNDERSCORE(); 

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
            	    break loop5;
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

    // $ANTLR start "PATH"
    public final void mPATH() throws RecognitionException {
        try {
            int _type = PATH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1916:5: ( ( ( LETTER ':' ) | '/' )? ( FILE ( '/' | '\\\\' ) )* FILE '.lpn' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1916:7: ( ( LETTER ':' ) | '/' )? ( FILE ( '/' | '\\\\' ) )* FILE '.lpn'
            {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1916:7: ( ( LETTER ':' ) | '/' )?
            int alt6=3;
            int LA6_0 = input.LA(1);

            if ( ((LA6_0>='A' && LA6_0<='Z')||(LA6_0>='a' && LA6_0<='z')) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==':') ) {
                    alt6=1;
                }
            }
            else if ( (LA6_0=='/') ) {
                alt6=2;
            }
            switch (alt6) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1916:8: ( LETTER ':' )
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1916:8: ( LETTER ':' )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1916:9: LETTER ':'
                    {
                    mLETTER(); 
                    match(':'); 

                    }


                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1916:23: '/'
                    {
                    match('/'); 

                    }
                    break;

            }

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1916:29: ( FILE ( '/' | '\\\\' ) )*
            loop7:
            do {
                int alt7=2;
                alt7 = dfa7.predict(input);
                switch (alt7) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1916:30: FILE ( '/' | '\\\\' )
            	    {
            	    mFILE(); 
            	    if ( input.LA(1)=='/'||input.LA(1)=='\\' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            mFILE(); 
            match(".lpn"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PATH"

    // $ANTLR start "MEMBER"
    public final void mMEMBER() throws RecognitionException {
        try {
            int _type = MEMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1917:7: ( ID '.' ID )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1917:9: ID '.' ID
            {
            mID(); 
            match('.'); 
            mID(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MEMBER"

    // $ANTLR start "LABELSTRING"
    public final void mLABELSTRING() throws RecognitionException {
        try {
            int _type = LABELSTRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1918:12: ( DIGIT ( ( LETTER ) | ( UNDERSCORE ( LETTER | DIGIT ) ) ) ( ( '_' )? ( LETTER | DIGIT ) )* )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1918:15: DIGIT ( ( LETTER ) | ( UNDERSCORE ( LETTER | DIGIT ) ) ) ( ( '_' )? ( LETTER | DIGIT ) )*
            {
            mDIGIT(); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1918:21: ( ( LETTER ) | ( UNDERSCORE ( LETTER | DIGIT ) ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( ((LA8_0>='A' && LA8_0<='Z')||(LA8_0>='a' && LA8_0<='z')) ) {
                alt8=1;
            }
            else if ( (LA8_0=='_') ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1918:22: ( LETTER )
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1918:22: ( LETTER )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1918:23: LETTER
                    {
                    mLETTER(); 

                    }


                    }
                    break;
                case 2 :
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1918:33: ( UNDERSCORE ( LETTER | DIGIT ) )
                    {
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1918:33: ( UNDERSCORE ( LETTER | DIGIT ) )
                    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1918:34: UNDERSCORE ( LETTER | DIGIT )
                    {
                    mUNDERSCORE(); 
                    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }


                    }
                    break;

            }

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1918:64: ( ( '_' )? ( LETTER | DIGIT ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='0' && LA10_0<='9')||(LA10_0>='A' && LA10_0<='Z')||LA10_0=='_'||(LA10_0>='a' && LA10_0<='z')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1918:65: ( '_' )? ( LETTER | DIGIT )
            	    {
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1918:65: ( '_' )?
            	    int alt9=2;
            	    int LA9_0 = input.LA(1);

            	    if ( (LA9_0=='_') ) {
            	        alt9=1;
            	    }
            	    switch (alt9) {
            	        case 1 :
            	            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1918:65: '_'
            	            {
            	            match('_'); 

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
            	    break loop10;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LABELSTRING"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1920:3: ( ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+ )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1920:5: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
            {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1920:5: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>='\t' && LA11_0<='\n')||(LA11_0>='\f' && LA11_0<='\r')||LA11_0==' ') ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:
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
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1921:8: ( '//' ( . )* ( '\\n' | '\\r' ) )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1921:10: '//' ( . )* ( '\\n' | '\\r' )
            {
            match("//"); 

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1921:15: ( . )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0=='\n'||LA12_0=='\r') ) {
                    alt12=2;
                }
                else if ( ((LA12_0>='\u0000' && LA12_0<='\t')||(LA12_0>='\u000B' && LA12_0<='\f')||(LA12_0>='\u000E' && LA12_0<='\uFFFF')) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1921:15: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop12;
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1922:17: ( '/*' ( . )* '*/' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1922:19: '/*' ( . )* '*/'
            {
            match("/*"); 

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1922:24: ( . )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0=='*') ) {
                    int LA13_1 = input.LA(2);

                    if ( (LA13_1=='/') ) {
                        alt13=2;
                    }
                    else if ( ((LA13_1>='\u0000' && LA13_1<='.')||(LA13_1>='0' && LA13_1<='\uFFFF')) ) {
                        alt13=1;
                    }


                }
                else if ( ((LA13_0>='\u0000' && LA13_0<=')')||(LA13_0>='+' && LA13_0<='\uFFFF')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1922:24: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop13;
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1923:11: ( ( '<' '!' '-' '-' ) ( . )* ( '-' '-' '>' ) )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1923:13: ( '<' '!' '-' '-' ) ( . )* ( '-' '-' '>' )
            {
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1923:13: ( '<' '!' '-' '-' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1923:14: '<' '!' '-' '-'
            {
            match('<'); 
            match('!'); 
            match('-'); 
            match('-'); 

            }

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1923:31: ( . )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0=='-') ) {
                    int LA14_1 = input.LA(2);

                    if ( (LA14_1=='-') ) {
                        int LA14_3 = input.LA(3);

                        if ( (LA14_3=='>') ) {
                            alt14=2;
                        }
                        else if ( ((LA14_3>='\u0000' && LA14_3<='=')||(LA14_3>='?' && LA14_3<='\uFFFF')) ) {
                            alt14=1;
                        }


                    }
                    else if ( ((LA14_1>='\u0000' && LA14_1<=',')||(LA14_1>='.' && LA14_1<='\uFFFF')) ) {
                        alt14=1;
                    }


                }
                else if ( ((LA14_0>='\u0000' && LA14_0<=',')||(LA14_0>='.' && LA14_0<='\uFFFF')) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1923:31: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1923:34: ( '-' '-' '>' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1923:35: '-' '-' '>'
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
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1924:7: ( '<' '?' ( . )* '?' '>' )
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1924:9: '<' '?' ( . )* '?' '>'
            {
            match('<'); 
            match('?'); 
            // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1924:17: ( . )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0=='?') ) {
                    int LA15_1 = input.LA(2);

                    if ( (LA15_1=='>') ) {
                        alt15=2;
                    }
                    else if ( ((LA15_1>='\u0000' && LA15_1<='=')||(LA15_1>='?' && LA15_1<='\uFFFF')) ) {
                        alt15=1;
                    }


                }
                else if ( ((LA15_0>='\u0000' && LA15_0<='>')||(LA15_0>='@' && LA15_0<='\uFFFF')) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1924:17: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop15;
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
        // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:8: ( T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | LPAREN | RPAREN | LBRACK | RBRACK | QMARK | COLON | SEMICOLON | PERIOD | UNDERSCORE | COMMA | QUOTE | MODULE | MAIN | NAME | INPUT | OUTPUT | INTERNAL | MARKING | STATE_VECTOR | TRANSITION | LABEL | PRESET | POSTSET | TRUE | FALSE | PLUS | MINUS | TIMES | DIV | MOD | EQUALS | GREATER | LESS | GREATER_EQUAL | LESS_EQUAL | EQUIV | NOT_EQUIV | NEGATION | AND | OR | IMPLICATION | BITWISE_NEGATION | BITWISE_AND | BITWISE_OR | BITWISE_XOR | BITWISE_LSHIFT | BITWISE_RSHIFT | INT | ID | PATH | MEMBER | LABELSTRING | WS | COMMENT | MULTILINECOMMENT | XMLCOMMENT | IGNORE )
        int alt16=76;
        alt16 = dfa16.predict(input);
        switch (alt16) {
            case 1 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:10: T__64
                {
                mT__64(); 

                }
                break;
            case 2 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:16: T__65
                {
                mT__65(); 

                }
                break;
            case 3 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:22: T__66
                {
                mT__66(); 

                }
                break;
            case 4 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:28: T__67
                {
                mT__67(); 

                }
                break;
            case 5 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:34: T__68
                {
                mT__68(); 

                }
                break;
            case 6 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:40: T__69
                {
                mT__69(); 

                }
                break;
            case 7 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:46: T__70
                {
                mT__70(); 

                }
                break;
            case 8 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:52: T__71
                {
                mT__71(); 

                }
                break;
            case 9 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:58: T__72
                {
                mT__72(); 

                }
                break;
            case 10 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:64: T__73
                {
                mT__73(); 

                }
                break;
            case 11 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:70: T__74
                {
                mT__74(); 

                }
                break;
            case 12 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:76: T__75
                {
                mT__75(); 

                }
                break;
            case 13 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:82: T__76
                {
                mT__76(); 

                }
                break;
            case 14 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:88: T__77
                {
                mT__77(); 

                }
                break;
            case 15 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:94: T__78
                {
                mT__78(); 

                }
                break;
            case 16 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:100: T__79
                {
                mT__79(); 

                }
                break;
            case 17 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:106: T__80
                {
                mT__80(); 

                }
                break;
            case 18 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:112: T__81
                {
                mT__81(); 

                }
                break;
            case 19 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:118: T__82
                {
                mT__82(); 

                }
                break;
            case 20 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:124: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 21 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:131: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 22 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:138: LBRACK
                {
                mLBRACK(); 

                }
                break;
            case 23 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:145: RBRACK
                {
                mRBRACK(); 

                }
                break;
            case 24 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:152: QMARK
                {
                mQMARK(); 

                }
                break;
            case 25 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:158: COLON
                {
                mCOLON(); 

                }
                break;
            case 26 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:164: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 27 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:174: PERIOD
                {
                mPERIOD(); 

                }
                break;
            case 28 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:181: UNDERSCORE
                {
                mUNDERSCORE(); 

                }
                break;
            case 29 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:192: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 30 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:198: QUOTE
                {
                mQUOTE(); 

                }
                break;
            case 31 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:204: MODULE
                {
                mMODULE(); 

                }
                break;
            case 32 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:211: MAIN
                {
                mMAIN(); 

                }
                break;
            case 33 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:216: NAME
                {
                mNAME(); 

                }
                break;
            case 34 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:221: INPUT
                {
                mINPUT(); 

                }
                break;
            case 35 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:227: OUTPUT
                {
                mOUTPUT(); 

                }
                break;
            case 36 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:234: INTERNAL
                {
                mINTERNAL(); 

                }
                break;
            case 37 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:243: MARKING
                {
                mMARKING(); 

                }
                break;
            case 38 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:251: STATE_VECTOR
                {
                mSTATE_VECTOR(); 

                }
                break;
            case 39 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:264: TRANSITION
                {
                mTRANSITION(); 

                }
                break;
            case 40 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:275: LABEL
                {
                mLABEL(); 

                }
                break;
            case 41 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:281: PRESET
                {
                mPRESET(); 

                }
                break;
            case 42 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:288: POSTSET
                {
                mPOSTSET(); 

                }
                break;
            case 43 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:296: TRUE
                {
                mTRUE(); 

                }
                break;
            case 44 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:301: FALSE
                {
                mFALSE(); 

                }
                break;
            case 45 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:307: PLUS
                {
                mPLUS(); 

                }
                break;
            case 46 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:312: MINUS
                {
                mMINUS(); 

                }
                break;
            case 47 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:318: TIMES
                {
                mTIMES(); 

                }
                break;
            case 48 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:324: DIV
                {
                mDIV(); 

                }
                break;
            case 49 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:328: MOD
                {
                mMOD(); 

                }
                break;
            case 50 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:332: EQUALS
                {
                mEQUALS(); 

                }
                break;
            case 51 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:339: GREATER
                {
                mGREATER(); 

                }
                break;
            case 52 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:347: LESS
                {
                mLESS(); 

                }
                break;
            case 53 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:352: GREATER_EQUAL
                {
                mGREATER_EQUAL(); 

                }
                break;
            case 54 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:366: LESS_EQUAL
                {
                mLESS_EQUAL(); 

                }
                break;
            case 55 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:377: EQUIV
                {
                mEQUIV(); 

                }
                break;
            case 56 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:383: NOT_EQUIV
                {
                mNOT_EQUIV(); 

                }
                break;
            case 57 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:393: NEGATION
                {
                mNEGATION(); 

                }
                break;
            case 58 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:402: AND
                {
                mAND(); 

                }
                break;
            case 59 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:406: OR
                {
                mOR(); 

                }
                break;
            case 60 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:409: IMPLICATION
                {
                mIMPLICATION(); 

                }
                break;
            case 61 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:421: BITWISE_NEGATION
                {
                mBITWISE_NEGATION(); 

                }
                break;
            case 62 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:438: BITWISE_AND
                {
                mBITWISE_AND(); 

                }
                break;
            case 63 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:450: BITWISE_OR
                {
                mBITWISE_OR(); 

                }
                break;
            case 64 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:461: BITWISE_XOR
                {
                mBITWISE_XOR(); 

                }
                break;
            case 65 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:473: BITWISE_LSHIFT
                {
                mBITWISE_LSHIFT(); 

                }
                break;
            case 66 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:488: BITWISE_RSHIFT
                {
                mBITWISE_RSHIFT(); 

                }
                break;
            case 67 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:503: INT
                {
                mINT(); 

                }
                break;
            case 68 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:507: ID
                {
                mID(); 

                }
                break;
            case 69 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:510: PATH
                {
                mPATH(); 

                }
                break;
            case 70 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:515: MEMBER
                {
                mMEMBER(); 

                }
                break;
            case 71 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:522: LABELSTRING
                {
                mLABELSTRING(); 

                }
                break;
            case 72 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:534: WS
                {
                mWS(); 

                }
                break;
            case 73 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:537: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 74 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:545: MULTILINECOMMENT
                {
                mMULTILINECOMMENT(); 

                }
                break;
            case 75 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:562: XMLCOMMENT
                {
                mXMLCOMMENT(); 

                }
                break;
            case 76 :
                // C:\\Users\\Manny Rodriguez\\workspace\\platu\\src\\platu\\lpn\\io\\PlatuInst.g:1:573: IGNORE
                {
                mIGNORE(); 

                }
                break;

        }

    }


    protected DFA7 dfa7 = new DFA7(this);
    protected DFA16 dfa16 = new DFA16(this);
    static final String DFA7_eotS =
        "\6\uffff";
    static final String DFA7_eofS =
        "\6\uffff";
    static final String DFA7_minS =
        "\1\60\1\56\1\60\1\56\2\uffff";
    static final String DFA7_maxS =
        "\4\172\2\uffff";
    static final String DFA7_acceptS =
        "\4\uffff\1\2\1\1";
    static final String DFA7_specialS =
        "\6\uffff}>";
    static final String[] DFA7_transitionS = {
            "\12\1\7\uffff\32\1\6\uffff\32\1",
            "\1\4\1\5\12\3\7\uffff\32\3\1\uffff\1\5\2\uffff\1\2\1\uffff"+
            "\32\3",
            "\12\3\7\uffff\32\3\6\uffff\32\3",
            "\1\4\1\5\12\3\7\uffff\32\3\1\uffff\1\5\2\uffff\1\2\1\uffff"+
            "\32\3",
            "",
            ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "()* loopback of 1916:29: ( FILE ( '/' | '\\\\' ) )*";
        }
    }
    static final String DFA16_eotS =
        "\1\uffff\1\55\1\71\2\55\2\uffff\1\100\3\55\13\uffff\7\55\1\uffff"+
        "\1\115\2\uffff\1\117\1\122\1\127\1\131\1\133\1\135\2\uffff\1\136"+
        "\1\55\1\uffff\1\55\2\uffff\1\55\3\uffff\1\151\6\uffff\6\55\1\uffff"+
        "\1\167\12\55\23\uffff\1\136\1\uffff\1\u0083\1\55\1\u0087\1\55\1"+
        "\146\7\uffff\1\u008f\2\55\1\u0092\1\55\1\u0094\4\55\1\uffff\4\55"+
        "\1\u009d\5\55\1\u0083\2\uffff\1\u0083\1\55\1\uffff\1\55\1\146\4"+
        "\uffff\1\u00aa\1\uffff\2\55\1\uffff\1\55\1\uffff\1\u00ae\2\55\1"+
        "\u00b1\2\55\1\u00b4\1\55\1\uffff\6\55\1\u00bc\1\60\1\uffff\1\u00be"+
        "\3\uffff\1\u00c1\1\u00c2\1\55\1\uffff\2\55\1\uffff\1\u00c6\1\u00c7"+
        "\1\uffff\2\55\1\u00ca\2\55\1\u00cd\1\55\3\uffff\1\u00d0\1\u00d1"+
        "\2\uffff\1\u00d2\2\55\2\uffff\1\u00d5\1\55\1\uffff\1\u00d7\1\55"+
        "\1\uffff\1\u00d9\4\uffff\1\u00db\1\55\1\uffff\1\55\1\uffff\1\u00de"+
        "\1\uffff\1\u00df\1\uffff\2\55\2\uffff\2\55\1\u00e4\1\55\1\uffff"+
        "\1\u00e6\1\uffff";
    static final String DFA16_eofS =
        "\u00e7\uffff";
    static final String DFA16_minS =
        "\1\11\1\56\1\52\2\56\2\uffff\4\56\13\uffff\7\56\1\uffff\1\76\2"+
        "\uffff\2\75\1\41\1\75\1\46\1\174\2\uffff\2\56\1\uffff\1\56\1\uffff"+
        "\1\60\1\56\1\uffff\1\101\5\56\3\uffff\6\56\1\uffff\13\56\23\uffff"+
        "\1\56\1\60\4\56\1\160\1\uffff\2\56\1\uffff\15\56\1\uffff\13\56\1"+
        "\uffff\1\60\2\56\1\uffff\1\56\1\156\5\56\1\uffff\2\56\1\uffff\1"+
        "\56\1\uffff\10\56\1\uffff\7\56\1\60\4\56\1\uffff\3\56\1\uffff\2"+
        "\56\1\uffff\2\56\1\uffff\7\56\1\uffff\1\56\1\uffff\2\56\2\uffff"+
        "\3\56\2\uffff\2\56\1\uffff\2\56\1\uffff\2\56\3\uffff\2\56\1\uffff"+
        "\1\56\1\uffff\1\56\1\uffff\1\56\1\uffff\2\56\2\uffff\4\56\1\uffff"+
        "\1\56\1\uffff";
    static final String DFA16_maxS =
        "\1\176\4\172\2\uffff\4\172\13\uffff\7\172\1\uffff\1\76\2\uffff"+
        "\1\75\1\76\1\77\1\75\1\46\1\174\2\uffff\2\172\1\uffff\1\172\1\uffff"+
        "\2\172\1\uffff\6\172\3\uffff\6\172\1\uffff\13\172\23\uffff\6\172"+
        "\1\160\1\uffff\2\172\1\uffff\15\172\1\uffff\13\172\1\uffff\3\172"+
        "\1\uffff\1\172\1\156\5\172\1\uffff\2\172\1\uffff\1\172\1\uffff\10"+
        "\172\1\uffff\14\172\1\uffff\3\172\1\uffff\2\172\1\uffff\2\172\1"+
        "\uffff\7\172\1\uffff\1\172\1\uffff\2\172\2\uffff\3\172\2\uffff\2"+
        "\172\1\uffff\2\172\1\uffff\2\172\3\uffff\2\172\1\uffff\1\172\1\uffff"+
        "\1\172\1\uffff\1\172\1\uffff\2\172\2\uffff\4\172\1\uffff\1\172\1"+
        "\uffff";
    static final String DFA16_acceptS =
        "\5\uffff\1\7\1\10\4\uffff\1\24\1\25\1\26\1\27\1\30\1\31\1\32\1"+
        "\33\1\34\1\35\1\36\7\uffff\1\55\1\uffff\1\57\1\61\6\uffff\1\75\1"+
        "\100\2\uffff\1\110\1\uffff\1\104\2\uffff\1\105\6\uffff\1\111\1\112"+
        "\1\60\6\uffff\1\14\13\uffff\1\74\1\56\1\67\1\62\1\65\1\102\1\63"+
        "\1\66\1\101\1\113\1\114\1\64\1\70\1\71\1\72\1\76\1\73\1\77\1\103"+
        "\7\uffff\1\106\2\uffff\1\15\15\uffff\1\16\13\uffff\1\107\3\uffff"+
        "\1\23\7\uffff\1\17\2\uffff\1\5\1\uffff\1\37\10\uffff\1\44\14\uffff"+
        "\1\13\3\uffff\1\40\2\uffff\1\53\2\uffff\1\41\7\uffff\1\42\1\uffff"+
        "\1\3\2\uffff\1\4\1\11\3\uffff\1\21\1\22\2\uffff\1\50\2\uffff\1\54"+
        "\2\uffff\1\6\1\12\1\20\2\uffff\1\43\1\uffff\1\51\1\uffff\1\1\1\uffff"+
        "\1\45\2\uffff\1\52\1\2\4\uffff\1\47\1\uffff\1\46";
    static final String DFA16_specialS =
        "\u00e7\uffff}>";
    static final String[] DFA16_transitionS = {
            "\2\53\1\uffff\2\53\22\uffff\1\53\1\44\1\25\2\uffff\1\40\1\45"+
            "\1\uffff\1\13\1\14\1\37\1\35\1\24\1\36\1\22\1\2\12\51\1\20\1"+
            "\21\1\43\1\41\1\42\1\17\1\uffff\32\52\1\5\1\uffff\1\6\1\50\1"+
            "\23\1\uffff\1\4\1\52\1\3\1\12\1\52\1\34\1\11\1\52\1\1\2\52\1"+
            "\32\1\7\1\26\1\27\1\33\2\52\1\31\1\10\1\52\1\30\4\52\1\15\1"+
            "\46\1\16\1\47",
            "\1\61\1\60\12\57\1\60\6\uffff\32\57\1\uffff\1\60\2\uffff\1"+
            "\56\1\uffff\15\57\1\54\14\57",
            "\1\70\4\uffff\1\67\12\60\7\uffff\32\60\6\uffff\2\60\1\64\5"+
            "\60\1\62\3\60\1\63\6\60\1\66\1\60\1\65\4\60",
            "\1\61\1\60\12\57\1\60\6\uffff\32\57\1\uffff\1\60\2\uffff\1"+
            "\56\1\uffff\13\57\1\72\2\57\1\73\13\57",
            "\1\61\1\60\12\57\1\60\6\uffff\32\57\1\uffff\1\60\2\uffff\1"+
            "\56\1\uffff\21\57\1\74\1\75\7\57",
            "",
            "",
            "\1\61\1\60\12\57\1\60\6\uffff\32\57\1\uffff\1\60\2\uffff\1"+
            "\56\1\uffff\1\77\15\57\1\76\13\57",
            "\1\61\1\60\12\57\1\60\6\uffff\32\57\1\uffff\1\60\2\uffff\1"+
            "\56\1\uffff\21\57\1\101\10\57",
            "\1\61\1\60\12\57\1\60\6\uffff\32\57\1\uffff\1\60\2\uffff\1"+
            "\56\1\uffff\24\57\1\102\5\57",
            "\1\61\1\60\12\57\1\60\6\uffff\32\57\1\uffff\1\60\2\uffff\1"+
            "\56\1\uffff\4\57\1\103\25\57",
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
            "\1\61\1\60\12\57\1\60\6\uffff\32\57\1\uffff\1\60\2\uffff\1"+
            "\56\1\uffff\1\104\31\57",
            "\1\61\1\60\12\57\1\60\6\uffff\32\57\1\uffff\1\60\2\uffff\1"+
            "\56\1\uffff\24\57\1\105\5\57",
            "\1\61\1\60\12\57\1\60\6\uffff\32\57\1\uffff\1\60\2\uffff\1"+
            "\56\1\uffff\1\106\31\57",
            "\1\61\1\60\12\57\1\60\6\uffff\32\57\1\uffff\1\60\2\uffff\1"+
            "\56\1\uffff\23\57\1\107\6\57",
            "\1\61\1\60\12\57\1\60\6\uffff\32\57\1\uffff\1\60\2\uffff\1"+
            "\56\1\uffff\1\110\31\57",
            "\1\61\1\60\12\57\1\60\6\uffff\32\57\1\uffff\1\60\2\uffff\1"+
            "\56\1\uffff\16\57\1\112\2\57\1\111\10\57",
            "\1\61\1\60\12\57\1\60\6\uffff\32\57\1\uffff\1\60\2\uffff\1"+
            "\56\1\uffff\1\113\31\57",
            "",
            "\1\114",
            "",
            "",
            "\1\116",
            "\1\120\1\121",
            "\1\125\32\uffff\1\124\1\123\1\uffff\1\126",
            "\1\130",
            "\1\132",
            "\1\134",
            "",
            "",
            "\2\60\12\137\7\uffff\32\141\1\uffff\1\60\2\uffff\1\140\1\uffff"+
            "\32\141",
            "\1\61\1\60\12\57\1\60\6\uffff\32\57\1\uffff\1\60\2\uffff\1"+
            "\56\1\uffff\32\57",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\2\57\1\142\2\57\1\143\11\57\1\144\12\57",
            "",
            "\12\57\7\uffff\32\57\6\uffff\32\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "",
            "\32\146\6\uffff\13\146\1\145\16\146",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\15"+
            "\60\1\147\14\60",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\1\150"+
            "\31\60",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\13"+
            "\60\1\152\2\60\1\153\13\60",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\1\154"+
            "\31\60",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\21"+
            "\60\1\155\10\60",
            "",
            "",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\1\156\31\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\15\57\1\157\14\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\6\57\1\160\23\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\22\57\1\161\7\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\3\57\1\162\26\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\10\57\1\163\10\57\1\164\10\57",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\1\165\23\57\1\166\5\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\1\170\31\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\13\57\1\171\16\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\14\57\1\172\15\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\23\57\1\173\6\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\21\57\1\174\10\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\1\175\31\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\1\57\1\176\30\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\4\57\1\177\25\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\22\57\1\u0080\7\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\13\57\1\u0081\16\57",
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
            "\2\60\12\137\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff"+
            "\32\60",
            "\12\u0082\7\uffff\32\u0082\6\uffff\32\u0082",
            "\2\60\12\u0085\7\uffff\32\u0085\1\uffff\1\60\2\uffff\1\u0084"+
            "\1\uffff\32\u0085",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\13\57\1\u0086\16\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\24\57\1\u0088\5\57",
            "\1\u0089",
            "",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\2\60"+
            "\1\u008a\27\60",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\10"+
            "\60\1\u008b\21\60",
            "",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\1\u008c"+
            "\31\60",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\15"+
            "\60\1\u008d\14\60",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\21"+
            "\60\1\u008e\10\60",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\22\57\1\u0090\7\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\22\57\1\u0091\7\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\4\57\1\u0093\25\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\15\57\1\u0095\14\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\12\57\1\u0096\17\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\15\57\1\u0097\14\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\4\57\1\u0098\25\57",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\21\57\1\u0099\10\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\1\u009a\31\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\4\57\1\u009b\25\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\17\57\1\u009c\12\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\23\57\1\u009e\6\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\4\57\1\u009f\25\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\22\57\1\u00a0\7\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\23\57\1\u00a1\6\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\22\57\1\u00a2\7\57",
            "\2\60\12\u0085\7\uffff\32\u0085\1\uffff\1\60\2\uffff\1\u0084"+
            "\1\uffff\32\u0085",
            "",
            "\12\u0085\7\uffff\32\u0085\6\uffff\32\u0085",
            "\2\60\12\u0085\7\uffff\32\u0085\1\uffff\1\60\2\uffff\1\u0084"+
            "\1\uffff\32\u0085",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\24\57\1\u00a3\5\57",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\23\57\1\u00a4\6\57",
            "\1\u00a5",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\13"+
            "\60\1\u00a6\16\60",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\15"+
            "\60\1\u00a7\14\60",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\22"+
            "\60\1\u00a8\7\60",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\22"+
            "\60\1\u00a9\7\60",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\32"+
            "\60",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\22\57\1\u00ab\7\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\23\57\1\u00ac\6\57",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\21\57\1\u00ad\10\57",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\10\57\1\u00af\21\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\22\57\1\u00b0\7\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\3\57\1\u00b2\26\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\30\57\1\u00b3\1\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\24\57\1\u00b5\5\57",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\4\57\1\u00b6\25\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\13\57\1\u00b7\16\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\4\57\1\u00b8\25\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\22\57\1\u00b9\7\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\4\57\1\u00ba\25\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\3\57\1\u00bb\26\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "\12\146\7\uffff\32\146\4\uffff\1\146\1\uffff\32\146",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\24"+
            "\60\1\u00bd\5\60",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\32"+
            "\60",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\22"+
            "\60\1\u00bf\7\60",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\23"+
            "\60\1\u00c0\6\60",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\23\57\1\u00c3\6\57",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\15\57\1\u00c4\14\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\10\57\1\u00c5\21\57",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\23\57\1\u00c8\6\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\25\57\1\u00c9\4\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\23\57\1\u00cb\6\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\4\57\1\u00cc\25\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\4\57\1\u00ce\25\57",
            "",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\3\60"+
            "\1\u00cf\26\60",
            "",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\32"+
            "\60",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\32"+
            "\60",
            "",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\6\57\1\u00d3\23\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\23\57\1\u00d4\6\57",
            "",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\4\57\1\u00d6\25\57",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\23\57\1\u00d8\6\57",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\4\60"+
            "\1\u00da\25\60",
            "",
            "",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\10\57\1\u00dc\21\57",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\2\57\1\u00dd\27\57",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "",
            "\14\60\7\uffff\32\60\1\uffff\1\60\2\uffff\1\60\1\uffff\32"+
            "\60",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\16\57\1\u00e0\13\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\23\57\1\u00e1\6\57",
            "",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\15\57\1\u00e2\14\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\16\57\1\u00e3\13\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\21\57\1\u00e5\10\57",
            "",
            "\1\61\1\60\12\57\7\uffff\32\57\1\uffff\1\60\2\uffff\1\56\1"+
            "\uffff\32\57",
            ""
    };

    static final short[] DFA16_eot = DFA.unpackEncodedString(DFA16_eotS);
    static final short[] DFA16_eof = DFA.unpackEncodedString(DFA16_eofS);
    static final char[] DFA16_min = DFA.unpackEncodedStringToUnsignedChars(DFA16_minS);
    static final char[] DFA16_max = DFA.unpackEncodedStringToUnsignedChars(DFA16_maxS);
    static final short[] DFA16_accept = DFA.unpackEncodedString(DFA16_acceptS);
    static final short[] DFA16_special = DFA.unpackEncodedString(DFA16_specialS);
    static final short[][] DFA16_transition;

    static {
        int numStates = DFA16_transitionS.length;
        DFA16_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA16_transition[i] = DFA.unpackEncodedString(DFA16_transitionS[i]);
        }
    }

    class DFA16 extends DFA {

        public DFA16(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 16;
            this.eot = DFA16_eot;
            this.eof = DFA16_eof;
            this.min = DFA16_min;
            this.max = DFA16_max;
            this.accept = DFA16_accept;
            this.special = DFA16_special;
            this.transition = DFA16_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | LPAREN | RPAREN | LBRACK | RBRACK | QMARK | COLON | SEMICOLON | PERIOD | UNDERSCORE | COMMA | QUOTE | MODULE | MAIN | NAME | INPUT | OUTPUT | INTERNAL | MARKING | STATE_VECTOR | TRANSITION | LABEL | PRESET | POSTSET | TRUE | FALSE | PLUS | MINUS | TIMES | DIV | MOD | EQUALS | GREATER | LESS | GREATER_EQUAL | LESS_EQUAL | EQUIV | NOT_EQUIV | NEGATION | AND | OR | IMPLICATION | BITWISE_NEGATION | BITWISE_AND | BITWISE_OR | BITWISE_XOR | BITWISE_LSHIFT | BITWISE_RSHIFT | INT | ID | PATH | MEMBER | LABELSTRING | WS | COMMENT | MULTILINECOMMENT | XMLCOMMENT | IGNORE );";
        }
    }
 

}