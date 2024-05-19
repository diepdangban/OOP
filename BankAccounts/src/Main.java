public class Main {
    public static void main(String[] args) {
        Fee fee= new Fee();
        fee.deposit(50);
        fee.withDraw(20);
        fee.endMonth();
    }
}