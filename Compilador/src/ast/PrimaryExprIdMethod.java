/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.*;

public class PrimaryExprIdMethod extends PrimaryExpr {

    private Id id, method;
    private List<Expr> exprList;
    private int methodIdx;

    public PrimaryExprIdMethod(Id id, Id method, int methodIdx) {
        this.id = id;
        this.method = method;
        this.exprList = new ArrayList<>();
        this.methodIdx = methodIdx;
    }

    public PrimaryExprIdMethod(Id id, Id method, List<Expr> exprList, int methodIdx) {
        this.id = id;
        this.method = method;
        this.exprList = exprList;
        this.methodIdx = methodIdx;
    }

    public void genC(PW pw) {
        //( (int (*)(_class_A *)) _a->vt[0] )(_a);
        //Método
        pw.print("( ");
        
        //Casts
        pw.print("(");
        pw.print(this.method.getType().getCname());
        if(!this.method.getType().isBasicType())
            pw.print("*");
        pw.print("(*)()");
        pw.print(")");
        
        //Acessando o método no vetor de métodos públicos
        pw.print(this.id.getCName() + "->vt");    
        pw.print("[");
        pw.print(Integer.toString(methodIdx));
        pw.print("] ");
        pw.print(")");


        //Parâmetros
        pw.print("(");
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