// id: 325655058 author: Ofir Gurvits

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Game environment.
 */
public class GameEnvironment {
    private java.util.List<Collidable> obstacles;

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.obstacles = new ArrayList<Collidable>();
    }

    /**
     * Gets obstacles.
     * @return the obstacles
     */
    public List<Collidable> getObstacles() {
        return obstacles;
    }

    /**
     * Remove collidable.
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        obstacles.remove(c);
    }

    /**
     * Add collidable.
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        obstacles.add(c);
    }

    /**
     * If this object will not collide with any of the collidables returns null
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory
     * @return the collision info
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestIntersection = null;
        Collidable closestCollidable = null;
        boolean hasIntersected = false;
        //go over all the collidables
        for (Collidable collidable : obstacles) {
            //find the current intersection
            Point currentIntersection = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (currentIntersection != null) {
                hasIntersected = true;
                //if this is the first intersection set it to be the closest
                if (closestIntersection == null) {
                    closestIntersection = currentIntersection;
                    closestCollidable = collidable;
                } else {
                    //check which current intersection is closer than the first one and if it change the closest one
                    if (currentIntersection.distance(trajectory.start())
                            < closestIntersection.distance(trajectory.start())) {
                        closestIntersection = currentIntersection;
                        closestCollidable = collidable;
                    }
                }
            }
        }
        //return the info if there is an intersection point
        if (hasIntersected) {
            return new CollisionInfo(closestCollidable, closestIntersection);
        }
        return null;
    }

    /**
     * Draw obstacles.
     *
     * @param d the d
     */
    public void drawObstacles(DrawSurface d) {
        for (Collidable collidable : obstacles) {
            collidable.drawOn(d);
        }
    }

    /**
     * Is in obstacle boolean.
     * @param center the center
     * @return whether is in obstacle.
     */
    public boolean isInObstacle(Point center) {
        //go over all obstacles
        for (Collidable obstacle : obstacles) {
            //check if in current obstacle
            if (obstacle.getCollisionRectangle().pointInRectangle(center)) {
                return true;
            }
        }
        return false;
    }
}
