package ast;

import java.util.List;

public class PrintStat extends Stat {

    private boolean ln;
    private List<Expr> exprList;

    public PrintStat(boolean ln, List<Expr> exprList) {
        this.ln = ln;
        this.exprList = exprList;
    }

    @Override
    public void genJava(PW pw) {
        for (Expr expr : exprList) {
            pw.printIdent("System.out.print");
            if (ln) {
                pw.print("ln");
            }
            pw.print("(");
            expr.genJava(pw);
            pw.println(");");
        }
    }

}