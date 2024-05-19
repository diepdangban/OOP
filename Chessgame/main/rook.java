public class rook extends piece {
    public rook(int color, int col, int row) {
        super(color, col, row);
        if (color == Panel.WHITE) {
            image = getImage("/piece/Xe trắng");
        } else {
            image = getImage("/piece/Xe đen");
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