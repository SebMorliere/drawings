package mandelbrot;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class MandelbrotDraw extends JPanel {
    private ArrayList<ScreenPixel> screenPixels;
    private ArrayList<Integer> xlist;
    private ArrayList<Integer> ylist;
    private ArrayList<Double> aList;
    private ArrayList<Double> bList;

    private int ITER_MAX = 500;
    private double BOUDARY = 2d;

    private MAlgo mandelbrot;

    public MandelbrotDraw() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        this.setBackground(Color.BLACK);
        this.screenPixels = new ArrayList<>();
    }

    private void init() {
        int maxW = this.getWidth();
        int maxH = this.getHeight();
        double aMin = -2d;
        double aMax = 1d;
        double bMin = -1d;
        double bMax = 1d;
        this.xlist = IntStream.range(0, maxW).boxed().collect(Collectors.toCollection(ArrayList::new));
        this.ylist = IntStream.range(0, maxH).boxed().collect(Collectors.toCollection(ArrayList::new));
        this.aList = scale(0, maxW, aMin, aMax).boxed().collect(Collectors.toCollection(ArrayList::new));
        this.bList = scale(0, maxH, bMin, bMax).boxed().collect(Collectors.toCollection(ArrayList::new));

        ArrayList<Pair<Integer, Double>> horizontalPairs = IntStream.range(0, xlist.size())
                .mapToObj(i -> new Pair<>(xlist.get(i), aList.get(i)))
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Pair<Integer, Double>> verticalPairs = IntStream.range(0, ylist.size())
                .mapToObj(i -> new Pair<>(ylist.get(i), bList.get(i)))
                .collect(Collectors.toCollection(ArrayList::new));

        horizontalPairs.forEach(integerDoublePairH -> verticalPairs.forEach(integerDoublePairV -> {
            ComplexNumber c = new ComplexNumber(integerDoublePairH.getValue(), integerDoublePairV.getValue());
            this.screenPixels.add(new ScreenPixel(integerDoublePairH.getKey(), integerDoublePairV.getKey(), Color.BLACK, c));
        }));

//        this.setBackground(Color.BLACK);
        this.setBackground(new Color(0.5f, 0.5f, 0.5f));
        this.mandelbrot = new MAlgo(ITER_MAX, BOUDARY);
    }

    private void draw(Graphics2D g) {
        if (this.mandelbrot == null) {
            this.init();
        }
        this.screenPixels.forEach(p -> {
            int res = this.mandelbrot.calc(p.complexNumber);
            p.color = generateColor(res);
        });
        this.screenPixels.forEach(p -> {
            g.setPaint(p.color);
            g.drawLine(p.x, p.y, p.x, p.y);
        });
    }

    private Color generateColor(int i) {
        if (i == ITER_MAX) {
            return Color.BLACK;
        } else {
            int r = 125;
            int g = 125;
            int b = 125;
            int tier1 = Math.round(ITER_MAX / 3f);
            int tier2 = tier1 + tier1;
            if (i < tier1) {
                r = Math.round(((float) i / tier1) * 255);
            } else if (i < tier2) {
                g = Math.round(((float) i / tier2) * 255);
            } else {
                b = Math.round(((float) i / ITER_MAX) * 255);
            }
//            System.out.println(i + " >> " + "r:" + r + " g:" + g + " b:" + b);
            return new Color(r, g, b);
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.draw((Graphics2D) graphics);
        this.setCursor(Cursor.getDefaultCursor());
    }

    public static DoubleStream scale(int xMin, int xMax, double aMin, double aMax) {
        DoubleStream res = IntStream.range(xMin, xMax).mapToDouble(v -> (v * (aMax - aMin) / xMax) + aMin);
        return res;
    }

}
