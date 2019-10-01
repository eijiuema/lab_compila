/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.*;
import comp.CompilationError;

public class Program {

	public Program(ArrayList<TypeCianetoClass> classList, ArrayList<MetaobjectAnnotation> metaobjectCallList,
			ArrayList<CompilationError> compilationErrorList) {
		this.classList = classList;
		this.metaobjectCallList = metaobjectCallList;
		this.compilationErrorList = compilationErrorList;
	}

	/**
	 * the name of the main Java class when the code is generated to Java. This name
	 * is equal to the file name (without extension)
	 */
	private String mainJavaClassName;

	public void genJava(PW pw) {

		pw.println("import java.util.Scanner;");
		pw.println("public class " + mainJavaClassName + " {");
		pw.add();
		pw.printlnIdent("public static void main(String args[]) {");
		pw.add();
		pw.printlnIdent("new Program().run();");
		pw.sub();
		pw.printlnIdent("}");
		pw.sub();
		pw.println("}");
		pw.println();
		for (TypeCianetoClass typeCianetoClass : classList) {
			pw.printIdent("class ");
			pw.print(typeCianetoClass.getName());
			pw.println(" {");
			pw.add();
			typeCianetoClass.getFieldList().genJava(pw);
			typeCianetoClass.getPublicMethodList().genJava(pw);
			typeCianetoClass.getPrivateMethodList().genJava(pw);
			pw.sub();
			pw.printlnIdent("}");
			pw.println();
		}
	}

	public void genC(PW pw) {
	}

	public ArrayList<TypeCianetoClass> getClassList() {
		return classList;
	}

	public void addClass(TypeCianetoClass c) {
		classList.add(c);
	}

	public ArrayList<MetaobjectAnnotation> getMetaobjectCallList() {
		return metaobjectCallList;
	}

	public boolean hasCompilationErrors() {
		return compilationErrorList != null && compilationErrorList.size() > 0;
	}

	public ArrayList<CompilationError> getCompilationErrorList() {
		return compilationErrorList;
	}

	private ArrayList<TypeCianetoClass> classList;
	private ArrayList<MetaobjectAnnotation> metaobjectCallList;

	ArrayList<CompilationError> compilationErrorList;

	public void setMainJavaClassName(String className) {
		this.mainJavaClassName = className;
	}

}