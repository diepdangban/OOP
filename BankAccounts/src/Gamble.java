import static java.lang.Math.random;

public class Gamble extends BankAccount {

    public  void withDraw ( int amount ){
        double rand = random();
        if ( rand >= .5 ){ balance -= (2*amount); }
        numberOfTransactions++;
    }
    public int charged(){
        return 0;
    }

}
