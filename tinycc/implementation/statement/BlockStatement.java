package tinycc.implementation.statement;

import java.util.List;

import tinycc.diagnostic.Diagnostic;
import tinycc.diagnostic.Locatable;
import tinycc.implementation.Scope;
import tinycc.implementation.type.Type;

public class BlockStatement extends Statement {

    Locatable loc;
    List<Statement> statements;
    
    public BlockStatement (Locatable loc, List<Statement> statements){
        this.loc=loc;
        this.statements=statements;
    }

    @Override
    public String toString() {
        String s="Block[";
        int statementLength=this.statements.size()-1;
        for (Statement statement: this.statements){
            s+=statement.toString();
            if (statementLength>0){
                statementLength-=1;
                s+=",";
            }
        }
        s+="]";
        return s;

    }

    @Override
    public void checkType(Diagnostic d, Scope parent) {
        Scope scope = parent.newNestedScope();
        // local variables are added by declaration statements
        // in the statement list that constitutes the block's body
        for (Statement s : statements)
          s.checkType(d, scope);
      }
}
    

