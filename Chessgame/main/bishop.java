public class bishop extends piece  {
    public bishop(int color, int col, int row){
        super(color,col,row);

        if(color == CGPanel.WHITE){
            image = getImage("/piece/Tượng trắng");
        }
        else {
            image = getImage("/piece/Tượng đen");
        }

    }
    @Override
    public boolean canMove ( int currentcolor, int targetCol, int targetRow ){

        if ( isWithinBoard( targetCol, targetRow ) && isValidSquare( targetCol , targetRow ) ){
            int moveCol = Math.abs( targetCol - preCol);
            int moveRow = Math.abs( targetRow - preRow);
            if( moveCol == moveRow ) {
                return true;
            }
        }
        return false;
    }
}
