package drawables;

import java.awt.*;
import java.util.Random;

public class Points extends Size implements Drawable {

    public Points(Size size) {
        this.width = size.width;
        this.height = size.height;
    }

    public void draw(Graphics2D g2d) {
        g2d.setPaint(Color.BLACK);
        Random r = new Random();
        for (int i = 0; i < 2000; i++) {
            int x = Math.abs(r.nextInt()) % this.width;
            int y = Math.abs(r.nextInt()) % this.height;
            g2d.drawLine(x, y, x, y);
        }
    }
}