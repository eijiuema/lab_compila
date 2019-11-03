/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package comp;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import ast.*;
import lexer.Lexer;
import lexer.Token;

public class Compiler {

	public Compiler() {
	}

	// compile must receive an input with an character less than
	// p_input.lenght
	public Program compile(char[] input, PrintWriter outError) {

		ArrayList<CompilationError> compilationErrorList = new ArrayList<>();
		signalError = new ErrorSignaller(outError, compilationErrorList);
		symbolTable = new SymbolTable();
		lexer = new Lexer(input, signalError);
		signalError.setLexer(lexer);

		Program program = null;
		lexer.nextToken();
		program = program(compilationErrorList);
		return program;
	}

	private Program program(ArrayList<CompilationError> compilationErrorList) {
		ArrayList<MetaobjectAnnotation> metaobjectCallList = new ArrayList<>();
		ArrayList<TypeCianetoClass> CianetoClassList = new ArrayList<>();
		Program program = new Program(CianetoClassList, metaobjectCallList, compilationErrorList);
		boolean thereWasAnError = false;

		while (lexer.token == Token.CLASS || (lexer.token == Token.ID && lexer.getStringValue().equals("open"))
				|| lexer.token == Token.ANNOT) {
			try {
				while (lexer.token == Token.ANNOT) {
					metaobjectAnnotation(metaobjectCallList);
				}

		
				TypeCianetoClass typeCianetoClass = classDec();
				
				program.addClass(typeCianetoClass);
				
			} catch (CompilerError e) {
				// if there was an exception, there is a compilation error
				//e.printStackTrace();
				thereWasAnError = true;

			} catch (RuntimeException e) {
				//e.printStackTrace();
				thereWasAnError = true;
			}

		}
		if (symbolTable.getClass("Program") == null) {
			try {
				error("Source code is missing the Program class",true);
			}
			catch (CompilerError e) {
				thereWasAnError = true;
			}
		}
		if (!thereWasAnError && lexer.token != Token.EOF) {
			try {
				error("End of file expected");
			} catch (CompilerError e) {
			}
		}

		return program;
	}

	/**
	 * parses a metaobject annotation as <code>{@literal @}cep(...)</code> in <br>
	 * <code>
	 * {@literal @}cep(5, "'class' expected") <br>
	 * class Program <br>
	 *     func run { } <br>
	 * end <br>
	 * </code>
	 *
	 * 
	 */
	@SuppressWarnings("incomplete-switch")
	private void metaobjectAnnotation(ArrayList<MetaobjectAnnotation> metaobjectAnnotationList) {
		String name = lexer.getMetaobjectName();
		int lineNumber = lexer.getLineNumber();
		lexer.nextToken();
		ArrayList<Object> metaobjectParamList = new ArrayList<>();
		boolean getNextToken = false;
		if (lexer.token == Token.LEFTPAR) {
			// metaobject call with parameters
			lexer.nextToken();
			while (lexer.token == Token.LITERALINT || lexer.token == Token.LITERALSTRING || lexer.token == Token.ID) {
				switch (lexer.token) {
				case LITERALINT:
					metaobjectParamList.add(lexer.getNumberValue());
					break;
				case LITERALSTRING:
					metaobjectParamList.add(lexer.getLiteralStringValue());
					break;
				case ID:
					metaobjectParamList.add(lexer.getStringValue());
				}
				lexer.nextToken();
				if (lexer.token == Token.COMMA)
					lexer.nextToken();
				else
					break;
			}
			if (lexer.token != Token.RIGHTPAR)
				error("')' expected after annotation with parameters");
			else {
				getNextToken = true;
			}
		}
		switch (name) {
		case "nce":
			if (metaobjectParamList.size() != 0)
				error("Annotation 'nce' does not take parameters");
			break;
		case "cep":
			if (metaobjectParamList.size() != 3 && metaobjectParamList.size() != 4)
				error("Annotation 'cep' takes three or four parameters");
			if (!(metaobjectParamList.get(0) instanceof Integer)) {
				error("The first parameter of annotation 'cep' should be an integer number");
			} else {
				int ln = (Integer) metaobjectParamList.get(0);
				metaobjectParamList.set(0, ln + lineNumber);
			}
			if (!(metaobjectParamList.get(1) instanceof String) || !(metaobjectParamList.get(2) instanceof String))
				error("The second and third parameters of annotation 'cep' should be literal strings");
			if (metaobjectParamList.size() >= 4 && !(metaobjectParamList.get(3) instanceof String))
				error("The fourth parameter of annotation 'cep' should be a literal string");
			break;
		case "annot":
			if (metaobjectParamList.size() < 2) {
				error("Annotation 'annot' takes at least two parameters");
			}
			for (Object p : metaobjectParamList) {
				if (!(p instanceof String)) {
					error("Annotation 'annot' takes only String parameters");
				}
			}
			if (!((String) metaobjectParamList.get(0)).equalsIgnoreCase("check")) {
				error("Annotation 'annot' should have \"check\" as its first parameter");
			}
			break;
		default:
			error("Annotation '" + name + "' is illegal");
		}
		metaobjectAnnotationList.add(new MetaobjectAnnotation(name, metaobjectParamList));
		if (getNextToken)
			lexer.nextToken();
	}

