import essay.EssayFrame;
import examples.drawings.buttons.ImageIconButtonEx;
import mandelbrot.MandelbrotFrame;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

//        EventQueue.invokeLater(EssayFrame::new);
//        EventQueue.invokeLater(MandelbrotFrame::new);
        EventQueue.invokeLater(ImageIconButtonEx::new);
    }
}


