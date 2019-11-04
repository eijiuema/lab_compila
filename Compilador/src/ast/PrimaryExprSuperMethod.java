/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.*;

public class PrimaryExprSuperMethod extends PrimaryExpr {

    private Id method;
    private TypeCianetoClass superClass;
    List<Expr> exprList;
    private int methodIdx;

    public PrimaryExprSuperMethod(TypeCianetoClass superClass, Id method, int methodIdx) {
        this.method = method;
        this.exprList = new ArrayList<>();
        this.superClass = superClass;
        this.methodIdx = methodIdx;
    }

    public PrimaryExprSuperMethod(TypeCianetoClass superClass, Id method, List<Expr> exprList, int methodIdx) {
        this.method = method;
        this.exprList = exprList;
        this.superClass = superClass;
        this.methodIdx = methodIdx;
    }

    public void genC(PW pw) {
        //_A_put( (_class_A *) self, _p_i ) 
        //Método
        pw.print("(");
        //Casts
        /*
        if(expr.getType() != methodExpr.getType()){
            pw.print("(");
            pw.print(leftExpr.getType().getCname() + "*");
            pw.print(")");
        }*/

        if(methodIdx == -1){
            //Acessando o método privado estaticamente
            pw.print("_" + this.superClass.getName()); //.getCName;
            pw.print(method.getCName());
            pw.print(")");
        } else {
            //Acessando o método no vetor de métodos públicos
            //Cast para superclasse
            pw.print("(");
            pw.print("(" + this.superClass.getCname() + "*)");
            pw.print("self)->vt");    
            pw.print("[");
            pw.print(Integer.toString(methodIdx));
            pw.print("] ");
            pw.print(")");
        }
        
        //Parâmetros
        pw.print("(");
        //Cast
        pw.print("(" + this.superClass.getCname() + "*) ");
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
        for(Expr expr: this.exprList){
            expr.genJava(pw);
            if( !expr.equals(this.exprList.get(this.exprList.size()-1)) ){
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