	private TypeCianetoClass classDec() {

		TypeCianetoClass typeCianetoClass = null;
		boolean open = false;

		if (lexer.token == Token.ID && lexer.getStringValue().equals("open")) {
			open = true;
			lexer.nextToken();
		}

		check(Token.CLASS, "'class' expected");
		lexer.nextToken();

		check(Token.ID, "'Identifier' expected");

		Id id = id();

		if (symbolTable.getClass(id) != null) {
			error("There's already a class named " + id.getName());
		}

		typeCianetoClass = new TypeCianetoClass(id.getName(), open);

		self = typeCianetoClass;

		symbolTable.putClass(typeCianetoClass);

		if (lexer.token == Token.EXTENDS) {
			lexer.nextToken();
			check(Token.ID, "'identifier' expected");

			Id idSuper = id(false);
			if(idSuper.getName().equals(self.getName()))
				error("The class " + idSuper.getName() + "  cannot inherith from itself");
			else
				next();
			
				TypeCianetoClass superTypeCianetoClass = symbolTable.getClass(idSuper);
			if (null == superTypeCianetoClass) {
				error("There's no class named " + idSuper.getName());
			} else if (!superTypeCianetoClass.getOpen()) {
				error("The class " + superTypeCianetoClass.getName() + " isn't open and thus cannot be extended");
			} else {
				self.setSuperclass(superTypeCianetoClass);
			}
		}

		memberList();

		if (typeCianetoClass.getName().equals("Program") && typeCianetoClass.getPublicMethodIdx("run") == -1) {
			error("The Program class must have a public run method");
		}

		check(Token.END, "'end' was expected");
		lexer.nextToken();

		return typeCianetoClass;
	}

	private void memberList() {

		while (true) {
			String qualifier = qualifier();
			if (lexer.token == Token.VAR) {
				if (!(qualifier.equals("private") || qualifier.equals(""))) {
					error("Invalid qualifier");
				}
				self.addField(qualifier, fieldDec());
			} else if (lexer.token == Token.FUNC) {
				methodDec(qualifier);
			} else {
				break;
			}
		}

	}

	private void error(String msg) {
		this.signalError.showError(msg);
	}

	private void next() {
		lexer.nextToken();
	}

	private void check(Token shouldBe, String msg) {
		if (lexer.token != shouldBe) {
			error(msg);
		}
	}

