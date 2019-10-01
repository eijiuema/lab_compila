/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class ReadExpr extends PrimaryExpr {

    private Type type;

    public ReadExpr(Type type) {
        this.type = type;
    }

    public void genJava(PW pw) {

        if (type == Type.intType)
            pw.print("In.nextInt()");
        else if (type == Type.stringType)
            pw.print("In.nextLine()");
    }

    public Type getType() {
        return type;
    }

}