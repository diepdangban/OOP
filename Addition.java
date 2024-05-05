public class Addition implements BinaryExpression {
    public Addition(Expression l, Expression r){
        left = l;
        right = r;
    }
    Expression left,right;
    public int evaluate(){
        return left.evaluate()+right.evaluate();
    }
    public String toString(){
        return (left+"+"+right);
    }

    @Override
    public Expression left() {
        return left;
    }

    @Override
    public Expression right() {
        return null;
    }
}
