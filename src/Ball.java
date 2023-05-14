// id: 325655058 author: Ofir Gurvits

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * author: Ofir Gurvits
 * this class represents a ball.
 */
public class Ball implements Sprite {
    private static final int EPSILON = 5;
    private GameEnvironment gameEnvironment;
    private Velocity v = new Velocity(0, 0);
    private Point center;
    private final int r;
    private final java.awt.Color color;

    // constructor

    /**
     * initializes a ball with the parameters given.
     *
     * @param center          the center point of the Ball
     * @param r               the length of the radius
     * @param color           the color of the ball
     * @param gameEnvironment the game environment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.setVelocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Instantiates a new Ball.
     *
     * @param center the center
     * @param r      the r
     * @param color  the color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.setVelocity(0, 0);
    }
    public Ball(int x,int y, int r, java.awt.Color color) {
        this.center = new Point(x,y);
        this.r = r;
        this.color = color;
        this.setVelocity(0, 0);
    }


    /**
     * initializes a ball with the parameters given.
     *
     * @param x               the x value of the center point of the Ball
     * @param y               the y value of the center point of the Ball
     * @param r               the length of the radius
     * @param color           the color of the ball
     * @param gameEnvironment the game environment
     */
    public Ball(double x, double y, int r, Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.setVelocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x     the x
     * @param y     the y
     * @param r     the r
     * @param color the color
     */
    public Ball(double x, double y, int r, Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.setVelocity(0, 0);
    }


    /**
     * sets the value of the velocity to the value given.
     *
     * @param v the given velocity
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * sets the velocity with the parameters given.
     *
     * @param dx the speed in the x-axis
     * @param dy the speed in the y-axis
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    // accessors

    /**
     * returns parameter.
     *
     * @return the x coordinate
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * returns parameter.
     *
     * @return the y coordinate
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * returns parameter.
     *
     * @return the size of the radius.
     */
    public int getSize() {
        return r;
    }

    /**
     * returns parameter.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return v;
    }

    /**
     * draws the ball on the given DrawSurface.
     *
     * @param surface the surface being drawn on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(),this.getY(),this.getSize());
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    private Line findTrajectory() {
        return new Line(center, v.applyToPoint(center));
    }

    /**
     * Move one step.
     */
    public void moveOneStep() {
        Line trajectory = findTrajectory();
        CollisionInfo info = gameEnvironment.getClosestCollision(trajectory);
        //if no intersection is found then the ball is moved normally
        if (info == null) {
            center = v.applyToPoint(center);
        } else {
            //otherwise the intersection is found
            Point newCenter = new Point(info.collisionPoint().getX(), info.collisionPoint().getY());
            //the new movement is calculated
            v = info.collisionObject().hit(this, info.collisionPoint(), v);
            //if the ball is inside the obstacle it hit it then it is moved outside
            if (center.getY() > info.collisionPoint().getY()) {
                newCenter.setY(info.collisionPoint().getY() + EPSILON);
            } else if (center.getY() < info.collisionPoint().getY()) {
                newCenter.setY(info.collisionPoint().getY() - EPSILON);
            }
            if (center.getX() > info.collisionPoint().getX()) {
                newCenter.setX(info.collisionPoint().getX() + EPSILON);
            } else if (center.getX() < info.collisionPoint().getX()) {
                newCenter.setX(info.collisionPoint().getX() - EPSILON);
            }
            center = newCenter;
            //if the ball is stuck inside a obstacle then it is moved outside it
            while (gameEnvironment.isInObstacle(center)) {
                if (center.getY() < (double) GameLevel.FIRST_ROW_Y + GameLevel.HEIGHT_OF_BLOCK * 2
                        && center.getY() > GameLevel.UPPER_LEFT.getY() + GameLevel.HEIGHT_OF_BLOCK) {
                    center = new Point(center.getX(), center.getY() - EPSILON);
                } else if (center.getY() < (double) GameLevel.HEIGHT / 2) {
                    center = new Point(center.getX(), center.getY() + EPSILON);
                } else {
                    center = new Point(center.getX(), center.getY() - EPSILON);
                }
                if (center.getX() <= GameLevel.UPPER_LEFT.getX() + GameLevel.WIDTH_OF_BORDER) {
                    center = new Point(center.getX() + EPSILON, center.getY());
                }
                if (center.getX() >= GameLevel.UPPER_LEFT.getX() + GameLevel.WIDTH - GameLevel.WIDTH_OF_BORDER) {
                    center = new Point(center.getX() - EPSILON, center.getY());
                }
            }

        }
    }

    /**
     * returns the appropriate parameter.
     *
     * @return color color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets game environment.
     *
     * @param gameEnvironment the game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Gets center.
     *
     * @return the center
     */
    public Point getCenter() {
        return this.center;
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * removes the current ball from the game.
     * @param g the game from which the ball is removed
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

}