/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class PrimaryExprSelfIdMethod extends PrimaryExpr{

    private Id selfId, id, method;
    List<Expr> exprList;

    public PrimaryExprSelfIdMethod(Id selfId, Id id, Id method, List<Expr> exprList){
        this.selfId = selfId;
        this.id = id;
        this.method = method;
        this.exprList = exprList;
    }

    public void genJava(PW pw){
        pw.print("this");
        pw.print(".");
        pw.print(this.id.getName());
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