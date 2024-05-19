import java.awt.*;

public class play {
    public static void drawmenu(Graphics2D graphics2d){

        // set phông chữ hàng cột
        Font font = new Font("Serif", Font.PLAIN, 15);
        graphics2d.setFont(font);

        // hiện thị hàng, cột
        graphics2d.setColor(new Color(50, 105, 184));
        int COL=20;
        for ( int rol = 0; rol < 8 ; rol++ ){
            String str = String.valueOf(8-rol);
            graphics2d.drawString( str , 5, COL);
            COL+=Board.SQUARE_SIZE;
        }
        int ROW=Board.SQUARE_SIZE-13;
        String ABC = " abcdefgh " ;
        for ( int col = 1; col <= 8 ; col++ ){
            graphics2d.drawString(String.valueOf(ABC.charAt(col)), ROW, CGPanel.HEIGHT-10);
            ROW+=Board.SQUARE_SIZE;
        }

        // vẽ bảng checkmate
        graphics2d.setColor(new Color(255, 255, 255));
        Font font1 = new Font("Impact", Font.PLAIN, 25);
        graphics2d.setFont(font1);
        graphics2d.drawString(" Checkmate ", CGPanel.HEIGHT + Board.HALF_SQUARE_SIZE * 3, Board.SQUARE_SIZE / 3 * 2);

        // vẽ ô lượt chơi
        graphics2d.setColor(new Color(8, 54, 67) );
        graphics2d.fillRect( CGPanel.HEIGHT+Board.HALF_SQUARE_SIZE , Board.SQUARE_SIZE, Board.SQUARE_SIZE * 4, Board.SQUARE_SIZE  );
        graphics2d.setColor(new Color(154, 202, 216) );
        graphics2d.drawRect( CGPanel.HEIGHT+Board.HALF_SQUARE_SIZE , Board.SQUARE_SIZE, Board.SQUARE_SIZE * 4, Board.SQUARE_SIZE  );

        // Hiển thị lượt chơi

        Font font2 = new Font("Serif", Font.PLAIN, 25); // chỉnh lại cỡ chữ
        graphics2d.setFont(font2);

        if( CGPanel.currentcolor == CGPanel.WHITE ){
            graphics2d.drawString("White's Turn",  CGPanel.HEIGHT + Board.HALF_SQUARE_SIZE * 3   ,  Board.SQUARE_SIZE + 40 );
        }
        else{
            graphics2d.drawString("Black's Turn",  CGPanel.HEIGHT + Board.HALF_SQUARE_SIZE * 3   ,  Board.SQUARE_SIZE + 40 );
        }

        //kẻ khung lịch sử lượt chơi
        graphics2d.setColor(new Color(41, 65, 73) );
        graphics2d.fillRect(CGPanel.HEIGHT + Board.HALF_SQUARE_SIZE,5 * Board.HALF_SQUARE_SIZE,4 * Board.SQUARE_SIZE, 5 * Board.SQUARE_SIZE );
        graphics2d.setColor(new Color(170, 209, 223) );
        graphics2d.drawRect(CGPanel.HEIGHT + Board.HALF_SQUARE_SIZE,5 * Board.HALF_SQUARE_SIZE,4 * Board.SQUARE_SIZE, 5 * Board.SQUARE_SIZE );
    }

}
