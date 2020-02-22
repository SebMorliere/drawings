package home;

import javax.swing.*;

public enum Buttons {
    HOME("Home", "src/resources/icon-9-dots.png"),
    SAVE("Save", "src/resources/icon-save-big.png"),
    QUIT("Quit", "src/resources/icon-exit-big.png"),
    MANDELBROT("Mandelbrot", "src/resources/icon-blur-big.png"),
    WATERFALL("Waterfall", "src/resources/icon-gradient-big.png"),
    G2048("2048", "src/resources/icon-blocks-big.png");

    String label;
    String iconPath;
    ImageIcon icon;
    JButton button;

    Buttons(String label, String iconPath) {
        this.label = label;
        this.iconPath = iconPath;
        this.icon = new ImageIcon(this.iconPath);
        this.button = new JButton(this.label, this.icon);
    }
}
