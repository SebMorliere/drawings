import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HelloWorld extends JFrame {
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame mainFrame = new JFrame("ima biutiful windo");
            mainFrame.setTitle("This is my world");
            mainFrame.setSize(800, 800);
            mainFrame.setLocationRelativeTo(null);
            Surface surface = new Surface();
            mainFrame.add(surface);
            mainFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    surface.getTimer().stop();
                }
            });
            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mainFrame.setVisible(true);
        });
    }
}


