package tinycc.implementation;

import java.util.List;

import tinycc.diagnostic.Diagnostic;
import tinycc.implementation.statement.Statement;
import tinycc.implementation.type.FunctionType;
import tinycc.implementation.type.Type;
import tinycc.parser.Token;

public class FunctionDefinition extends FunctionAndExternal {
    FunctionType type; 
    Token name; 
    List<Token> parameterNames; 
    Statement body;

    public FunctionDefinition(Type type, Token name, List<Token> parameterNames, Statement body){
        this.type= (FunctionType) type;
        this.name=name;
        this.parameterNames=parameterNames;
        this.body=body;

        
        
    }

    public void checkType(Diagnostic d , Scope s){
        if (s.isNestedScope()){
            d.printError(name, "defining function inside a scope", body);
        }
        if (s.functionLookup(name.toString())){
            d.printError(name, "function already declared");
        }
        s.addFunction(name.toString());
        s.add(this.name.getText(), type);
        s.setReturnType(type.getReturnType());
        Scope functionScope=s.newNestedScope();
        int i=0;
        for (Token parameter: parameterNames){
            if (functionScope.lookupNestedScope(parameter.toString())){
                d.printError(parameter, "parameter already declared");
            } 
            functionScope.add(parameter.getText(), type.getParameters().get(i));
        }
        body.checkType(d, functionScope);

            
    }
    
}
