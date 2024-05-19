public class NicklenDime extends BankAccount {
    public int wdcount=0;

    public void withDraw ( int amount ){
        super.withDraw(amount);
        wdcount++;
    }

    public int charged(){
        return 2*wdcount;
    }

    public void endMonth(){
        super.endMonth();
        wdcount =0;
    }

}
