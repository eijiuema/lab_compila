/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public abstract class BasicValue extends Factor{
    
    public abstract void genJava(PW pw);

    public abstract void genC(PW pw);
    
    public abstract Type getType();
  
}