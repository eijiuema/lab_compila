package ast;
import java.util.*;

public class PrimaryExprSuperIdColon extends PrimaryExpr{

    private Id superId, id;
    List<Expr> exprList;
    public PrimaryExprSuperIdColon(Id superId, Id id, List<Expr> exprList){
        this.superId = superId;
        this.id = id;
        this.exprList = exprList;
    }

    public void genJava(PW pw){
        
    }

    public Type getType() {
        return secondId.getType();
    }
}