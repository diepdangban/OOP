public class Main {
    public static void main(String[] args) {
        // 1^2 + 2^2
        Expression one = new Numeral(1);
        Expression two = new Numeral(2);
        Square one_square = new Square(one);
        Square two_square = new Square(two);
        BinaryExpression e1=new Addition(one_square,two_square);
        System.out.print(e1.left());
        System.out.print(e1+"="+ ((Addition) e1).evaluate());
    }
}