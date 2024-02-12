package tinycc.implementation.expression;

import tinycc.diagnostic.Diagnostic;
import tinycc.implementation.Scope;
import tinycc.implementation.type.Type;
import tinycc.parser.Token;

public abstract class BinaryExpression extends Expression {
    protected Token operator;
    Expression left;
    protected Expression right;

    public BinaryExpression(Token operator, Expression left, Expression right){
        this.operator=operator;
        this.left=left;
        this.right=right;
    }

    public Expression getLeft(){
        return this.left;
    }

    public Expression getRight(){
        return this.right;
    }

    public abstract Type checkType(Diagnostic d, Scope s);

    @Override
    public String toString() {
        return "Binary_" + operator.toString() + "["+  left.toString() + "," + right.toString() + "]";
    }
    
}
