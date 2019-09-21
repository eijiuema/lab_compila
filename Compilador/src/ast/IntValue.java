package ast;

public class IntValue extends BasicValue{

    private int value;
    
    public IntValue( int value ){
        this.value = value;
    }

    @Override
    public void genJava(PW pw) {
        pw.print(Integer.toString(value));

    }

    public Type getType() {
        return Type.intType;
    }
}