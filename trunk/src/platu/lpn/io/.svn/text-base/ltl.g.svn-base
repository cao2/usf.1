grammar ltl;

options{
	language = Java;
}

@header{
    package platu.lpn.io;
}

@lexer::header{
    package platu.lpn.io;
}

start	:	ltlExpr SEMICOLON
	;
	
ltlTerm
	:	TRUE
	|	FALSE
	|	ID
	|	LPAREN ltlExpr RPAREN
	;
	
ltlNegation
	:	(NOT)* ltlTerm
	;
	
ltlUnary
	:	G (ltlNegation)*
	|	F (ltlNegation)*
	|	X (ltlNegation)*
	;

ltlAnd
	:	ltlUnary (LAND ltlUnary)*
	;
		
ltlOr
	:	ltlAnd (LOR ltlAnd)*
	;
	
ltlXor
	:	ltlOr (LXOR ltlOr)*
	;
	
ltlEquiv
	:	ltlXor (EQUIV ltlXor)*
	;
	
ltlImp
	:	ltlEquiv (IMPL ltlEquiv)*	
	;	

ltlExpr	
	:	ltlImp ((U | R) ltlImp)*
	;
		
	
G	:	'G';
F	:	'F';
X	:	'X';
U	:	'U';
R	:	'R';
	
TRUE	:	('true');
FALSE	:	('false');
SEMICOLON	:	';';
LPAREN	:	'(';
RPAREN	:	')';
NOT	:	'!';
LOR	:	'+';
LAND	:	'*';
LXOR	:	'^';
EQUIV	:	'<->';
IMPL	:	'->';

fragment LETTER: ('a'..'z' | 'A'..'Z');
fragment DIGIT: '0'..'9';
ID: LETTER ('_'? (LETTER | DIGIT))*;

WS: (' ' | '\t' | '\n' | '\r' | '\f')+ {$channel = HIDDEN;};
COMMENT: '//' .* ('\n' | '\r') {$channel = HIDDEN;};
MULTILINECOMMENT: '/*' .* '*/' {$channel = HIDDEN;};
XMLCOMMENT: ('<' '!' '-' '-') .* ('-' '-' '>') {$channel = HIDDEN;};
IGNORE: '<' '?' .* '?' '>' {$channel = HIDDEN;};
