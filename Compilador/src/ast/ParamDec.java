/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class ParamDec {
    private Id id;
    private Type type;    

    public ParamDec(Type type, Id id) {
        this.type = type;
        this.id = id;
    }

    public void genC(PW pw) {
//TODO genC
}
public void genJava(PW pw){
        pw.print(type.getJavaName());
        pw.print(" ");
        pw.print(id.getName());
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
}