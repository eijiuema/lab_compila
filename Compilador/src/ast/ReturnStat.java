/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class ReturnStat extends Stat {
    private Expr expr;    

    public ReturnStat(Expr expr) {
        this.expr = expr;        
    }

    public void genJava(PW pw){
        //...
    }
/*
    @Override
    public void genC(PW pw) {
        
    }
*/    
}