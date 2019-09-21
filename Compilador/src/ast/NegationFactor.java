package ast;

public class NegationFactor extends Factor{
    
    Factor factor;

    public NegationFactor( Factor factor){
        this.factor = factor;
    }

    public void genJava(PW pw){
        pw.print("! ");
        factor.genJava(pw);
    }    
}