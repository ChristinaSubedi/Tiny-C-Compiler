package tinycc.implementation;

import tinycc.diagnostic.Diagnostic;

public abstract class FunctionAndExternal {
    public abstract void checkType(Diagnostic d, Scope s);
    
}
