package ast;
import java.util.*;

public class IntValue extends BasicValue{
    
    private int value;
    
    public IntValue( int value ){
        this.value = value;
        this.type = Type.intType;
    }

    @Override
    public void genJava(PW pw) {
        pw.print(Integer.toString(value));

    }

}