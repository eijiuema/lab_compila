package ast;
import java.util.*;

public class PrimaryExprSelfIdId extends PrimaryExpr{

    private Id selfId, firstId, secondId;
    
    public PrimaryExprSelfIdId(Id selfId, Id firstId, Id secondId){
        this.selfId = selfId;
        this.firstId = firstId;
        this.secondId = secondId;
    }

    public void genJava(PW pw){
        
    }

    public Type getType() {
        return secondId.getType();
    }
}