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
      this.superclass = null;
   }

   @Override
   public String getCname() {
      return "_class_" + super.getName();
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

         while (!(rightSuperClass.equals(this)) && rightSuperClass.getSuperclass() != null) {
            rightSuperClass = rightSuperClass.getSuperclass();
         }

         if (this.equals(rightSuperClass))
            return true;
         else
            return false;
      }
   }

   public boolean hasField(String field) {
      return this.fieldList.getField(field) != null;
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
      return this.publicMethodList.getMethod(method, exprList) != null || superclass != null && superclass.hasPublicMethod(method, exprList) ;
   }

   public boolean hasPublicMethodEquals(MethodDec methodDec) {
      return this.publicMethodList.getMethodEquals(methodDec) != null || superclass != null && this.superclass.hasPublicMethodEquals(methodDec);
   }

   public boolean hasMethodEquals(MethodDec methodDec) {
      return this.privateMethodList.getMethodEquals(methodDec) != null || this.publicMethodList.getMethodEquals(methodDec) != null;
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

   public TypeCianetoClass getSuperContainsMethod(MethodDec methodDec) {

      if (hasPublicMethodEquals(methodDec)) {
         return this;
      } else if (superclass != null) {
         return superclass.getSuperContainsMethod(methodDec);
      } else {
         return null;
      }

   }

   public String getQualifierFromPublicMethodDecEquals(MethodDec methodDec) {
      String qualif;

      if ((qualif = this.publicMethodList.getQualifierFromMethodDecEquals(methodDec)) != null) {
         return qualif;
      }

      if (this.superclass == null) {
         return null;
      }

      return this.superclass.getQualifierFromPublicMethodDecEquals(methodDec);
   }

   public void genC(PW pw) {
      pw.println("// Codigo da classe " + this.getCname()  );
      
      //Struct com campos
      pw.println("typedef struct _St_" + this.getName() + " {");
      pw.add();
      fieldList.genJava(pw);
      pw.printlnIdent("Func* vt;");
      pw.sub();
      pw.println("}" + this.getCname() + ";");
      pw.println();

      //Assinatura do construtor
      pw.println(this.getCname() + "* new_" + this.getName() + "(void);");
      pw.println();

      publicMethodList.genC(pw, this);
      privateMethodList.genC(pw, this);

      //M�todos p�blicos
      pw.print("Func VT"+ this.getCname() +"[] = ");
      this.publicMethodList.genCFunctionPointersArray(pw, this);
      pw.println(";");
      pw.println();


      //Construtor da classe
      pw.println(this.getCname() + "* new_" + this.getName() + "(){");
      pw.add();
      pw.printlnIdent(this.getCname() + "* t;");
      pw.printlnIdent("if ( (t = malloc(sizeof(" + this.getCname() + "))) != NULL )");
      pw.add();
      pw.printlnIdent("t->vt = VT" + this.getCname() + ";");
      pw.sub();
      pw.printlnIdent("return t;");
      pw.sub();
      pw.println("}");
      pw.println();

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
