package game2048;

public class GridSpot {
    int lineIndex;
    int columnIndex;

    public GridSpot(int lineIndex, int columnIndex) {
        this.lineIndex = lineIndex;
        this.columnIndex = columnIndex;
    }

    public GridSpot asGridSpot() {
        return this;
    }

    @Override
    public String toString() {
        return "l:" + this.lineIndex + " c:" + this.columnIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof GridSpot) {
            return ((GridSpot) obj).lineIndex == this.lineIndex && ((GridSpot) obj).columnIndex == this.columnIndex;
        } else {
            return false;
        }
    }
}
