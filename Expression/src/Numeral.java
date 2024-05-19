public class Numeral implements Expression {
    private int value ;
    public Numeral ( int value ){
        this.value=value;
    }
    public int evaluate(){
        return value;
    }
    public String toString (){
        return ""+value ;
    }
}
