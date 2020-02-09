package Surfaces;

import java.util.Collections;
import java.util.Random;

public class HorizonPoint implements Cloneable {
    public int x;
    public int y;

    private Random rand = new Random();
    private int maxWidth;
    private int maxHeight;
    private int deltaPlus = 5;
    private int deltaMinus = -5;
    private int delta = deltaPlus - deltaMinus;
    private int yMax;
    private int yMin;

    public HorizonPoint(int maxWidth, int maxHeight, int horizonAltitude, int x) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.x = x;
        this.y = horizonAltitude > maxHeight ? 0 : maxHeight - horizonAltitude;
        this.yMax = this.y + 100 > maxHeight ? maxHeight : this.y + 100;
        this.yMin = this.y - 100 < 0 ? 0 : this.y - 100;
    }

    public void evolve() {
        int rnd = rand.nextInt(delta + 1) + deltaMinus;
        int newAlt = this.y + rnd;
        this.y =  newAlt > yMax ? yMax : newAlt < yMin ? yMin : newAlt;
    }

    public void dropAltitude() {
        this.dropAltitude(2);
    }

    public void dropAltitude(int deltaY) {
        int newHeight = this.y + Math.abs(deltaY); // altitude is going up but y is going down
        if (newHeight > maxHeight) {
            this.y = maxHeight;
            this.yMax = maxHeight;
            this.yMin = maxHeight;
        } else {
            this.y += Math.abs(deltaY);
            this.yMax += Math.abs(deltaY);
            this.yMin += Math.abs(deltaY);
        }
    }

    @Override
    protected Object clone() {
        return new HorizonPoint(this.maxWidth, this.maxHeight, this.maxHeight - this.y, x);
    }

    @Override
    public String toString() {
        return "x: " + String.join("", Collections.nCopies(6 - String.valueOf(x).length(), " ")) + x
                + " | y: " + String.join("", Collections.nCopies(6 - String.valueOf(y).length(), " ")) + y;
    }
}