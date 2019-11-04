/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class RepeatStat extends Stat {

    private List<Stat> statList;
    private Expr expr;    

    public RepeatStat(List<Stat> statList, Expr expr) {
        this.expr = expr;
        this.statList = statList;        
    }

    public void genC(PW pw) {
        pw.printlnIdent("do{");        
        pw.add();
        for (Stat st : this.statList) {
            st.genC(pw);
        }
        pw.sub();
        pw.printIdent("} while( (");
        expr.genC(pw);
        pw.println(" ) == false);");
    }

    public void genJava(PW pw){
        pw.printlnIdent("do{");        
        pw.add();
        for (Stat st : this.statList) {
            st.genJava(pw);
        }
        pw.sub();
        pw.printIdent("} while( (");
        expr.genJava(pw);
        pw.println(") == false);");
    }
      
}