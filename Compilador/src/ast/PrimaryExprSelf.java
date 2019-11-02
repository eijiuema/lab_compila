/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class PrimaryExprSelf extends PrimaryExpr{

    private Id selfId;

    public PrimaryExprSelf(Id selfId) {
        this.selfId = selfId;
    }

    public void genC(PW pw) {
        pw.print("self");
    }
    public void genJava(PW pw){
        pw.print("this");
    }

    public Type getType() {
        return selfId.getType();
    }
}