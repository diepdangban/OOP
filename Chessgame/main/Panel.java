import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.util.ArrayList;

public class Panel extends JPanel implements Runnable {

    public static final int WIDTH = Board.SQUARE_SIZE*(8+5);
    public static final int HEIGHT = Board.SQUARE_SIZE*(8);
    public static final int WHITE = 1 ;
    public static final int BLACK = 0;
    int currentColor = WHITE;
    final int FPS=60;

    // BOOLEANS
    boolean canMove;
    boolean validSquare;
    
    Thread thread;

    Board board = new Board();

    Mouse mouse = new Mouse();

    //pieces
    public static ArrayList<piece> Pieces = new ArrayList<>();
    public static ArrayList<piece> simPieces = new ArrayList<>();
    piece activeP;

    public Panel(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(new Color(0, 0, 0));

        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        setPieces();
        copyPieces(Pieces,simPieces);
    }

    public void setPieces(){
        // trắng
        Pieces.add( new pawn(WHITE,0,6) );
        Pieces.add( new pawn(WHITE,1,6) );
        Pieces.add( new pawn(WHITE,2,6) );
        Pieces.add( new pawn(WHITE,3,6) );
        Pieces.add( new pawn(WHITE,4,6) );
        Pieces.add( new pawn(WHITE,5,6) );
        Pieces.add( new pawn(WHITE,6,6) );
        Pieces.add( new pawn(WHITE,7,6) );
        Pieces.add( new rook(WHITE,0,7) );
        Pieces.add( new knight(WHITE,1,7) );
        Pieces.add( new bishop(WHITE,2,7) );
        Pieces.add( new queen(WHITE,3,7) );
        Pieces.add( new king(WHITE,4,4) );
        Pieces.add( new bishop(WHITE,5,7) );
        Pieces.add( new knight(WHITE,6,7) );
        Pieces.add( new rook(WHITE,4,7) );

        // đen
        Pieces.add( new pawn(BLACK,0,1) );
        Pieces.add( new pawn(BLACK,1,1) );
        Pieces.add( new pawn(BLACK,2,1) );
        Pieces.add( new pawn(BLACK,3,1) );
        Pieces.add( new pawn(BLACK,4,1) );
        Pieces.add( new pawn(BLACK,5,1) );
        Pieces.add( new pawn(BLACK,6,1) );
        Pieces.add( new pawn(BLACK,7,1) );
        Pieces.add( new rook(BLACK,0,0) );
        Pieces.add( new knight(BLACK,1,0) );
        Pieces.add( new bishop(BLACK,2,0) );
        Pieces.add( new queen(BLACK,3,0) );
        Pieces.add( new king(BLACK,4,0) );
        Pieces.add( new bishop(BLACK,5,0) );
        Pieces.add( new knight(BLACK,6,0) );
        Pieces.add( new rook(BLACK,7,0) );
    }

    private void copyPieces(ArrayList<piece> source, ArrayList<piece> target ){
        target.clear();
        for (int i = 0 ; i <source.size();i++ ){
            target.add( source.get(i) );;
        }
    }

    public void launchGame(){
        thread = new Thread(this);
        thread.start();
        //  lauchgame -> start() -> run();
    }

    @Override
    public void run(){
        // game loop
        double drawInterval=1000000000/FPS;
        double delta =0;
        long lastTime = System.nanoTime(); // nanotime để cập nhật thời gian và gọi lại 2 method mỗi 1/60 giây
        long currentTime;

        while(thread != null){

            currentTime = System.nanoTime();

            delta += ( currentTime - lastTime )/drawInterval;
            lastTime = currentTime;

            if(delta>=1){
                update();
                repaint();
                delta--;
            }
        }
    }

    private void update(){
        if(mouse.pressed){
            if( this.activeP == null ){ // nếu chưa đc chọn, ktra xem có chọn đc quân cờ này không
                for (piece p : simPieces){
                    if( p.color == currentColor && p.col==mouse.x/Board.SQUARE_SIZE && p.row==mouse.y/ Board.SQUARE_SIZE  ){
                        activeP = p;simulate();break;
                    }
                }
            }
            else {
                simulate();
            }
        }
        if(mouse.pressed == false){
            if(activeP != null){
                if(validSquare){
                    copyPieces(simPieces,Pieces);
                    activeP.updatePosition();
                    activeP = null;
                }
                else{
                    copyPieces(Pieces,simPieces);
                    activeP.resetPosition();
                    activeP = null;
                }
            }
        }
    }

    private void simulate() {
        canMove=false;
        validSquare=false;
        copyPieces(Pieces,simPieces);
        activeP.x = mouse.x - Board.HALF_SQUARE_SIZE;
        activeP.y = mouse.y - Board.HALF_SQUARE_SIZE;
        activeP.col = activeP.getCol(activeP.x);
        activeP.row = activeP.getRow(activeP.y);
        if(activeP.canMove( activeP.color, activeP.col, activeP.row )){
            canMove = true ;
            validSquare = true ;
        }
        else {
            System.out.print("can not move");;
        }
    }



    public void paintComponent (Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        
        board.draw(g2d);

        for (piece p : simPieces){
            p.draw(g2d);
        }

        if(activeP != null){
            if(canMove) {
                g2d.setColor(new Color(255, 255, 255));
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                g2d.fillRect(activeP.col * Board.SQUARE_SIZE, activeP.row * Board.SQUARE_SIZE, Board.SQUARE_SIZE, Board.SQUARE_SIZE);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }
            activeP.draw(g2d);
        }

    }


}
