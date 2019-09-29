/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class WhileStat extends Stat {
    private List<Stat> statList;
    private Expr expr;    

    public WhileStat(List<Stat> statList, Expr expr) {
        this.expr = expr;
        this.statList = statList;        
    }

    public void genJava(PW pw){
        pw.printIdent("while(");
        expr.genJava(pw);
        pw.println(") {");
        pw.add();
        for (Stat st : this.statList) {
            st.genJava(pw);
        }
        pw.sub();
        pw.printlnIdent("}");
    }
/*
    @Override
    public void genC(PW pw) {
        
    }
*/    
}