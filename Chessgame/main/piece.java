import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public abstract class piece {

    public BufferedImage image;
    public int x,y;
    public int color;
    public int col;
    public int row;
    public static int preCol;
    public static int preRow;

    public piece (int color, int col, int row){
        this.color=color;
        this.col=col;
        this.row=row;
        x=getX(col);
        y=getY(row);
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
        for (int index = 0; index < Panel.simPieces.size(); index++ ){
            if( Panel.simPieces.get(index) == this ){
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

    public boolean canMove( int currentcolor, int targetCol,int targetRow){
        return false;
    }


    public void draw(Graphics2D graphics2d){
        graphics2d.drawImage(image,x,y,Board.SQUARE_SIZE,Board.SQUARE_SIZE,null);
    }


    abstract boolean canMove(int targetCol, int targetRow);
}
