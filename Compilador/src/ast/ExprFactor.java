/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class ExprFactor extends Factor {

    Expr expr;

    public ExprFactor(Expr expr) {
        this.expr = expr;
    }

    public void genC(PW pw) {
        pw.print("( ");
        expr.genC(pw);
        pw.print(" )");
    
    }

    public void genJava(PW pw) {
        pw.print("(");
        expr.genJava(pw);
        pw.print(")");
    }

    public Type getType() {
        return this.expr.getType();
    }
}