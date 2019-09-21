package ast;

public abstract class ReadExpr extends PrimaryExpr{

    public abstract void genJava(PW pw);    

    public abstract Type getType();

}