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

    public void genJava(PW pw) {

        for (AbstractMap.SimpleEntry<String, MethodDec> method : this.methodList) {
            pw.printIdent(method.getKey());
            if (!method.getKey().equals("")) {
                pw.print(" ");
            }
            method.getValue().genJava(pw);
        }
    }

}