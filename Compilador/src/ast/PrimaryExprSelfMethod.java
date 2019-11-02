/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.*;

public class PrimaryExprSelfMethod extends PrimaryExpr {

    private Id method;
    List<Expr> exprList;

    public PrimaryExprSelfMethod(Id method, List<Expr> exprList) {
        this.method = method;
        this.exprList = exprList;
    }

    public PrimaryExprSelfMethod(Id method) {
        this.method = method;
        this.exprList = new ArrayList<>();
    }

    public void genC(PW pw) {
        //TODO genc
        pw.print("FALTAIMPLEMENTAR");
    }
    
    public void genJava(PW pw) {
        pw.print("this.");
        pw.print(this.method.getName());
        pw.print("(");
        for (Expr expr : this.exprList) {
            expr.genJava(pw);
            if (!expr.equals(this.exprList.get(this.exprList.size() - 1))) {
                pw.print(", ");
            }

        }
        pw.print(")");
    }

    public Type getType() {
        return method.getType();
    }
    
    @Override
    public boolean hasMethodCallWithReturn() {
        return method.getType() != Type.undefinedType;
    }
}