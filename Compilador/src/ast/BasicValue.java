package ast;
import java.util.*;

public abstract class BasicValue extends Factor{
    
    public abstract void genJava(PW pw);    
}