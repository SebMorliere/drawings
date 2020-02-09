package mandelbrot;

public class MAlgo {
    private int iterationMAx;
    private double boundarySquared;

    public MAlgo(int iterationMAx, double boundary) {
        this.iterationMAx = iterationMAx;
        this.boundarySquared = boundary * boundary;
    }

    public int calc(ComplexNumber c) {
        int i = 0;
        ComplexNumber nextZ = processNext(c, c);
        while (i < this.iterationMAx && nextZ.unracinedValue() < this.boundarySquared) {
            nextZ = processNext(c, nextZ);
            i++;
        }
        return i;
    }

    private static ComplexNumber processNext(ComplexNumber initialC, ComplexNumber lastZ) {
        // c = a + b*1
        // Z0 = c
        // Zn+1 = Zn^2 + c
        // Zn+1 = (Zna^2 - Znb + a) + (2*Zna*Znb + b)*i
        double newA = lastZ.a * lastZ.a - lastZ.b * lastZ.b + initialC.a;
        double newB = 2.0 * lastZ.a * lastZ.b + initialC.b;
        return new ComplexNumber(newA, newB);
    }

}
