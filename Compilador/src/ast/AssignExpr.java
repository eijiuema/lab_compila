/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
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
        pw.printIdent("");
        leftExpr.genJava(pw);
        
        if(rightExpr != null){
            pw.print(" = ");
            rightExpr.genJava(pw);
        }
        
        pw.println(";");
    }
/*
    @Override
    public void genC(PW pw) {
        // TODO Auto-generated method stub

    }
*/    
}