package tinycc.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tinycc.implementation.type.Type;
import tinycc.implementation.type.VoidType;

public class Scope {
    private final Map<String, Type> table;
    private final Map<String, Type> funcDefTable;
    private final List<String> functionTable;
    private final Scope parent;
    private Type returnType=null;
    private boolean inALoop=false;

    public void setInALoop(){
      this.inALoop=true;
    }
  
    public Scope() {
      this(null);
    }
  
    private Scope(Scope parent) {
      this.parent = parent;
      this.table  = new HashMap<String, Type>();
      this.funcDefTable  = new HashMap<String, Type>();
      this.functionTable=new ArrayList<>();
    }

    public void addNewFuncDef(String funcName, Type type){
      funcDefTable.put(funcName, type);
    }

    public boolean funcDefLookUp(String funcDef){
      if (funcDefTable.containsKey(funcDef)){
        return true;
      } else if (this.parent!=null){
        return parent.funcDefLookUp(funcDef);
      }
      return false;
    }

    public boolean isInALoop(){
      if (this.inALoop){
        return true;
      }
      if (this.parent!=null){
        return parent.isInALoop();
      }
      return false;
    }

    public boolean isFuncDefCorrect(String funcName, Type type){
      if (!funcDefTable.containsKey(funcName)){
        funcDefTable.put(funcName, type);
        return true;
      } else {
        return (funcDefTable.get(funcName).equals(type));
      }
  
    }
  
    public Scope newNestedScope() {
      return new Scope(this);
    }

    public void setReturnType(Type rType){
      this.returnType=rType;
    }

    public Type getReturnType(){
      if (this.returnType!=null){
        return this.returnType;
      } else if (parent !=null){
        return parent.getReturnType();
      }
      return new VoidType();
    }

    public boolean isNestedScope(){
      return (this.parent!=null);
    }

    public boolean lookupNestedScope(String id){
      return (table.containsKey(id));
    }
  
    public void add(String id, Type d){
    //   throws IdAlreadyDeclared {
    //   if (table.contains(id))
    //     throw new IdAlreadyDeclared(id);
      table.put(id, d);
    }

    public void addFunction(String funcName){
      functionTable.add(funcName);
    }

    public boolean functionLookup(String functionName){
      if (functionTable.contains(functionName)){
        return true;
      } else if (parent!=null){
        return parent.functionLookup(functionName);
      }
      return false;
    }



    //Done with ChatGpt, gave it the skeleton and asked it to complete it
  
    public Type lookup(String id)  {
        if (table.containsKey(id)) {
          Type rType=table.get(id);
            return rType;
        } else if (parent != null) {
            return parent.lookup(id);
        } else {
            return null; // Identifier not found in any scope
        }
    }
  }
