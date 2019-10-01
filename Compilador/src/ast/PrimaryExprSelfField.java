/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class PrimaryExprSelfField extends PrimaryExpr{

    private Id selfId, field;
    public PrimaryExprSelfField(Id selfId, Id field){
        this.selfId = selfId;
        this.field = field;
    }

    public void genJava(PW pw){
        pw.print("this");
        pw.print(".");
        pw.print(this.field.getName());
    }

    public Type getType() {
        return field.getType();
    }
}