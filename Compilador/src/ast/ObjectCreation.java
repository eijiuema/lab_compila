/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class ObjectCreation extends Factor {

    private Id id;

    public ObjectCreation(Id id) {
        this.id = id;
    }

    public void genC(PW pw) {
        pw.print("new_");
        pw.print(this.id.getName());
        pw.print("()");
    }
    public void genJava(PW pw) {
        pw.print("new ");
        pw.print(this.id.getName());
        pw.print("()");
    }

    public Type getType() {
        return this.id.getType();
    }
}