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


    public void genC(PW pw) {
        
        if (type == Type.intType)
            pw.print("readInt()");
        else if (type == Type.stringType)
            pw.print("readString()");
    }

    public void genJava(PW pw) {

        if (type == Type.intType)
            pw.print("(new Scanner(System.in)).nextInt()");
        else if (type == Type.stringType)
            pw.print("(new Scanner(System.in)).nextLine()");
    }

    public Type getType() {
        return type;
    }

}