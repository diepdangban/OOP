import java.awt.*;

public class king extends piece  {
    public king(int color, int col, int row){
        super(color,col,row);

        if(color == CGPanel.WHITE){
            image = getImage("/piece/Vua trắng");
        }
        else {
            image = getImage("/piece/Vua đen");
        }
    }

    @Override
    public boolean canMove ( int currentcolor, int targetCol, int targetRow ){

        if ( isWithinBoard( targetCol, targetRow ) && isValidSquare( targetCol , targetRow ) ) {
            int moveCol = Math.abs(targetCol - preCol);
            int moveRow = Math.abs(targetRow - preRow);
            if (moveCol + moveRow == 1 || moveRow * moveCol == 1) {
                return true;
            }
        }
        /// nhập thành
        if(this.moved == false){
            // nhập thành bên phải
            if( targetCol == preCol+2 && targetRow == preRow && isNotBlockOnStraight(targetCol+1, targetRow)) {
                for (piece p : CGPanel.simPieces) {
                    if (p.col == preCol + 3 && p.row == preRow && p.moved == false) {
                        CGPanel.rookC = p;

                        return true;
                    }
                }
            }
            // nhập thành bên trái
            if( targetCol == preCol-2 && targetRow == preRow && isNotBlockOnStraight(targetCol-2, targetRow)) {
                for (piece p : CGPanel.simPieces) {
                    if (p.col == preCol - 4 && p.row == preRow && p.moved == false) {
                        CGPanel.rookC = p;
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
