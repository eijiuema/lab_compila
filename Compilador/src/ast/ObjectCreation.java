package ast;
import java.util.*;

public class ObjectCreation extends Factor{

    private Id id;
    
    public ObjectCreation(Id id){
        this.id = id;
    }

    public void genJava(PW pw){
        pw.print("new ");
        //...
    }    
}