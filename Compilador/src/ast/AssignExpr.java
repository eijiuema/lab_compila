package ast;
import java.util.*;

public class AssignExpr extends Stat{
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

        pw.print("=");
        
        if(rightExpr != null)
            rightExpr.genJava();
    }
    
}