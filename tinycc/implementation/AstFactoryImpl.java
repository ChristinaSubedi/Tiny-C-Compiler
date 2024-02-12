package tinycc.implementation;

import java.util.ArrayList;
import java.util.List;

import tinycc.diagnostic.Diagnostic;
import tinycc.diagnostic.Locatable;
import tinycc.implementation.expression.CallExpression;
import tinycc.implementation.expression.ConditionalExpression;
import tinycc.implementation.expression.Expression;
import tinycc.implementation.expression.BinaryExp.AdditionExpression;
import tinycc.implementation.expression.BinaryExp.AndOrExpression;
import tinycc.implementation.expression.BinaryExp.AssignExpression;
import tinycc.implementation.expression.BinaryExp.IsEqualExpression;
import tinycc.implementation.expression.BinaryExp.MultAndDivExpression;
import tinycc.implementation.expression.BinaryExp.OtherComparisonExpression;
import tinycc.implementation.expression.BinaryExp.SubtractionExpression;
import tinycc.implementation.expression.PrimaryExp.CharacterExpression;
import tinycc.implementation.expression.PrimaryExp.IdentifierExpression;
import tinycc.implementation.expression.PrimaryExp.IntegerExpression;
import tinycc.implementation.expression.PrimaryExp.StringExpression;
import tinycc.implementation.expression.UnaryExp.UnaryAddress;
import tinycc.implementation.expression.UnaryExp.UnaryPointer;
import tinycc.implementation.expression.UnaryExp.UnarySizeOf;
import tinycc.implementation.statement.BlockStatement;
import tinycc.implementation.statement.BreakStatement;
import tinycc.implementation.statement.ContinueStatement;
import tinycc.implementation.statement.DeclarationStatement;
import tinycc.implementation.statement.ExpressionStatement;
import tinycc.implementation.statement.IfStatement;
import tinycc.implementation.statement.ReturnStatement;
import tinycc.implementation.statement.Statement;
import tinycc.implementation.statement.WhileStatement;
import tinycc.implementation.type.CharacterType;
import tinycc.implementation.type.FunctionType;
import tinycc.implementation.type.IntegerType;
import tinycc.implementation.type.PointerType;
import tinycc.implementation.type.Type;
import tinycc.implementation.type.VoidType;
import tinycc.mipsasmgen.MipsAsmGen;
import tinycc.parser.ASTFactory;
import tinycc.parser.Token;
import tinycc.parser.TokenKind;

public class AstFactoryImpl implements ASTFactory {
    List<FunctionAndExternal> funcAndExternalDec=new ArrayList<>();
    Scope scope=new Scope();

    @Override
    public Statement createBlockStatement(Locatable loc, List<Statement> statements) {
        return new BlockStatement(loc, statements);
    }

    @Override
    public Statement createDeclarationStatement(Type type, Token name, Expression init) {
        return new DeclarationStatement(type, name, init);
    }

    @Override
    public Statement createExpressionStatement(Locatable loc, Expression expression) {
        return new ExpressionStatement(loc, expression);
    }

    @Override
    public Statement createIfStatement(Locatable loc, Expression condition, Statement consequence,
            Statement alternative) {
        return new IfStatement(loc, condition, consequence, alternative);
    }

    @Override
    public Statement createReturnStatement(Locatable loc, Expression expression) {
        return new ReturnStatement(loc, expression);
    }

    @Override
    public Statement createWhileStatement(Locatable loc, Expression condition, Statement body) {
        return new WhileStatement(loc, condition, body);
    }

    @Override
    public Type createFunctionType(Type returnType, List<Type> parameters) {
        return new FunctionType(returnType, parameters);
    }

    @Override
    public Type createPointerType(Type pointsTo) {
        return new PointerType(pointsTo);
    }

    @Override
    public Type createBaseType(TokenKind kind) {
        switch (kind){
            case CHAR: return new CharacterType();
            case INT: return new IntegerType();
            case VOID: return new VoidType();
            default: return null;

        }


    }

    @Override
    public Expression createBinaryExpression(Token operator, Expression left, Expression right) {
        switch (operator.getKind()){
            case PLUS: return new AdditionExpression(operator, left, right);
            case MINUS: return new SubtractionExpression(operator, left, right);
            case ASTERISK: return new MultAndDivExpression(operator, left, right);
            case SLASH: return new MultAndDivExpression(operator, left, right);
            case EQUAL_EQUAL:
            case BANG_EQUAL: 
                return new IsEqualExpression(operator, left, right);
            case EQUAL: return new AssignExpression(operator, left, right);
            case LESS:
            case GREATER:
            case GREATER_EQUAL:
            case LESS_EQUAL:
                return new OtherComparisonExpression(operator, left, right);
            case AND_AND:
            case PIPE_PIPE:
                return new AndOrExpression(operator, left, right);
            default: return null;

        }

    }

    @Override
    public Expression createCallExpression(Token token, Expression callee, List<Expression> arguments) {
        return new CallExpression(token, callee, arguments);

    }

    @Override
    public Expression createConditionalExpression(Token token, Expression condition, Expression consequence,
            Expression alternative) {
        return new ConditionalExpression(token, condition, consequence, alternative);
    }

    @Override
    public Expression createUnaryExpression(Token operator, boolean postfix, Expression operand) {
       switch (operator.getKind()){
           case ASTERISK: return new UnaryPointer(operator, postfix, operand);
           case AND: return new UnaryAddress(operator, postfix, operand);
           case SIZEOF: return new UnarySizeOf(operator, postfix, operand);
           default: return null;
       }
    }

    @Override
    public Expression createPrimaryExpression(Token token) {
        switch(token.getKind()){
            case CHARACTER: return new CharacterExpression(token);
            case IDENTIFIER: return new IdentifierExpression(token);
            case NUMBER: return new IntegerExpression(token);
            case STRING: return new StringExpression(token);
            default: return null;
        }

    }

    @Override
    public void createExternalDeclaration(Type type, Token name) {
        ExternalDeclaration newExternalDeclaration= new ExternalDeclaration(type, name);
        funcAndExternalDec.add(newExternalDeclaration);
    }

    @Override
    public void createFunctionDefinition(Type type, Token name, List<Token> parameterNames, Statement body) {
        FunctionDefinition newFunctionDefinition=new FunctionDefinition(type, name, parameterNames, body);
        funcAndExternalDec.add(newFunctionDefinition);
    }

    public void checkSemantics(Diagnostic d){
        for (int i=0; i<this.funcAndExternalDec.size(); i++){
            funcAndExternalDec.get(i).checkType(d, this.scope);
        }
    }

    public void codeGen(MipsAsmGen gen){

    }

    @Override
    public Statement createBreakStatement(Locatable loc){
        return new BreakStatement(loc);
    }

    @Override
    public Statement createContinueStatement(Locatable loc) {
		return new ContinueStatement(loc);
	}
    
}
