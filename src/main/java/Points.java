import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Points extends JFrame implements Drawable {
    public void draw(Graphics graphics) {
        pack();
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setPaint(Color.GREEN);
        int w = getWidth();
        int h = getHeight();
        Random r = new Random();
        for (int i = 0; i < 2000; i++) {
            int x = Math.abs(r.nextInt()) % w;
            int y = Math.abs(r.nextInt()) % h;
            g2d.drawLine(x, y, x, y);
        }
    }
}