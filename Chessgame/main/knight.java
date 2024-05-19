public class knight extends piece  {
    public knight(int color, int col, int row){
        super(color,col,row);

        if(color == CGPanel.WHITE){
            image = getImage("/piece/Mã trắng");
        }
        else {
            image = getImage("/piece/Mã đen");
        }

    }
    @Override
    public boolean canMove ( int currentcolor, int targetCol, int targetRow ){

        if ( isWithinBoard( targetCol, targetRow ) && isValidSquare( targetCol , targetRow )  ){
            int moveCol = Math.abs( targetCol - preCol);
            int moveRow = Math.abs( targetRow - preRow);
            if( moveRow * moveCol == 2 ) {
                return true;
            }
        }
        return false;
    }
}
