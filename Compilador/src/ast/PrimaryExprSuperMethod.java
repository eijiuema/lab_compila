/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.*;

public class PrimaryExprSuperMethod extends PrimaryExpr {

    private MethodDec method;
    private TypeCianetoClass superClass;
    List<Expr> exprList;
    private int methodIdx;

    public PrimaryExprSuperMethod(TypeCianetoClass superClass, MethodDec method, int methodIdx) {
        this.method = method;
        this.exprList = new ArrayList<>();
        this.superClass = superClass;
        this.methodIdx = methodIdx;
    }

    public PrimaryExprSuperMethod(TypeCianetoClass superClass, MethodDec method, List<Expr> exprList, int methodIdx) {
        this.method = method;
        this.exprList = exprList;
        this.superClass = superClass;
        this.methodIdx = methodIdx;
    }

    public void genC(PW pw) {
        
        
        // Acessando o m�todo estaticamente
        pw.print(method.getCName());

        // Par�metros
        pw.print("(");
        // Cast
        pw.print("(" + method.cl.getCname() + " *) ");
        
        pw.print("self");
        for (Expr expr : this.exprList) {
            pw.print(", ");
            expr.genC(pw);
        }
        pw.print(")");
    }

    public void genJava(PW pw) {
        pw.print("super");
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