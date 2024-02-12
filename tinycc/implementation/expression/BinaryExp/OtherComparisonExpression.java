package tinycc.implementation.expression.BinaryExp;

import tinycc.diagnostic.Diagnostic;
import tinycc.implementation.Scope;
import tinycc.implementation.expression.BinaryExpression;
import tinycc.implementation.expression.Expression;
import tinycc.implementation.type.IntegerType;
import tinycc.implementation.type.PointerType;
import tinycc.implementation.type.Type;
import tinycc.parser.Token;

public class OtherComparisonExpression extends BinaryExpression {
    public OtherComparisonExpression(Token operator, Expression left, Expression right) {
        super(operator, left, right);
    }

    @Override
    public Type checkType(Diagnostic d, Scope s) {
        Type l = getLeft().checkType(d, s);
        Type r = getRight().checkType(d, s);

        if (l.isInteger() && r.isInteger()){
            return new IntegerType();
        } else if (l.isPointer() && r.isPointer()){
            PointerType newR=(PointerType) r;
            PointerType newL=(PointerType) l;

            if (newR.isVoidPointer()  || newL.isVoidPointer()){
                d.printError(operator, "subtracting null or void pointer", l,r);
            }

            if( newR.getType().equals(newL.getType())){
                return new IntegerType();
            };
            d.printError(operator, "pointer type not the same", l , r);
        }
        d.printError(operator, "not correct comparison type", l, r);
        return null;
    }
}
