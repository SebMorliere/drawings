import java.awt.*;

public class BasicStrokes extends Component implements Drawable {
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics.create();
        float[] dash1 = {2f, 0f, 2f};
        float[] dash2 = {1f, 1f, 1f};
        float[] dash3 = {4f, 0f, 2f};
        float[] dash4 = {4f, 4f, 1f};

        graphics2D.drawLine(20, 40, 250, 40);
        BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
        BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash2, 2f);
        BasicStroke bs3 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash3, 2f);
        BasicStroke bs4 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash4, 2f);
        graphics2D.setStroke(bs1);
        graphics2D.drawLine(20, 80, 250, 80);
        graphics2D.setStroke(bs2);
        graphics2D.drawLine(20, 120, 250, 120);
        graphics2D.setStroke(bs3);
        graphics2D.drawLine(20, 160, 250, 160);
        graphics2D.setStroke(bs4);
        graphics2D.drawLine(20, 200, 250, 200);
        graphics2D.dispose();
    }
}