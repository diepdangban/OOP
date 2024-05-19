public class pawn extends piece  {

    public pawn(int color, int col, int row){
        super(color,col,row);
        if(color == CGPanel.WHITE){
            image = getImage("/piece/Tốt trắng");
        }
        else {
            image = getImage("/piece/Tốt đen");
        }

    }

    public boolean IsPromote(int targetCol, int targetRow){
        if(this.color==CGPanel.WHITE){
            if( targetRow==0 ){
                return true;
            }
        }
        else {
            if( targetRow==7 ){
                return true;
            }
        }
        return false;
    }

    public boolean canMove (int currentcolor, int targetCol, int targetRow ){
        if ( isWithinBoard( targetCol, targetRow ) ){//&& isValidSquare( targetCol , targetRow )  ){
            int moveCol = targetCol - preCol; // di chuyển ngang
            int moveRow = targetRow - preRow; // di chuyển dọc
            capturedP = getCapturedP(targetCol,targetRow);

            if( color == CGPanel.WHITE){ // cờ trắng đi lùi trên bảng nên phải đảo moveRow
                moveRow*=(-1);
            }

            if( moveCol == 0 && moveRow == 1 && capturedP==null ) { // bước lên 1 bước
                return true;
            }
            // Bước đâù tiên đi hai bước
            int firstMove = 1;

            if( color == CGPanel.WHITE && preRow != 6 ){ firstMove = 0; }
            if( color == CGPanel.BLACK && preRow != 1 ){ firstMove = 0; }

            if( firstMove == 1 ){
                if( moveCol == 0 && moveRow == 2 && capturedP==null ) { // bước lên hai bước
                    return true;
                }
            }
            // ăn chéo
            if( Math.abs(moveCol) == 1 && moveRow == 1 && capturedP!=null ){
                return true;
            }
        }
        return false;
    }

}
