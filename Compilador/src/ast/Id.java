/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class Id {

    private String name;
    private Type type;

    public Id(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getCName() {
        return "_" + name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
