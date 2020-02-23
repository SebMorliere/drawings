package game2048;

import java.awt.*;

public enum ColoredValue {
    RANK_1(     2, new Color(255, 230, 200), new Color( 73,  73,  73)),
    RANK_2(     4, new Color(255, 210, 180), new Color( 73,  73,  73)),
    RANK_3(     8, new Color(255, 190, 160), new Color( 73,  73,  73)),
    RANK_4(    16, new Color(255, 170, 140), new Color( 73,  73,  73)),
    RANK_5(    32, new Color(255, 150, 120), new Color( 73,  73,  73)),
    RANK_6(    64, new Color(255, 130, 100), new Color( 73,  73,  73)),
    RANK_7(   128, new Color(255, 110,  80), new Color( 73,  73,  73)),
    RANK_8(   256, new Color(255,  90,  60), new Color( 73,  73,  73)),
    RANK_9(   512, new Color(255,  70,  40), new Color( 73,  73,  73)),
    RANK_10( 1024, new Color(255,  50,  20), new Color( 73,  73,  73)),
    RANK_11( 2048, new Color(255,  30,   0), new Color( 73,  73,  73)),
    RANK_12( 4096, new Color(180,  30,   0), new Color(222, 222, 222)),
    RANK_13( 8198, new Color(110,  30,   0), new Color(222, 222, 222)),
    RANK_14(16384, new Color( 40,  30,   0), new Color(222, 222, 222)),
    RANK_N(    -1, new Color(  0,  00,   0), new Color(255, 255, 255));

    Integer value;
    Color bgColor;
    Color fontColor;

    ColoredValue(Integer value, Color bgColor, Color fontColor) {
        this.value = value;
        this.bgColor = bgColor;
        this.fontColor = fontColor;
    }

    @Override
    public String toString() {
        return this.value.toString() + "ù";
    }
}
