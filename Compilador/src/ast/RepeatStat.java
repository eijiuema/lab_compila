/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class RepeatStat extends Stat {

    private List<Stat> statList;
    private Expr expr;    

    public RepeatStat(List<Stat> statList, Expr expr) {
        this.expr = expr;
        this.statList = statList;        
    }

    public void genJava(PW pw){
        //...
    }
/*
    @Override
    public void genC(PW pw) {
        // TODO Auto-generated method stub

    }
*/    
}