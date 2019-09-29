/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class PrimaryExprSelfIdIdColon extends PrimaryExpr{

    private Id selfId, id, idColon;
    List<Expr> exprList;

    public PrimaryExprSelfIdIdColon(Id selfId, Id id, Id idColon, List<Expr> exprList){
        this.selfId = selfId;
        this.id = id;
        this.idColon = idColon;
        this.exprList = exprList;
    }

    public void genJava(PW pw){
        pw.print("this");
        pw.print(".");
        pw.print(this.id.getName());
        pw.print(".");
        pw.print(this.idColon.getName());
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
        return idColon.getType();
    }
}