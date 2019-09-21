package ast;
import java.util.*;

public class PrimaryExprSelf extends PrimaryExpr{

    private Id selfId;
    
    public PrimaryExprSelf(Id selfId){
        this.selfId = selfId;
    }

    public void genJava(PW pw){
        
    }

    public Type getType() {
        return selfId.getType();
    }
}