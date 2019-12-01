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
    public TypeCianetoClass cl;

    public MethodDec(Id id, TypeCianetoClass cl) {
        this.id = id;
        this.formalParamDec = new ArrayList<>();
        this.statList = new ArrayList<>();
        this.cl = cl;
    }

    public Id getId() {
        return this.id;
    }

    public String getName() {
        return this.id.getName();
    }

    public String getCName() {
        return "_" + this.cl.getName() + this.id.getCName();
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

    public String genCparameterTypes() {
        
        String str = "";

        // gen c Types
        str += ("( ");
        // self parameter
        str += (cl.getCname() + " *");
        for (ParamDec paramDec : this.formalParamDec) {
            str += (", ");
            str += (paramDec.getType().getCname());
            str += (" ");
            if(!paramDec.getType().isBasicType()) {
                str += ("*");
            }

        }
        str += (")");
        return str;
    }

    public void genC(PW pw, TypeCianetoClass cl) {
        // gen c Type
        pw.print(this.getType().getCname());
        if(!this.getType().isBasicType())
            pw.print("*");
        pw.print(" ");
        // gen c id
        pw.print("_" + cl.getName());
        pw.print(this.id.getCName());
        // gen c formalParamDec
        pw.print("( ");
        // self parameter
        pw.print( cl.getCname() + " *self");
        for (ParamDec paramDec : this.formalParamDec) {
            pw.print(", ");
            paramDec.genC(pw);

        }
        pw.println(") {");
        pw.add();
        // gen c statList
        for (Stat stat : this.statList) {
            stat.genC(pw);
        }
        pw.sub();
        pw.printlnIdent("}");
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
            if (!(paramDec == formalParamDec.get(formalParamDec.size() - 1))) {
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

    public boolean checkParamListEquals(List<ParamDec> list) {
        if (formalParamDec.size() != list.size()) {
            return false;
        }

        for (int i = 0; i < formalParamDec.size(); i++) {
            Type paramType = formalParamDec.get(i).getType();
            Type exprType = list.get(i).getType();
            if (!paramType.canConvertFrom(exprType)) {
                return false;
            }
        }

        return true;
    }
    

	public boolean checkParamListCompatible(List<Expr> exprList) {
		
        if (formalParamDec.size() != exprList.size()) {
            return false;
        }

        for (int i = 0; i < formalParamDec.size(); i++) {
            if (!formalParamDec.get(i).getType().canConvertFrom(exprList.get(i).getType())) {
                return false;
            }
        }

        return true;
    }

	public List<ParamDec> getParamList() {
		return this.formalParamDec;
	}

	public String genCFunctionPointer() {
        String str = "";
        // gen c id
        str += ("_" + cl.getName());
        str += (this.id.getCName());

        return str;
	}

	public void genCheader(PW pw) {
        // gen c Type
        pw.print(this.getType().getCname());
        if(!this.getType().isBasicType())
            pw.print("*");
        pw.print(" ");
        // gen c id
        pw.print("_" + cl.getName());
        pw.print(this.id.getCName());
        // gen c formalParamDec
        pw.print("( ");
        // self parameter
        pw.print( cl.getCname() + " *self");
        for (ParamDec paramDec : this.formalParamDec) {
            pw.print(", ");
            paramDec.genC(pw);

        }
        pw.println(");");
	}

	public boolean equalsDec(MethodDec methodSignature) {
        
        return this.id.getName().equals(methodSignature.getId().getName())
           && this.formalParamDec.equals(methodSignature.getParamList());
	}

}