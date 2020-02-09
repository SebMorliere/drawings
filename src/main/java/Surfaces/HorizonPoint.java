package Surfaces;

import java.util.Collections;
import java.util.Random;

public class HorizonPoint {
    private Random rand = new Random();
    private int deltaPlus = 5;
    private int deltaMinus = -5;
    private int delta = deltaPlus - deltaMinus;
    private int horizonMaxAlt;
    private int horizonMinAlt;

    public int x;
    public int y;

    public HorizonPoint(int horizonAltitude, int x) {
        this.x = x;
        this.y = horizonAltitude;
        this.horizonMaxAlt = horizonAltitude + 100;
        this.horizonMinAlt = horizonAltitude - 100;
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
        this.dropAltitude(20);
    }

    public void dropAltitude(int deltaY) {
        int newAlt = this.y - Math.abs(deltaY);
        if (newAlt < 0) {
            this.y = 0;
        } else {
            this.y -= Math.abs(deltaY);
        }
    }

    @Override
    public String toString() {
        return "x: " + String.join("", Collections.nCopies(4 - String.valueOf(x).length(), " ")) + x
                + " | y: " + String.join("", Collections.nCopies(3 - String.valueOf(y).length(), " ")) + y;
    }
}