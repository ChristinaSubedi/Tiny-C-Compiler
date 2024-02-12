package tinycc.implementation;



import tinycc.diagnostic.Diagnostic;
import tinycc.implementation.type.FunctionType;
import tinycc.implementation.type.Type;
import tinycc.parser.Token;

public class ExternalDeclaration extends FunctionAndExternal {
    Type type;
    Token name;

    public ExternalDeclaration(Type type, Token name){
        this.type=type;
        this.name=name;
        
    }

    public void checkType(Diagnostic d, Scope s){
        if (type.isFunction()){
            if (!s.isFuncDefCorrect(name.toString(), type)){
                d.printError(name, "function redeclared with different return type",type);
            }
            FunctionType fType=(FunctionType) type;
            for (int i=0; i<fType.getParameters().size(); i++){
                if (fType.getParameters().get(i).isVoid()){
                    d.printError(name, "function defined with void parameters");
                }
            }
            s.add(name.getText(), this.type);
        } else if (s.lookup(name.toString())!=null){
            Type t= s.lookup(name.toString());
            if (!this.type.equals(t)){
                d.printError(name, "variable redeclared in global scope with diff type");
            }
        } else if (this.type.isVoid()){
            d.printError(name, "Variable declared with void type");
        } else {
            s.add(name.toString(), type);
        }
    }
    
}
