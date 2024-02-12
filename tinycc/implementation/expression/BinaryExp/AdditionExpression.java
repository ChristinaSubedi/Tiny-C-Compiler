package tinycc.implementation.expression.BinaryExp;



import tinycc.diagnostic.Diagnostic;
import tinycc.implementation.Scope;
import tinycc.implementation.expression.BinaryExpression;
import tinycc.implementation.expression.Expression;
import tinycc.implementation.type.IntegerType;
import tinycc.implementation.type.PointerType;
import tinycc.implementation.type.Type;
import tinycc.parser.Token;

public class AdditionExpression extends BinaryExpression {
    public AdditionExpression(Token operator, Expression left, Expression right) {
        super(operator, left, right);
    }

    public Type checkType(Diagnostic d, Scope s) {
        Type l = getLeft().checkType(d, s);
        Type r = getRight().checkType(d, s);

        if (l.isInteger() && r.isInteger()){
            return new IntegerType();
        } else if (l.isPointer() && l.isComplete() && r.isInteger()){
            PointerType lPointerType=(PointerType) l;
            if (lPointerType.isVoidPointer() ){
                d.printError(operator, "void pointer",l);
            }
            return l;
        } else if (r.isPointer() && r.isComplete() && l.isInteger()){
            PointerType rPointerType=(PointerType) r;
            if (rPointerType.isVoidPointer()){
                d.printError(operator, "void pointer",r);
            }

            return r;
        }
        d.printError(this.operator, "Addition not correct rules");
        return null;
  
      }

}
