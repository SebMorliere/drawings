package examples.drawings.buttons;

import essay.EssayFrame;
import mandelbrot.MandelbrotFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class ImageIconButtonEx extends JFrame implements ActionListener {


    public ImageIconButtonEx() {
        ImageIcon homeIcon = new ImageIcon("src/resources/icon-9-dots.png");
        ImageIcon saveIcon = new ImageIcon("src/resources/icon-save-big.png");
        ImageIcon exitIcon = new ImageIcon("src/resources/icon-exit-big.png");
        ImageIcon blurIcon = new ImageIcon("src/resources/icon-blur-big.png");
        ImageIcon gradientIcon = new ImageIcon("src/resources/icon-gradient-big.png");

        JButton[] buttonArray = new JButton[]{
                new JButton(ButtonLabels.MANDELBROT.label, blurIcon),
                new JButton(ButtonLabels.WATERFALL.label, gradientIcon),
                new JButton(ButtonLabels.QUIT.label, exitIcon)
        };
        Arrays.stream(buttonArray).forEach(btn -> btn.addActionListener(this));

        createLayout(buttonArray);

        this.setTitle("Awesome Buttons");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void createLayout(JComponent... arg) {
        Container pane = this.getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        GroupLayout.SequentialGroup sg = gl.createSequentialGroup();
        Arrays.stream(arg).forEach(sg::addComponent);
        gl.setHorizontalGroup(sg);

        GroupLayout.ParallelGroup pg = gl.createParallelGroup();
        Arrays.stream(arg).forEach(pg::addComponent);
        gl.setVerticalGroup(pg);

        gl.linkSize(arg);

        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ButtonLabels.QUIT.label)) {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if (e.getActionCommand().equals(ButtonLabels.MANDELBROT.label)) {
            new MandelbrotFrame();
        } else if (e.getActionCommand().equals(ButtonLabels.WATERFALL.label)) {
            new EssayFrame();
        }
    }
}