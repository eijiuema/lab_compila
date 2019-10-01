/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class PrimaryExprIdField extends PrimaryExpr{

    private Id id, field;
    
    public PrimaryExprIdField(Id id, Id field){
        this.id = id;
        this.field = field;
    }

    public void genJava(PW pw){
        pw.print(this.id.getName());
        pw.print(".");
        pw.print(this.field.getName());
    }

    public Type getType() {
        return field.getType();
    }
}