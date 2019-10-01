/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class PrimaryExprSelfId extends PrimaryExpr{

    private Id id;
    public PrimaryExprSelfId(Id id){
        this.id = id;
    }

    public void genJava(PW pw){
        pw.print("this");
        pw.print(".");
        pw.print(this.id.getName());
    }

    public Type getType() {
        return id.getType();
    }
}