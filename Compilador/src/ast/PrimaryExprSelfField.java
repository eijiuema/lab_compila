/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class PrimaryExprSelfField extends PrimaryExpr {

    private Id field;

    public PrimaryExprSelfField(Id field) {
        this.field = field;
    }

    public void genC(PW pw) {
        //TODO genc
        pw.print("FALTAIMPLEMENTAR");
    }
    
    public void genJava(PW pw) {
        pw.print("this");
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