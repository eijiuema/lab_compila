/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;
import java.util.*;

public class MemberList{
    List<AbstractMap.SimpleEntry<String, Member>> memberList;

    public MemberList(List<AbstractMap.SimpleEntry<String, Member>> memberList){
        this.memberList = memberList;
    }

    public List<AbstractMap.SimpleEntry<String, Member>> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<AbstractMap.SimpleEntry<String, Member>> memberList) {
        this.memberList = memberList;
    }


}