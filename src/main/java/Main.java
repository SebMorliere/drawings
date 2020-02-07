import drawables.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Main extends JFrame {
    public static final int DELAY = 100;

    public static void main(String[] args) {
        int defaultWidth = 800;
        int defaultHeight = 800;

        EventQueue.invokeLater(() -> {
            ArrayList<Drawable> drawables = new ArrayList<>();
            drawables.add(new Points(defaultWidth, defaultHeight));
            drawables.add(new Lines());
            drawables.add(new BasicStrokes());

            Surface surface = new Surface(drawables);

            JFrame mainFrame = new JFrame("ima biutiful windo");
            mainFrame.setTitle("This is my world");
            mainFrame.setSize(defaultWidth, defaultHeight);
            mainFrame.setLocationRelativeTo(null);
            mainFrame.add(surface);
            mainFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    surface.getTimer().stop();
                }
            });
            mainFrame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    int w = e.getComponent().getWidth();
                    int h = e.getComponent().getHeight();
                    drawables.forEach(drawable -> {
                        if (drawable instanceof Resizable) {
                            ((Resizable) drawable).resize(w, h);
                        }
                    });

                    super.componentResized(e);
                }
            });
            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mainFrame.setVisible(true);
        });
    }
}


