package game2048;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Game2048Frame extends JFrame {
    public static int gridWidth = 4;
    public static int gridHeight = 4;
    public static int cellPixellength = 200;

    private Game2048Draw game;

    public Game2048Frame() throws HeadlessException {
        this.setTitle("this is 2048");
        this.setSize(gridWidth * cellPixellength, gridHeight * cellPixellength);
        this.setLocationRelativeTo(null);

        this.setUndecorated(true);

        game = new Game2048Draw(gridWidth, gridHeight);
        KeyAdapter gameKeyAdapter = game.getKeyAdapter();
        this.add(game);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                game.getTimer().stop();
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                gameKeyAdapter.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                gameKeyAdapter.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    Game2048Frame.this.dispatchEvent(new WindowEvent(Game2048Frame.this, WindowEvent.WINDOW_CLOSING));
                } else {
                    gameKeyAdapter.keyReleased(e);
                }
            }
        });
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
