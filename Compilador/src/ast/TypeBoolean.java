/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class TypeBoolean extends Type {

   public TypeBoolean() {
      super("boolean");
   }

   @Override
   public String getCname() {
      return "boolean";
   }

   @Override
   public String getJavaName() {
      return "boolean";
   }

   @Override
   public boolean canConvertFrom(Type right) {
      if (right == Type.booleanType)
         return true;
      else
         return false;
   }

   @Override
   public boolean isBasicType() {
      return true;
   }

}
