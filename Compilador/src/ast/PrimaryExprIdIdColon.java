/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class PrimaryExprIdIdColon extends PrimaryExpr{

    private Id id, idColon;
    private List<Expr> exprList;

    public PrimaryExprIdIdColon(Id id, Id idColon, List<Expr> exprList){
        this.id = id;
        this.idColon = idColon;
        this.exprList = exprList;
    }

    public void genJava(PW pw){
        
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