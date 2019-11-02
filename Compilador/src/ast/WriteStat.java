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

    public void genC(PW pw) {
        pw.printIdent("printf(\"");
        if(expr.getType() == Type.stringType)
            pw.print("%s");
        else
            pw.print("%d");
        if(this.printName.equals("println"))
            pw.print("\\n");
        pw.println("\", ");

        this.expr.genC(pw);
        
        pw.println(");");
    }

    public void genJava(PW pw){

        pw.printIdent("System.out.");
        pw.print(this.printName);
        pw.print("(");

        expr.genJava(pw);

        pw.println(");");
        
    }
   
}