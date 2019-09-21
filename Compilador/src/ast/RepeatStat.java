package ast;
import java.util.*;

public class RepeatStat extends Stat{
    private StatList statList;
    private Expr expr;    

    public RepeatStat(StatList statList, Expr expr) {
        this.expr = expr;
        this.statList = statList;        
    }

    public void genJava(PW pw){
        //...
    }
    
}