/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class Expr{
    private SimpleExpr leftSimpleExpr;
    private char relation;
    private SimpleExpr rightSimpleExpr;    
    private Type type;

    public Expr(SimpleExpr leftSimpleExpr, char relation, SimpleExpr rightSimpleExpr) {
        this.leftSimpleExpr = leftSimpleExpr;
        this.relation = relation;
        this.rightSimpleExpr = rightSimpleExpr;        
    }

    public Expr(SimpleExpr leftSimpleExpr) {
        this.leftSimpleExpr = leftSimpleExpr;
        this.relation = ' ';
        this.rightSimpleExpr = null;        
    }

    public void genJava(PW pw){

        leftSimpleExpr.genJava(pw);
        
        // if(relation != ' ')
        //     pw.print(relation);
        
        if(rightSimpleExpr != null)
            rightSimpleExpr.genJava(pw);
	}

	public Type getType() {        
        if(relation != ' ')
            return Type.booleanType;
        else
            return this.leftSimpleExpr.getType();
	}

}
