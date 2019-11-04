/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class PrimaryExprSelfIdField extends PrimaryExpr{

    private Id id, field;
    private TypeCianetoClass self;

    public PrimaryExprSelfIdField(TypeCianetoClass self, Id id, Id field){
        this.id = id;
        this.field = field;
        this.self = self;
    }

    public void genC(PW pw) {
        pw.print("((" + self.getCname() + "*)");
        pw.print("self)->" + this.id.getCName() + "->"+ this.field.getCName());
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