package ast;
import java.util.*;

public class SumSubExpr{
    private Term leftTerm;
    private char lowOp;
    private Term rightTerm;    

    public Term(Term leftTerm, char lowOp, Term rightTerm) {
        this.leftTerm = leftTerm;
        this.lowOp = lowOp;
        this.rightTerm = rightTerm;        
    }

    public Term(Term leftTerm) {
        this.leftTerm = leftTerm;
        this.lowOp = ' ';
        this.rightTerm = null;        
    }

    public void genJava(PW pw){

        leftTerm.genJava();
        
        if(lowOp != ' ')
            pw.print(signal);
        
        if(rightTerm != null)
            rightTerm.genJava();
    } 
}