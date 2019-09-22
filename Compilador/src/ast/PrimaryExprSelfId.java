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
        
    }

    public Type getType() {
        return secondId.getType();
    }
}