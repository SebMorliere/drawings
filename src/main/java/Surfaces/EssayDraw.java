package Surfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static conf.Default.DELAY;

public class EssayDraw extends JPanel implements ActionListener {
    private Timer timer;

    public EssayDraw() {
        this.timer = new Timer(DELAY, this);
        this.timer.start();
        this.setBackground(Color.BLACK);
    }

    public Timer getTimer() {
        return timer;
    }

    private void draw(Graphics g) {
        this.draw((Graphics2D) g);
    }

    private void draw(Graphics2D g) {
        g.setPaint(Color.GREEN);
        g.drawLine(0, 0, this.getWidth(), this.getHeight());
        g.setPaint(Color.BLUE);
        g.drawLine(this.getWidth(), 0, 0, this.getHeight());

    }
    public void redraw() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.draw(graphics);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
