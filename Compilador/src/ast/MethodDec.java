package ast;
import java.util.*;

public class MethodDec extends Member{
    
    private Id id;
    private Type type;
    private List<ParamDec> formalParamDec;
    private List<Stat> statList;

    public MethodDec(Id id, Type type, List<ParamDec> formalParamDec, List<Stat> statList){
        this.id =id;
        this.type = type;
        this.formalParamDec = formalParamDec;
        this.statList = statList;
    }

    public void genJava(PW pw){
        //...
    }
    
}