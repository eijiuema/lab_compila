/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.ArrayList;
import java.util.List;

public class SumSubExpr {
    private Term leftTerm;
    private List<Pair<String, Term>> lowOpTermList;

    public SumSubExpr(Term leftTerm) {
        this.leftTerm = leftTerm;
        this.lowOpTermList = new ArrayList<>();
    }

    public boolean addLowOpTerm(String lowOp, Term term) {
        return this.lowOpTermList.add(new Pair<String, Term>(lowOp, term));
    }

    public void genJava(PW pw) {
        leftTerm.genJava(pw);

        for (Pair<String, Term> lowOpTerm : lowOpTermList) {
            pw.print(" " + lowOpTerm.getFirst() + " ");
            lowOpTerm.getSecond().genJava(pw);
        }

    }

    public Type getType() {
        return this.leftTerm.getType();
    }

    public boolean isAssignable() {
        if (lowOpTermList.size() > 0) {
            return false;
        }
        return leftTerm.isAssignable();
    }
}