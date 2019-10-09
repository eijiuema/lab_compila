/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class Expr extends Factor {
    private SimpleExpr leftSimpleExpr;
    private String relation;
    private SimpleExpr rightSimpleExpr;

    public Expr(SimpleExpr leftSimpleExpr, String relation, SimpleExpr rightSimpleExpr) {
        this.leftSimpleExpr = leftSimpleExpr;
        this.relation = relation;
        this.rightSimpleExpr = rightSimpleExpr;
    }

    public Expr(SimpleExpr leftSimpleExpr) {
        this.leftSimpleExpr = leftSimpleExpr;
        this.relation = null;
        this.rightSimpleExpr = null;
    }

    public void genJava(PW pw) {

        leftSimpleExpr.genJava(pw);

        if (relation != null)
            pw.print(" " + relation + " ");

        if (rightSimpleExpr != null)
            rightSimpleExpr.genJava(pw);
    }

    public Type getType() {
        if (relation == null)
            return this.leftSimpleExpr.getType();
        else
            return Type.booleanType;
    }

    public boolean isAssignable() {
        return this.leftSimpleExpr.isAssignable();
    }

}
