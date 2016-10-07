// define a grammar called Hello
grammar Hello;
r   : 'hello' (conditional | assignment ';')+;
conditional : 'if' BOOL (';' | ':' conditional) ;
assignment : Identifier '=' Identifier;
BOOL: 'false' | 'true';
Identifier  : [a-z]+ ;
WS  : [ \t\r\n]+ -> skip ;
