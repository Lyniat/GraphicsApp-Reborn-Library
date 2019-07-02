package de.ur.mi.geom;

/**
 * The Point class represents a point on the drawing area.
 */
public class Point {

    private double x;
    private double y;

    /**
     * Creates a new object of class point at 0, 0.
     */
    public Point() {
        x = 0;
        y = 0;
    }

    /**
     * Constructs a new object of class point at the specified position.
     *
     * @param x
     *            The x-position of the point
     * @param y
     *            The y-position of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of the point.
     *
     * @return The x-coordinate of the point
     *
     * */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the point.
     *
     * @return The y-coordinate of the point
     *
     * */
    public double getY() {
        return y;
    }

    /**
     * Returns a new point whose coordinates are the same as this one.
     *
     * @return A new point whose coordinates are the same as this one
     *
     * */
    public Point getPosition() {
        return new Point(x, y);
    }

    /**
     * Sets the position of the point to the specified position.
     *
     * @param x
     *            The x position of the point
     * @param y
     *            The y position of the point
     * */
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the position of the point to the position of a new point object.
     *
     * @param point
     *            The point which contains the new position information
     * */
    public void setLocation(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

}