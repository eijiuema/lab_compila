/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package comp;

import java.util.Hashtable;

import ast.Id;
import ast.TypeCianetoClass;

public class SymbolTable {

    public Hashtable<Id, TypeCianetoClass> classTable = new Hashtable<>();
    public Hashtable<Id, Object> localTable = new Hashtable<>();

}
