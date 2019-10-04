/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.ArrayList;
import java.util.List;

/*
 * Krakatoa Class
 */
public class TypeCianetoClass extends Type {

   public TypeCianetoClass(String name, boolean open) {
      super(name);
      this.publicMethodList = new MethodList();
      this.privateMethodList = new MethodList();
      this.fieldList = new FieldList();
      this.open = open;
   }

   @Override
   public String getCname() {
      return super.getName();
   }

   private TypeCianetoClass superclass;
   private FieldList fieldList;
   private MethodList publicMethodList, privateMethodList;
   private boolean open;

   @Override
   public String getJavaName() {
      return super.getName();
   }

   public String getName() {
      return super.getName();
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

   public boolean getOpen() {
      return open;
   }

   public void setOpen(boolean open) {
      this.open = open;
   }

   @Override
   public boolean canConvertFrom(Type right) {
      if (right == Type.nilType)
         return true;
      else if (right == Type.booleanType || right == Type.intType || right == Type.stringType
            || right == Type.undefinedType)
         return false;
      else {
         TypeCianetoClass rightSuperClass = (TypeCianetoClass) right;

         while (!(rightSuperClass.equals(this)) && rightSuperClass != null)
            rightSuperClass = rightSuperClass.getSuperclass();

         if (this.equals(rightSuperClass))
            return true;
         else
            return false;
      }
   }

   public boolean hasField(String field) {
      return this.fieldList.getField(field) != null || this.superclass.getField(field) != null
            || (this.superclass != null && this.superclass.hasField(field));
   }

   public Id getField(String field) {
      Id id = this.fieldList.getField(field);
      if (id != null) {
         return id;
      }
      if (this.superclass == null) {
         return null;
      }
      return this.superclass.fieldList.getField(field);
   }

   public boolean hasPrivateMethod(String method, List<Expr> exprList) {
      return this.privateMethodList.getMethod(method, exprList) != null;
   }

   public boolean hasPrivateMethod(String method) {
      return this.hasPrivateMethod(method, new ArrayList<>());
   }

   public boolean hasPublicMethod(String method) {
      return this.hasPublicMethod(method, new ArrayList<>());
   }

   public boolean hasPublicMethod(String method, List<Expr> exprList) {
      return this.publicMethodList.getMethod(method, exprList) != null;
   }

   public boolean hasMethod(String method, List<Expr> exprList) {
      return hasPrivateMethod(method, exprList) || hasPublicMethod(method, exprList)
            || (this.superclass != null && this.superclass.hasMethod(method, exprList));
   }

   public boolean hasMethod(String method) {
      return hasMethod(method, new ArrayList<>());
   }

   public Id getMethod(String method) {
      return getMethod(method, new ArrayList<>());
   }

   public Id getMethod(String method, List<Expr> exprList) {
      Id id;

      if ((id = this.privateMethodList.getMethod(method, exprList)) != null) {
         return id;
      }

      if ((id = this.publicMethodList.getMethod(method, exprList)) != null) {
         return id;
      }

      if (this.superclass == null) {
         return null;
      }

      return this.superclass.getMethod(method, exprList);
   }

   public void genJava(PW pw) {
      pw.printIdent("class ");
      pw.print(getJavaName());
      if (superclass != null) {
         pw.print(" extends " + superclass.getJavaName());
      }
      pw.println(" {");
      pw.add();
      fieldList.genJava(pw);
      publicMethodList.genJava(pw);
      privateMethodList.genJava(pw);
      pw.sub();
      pw.printlnIdent("}");
      pw.println();
   }

}
