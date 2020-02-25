package game2048;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import static conf.Default.REFRESH_RATE;

public class Game2048Draw extends JPanel implements ActionListener {
    final int cellLength = Game2048Frame.cellPixellength;

    private Timer timer;
    private int animationDuration = 200; /*ms*/
    private Boolean isMoving;
    private Grid<Cell> grid;

    public KeyAdapter getKeyAdapter() {
        return new KeyAdapter() {
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
    }

    public Game2048Draw(int width, int height) {
        this.timer = new Timer(REFRESH_RATE, this);
        this.timer.start();
        this.setBackground(new Color(90, 90, 90));
        this.initGrid(width, height);
    }

    private void initGrid(int width, int height) {
        this.isMoving = false;
        this.grid = new Grid<Cell>(width, height);
        Random random = new Random();
        final int index1 = random.nextInt(this.grid.size());
        int index2 = random.nextInt(this.grid.size());
        while (index2 == index1) {
            index2 = random.nextInt(this.grid.size());
        }
        this.grid.setCell(index1, new Cell(random.nextInt(10) > 2 ? ColoredValue.RANK_1 : ColoredValue.RANK_2));
        this.grid.setCell(index2, new Cell(random.nextInt(10) > 2 ? ColoredValue.RANK_1 : ColoredValue.RANK_2));
    }

    public Timer getTimer() {
        return timer;
    }

    private void draw(Graphics2D g) {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHints(rh);

        IntStream.range(0, this.grid.height).forEach(lineIndex -> {
            IntStream.range(0, this.grid.width).forEach(colIndex -> {
                final Coords coords = getCellCoords(lineIndex, colIndex);
                this.grid.getCell(lineIndex, colIndex)
                        .ifPresent(value -> drawCell(g, value, coords));
            });
        });
    }

    private Coords getCellCoords(int lineIndex, int colIndex) {
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

