/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class ClassDec {
    
    private Id id, extendsId;
    private boolean open;
    private MemberList memberList;

    public ClassDec(Id id, Id extendsId, MemberList memberList, boolean open){
        this.id = id;
        this.memberList = memberList;
        this.extendsId = extendsId;
        this.open = open;
    }

    public void genJava(PW pw){
        //...
    }

    public TypeCianetoClass getTypeCianetoClass( ArrayList<TypeCianetoClass> classList){
        TypeCianetoClass cClass = new TypeCianetoClass(id.getName());

        if(this.extendsId != null){
        //Procurando a classe m�e na lista de classes declaradas
            for (TypeCianetoClass declaredClass : classList) {
                if(declaredClass.getName() == extendsId.getName()){
                    cClass.setSuperclass(declaredClass);
                    break;
                }                
            }
        }
        List<AbstractMap.SimpleEntry<String, Member>> members = memberList.getMemberList();

        for (AbstractMap.SimpleEntry<String, Member> m : members) {
            //Se for um FieldDec
            if( m.getValue().getClass() == FieldDec.class ){
                cClass.addField(m.getKey(), (FieldDec) m.getValue());
            }
            else{//Sen�o, � um MethodDec
                if(m.getKey().contains("private")){
                    cClass.addPrivateMethodList(m.getKey(), (MethodDec) m.getValue());
                }
                else{
                    cClass.addPublicMethodList(m.getKey(), (MethodDec) m.getValue());
                }
            }
        }

        return cClass; 
    }
    
}