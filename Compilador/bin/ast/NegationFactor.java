package ast;
import java.util.*;

public class NegationFactor extends Factor{
    
    Factor factor;

    public NegationValue( Factor factor){
        this.factor = factor;
    }

    public void genJava(PW pw){
        pw.print("! ");
        factor.genJava();
    }    
}