public class queen extends piece  {
    public queen(int color, int col, int row){
        super(color,col,row);

        if(color == CGPanel.WHITE){
            image = getImage("/piece/Hậu trắng");
        }
        else {
            image = getImage("/piece/Hậu đen");
        }

    }

    @Override
    public boolean canMove ( int currentcolor, int targetCol, int targetRow ){

        if ( isWithinBoard( targetCol, targetRow  ) &&  isValidSquare( targetCol , targetRow )  ){
            int moveCol = Math.abs( targetCol - preCol);
            int moveRow = Math.abs( targetRow - preRow);
            if( moveCol == moveRow || moveCol == 0 || moveRow == 0 ) {
                return true;
            }
        }
        return false;
    }
}
