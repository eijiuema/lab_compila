/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class FieldDec extends Member{
    
    private Type type;
    private List<Id> idList;

    public FieldDec(Type type, List<Id> idList){
        this.type = type;
        this.idList = idList;
    }

    public void genJava(PW pw){
        pw.print(type.getJavaName());
        pw.print(" ");

        for(Id id : idList){
            pw.print(id.getName());
            if(id != idList.get(idList.size()-1))
                pw.print(", ");
        }
    }
    
}