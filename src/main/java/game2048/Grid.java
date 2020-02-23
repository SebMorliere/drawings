package game2048;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Grid<T> {
    final public Integer width;
    final public Integer height;

    final private ArrayList<Optional<T>> inlineGrid;

    public Grid(Integer width, Integer height) {
        if (width < 2 || height < 2) {
            throw new RuntimeException("must be at least 2 by 2 grid");
        }
        this.width = width;
        this.height = height;

        this.inlineGrid = new ArrayList<>(Collections.nCopies(this.width * this.height, Optional.empty()));
//        System.out.println("grid size: " + this.inlineGrid.size());
    }

    public void setCell(int index, T cell) {
        if (index > this.size()) {
            throw new RuntimeException("index too big for that poor little grid (" + index + ">" + this.size() + ")");
        } else {
            this.inlineGrid.set(index, Optional.of(cell));
        }
    }

    public Optional<T> getCell(int index) {
        if (index > this.size()) {
            return Optional.empty();
        } else {
            return this.inlineGrid.get(index);
        }
    }

    public Optional<T> getCell(int x, int y) {
        if (x < 0 || x > this.width || y < 0 || y > this.height) {
            return Optional.empty();
        } else {
            int index = this.width * x + y;
            return this.inlineGrid.get(index);
        }
    }

    public Stream<Optional<T>> streamCells() {
        return this.inlineGrid.stream();
    }

    public Stream<Integer> streamIndex() {
        return IntStream.range(0, this.size()).boxed();
    }

    public Stream<ArrayList<Optional<T>>> streamLines() {
        return IntStream.range(0, this.height).boxed().map(this::getLine);
    }

    public Stream<ArrayList<Optional<T>>> streamColumn() {
        return IntStream.range(0, this.width).boxed().map(this::getLine);
    }

    public ArrayList<Optional<T>> getLine(int lineNumber) {
        if (lineNumber < 1 || lineNumber > this.height) {
            System.out.println("out of range line index: " + lineNumber);
            return new ArrayList<>();
        } else {
            int startIndex = (lineNumber - 1) * this.width;
            int endIndex = lineNumber * this.width - 1;
            return (ArrayList<Optional<T>>) this.inlineGrid.subList(startIndex, endIndex);
        }
    }

    private Stream<Integer> getLineIndexStream(int lineNumber) {
        if (lineNumber < 1 || lineNumber > this.height) {
            System.out.println("out of range line index: " + lineNumber);
            return Stream.empty();
        } else {
            int startIndex = (lineNumber - 1) * this.width;
            int endIndex = lineNumber * this.width - 1;
            return IntStream.range(startIndex, endIndex).boxed();
        }
    }

    private Stream<Integer> getColumnIndexStream(int colNumber) {
        if (colNumber < 1 || colNumber > this.width) {
            System.out.println("out of range column index: " + colNumber);
            return Stream.empty();
        } else {
            ArrayList<Optional<T>> column = new ArrayList<>();
            int nextIndex = colNumber - 1;
            return Stream.iterate(nextIndex, i -> i + this.width).limit(this.size());
        }
    }

    public ArrayList<Optional<T>> getColumn(int colNumber) {
        if (colNumber < 1 || colNumber > this.width) {
            System.out.println("out of range column index: " + colNumber);
            return new ArrayList<>();
        } else {
            ArrayList<Optional<T>> column = new ArrayList<>();
            int nextIndex = colNumber - 1;
            while (nextIndex < this.size()) {
                column.add(this.inlineGrid.get(nextIndex));
                nextIndex += this.width;
            }
            return column;
        }
    }

    public void updateLine(int lineNumber, ArrayList<Optional<T>> newLine) {
        if (lineNumber < 1 || lineNumber > this.height) {
            System.out.println("out of range line index: " + lineNumber);
            // if new line is bigger than expected, it will be truncated
            // if new line is smaller then expected, it will be filled with Optional.empty()
        } else {
            int startIndex = (lineNumber - 1) * this.width;
            int endIndex = lineNumber * this.width - 1;
            IntStream.range(startIndex, endIndex).boxed();
        }
    }

    public Integer size() {
        return this.inlineGrid.size();
    }

    @Override
    public String toString() {
        return this.inlineGrid.stream()
                .map(t -> (t.isPresent() ? t.get().toString() : "<->"))
                .collect(Collectors.joining(", "));
    }
}
