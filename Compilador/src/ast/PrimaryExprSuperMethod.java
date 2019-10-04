/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.*;

public class PrimaryExprSuperMethod extends PrimaryExpr {

    private Id method;
    List<Expr> exprList;

    public PrimaryExprSuperMethod(Id method) {
        this.method = method;
        this.exprList = new ArrayList<>();
    }

    public PrimaryExprSuperMethod(Id method, List<Expr> exprList) {
        this.method = method;
        this.exprList = exprList;
    }

    public void genJava(PW pw) {
        pw.print("super");
        pw.print(".");
        pw.print(this.method.getName());
        pw.print("(");
        for(Expr expr: this.exprList){
            expr.genJava(pw);
            if( !expr.equals(this.exprList.get(this.exprList.size()-1)) ){
                pw.print(", ");
            }

        }
        pw.print(")");
    }

    public Type getType() {
        return method.getType();
    }
}