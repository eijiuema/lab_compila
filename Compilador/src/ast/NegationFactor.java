/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
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

    public Type getType() {
        return this.factor.getType();
    }
}