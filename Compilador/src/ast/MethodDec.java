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
        //gen java Type
        pw.print(this.type.getJavaName() + " ");
        //gen java id
        pw.print(this.id.getName());
        //gen java formalParamDec
        pw.print("(");
        for( ParamDec paramDec : this.formalParamDec ){
            paramDec.genJava(pw);
            if( !paramDec.equals(formalParamDec.get(formalParamDec.size()-1)) ){
                pw.print(", ");
            }
        }
        pw.println("){");
        pw.add();
        //gen java statList
        for( Stat stat : this.statList){
            stat.genJava(pw);
        }
        pw.sub();
        pw.printlnIdent("}");
        
    }

}