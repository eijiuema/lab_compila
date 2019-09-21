package ast;
import java.util.*;

public class SimpleExpr{
    
    private List<SumSubExpr> sumSubExprList;

    public SimpleExpr( List<SumSubExpr> sumSubExprList){
        this.sumSubExprList = sumSubExprList;
    }

    public void genJava(PW pw){
    }

}