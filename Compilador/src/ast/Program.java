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

	public void genC(PW pw) {
		
		//Includes
		pw.println("#include <malloc.h>");
		pw.println("#include <stdlib.h>");
		pw.println("#include <stdio.h>");
		pw.println();
		
		/* define o tipo boolean */
		pw.println("typedef int boolean;");
		pw.println("#define true 1");
		pw.println("#define false 0");
		pw.println();

		// crie funções readInt e readString
		pw.println("int readInt() {");
		pw.add();
		pw.printlnIdent("int _n;");
		pw.printlnIdent("char __s[512];");
		pw.printlnIdent("gets(__s);");
		pw.printlnIdent("sscanf(__s, \"%d\", &_n);");
		pw.printlnIdent("return _n;");
		pw.sub();
		pw.println("}");
		pw.println("char *readString() {");
		pw.add();
		pw.printlnIdent("char s[512];");
		pw.printlnIdent("gets(s);");
		pw.printlnIdent("char *ret = malloc(strlen(s) + 1);");
		pw.printlnIdent("strcpy(ret, s);");
		pw.printlnIdent("return ret;");
		pw.sub();
		pw.println("}");
		pw.println();

		//TODO gerar classes()
		for (TypeCianetoClass typeCianetoClass : classList) {
			typeCianetoClass.genC(pw);
			pw.println();
		}
		/* define um tipo Func que é um ponteiro para funç~
		ao */
		pw.println("typedef void (*Func)();");
		pw.println("int main() {");
		pw.add();
		pw.printlnIdent("//_class_Program *program;");
		pw.printlnIdent("//program = new_Program();");
		pw.printlnIdent("//_Program_run(program);");
		pw.printlnIdent("return 0;");
		pw.sub();
		pw.println("}");
		pw.println();
	}

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
			typeCianetoClass.genJava(pw);
		}
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