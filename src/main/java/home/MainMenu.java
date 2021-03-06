package home;

import essay.EssayFrame;
import game2048.Game2048Frame;
import mandelbrot.MandelbrotFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

        this.createLayout(buttonArray);

        this.setTitle("Awesome Buttons");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

// // display all system fonts
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        Font[] fonts = ge.getAllFonts();
//        Arrays.stream(fonts).forEach(font -> System.out.println(font.getFontName() + " : " + font.getFamily()));

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
        } else if (e.getActionCommand().equals(Buttons.G2048.label)) {
            new Game2048Frame();
        }
    }
}