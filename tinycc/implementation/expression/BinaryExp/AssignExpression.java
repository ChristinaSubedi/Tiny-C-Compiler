package tinycc.implementation.expression.BinaryExp;

import tinycc.diagnostic.Diagnostic;
import tinycc.implementation.Scope;
import tinycc.implementation.expression.BinaryExpression;
import tinycc.implementation.expression.Expression;
import tinycc.implementation.type.IntegerType;
import tinycc.implementation.type.PointerType;
import tinycc.implementation.type.Type;
import tinycc.parser.Token;

public class AssignExpression extends BinaryExpression {
    public AssignExpression(Token operator, Expression left, Expression right) {
        super(operator, left, right);
    }

    @Override
    public Type checkType(Diagnostic d, Scope s) {
        Type l = getLeft().checkType(d, s);
        Type r = getRight().checkType(d, s);

        if (!(getLeft().isLvalue())){
            d.printError(operator, "left is not a valid l value",l);
            
        } else if (!(l.isScalar() && r.isScalar())){
            d.printError(operator, "not scalar",l,r);
        } else if (l.equals(r)){
            return l;
        } else if (l.isInteger() && r.isInteger()){
            return new IntegerType();
        } else if (l.isPointer() && r.isPointer()){
            PointerType lPointerType=(PointerType) l;
            PointerType rPointerType=(PointerType) r;
            if (lPointerType.getType().isVoid() && rPointerType.getType().isVoid() ){
                return l;
            }

            if (!lPointerType.getType().isVoid() && rPointerType.getType().isVoid() ){
                return l;
            }

            if (lPointerType.getType().isVoid() && !rPointerType.getType().isVoid() ){
                return r;
            }

        } else if (l.isPointer() && r.isInteger()){
            if (getRight().isNullPointer()){
                return l;
            }
        } else if (l.equals(r)){
            return l;
        }
        d.printError(operator, "assign is invalid", l,r);
        return null;
    }
}
