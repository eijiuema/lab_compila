/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class IfStat extends Stat{
    private List<Stat> ifStList, elseStList;
    private Expr expr;    

    public IfStat(Expr expr, List<Stat> ifStList, List<Stat> elseStList) {
        this.expr = expr;
        this.ifStList = ifStList;
        this.elseStList = elseStList;        
    }

    public void genJava(PW pw){
        //...
    }
    
}