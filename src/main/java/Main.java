import conf.Default;
import drawables.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Main extends JFrame {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            ArrayList<Drawable> drawables = new ArrayList<>();
            drawables.add(new Points(new Size()));
            drawables.add(new Lines());
            drawables.add(new BasicStrokes());

            Surface surface = new Surface(drawables);

            JFrame mainFrame = new JFrame("ima biutiful windo");
            mainFrame.setSize(Default.WIDTH, Default.HEIGHT);
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
                        if (drawable instanceof Size) {
                            ((Size) drawable).resize(new Size(w, h));
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


