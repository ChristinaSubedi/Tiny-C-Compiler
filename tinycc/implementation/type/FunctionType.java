package tinycc.implementation.type;

import java.util.List;

public class FunctionType extends BaseType {
    Type returnType;
     List<Type> parameters;

    public FunctionType(Type returnType, List<Type> parameters){
        this.returnType=returnType;
        this.parameters=parameters;
    }

    @Override
    public String toString() {
        String s="FunctionType["+returnType.toString();
        s+=parameters.size()>0 ? "," : "";
        int size=parameters.size()-1;
        for (int i=0;i<parameters.size(); i++){
            s+=parameters.get(i).toString();
            s+=(i<size)? ",": "";
        }
        s+="]";
        return s;
    }

    @Override
    public boolean isFunction(){
        return true;
    }

    public List<Type>  getParameters(){
        return this.parameters;
    }

    public Type getReturnType(){
        return this.returnType;
    }
    
}
