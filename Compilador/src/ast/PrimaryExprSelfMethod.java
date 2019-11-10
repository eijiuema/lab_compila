/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.*;

public class PrimaryExprSelfMethod extends PrimaryExpr {

    private Id method;
    List<Expr> exprList;
    private int methodIdx;
    private TypeCianetoClass self;

    public PrimaryExprSelfMethod(TypeCianetoClass self, Id method, List<Expr> exprList, int methodIdx) {
        this.method = method;
        this.exprList = exprList;
        this.methodIdx = methodIdx;
        this.self = self;
    }

    public PrimaryExprSelfMethod(TypeCianetoClass self, Id method, int methodIdx) {
        this.method = method;
        this.exprList = new ArrayList<>();
        this.methodIdx = methodIdx;
        this.self = self;
    }

    public void genC(PW pw) {
        //( (int (*)(_class_self *)) self->vt[0] )(self, ...);
        //Método
        pw.print("( ");
        
        //Casts
        pw.print("(");
        pw.print(this.method.getType().getCname());
        if(!this.method.getType().isBasicType())
            pw.print("*");
        pw.print("(*)()");
        pw.print(")");
        
        if(methodIdx == -1){
            //Acessando o método privado estaticamente
            pw.print("_" + self.getName());
            pw.print(method.getCName());
            pw.print(")");
        } else {
            //Acessando o método no vetor de métodos públicos
            pw.print("self->vt");    
            pw.print("[");
            pw.print(Integer.toString(methodIdx));
            pw.print("] ");
            pw.print(")");
        }
        //Parâmetros
        pw.print("(");
        pw.print("self");
        for (Expr expr : this.exprList) {
            pw.print(", ");
            expr.genC(pw);
        }
        pw.print(")");
    }
    
    public void genJava(PW pw) {
        pw.print("this.");
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