package ast;

public class AssertStat extends Stat {
    
    private StringValue stringvalue;
    private Expr expr;    

    public AssertStat(Expr expr,StringValue stringvalue) {
        this.expr = expr;
        this.stringvalue = stringvalue;        
    }

    public void genJava(PW pw){
        //...
    }
/*
    @Override
    public void genC(PW pw) {
        // TODO Auto-generated method stub

    }
*/    
}