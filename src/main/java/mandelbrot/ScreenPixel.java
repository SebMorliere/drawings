package mandelbrot;

import java.awt.*;

public class ScreenPixel {
    int x;
    int y;
    Color color;
    ComplexNumber complexNumber;

    public ScreenPixel(int x, int y, Color color, ComplexNumber complexNumber) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.complexNumber = complexNumber;
    }
}
