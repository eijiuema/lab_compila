/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class FieldDec extends Member {

    private List<Id> idList;

    public FieldDec(List<Id> idList){
        this.idList = idList;
    }
    
    public Id getField(String field) {
        for (Id id : idList) {
            if (id.getName().equals(field)) {
                return id;
            }
        }
        return null;
    }

    public void genC(PW pw) {
        pw.print(idList.get(0).getType().getCname());
        pw.print(" ");
        if(!idList.get(0).getType().isBasicType())
            pw.print("*");
        pw.print(idList.get(0).getCName());
        idList.stream().skip(1).forEach(id -> {
            pw.print(", ");
            if(!idList.get(0).getType().isBasicType())
                pw.print("*");
            pw.print(id.getCName());
        });
    }

    public void genJava(PW pw){
        pw.print(idList.get(0).getType().getJavaName());
        pw.print(" ");
        pw.print(idList.get(0).getName());
        idList.stream().skip(1).forEach(id -> {
            pw.print(", " + id.getName());
        });
    }
    
}