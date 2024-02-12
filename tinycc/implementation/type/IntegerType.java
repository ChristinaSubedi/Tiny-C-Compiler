package tinycc.implementation.type;

public class IntegerType extends ScalarType {


    @Override
    public boolean isInteger(){
        return true;
    }

    @Override
    public String toString() {
        return "Type_int";
    }
    
}
