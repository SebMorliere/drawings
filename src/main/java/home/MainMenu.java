package home;

import essay.EssayFrame;
import mandelbrot.MandelbrotFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class MainMenu extends JFrame implements ActionListener {


    public MainMenu() {
        JButton[] buttonArray = new JButton[]{
                Buttons.MANDELBROT.button,
                Buttons.WATERFALL.button,
                Buttons.G2048.button,
                Buttons.QUIT.button
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
        if (e.getActionCommand().equals(Buttons.QUIT.label)) {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if (e.getActionCommand().equals(Buttons.MANDELBROT.label)) {
            new MandelbrotFrame();
        } else if (e.getActionCommand().equals(Buttons.WATERFALL.label)) {
            new EssayFrame();
        }
    }
}