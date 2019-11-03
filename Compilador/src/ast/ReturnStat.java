/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class ReturnStat extends Stat {
    private Expr expr;
    private Type returnType;

    public ReturnStat(Expr expr, Type returnType) {
        this.returnType = returnType;
        this.expr = expr;        
    }

    public void genC(PW pw) {
        pw.printIdent("return ");
        
        //Cast
        pw.print("(");
        pw.print(returnType.getCname());
        if(!returnType.isBasicType())
            pw.print("* ");
        pw.print(") ");
        
        this.expr.genC(pw);
        pw.println(";");
    
    }

    public void genJava(PW pw){
        pw.printIdent("return ");
        this.expr.genJava(pw);
        pw.println(";");
    }
 
}