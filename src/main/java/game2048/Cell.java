package game2048;

public class Cell {
    public ColoredValue params;

    public Cell(ColoredValue params) {
        this.params = params;
    }

    @Override
    public boolean equals(Object obj) {
        return this.params.equals(obj);
    }

    @Override
    public String toString() {
        return "<" + this.params.value + ">";
    }
}
