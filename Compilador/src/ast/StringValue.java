/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class StringValue extends BasicValue{
    
    private String value;

    public StringValue( String value){
        this.value = value;
    }

    public void genC(PW pw) {
//TODO genC
}
public void genJava(PW pw){
        pw.print("\"");
        pw.print(value);
        pw.print("\"");
    }


    public Type getType() {
        return Type.stringType;
    }

}