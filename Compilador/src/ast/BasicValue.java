package ast;

public abstract class BasicValue extends Factor{
    
    public abstract void genJava(PW pw);
    
    public abstract Type getType();
  
}