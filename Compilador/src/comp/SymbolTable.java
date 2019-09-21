package comp;

import java.util.Hashtable;

import ast.Id;

public class SymbolTable {

    public Hashtable<String, Object> globalTable;
    public Hashtable<String, Object> localTable;

}
