/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class PrimaryExprSelfIdColon extends PrimaryExpr{

    private Id selfId, idColon;
    List<Expr> exprList;
    public PrimaryExprSelfIdColon(Id selfId, Id idColon, List<Expr> exprList){
        this.selfId = selfId;
        this.idColon = idColon;
        this.exprList = exprList;
    }

    public void genJava(PW pw){
        
    }

    public Type getType() {
        return idColon.getType();
    }
}