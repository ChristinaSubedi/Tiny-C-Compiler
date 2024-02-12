package tinycc.implementation.expression.PrimaryExp;

import tinycc.diagnostic.Diagnostic;
import tinycc.implementation.Scope;
import tinycc.implementation.expression.PrimaryExpression;
import tinycc.implementation.expression.PrimaryExpression.PrimaryExpressionType;
import tinycc.implementation.type.CharacterType;
import tinycc.implementation.type.IntegerType;
import tinycc.implementation.type.PointerType;
import tinycc.implementation.type.Type;
import tinycc.parser.Token;

public class StringExpression extends PrimaryExpression {
    Token token;

    public StringExpression(Token token){
        this.token=token;
    }

    @Override
    public PrimaryExpressionType getPrimaryExpressionType() {
        return PrimaryExpressionType.STRING;
    }

    @Override
    public String toString() {
        return "Const_" + token.toString() +"";
    }

    @Override
    public Type checkType(Diagnostic d, Scope s) {
        return new PointerType(new CharacterType());
    }
    
}
