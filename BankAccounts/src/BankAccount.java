abstract public class BankAccount {
    public double balance =0;
    public double numberOfTransactions = 0;
    public double charge = 0;

    public void deposit( int amount ){
        balance += amount;
        numberOfTransactions++;
    }

    public void withDraw ( int amount ){
        balance -=amount;
        numberOfTransactions++;
    }

    abstract int charged();

    public void endMonth (){
        charge =charged();
        System.out.println( "Month summary ");
        System.out.println( "Balance : "+ balance);
        System.out.println( "Charge : "+ charge);
        System.out.println( "Number of Transactions : "+ numberOfTransactions);
        numberOfTransactions = 0;
        balance -= charge;
    }
}
