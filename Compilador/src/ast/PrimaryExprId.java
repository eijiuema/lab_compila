/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class PrimaryExprId extends PrimaryExpr{

    private Id id;
    public PrimaryExprId(Id id){
        this.id = id;
    }

    public void genJava(PW pw){
        pw.print(this.id.getName());
    }

    public Type getType() {
        return id.getType();
    }

    @Override
    public boolean isAssignable() {
        return true;
    }
}