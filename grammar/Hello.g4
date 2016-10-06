// define a grammar called Hello
grammar Hello;
r   : 'hello' IDX;
IDX  : [a-z]+ ;
WS  : [ \t\r\n]+ -> skip ;
