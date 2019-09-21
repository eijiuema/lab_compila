package ast;
import java.util.*;

public class PrimaryExprIdIdColon extends PrimaryExpr{

    private Id id, idColon;
    private List<Expr> exprList;

    public PrimaryExprIdIdColon(Id id, Id idColon, List<Expr> exprList){
        this.id = id;
        this.idColon = idColon;
        this.exprList = exprList;
    }

    public void genJava(PW pw){
        
    }

    public Type getType() {
        return idColon.getType();
    }
}