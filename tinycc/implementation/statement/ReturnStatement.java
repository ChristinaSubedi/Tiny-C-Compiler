package tinycc.implementation.statement;

import tinycc.diagnostic.Diagnostic;
import tinycc.diagnostic.Locatable;
import tinycc.implementation.Scope;
import tinycc.implementation.expression.Expression;
import tinycc.implementation.type.PointerType;
import tinycc.implementation.type.Type;

public class ReturnStatement extends Statement {
    Locatable loc;
    Expression expression;

    public ReturnStatement(Locatable loc, Expression expression){
        this.loc=loc;
        this.expression=expression;
        
    }

    @Override
    public String toString() {
        String s=(expression==null)? "Return[]" : "Return[" + expression.toString() +"]";
        return s;
    }

    @Override
    public void checkType(Diagnostic d, Scope s) {
        Type l=s.getReturnType();
        if (l.isVoid() && expression!=null){
            d.printError(loc, "return should be void but is ", expression);
        } else if (l.isVoid() && expression==null){
            return;
        }
        Type r=expression.checkType(d, s);
        if (!(l.isScalar() && r.isScalar())){
            d.printError(loc, "not scalar",l,r);
        } else if (l.equals(r)){
            return;
        } else if (l.isInteger() && r.isInteger()){
            return;
        } else if (l.isPointer() && r.isPointer()){
            PointerType lPointerType=(PointerType) l;
            PointerType rPointerType= (PointerType) r;
            if (lPointerType.getType().isVoid() || rPointerType.getType().isVoid()){
                return;
            }
        } else if (l.isPointer() && r.isInteger()){
            if (expression.isNullPointer()){
                return ;
            }
        } else if (l.equals(r)){
            return;
        }
        d.printError(loc, "assign is invalid", l,r);
    }
    
}
