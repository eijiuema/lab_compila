package ast;

public class SumSubExpr{
    private Term leftTerm;
    private char lowOp;
    private Term rightTerm;    

    public SumSubExpr(Term leftTerm, char lowOp, Term rightTerm) {
        this.leftTerm = leftTerm;
        this.lowOp = lowOp;
        this.rightTerm = rightTerm;        
    }

    public SumSubExpr(Term leftTerm) {
        this.leftTerm = leftTerm;
        this.lowOp = ' ';
        this.rightTerm = null;        
    }

    public void genJava(PW pw){

        leftTerm.genJava(pw);
        
        if(rightTerm != null)
            rightTerm.genJava(pw);
    }

    public Type getType() {
        return this.leftTerm.getType();
    }
}