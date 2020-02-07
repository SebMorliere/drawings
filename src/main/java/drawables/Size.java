package drawables;

import static conf.Default.HEIGHT;
import static conf.Default.WIDTH;

public class Size {
    int width;
    int height;

    public Size() {
        this.height = HEIGHT;
        this.width = WIDTH;
    }

    public void resize(Size size) {
        this.height = size.height;
        this.width = size.width;
    }

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
