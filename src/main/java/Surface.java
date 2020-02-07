import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Surface extends JPanel implements ActionListener {
    private static final int DELAY = 100;
    private Timer timer;

    public Surface() {
        initTimer(DELAY);
    }

    private void initTimer(int delay) {
        timer = new Timer(delay, this);
        timer.start();
    }

    public Timer getTimer() {
        return timer;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        new Points().draw(graphics);
        new Lines().draw(graphics);
        new BasicStrokes().draw(graphics);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}