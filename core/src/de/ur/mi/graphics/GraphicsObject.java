package de.ur.mi.graphics;

import de.ur.mi.graphicsapp.GraphicsApp;
import de.ur.mi.geom.Point;

/**
 * This class is the common superclass of all graphical objects that can be
 * displayed on a GraphicsApp. Because it is an abstract class, you are not
 * allowed to construct an object whose class is GraphicsObject directly. What
 * you do instead is construct one of the concrete subclasses like Rect or Line.
 * The purpose of this class definition is to define methods that apply to all
 * graphical objects regardless of their specific class.
 */
public abstract class GraphicsObject{

    public static int DEFAULT_WIDTH = 100;
    public static int DEFAULT_HEIGHT = 100;

    public static GraphicsApp app;

    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected double borderWeight;
    protected Color borderColor;
    protected Color color;
    protected boolean mousePressed;

    public GraphicsObject(double x, double y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        borderColor = Color.BLACK;
        borderWeight = 0;

        //null check needed for unit tests
        //if(app != null){
            app.addObject(this);
        //}
    }

    public GraphicsObject(Point point, int width, int height, Color color) {
        this(point.getX(), point.getY(), color);
    }

    public GraphicsObject(double x, double y, Color color) {
        this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, color);
    }

    public GraphicsObject(double x, double y) {
        this(x, y, DEFAULT_WIDTH, DEFAULT_WIDTH, Color.BLACK);
    }

    public GraphicsObject(Point point, Color color) {
        this(point.getX(), point.getY(), color);
    }

    public GraphicsObject(Point point) {
        this(point.getX(), point.getY());
    }

    /**
     * Returns the x-coordinate of the object.
     *
     * @return the x-coordinate of the object
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the object.
     *
     * @return The y-coordinate of the object.
     */
    public double getY() {
        return y;
    }

    /**
     * Returns the width of the object.
     *
     * @return The width of the object
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the object.
     *
     * @return The height of the object
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the top edge of the object.
     *
     * @return The top edge of the object
     */
    public double getTopBorder() {
        return getY();
    }

    /**
     * Returns the bottom edge of the object.
     *
     * @return The bottom edge of the object
     */
    public double getBottomBorder() {
        return getY() + getHeight();
    }

    /**
     * Returns the left edge of the object.
     *
     * @return The left edge of the object
     */
    public double getLeftBorder() {
        return getX();
    }

    /**
     * Returns the right edge of the object.
     *
     * @return The right edge of the object
     */
    public double getRightBorder() {
        return getX() + getWidth();
    }

    /**
     * Returns the color of the object.
     *
     * @return The color of the object.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the border color of the object.
     *
     * @return The border color of the object
     */
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * Returns the border weight of the object.
     *
     * @return The border weight of the object
     */
    public double getBorderWeight() {
        return borderWeight;
    }

    /**
     * Sets the x-coordinate of the object.
     *
     * @param x
     *            The x position of the object
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the object.
     *
     * @param y
     *            The y position of the object
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Returns the position as point object.
     *
     * @return The position of the object as point
     */
    public Point getPosition() {
        return new Point(getX(), getY());
    }

    /**
     * Sets the position of the object to the specified coordinates.
     *
     * @param x
     *            The x position of the object
     * @param y
     *            The y position of the object
     */
    public void setPosition(double x, double y) {
        setX(x);
        setY(y);
    }

    /**
     * Sets the position of the object to the specified point.
     *
     * @param point
     *            a point representing the new position of the object
     */
    public void setPosition(Point point) {
        setX(point.getX());
        setY(point.getY());
    }

    /**
     * Sets the color of the object to the specified color.
     *
     * @param color
     *            The new fill color of the object
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the color of the object to the specified rgb values.
     *
     * @param red
     *            the red value of the new color (0-255)
     * @param green
     *            the green value of the new color (0-255)
     * @param blue
     *            the blue value of the new color (0-255)
     */
    public void setColor(int red, int green, int blue) {
        this.color = new Color(red, green, blue);
    }

    /**
     * Sets the color and the weight of border.
     *
     * @param color
     *            The new stroke color of the object
     * @param weight
     *            The new stroke weight of the object
     */
    public void setBorder(Color color, double weight) {
        setBorderColor(color);
        setBorderWeight(weight);
    }

    /**
     * Sets the border color of the object.
     *
     * @param color
     *            The new border color of the object
     */
    public void setBorderColor(Color color) {
        borderColor = color;
    }

    /**
     * Sets the border weight of the object.
     *
     * @param weight
     *            The new stroke weight of the object
     */
    public void setBorderWeight(double weight) {
        borderWeight = weight;
    }

    /**
     * Moves the object on the screen using the displacements dx and dy.
     *
     * @param dx
     *            The horizontal change of position
     * @param dy
     *            The vertical change of position
     *
     */
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    /**
     * Returns the distance to another GraphicsObject
     *
     * @param object
     *            the other GraphicsObject
     * @return the distance between this object and the given object.
     */
    public double distanceTo(GraphicsObject object) {
        double dx = object.getX() - getX();
        double dy = object.getY() - getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Draws the GraphicsObject to the canvas.
     */
    public void draw() {
        if(!app.rendering) {
            app.addObject(this);
        }
    }

    /**
     * Checks to see whether this objects contains the given coordinates.
     *
     * @param x
     *            The x position to test
     * @param y
     *            The y position to test
     */
    public boolean hitTest(double x, double y) {
        return ((x >= getLeftBorder()) && (x <= getRightBorder())
                && (y >= getTopBorder()) && (y <= getBottomBorder()));
    }

}