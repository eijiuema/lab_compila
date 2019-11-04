/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class PrimaryExprSelfField extends PrimaryExpr {

    private Id field;
    private TypeCianetoClass self;

    public PrimaryExprSelfField(TypeCianetoClass self, Id field) {
        this.self = self;
        this.field = field;
	}

	public void genC(PW pw) {
        pw.print("((" + self.getCname() + "*)");
        pw.print("self)->" + self.getCname() + this.field.getCName());
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