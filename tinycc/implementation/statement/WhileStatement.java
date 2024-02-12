package tinycc.implementation.statement;

import tinycc.diagnostic.Diagnostic;
import tinycc.diagnostic.Locatable;
import tinycc.implementation.Scope;
import tinycc.implementation.expression.Expression;

public class WhileStatement extends Statement {
    Locatable loc;
    Expression condition;
    Statement body;

    public WhileStatement (Locatable loc, Expression condition, Statement body){
        this.loc=loc;
        this.condition=condition;
        this.body=body;
        
    }

    @Override
    public String toString() {
        return "While[" + condition.toString()+ "," + body.toString() +"]";
    }

    @Override
    public void checkType(Diagnostic d, Scope s) {
        s.setInALoop();
        if (!condition.checkType(d, s).isScalar()){
            d.printError(loc, "if not scalar", condition);
            body.checkType(d, s);
        }
    }
    
}
