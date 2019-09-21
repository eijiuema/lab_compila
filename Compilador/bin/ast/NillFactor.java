package ast;
import java.util.*;

public class NilFactor extends Factor{

    public static NilFactor() {
        
    }

    public void genJava(PW pw){
        pw.print("null");
    }

}