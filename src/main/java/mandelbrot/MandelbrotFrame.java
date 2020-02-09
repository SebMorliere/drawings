package mandelbrot;

import conf.Default;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class MandelbrotFrame extends JFrame {
    public MandelbrotFrame() throws HeadlessException {
        this.setTitle("ima biutiful windo - Essay");
        this.setSize(Default.WIDTH, Default.HEIGHT);
        this.setLocationRelativeTo(null);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);

        this.add(new MandelbrotDraw());
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    MandelbrotFrame.this.dispatchEvent(new WindowEvent(MandelbrotFrame.this, WindowEvent.WINDOW_CLOSING));
                } else {
                    System.out.println("*** keyReleased was: " + e.paramString());
                }
            }
        });
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
