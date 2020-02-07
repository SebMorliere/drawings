import drawables.Drawable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Surface extends JPanel implements ActionListener {
    private Timer timer;
    private ArrayList<Drawable> drawables;

    public Surface(ArrayList<Drawable> drawables) {
        this.timer = new Timer(Main.DELAY, this);
        this.timer.start();
        this.drawables = drawables;
    }

    public Timer getTimer() {
        return timer;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        drawables.forEach(dr -> dr.draw(g2d));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}