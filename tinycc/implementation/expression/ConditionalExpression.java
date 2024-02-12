package tinycc.implementation.expression;

import tinycc.diagnostic.Diagnostic;
import tinycc.implementation.Scope;
import tinycc.implementation.type.Type;
import tinycc.parser.Token;

public class ConditionalExpression extends Expression {

    Token token; 
    Expression condition;
    Expression consequence;
    Expression alternative;

    public ConditionalExpression(Token token, Expression condition, Expression consequence, Expression alternative){
        this.token=token;
        this.condition=condition;
        this.alternative=alternative;
    }

    @Override
    public String toString() {
        String s= "Conditional[" + condition.toString() + " , " + consequence.toString() + ","  + alternative.toString()  + "]";
        return s;
    }

    @Override
    public Type checkType(Diagnostic d, Scope s) {
        if (!condition.checkType(d, s).isScalar()){
            d.printError(token, "if not scalar", condition);
        }
        consequence.checkType(d, s);
        alternative.checkType(d, s);
        return null;

    }
    
}
