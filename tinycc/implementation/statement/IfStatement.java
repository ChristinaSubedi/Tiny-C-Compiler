package tinycc.implementation.statement;

import tinycc.diagnostic.Diagnostic;
import tinycc.diagnostic.Locatable;
import tinycc.implementation.Scope;
import tinycc.implementation.expression.Expression;

public class IfStatement extends Statement {
    Locatable loc;
    Expression condition;
    Statement consequence;
    Statement alternative;

    public IfStatement(Locatable loc, Expression condition, Statement consequence,
    Statement alternative){
        this.loc=loc;
        this.condition=condition;
        this.consequence=consequence;
        this.alternative=alternative;
        
    }

    @Override
    public String toString() {
        String s= "If[" + condition.toString() + " , " + consequence.toString() + (alternative != null ? "," + alternative.toString() : "") + "]";
        return s;

    }

    @Override
    public void checkType(Diagnostic d, Scope s) {

        if (!condition.checkType(d, s).isScalar()){
            d.printError(loc, "if not scalar", condition);
        }
        consequence.checkType(d, s);
        if (alternative!=null){
            alternative.checkType(d, s);
        }
    }
    
}
