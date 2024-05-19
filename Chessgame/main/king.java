public class king extends piece {
    public king(int color, int col, int row) {
        super(color, col, row);

        if (color == Panel.WHITE) {
            image = getImage("/piece/Vua trắng");
        } else {
            image = getImage("/piece/Vua đen");
        }
    }
    @Override
    public boolean canMove (  int targetCol, int targetRow ){

//        if ( isWithinBoard( targetCol, targetRow ) && isValidSquare( targetCol , targetRow ) ){
        int moveCol = Math.abs( targetCol - preCol);
        int moveRow = Math.abs( targetRow - preRow);
        if( moveCol + moveRow == 1 || moveRow * moveCol == 1) {
            return true;
        }
//        }
        return true;
    }
}