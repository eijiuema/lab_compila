/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package comp;

import java.util.Hashtable;

import ast.Id;
import ast.TypeCianetoClass;

public class SymbolTable {

    private Hashtable<String, TypeCianetoClass> classTable = new Hashtable<>();
    private Hashtable<String, Id> localTable = new Hashtable<>();

    public TypeCianetoClass getClass(Id id) {
        return classTable.get(id.getName());
    }
    
    public TypeCianetoClass getClass(String name) {
        return classTable.get(name);
    }

    public void putClass(TypeCianetoClass typeCianetoClass) {
        classTable.put(typeCianetoClass.getName(), typeCianetoClass);
    }

    public void clearIds() {
        localTable.clear();
    }
    
    public Id getId(Id id) {
        return localTable.get(id.getName());
    }

    public Id getId(String name) {
        return localTable.get(name);
    }

    public void putId(Id id) {
        localTable.put(id.getName(), id);
    }

    public boolean hasId(String name) {
        return localTable.containsKey(name);
    }

    public boolean hasId(Id id) {
        return localTable.containsKey(id.getName());
    }

    @Override
    public String toString() {
        return classTable.toString() + "\n" + localTable.toString();
    }

}
