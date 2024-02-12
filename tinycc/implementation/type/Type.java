package tinycc.implementation.type;

/**
 * The main type class (see project description)
 *
 * You can change this class but the given name of the class must not be
 * modified.
 */
public abstract class Type {

	/**
	 * Creates a string representation of this type.
	 *
	 * @remarks See project documentation.
	 * @see StringBuilder
	 */
	@Override
	public abstract String toString();

	public boolean isScalar(){
        return false;
    }

    public boolean isVoid(){
        return false;
    }

    public boolean isInteger(){
        return false;
    }

    public boolean isPointer(){
        return false;
    }


    public boolean isComplete(){
        return false;
    }

    public boolean isObject(){
        return false;
    }

    public boolean isFunction(){
        return false;
    }

    public boolean isNullPointer(){
        return false;
    }

}
