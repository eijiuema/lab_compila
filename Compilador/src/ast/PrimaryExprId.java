package ast;
import java.util.*;

public class PrimaryExprId extends PrimaryExpr{

    private Id id;
    public PrimaryExprId(Id id){
        this.id = id;
    }

    public void genJava(PW pw){
        
    }

    public Type getType() {
        return id.getType();
    }
}