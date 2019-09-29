/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class PrimaryExprSelfIdId extends PrimaryExpr{

    private Id selfId, firstId, secondId;
    
    public PrimaryExprSelfIdId(Id selfId, Id firstId, Id secondId){
        this.selfId = selfId;
        this.firstId = firstId;
        this.secondId = secondId;
    }

    public void genJava(PW pw){
        pw.print("this");
        pw.print(".");
        pw.print(this.firstId.getName());
        pw.print(".");
        pw.print(this.secondId.getName());
    }

    public Type getType() {
        return secondId.getType();
    }
}