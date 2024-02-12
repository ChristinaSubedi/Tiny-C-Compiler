package tinycc.implementation.expression.UnaryExp;

import tinycc.diagnostic.Diagnostic;
import tinycc.implementation.Scope;
import tinycc.implementation.expression.Expression;
import tinycc.implementation.expression.UnaryExpression;
import tinycc.implementation.type.PointerType;
import tinycc.implementation.type.Type;
import tinycc.parser.Token;

public class UnaryAddress extends UnaryExpression {

    public UnaryAddress(Token operator, boolean postfix, Expression operand) {
        super(operator, postfix, operand);
    }

    @Override
    public Type checkType(Diagnostic d, Scope s) {
        Type t=getOperand().checkType(d, s);
        if (!getOperand().isLvalue()){
            d.printError(getOperator(), "not proper l value", t);
        }
        if (!t.isObject()){
            d.printError(getOperator(), "not proper object",t);
        }
        return new PointerType(t);
    }
    
}
