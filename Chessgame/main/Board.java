import java.awt.*;

public class Board {

    final int MAX_COL = 8;
    final int MAX_ROW = 8;
    public static final int SQUARE_SIZE = 70;
    public static final int HALF_SQUARE_SIZE = SQUARE_SIZE/2;

    public void draw(Graphics2D graphics2d ){
        int color=0;

        for(  int row = 0; row < MAX_ROW; row++ ){
            for (int col =0 ; col < MAX_COL; col++ ) {
                if( color == 0 ){
                    graphics2d.setColor(new Color(207, 234, 234));
                    color=1;
                }
                else {
                    graphics2d.setColor(new Color(116, 182, 216));
                    color=0;
                }
                graphics2d.fillRect(col*SQUARE_SIZE,row*SQUARE_SIZE,SQUARE_SIZE,SQUARE_SIZE);
            }
            if(color==0){color=1;}
            else {color=0;}
        }

    }

}
