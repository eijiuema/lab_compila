/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class Break extends Stat{
    
    public void genC(PW pw) {
        pw.printlnIdent("break;");
    }
    public void genJava(PW pw){
        pw.printlnIdent("break;");
    }

    
}