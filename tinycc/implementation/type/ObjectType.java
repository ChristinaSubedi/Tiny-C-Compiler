package tinycc.implementation.type;

public abstract class ObjectType extends BaseType {
    
    @Override
    public boolean isObject(){
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return true;
    }


    
}
