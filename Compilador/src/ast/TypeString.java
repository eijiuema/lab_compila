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
        return "String";
    }
    @Override
    public boolean canConvertFrom(Type right) {  
      if( right == Type.stringType || right == Type.nilType)
         return true;
      else
         return false;
   }

}