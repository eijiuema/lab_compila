package ast;

public class ReadString extends ReadExpr {

    private Type type;

    public ReadString(){
        this.type = Type.stringType;
    }

    public void genJava(PW pw){
        
    }
}