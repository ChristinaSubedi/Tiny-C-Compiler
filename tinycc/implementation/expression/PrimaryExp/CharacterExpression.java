package tinycc.implementation.expression.PrimaryExp;

import tinycc.diagnostic.Diagnostic;
import tinycc.implementation.Scope;
import tinycc.implementation.expression.PrimaryExpression;
import tinycc.implementation.expression.PrimaryExpression.PrimaryExpressionType;
import tinycc.implementation.type.IntegerType;
import tinycc.implementation.type.Type;
import tinycc.mipsasmgen.MipsAsmGen;
import tinycc.parser.Token;

public class CharacterExpression extends PrimaryExpression {
    Token token;

    public CharacterExpression(Token token){
        this.token=token;
    }

    @Override
    public PrimaryExpressionType getPrimaryExpressionType() {
        return PrimaryExpressionType.NUMBER;
    }

    @Override
    public String toString() {
        return "Const_" + token.toString();
    }

    public  boolean isNullPointer(){
        return token.toString().equals("0");
      }

    @Override
    public Type checkType(Diagnostic d, Scope s) {
        return new IntegerType();
    }


    
}
