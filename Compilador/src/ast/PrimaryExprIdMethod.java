/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.*;

public class PrimaryExprIdMethod extends PrimaryExpr {

    private Id id;
    private MethodDec method;
    private List<Expr> exprList;
    private int methodIdx;

    public PrimaryExprIdMethod(Id id, MethodDec method, int methodIdx) {
        this.id = id;
        this.method = method;
        this.exprList = new ArrayList<>();
        this.methodIdx = methodIdx;
    }

    public PrimaryExprIdMethod(Id id, MethodDec method, List<Expr> exprList, int methodIdx) {
        this.id = id;
        this.method = method;
        this.exprList = exprList;
        this.methodIdx = methodIdx;
    }

    public void genC(PW pw) {
        //( (int (*)(_class_A *)) _a->vt[0] )(_a);
        //M�todo
        pw.print("(");
        
        //Casts
        pw.print("(");
        pw.print(this.method.getType().getCname());
        if(!this.method.getType().isBasicType())
            pw.print("*");
        pw.print("(*)");
        pw.print(method.genCparameterTypes());
        pw.print(")");
        
        //Acessando o m�todo no vetor de m�todos p�blicos
        pw.print(this.id.getCName() + "->vt");    
        pw.print("[");
        pw.print(Integer.toString(methodIdx));
        pw.print("] ");
        pw.print(")");


        //Par�metros
        pw.print("(");
        if (id.getType() != method.cl) {
            pw.print("(" + method.cl.getCname() + "*)");
        }
        pw.print(this.id.getCName());
        for (Expr expr : this.exprList) {
            pw.print(", ");
            expr.genC(pw);
        }
        pw.print(")");
    }

    public void genJava(PW pw) {

        pw.print(this.id.getName());
        pw.print(".");
        pw.print(this.method.getName());
        pw.print("(");
        for (Expr expr : this.exprList) {
            expr.genJava(pw);
            if (!expr.equals(this.exprList.get(this.exprList.size() - 1))) {
                pw.print(", ");
            }

        }
        pw.print(")");
    }

    public Type getType() {
        return method.getType();
    }

    @Override
    public boolean hasMethodCallWithReturn() {
        return method.getType() != Type.undefinedType;
    }
}