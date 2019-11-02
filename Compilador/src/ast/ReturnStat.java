/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class ReturnStat extends Stat {
    private Expr expr;    

    public ReturnStat(Expr expr) {
        this.expr = expr;        
    }

    public void genC(PW pw) {
        pw.printIdent("return ");
        this.expr.genC(pw);
        pw.println(";");
    
    }

    public void genJava(PW pw){
        pw.printIdent("return ");
        this.expr.genJava(pw);
        pw.println(";");
    }
 
}