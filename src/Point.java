// id: 325655058 author: Ofir Gurvits

/**
 * this class represents a point.
 * author: Ofir Gurvits
 */
public class Point {
    private static final double EPSILON = Math.pow(10, -10);
    private double x;
    private double y;

    /**
     * constructs a point.
     * @param x value of point
     * @param y value of point
     */

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * returns x value of point.
     * @return x val
     */
    public double getX() {
        return x;
    }

    /**
     * returns y value of point.
     * @return y val
     */
    public double getY() {
        return y;
    }

    /**
     * sets the x value to the value given.
     * @param x the value given
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * sets the y value to the value given.
     * @param y the value given
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * compares the x and y values of this point and other given point.
     * @param other the other point being compared
     * @return returns true of both values are the same, returns false otherwise.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }

        return Math.abs(this.x - other.getX()) <= EPSILON && Math.abs(this.y - other.getY()) <= EPSILON;
    }

    /**
     * calculates the distance between this point and another point.
     * @param other the other point being compared
     * @return the distance between the 2 points
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }
}
