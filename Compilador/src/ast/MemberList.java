package ast;
import java.util.*;

public abstract class MemberList{
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