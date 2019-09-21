package ast;
import java.util.*;

public abstract class ReadExpr extends PrimaryExpr{

    public abstract void genJava(PW pw);    
}