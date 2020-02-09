package Surfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static conf.Default.DELAY;

public class EssayDraw extends JPanel implements ActionListener {
    private Timer timer;
    private Color lightBlue = new Color(100, 160, 240);
    private Color darkBlue = new Color(40, 100, 180);
    private int step = 60;

    private ArrayList<HorizonPoint> horizonPointList;
    private ArrayList<ArrayList<HorizonPoint>> landscape;

    public EssayDraw() {
        this.timer = new Timer(DELAY, this);
        this.timer.start();
        this.setBackground(Color.BLACK);
    }

    private void init() {
        this.horizonPointList = new ArrayList<>();
        int x = 0;
        int maxW = this.getWidth();
        while (x < maxW + step) {
            if (x > maxW) {
                horizonPointList.add(new HorizonPoint(Math.round(EssayDraw.this.getHeight() / 2f), maxW));
            } else {
                horizonPointList.add(new HorizonPoint(Math.round(EssayDraw.this.getHeight() / 2f), x));
            }
            x += step;
        }
    }

    public Timer getTimer() {
        return timer;
    }

    private void draw(Graphics g) {
        this.draw((Graphics2D) g);
    }

    private void draw(Graphics2D g) {
        if (horizonPointList == null) {
            this.init();
            this.horizonPointList.forEach(HorizonPoint::evolve);
            this.drawLines(g, this.horizonPointList, lightBlue);
        } else {
            this.horizonPointList.forEach(HorizonPoint::evolve);
            this.drawLines(g, this.horizonPointList, darkBlue);
        }
    }

private void drawLines(Graphics2D g, ArrayList<HorizonPoint> horizonPointList, Color color) {
    g.setPaint(color);
    for (int i = 0; i < horizonPointList.size(); i++) {
        HorizonPoint hp1 = horizonPointList.get(i);
        try {
            HorizonPoint hp2 = horizonPointList.get(i + 1);
            g.drawLine(hp1.x, hp1.y, hp2.x, hp2.y);
        } catch (IndexOutOfBoundsException e) {
            break;
        }
    }
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
