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
        //...
    }
    
}