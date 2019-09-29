/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class WriteStat extends Stat {

    private Expr expr; 
    private String printName;   

    public WriteStat(Expr expr, String printName) {
        this.expr = expr;
        this.printName = printName;      
    }

    public void genJava(PW pw){

        pw.printIdent("System.out.");
        pw.print(this.printName);
        pw.print("(");

        expr.genJava(pw);

        pw.println(");");
        
    }
/*
    @Override
    public void genC(PW pw) {
        // TODO Auto-generated method stub
    }
*/    
}