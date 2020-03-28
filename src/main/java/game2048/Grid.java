package game2048;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Grid<T extends GridSpot> {
    final public Integer width;
    final public Integer height;
    final public Integer maxSize;

    final private ArrayList<T> cells;

    public Grid(Integer width, Integer height) {
        if (width < 2 || height < 2) {
            throw new RuntimeException("must be at least 2 by 2 grid");
        }
        this.width = width;
        this.height = height;

        this.cells = new ArrayList<>();
        this.maxSize = this.width * this.height;
    }

    public ArrayList<GridSpot> getFreeSpots() {
        return IntStream.range(0, this.height).boxed()
                .flatMap(linidx -> IntStream.range(0, this.width).boxed()
                        .map(colidx -> new GridSpot(linidx, colidx)))
                .filter(gridSpot -> this.cells.stream().noneMatch(gridSpot::equals))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Optional<GridSpot> getRandomFreeSpot() {
        if (this.cells.size() == this.maxSize) {
            return Optional.empty();
        }
        int indexOfFreeSpot = new Random().nextInt(this.getFreeSpots().size());
        return Optional.of(this.getFreeSpots().get(indexOfFreeSpot));
    }

    public boolean addCell(T cell) {
        if (this.getCell(cell.lineIndex, cell.columnIndex).isPresent()) {
            System.out.println("impossible to add new cell, grid spot not empty: " + cell.toString());
            return false;
        } else if (this.cells.size() > this.maxSize) {
            System.out.println("impossible to add new cell, grid is full!");
            return false;
        } else {
            System.out.println("adding cell: " + cell);
            this.cells.add(cell);
            return true;
        }
    }

    public void removeCell(T cell) {
        this.cells.remove(cell);
    }

    public boolean moveCell(T cell, GridSpot newSpot) {
        if (this.getCell(newSpot.lineIndex, newSpot.columnIndex).isPresent()) {
            System.out.println("impossible to move cell from (" + cell.asGridSpot().toString() + ") to non empty spot: (" + newSpot.toString() + ")");
            return false;
        } else {
            cell.lineIndex = newSpot.lineIndex;
            cell.columnIndex = newSpot.columnIndex;
            return true;
        }
    }

    public Stream<T> streamCells() {
        return this.cells.stream();
    }

    public Optional<T> getCell(int lineIndex, int columnIndex) {
        return this.cells.stream()
                .filter(cell -> new GridSpot(lineIndex, columnIndex).equals(cell))
                .findFirst();
    }

    @Override
    public String toString() {
        return this.cells.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }
}
