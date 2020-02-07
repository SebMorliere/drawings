package drawables;

import java.awt.*;
import java.util.Random;

public class Points implements Drawable, Resizable {
    private int w;
    private int h;

    public Points(int width, int height) {
        this.w = width;
        this.h = height;
    }

    public void resize(int width, int height) {
        this.w = width;
        this.h = height;
    }

    public void draw(Graphics2D g2d) {
        g2d.setPaint(Color.BLACK);
        Random r = new Random();
        for (int i = 0; i < 2000; i++) {
            int x = Math.abs(r.nextInt()) % this.w;
            int y = Math.abs(r.nextInt()) % this.h;
            g2d.drawLine(x, y, x, y);
        }
    }
}