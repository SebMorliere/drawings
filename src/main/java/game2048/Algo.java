package game2048;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Algo {

    public static void slideLeft(Grid<Cell> grid) {
        IntStream.range(0, grid.height).boxed().forEach(lineIndex -> {
            final ArrayList<Boolean> wasMerged = new ArrayList<>();
            final ArrayList<Boolean> emptySpots = new ArrayList<>();
            IntStream.range(0, grid.width).boxed().forEach(colIndex -> {
//                System.out.println("going for cell at l:" + lineIndex + " c: " + colIndex);
                Optional<Cell> cellOpt = grid.getCell(lineIndex, colIndex);
                if (cellOpt.isPresent()) {
                    Cell thisCell = cellOpt.get();
                    int indexOfFirstEmptySpot = emptySpots.indexOf(true);
                    int indexOfLastTakenSpot = emptySpots.lastIndexOf(false);
                    if (indexOfFirstEmptySpot == -1 && colIndex == 0) {
//                        System.out.println("c1");
                        emptySpots.add(false);
                        wasMerged.add(false);
                    } else if (indexOfFirstEmptySpot == 0) {
//                        System.out.println("c2");
                        if (grid.moveCell(thisCell, new GridSpot(lineIndex, indexOfFirstEmptySpot))) {
                            emptySpots.add(true);
                        } else {
                            emptySpots.add(false);
                        }
                        wasMerged.add(false);
                    } else if (!wasMerged.get(indexOfLastTakenSpot)
                            && grid.getCell(lineIndex, indexOfLastTakenSpot).isPresent()) {
                        Cell previousCell = grid.getCell(lineIndex, indexOfLastTakenSpot).get();
                        if (previousCell.isSameRank(thisCell)) {
//                            System.out.println("c3");
                            previousCell.merge();
                            grid.removeCell(thisCell);
                            emptySpots.add(true);
                            wasMerged.add(true);
                        } else if (indexOfLastTakenSpot + 1 != colIndex) {
//                            System.out.println("c4");
                            if (grid.moveCell(thisCell, new GridSpot(lineIndex, indexOfFirstEmptySpot))) {
                                emptySpots.add(true);
                            } else {
                                emptySpots.add(false);
                            }
                            wasMerged.add(false);
                        } else {
//                            System.out.println("c5");
                            emptySpots.add(false);
                            wasMerged.add(false);
                        }
                    } else {
                        if (indexOfFirstEmptySpot != colIndex) {
//                            System.out.println("c6");
                            if (grid.moveCell(thisCell, new GridSpot(lineIndex, indexOfFirstEmptySpot))) {
                                emptySpots.add(true);
                            } else {
                                emptySpots.add(false);
                            }
                        } else {
//                            System.out.println("c7");
                            emptySpots.add(false);
                        }
                        wasMerged.add(false);
                    }
                } else {
//                    System.out.println("c8");
                    emptySpots.add(true);
                    wasMerged.add(false);
                }
            });
        });
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
