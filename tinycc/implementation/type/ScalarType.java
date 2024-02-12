package tinycc.implementation.type;

public abstract class ScalarType extends ObjectType {

    @Override
    public boolean isScalar(){
        return true;
    }

    @Override
    public boolean isComplete(){
        return true;
    }
    
}
