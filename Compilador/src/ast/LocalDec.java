/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class LocalDec extends Stat {
    private Type decType;
    private List<Id> idList;
    private Expr expr;    

    public LocalDec( Type decType, List<Id> idList, Expr expr) {
        this.expr = expr;      
        this.decType = decType;
        this.idList = idList;
    }

    public void genJava(PW pw){
    }
    
}