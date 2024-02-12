package tinycc.implementation.expression.UnaryExp;

import tinycc.diagnostic.Diagnostic;
import tinycc.implementation.Scope;
import tinycc.implementation.expression.Expression;
import tinycc.implementation.expression.UnaryExpression;
import tinycc.implementation.type.ObjectType;
import tinycc.implementation.type.PointerType;
import tinycc.implementation.type.Type;
import tinycc.parser.Token;

public class UnaryPointer extends UnaryExpression {

    public UnaryPointer(Token operator, boolean postfix, Expression operand) {
        super(operator, postfix, operand);
    }

    @Override
    public boolean isLvalue(){
		return true;
	}

    @Override
    public Type checkType(Diagnostic d, Scope s) {
        Type t=getOperand().checkType(d, s);
        if (!t.isComplete()){
            d.printError(getOperator(), "pointer not complete in unary", t);
        }
        if (!t.isPointer()){
            d.printError(getOperator(), "not pointer", t);
        }
        PointerType newPointer=(PointerType) t;
        return  newPointer.getType();
    }
    
}
