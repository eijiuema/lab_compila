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
        
    }

    public Type getType() {
        return idColon.getType();
    }
}