/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class PrimaryExprSelf extends PrimaryExpr{

    private Id selfId;

    public PrimaryExprSelf() {
        
    }
    
    public PrimaryExprSelf(Id selfId){
        this.selfId = selfId;
    }

    public void genJava(PW pw){
        
    }

    public Type getType() {
        return selfId.getType();
    }
}