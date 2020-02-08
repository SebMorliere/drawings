package Surfaces;

import conf.Default;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EssayFrame extends JFrame {
    private EssayDraw essayDraw;

    public EssayFrame() throws HeadlessException {
        System.out.println("EssayFrame under construction");
        this.setTitle("ima biutiful windo - Essay");
        this.setSize(Default.WIDTH, Default.HEIGHT);
        this.setLocationRelativeTo(null);

        essayDraw = new EssayDraw();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);

        this.add(essayDraw);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                essayDraw.getTimer().stop();
            }
        });
        this.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                System.out.println("keyPressed");
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                System.out.println("keyTyped");
//            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    EssayFrame.this.dispatchEvent(new WindowEvent(EssayFrame.this, WindowEvent.WINDOW_CLOSING));
                } else {
                    System.out.println("*** keyReleased was: " + e.paramString());
                }
            }
        });
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        System.out.println("EssayFrame constructed");
    }
}
