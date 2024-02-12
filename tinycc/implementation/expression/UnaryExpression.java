package tinycc.implementation.expression;


import tinycc.parser.Token;

public abstract class UnaryExpression extends Expression {
    Token operator;
    boolean postfix;
    Expression operand;

    public UnaryExpression(Token operator, boolean postfix, Expression operand){
        this.operator=operator;
        this.postfix=postfix;
        this.operand=operand;
    }

    public Expression getOperand(){
        return this.operand;
    }

    public Token getOperator(){
        return this.operator;
    }

    @Override
    public String toString() {
        return "Unary_"+ operator.toString() + "[" + operand.toString() + "]";
    }
    
}
