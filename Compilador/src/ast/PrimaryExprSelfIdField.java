/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class PrimaryExprSelfIdField extends PrimaryExpr{

    private Id id, field;
    
    public PrimaryExprSelfIdField(Id id, Id field){
        this.id = id;
        this.field = field;
    }

    public void genC(PW pw) {
//TODO genC
}
public void genJava(PW pw){
        pw.print("this");
        pw.print(".");
        pw.print(this.id.getName());
        pw.print(".");
        pw.print(this.field.getName());
    }

    public Type getType() {
        return field.getType();
    }

    @Override
    public boolean isAssignable() {
        return true;
    }
}