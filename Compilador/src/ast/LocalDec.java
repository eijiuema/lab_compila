/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class LocalDec extends Stat {
    private Type decType;
    private List<Id> idList;
    private Expr expr;    

    public LocalDec( Type decType, List<Id> idList, Expr expr) {
        this.expr = expr;      
        this.decType = decType;
        this.idList = idList;
    }

    public void genJava(PW pw){
        pw.printIdent(this.decType.getJavaName());
        pw.print(" ");

        for(Id id: this.idList){
            pw.print(id.getName());;
            if( !id.equals(this.idList.get(this.idList.size()-1)) ){
                pw.print(", ");
            }
        }

        if(this.expr != null){
            pw.print(" = ");
            this.expr.genJava(pw);
        }

        pw.println(";");
    }
    
}