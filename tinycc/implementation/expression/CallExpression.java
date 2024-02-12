package tinycc.implementation.expression;

import java.util.List;

import tinycc.diagnostic.Diagnostic;
import tinycc.implementation.Scope;
import tinycc.implementation.type.FunctionType;
import tinycc.implementation.type.PointerType;
import tinycc.implementation.type.Type;
import tinycc.parser.Token;

public class CallExpression extends Expression {

    Token token; 
    Expression callee;
    List<Expression> arguments;

    public CallExpression(Token token, Expression callee, List<Expression> arguments){
        this.token=token;
        this.callee=callee;
        this.arguments=arguments;
    }

    @Override
    public String toString() {
        String s="Call[" + callee.toString();
        s+=arguments.size()>0 ? "," : "";
        int size=arguments.size()-1;
        for (int i=0;i<arguments.size(); i++){
            s+=arguments.get(i).toString();
            s+=(i<size)? ",": "";
        }
        s+="]";
        return s;
    }

    @Override
    public Type checkType(Diagnostic d, Scope s) {
        // Type t=callee.checkType(d, s)
        // if (!s.functionLookup(callee.getText()) && !s.funcDefLookUp(callee.toString())){
        //     d.printError(token, "function trying to call not found", s);
        // } 
        Type t=callee.checkType(d, s);
        if (!t.isFunction()){
            d.printError(token, "not a function", callee);
        }
   
        FunctionType  f= (FunctionType) t;
        if (f.getParameters().size()!=arguments.size()){
            d.printError(token, "number of parameters not the same");
        }
        if (this.arguments.size()>4){
            d.printError(token, "more than four arguments");
        }
        for (int i=0; i<f.getParameters().size(); i++){
            Type r=arguments.get(i).checkType(d, s);
            Type l=f.getParameters().get(i);
            if (l.isPointer() && r.isPointer()){
                PointerType lPointerType=(PointerType) l;
                PointerType rPointerType= (PointerType) r;
                if (rPointerType.getType().isVoid() && !lPointerType.isVoid()){
                    d.printError(token, "trying to assign a void pointer", l,r);
                }
            }
            if (!(l.isScalar() && r.isScalar())){
                d.printError(token, "not scalar",l,r);
            } else if (l.equals(r)){
                continue;
            } else if (l.isInteger() && r.isInteger()){
                continue;
            } else if (l.isPointer() && r.isPointer()){
                PointerType lPointerType=(PointerType) l;
                PointerType rPointerType= (PointerType) r;
                if (lPointerType.getType().isVoid() || rPointerType.getType().isVoid()){
                    continue;
                }
            } else if (l.isPointer() && r.isInteger()){
                if (arguments.get(i).isNullPointer()){
                    continue;
                }
            } else if (l.equals(r)){
                continue;
            }
            d.printError(token, "assign is invalid", l,r);
        }
        return f.getReturnType();
    }
    
}
