package game2048;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Optional;
import java.util.stream.IntStream;

import static conf.Default.REFRESH_RATE;

public class Game2048Draw extends JPanel implements ActionListener {
    private Timer timer;
    private int animationDuration = 200; /*ms*/
    private Boolean isMoving;
    private Grid<Cell> grid;

    public Game2048Draw(int width, int height) {
        this.timer = new Timer(REFRESH_RATE, this);
        this.timer.start();
        this.setBackground(Color.BLACK);
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
        System.out.println(this.grid.toString());
    }

    public Timer getTimer() {
        return timer;
    }

    private void draw(Graphics2D g) {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHints(rh);
        int fontSize = Math.round(Game2048Frame.cellPixellength * 0.40f);

        IntStream.range(0, this.grid.height).forEach(lineIndex -> {
            IntStream.range(0, this.grid.width).forEach(colIndex -> {
                Optional<Cell> cell = this.grid.getCell(lineIndex, colIndex);
                if (cell.isPresent()) {
                    g.setColor(cell.get().params.bgColor);
                    Rectangle rect = new Rectangle(
                            colIndex * Game2048Frame.cellPixellength,
                            lineIndex * Game2048Frame.cellPixellength,
                            Game2048Frame.cellPixellength, Game2048Frame.cellPixellength
                    );
                    g.fill(rect);
                    Font font = new Font("Arial", Font.PLAIN, fontSize);
                    g.setColor(cell.get().params.fontColor);
                    drawCenteredString(g, cell.get().params.value.toString(), rect, font);
                } else {
                    g.setColor(new Color(13, 52, 76));
                    g.fillRect(colIndex * Game2048Frame.cellPixellength, lineIndex * Game2048Frame.cellPixellength, Game2048Frame.cellPixellength, Game2048Frame.cellPixellength);
                }
            });
        });
    }

    private void drawCenteredString(Graphics2D g, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
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
}