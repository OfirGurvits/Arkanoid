// id: 325655058 author: Ofir Gurvits

/**
 * this class represents velocity with 2 vectors each representing the change in x and y axis.
 */
public class Velocity {
    private double dx;
    private double dy;

    // constructor

    /**
     * constructs a velocity with the given parameters.
     * @param dx x-axis velocity
     * @param dy y-axis velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    //setters

    /**
     * sets the speed on the x-axis.
     * @param dx the new movement on the x-axis.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * sets the speed on the y-axis.
     * @param dy the new movement on the y-axis.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    //getters

    /**
     * returns the speed in the x-axis.
     * @return speed in the x-axis.
     */
    public double getDx() {
        return dx;
    }

    /**
     * returns the speed in the y-axis.
     * @return speed in the y-axis.
     */
    public double getDy() {
        return dy;
    }

    /**
     * converts the degree to radians and then multiplies the x value by the sin of the angle
     * and the y value by the cos of the angle.
     * @param angle the degree of the speed vector
     * @param speed the size of the speed vector
     * @return returns the velocity value split into x and y axis.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radian = Math.toRadians(angle);
        double dx = Math.sin(radian) * speed;
        double dy = Math.cos(radian) * speed * -1;
        return new Velocity(dx, dy);
    }

    /**
     * moves the given point based on the given velocity.
     * @param p the point with the x,y positions
     * @return the new point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}