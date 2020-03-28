package game2048;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Optional;

import static conf.Default.REFRESH_RATE;

public class Game2048Draw extends JPanel implements ActionListener {
    final int cellLength = Game2048Frame.cellPixellength;

    private Timer timer;
    private int animationDuration = 150; /*ms*/
    private Grid<Cell> grid;
    public boolean isMoving;

    public KeyAdapter getKeyAdapter() {
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (Algo.slideUp(grid).size() > 0) {
                        Optional<GridSpot> newSpot = grid.getRandomFreeSpot();
                        if (newSpot.isPresent()) {
                            grid.addCell(new Cell(newSpot.get(), Cell.generateRandomRank()));
                        } else {
                            System.out.println("GAME ENDED!!!");
                        }
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (Algo.slideDown(grid).size() > 0) {
                        Optional<GridSpot> newSpot = grid.getRandomFreeSpot();
                        if (newSpot.isPresent()) {
                            grid.addCell(new Cell(newSpot.get(), Cell.generateRandomRank()));
                        } else {
                            System.out.println("GAME ENDED!!!");
                        }
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (Algo.slideLeft(grid).size() > 0) {
                        Optional<GridSpot> newSpot = grid.getRandomFreeSpot();
                        if (newSpot.isPresent()) {
                            grid.addCell(new Cell(newSpot.get(), Cell.generateRandomRank()));
                        } else {
                            System.out.println("GAME ENDED!!!");
                        }
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (Algo.slideRight(grid).size() > 0) {
                        Optional<GridSpot> newSpot = grid.getRandomFreeSpot();
                        if (newSpot.isPresent()) {
                            grid.addCell(new Cell(newSpot.get(), Cell.generateRandomRank()));
                        } else {
                            System.out.println("GAME ENDED!!!");
                        }
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    System.out.println(grid);
                }
            }
        };
    }

    public Game2048Draw(int width, int height) {
        this.timer = new Timer(REFRESH_RATE, this);
        this.timer.start();
        this.setBackground(new Color(90, 90, 90));
        this.initGrid(width, height);
        this.isMoving = false;
    }

    private void initGrid(int width, int height) {
        this.grid = new Grid<Cell>(width, height);
        this.grid.addCell(new Cell(grid.getRandomFreeSpot().get(), Cell.generateRandomStartingRank()));
        this.grid.addCell(new Cell(grid.getRandomFreeSpot().get(), Cell.generateRandomStartingRank()));
    }

    public Timer getTimer() {
        return timer;
    }

    private void draw(Graphics2D g) {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHints(rh);

        this.grid.streamCells().forEach(cell -> {
            drawCell(g, cell);
        })
        ;
    }

    private Coords getCellCoordsFromIndex(int lineIndex, int colIndex) {
        return new Coords(colIndex * cellLength, lineIndex * cellLength);
    }

    private void drawCell(Graphics2D g, Cell cell) {
        final int fontSize = Math.round(cellLength * 0.350f);
        final int cellMargin = Math.round(cellLength * 0.04f);
        final int cellRadius = Math.round(cellLength * 0.10f);

        ColoredValue cellParams = ColoredValue.values()[Math.min(15, cell.rank - 1)];
        Coords cellCoords = this.getCellCoordsFromIndex(cell.lineIndex, cell.columnIndex);

        g.setColor(cellParams.bgColor);
        final RoundRectangle2D roundedRect = new RoundRectangle2D.Double(
                cellCoords.x + cellMargin,
                cellCoords.y + cellMargin,
                cellLength - cellMargin * 2,
                cellLength - cellMargin * 2,
                cellRadius,
                cellRadius
        );
        g.fill(roundedRect);

        GradientPaint gradient = new GradientPaint(
                cellCoords.x + cellMargin,
                cellCoords.y + cellMargin,
                new Color(255, 255, 255, 0),
                cellCoords.x + cellLength - 2 * cellMargin,
                cellCoords.y + cellLength - 2 * cellMargin,
                new Color(255, 255, 255, 30)
        );
        g.setPaint(gradient);
//                    g.setColor(new Color(255,255,255, 100));
        final RoundRectangle2D innerRect = new RoundRectangle2D.Double(
                cellCoords.x + cellMargin * 3,
                cellCoords.y + cellMargin * 3,
                cellLength - cellMargin * 6,
                cellLength - cellMargin * 6,
                cellRadius * 5,
                cellRadius * 5
        );
        g.fill(innerRect);

        Font font = new Font("Arial Rounded MT Bold", Font.PLAIN, fontSize);
        g.setColor(cellParams.fontColor);
        drawCenteredString(g, cell.getValue(), roundedRect, font);
    }

    private void drawCenteredString(Graphics2D g, String text, RoundRectangle2D rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        double x = rect.getX() + (rect.getWidth() - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        double y = rect.getY() + ((rect.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, Math.round(x), Math.round(y));
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.draw((Graphics2D) graphics);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private static class Coords {
        int x;
        int y;

        public Coords(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

