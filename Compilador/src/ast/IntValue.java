package ast;

public class IntValue extends BasicValue{
    
    private int value;
    private Type type;
    
    public IntValue( int value ){
        this.value = value;
        this.type = Type.intType;
    }

    @Override
    public void genJava(PW pw) {
        pw.print(Integer.toString(value));

    }

}