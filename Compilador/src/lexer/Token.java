/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package lexer;

public enum Token {

    AND("&&"),
    ANNOT("~annotation"),
    ASSERT("assert"),
    ASSIGN("="),
    BOOLEAN("Boolean"),
    BREAK("break"),
    CLASS("class"),
    COMMA(","),
    DIV("/"),
    DOT("."),
    ELSE("else"),
    END("end"),
    EOF("~eof"),
    EQ("=="),
    EXTENDS("extends"),
    FALSE("false"),
    FINAL("final"),
    FUNC("func"),
    GE(">="),
    GT(">"),
    ID("~ident"),
    IDCOLON("~ident:"),
    IF("if"),
    INT("Int"),
    LE("<="),
    LEFTCURBRACKET("{"),
    LEFTPAR("("),
    LITERALINT("~number"),
    LITERALSTRING("~literalString"),
    LT("<"),
    MINUS("-"),
    MINUS_GT("->"),
    MULT("*"),
    NEQ("!="),
    NEW("new"),
    NOT("!"),
    NIL("nil"),
    OR("||"),
    OVERRIDE("override"),
    PLUS("+"),
    PLUSPLUS("++"),
    PRIVATE("private"),
    PUBLIC("public"),
    READ("read"),
    REPEAT("repeat"),
    RETURN("return"),
    RIGHTCURBRACKET("}"),
    RIGHTPAR(")"),
    SELF("self"),
    SEMICOLON(";"),
    STRING("String"),
    SUPER("super"),
    TRUE("true"),
    UNTIL("until"),
    VAR("var"),
    VOID("void"),
    WHILE("while"),
    WRITE("write"),
    WRITELN("writeln");

	Token(String name) {
		this.name = name;
	}

	@Override public String toString() {
		return name;
	}
	private String name;
}