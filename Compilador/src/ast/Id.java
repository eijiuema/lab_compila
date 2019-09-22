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

<<<<<<< HEAD
=======
    public void setName(String name) {
        this.name = name;
    }

>>>>>>> a9563696444b450a99ef9314c5db5e5c103a17c9
}
