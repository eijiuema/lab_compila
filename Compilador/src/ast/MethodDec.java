/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.*;

public class MethodDec extends Member {

    private Id id;
    private List<ParamDec> formalParamDec;
    private List<Stat> statList;

    public MethodDec(Id id) {
        this.id = id;
        this.formalParamDec = new ArrayList<>();
        this.statList = new ArrayList<>();
    }

    public Id getId() {
        return this.id;
    }

    public Type getType() {
        return this.id.getType();
    }

    public void setType(Type type) {
        this.id.setType(type);
    }

    public void addStat(Stat statement) {
        this.statList.add(statement);
    }

    public void addParamDec(ParamDec paramDec) {
        this.formalParamDec.add(paramDec);
    }

    public void genJava(PW pw) {
        // gen java Type
        if (this.getType() == Type.undefinedType) {
            pw.print("void ");
        } else {
            pw.print(this.getType().getJavaName() + " ");
        }
        // gen java id
        pw.print(this.id.getName());
        // gen java formalParamDec
        pw.print("(");
        for (ParamDec paramDec : this.formalParamDec) {
            paramDec.genJava(pw);
            if (!paramDec.equals(formalParamDec.get(formalParamDec.size() - 1))) {
                pw.print(", ");
            }
        }
        pw.println(") {");
        pw.add();
        // gen java statList
        for (Stat stat : this.statList) {
            stat.genJava(pw);
        }
        pw.sub();
        pw.printlnIdent("}");

    }

    public boolean checkParamList(List<Expr> exprList) {
        if (formalParamDec.size() != exprList.size()) {
            return false;
        }

        for (int i = 0; i < formalParamDec.size(); i++) {
            Type paramType = formalParamDec.get(i).getType();
            Type exprType = exprList.get(i).getType();
            if (!paramType.canConvertFrom(exprType)) {
                return false;
            }
        }

        return true;
    }

}