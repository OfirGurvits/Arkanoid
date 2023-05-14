// id: 325655058 author: Ofir Gurvits

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    //the movement speed of the paddle
    private int movement;
    private final biuoop.KeyboardSensor keyboard;
    private Rectangle shape;
    private static final int WIDTH = 800;
    private int width;
    private static final int HEIGHT = 30;
    //the default velocity of the ball
    private static final int VELOCITY = 10;
    private static final int WIDTH_OF_BORDER = GameLevel.WIDTH / 16;

    /**
     * Instantiates a new Paddle.
     *
     * @param gui    the gui
     * @param startX the start x
     * @param startY the start y
     * @param width  the width
     */
    public Paddle(GUI gui, int startX, int startY, int width) {
        this.width = width;
        this.movement = 10;
        this.shape = new Rectangle(startX, startY, WIDTH, HEIGHT);
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * Instantiates a new Paddle.
     *
     * @param keyboardSensor the keyboard sensor
     * @param startX         the start x
     * @param startY         the start y
     * @param movement       the movement
     * @param width          the width
     */
    public Paddle(KeyboardSensor keyboardSensor, int startX, int startY, int movement, int width) {
        this.width = width;
        this.movement = movement;
        this.shape = new Rectangle((double) WIDTH / 2 - (double) width / 2, startY, width, HEIGHT);
        this.keyboard = keyboardSensor;
    }


    /**
     * Move the paddle to the left.
     */
    public void moveLeft() {
        shape = new Rectangle(shape.getUpperLeft().getX() - movement, shape.getUpperLeft().getY(),
                shape.getWidth(), shape.getHeight());
        if (GameLevel.UPPER_LEFT.getX() + WIDTH_OF_BORDER > shape.getUpperLeft().getX()) {
            shape.getUpperLeft().setX(GameLevel.UPPER_LEFT.getX() + WIDTH_OF_BORDER);
        }
    }

    /**
     * Move the paddle to the right.
     */
    public void moveRight() {
        shape = new Rectangle(shape.getUpperLeft().getX() + movement, shape.getUpperLeft().getY(),
                shape.getWidth(), shape.getHeight());
        if (shape.getUpperLeft().getX() + shape.getWidth() > GameLevel.WIDTH - WIDTH_OF_BORDER) {
            this.shape.getUpperLeft().setX(GameLevel.UPPER_LEFT.getX()
                    + GameLevel.WIDTH - WIDTH_OF_BORDER - shape.getWidth());
        }
    }

    // Sprite
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(155, 255, 255));
        d.fillRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //if there hasnt been a collision return the same speed
        if (collisionPoint == null) {
            return currentVelocity;
        }
        //otherwise return the speed appropriate to the segment (the first if is for segment one etc..)
        if (collisionPoint.getX() >= shape.getUpperLeft().getX()
                && collisionPoint.getX() <= shape.getUpperLeft().getX() + width * 0.2) {
            return Velocity.fromAngleAndSpeed(300, VELOCITY);
        }
        if (collisionPoint.getX() > shape.getUpperLeft().getX() + width * 0.2
                && collisionPoint.getX() <= shape.getUpperLeft().getX() + width * 0.4) {
            return Velocity.fromAngleAndSpeed(330, VELOCITY);
        }
        if (collisionPoint.getX() > shape.getUpperLeft().getX() + width * 0.4
                && collisionPoint.getX() <= shape.getUpperLeft().getX() + width * 0.6) {
            return (new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1));
        }
        if (collisionPoint.getX() > shape.getUpperLeft().getX() + width * 0.6
                && collisionPoint.getX() <= shape.getUpperLeft().getX() + width * 0.8) {
            return Velocity.fromAngleAndSpeed(30, VELOCITY);
        }
        if (collisionPoint.getX() > shape.getUpperLeft().getX() + width * 0.8
                && collisionPoint.getX() <= shape.getUpperLeft().getX() + width) {
            return Velocity.fromAngleAndSpeed(60, VELOCITY);
        }
        return currentVelocity;
    }

    // Add this paddle to the game.
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    @Override
    public boolean ballInCollidable(Ball ball) {
        return shape.pointInRectangle(ball.getCenter());
    }
}