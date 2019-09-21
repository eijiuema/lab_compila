package ast;
import java.util.*;

public class ExprFactor extends Factor{
    
    Expr expr;

    public ExprFactor( Expr expr){
        this.expr = expr;
    }

    public void genJava(PW pw){
        pw.print("{");
        expr.genJava(pw);
        pw.print("}");
    }    
}