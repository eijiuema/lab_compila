/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class PrimaryExprSelfId extends PrimaryExpr{

    private Id firstId, secondId;
    public PrimaryExprSelfId(Id selfId, Id id){
        this.firstId = selfId;
        this.secondId = id;
    }

    public void genJava(PW pw){
        pw.print("this");
        pw.print(".");
        pw.print(this.secondId.getName());
    }

    public Type getType() {
        return secondId.getType();
    }
}