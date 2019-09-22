/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class TypeString extends Type {
    
    public TypeString() {
        super("String");
    }
    
   public String getCname() {
      return "char *";
   }

    @Override
    public String getJavaName() {
        // TODO Auto-generated method stub
        return "String";
    }

}