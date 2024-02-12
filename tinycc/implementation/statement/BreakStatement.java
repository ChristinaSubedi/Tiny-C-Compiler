package tinycc.implementation.statement;

import tinycc.diagnostic.Diagnostic;
import tinycc.diagnostic.Locatable;
import tinycc.implementation.Scope;

public class BreakStatement extends Statement {
    Locatable loc;

    public BreakStatement(Locatable loc){
        this.loc=loc;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toString'");
    }

    @Override
    public void checkType(Diagnostic d, Scope s) {
        if (s.isInALoop()){
            return;
        }
        d.printError(loc, "breaking not inside loop");
    }
    
}
