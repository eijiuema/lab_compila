/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class AssertStat extends Stat {
    
    private StringValue stringvalue;
    private Expr expr;    

    public AssertStat(Expr expr,StringValue stringvalue) {
        this.expr = expr;
        this.stringvalue = stringvalue;        
    }

    public void genC(PW pw) {      
        pw.printIdent("if ((");
        expr.genC(pw);
        pw.print(") == false ) ");
        pw.printIdent("printf(");
        stringvalue.genC(pw);
        pw.println(");");
    }

    public void genJava(PW pw){
        pw.printIdent("assert ");
        this.expr.genJava(pw);
        pw.print(" : ");
        this.stringvalue.genJava(pw);
        pw.println(";");
    }
 
}