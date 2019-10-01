/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class PrimaryExprSelfIdField extends PrimaryExpr{

    private Id selfId, id, field;
    
    public PrimaryExprSelfIdField(Id selfId, Id id, Id field){
        this.selfId = selfId;
        this.id = id;
        this.field = field;
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
}