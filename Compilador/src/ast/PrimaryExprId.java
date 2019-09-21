package ast;
import java.util.*;

public class PrimaryExprId extends PrimaryExpr{

    private Id id;
    private List<Expr> exprList;

    public PrimaryExprId( Id id, List<Expr> exprList){
        this.id = id;
        this.exprList = exprList;
    }
    public PrimaryExprId( Id id){
        this.id = id;
        this.exprList = null;
    }


    public void genJava(PW pw){
        
    }
}