	private void methodDec(String qualifier) {
		Id id = null;
		MethodDec methodDec = null;
		this.currentMethod = null;
		this.methodReturned = false;

		symbolTable.clearIds();

		lexer.nextToken();
		if (lexer.token == Token.ID) {
			id = id();
			methodDec = new MethodDec(id);
			if (self.hasField(id.getName())) {
				error("There's already a field named " + id.getName());
			}
		} else if (lexer.token == Token.IDCOLON) {
			id = idColon();
			if (self.getName().equals("Program") && id.getName().equals("run")) {
				error("The method Program.run must be parameterless");
			}
			methodDec = new MethodDec(id);
			formalParamDec(methodDec);
		} else {
			error("An identifier or identifer: was expected after 'func'");
		}

		if (lexer.token == Token.MINUS_GT) {
			if (self.getName().equals("Program") && id.getName().equals("run")) {
				error("The method Program.run must return nothing");
			}
			lexer.nextToken();
			methodDec.setType(type());
		}

		if (qualifier.contains("override")) {
			if (this.self.getSuperclass() == null || !this.self.getSuperclass().hasPublicMethodEquals(methodDec)) {
				error("The method " + methodDec.getId().getName() + " of type " + this.self.getName()
						+ " must override or implement a supertype method");
			} else {

				// Verificando os qualificadores do metodo sobrecarregado
				TypeCianetoClass superContainsMethod = self.getSuperContainsMethod(methodDec);
				String superclassMethodQualifier = superContainsMethod.getQualifierFromPublicMethodDecEquals(methodDec);
				if (superclassMethodQualifier.contains("final")) {
					error("Cannot override the final method from " + superContainsMethod.getName());
				}
				if (superclassMethodQualifier.contains("shared")) {
					error("Cannot override the shared method from " + superContainsMethod.getName());
				}

				// Verificando os qualificadores do metodo que esta sobrecarregando
				if (qualifier.contains("private")) {
					error("Cannot reduce the visibility of the inherited method from " + superContainsMethod.getName());
				}
				if (qualifier.contains("shared")) {
					error("This shared method cannot hide the instance method from " + superContainsMethod.getName());
				}
			}
		} else {
			if (self.hasMethodEquals(methodDec)) {
				error("There's already a method named " + id.getName() + " with the specified params");
			}
		}

		if (qualifier.equals("private")) {
			if (self.getName().equals("Program") && id.getName().equals("run")) {
				error("The method run of Program should be public");
			}
			self.addPrivateMethodList(qualifier, methodDec);
		} else {
			if (this.self.getSuperclass() != null && this.self.getSuperclass().hasPublicMethodEquals(methodDec)
					&& !qualifier.contains("override")) {
				error("The method " + methodDec.getId().getName() + " of type " + this.self.getName()
						+ " must have the \"override\" qualifier");
			}

			self.addPublicMethodList(qualifier, methodDec);
		}

		check(Token.LEFTCURBRACKET, "'{' expected");
		next();

		this.currentMethod = methodDec;

		statementList(methodDec);

		if (methodDec.getType() != Type.undefinedType && !methodReturned)
			error("this method must return a result of type " + methodDec.getType().getName());

		check(Token.RIGHTCURBRACKET, "'}' expected");
		next();
	}

	private void formalParamDec(MethodDec methodDec) {

		methodDec.addParamDec(paramDec());

		while (lexer.token == Token.COMMA) {
			next();
			methodDec.addParamDec(paramDec());
		}

		if ((lexer.token != Token.MINUS_GT) && (lexer.token != Token.LEFTCURBRACKET))
			error("',' expected");

	}

	private ParamDec paramDec() {
		Type t = type();
		Id id = id(t);

		if (symbolTable.hasId(id)) {
			error("There's already a param named " + id.getName());
		}

		symbolTable.putId(id);

		return new ParamDec(t, id);
	}

	private void statementList(MethodDec methodDec) {
		while (lexer.token != Token.RIGHTCURBRACKET && lexer.token != Token.END) {
			methodDec.addStat(statement());
		}
	}

	private Stat statement() {
		boolean checkSemiColon = true;
		Stat st = null;

		switch (lexer.token) {
		case IF:
			st = ifStat();
			checkSemiColon = false;
			break;
		case WHILE:
			nestedLoops++;
			st = whileStat();
			nestedLoops--;
			checkSemiColon = false;
			break;
		case RETURN:
			st = returnStat();
			break;
		case BREAK:
			st = breakStat();
			break;
		case SEMICOLON:
			st = new SemicolonStat();
			break;
		case REPEAT:
			nestedLoops++;
			st = repeatStat();
			nestedLoops--;
			break;
		case VAR:
			st = localDec();
			break;
		case ASSERT:
			st = assertStat();
			break;
		case ID:
			if (lexer.getStringValue().equals("Out")) {
				st = printStat();
			} else {
				st = assignExpr();
			}
			break;
		default:
			st = assignExpr();
		}

		if (checkSemiColon) {
			check(Token.SEMICOLON, "';' expected",true);
			next();
		}

		return st;
	}

	private void check(Token shouldBe, String msg, boolean b) {
		if (lexer.token != shouldBe) {
			error(msg,b);
		}
	}

	private void error(String msg, boolean b) {
		this.signalError.showError(msg,b);
	}

	private Stat printStat() {
		next();
		check(Token.DOT, "'.' was expected");
		next();
		check(Token.IDCOLON, "an Id was expected");

		boolean ln = false;

		switch (lexer.getStringValue()) {
		case "print:":
			ln = false;
			break;
		case "println:":
			ln = true;
			break;
		default:
			error("There's no method named " + lexer.getStringValue() + " in class Out" + " with the specified params");
			break;
		}

		next();

		List<Expr> exprList = exprList();

		for (Expr expr : exprList) {
			if (expr.getType() != Type.stringType && expr.getType() != Type.intType) {
				error("Attempt to print a " + expr.getType().getName() + " expression");
			}
		}

		return new PrintStat(ln, exprList);
	}

