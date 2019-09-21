package ast;

public class AssignExpr extends Stat {

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

        leftExpr.genJava(pw);

        pw.print("=");
        
        if(rightExpr != null)
            rightExpr.genJava(pw);
    }
/*
    @Override
    public void genC(PW pw) {
        // TODO Auto-generated method stub

    }
*/    
}