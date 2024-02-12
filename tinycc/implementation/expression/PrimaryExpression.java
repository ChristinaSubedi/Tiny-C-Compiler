package tinycc.implementation.expression;


public abstract class PrimaryExpression extends Expression {
  public enum PrimaryExpressionType{
      IDENTIFIER, NUMBER, STRING
  }

  public abstract PrimaryExpressionType getPrimaryExpressionType();






    
}