	private AssignExpr assignExpr() {
		Expr leftExpr = null, rightExpr = null;

		leftExpr = expr();

		if (leftExpr.hasMethodCallWithReturn()) {
			error("Return value of method call not used");
		}

		if (lexer.token == Token.ASSIGN) {
			if (!leftExpr.isAssignable()) {
				error("Left expression isn't assignable");
			}
			next();
			rightExpr = expr();

			if (!(leftExpr.getType().canConvertFrom(rightExpr.getType())))
				error(rightExpr.getType().getName() + " expression isn't convertible to " + leftExpr.getType().getName()
						+ " expression");

			return new AssignExpr(leftExpr, rightExpr);
		} else {
			return new AssignExpr(leftExpr);
		}
	}

	private LocalDec localDec() {
		Type type = Type.undefinedType;
		List<Id> idList = new ArrayList<Id>();
		Expr expr = null;

		next();
		type = type();
		check(Token.ID, "A variable name was expected");

		Id id = id(type);

		if (symbolTable.hasId(id)) {
			error("There's already a local var named " + id.getName());
		}

		symbolTable.putId(id);

		idList.add(id);

		while (lexer.token == Token.COMMA) {
			next();
			id = id(type);
			if (symbolTable.hasId(id)) {
				error("There's already a local var named " + id.getName());
			}
			symbolTable.putId(id);
			idList.add(id);
		}

		if (lexer.token == Token.ASSIGN) {
			next();
			if (idList.size() > 1)
				error("Only a single variable should follow the type");
			expr = expr();

			if (!(type.canConvertFrom(expr.getType()))) {
				error(expr.getType().getName() + " expression isn't convertible to " + type.getName() + " expression");
			}
		}

		return new LocalDec(type, idList, expr);

	}

	private RepeatStat repeatStat() {

		List<Stat> statList = new ArrayList<Stat>();
		Expr expr = null;

		next();
		while (lexer.token != Token.UNTIL && lexer.token != Token.RIGHTCURBRACKET && lexer.token != Token.END) {
			statList.add(statement());
		}
		check(Token.UNTIL, "missing keyword 'until'");
		next();
		expr = expr();
		checkType(expr, Type.booleanType, "expected Boolean expression");

		return new RepeatStat(statList, expr);
	}

	private Break breakStat() {
		next();

		if (nestedLoops < 1)
			error("break cannot be used outside of a loop");

		return new Break();
	}

	private ReturnStat returnStat() {
		Expr expr = null;

		next();
		expr = expr();

		if (this.currentMethod.getType() == Type.undefinedType) {
			error("this method cannot have a return statement");
		} else if (!this.currentMethod.getType().canConvertFrom(expr.getType())) {
			error("cannot convert from " + expr.getType().getName() + " to " + this.currentMethod.getType().getName());
		} else {
			this.methodReturned = true;
		}
		return new ReturnStat(expr,this.currentMethod.getType());
	}

	private WhileStat whileStat() {
		List<Stat> statList = new ArrayList<Stat>();
		Expr expr = null;
		next();
		expr = expr();
		checkType(expr, Type.booleanType, "expected Boolean expression");

		check(Token.LEFTCURBRACKET, "missing '{' after the 'while' expression");
		next();
		while (lexer.token != Token.RIGHTCURBRACKET && lexer.token != Token.END) {
			statList.add(statement());
		}
		check(Token.RIGHTCURBRACKET, "missing '}' after 'while' body");
		next();
		return new WhileStat(statList, expr);
	}

	private IfStat ifStat() {
		Expr expr;
		List<Stat> ifStList = new ArrayList<Stat>();
		List<Stat> elseStList = new ArrayList<Stat>();
		next();
		expr = expr();
		checkType(expr, Type.booleanType, "expected Boolean expression");

		check(Token.LEFTCURBRACKET, "'{' expected after the 'if' expression");
		next();
		while (lexer.token != Token.RIGHTCURBRACKET && lexer.token != Token.END && lexer.token != Token.ELSE) {
			ifStList.add(statement());
		}
		check(Token.RIGHTCURBRACKET, "'}' was expected");
		lexer.nextToken();
		if (lexer.token == Token.ELSE) {
			next();
			check(Token.LEFTCURBRACKET, "'{' expected after 'else'");
			next();
			while (lexer.token != Token.RIGHTCURBRACKET) {
				elseStList.add(statement());
			}
			check(Token.RIGHTCURBRACKET, "'}' was expected");
			next();
		}
		return new IfStat(expr, ifStList, elseStList);
	}

	private void checkType(Expr expr, Type shouldBe, String msg) {
		if (expr.getType() != shouldBe)
			error(msg);
	}

