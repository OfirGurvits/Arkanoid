// id: 325655058 author: Ofir Gurvits

/**
 * The type Collision info.
 */
public class CollisionInfo {
    private Collidable collidable;
    private Point collisionPoint;

    /**
     * Instantiates a new Collision info.
     * @param collidable     the collidable
     * @param collisionPoint the collision point
     */
    public CollisionInfo(Collidable collidable, Point collisionPoint) {
        this.collisionPoint = collisionPoint;
        this.collidable = collidable;
    }

    /**
     * Collision point point.
     * @return the point
     */
// the point at which the collision occurs.
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * Collision object collidable.
     * @return the collidable
     */
// the collidable object involved in the collision.
    public Collidable collisionObject() {
        return collidable;
    }

    /**
     * Sets collision point.
     * @param collisionPoint the collision point
     */
    public void setCollisionPoint(Point collisionPoint) {
        this.collisionPoint = collisionPoint;
    }
}