/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class SignalFactor {
    private String signal;
    private Factor factor;

    public SignalFactor(String signal, Factor factor) {
        this.signal = signal;
        this.factor = factor;
    }

    public SignalFactor(Factor factor) {
        this.signal = null;
        this.factor = factor;
    }

    public void genC(PW pw) {
        if (signal != null)
            pw.print(signal);

        factor.genC(pw);
    
    }
    
    public void genJava(PW pw) {
        if (signal != null)
            pw.print(signal);

        factor.genJava(pw);
    }

    public Type getType() {
        return this.factor.getType();
    }

    public boolean isAssignable() {
        if (signal != null) {
            return false;
        }
        return factor.isAssignable();
    }

    public boolean hasMethodCallWithReturn() {
        return factor.hasMethodCallWithReturn();
    }
}