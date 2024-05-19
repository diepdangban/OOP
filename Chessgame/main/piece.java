import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

abstract class piece {
    public BufferedImage image;
    public int x,y;
    public int color;
    public int col;
    public int row;
    public int preCol;
    public int preRow;
    public piece capturedP;
    public piece blockedP;

    public piece(int color, int col, int row){
        this.color=color;
        this.col=col;
        this.row=row;
        x = getX(col);
        y = getY(row);
        preCol = col;
        preRow = row;
    }

    public BufferedImage getImage(String imagePath){
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath +".png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public int getX(int col){
        return col* Board.SQUARE_SIZE;
    }
    public int getY(int row){
        return row* Board.SQUARE_SIZE;
    }
    public int getCol(int x){
        return (x + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }
    public int getRow(int y){
        return (y + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }

    public int getIndex(){
        for (int index = 0; index < CGPanel.simPieces.size(); index++ ){
            if( CGPanel.simPieces.get(index) == this ){
                return index;
            }
        }
        return 0;
    }

    public void updatePosition(){
        this.x=getX(col);
        this.y=getY(row);
        this.preCol=getCol(x);
        this.preRow=getRow(y);
    }

    public void resetPosition(){
        this.col= preCol;
        this.row= preRow;
        this.x=getX(col);
        this.y=getY(row);
    }

    public boolean canMove( int currentcolor, int targetCol,int targetRow){
        return false;
    }

    public boolean isWithinBoard(int targetCol,int targetRow){
        if( targetCol >= 0 && targetCol <= 7 && targetRow >= 0 && targetRow <= 7  ){
            if( targetCol == preCol && targetRow == preRow ){
                return false;
            }
            if( isNotBlockOnStraight(targetCol,targetRow) && isNotBlockOnDiagon(targetCol,targetRow) ){
            return true;
            }
        }
        return false;
    }

    public piece getCapturedP(int targetCol , int targetRow ){
        for ( piece Piece : CGPanel.simPieces ){
            if( Piece.col == targetCol && Piece.row == targetRow && Piece != this ){
                return Piece;
            }
        }
        return null;
    }

    public boolean isValidSquare ( int targetCol, int targetRow ){
        capturedP = getCapturedP(targetCol,targetRow);
        ///ô trống
        if( capturedP == null ){return true;}
        else {///ô không trống kiểm tra xem có ăn được hay không
            if( capturedP.color != this.color ){
                return true;
            }
            else{
                capturedP = null;
                return false;
            }

        }
    }

    public boolean isNotBlockOnStraight(int targetCol , int targetRow) {
        int moveCol = targetCol - preCol; // di chuyển ngang ||  trái <0  || phải >0
        int moveRow = targetRow - preRow; // di chuyển dọc   ||  xuống >0 || lên <0

        if (moveCol == 0) {
            //đi lên
            if (moveRow < 0) {
                for (int r = preRow - 1; r > targetRow; r--) {
                    blockedP = getCapturedP(targetCol, r);
                    if (blockedP != null) {
                        return false;
                    }
                }
            }
            // đi xuống
            if (moveRow > 0) {
                for (int r = preRow + 1; r < targetRow; r++) {
                    blockedP = getCapturedP(targetCol, r);
                    if (blockedP != null) {
                        return false;
                    }
                }
            }
        }
        if (moveRow == 0) {
            //đi sang trái
            if (moveCol < 0) {
                for (int c = preCol - 1; c > targetCol; c--) {
                    blockedP = getCapturedP(c, targetRow);
                    if (blockedP != null) {
                        return false;
                    }
                }
            }
            // đi sang phải
            if (moveCol > 0) {
                for (int c = preCol + 1; c < targetCol; c++) {
                    blockedP = getCapturedP(c, targetRow);
                    if (blockedP != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isNotBlockOnDiagon(int targetCol, int targetRow ){
        int moveCol = targetCol - preCol; // di chuyển ngang ||  trái <0  || phải >0
        int moveRow = targetRow - preRow; // di chuyển dọc   ||  xuống >0 || lên <0
        int r = preRow;
        int c = preCol;

        if( Math.abs(moveRow) == Math.abs(moveCol) ) { // đi xiên
            if (moveRow < 0) { // xiên lên
                 //xiên lên trái
                    for ( c = preCol - 1; c > targetCol; c-- ){
                        r--;
                        blockedP = getCapturedP(c, r);
                        if (blockedP != null) {
                            return false;
                        }

                    }

                // xiên lên phải
                    for ( c = preCol + 1; c < targetCol; c++ ){
                        r--;
                        blockedP = getCapturedP(c, r);
                        if (blockedP != null) {
                            return false;
                        }
                    }

            }
            if (moveRow > 0) { // xiên xuống
                //xiên xuống trái
                    for ( c = preCol - 1; c > targetCol; c-- ){
                        r++;
                        blockedP = getCapturedP(c, r);
                        if (blockedP != null) {
                            return false;
                        }
                    }

               // xiên xuống phải
                    for ( c = preCol + 1; c < targetCol; c++ ){
                        r++;
                        blockedP = getCapturedP(c, r);
                        if (blockedP != null) {
                            return false;
                        }
                    }
            }
        }
        return true;
    }

    public void draw(Graphics2D graphics2d){
        graphics2d.drawImage(image,x,y,Board.SQUARE_SIZE,Board.SQUARE_SIZE,null);
    }

}
