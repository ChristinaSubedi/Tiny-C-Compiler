package tinycc.implementation.expression.BinaryExp;

import tinycc.diagnostic.Diagnostic;
import tinycc.implementation.Scope;
import tinycc.implementation.expression.BinaryExpression;
import tinycc.implementation.expression.Expression;
import tinycc.implementation.expression.PrimaryExpression;
import tinycc.implementation.type.IntegerType;
import tinycc.implementation.type.PointerType;
import tinycc.implementation.type.Type;
import tinycc.parser.Token;

public class IsEqualExpression extends BinaryExpression {
    public IsEqualExpression(Token operator, Expression left, Expression right) {
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

            if( newR.getType().equals(newL.getType())){
                return new IntegerType();
            };
            d.printError(operator, "pointer type not the same", l , r);
        } else if (l.isPointer() && r.isInteger()){
            if (getRight().isNullPointer()){
                return new IntegerType();
            }
            d.printError(operator, "right not correct null pointer", r);
        } else if (l.isInteger() && r.isPointer()){
            if (getRight().isNullPointer()){
                return new IntegerType();
            }
            d.printError(operator, "left not correct null pointer", l);
        }
        d.printError(operator, "invalid isequal", l, r);
        return null;
    }
}
