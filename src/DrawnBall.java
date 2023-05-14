// id: 325655058 author: Ofir Gurvits

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Drawn ball.
 */
public class DrawnBall implements Sprite {
    private int x;
    private int y;
    private int r;
    private Color color;
    private boolean full;

    /**
     * Instantiates a new Drawn ball.
     *
     * @param x     the x
     * @param y     the y
     * @param r     the r
     * @param color the color
     * @param full  the full
     */
    public DrawnBall(int x, int y, int r, java.awt.Color color, boolean full) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.color = color;
        this.full = full;
    }

    /**
     * Instantiates a new Drawn ball.
     *
     * @param center the center
     * @param r      the r
     * @param color  the color
     * @param full   the full
     */
    public DrawnBall(Point center, int r, Color color, boolean full) {
        this.x = (int) center.getX();
        this.y = (int) center.getY();
        this.r = r;
        this.color = color;
        this.full = full;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        if (full) {
            d.fillCircle(x, y, r);
        } else {
            d.drawCircle(x, y, r);
        }

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
