/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class PrimaryExprSelfMethod extends PrimaryExpr{

    private Id selfId, method;
    List<Expr> exprList;
    public PrimaryExprSelfMethod(Id selfId, Id method, List<Expr> exprList){
        this.selfId = selfId;
        this.method = method;
        this.exprList = exprList;
    }

    public void genJava(PW pw){
        pw.print("this");
        pw.print(".");
        pw.print(this.method.getName());
    }

    public Type getType() {
        return method.getType();
    }
}