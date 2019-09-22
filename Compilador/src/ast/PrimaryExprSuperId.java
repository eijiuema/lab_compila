package ast;

public class PrimaryExprSuperId extends PrimaryExpr{

    private Id superId, id;
    
    public PrimaryExprSuperId(Id superId, Id id){
        this.superId = superId;
        this.id = id;
    }

    public void genJava(PW pw){
        
    }

    public Type getType() {
        return id.getType();
    }
}