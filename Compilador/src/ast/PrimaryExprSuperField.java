/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class PrimaryExprSuperField extends PrimaryExpr {

    private Id field;
    
    public PrimaryExprSuperField(Id field){
        this.field = field;
    }

    public void genC(PW pw) {
//TODO genC
}
public void genJava(PW pw) {
        pw.print("super");
        pw.print(".");
        pw.print(this.field.getName());

    }

    public Type getType() {
        return field.getType();
    }
}