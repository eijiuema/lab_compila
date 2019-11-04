/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class PrimaryExprSuperField extends PrimaryExpr {

    private Id field;
    private Id superClass;
    
    public PrimaryExprSuperField(Id superClass, Id field){
        this.field = field;
        this.superClass = superClass;
    }

    public void genC(PW pw) {
        pw.print(this.superClass.getCName());
        pw.print("->");
        pw.print(this.field.getCName());
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