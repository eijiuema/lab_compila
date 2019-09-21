package ast;
import java.util.*;

public class StringValue{
    
    private String value;

    public StringValue( String value){
        this.value = value;
    }

    public void genJava(PW pw){
        pw.print("\"");
        pw.print(value);
        pw.print("\" ");
    }
}