	private void checkType(SimpleExpr simpleExpr, Type shouldBe, String msg) {
		if (simpleExpr.getType() != shouldBe)
			error(msg);
	}

	private FieldDec fieldDec() {
		Type t = Type.undefinedType;
		List<Id> idList = new ArrayList<Id>();

		check(Token.VAR, "'var' was expected");
		lexer.nextToken();
		t = type();

		check(Token.ID, "A field name was expected");

		Id id = id(t);

		if (self.hasField(id.getName())) {
			error("There's already a field named " + id.getName() + " on " + self.getName());
		}

		idList.add(id);

		while (lexer.token == Token.COMMA) {
			next();
			idList.add(id(t));
		}

		if (lexer.token == Token.SEMICOLON) {
			next();
		}

		return new FieldDec(idList);

	}

	private Type type() {
		Type t = Type.undefinedType;

		if (lexer.token == Token.INT || lexer.token == Token.BOOLEAN || lexer.token == Token.STRING) {
			if (lexer.token == Token.INT)
				t = Type.intType;
			else if (lexer.token == Token.BOOLEAN)
				t = Type.booleanType;
			else
				t = Type.stringType;
			next();
		} else if (lexer.token == Token.ID) {
			TypeCianetoClass typeCianetoClass;
			Id id = id();
			typeCianetoClass = symbolTable.getClass(id);
			if (typeCianetoClass == null) {
				error("There's no class named " + id.getName());
			}
			t = typeCianetoClass;
		} else {
			this.error("A type was expected");
		}

		return t;

	}

	private String qualifier() {
		String s = "";

		if (lexer.token == Token.PRIVATE) {
			s = s + Token.PRIVATE.toString();
			next();
		} else if (lexer.token == Token.PUBLIC) {
			s = s + Token.PUBLIC.toString();
			next();
		} else if (lexer.token == Token.OVERRIDE) {
			s = s + Token.OVERRIDE.toString();
			next();
			if (lexer.token == Token.PUBLIC) {
				s = s + " " + Token.PUBLIC.toString();
				next();
			}
		} else if (lexer.token == Token.FINAL) {
			s = s + Token.FINAL.toString();
			next();
			if (lexer.token == Token.PUBLIC) {
				s = s + " " + Token.PUBLIC.toString();
				next();
			} else if (lexer.token == Token.OVERRIDE) {
				s = s + " " + Token.OVERRIDE.toString();
				next();
				if (lexer.token == Token.PUBLIC) {
					s = s + " " + Token.PUBLIC.toString();
					next();
				}
			}
		}

		return s;

	}

	private Stat assertStat() {

		lexer.nextToken();
		Expr expr = expr();
		checkType(expr, Type.booleanType, "expected Boolean expression");
		check(Token.COMMA, "',' expected after the expression of the 'assert' statement");
		next();
		check(Token.LITERALSTRING, "A literal string expected after the ',' of the 'assert' statement");
		StringValue stringValue = new StringValue(lexer.getStringValue());
		next();

		return new AssertStat(expr, stringValue);
	}

	private Expr expr() {

		SimpleExpr simpleExprLeft = simpleExpr();
		SimpleExpr simpleExprRight = null;
		String relation = null;
		Expr expr = null;

		switch (lexer.token) {
		case LT:
		case LE:
		case GT:
		case GE:
			checkType(simpleExprLeft, Type.intType, "expected left Int expression");
			relation = lexer.token.toString();
			lexer.nextToken();
			simpleExprRight = simpleExpr();
			checkType(simpleExprRight, Type.intType, "expected right Int expression");
			expr = new Expr(simpleExprLeft, relation, simpleExprRight);
			break;
		case EQ:
		case NEQ:
			relation = lexer.token.toString();
			lexer.nextToken();
			simpleExprRight = simpleExpr();
			if (!simpleExprLeft.getType().canConvertFrom(simpleExprRight.getType())
					&& !simpleExprRight.getType().canConvertFrom(simpleExprLeft.getType()))
				error("illegal comparision beteween " + simpleExprLeft.getType().getName() + " expression and "
						+ simpleExprRight.getType().getName() + " expression");
			expr = new Expr(simpleExprLeft, relation, simpleExprRight);
			break;
		default:
			expr = new Expr(simpleExprLeft);
			break;
		}

		return expr;

	}

	private SimpleExpr simpleExpr() {

		SumSubExpr sumSubExpr = sumSubExpr();
		SimpleExpr simpleExpr = new SimpleExpr(sumSubExpr);

		while (lexer.token == Token.PLUSPLUS) {
			if (sumSubExpr.getType() != Type.stringType && sumSubExpr.getType() != Type.intType)
				error("expected String expression or Int expression");
			lexer.nextToken();
			sumSubExpr = sumSubExpr();
			if (sumSubExpr.getType() != Type.stringType && sumSubExpr.getType() != Type.intType)
				error("expected String expression or Int expression");
			simpleExpr.addSumSubExpr(sumSubExpr);
		}

		return simpleExpr;

	}

