/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class SemicolonStat extends Stat{

    public void genJava(PW pw){
        pw.printIdent(";");
    }
    public void genC(PW pw){
        //...
    }
    
}