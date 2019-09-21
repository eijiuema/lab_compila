package ast;
import java.util.*;

public class ClassDec{
    
    private Id id, extendsId;
    private boolean open;
    private MemberList memberList;

    public ClassDec(Id id, Id extendsId, MemberList memberList, boolean open){
        this.id =id;
        this.memberList = memberList;
        this.extendsId = extendsId;
        this.open = open;
    }

    public void genJava(PW pw){
        //...
    }
    
}