/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.*;

public class PrimaryExprSuperIdColon extends PrimaryExpr {

    private Id superId;
    private Id id;
    List<Expr> exprList;

    public PrimaryExprSuperIdColon(Id superId, Id id, List<Expr> exprList) {
        this.superId = superId;
        this.id = id;
        this.exprList = exprList;
    }

    public void genJava(PW pw) {
        pw.print("super");
        pw.print(".");
        pw.print(this.id.getName());
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
        return id.getType();
    }
}