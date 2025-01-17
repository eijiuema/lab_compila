/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class NilFactor extends Factor{

    public void genC(PW pw) {
        pw.print(Type.nilType.getCname());
    }

    public void genJava(PW pw){
        pw.print(Type.nilType.getJavaName());
    }

    public Type getType() {
        return Type.nilType;
    }

}