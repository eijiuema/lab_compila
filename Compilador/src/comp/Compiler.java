/*
*	Anderson Pinheiro Garrote
*	Gabriel Eiji Uema Martin
*/
package comp;

import java.io.PrintWriter;
import java.util.AbstractMap;
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

				ClassDec c = classDec();

				program.addClass(c.getTypeCianetoClass(program.getClassList()));
				// program.addClass(new TypeCianetoClass("test"));
			} catch (CompilerError e) {
				// if there was an exception, there is a compilation error
				// e.printStackTrace();
				thereWasAnError = true;
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
			lexer.nextToken();
		}

		check(Token.CLASS, "'class' expected");
		lexer.nextToken();

		check(Token.ID, "'Identifier' expected");

		id = id(Type.undefinedType);
		// symbolTable.putInGlobal(className, id);

		if (lexer.token == Token.EXTENDS) {
			lexer.nextToken();
			check(Token.ID, "'identifier' expected");
			extendsId = id();
			// symbolTable.get(superclassName, extendsId);
		}

		memberList = memberList();

		check(Token.END, "'end' was expected");
		lexer.nextToken();

		return new ClassDec(id, extendsId, memberList, open);
	}

	private MemberList memberList() {
		String qualif = "";
		List<AbstractMap.SimpleEntry<String, Member>> members = new ArrayList<AbstractMap.SimpleEntry<String, Member>>();

		while (true) {
			qualif = qualifier();
			if (lexer.token == Token.VAR) {
				if (!(qualif.equals("private") || qualif.equals(""))) {
					error("Invalid qualifier");
				}
				members.add(new AbstractMap.SimpleEntry<String, Member>(qualif, fieldDec()));
			} else if (lexer.token == Token.FUNC) {
				members.add(new AbstractMap.SimpleEntry<String, Member>(qualif, methodDec()));
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
		Id id = null;
		Type t = Type.undefinedType;
		List<Stat> stLst = null;
		List<ParamDec> forParDec = null;

		lexer.nextToken();
		if (lexer.token == Token.ID) {
			// TODO porcurar na symboltable (local) e inserir se n�o existir SymbolTable
			lexer.nextToken();

		} else if (lexer.token == Token.IDCOLON) {
			// keyword method. It has parameters
			id = idColon();
			forParDec = formalParamDec();
		} else {
			error("An identifier or identifer: was expected after 'func'");
		}
		if (lexer.token == Token.MINUS_GT) {
			// method declared a return type
			lexer.nextToken();
			t = type();
		}
		check(Token.LEFTCURBRACKET, "'{' expected");
		next();
		stLst = statementList();
		check(Token.RIGHTCURBRACKET, "'}' expected");
		next();
		return new MethodDec(id, t, forParDec, stLst);

	}

	private List<ParamDec> formalParamDec() {
		List<ParamDec> lst = new ArrayList<ParamDec>();

		lst.add(paramDec());

		while (lexer.token == Token.COMMA) {
			next();
			lst.add(paramDec());
		}

		if ((lexer.token != Token.MINUS_GT) && (lexer.token != Token.LEFTCURBRACKET))
			error("',' expected");

		return lst;
	}

	private ParamDec paramDec() {
		Type t = Type.undefinedType;
		Id id = null;

		t = type();

		id = id(t);

		// TODO Adicionar na SymbolTable ou dar erro

		return new ParamDec(t, id);
	}

	private List<Stat> statementList() {
		List<Stat> lst = new ArrayList<Stat>();
		// only '}' is necessary in this test
		while (lexer.token != Token.RIGHTCURBRACKET && lexer.token != Token.END) {
			lst.add(statement());
		}
		return lst;
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
			st = whileStat();
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
			st = repeatStat();
			break;
		case VAR:
			st = localDec();
			break;
		case ASSERT:
			st = assertStat();
			break;
		default:/*
				 * if (lexer.token == Token.ID && lexer.getStringValue().equals("Out")) { st =
				 * writeStat(); } else {
				 */
			st = assignExpr();
			// }

		}

		if (checkSemiColon) {
			check(Token.SEMICOLON, "';' expected");
			next();
		}

		return st;
	}

	private AssignExpr assignExpr() {
		Expr leftExpr = null, rightExpr = null;

		leftExpr = expr();

		if (lexer.token == Token.ASSIGN) {
			next();
			rightExpr = expr();

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

		idList.add(id());

		while (lexer.token == Token.COMMA) {
			next();
			idList.add(id());
		}

		if (lexer.token == Token.ASSIGN) {
			next();
			if (idList.size() > 1)
				error("Only a single variable should follow the type");
			expr = expr();
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

		return new RepeatStat(statList, expr);
	}

	private Break breakStat() {
		next();

		return new Break();
	}

	private ReturnStat returnStat() {
		Expr expr = null;

		next();
		expr = expr();

		return new ReturnStat(expr);
	}

	private WhileStat whileStat() {
		List<Stat> statList = new ArrayList<Stat>();
		Expr expr = null;
		next();
		expr = expr();
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

	private FieldDec fieldDec() {
		Type t = Type.undefinedType;
		List<Id> idList = new ArrayList<Id>();

		lexer.nextToken();
		t = type();

		check(Token.ID, "A field name was expected");

		idList.add(id(t));

		while (lexer.token == Token.COMMA) {
			next();
			idList.add(id(t));
		}

		if (lexer.token == Token.SEMICOLON) {
			next();
		}

		return new FieldDec(t, idList);

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
			// TODO pesquisar tipo na classTable
			t = Type.undefinedType;
			next();
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

	/**
	 * change this method to 'private'. uncomment it implement the methods it calls
	 */
	private Stat assertStat() {

		lexer.nextToken();
		Expr expr = expr();
		check(Token.COMMA, "',' expected after the expression of the 'assert' statement");
		next();
		check(Token.LITERALSTRING, "A literal string expected after the ',' of the 'assert' statement");
		StringValue stringValue = new StringValue(lexer.getStringValue());
		next();

		return new AssertStat(expr, stringValue);
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
			String relation = lexer.token.toString();
			lexer.nextToken();
			expr = new Expr(simpleExpr, relation, simpleExpr());
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
			lexer.nextToken();
		} else if (lexer.token == Token.LITERALSTRING) {
			factor = new StringValue(lexer.getStringValue());
			lexer.nextToken();
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
			next();
		} else if (lexer.token == Token.ID) {

			Id id = id();

			if (lexer.token == Token.DOT) {

				lexer.nextToken();

				if (lexer.token == Token.NEW) {
					lexer.nextToken();
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
				primaryExpr = new PrimaryExprIdIdColon(id, idColon(), exprList());
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
				primaryExpr = new PrimaryExprSuperIdColon(null, idColon(), exprList());
			} else {
				error("'Id' was expected");
			}
		} else if (lexer.token == Token.SELF) {
			lexer.nextToken();
			if (lexer.token == Token.DOT) {
				lexer.nextToken();

				if (lexer.token == Token.ID) {
					Id id = id();
					if (lexer.token == Token.DOT) {
						lexer.nextToken();
						if (lexer.token == Token.ID) {
							primaryExpr = new PrimaryExprSelfIdId(null, id, id());
						} else if (lexer.token == Token.IDCOLON) {
							primaryExpr = new PrimaryExprSelfIdIdColon(null, id, idColon(), exprList());
						} else {
							error("'Id' was expected");
						}
					} else {
						primaryExpr = new PrimaryExprSelfId(null, id);
					}
				} else {
					primaryExpr = new PrimaryExprSelfIdColon(null, idColon(), exprList());
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

	private Id id() {
		return id(Type.undefinedType);
	}

	private Id idColon() {
		return idColon(Type.undefinedType);
	}

	private SymbolTable symbolTable = new SymbolTable();
	private Lexer lexer;
	private ErrorSignaller signalError;
}
