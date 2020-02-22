package examples.drawings;

import java.awt.*;

public class Lines implements Drawable {
    public void draw(Graphics2D g2d) {
        g2d.drawLine(30, 30, 200, 30);
        g2d.drawLine(200, 30, 30, 200);
        g2d.drawLine(30, 200, 200, 200);
        g2d.drawLine(200, 200, 30, 30);
    }
}