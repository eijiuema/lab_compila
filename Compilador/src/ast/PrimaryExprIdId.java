package ast;
import java.util.*;

public class PrimaryExprIdId extends PrimaryExpr{

    private Id firstId, secondId;
    public PrimaryExprIdId(Id firstId, Id secondId){
        this.firstId = firstId;
        this.secondId = secondId;
    }

    public void genJava(PW pw){
        
    }

    public Type getType() {
        return secondId.getType();
    }
}