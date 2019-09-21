package ast;
import java.util.*;

public class BooleanValue extends BasicValue{
    
    private boolean value;
    private Type type;
    
    public BooleanValue( boolean value ){
        this.value = value;
        this.type = Type.booleanType;
    }

    @Override
    public void genJava(PW pw) {
        pw.print(Boolean.toString(value));

    }

}