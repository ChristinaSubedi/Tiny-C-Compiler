package tinycc.implementation.expression.PrimaryExp;

import tinycc.diagnostic.Diagnostic;
import tinycc.implementation.Scope;
import tinycc.implementation.expression.PrimaryExpression;
import tinycc.implementation.expression.PrimaryExpression.PrimaryExpressionType;
import tinycc.implementation.type.Type;
import tinycc.parser.Token;

public class IdentifierExpression extends PrimaryExpression {

    Token token;

    public IdentifierExpression(Token token){
        this.token=token;
    }

    public Token getToken(){
        return this.token;
    }

    @Override
    public PrimaryExpressionType getPrimaryExpressionType() {
        return PrimaryExpressionType.IDENTIFIER;
    }

    @Override
    public String toString() {
        return "Var_" + token.toString();
    }

    @Override
    public boolean isLvalue(){
		return true;
	}

    @Override
    public boolean isIdentifier(){
        return true;
    }



    @Override
    public Type checkType(Diagnostic d, Scope s) {
        if (s.lookup(token.toString())==null){
            d.printError(token,"identifier not in scope");
        }
        return s.lookup(token.toString());

      
    }
    
}
