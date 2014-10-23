grammar ctl;

options {
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
	:	AG (ltlNegation)*
	|	AF (ltlNegation)*
	|	AX (ltlNegation)*
	|	EG (ltlNegation)*
	|	EF (ltlNegation)*
	|	EX (ltlNegation)*
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
	:	ltlImp (U ltlImp)*
	;
		
	
AG	:	'AG';
AF	:	'AF';
AX	:	'AX';
EG	:	'EG';
EF	:	'EF';
EX	:	'EX';
U	:	'U';
	
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
//ID	:	DIGIT;
