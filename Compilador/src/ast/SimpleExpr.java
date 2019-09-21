package ast;
import java.util.*;

public class SimpleExpr{
    
    private List<SumSubExpr> sumSubExprList;

    public SimpleExpr( String sumSubExprList){
        this.sumSubExprList = sumSubExprList;
    }

    public void genJava(PW pw){
    }

}