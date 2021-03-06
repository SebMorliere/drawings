package essay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static conf.Default.REFRESH_RATE;

public class EssayDraw extends JPanel implements ActionListener {
    private Timer timer;
    private Color lightBlue = new Color(100, 160, 240);
    private Color darkBlue = new Color(40, 100, 180);
    private int xStepSize = 30;
    private int landscapeBufferSize = 800;
    private int startingAlt = 1100;

    private ArrayList<HorizonPoint> horizonPointList;
    private ArrayList<ArrayList<HorizonPoint>> landscape;

    public EssayDraw() {
        this.timer = new Timer(REFRESH_RATE, this);
        this.timer.start();
        this.setBackground(Color.BLACK);
    }

    private void init() {
        this.horizonPointList = new ArrayList<>();
        this.landscape = new ArrayList<>();
        int x = 0;
        int maxH = this.getHeight();
        int maxW = this.getWidth();
        while (x < maxW + xStepSize) {
            horizonPointList.add(new HorizonPoint(maxW, maxH, startingAlt, x > maxW ? maxW : x));
            x += xStepSize;
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
            this.landscape.add(this.cloneHP(this.horizonPointList));
        } else {
            this.landscape.forEach(horizonPoints -> horizonPoints.forEach(horizonPoint -> {
                horizonPoint.dropAltitude(5);
            }));
            this.horizonPointList.forEach(HorizonPoint::evolve);
            this.drawLines(g, this.horizonPointList, darkBlue);
            this.landscape.forEach(horizonPoints -> drawLines(g, horizonPoints, lightBlue));
            this.landscape.add(0, this.cloneHP(this.horizonPointList));
            this.cleanLandscape();
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

    private ArrayList<HorizonPoint> cloneHP(ArrayList<HorizonPoint> list) {
        ArrayList<HorizonPoint> newList = new ArrayList<>();
        list.forEach(item -> newList.add((HorizonPoint) item.clone()));
        return newList;
    }

    private void cleanLandscape() {
        int idx = this.landscape.size();
        while (idx > landscapeBufferSize) {
            this.landscape.remove(idx - 1);
            idx = this.landscape.size();
        }
        this.landscape = this.landscape.stream()
                .filter(hplist -> !hplist.stream().allMatch(horizonPoint -> horizonPoint.y >= this.getHeight()))
                .collect(Collectors.toCollection(ArrayList::new));
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
