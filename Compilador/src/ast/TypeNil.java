/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package ast;

public class TypeNil extends Type {
	
	public TypeNil() {
		super("NilType");
	}

	@Override
	public String getCname() {
		return "NULL";
	}

	@Override
	public String getJavaName() {
		return "null";
	}

	@Override
   public boolean canConvertFrom(Type right) {
		return false;
   }

	@Override
	public boolean isBasicType() {
		return true;
	}

}
