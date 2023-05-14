// id: 325655058 author: Ofir Gurvits
import biuoop.DrawSurface;

/**
 * The interface for Collidables.
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     * @return the collision rectangle
     */
// Return the "collision shape" of the object.
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param hitter the ball that hits
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Add to a given game.
     *
     * @param game the game
     */
    void addToGame(GameLevel game);

    /**
     * Draw on the surface given.
     *
     * @param d the d
     */
    void drawOn(DrawSurface d);

    /**
     * notify collidable that time has passed.
     */
    void timePassed();

    /**
     * Ball in collidable boolean.
     *
     * @param ball the ball
     * @return the boolean
     */
    boolean ballInCollidable(Ball ball);
}
