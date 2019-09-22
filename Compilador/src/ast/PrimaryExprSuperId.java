/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class PrimaryExprSuperId extends PrimaryExpr{

    private Id firstId, secondId;
    public PrimaryExprSuperId(Id superId, Id id){
        this.firstId = superId;
        this.secondId = id;
    }

    public void genJava(PW pw){
        
    }

    public Type getType() {
        return secondId.getType();
    }
}