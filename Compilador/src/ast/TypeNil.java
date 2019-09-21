package ast;

public class TypeNil extends Type {

	public TypeNil() {
		super("NilType");
	}

	@Override
	public String getCname() {
		return "NULL";
	}

}
