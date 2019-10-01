/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class PrimaryExprSuperField extends PrimaryExpr{

    private Id superId, field;
    
    public PrimaryExprSuperField(Id superId, Id field){
        this.superId = superId;
        this.field = field;
    }

    public void genJava(PW pw){
        pw.print("super");
        pw.print(".");
        pw.print(this.field.getName());
        
    }

    public Type getType() {
        return field.getType();
    }
}