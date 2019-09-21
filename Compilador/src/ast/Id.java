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

    public String getName() {
        return name;
    }

}
