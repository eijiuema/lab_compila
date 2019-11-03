/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class TypeInt extends Type {
    
    public TypeInt() {
        super("Int");
    }
    
   public String getCname() {
      return "int";
   }

    @Override
    public String getJavaName() {
        return "int";
    }

    @Override
   public boolean canConvertFrom(Type right) {  
      if( right == Type.intType)
         return true;
      else
         return false;
   }

   @Override
   public boolean isBasicType() {
      return true;
   }
}