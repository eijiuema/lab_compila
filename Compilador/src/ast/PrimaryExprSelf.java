/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class PrimaryExprSelf extends PrimaryExpr{

    private Id selfId;
    private TypeCianetoClass self;

    public PrimaryExprSelf(TypeCianetoClass self, Id selfId) {
        this.selfId = selfId;
        this.self = self;
    }

    public void genC(PW pw) {
        pw.print("((" + self.getCname() + "*)");
        pw.print("self)");
    }
    public void genJava(PW pw){
        pw.print("this");
    }

    public Type getType() {
        return selfId.getType();
    }
}