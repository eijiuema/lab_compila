package ast;
import java.util.*;

public abstract class MemberList{
    List<AbstractMap.SimpleEntry<String, Member>> memberList;

    public MemberList(List<AbstractMap.SimpleEntry<String, Member>> memberList){
        this.memberList = memberList;
    }

}