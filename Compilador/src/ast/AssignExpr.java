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

    public void genC(PW pw) {
        pw.printIdent("");
        leftExpr.genC(pw);
        
        if(rightExpr != null){
            pw.print(" = ");

            //Cast
            if(leftExpr.getType() != rightExpr.getType() && rightExpr.getType() != Type.nilType){
                pw.print("(");
                pw.print(leftExpr.getType().getCname() + "*");
                pw.print(") ");
            }

            rightExpr.genC(pw);
        }
        
        pw.println(";");
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
}