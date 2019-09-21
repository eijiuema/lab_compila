package ast;
import java.util.*;

public class ParamDec{
    private Id id;
    private Type type;    

    public ParamDec(Type type, Id id) {
        this.type = type;
        this.id = id;
    }

    public void genJava(PW pw){
        //...
    }
    
}