// id: 325655058 name: Ofir Gurvits

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * this class represents a line.
 * author: Ofir Gurvits
 */
public class Line implements Sprite {
    private static final double EPSILON = Math.pow(10, -10);
    private final Point start;
    private final Point end;
    private final Point origin;
    private Color color = Color.black;

    /**
     * constructor that constructs line with points.
     *
     * @param start the "first" point
     * @param end   the "last" point
     */
    public Line(Point start, Point end) {
        this.origin = start;
        if (end.getX() < start.getX()) {
            this.start = end;
            this.end = start;
        } else {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * compares Doubles and considers potential computer errors.
     *
     * @param one the first Double
     * @param two the other Double
     * @return wether the doubles are equal or not
     */
    private boolean compareDoubles(Double one, Double two) {
        //those ifs deal with null values
        if (one == null && two == null) {
            return true;
        }
        if (one == null || two == null) {
            return false;
        }
        //returns weather the doubles are in an epsilon environment within each other
        return (Math.abs(one - two) < EPSILON);
    }

    /**
     * initializes a line with the given parameters.
     *
     * @param x1 the x value of the first point
     * @param y1 the y value of the first point
     * @param x2 the x value of the second point
     * @param y2 the y value of the second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point start = new Point(x1, y1);
        this.origin = start;
        Point end = new Point(x2, y2);
        if (end.getX() < start.getX()) {
            this.start = end;
            this.end = start;
        } else {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * returns the "end of the line".
     *
     * @return the pont classified as the end point
     */
    public Point end() {
        return end;
    }

    /**
     * returns the appropriate parameter.
     *
     * @return returns the point classified as a starting point.
     */
    public Point start() {
        return start;
    }

    /**
     * calculates the middle point between the start and the end point of the line then returns it.
     *
     * @return the average value of the end and start points.
     */
    public Point middle() {
        double x = (this.end.getX() + this.start.getX()) / 2;
        double y = (this.end.getY() + this.start.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * calculates the slope of the line and if the slope is infinite returns null.
     *
     * @return slope double
     */
    public Double slope() {
        if (this.end.getX() == this.start.getX()) {
            return null;
        }
        return ((this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX()));
    }

    /**
     * calculates the constant in the linear equation of the line segment.
     *
     * @return constant double
     */
    public double findConst() {
        if (slope() != null) {
            return (-1 * this.start.getX() * this.slope()) + this.start.getY();
        }
        return this.start.getX();
    }

    /**
     * checks if there is an intersection point and if there is then it calculates it.
     *
     * @param other the line with which the intersection is checked
     * @return returns the intersection point if there is one or null otherwise
     */
    public Point findIntersection(Line other) {
        //if the lines intersect at the edges then the edge is returned
        if (this.end.equals(other.start)) {
            return this.end;
        }
        if (this.start.equals(other.end)) {
            return this.start;
        }
        //if the lines are parallel then null is returned as there is no defined intersection
        if (compareDoubles(this.slope(), other.slope())) {
            return null;
        }
        //if one of the lines has an infinite slope then the intersection is calculated
        if (this.slope() == null) {
            double y = other.slope() * this.start.getX() + other.findConst();
            Point intersection = new Point(this.start.getX(), y);
            if (this.isPointOn(intersection) && other.isPointOn(intersection)) {
                return intersection;
            } else {
                return null;

            }
        }
        if (other.slope() == null) {
            double y = this.slope() * other.start.getX() + this.findConst();
            Point intersection = new Point(other.start.getX(), y);
            if (this.isPointOn(intersection) && other.isPointOn(intersection)) {
                return intersection;
            } else {
                return null;

            }
        }
        //the intersection is defined and can be calculated normally as both lines have defined slopes
        double x = (this.findConst() - other.findConst()) / (other.slope() - this.slope());
        double y = x * this.slope() + this.findConst();
        Point intersection = new Point(x, y);
        //if the intersection of the lines is on both segments then it is returned
        if (this.isPointOn(intersection) && other.isPointOn(intersection)) {
            return intersection;
        }
        return null;
    }

    /**
     * checks if both points are exactly the same.
     *
     * @param other the other line being compared
     * @return since the start of each line is always the smaller point it is enough to compare the starts and ends.
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end);
    }

    /**
     * checks if the given collinear point is on the segment.
     *
     * @param point the given point.
     * @return weather the point is on the line.
     */
    public boolean isPointOn(Point point) {
        if (point == null) {
            return false;
        }
        return (point.getX() <= this.end.getX() + EPSILON && point.getX() >= this.start.getX() - EPSILON)
                && (point.getY() >= Math.min(this.end.getY(), this.start.getY())
                && point.getY() <= Math.max(this.end.getY(), this.start.getY()));
    }

    /**
     * checks if the lines are intersecting.
     *
     * @param other the other line which is compared
     * @return whether the lines intersect or not
     */
    public boolean isIntersecting(Line other) {
        //each line intersects with itself
        if (equals(other)) {
            return true;
        }
        //if the lines intersect at the edges then returns true
        if (this.end.equals(other.start)) {
            return true;
        }
        if (this.start.equals(other.end)) {
            return true;
        }
        //if both of the slopes are undefined then false is returned
        if (this.slope() == null && other.slope() == null) {
            return false;
        }
        //calculates the intersection if one of the slopes is defined and checks if it on the segments
        if (this.slope() == null && (this.start.getX() <= other.end.getX()
                && this.start.getX() >= other.start.getX())) {
            return this.isPointOn(findIntersection(other));
        }

        if (other.slope() == null && (other.start.getX() <= this.end.getX()
                && other.start.getX() >= this.start.getX())) {
            return this.isPointOn(findIntersection(other));
        }
        //if the slopes are equal then there is no defined intersection
        if (compareDoubles(this.slope(), other.slope()) && (this.findConst() != other.findConst())) {
            return false;
        }
        //if the lines are on top of each other then they intersect
        if (compareDoubles(this.slope(), other.slope())
                && (this.isPointOn(other.start) || this.isPointOn(other.end))) {
            return true;
        }
        //checks if the intersection of the lines is on the segments
        Point intersection = this.findIntersection(other);
        return this.isPointOn(intersection) && other.isPointOn(intersection);
    }

    /**
     * returns the intersection if it exists otherwise returns null.
     *
     * @param other the other line
     * @return the intersection if it exists otherwise returns null
     */
    public Point intersectionWith(Line other) {
        if (!isIntersecting(other)) {
            return null;
        }
        return this.findIntersection(other);
    }

    /**
     * finds the closest intersection between the line and rectangle.
     *
     * @param rect the rectangle
     * @return If this line does not intersect with the rectangle, return null. Otherwise, return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        Point minimal = intersectionPoints.get(0);
        while (!intersectionPoints.isEmpty()) {
            if (minimal.distance(this.origin) > intersectionPoints.get(0).distance(this.origin)) {
                minimal = intersectionPoints.get(0);
            }
            intersectionPoints.remove(0);
        }
        return minimal;
    }

    /**
     * calculates the length of a line and returns it.
     *
     * @return distance between start and end
     */
    public double length() {
        return end().distance(start());
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end().getY());
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }
}