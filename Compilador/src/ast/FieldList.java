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

	public void genJava(PW pw) {
        for (AbstractMap.SimpleEntry<String, FieldDec> fd : fieldList ){
            pw.printIdent("private");
            pw.print(fd.getKey());
            pw.print(" ");
            fd.getValue().genJava(pw);
            pw.println(";");
        }
    }
    
    public boolean hasField(String field) {
        for (AbstractMap.SimpleEntry<String, FieldDec> fd : fieldList) {
            if (fd.getValue().hasField(field)) {
                return true;
            }
        }
        return false;
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
