package ast;
import java.util.*;

public class WriteStat extends Stat{
    private Expr expr;    

    public WriteStat(Expr expr) {
        this.expr = expr;      
    }

    public void genJava(PW pw){

        pw.print("System.out.print(");

        expr.genJava(pw);

        pw.print(")");
        
    }
    
}