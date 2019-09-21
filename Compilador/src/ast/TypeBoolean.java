package ast;

public class TypeBoolean extends Type {

   public TypeBoolean() { super("boolean"); }

   @Override
   public String getCname() {
      return "int";
   }

   @Override
   public String getJavaName() {
      return "int";
   }

}
