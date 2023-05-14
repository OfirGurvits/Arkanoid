// id: 325655058 author: Ofir Gurvits

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle shape;
    private Color color;
    private static final Color DEFAULT_COLOR = Color.gray;
    private static final Color DEFAULT_BORDER = Color.black;
    private List<HitListener> hitListeners = new ArrayList<>();


    /**
     * Instantiates a new Block.
     *
     * @param shape the shape
     * @param color the color
     */
    public Block(Rectangle shape, Color color) {
        this.shape = shape;
        this.color = color;
    }

    /**
     * Instantiates a new Block.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param color     the color
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.shape = (new Rectangle(upperLeft, width, height));
        this.color = color;
    }

    /**
     * Instantiates a new Block.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param color  the color
     */
    public Block(double x, double y, double width, double height, Color color) {
        this(new Point(x, y), width, height, color);
        this.color = color;
    }

    /**
     * Instantiates a new Block.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Block(Point upperLeft, int width, int height) {
        this.shape = new Rectangle(upperLeft, width, height);
        this.color = DEFAULT_COLOR;
    }

    /**
     * Instantiates a new Block.
     *
     * @param shape the shape
     */
    public Block(Rectangle shape) {
        this.shape = shape;
        this.color = DEFAULT_COLOR;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return shape;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //if there has been no intersection the speed isnt changed
        if (collisionPoint == null) {
            return currentVelocity;
        }
        this.notifyHit(hitter);
        //if the ball hits the left corner from the right then the velocity is set
        //if the ball hits the right corner from the left then the velocity is set
        //if there has been a hit on the y-axis then the velocity in this axis is changed
        if (collisionPoint.getY() == shape.getUpperLeft().getY() || collisionPoint.getY()
                == shape.getUpperLeft().getY() + shape.getHeight()) {

            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
        }
        //if there has been a hit on the x-axis then the velocity in this axis is changed
        if (collisionPoint.getX() == shape.getUpperLeft().getX()
                || collisionPoint.getX() == shape.getUpperLeft().getX() + shape.getWidth()) {
            return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
        }
        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
        d.setColor(DEFAULT_BORDER);
        d.drawRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * returns whether the ball is inside the given block.
     *
     * @param ball the ball
     * @return true or false
     */
    public boolean ballInCollidable(Ball ball) {
        return shape.pointInRectangle(ball.getCenter());
    }

    /**
     * removes this block from the game.
     * @param game the game from which the block is being removed
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
