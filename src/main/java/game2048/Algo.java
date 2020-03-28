package game2048;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.IntStream;

public class Algo {

    public static ArrayList<Cell> slideLeft(Grid<Cell> grid) {
        final ArrayList<Cell> cellsChanged = new ArrayList<>();
        IntStream.range(0, grid.height).boxed().forEach(lineIndex -> {
            final ArrayList<Boolean> mergedSpots = new ArrayList<>(Collections.nCopies(grid.width, false));
            IntStream.range(0, grid.width).boxed().map(idx -> {
                int colIndex = idx;
                return grid.getCell(lineIndex, colIndex);
            }).forEach(cellOpt -> {
                if (cellOpt.isPresent()) {
                    Cell cell = cellOpt.get();
                    final int colIndexOrig = cell.columnIndex;
                    boolean processed = false;
                    int slider = 1;
                    Optional<GridSpot> lastFreeSpotOpt = Optional.empty();
                    while (!processed) {
                        int destIndex = cell.columnIndex - slider;
                        if (destIndex > -1) {
                            final Optional<Cell> destCellOpt = grid.getCell(lineIndex, destIndex);

                            // if destination cell is empty
                            if (!destCellOpt.isPresent()) {
                                // remember the free spot
                                lastFreeSpotOpt = Optional.of(new GridSpot(lineIndex, destIndex));
                                // if it is the last possible spot then move cell
                                if (destIndex == 0) {
                                    grid.moveCell(cell, lastFreeSpotOpt.get());
                                    mergedSpots.set(lastFreeSpotOpt.get().columnIndex, false);
                                    mergedSpots.set(cell.columnIndex, false);
                                    processed = true;
                                    cellsChanged.add(cell);
                                    // else continue checking
                                } else {
                                    processed = false;
                                }

                                // if spot is not merged
                            } else if (!mergedSpots.get(destIndex)) {
                                final Cell destCell = destCellOpt.get();
                                // if it is mergeable then merge it!
                                if (cell.rank == destCell.rank) {
                                    destCell.merge();
                                    grid.removeCell(cell);
                                    mergedSpots.set(destIndex, true);
                                    mergedSpots.set(colIndexOrig, false);
                                    cellsChanged.add(destCell);
                                    processed = true;
                                    // if the previous spot was free, move to it
                                } else if (lastFreeSpotOpt.isPresent()) {
                                    grid.moveCell(cell, lastFreeSpotOpt.get());
                                    mergedSpots.set(lastFreeSpotOpt.get().columnIndex, false);
                                    mergedSpots.set(colIndexOrig, false);
                                    cellsChanged.add(cell);
                                    processed = true;
                                    // no move nor merge possible
                                } else {
                                    processed = true;
                                }

                                // if spot is merged, then move to last know possible position
                            } else if (lastFreeSpotOpt.isPresent()) {
                                grid.moveCell(cell, lastFreeSpotOpt.get());
                                mergedSpots.set(lastFreeSpotOpt.get().columnIndex, false);
                                mergedSpots.set(cell.columnIndex, false);
                                cellsChanged.add(cell);
                                processed = true;
                            } else {
                                processed = true;
                            }

                            // nothing to do anymore
                        } else {
                            processed = true;
                        }
                        slider++;
                    }
                }
            });
        });
        return cellsChanged;
    }

    public static ArrayList<Cell> slideRight(Grid<Cell> grid) {
        final ArrayList<Cell> cellsChanged = new ArrayList<>();
        IntStream.range(0, grid.height).boxed().forEach(lineIndex -> {
            final ArrayList<Boolean> mergedSpots = new ArrayList<>(Collections.nCopies(grid.width, false));
            IntStream.range(0, grid.width).boxed().map(idx -> {
                int colIndex = grid.width - idx - 1;
                return grid.getCell(lineIndex, colIndex);
            }).forEach(cellOpt -> {
                if (cellOpt.isPresent()) {
                    Cell cell = cellOpt.get();
                    final int colIndexOrig = cell.columnIndex;
                    boolean processed = false;
                    int slider = 1;
                    Optional<GridSpot> lastFreeSpotOpt = Optional.empty();
                    while (!processed) {
                        int destIndex = cell.columnIndex + slider;
                        if (destIndex < mergedSpots.size()) {
                            final Optional<Cell> destCellOpt = grid.getCell(lineIndex, destIndex);

                            // if destination cell is empty
                            if (!destCellOpt.isPresent()) {
                                // remember the free spot
                                lastFreeSpotOpt = Optional.of(new GridSpot(lineIndex, destIndex));
                                // if it is the last possible spot then move cell
                                if (destIndex == mergedSpots.size() - 1) {
                                    grid.moveCell(cell, lastFreeSpotOpt.get());
                                    mergedSpots.set(lastFreeSpotOpt.get().columnIndex, false);
                                    mergedSpots.set(cell.columnIndex, false);
                                    processed = true;
                                    cellsChanged.add(cell);
                                    // else continue checking
                                } else {
                                    processed = false;
                                }

                                // if spot is not merged
                            } else if (!mergedSpots.get(destIndex)) {
                                final Cell destCell = destCellOpt.get();
                                // if it is mergeable then merge it!
                                if (cell.rank == destCell.rank) {
                                    destCell.merge();
                                    grid.removeCell(cell);
                                    mergedSpots.set(destIndex, true);
                                    mergedSpots.set(colIndexOrig, false);
                                    cellsChanged.add(destCell);
                                    processed = true;
                                    // if the previous spot was free, move to it
                                } else if (lastFreeSpotOpt.isPresent()) {
                                    grid.moveCell(cell, lastFreeSpotOpt.get());
                                    mergedSpots.set(lastFreeSpotOpt.get().columnIndex, false);
                                    mergedSpots.set(colIndexOrig, false);
                                    cellsChanged.add(cell);
                                    processed = true;
                                    // no move nor merge possible
                                } else {
                                    processed = true;
                                }

                                // if spot is merged, then move to last know possible position
                            } else if (lastFreeSpotOpt.isPresent()) {
                                grid.moveCell(cell, lastFreeSpotOpt.get());
                                mergedSpots.set(lastFreeSpotOpt.get().columnIndex, false);
                                mergedSpots.set(cell.columnIndex, false);
                                cellsChanged.add(cell);
                                processed = true;
                            } else {
                                processed = true;
                            }

                            // nothing to do anymore
                        } else {
                            processed = true;
                        }
                        slider++;
                    }
                }
            });
        });
        return cellsChanged;
    }
}
