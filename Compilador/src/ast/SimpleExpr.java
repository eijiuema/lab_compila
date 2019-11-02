/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.*;

public class SimpleExpr {

    private List<SumSubExpr> sumSubExprList;

    public SimpleExpr(SumSubExpr sumSubExpr) {
        this.sumSubExprList = new ArrayList<>();
        this.sumSubExprList.add(sumSubExpr);
    }

    public boolean addSumSubExpr(SumSubExpr sumSubExpr) {
        return this.sumSubExprList.add(sumSubExpr);
    }

    public void genC(PW pw) {
//TODO genC
}
public void genJava(PW pw) {
        for (SumSubExpr sse : this.sumSubExprList) {
            sse.genJava(pw);
            if (!sse.equals(this.sumSubExprList.get(this.sumSubExprList.size() - 1))) {
                pw.print(" + ");
            }
        }
    }

    public Type getType() {
        if (sumSubExprList.size() == 1)
            return this.sumSubExprList.get(0).getType();
        else
            return Type.stringType;
    }

    public boolean isAssignable() {
        if (sumSubExprList.size() != 1) {
            return false;
        }
        return sumSubExprList.get(0).isAssignable();
    }

    public boolean hasMethodCallWithReturn() {
        for (SumSubExpr sse : sumSubExprList) {
            if (sse.hasMethodCallWithReturn()) {
                return true;
            }
        }
        return false;
    }
}