	private SumSubExpr sumSubExpr() {
		Term term = term();
		SumSubExpr sumSubExpr = new SumSubExpr(term);

		if (lexer.token == Token.PLUS || lexer.token == Token.MINUS || lexer.token == Token.OR) {
			if (lexer.token == Token.OR && term.getType() != Type.booleanType) {
				error("\"" + lexer.token.toString() + "\" operands must be " + Type.booleanType.getName());
			} else if (!(lexer.token == Token.OR) && term.getType() != Type.intType) {
				error(term.getType() + " - " + Type.intType);
				error("\"" + lexer.token.toString() + "\" operands must be " + Type.intType.getName());
			}
		}

		while (lexer.token == Token.PLUS || lexer.token == Token.MINUS || lexer.token == Token.OR) {
			String lowOp = lexer.token.toString();
			lexer.nextToken();
			term = term();

			if (lowOp.equals(Token.OR.toString()) && term.getType() != Type.booleanType) {
				error("\"" + lowOp + "\" operands must be " + Type.booleanType.getName());
			} else if (!(lowOp.equals(Token.OR.toString())) && term.getType() != Type.intType) {
				error("\"" + lowOp + "\" operands must be " + Type.intType.getName());
			}

			sumSubExpr.addLowOpTerm(lowOp, term);
		}

		return sumSubExpr;

	}

	private Term term() {
		SignalFactor signalFactor = signalFactor();
		Term term = new Term(signalFactor);

		if (lexer.token == Token.MULT || lexer.token == Token.DIV || lexer.token == Token.AND) {
			if (lexer.token == Token.AND && signalFactor.getType() != Type.booleanType) {
				error("\"" + lexer.token.toString() + "\" operands must be " + Type.booleanType.getName());
			} else if (lexer.token != Token.AND && signalFactor.getType() != Type.intType) {
				error("\"" + lexer.token.toString() + "\" operands must be " + Type.intType.getName());
			}
		}

		while (lexer.token == Token.MULT || lexer.token == Token.DIV || lexer.token == Token.AND) {
			String highOp = lexer.token.toString();
			lexer.nextToken();

			signalFactor = signalFactor();

			if (highOp.equals(Token.AND.toString()) && signalFactor.getType() != Type.booleanType) {
				error("\"" + highOp + "\" operands must be " + Type.booleanType.getName());
			} else if (!(highOp.equals(Token.AND.toString())) && signalFactor.getType() != Type.intType) {
				error("\"" + highOp + "\" operands must be " + Type.intType.getName());
			}

			term.addHighOpSignalFactor(highOp, signalFactor);
		}

		return term;

	}

	private SignalFactor signalFactor() {

		SignalFactor signalFactor = null;
		String signal;

		switch (lexer.token) {
		case PLUS:
		case MINUS:
			signal = lexer.token.toString();
			lexer.nextToken();
			signalFactor = new SignalFactor(signal, factor());
			break;
		default:
			signalFactor = new SignalFactor(factor());
			break;
		}

		return signalFactor;

	}

	private Factor factor() {

		Factor factor = null;

		if (lexer.token == Token.LITERALINT) {
			factor = new IntValue(lexer.getNumberValue());
			lexer.nextToken();
		} else if (lexer.token == Token.TRUE || lexer.token == Token.FALSE) {
			factor = new BooleanValue(lexer.token == Token.TRUE);
			lexer.nextToken();
		} else if (lexer.token == Token.LITERALSTRING) {
			factor = new StringValue(lexer.getLiteralStringValue());
			lexer.nextToken();
		} else if (lexer.token == Token.LEFTPAR) {
			lexer.nextToken();
			factor = new ExprFactor(expr());
			check(Token.RIGHTPAR, "')' was expected");
			lexer.nextToken();
		} else if (lexer.token == Token.NOT) {
			lexer.nextToken();
			factor = factor();
			checkType(factor, Type.booleanType, "\"!\" operand must be Boolean");
			factor = new NegationFactor(factor);
		} else if (lexer.token == Token.NIL) {
			factor = new NilFactor();
			next();
		} else if (lexer.token == Token.ID) {

			Id id = id();

			if (id.getName().equals("In")) {
				return readExpr();
			}

			if (lexer.token == Token.DOT) {

				lexer.nextToken();

				if (lexer.token == Token.NEW) {
					lexer.nextToken();
					TypeCianetoClass typeCianetoClass = symbolTable.getClass(id);
					if (typeCianetoClass == null) {
						error("There's no class named " + id.getName());
					}
					id.setType(typeCianetoClass);
					factor = new ObjectCreation(id);
				} else {
					if (!symbolTable.hasId(id)) {
						error("There's no id named " + id.getName());
					}
					id = symbolTable.getId(id);
					factor = primaryExpr(id, true);
				}
			} else {
				if (!symbolTable.hasId(id)) {
					error("There's no id named " + id.getName());
				}
				id = symbolTable.getId(id);
				factor = primaryExpr(id, false);
			}

		} else {
			factor = primaryExpr();
		}

		return factor;
	}

