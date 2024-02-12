package tinycc.implementation.expression.BinaryExp;

import tinycc.diagnostic.Diagnostic;
import tinycc.implementation.Scope;
import tinycc.implementation.expression.BinaryExpression;
import tinycc.implementation.expression.Expression;
import tinycc.implementation.type.IntegerType;
import tinycc.implementation.type.Type;
import tinycc.parser.Token;

public class MultAndDivExpression extends BinaryExpression {
    public MultAndDivExpression(Token operator, Expression left, Expression right) {
        super(operator, left, right);
    }

    @Override
    public Type checkType(Diagnostic d, Scope s) {
        Type l = getLeft().checkType(d, s);
        Type r = getRight().checkType(d, s);

        if (l.isInteger() && r.isInteger()){
            return new IntegerType();
        }
        d.printError(operator, "not integer type", l, r);
        return null;
    }
}
