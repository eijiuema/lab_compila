/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.*;

public class MethodDec extends Member {

    private Id id;
    private Type type;
    private List<ParamDec> formalParamDec;
    private List<Stat> statList;

    public MethodDec(Id id) {
        this.id = id;
        this.type = Type.undefinedType;
        this.formalParamDec = new ArrayList<>();
        this.statList = new ArrayList<>();
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void addStat(Stat statement) {
        this.statList.add(statement);
    }

    public void addParamDec(ParamDec paramDec) {
        this.formalParamDec.add(paramDec);
    }

    public void genJava(PW pw) {
        
    }

}