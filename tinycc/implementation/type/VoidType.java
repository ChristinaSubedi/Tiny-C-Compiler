package tinycc.implementation.type;

public class VoidType extends ObjectType {

    @Override
    public boolean isVoid(){
        return true;
    }

    @Override
    public String toString() {
        return "Type_void";
    }
    
}
