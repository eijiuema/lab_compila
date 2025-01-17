/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.*;

public class IfStat extends Stat {
    private List<Stat> ifStList, elseStList;
    private Expr expr;

    public IfStat(Expr expr, List<Stat> ifStList, List<Stat> elseStList) {
        this.expr = expr;
        this.ifStList = ifStList;
        this.elseStList = elseStList;
    }

    public void genC(PW pw) {
        
        pw.printIdent("if (");
        expr.genC(pw);
        pw.println(" ) {");
        pw.add();
        for (Stat st : this.ifStList) {
            st.genC(pw);
        }
        if (elseStList != null && elseStList.size() > 0) {
            pw.sub();
            pw.printlnIdent("} else {");
            pw.add();
            for (Stat st : this.elseStList) {
                st.genC(pw);
            }
        }
        pw.sub();
        pw.printlnIdent("}");

    }

    public void genJava(PW pw) {
        pw.printIdent("if (");
        expr.genJava(pw);
        pw.println(") {");
        pw.add();
        for (Stat st : this.ifStList) {
            st.genJava(pw);
        }
        if (elseStList != null && elseStList.size() > 0) {
            pw.sub();
            pw.printlnIdent("} else {");
            pw.add();
            for (Stat st : this.elseStList) {
                st.genJava(pw);
            }
        }
        pw.sub();
        pw.printlnIdent("}");

    }

}