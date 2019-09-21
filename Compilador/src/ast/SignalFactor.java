package ast;

public class SignalFactor{
    private char signal;
    private Factor factor;

    public SignalFactor(char signal, Factor factor) {
        this.signal = signal;
        this.factor = factor;
    }

    public SignalFactor(Factor factor) {
        this.signal = ' ';
        this.factor = factor;
    }

    public void genJava(PW pw){
        if(signal != ' ')
            pw.print(signal+"");
        
            factor.genJava(pw);
    }
     
    public Type getType() {
        return this.factor.getType();
    }
}