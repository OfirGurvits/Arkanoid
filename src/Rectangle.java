// id: 325655058 author: Ofir Gurvits

import java.util.ArrayList;

/**
 * The type Rectangle.
 */
public class Rectangle {
    private Point upperleft;
    private double width;
    private double height;

    /**
     * Instantiates a new Rectangle.
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperleft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Instantiates a new Rectangle.
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     */
    public Rectangle(double x, double y, double width, double height) {
        this(new Point(x, y), width, height);
    }

    /**
     * calculates all the intersection points between a given line and the shape.
     * @param line the line
     * @return Return a (possibly empty) List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        //the lines representing the borders of the shape are calculated
        Line top = new Line(upperleft.getX(), upperleft.getY(), upperleft.getX() + width, upperleft.getY());
        Line leftBorder = new Line(upperleft.getX(), upperleft.getY(),
                upperleft.getX(), upperleft.getY() + height);
        Line rightBorder = new Line(upperleft.getX() + width, upperleft.getY(),
                upperleft.getX() + width, upperleft.getY() + height);
        Line bottom = new Line(upperleft.getX(), upperleft.getY() + height,
                upperleft.getX() + width, upperleft.getY() + height);
        //an empty list is created
        java.util.List<Point> intersections = new ArrayList<>();
        //if an intersection is found between the given line and the appropriate segment it is added to the list
        if (top.findIntersection(line) != null) {
            intersections.add(top.findIntersection(line));
        }
        if (leftBorder.findIntersection(line) != null) {
            intersections.add(leftBorder.findIntersection(line));
        }
        if (rightBorder.findIntersection(line) != null) {
            intersections.add(rightBorder.findIntersection(line));
        }
        if (bottom.findIntersection(line) != null) {
            intersections.add(bottom.findIntersection(line));
        }
        return intersections;
    }

    /**
     * Gets width.
     * @return the width
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return width;
    }

    /**
     * Gets height.
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets upper left.
     * @return the upper left
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return upperleft;
    }

    /**
     * Point in rectangle boolean.
     * @param point the point
     * @return if the point is in the shape
     */
    public boolean pointInRectangle(Point point) {
        return (point.getX() >= upperleft.getX() && point.getX() <= upperleft.getX() + width)
                && (point.getY() >= upperleft.getY() && point.getY() <= upperleft.getY() + height);
    }
}