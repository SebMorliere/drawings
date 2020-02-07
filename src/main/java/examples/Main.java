package examples;

import java.awt.*;

public class Main {
    public final static int DELAY = 100;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
//            new PointsEx().setVisible(true);
            new LinesEx().setVisible(true);
            new BasicStrokesEx().setVisible(true);
        });
    }
}
