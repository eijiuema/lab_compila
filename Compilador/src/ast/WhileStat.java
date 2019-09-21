package ast;
import java.util.*;

public class WhileStat extends Stat{
    private StatList statList;
    private Expr expr;    

    public WhileStat(StatList statList, Expr expr) {
        this.expr = expr;
        this.statList = statList;        
    }

    public void genJava(PW pw){
        //...
    }
    
}