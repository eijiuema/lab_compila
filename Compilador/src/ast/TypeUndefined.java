/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class TypeUndefined extends Type {
    // variables that are not declared have this type
    
   public TypeUndefined() { super("undefined"); }
   
   public String getCname() {
      return "long";      
   }

   @Override
   public String getJavaName() {
      return "long";
   }

   @Override
   public boolean canConvertFrom(Type right) {  
      return false;
   }
   
}
