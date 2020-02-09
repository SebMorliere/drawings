package mandelbrot;

public class ComplexNumber {
    // complex number: a + i.b
    double a;
    double b;

    public ComplexNumber(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double unracinedValue() {
        return (this.a * this.a) + (this.b * this.b);

    }

    public double absloluteValue() {
        // |a+bi| = racine(a^2 + b^2)
        return Math.sqrt(unracinedValue());

    }

}
