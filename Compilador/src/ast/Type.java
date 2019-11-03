/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

abstract public class Type {

    public Type( String name ) {
        this.name = name;
    }

    public static Type booleanType = new TypeBoolean();
    public static Type intType = new TypeInt();
    public static Type stringType = new TypeString();
    public static Type undefinedType = new TypeUndefined();
    public static Type nilType = new TypeNil();

    public String getName() {
        return name;
    }

    abstract public boolean canConvertFrom(Type right);

    abstract public String getCname();

    abstract public String getJavaName();

    abstract public boolean isBasicType();

    private String name;
}
