package tinycc.implementation.statement;

import tinycc.diagnostic.Diagnostic;
import tinycc.implementation.Scope;
import tinycc.implementation.expression.Expression;
import tinycc.implementation.type.PointerType;
import tinycc.implementation.type.Type;
import tinycc.parser.Token;

public class DeclarationStatement extends Statement {
    Type type;
    Token name;
    Expression init;

    public DeclarationStatement(Type type, Token name, Expression init){
        this.type=type;
        this.name=name;
        this.init=init;

    }

    @Override
    public String toString() {
        return "Declaration_" + name.toString() + "[" + type.toString() + (init != null ? "," + init.toString() : "") + "]";


    }

    @Override
    public void checkType(Diagnostic d, Scope s) {
        if (this.type.isVoid()){
            d.printError(name, "declaring void", type);
        }
        Type l= this.type;
        if (s.isNestedScope() && s.lookupNestedScope(name.toString())){
            d.printError(name, "redeclaring in nested scope", name);

        }
        s.add(name.toString(),this.type);

        if (init!=null){
            Type r=init.checkType(d, s);
            if (!(l.isScalar() && r.isScalar())){
                d.printError(name, "not scalar",l,r);
            } else if(l.equals(r)) {
                return;
                
            }else if (l.isPointer() && r.isPointer()){
                PointerType lPointerType=(PointerType) l;
                PointerType rPointerType= (PointerType) r;
                if (lPointerType.getType().isVoid() || rPointerType.getType().isVoid()){
                    return;
                }
            } else if (l.isInteger() && r.isInteger()){
                return;
            }  else if (l.isPointer() && r.isInteger()){
                if (init.isNullPointer()){
                    return ;
                }
            } else if (l.equals(r)){
                return;
            }
            d.printError(name, "declaration is invalid", l,r);
        }
    }
    
}
