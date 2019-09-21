package ast;
import java.util.*;

public class ReadString extends ReadExpr{

    public ReadString(){
        this.type = Type.stringType;
    }

    public void genJava(PW pw){
        
    }
}