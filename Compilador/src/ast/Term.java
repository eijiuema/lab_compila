package ast;
import java.util.*;

public class Term{
    private SignalFactor leftSignalFactor;
    private char highOp;
    private SignalFactor rightSignalFactor;    

    public Term(SignalFactor leftSignalFactor, char highop, SignalFactor rightSignalFactor) {
        this.leftSignalFactor = leftSignalFactor;
        this.highOp = highop;
        this.rightSignalFactor = rightSignalFactor;        
    }

    public Term(SignalFactor leftSignalFactor) {
        this.leftSignalFactor = leftSignalFactor;
        this.highop = ' ';
        this.rightSignalFactor = null;        
    }
    
    public void genJava(PW pw){

        leftSignalFactor.genJava();
        
        if(highop != ' ')
            pw.print(highop);
        
        if(rightSignalFactor != null)
            rightSignalFactor.genJava();
    } 
}