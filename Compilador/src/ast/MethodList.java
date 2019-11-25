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

    public void genC(PW pw, TypeCianetoClass cl) {

        for (AbstractMap.SimpleEntry<String, MethodDec> method : this.methodList) {
            method.getValue().genC(pw, cl);
            pw.println();
        }
    }

    public ArrayList<String> genCFunctionPointersArray(TypeCianetoClass cl, ArrayList<String> superClassMethods ){
        
        ArrayList<String> fp = superClassMethods;

        for (AbstractMap.SimpleEntry<String, MethodDec> method : this.methodList) {
            String methodStr = new String();

            methodStr += ("(");
            methodStr += (method.getValue().getType().getCname());
            methodStr += ("(*)");

            methodStr += (method.getValue().genCparameterTypes(cl));

            methodStr += (") ");

            methodStr += (method.getValue().genCFunctionPointer(cl));
            
            //Verifica sobrecarga
            if(method.getKey().contains("override")){
                fp.set( cl.getMethodIdx(method.getValue()), methodStr);
            } else
                fp.add(methodStr);
        }
        return fp;
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

	public int getMethodIdx(String method, List<Expr> exprList, TypeCianetoClass superclass) {
        int idx = 0;
        for (AbstractMap.SimpleEntry<String, MethodDec> smd : this.methodList) {
            MethodDec md = smd.getValue();
            Id id = md.getId();
            if (id.getName().equals(method) && md.checkParamListCompatible(exprList) && !smd.getKey().contains("override")) {
                
                if(superclass != null){
                    idx += superclass.publicInheritedMethodsSize();
                    //System.out.println("superclass:" + superclass.getCname() + " idx:" + idx);
                }
                return idx;
            }
            idx++;
        }

        //Se não achou, procure na superclasse
        if(superclass != null)
            return superclass.getPublicMethodIdx(method, exprList);
        
        //Não existe mais onde procurar
        return -1;
    }
    
    public int getMethodIdx(MethodDec methodSignature, TypeCianetoClass superclass){
		int idx = 0;
        for (AbstractMap.SimpleEntry<String, MethodDec> smd : this.methodList) {
            MethodDec md = smd.getValue();
            if (md.equalsDec(methodSignature) && !smd.getKey().contains("override")) {
                if(superclass != null){
                    idx += superclass.publicInheritedMethodsSize();
                }
                return idx;
            }
            idx++;
        }

        //Se não achou, procure na superclasse
        if(superclass != null)
            return superclass.getMethodIdx(methodSignature);
        
        //Não existe mais onde procurar
        return -1;
	}

	public int size() {
        int count = this.methodList.size();
        for (AbstractMap.SimpleEntry<String, MethodDec> method : this.methodList) {
            if(method.getKey().contains("override"))
                count --;
        }
		return count;
	}

	public void genCheaders(PW pw, TypeCianetoClass cl) {
        for (AbstractMap.SimpleEntry<String, MethodDec> method : this.methodList) {
            method.getValue().genCheader(pw, cl);
            pw.println();
        }
	}

}