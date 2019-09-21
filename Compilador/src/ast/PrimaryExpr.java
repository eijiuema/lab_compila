package ast;

public abstract class PrimaryExpr extends Factor{

    public abstract void genJava(PW pw);    
}