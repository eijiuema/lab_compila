/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.ArrayList;
import java.util.List;

public class Term {
    private SignalFactor leftSignalFactor;
    private List<Pair<String, SignalFactor>> highOpSignalFactorList;

    public Term(SignalFactor leftSignalFactor, List<Pair<String, SignalFactor>> signalFactorList) {
        this.leftSignalFactor = leftSignalFactor;
        this.highOpSignalFactorList = signalFactorList;
    }

    public Term(SignalFactor leftSignalFactor) {
        this.leftSignalFactor = leftSignalFactor;
        this.highOpSignalFactorList = new ArrayList<>();
    }

    public boolean addHighOpSignalFactor(String highOp, SignalFactor signalFactor) {
        return this.highOpSignalFactorList.add(new Pair<String, SignalFactor>(highOp, signalFactor));
    }

    public void genC(PW pw) {
        leftSignalFactor.genC(pw);

        for (Pair<String, SignalFactor> pair : highOpSignalFactorList) {
            pw.print(" " + pair.getFirst() + " ");
            pair.getSecond().genC(pw);
        }
    }
    
    public void genJava(PW pw) {

        leftSignalFactor.genJava(pw);

        for (Pair<String, SignalFactor> pair : highOpSignalFactorList) {
            pw.print(" " + pair.getFirst() + " ");
            pair.getSecond().genJava(pw);
        }

    }

    public Type getType() {
        return this.leftSignalFactor.getType();
    }

    public boolean isAssignable() {
        if (highOpSignalFactorList.size() > 0) {
            return false;
        }
        return leftSignalFactor.isAssignable();
    }

    public boolean hasMethodCallWithReturn() {
        if (leftSignalFactor.hasMethodCallWithReturn()) {
            return true;
        }

        for (Pair<String, SignalFactor> hosfPair : highOpSignalFactorList) {
            if(hosfPair.getSecond().hasMethodCallWithReturn()) {
                return true;
            }
        }
        return false;
    }
}