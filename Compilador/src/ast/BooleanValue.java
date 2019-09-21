package ast;
import java.util.*;

public class BooleanValue extends BasicValue{
    
    private boolean value;
    
    public BooleanValue( boolean value ){
        this.value = value;
        this.type = Type.BooleanType;
    }

    @Override
    public void genJava(PW pw) {
        pw.print(Boolean.toString(value));

    }

}