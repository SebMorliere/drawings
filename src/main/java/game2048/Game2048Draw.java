package game2048;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static conf.Default.REFRESH_RATE;

public class Game2048Draw extends JPanel implements ActionListener {
    private Timer timer;
    private int animationDuration = 200; /*ms*/
    private Boolean isMoving;
    private Grid<Cell> grid;

    final int cellLength = Game2048Frame.cellPixellength;

    public Game2048Draw(int width, int height) {
        this.timer = new Timer(REFRESH_RATE, this);
        this.timer.start();
        this.setBackground(new Color(90, 90, 90));
        this.initGrid(width, height);
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println(">>> UP");
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    System.out.println(">>> DOWN");
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println(">>> LEFT");
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.out.println(">>> RIGHT");
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    System.out.println(grid);
                }
            }
        };
        this.addKeyListener(keyAdapter);
    }

    private void initGrid(int width, int height) {
        this.isMoving = false;
        this.grid = new Grid<Cell>(width, height);
        this.grid.streamIndex().forEach(idx -> {
            if (idx > 11) {
                this.grid.setCell(idx, new Cell(ColoredValue.RANK_6));

            } else if (idx > 7) {
                this.grid.setCell(idx, new Cell(ColoredValue.RANK_4));

            } else if (idx > 3) {
                this.grid.setCell(idx, new Cell(ColoredValue.RANK_2));
            } else {
                this.grid.setCell(idx, new Cell(ColoredValue.RANK_1));
            }
        });
    }

    public Timer getTimer() {
        return timer;
    }

    private void draw(Graphics2D g) {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHints(rh);
        int fontSize = Math.round(Game2048Frame.cellPixellength * 0.40f);
        int cellMargin = Math.round(Game2048Frame.cellPixellength * 0.04f);
        int cellRadius = Math.round(Game2048Frame.cellPixellength * 0.10f);

        IntStream.range(0, this.grid.height).forEach(lineIndex -> {
            IntStream.range(0, this.grid.width).forEach(colIndex -> {
                final Coords coords = getCellPosition(lineIndex, colIndex);
                this.grid.getCell(lineIndex, colIndex)
                        .ifPresent(value -> drawCell(g, value, coords));
            });
        });
    }

    private Coords getCellPosition(int lineIndex, int colIndex) {
        return new Coords(colIndex * cellLength, lineIndex * cellLength);
    }

    private void drawCell(Graphics2D g, Cell cell, Coords coords) {
        final int fontSize = Math.round(cellLength * 0.350f);
        final int cellMargin = Math.round(cellLength * 0.04f);
        final int cellRadius = Math.round(cellLength * 0.10f);


        g.setColor(cell.params.bgColor);
        final RoundRectangle2D roundedRect = new RoundRectangle2D.Double(
                coords.x + cellMargin,
                coords.y + cellMargin,
                cellLength - cellMargin * 2,
                cellLength - cellMargin * 2,
                cellRadius,
                cellRadius
        );
        g.fill(roundedRect);
        GradientPaint gradient = new GradientPaint(
                coords.x + cellMargin,
                coords.y + cellMargin,
                new Color(255,255,255,0),
                coords.x + cellLength - 2*cellMargin,
                coords.y + cellLength - 2*cellMargin,
                new Color(255,255,255,30)
        );
        g.setPaint(gradient);
//                    g.setColor(new Color(255,255,255, 100));
        final RoundRectangle2D innerRect = new RoundRectangle2D.Double(
                coords.x + cellMargin * 3,
                coords.y + cellMargin * 3,
                cellLength - cellMargin * 6,
                cellLength - cellMargin * 6,
                cellRadius * 5,
                cellRadius * 5
        );
        g.fill(innerRect);

        Font font = new Font("Arial Rounded MT Bold", Font.PLAIN, fontSize);
        g.setColor(cell.params.fontColor);
        drawCenteredString(g, cell.params.value.toString(), roundedRect, font);
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

