package comp;

import java.beans.Expression;
import java.io.PrintWriter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Hashtable;
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

				ClassDec c = classDec();

				program.addClass(c.getTypeCianetoClass(program.getClassList()));
			} catch (CompilerError e) {
				// if there was an exception, there is a compilation error
				thereWasAnError = true;
				while (lexer.token != Token.CLASS && lexer.token != Token.EOF) {
					try {
						next();
					} catch (RuntimeException ee) {
						e.printStackTrace();
						return program;
					}
				}
			} catch (RuntimeException e) {
				e.printStackTrace();
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

	private ClassDec classDec() {
		boolean open = false;
		Id id = null;
		Id extendsId = null;
		MemberList memberList = null;

		if (lexer.token == Token.ID && lexer.getStringValue().equals("open")) {
			open = true;
		}

		check(Token.CLASS, "'class' expected");
		lexer.nextToken();

		check(Token.ID, "'Identifier' expected");

		String className = lexer.getStringValue();
		id = new Id(className, Type.undefinedType);
		// symbolTable.putInGlobal(className, id);

		lexer.nextToken();

		if (lexer.token == Token.EXTENDS) {
			lexer.nextToken();
			check(Token.ID, "'identifier' expected");
			String superclassName = lexer.getStringValue();
			extendsId = new Id(superclassName, Type.undefinedType);
			// symbolTable.putInGlobal(superclassName, extendsId);

			lexer.nextToken();
		}

		memberList = memberList();

		if (lexer.token != Token.END)
			error("'end' expected");
		lexer.nextToken();

		return new ClassDec(id, extendsId, memberList, open);
	}

	private MemberList memberList() {
		String qualifier = "";
		List<AbstractMap.SimpleEntry<String, Member>> members = new ArrayList<AbstractMap.SimpleEntry<String, Member>>();

		while (true) {
			// qualifier = qualifier();
			qualifier();
			if (lexer.token == Token.VAR) {
				if (qualifier != "private" && qualifier != "")
					error("Invalid qualifier");
				members.add(new AbstractMap.SimpleEntry<String, Member>(qualifier, fieldDec()));
			} else if (lexer.token == Token.FUNC) {
				members.add(new AbstractMap.SimpleEntry<String, Member>(qualifier, methodDec()));
			} else {
				break;
			}
		}
		return new MemberList(members);
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

	private MethodDec methodDec() {
		lexer.nextToken();
		if (lexer.token == Token.ID) {
			// unary method
			lexer.nextToken();

		} else if (lexer.token == Token.IDCOLON) {
			// keyword method. It has parameters

		} else {
			error("An identifier or identifer: was expected after 'func'");
		}
		if (lexer.token == Token.MINUS_GT) {
			// method declared a return type
			lexer.nextToken();
			type();
		}
		if (lexer.token != Token.LEFTCURBRACKET) {
			error("'{' expected");
		}
		next();
		statementList();
		if (lexer.token != Token.RIGHTCURBRACKET) {
			error("'{' expected");
		}
		next();
		return null;

	}

	private void statementList() {
		// only '}' is necessary in this test
		while (lexer.token != Token.RIGHTCURBRACKET && lexer.token != Token.END) {
			statement();
		}
	}

	private void statement() {
		boolean checkSemiColon = true;
		switch (lexer.token) {
		case IF:
			ifStat();
			checkSemiColon = false;
			break;
		case WHILE:
			whileStat();
			checkSemiColon = false;
			break;
		case RETURN:
			returnStat();
			break;
		case BREAK:
			breakStat();
			break;
		case SEMICOLON:
			next();
			break;
		case REPEAT:
			repeatStat();
			break;
		case VAR:
			localDec();
			break;
		case ASSERT:
			assertStat();
			break;
		default:
			if (lexer.token == Token.ID && lexer.getStringValue().equals("Out")) {
				writeStat();
			} else {
				expr();
			}

		}
		if (checkSemiColon) {
			check(Token.SEMICOLON, "';' expected");
		}
	}

	private void localDec() {
		next();
		type();
		check(Token.ID, "A variable name was expected");
		while (lexer.token == Token.ID) {
			next();
			if (lexer.token == Token.COMMA) {
				next();
			} else {
				break;
			}
		}
		if (lexer.token == Token.ASSIGN) {
			next();
			// check if there is just one variable
			expr();
		}

	}

	private void repeatStat() {
		next();
		while (lexer.token != Token.UNTIL && lexer.token != Token.RIGHTCURBRACKET && lexer.token != Token.END) {
			statement();
		}
		check(Token.UNTIL, "missing keyword 'until'");
	}

	private void breakStat() {
		next();

	}

	private void returnStat() {
		next();
		expr();
	}

	private void whileStat() {
		next();
		expr();
		check(Token.LEFTCURBRACKET, "missing '{' after the 'while' expression");
		next();
		while (lexer.token != Token.RIGHTCURBRACKET && lexer.token != Token.END) {
			statement();
		}
		check(Token.RIGHTCURBRACKET, "missing '}' after 'while' body");
	}

	private void ifStat() {
		next();
		expr();
		check(Token.LEFTCURBRACKET, "'{' expected after the 'if' expression");
		next();
		while (lexer.token != Token.RIGHTCURBRACKET && lexer.token != Token.END && lexer.token != Token.ELSE) {
			statement();
		}
		check(Token.RIGHTCURBRACKET, "'}' was expected");
		if (lexer.token == Token.ELSE) {
			next();
			check(Token.LEFTCURBRACKET, "'{' expected after 'else'");
			next();
			while (lexer.token != Token.RIGHTCURBRACKET) {
				statement();
			}
			check(Token.RIGHTCURBRACKET, "'}' was expected");
		}
	}

	/**
	
	 */
	private void writeStat() {
		next();
		check(Token.DOT, "a '.' was expected after 'Out'");
		next();
		check(Token.IDCOLON, "'print:' or 'println:' was expected after 'Out.'");
		String printName = lexer.getStringValue();
		expr();
	}

	private FieldDec fieldDec() {
		lexer.nextToken();
		type();
		if (lexer.token != Token.ID) {
			this.error("A field name was expected");
		} else {
			while (lexer.token == Token.ID) {
				lexer.nextToken();
				if (lexer.token == Token.COMMA) {
					lexer.nextToken();
				} else {
					break;
				}
			}
		}
		return null;

	}

	private void type() {
		if (lexer.token == Token.INT || lexer.token == Token.BOOLEAN || lexer.token == Token.STRING) {
			next();
		} else if (lexer.token == Token.ID) {
			next();
		} else {
			this.error("A type was expected");
		}

	}

	private void qualifier() {
		if (lexer.token == Token.PRIVATE) {
			next();
		} else if (lexer.token == Token.PUBLIC) {
			next();
		} else if (lexer.token == Token.OVERRIDE) {
			next();
			if (lexer.token == Token.PUBLIC) {
				next();
			}
		} else if (lexer.token == Token.FINAL) {
			next();
			if (lexer.token == Token.PUBLIC) {
				next();
			} else if (lexer.token == Token.OVERRIDE) {
				next();
				if (lexer.token == Token.PUBLIC) {
					next();
				}
			}
		}
	}

	/**
	 * change this method to 'private'. uncomment it implement the methods it calls
	 */
	private Stat assertStat() {

		lexer.nextToken();
		int lineNumber = lexer.getLineNumber();
		expr();
		if (lexer.token != Token.COMMA) {
			this.error("',' expected after the expression of the 'assert' statement");
		}
		lexer.nextToken();
		if (lexer.token != Token.LITERALSTRING) {
			this.error("A literal string expected after the ',' of the 'assert' statement");
		}
		String message = lexer.getLiteralStringValue();
		lexer.nextToken();
		if (lexer.token == Token.SEMICOLON)
			lexer.nextToken();

		return null;
	}

	private IntValue intValue() {

		IntValue e = null;

		// the number value is stored in lexer.getToken().value as an object of
		// Integer.
		// Method intValue returns that value as an value of type int.
		int value = lexer.getNumberValue();
		lexer.nextToken();
		return new IntValue(value);
	}

	private static boolean startExpr(Token token) {

		return token == Token.FALSE || token == Token.TRUE || token == Token.NOT || token == Token.SELF
				|| token == Token.LITERALINT || token == Token.SUPER || token == Token.LEFTPAR || token == Token.NIL
				|| token == Token.ID || token == Token.LITERALSTRING;

	}

	private Expr expr() {

		SimpleExpr simpleExpr = simpleExpr();
		Expr expr = null;

		switch (lexer.token) {
		case EQ:
		case LT:
		case LE:
		case GT:
		case GE:
		case NEQ:
			expr = new Expr(simpleExpr, lexer.token.toString(), simpleExpr());
			break;
		default:
			expr = new Expr(simpleExpr);
			break;
		}

		return expr;
		
	}

	private SimpleExpr simpleExpr() {

		SimpleExpr simpleExpr = new SimpleExpr(sumSubExpr());

		while (lexer.token == Token.PLUSPLUS) {
			lexer.nextToken();
			simpleExpr.addSumSubExpr(sumSubExpr());
		}

		return simpleExpr;

	}

	private SumSubExpr sumSubExpr() {

		SumSubExpr sumSubExpr = new SumSubExpr(term());

		while (lexer.token == Token.PLUS || lexer.token == Token.MINUS || lexer.token == Token.OR) {
			String lowOp = lexer.token.toString();
			lexer.nextToken();
			sumSubExpr.addLowOpTerm(lowOp, term());
		}

		return sumSubExpr;

	}

	private Term term() {

		Term term = new Term(signalFactor());

		while (lexer.token == Token.MULT || lexer.token == Token.DIV || lexer.token == Token.AND) {
			String highOp = lexer.token.toString();
			lexer.nextToken();
			term.addHighOpSignalFactor(highOp, signalFactor());
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
		} else if (lexer.token == Token.LITERALSTRING) {
			factor = new StringValue(lexer.getStringValue());
		} else if (lexer.token == Token.LEFTPAR) {
			lexer.nextToken();
			factor = expr();
			check(Token.RIGHTPAR, "')' was expected");
			lexer.nextToken();
		} else if (lexer.token == Token.NOT) {
			lexer.nextToken();
			factor = new NegationFactor(factor());
		} else if (lexer.token == Token.NIL) {
			factor = new NilFactor();
		} else if (lexer.token == Token.ID) {

			Id id = id();

			if (lexer.token == Token.DOT) {

				lexer.nextToken();

				if (lexer.token == Token.NEW) {
					factor = new ObjectCreation(id);
				} else {
					factor = primaryExpr(id, true);
				}

			} else {
				factor = primaryExpr(id, false);
			}

		} else {
			factor = primaryExpr();
		}

		return factor;
	}

	private PrimaryExpr primaryExpr(Id id, boolean hasDot) {

		/*
		 * Não existe um bloco de código pro In.read{Int|String} pois a classe/objeto
		 * será adicionada à lista no inicio do compilador, logo cairá no caso Id.id
		 */

		PrimaryExpr primaryExpr = null;

		if (hasDot) {
			if (lexer.token == Token.ID) {
				primaryExpr = new PrimaryExprIdId(id, id());
			} else if (lexer.token == Token.IDCOLON) {
				primaryExpr = new PrimaryExprIdIdColon(id, id(), exprList());
			} else {
				error("'Id' was expected");
			}
		} else {
			primaryExpr = new PrimaryExprId(id);
		}

		return primaryExpr;
	}

	private PrimaryExpr primaryExpr() {

		PrimaryExpr primaryExpr = null;

		if (lexer.token == Token.SUPER) {
			lexer.nextToken();
			check(Token.DOT, "'.' was expected");
			lexer.nextToken();
			if (lexer.token == Token.ID) {
				primaryExpr = new PrimaryExprSuperId(null, id());
			} else if (lexer.token == Token.IDCOLON) {
				primaryExpr = new PrimaryExprSuperIdColon(null, id(), exprList());
			} else {
				error("'Id' was expected");
			}
		} else if (lexer.token == Token.SELF) {
			lexer.nextToken();
			if (lexer.token == Token.DOT) {
				lexer.nextToken();
				Id id = id();
				if (lexer.token == Token.DOT) {
					lexer.nextToken();
					if (lexer.token == Token.ID) {
						primaryExpr = new PrimaryExprSelfIdId(null, id, id());
					} else if (lexer.token == Token.IDCOLON) {
						primaryExpr = new PrimaryExprSelfIdIdColon(null, id, id(), exprList());
					} else {
						error("'Id' was expected");
					}
				} else {
					primaryExpr = new PrimaryExprSelfId(null, id);
				}
			} else {
				primaryExpr = new PrimaryExprSelf();
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

	private Id id(Type type) {
		check(Token.ID, "a identifier was expected");
		return new Id(lexer.getStringValue(), type);
	}

	private Id id() {
		return id(Type.undefinedType);
	}

	private SymbolTable symbolTable = new SymbolTable();
	private Lexer lexer;
	private ErrorSignaller signalError;
}
