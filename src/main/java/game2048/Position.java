package game2048;

public class Position {
    public Integer index;
//    public Integer column;
//    public Integer line;
//
//    public Position(Integer column, Integer line) {
//        this.column = column;
//        this.line = line;
//    }

    public Position(Integer index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "[" + this.index.toString() + "]";
    }
}
