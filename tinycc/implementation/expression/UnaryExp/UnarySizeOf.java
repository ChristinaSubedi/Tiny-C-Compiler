package tinycc.implementation.expression.UnaryExp;

import tinycc.diagnostic.Diagnostic;
import tinycc.implementation.Scope;
import tinycc.implementation.expression.Expression;
import tinycc.implementation.expression.UnaryExpression;
import tinycc.implementation.type.IntegerType;
import tinycc.implementation.type.PointerType;
import tinycc.implementation.type.Type;
import tinycc.parser.Token;

public class UnarySizeOf extends UnaryExpression {

    public UnarySizeOf(Token operator, boolean postfix, Expression operand) {
        super(operator, postfix, operand);
    }

    @Override
    public Type checkType(Diagnostic d, Scope s) {
        Type t=getOperand().checkType(d, s);
        if (t.isPointer()){
            PointerType p=(PointerType) t;
            if (p.getType().isVoid()){
                d.printError(getOperator(), "can't calculate sizeof of void pointer", t);
            }
        }
        if (t.isVoid()){
            d.printError(getOperator(), "can't calculate sizeof of void ", t);
        }
        if (t.isObject()){
            return new IntegerType();
        } 
        d.printError(getOperator(), "not valid object", t);
        return null;
    }
    
}