	private Factor readExpr() {
		ReadExpr readExpr = null;

		check(Token.DOT, "an '.' was expected");
		next();

		if (lexer.token == Token.ID) {
			Id id = id();
			switch (id.getName()) {
			case "readInt":
				readExpr = new ReadExpr(Type.intType);
				break;
			case "readString":
				readExpr = new ReadExpr(Type.stringType);
				break;
			default:
				error("There's no method named " + id.getName() + " in class In" + " with the specified params");
				break;
			}
		} else if (lexer.token == Token.IDCOLON) {
			Id id = idColon();
			error("There's no method named " + id.getName() + " in class In with the specified params");
		} else {
			error("'Id' was expected");
		}

		return readExpr;

	}

	private void checkType(Factor factor, Type shouldBe, String msg) {
		if (factor.getType() != shouldBe)
			error(msg);
	}

	private PrimaryExpr primaryExpr(Id id, boolean hasDot) {

		/*
		 * Não existe um bloco de código pro In.read{Int|String} pois a classe/objeto
		 * será adicionada à lista no inicio do compilador, logo cairá no caso Id.id
		 */

		PrimaryExpr primaryExpr = null;

		if (hasDot) {
			if (lexer.token == Token.ID) {
				if (!symbolTable.hasId(id)) {
					error("There's no id named " + id.getName());
				}
				id = symbolTable.getId(id);
				if (id.getType().getClass() != TypeCianetoClass.class) {
					error("The id " + id.getName() + "isn't an instance of a class");
				}
				Id id2 = id();
				TypeCianetoClass typeCianetoClass = (TypeCianetoClass) id.getType();
				int methodIdx = typeCianetoClass.getPublicMethodIdx(id2.getName());
				if (methodIdx == -1) {
					error("Method not found in class " + typeCianetoClass.getName());
				}
				id2 = typeCianetoClass.getMethod(id2.getName());
				primaryExpr = new PrimaryExprIdMethod(id, id2, methodIdx);
			} else if (lexer.token == Token.IDCOLON) {
				if (!symbolTable.hasId(id)) {
					error("There's no id named " + id.getName());
				}
				id = symbolTable.getId(id);
				if (id.getType().getClass() != TypeCianetoClass.class) {
					error(id.getName() + " is not an instance of a class");
				}
				TypeCianetoClass typeCianetoClass = (TypeCianetoClass) id.getType();
				Id id2 = idColon();
				List<Expr> exprList = exprList();
				int methodIdx = typeCianetoClass.getPublicMethodIdx(id2.getName(), exprList);
				if (methodIdx != -1) {
					id2 = typeCianetoClass.getMethod(id2.getName(), exprList);
					primaryExpr = new PrimaryExprIdMethod(id, id2, exprList, methodIdx);
				} else {
					error("There's no method in " + typeCianetoClass.getName() + " named " + id2.getName());
				}
			} else {
				error("'Id' was expected");
			}
		} else {
			if (!symbolTable.hasId(id)) {
				error("There's no id named " + id.getName());
			}
			id = symbolTable.getId(id);
			primaryExpr = new PrimaryExprId(id);
		}

		return primaryExpr;
	}

