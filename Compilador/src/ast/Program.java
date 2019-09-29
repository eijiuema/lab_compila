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

	public void setMainJavaClassName(String mainJavaClassName) {
		this.mainJavaClassName = mainJavaClassName;
	}

	/**
	 * the name of the main Java class when the code is generated to Java. This name
	 * is equal to the file name (without extension)
	 */
	private String mainJavaClassName;

	public void genJava(PW pw) {

		pw.print("class " + mainJavaClassName + "{ public static void main(String args[]){}}");

		for (TypeCianetoClass typeCianetoClass : classList) {
			pw.print("class ");
			pw.print(typeCianetoClass.getName());
			pw.println("{");
			pw.add();
			typeCianetoClass.getFieldList().genJava(pw);
			// typeCianetoClass.getPublicMethodList().genJava(pw);
			// typeCianetoClass.getPrivateMethodList().genJava(pw);
			pw.sub();
			pw.println("}");
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

}