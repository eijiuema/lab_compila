/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class FieldList {

    private List<AbstractMap.SimpleEntry<String, FieldDec>> fieldList;

    public FieldList() {
        this.fieldList = new ArrayList<AbstractMap.SimpleEntry<String, FieldDec>>();
    }

    public void addField( String qualifier, FieldDec field){
        this.fieldList.add( new AbstractMap.SimpleEntry<String, FieldDec>(qualifier,field));
    }

    public List<AbstractMap.SimpleEntry<String, FieldDec>> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<AbstractMap.SimpleEntry<String, FieldDec>> fieldList) {
        this.fieldList = fieldList;
    }

	public void genC(PW pw) {
        for (AbstractMap.SimpleEntry<String, FieldDec> fd : fieldList ){
            pw.printIdent("");
            fd.getValue().genC(pw);
            pw.println(";");
        }
    }

    public void genJava(PW pw) {
        for (AbstractMap.SimpleEntry<String, FieldDec> fd : fieldList ){
            pw.printIdent("");
            if (fd.getKey().equals(""))
                pw.print("private");
            pw.print(fd.getKey());
            pw.print(" ");
            fd.getValue().genJava(pw);
            pw.println(";");
        }
    }

    public Id getField(String field) {
        for (AbstractMap.SimpleEntry<String, FieldDec> fd : fieldList) {
            Id id = fd.getValue().getField(field);
            if (id != null) {
                return id;
            }
        }
        return null;
    }
    
}
