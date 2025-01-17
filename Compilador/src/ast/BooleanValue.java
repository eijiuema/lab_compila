/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class BooleanValue extends BasicValue{
    
    private boolean value;
    
    public BooleanValue( boolean value ){
        this.value = value;
    }

    @Override
    public void genJava(PW pw) {
        pw.print(Boolean.toString(value));
    }

    public void genC(PW pw) {
        pw.print(Boolean.toString(value));
    }

    public Type getType() {
        return Type.booleanType;
    }

}