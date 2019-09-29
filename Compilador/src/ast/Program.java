/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.*;
import comp.CompilationError;

public class Program {

	private String mainClassName;

	public Program(ArrayList<TypeCianetoClass> classList, ArrayList<MetaobjectAnnotation> metaobjectCallList, 
			       ArrayList<CompilationError> compilationErrorList) {
		this.classList = classList;
		this.metaobjectCallList = metaobjectCallList;
		this.compilationErrorList = compilationErrorList;
	}

	public void setFilename(String filename) {
		this.mainClassName = filename;
	}

	public void genJava(PW pw) {

		pw.println("class " + mainClassName + "{");
		pw.add();
		pw.printlnIdent("public static void main(String args[]){");
		pw.printlnIdent("}");
		pw.sub();
		pw.println("}");

		for (TypeCianetoClass typeCianetoClass : classList) {
			pw.print("class ");
			pw.print(typeCianetoClass.getName());
			pw.println("{");
			pw.add();
			typeCianetoClass.getFieldList().genJava(pw);
			typeCianetoClass.getPublicMethodList().genJava(pw);
			typeCianetoClass.getPrivateMethodList().genJava(pw);
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
		return compilationErrorList != null && compilationErrorList.size() > 0 ;
	}

	public ArrayList<CompilationError> getCompilationErrorList() {
		return compilationErrorList;
	}

	
	private ArrayList<TypeCianetoClass> classList;
	private ArrayList<MetaobjectAnnotation> metaobjectCallList;
	
	ArrayList<CompilationError> compilationErrorList;

	public void setMainJavaClassName(String className) {
	}

	
}