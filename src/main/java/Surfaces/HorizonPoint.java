package Surfaces;

import java.util.Collections;
import java.util.Random;

public class HorizonPoint implements Cloneable {
    @Override
    protected Object clone() {
        return new HorizonPoint(this.maxWidth, this.maxHeight, this.maxHeight - this.y, x);
    }

    private Random rand = new Random();
    private int maxWidth;
    private int maxHeight;
    private int deltaPlus = 5;
    private int deltaMinus = -5;
    private int delta = deltaPlus - deltaMinus;
    private int horizonMaxAlt;
    private int horizonMinAlt;

    public int x;
    public int y;

    public HorizonPoint(int maxWidth, int maxHeight, int horizonAltitude, int x) {
        this.x = x;
        this.y = maxHeight - horizonAltitude;
        this.horizonMaxAlt = horizonAltitude + 100;
        this.horizonMinAlt = horizonAltitude - 100;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    public void evolve() {
        int rnd = rand.nextInt(delta + 1) + deltaMinus;
        int newAlt = this.y + rnd;
        if (newAlt > horizonMaxAlt) {
            this.y = horizonMaxAlt;
        } else if (newAlt < horizonMinAlt) {
            this.y = horizonMinAlt;
        } else {
            this.y = newAlt;
        }
    }

    public void dropAltitude() {
        this.dropAltitude(2);
    }

    public void dropAltitude(int deltaY) {
        int newHeight = this.y + Math.abs(deltaY); // altitude is going up but y is going down
        if (newHeight > maxHeight) {
            this.y = maxHeight;
            this.horizonMaxAlt = maxHeight;
            this.horizonMinAlt = maxHeight;
        } else {
            this.y += Math.abs(deltaY);
            this.horizonMaxAlt += Math.abs(deltaY);
            this.horizonMinAlt += Math.abs(deltaY);
        }
    }

    @Override
    public String toString() {
        return "x: " + String.join("", Collections.nCopies(4 - String.valueOf(x).length(), " ")) + x
                + " | y: " + String.join("", Collections.nCopies(3 - String.valueOf(y).length(), " ")) + y;
    }
}