package ast;
import java.util.*;

public class PrimaryExprId extends PrimaryExpr{

    private Id id;
    private List<Expr> exprList;

    public BooleanValue( Id id, List<Expr> exprList){
        this.id = id;
        this.exprList = exprList;
    }
    public BooleanValue( Id id){
        this.value = value;
        this.exprList = null;
    }


    public void genJava(PW pw){
        
    }
}