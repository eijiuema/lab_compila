/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class TypeUndefined extends Type {
    // variables that are not declared have this type
    
   public TypeUndefined() { super("UndefinedType"); }
   
   public String getCname() {
      return "void";      
   }

   @Override
   public String getJavaName() {
      return "void";
   }

   @Override
   public boolean canConvertFrom(Type right) {  
      return false;
   }

   @Override
   public boolean isBasicType() {
      return true;
   }
   
}
