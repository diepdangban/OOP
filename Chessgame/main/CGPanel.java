import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.Component ;

public class CGPanel extends JPanel implements Runnable {
    public static final int HEIGHT = Board.SQUARE_SIZE*8;
    public static final int WIDTH = Board.SQUARE_SIZE*(8+5) ;
    final int FPS = 60;
    Thread thread;

    Board board = new Board(); // bàn cờ
    Mouse mouse = new Mouse(); // chuột
    // COLOR
    public static final int WHITE=0;
    public static final int BLACK=1;
    public static int currentcolor = WHITE;

    // BOOLEANS
    boolean canMove;
    boolean validSquare;

    //bắt tốt qua đường

    public static boolean enpassant = false;
    public static int enpassantCol=8;
    public static int enpassantRow=8;
    public static boolean canep=false;

    //quân cờ
    public static ArrayList<piece> Pieces = new ArrayList<>();
    public static ArrayList<piece> simPieces = new ArrayList<>();
    static ArrayList<piece> promotePieces = new ArrayList<>();
    piece activeP;
    static piece rookC;
    static piece enpassantPiece=null;
    public static boolean promotion=false;

    public static String lastcol;
    public static String lastrow;
    public static String newcol;
    public static String newrow;

    public CGPanel(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(new Color(19, 19, 19, 255));

        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        setPieces();
        copyPieces(Pieces,simPieces);
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
        Pieces.add( new king(WHITE,4,7) );
        Pieces.add( new bishop(WHITE,5,7) );
        Pieces.add( new knight(WHITE,6,7) );
        Pieces.add( new rook(WHITE,7,7) );

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

    // phương thức update để cập nhật bước di chuyển của quân cờ
    public void update() {
        if (promotion) {
            promoting();

        }
        else {
            if (mouse.pressed) {
                if (this.activeP == null) { // nếu chưa đc chọn, ktra xem có chọn đc quân cờ này không
                    for (piece Piece : simPieces) {
                        if (Piece.color == currentcolor && Piece.col == mouse.x / Board.SQUARE_SIZE && Piece.row == mouse.y / Board.SQUARE_SIZE) {
                            activeP = Piece;
                        }
                    }
                } else {//nếu đang chọn quân cờ , simulate the move;
                    simulate();
                }
            }
            if (mouse.pressed == false) {
                if (activeP != null) {
                    if (validSquare) {
                        copyPieces(simPieces, Pieces);
                        activeP.updatePosition();
                        activeP.moved = true;

                        Castling();
                        enPassant();

                        if (activeP.promote == true) {
                            promotePieces.clear();
                            promotePieces.add(new rook(currentcolor, 9, 2));
                            promotePieces.add(new knight(currentcolor, 9, 3));
                            promotePieces.add(new bishop(currentcolor, 9, 4));
                            promotePieces.add(new queen(currentcolor, 9, 5));
                            promotion = true;
                        }

                        activeP = null;
                        // đổi lượt
                        if (currentcolor == WHITE) {
                            currentcolor = BLACK;
                            System.out.println("BLACK's turn");
                        } else {
                            currentcolor = WHITE;
                            System.out.println("WHITE's turn");
                        }
                    } else {
                        copyPieces(Pieces, simPieces);
                        activeP.resetPosition();
                        activeP = null;
                    }
                }
            }
        }
    }

    private void enPassant( ) {
        if(enpassantPiece!=null&&canep==true) {
            simPieces.remove(enpassantPiece.getIndex());
            copyPieces(simPieces, Pieces);
        }
    }

    private void promoting() {
    }

    private void simulate(){
        canMove=false;
        validSquare=false;

        // cập nhật ds quân cờ
        copyPieces(Pieces,simPieces);

        // nếu quân cờ đang được chọn , mô phỏng nước đi
        activeP.x = mouse.x - Board.HALF_SQUARE_SIZE;
        activeP.y = mouse.y - Board.HALF_SQUARE_SIZE;
        activeP.col = activeP.getCol(activeP.x);
        activeP.row = activeP.getRow(activeP.y);
//         ktra nếu quân cờ lơ lửng trên một ô
        if(activeP.canMove( activeP.color, activeP.col, activeP.row )){
            canMove = true ;
            validSquare = true ;
            if( activeP.capturedP != null ){
                simPieces.remove(activeP.capturedP.getIndex());
            }
        }
    }

    // nhập thành
    public boolean Castling (){
        if(CGPanel.rookC!=null){
            if(CGPanel.rookC.col==0){
                CGPanel.rookC.col=3;
                CGPanel.rookC.x=CGPanel.rookC.getX(CGPanel.rookC.col);
            }
            if(CGPanel.rookC.col==7){
                CGPanel.rookC.col=5;
                CGPanel.rookC.x=CGPanel.rookC.getX(CGPanel.rookC.col);
            }
            CGPanel.rookC.updatePosition();
            return true;
        }
        return false;
    }

    // paint component để thêm các đối tượng ( quân cờ , ...) vào màn hình
    public void paintComponent( Graphics graphic ){
        super.paintComponent(graphic);

        Graphics2D graphics2d=(Graphics2D)graphic;

        //board
        board.draw(graphics2d);
        play.drawmenu(graphics2d);
        //if(CGPanel.promotion ){
            Font font2 = new Font("Serif", Font.PLAIN, 25); // chỉnh lại cỡ chữ
            graphics2d.setFont(font2);
            graphics2d.drawString("Promote to :",(9)*Board.SQUARE_SIZE,Board.SQUARE_SIZE*4);
            for (piece p : CGPanel.promotePieces) {
            graphics2d.drawImage(p.image,p.getX(p.col),p.getY(p.row),Board.SQUARE_SIZE,Board.SQUARE_SIZE,null);
        }
        //}
        //pieces
        for (piece p : simPieces) { p.draw(graphics2d);}
        if (activeP != null) {
            if(canMove){
                graphics2d.setColor(Color.white);
                graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                graphics2d.fillRect(activeP.col * Board.SQUARE_SIZE, activeP.row * Board.SQUARE_SIZE, Board.SQUARE_SIZE, Board.SQUARE_SIZE);
                graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }
            activeP.draw(graphics2d);

        }
        else{
            if(Castling()){
                CGPanel.rookC.draw(graphics2d);
                CGPanel.rookC=null;
            }
        }


        // phong tốt

    }
}