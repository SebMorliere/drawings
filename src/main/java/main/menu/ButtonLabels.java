package main.menu;

public enum ButtonLabels {
    HOME("Home"),
    SAVE("Save"),
    QUIT("Quit"),
    MANDELBROT("Mandelbrot"),
    WATERFALL("Watrfall");

    String label;

    ButtonLabels(String label) {
        this.label = label;
    }
}
