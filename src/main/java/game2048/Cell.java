package game2048;

import java.util.Random;

public class Cell extends GridSpot {
    private static int exhausted = 0;
    int rank;

    public Cell(GridSpot spot, int rank) {
        super(spot.lineIndex, spot.columnIndex);
        this.rank = Math.max(rank, 1);
    }

    public String getValue() {
        return String.valueOf(Math.round(Math.pow(2d, this.rank)));
    }

    public Cell merge() {
        this.rank++;
        return this;
    }

    public static int generateRandomStartingRank() {
        int proba = 10 + Cell.exhausted * 50;
        int value = new Random().nextInt(proba) > 2 ? 1 : 2;
        if (value == 2) {
            Cell.exhausted++;
        }
        return value;
    }

    public static int generateRandomRank() {
        return new Random().nextInt(10) > 2 ? 1 : 2;
    }

    public boolean isSameRank(Cell cell) {
        return this.rank == cell.rank;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Cell) {
            return super.equals(obj) && this.rank == ((Cell) obj).rank;
        } else {
            return false;
        }
    }
}

