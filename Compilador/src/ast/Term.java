package ast;

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
        this.highOp = ' ';
        this.rightSignalFactor = null;        
    }
    
    public void genJava(PW pw){

        leftSignalFactor.genJava(pw);
        
        if(highOp != ' ')
            pw.print(highOp+"");
        
        if(rightSignalFactor != null)
            rightSignalFactor.genJava(pw);
    } 
}