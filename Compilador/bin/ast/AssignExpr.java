package ast;
import java.util.*;

public class AssignExpr{
    private Expr leftExpr;
    private Expr rightExpr;    

    public AssignExpr(Expr leftExpr, Expr rightExpr) {
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;        
    }

    public AssignExpr(Expr leftExpr) {
        this.leftExpr = leftExpr;
        this.rightExpr = null;      
    }

    public void genJava(PW pw){

        leftExpr.genJava();
        
        if(relation != ' ')
            pw.print(signal);
        
        if(rightExpr != null)
            rightExpr.genJava();
    }
    
}