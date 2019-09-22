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
    
    
    
}
