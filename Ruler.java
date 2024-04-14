public class UseAgrument {
    public static void main(String[] args) {
        int n=5;
        String ruler="1";
        char k= '2';
        int i = 1;
        for (i=2;i<n+2;i++){
            System.out.println(ruler);
            ruler=ruler+i+ruler;
            //k=k+1;
        }
    }
}