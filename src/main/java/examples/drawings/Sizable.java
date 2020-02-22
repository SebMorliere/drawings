package examples.drawings;

import static conf.Default.HEIGHT;
import static conf.Default.WIDTH;

public class Sizable {
    int width;
    int height;

    public Sizable() {
        this.height = HEIGHT;
        this.width = WIDTH;
    }

    public Sizable(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void resize(Sizable sizable) {
        this.height = sizable.height;
        this.width = sizable.width;
    }
}
