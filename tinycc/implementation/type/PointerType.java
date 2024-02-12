package tinycc.implementation.type;

public class PointerType extends ScalarType {
    Type pointsTo;

    public PointerType(Type pointsTo){
        this.pointsTo=pointsTo;
    }

    @Override
    public boolean isPointer(){
        return true;
    }

    @Override 
    public boolean isComplete(){
        if (this.pointsTo.isVoid()){
            return true;
        }
        return this.pointsTo.isComplete();
    }

    public boolean isVoidPointer(){
        return (this.pointsTo.isVoid());
    }

    @Override
    public boolean equals(Object object){
        if (object instanceof PointerType){
            PointerType p= (PointerType) object;
            return p.getType().equals(this.getType());
        }
        return false;
    }


  
    public Type getType(){
        return this.pointsTo;
    }


    @Override
    public String toString() {
        return "Pointer[" + pointsTo.toString() + "]";
    }
    
}
