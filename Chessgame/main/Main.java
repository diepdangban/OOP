import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("My Chess Game");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        Panel gamepanel = new Panel();
        window.add(gamepanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamepanel.launchGame();

    }
}