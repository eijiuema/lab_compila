/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class MethodList {

    private List<AbstractMap.SimpleEntry<String, MethodDec>> methodList;

    public MethodList() {
        this.methodList = new ArrayList<AbstractMap.SimpleEntry<String, MethodDec>>();
    }

    public void addMethod(String qualifier, MethodDec method) {
        this.methodList.add(new AbstractMap.SimpleEntry<String, MethodDec>(qualifier, method));
    }

    public List<AbstractMap.SimpleEntry<String, MethodDec>> getMethodList() {
        return methodList;
    }

    public void setMethodList(List<AbstractMap.SimpleEntry<String, MethodDec>> methodList) {
        this.methodList = methodList;
    }

    public void genC(PW pw) {
//TODO genC
}
public void genJava(PW pw) {

        for (AbstractMap.SimpleEntry<String, MethodDec> method : this.methodList) {
            if (method.getKey().contains("override")) {
                pw.printlnIdent("@Override");
            }
            pw.printIdent(method.getKey().replace("override", ""));
            if (!method.getKey().equals("")) {
                pw.print(" ");
            }
            method.getValue().genJava(pw);
        }
    }

    public Id getMethod(String method, List<Expr> exprList) {
        for (AbstractMap.SimpleEntry<String, MethodDec> smd : methodList) {
            MethodDec md = smd.getValue();
            Id id = md.getId();
            if (id.getName().equals(method) && md.checkParamListCompatible(exprList)) {
                return id;
            }
        }
        return null;
    }

    public Id getMethodEquals(MethodDec methodDec) {
        for (AbstractMap.SimpleEntry<String, MethodDec> smd : methodList) {
            MethodDec md = smd.getValue();
            Id id = md.getId();
            if (id.getName().equals(methodDec.getId().getName()) && md.checkParamListEquals(methodDec.getParamList())
                    && md.getType() == methodDec.getType()) {
                return id;
            }
        }
        return null;
    }

    public String getQualifierFromMethodDecEquals(MethodDec methodDec) {
        for (AbstractMap.SimpleEntry<String, MethodDec> smd : methodList) {
            MethodDec md = smd.getValue();
            String qualif = smd.getKey();

            if (md.getId().getName().equals(methodDec.getId().getName())
                    && md.checkParamListEquals(methodDec.getParamList())) {
                return qualif;
            }
        }
        return null;
    }

}