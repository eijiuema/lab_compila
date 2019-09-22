/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
/*
 * Krakatoa Class
 */
public class TypeCianetoClass extends Type {

   public TypeCianetoClass( String name ) {
      super(name);
   }

   @Override
   public String getCname() {
      return getName();
   }

   private String name;
   private TypeCianetoClass superclass;
   private FieldList fieldList;
   private MethodList publicMethodList, privateMethodList;
   
   @Override
   public String getJavaName() {
      return null;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public TypeCianetoClass getSuperclass() {
      return superclass;
   }

   public void setSuperclass(TypeCianetoClass superclass) {
      this.superclass = superclass;
   }

   public FieldList getFieldList() {
      return fieldList;
   }

   public void addField(String qualifier, FieldDec field) {
      this.fieldList.addField(qualifier, field);
   }

   public MethodList getPublicMethodList() {
      return publicMethodList;
   }

   public void addPublicMethodList(String qualifier, MethodDec method) {
      this.publicMethodList.addMethod(qualifier, method);
   }

   public MethodList getPrivateMethodList() {
      return privateMethodList;
   }

   public void addPrivateMethodList(String qualifier, MethodDec method) {
      this.privateMethodList.addMethod(qualifier, method);
   }
}