	private PrimaryExpr primaryExpr() {

		PrimaryExpr primaryExpr = null;

		if (lexer.token == Token.SUPER) {
			if (self.getSuperclass() == null) {
				error(self.getName() + "doesn't has a super class");
			}
			lexer.nextToken();
			check(Token.DOT, "'.' was expected");
			lexer.nextToken();
			if (lexer.token == Token.ID) {
				Id method = id();
				if (self.getSuperclass().getPublicMethodIdx(method.getName()) == -1) {
					error("There's no unary method named " + method.getName() + " on "
							+ self.getSuperclass().getName());
				}
				method = self.getSuperclass().getMethod(method.getName());
				primaryExpr = new PrimaryExprSuperMethod(method);
			} else if (lexer.token == Token.IDCOLON) {
				Id method = idColon();
				List<Expr> exprList = exprList();
				if (self.getSuperclass().getPublicMethodIdx(method.getName(), exprList) == -1) {
					error("There's no method named " + method.getName() + "in " + self.getSuperclass().getName()
							+ " with the specified params");
				}
				method = self.getSuperclass().getMethod(method.getName(), exprList);
				primaryExpr = new PrimaryExprSuperMethod(method, exprList);
			} else {
				error("'Id' was expected");
			}
		} else if (lexer.token == Token.SELF) {
			lexer.nextToken();
			if (lexer.token == Token.DOT) {
				lexer.nextToken();
				if (lexer.token == Token.ID) {
					Id id = id();
					if (self.hasField(id.getName())) {
						id = self.getField(id.getName());
						primaryExpr = new PrimaryExprSelfField(id);
					} else if (self.hasMethod(id.getName())) {
						id = self.getMethod(id.getName());
						primaryExpr = new PrimaryExprSelfMethod(id);
					} else {
						error("There's no field nor unary method named " + id.getName() + " in class "
								+ self.getName());
					}
					if (lexer.token == Token.DOT) {
						if (id.getType().getClass() != TypeCianetoClass.class) {
							error(id.getName() + " isn't a class");
						}
						TypeCianetoClass classType = (TypeCianetoClass) id.getType();
						lexer.nextToken();
						if (lexer.token == Token.ID) {
							Id id2 = id();
							if (classType.hasField(id2.getName())) {
								id2 = classType.getField(id2.getName());
								primaryExpr = new PrimaryExprSelfIdField(id, id2);
							} else if (classType.getPublicMethodIdx(id2.getName()) != -1) {
								id2 = classType.getMethod(id2.getName());
								primaryExpr = new PrimaryExprSelfIdMethod(id, id2);
							} else {
								error("There's no field nor unary method named " + id2.getName() + " on class"
										+ classType.getName());
							}
						} else if (lexer.token == Token.IDCOLON) {
							Id method = idColon();
							List<Expr> exprList = exprList();
							if (classType.getPublicMethodIdx(method.getName(), exprList) == -1 ) {
								error("Method not found in class " + classType.getName());
							}
							method = classType.getMethod(method.getName(), exprList);
							primaryExpr = new PrimaryExprSelfIdMethod(id, method, exprList);
						} else {
							error("'Id' was expected");
						}
					}
				} else if (lexer.token == Token.IDCOLON) {
					Id method = idColon();
					List<Expr> exprList = exprList();
					if (!self.hasMethod(method.getName(), exprList)) {
						error("There's no method named " + method.getName() + " on class " + self.getName()
								+ " with the specified params");
					}
					method = self.getMethod(method.getName(), exprList);
					primaryExpr = new PrimaryExprSelfMethod(method, exprList);
				} else {
					error("':' was expected");
				}
			} else {
				primaryExpr = new PrimaryExprSelf(new Id(self.getName(), self));
			}
		} else {
			error("'super', 'Id' or 'self' was expected");
		}

		return primaryExpr;
	}

	private List<Expr> exprList() {
		List<Expr> exprList = new ArrayList<>();

		exprList.add(expr());

		while (lexer.token == Token.COMMA) {
			lexer.nextToken();
			exprList.add(expr());
		}

		return exprList;
	}

	private Id idColon(Type type) {
		check(Token.IDCOLON, "a identifier with colon was expected");
		String id = lexer.getStringValue().substring(0, lexer.getStringValue().length() - 1);
		lexer.nextToken();
		return new Id(id, type);
	}

	private Id id(Type type) {
		check(Token.ID, "a identifier was expected");
		String id = lexer.getStringValue();
		lexer.nextToken();
		return new Id(id, type);
	}


	private Id id(Type type, boolean nextToken) {
		check(Token.ID, "a identifier was expected");
		String id = lexer.getStringValue();
		if(nextToken)
			lexer.nextToken();
		return new Id(id, type);
	}

	private Id id() {
		return id(Type.undefinedType);
	}


	private Id id(boolean nextToken) {
		return id(Type.undefinedType,nextToken);
	}

	private Id idColon() {
		return idColon(Type.undefinedType);
	}

	private SymbolTable symbolTable = new SymbolTable();
	private TypeCianetoClass self = null;
	private MethodDec currentMethod = null;
	private boolean methodReturned = false;
	private long nestedLoops = 0;
	private Lexer lexer;
	private ErrorSignaller signalError;
}
