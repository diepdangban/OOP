public class Square implements Expression {
    private Expression expression;
    public int evaluate(){
        return expression.evaluate()*expression.evaluate();
    }
    public Square (Expression exp){
        this.expression= exp;
    }
    public String toString (){
        return expression + "^2";
    }
}
