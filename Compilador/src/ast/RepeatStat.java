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
//TODO genC
}
public void genJava(PW pw){
        pw.printlnIdent("do{");        
        pw.add();
        for (Stat st : this.statList) {
            st.genJava(pw);
        }
        pw.sub();
        pw.printlnIdent("} while(");
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