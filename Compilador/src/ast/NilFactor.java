package ast;
import java.util.*;

public class NilFactor extends Factor{

    public NilFactor() {
        this.type = Type.nullType;
    }

    public void genJava(PW pw){
        pw.print("null");
    }

}