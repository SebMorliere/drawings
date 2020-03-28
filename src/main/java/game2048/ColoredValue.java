package game2048;

import java.awt.*;

public enum ColoredValue {
    RANK_1(  1, new Color(255, 230, 200), new Color( 73,  73,  73)),
    RANK_2(  2, new Color(255, 210, 180), new Color( 73,  73,  73)),
    RANK_3(  3, new Color(255, 190, 160), new Color( 73,  73,  73)),
    RANK_4(  4, new Color(255, 170, 140), new Color( 73,  73,  73)),
    RANK_5(  5, new Color(255, 150, 120), new Color( 73,  73,  73)),
    RANK_6(  6, new Color(255, 130, 100), new Color( 73,  73,  73)),
    RANK_7(  7, new Color(255, 110,  80), new Color( 73,  73,  73)),
    RANK_8(  8, new Color(255,  90,  60), new Color( 73,  73,  73)),
    RANK_9(  9, new Color(255,  70,  40), new Color( 73,  73,  73)),
    RANK_10(10, new Color(255,  50,  20), new Color( 73,  73,  73)),
    RANK_11(11, new Color(255,  30,   0), new Color( 73,  73,  73)),
    RANK_12(12, new Color(180,  30,   0), new Color(222, 222, 222)),
    RANK_13(13, new Color(110,  30,   0), new Color(222, 222, 222)),
    RANK_14(14, new Color( 40,  30,   0), new Color(222, 222, 222)),
    RANK_15(15, new Color(  0,  00,   0), new Color(255, 255, 255));

    Integer rank;
    Color bgColor;
    Color fontColor;

    ColoredValue(Integer rank, Color bgColor, Color fontColor) {
        this.rank = rank;
        this.bgColor = bgColor;
        this.fontColor = fontColor;
    }



    @Override
    public String toString() {
        return this.rank.toString() + "ù";
    }
}
