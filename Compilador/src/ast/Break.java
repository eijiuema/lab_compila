/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class Break extends Stat{
    
    public void genC(PW pw) {
//TODO genC
}
public void genJava(PW pw){
        pw.printlnIdent("break;");
    }
/*
    @Override
    public void genC(PW pw) {
        // TODO Auto-generated method stub

    }
*/    
}