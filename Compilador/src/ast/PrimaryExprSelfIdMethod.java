/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.*;

public class PrimaryExprSelfIdMethod extends PrimaryExpr {

    private Id id;
    private MethodDec method;
    List<Expr> exprList;
    private int methodIdx;
    private TypeCianetoClass self;

    public PrimaryExprSelfIdMethod(TypeCianetoClass self, Id id, MethodDec method, int methodIdx) {
        this.id = id;
        this.method = method;
        this.exprList = new ArrayList<>();
        this.methodIdx = methodIdx;
        this.self = self;
    }

    public PrimaryExprSelfIdMethod(TypeCianetoClass self, Id id, MethodDec method, List<Expr> exprList, int methodIdx) {
        this.id = id;
        this.method = method;
        this.exprList = exprList;
        this.methodIdx = methodIdx;
        this.self = self;
    }

    public void genC(PW pw) {

        if (methodIdx != -1) {

            // ( (int (*)(_class_self *)) self->vt[0] )(self, ...);
            // M�todo
            pw.print("( ");

            // Casts
            pw.print("(");
            pw.print(this.method.getType().getCname());
            if(!this.method.getType().isBasicType())
                pw.print("*");
            pw.print("(*)");
            pw.print(method.genCparameterTypes());
            pw.print(")");

            // Acessando o m�todo no vetor de m�todos p�blicos
            pw.print("self->" + self.getCname() + this.id.getCName() + "->vt");
            pw.print("[");
            pw.print(Integer.toString(methodIdx));
            pw.print("] ");
            pw.print(")");
        } else {
            // Acessando o m�todo privado estaticamente
            pw.print("_" + id.getType().getName()); // .getCName;
            pw.print(method.getCName());
        }

        // Par�metros
        pw.print("(");
        pw.print("self->" + self.getCname() + this.id.getCName());
        for (Expr expr : this.exprList) {
            pw.print(", ");
            expr.genC(pw);
        }
        pw.print(")");
    }

    public void genJava(PW pw) {
        pw.print("this");
        pw.print(".");
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