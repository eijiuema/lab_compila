package ast;
import java.util.*;

public class WhileStat extends Stat {
    private List<Stat> statList;
    private Expr expr;    

    public WhileStat(List<Stat> statList, Expr expr) {
        this.expr = expr;
        this.statList = statList;        
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