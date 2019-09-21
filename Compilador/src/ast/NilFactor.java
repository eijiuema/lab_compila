package ast;

public class NilFactor extends Factor{

    private Type type;

    public NilFactor() {
        this.type = Type.nilType;
    }

    public void genJava(PW pw){
        pw.print(type.getJavaName());
    }

}