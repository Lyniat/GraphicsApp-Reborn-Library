package de.ur.mi.graphics;

import de.ur.mi.geom.Point;

/**
 * The Ellipse class is a graphical object whose appearance consists of an
 * ellipse.
 */
public class Ellipse extends GraphicsObject implements Scalable, Resizable {

    /**
     * Constructs a new ellipse with the specified width and height, with its
     * center positioned at the x and y coordinates.
     *
     * @param x
     *            The x-position of the ellipse in pixels
     * @param y
     *            The y-position of the ellipse in pixels
     * @param width
     *            The width of the ellipse in pixels
     * @param height
     *            The height of the ellipse in pixels
     * @param color
     *            The background color for the ellipse
     */

    public Ellipse(double x, double y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    /**
     * Constructs a new ellipse with the specified width and height, with its
     * center positioned at the origin (0,0).
     *
     * @param width
     *            The width of the ellipse in pixels
     * @param height
     *            The height of the ellipse in pixels
     * @param color
     *            The background color for the ellipse
     */

    public Ellipse(int width, int height, Color color) {
        super(width, height, color);
    }

    /**
     * Constructs a new ellipse with the specified width and height, with its
     * center positioned at the given point.
     *
     * @param point
     *            a point representing the top-left corner of the rectangle
     * @param width
     *            The width of the rectangle in pixels
     * @param height
     *            The height of the rectangle in pixels
     * @param color
     *            The background color for the rectangle
     */
    public Ellipse(Point point, int width, int height, Color color) {
        super(point.getX(), point.getY(), width, height, color);
    }

    @Override
    public void draw() {
        super.draw();
        if(!app.rendering){
            return;
        }
        //app.ellipse((float) x, (float) y, (float) width, (float) height);
        app.shapeRenderer.setColor(color.convertColor());
        app.shapeRenderer.ellipse((float)(x-width/2f),(float)(app.getHeight()-y-height/2f),(float)width,(float)height);
    }

    @Override
    public double getLeftBorder() {
        return super.getX() - (getWidth() * 0.5);
    }

    @Override
    public double getRightBorder() {
        return super.getX() + (super.getWidth() * 0.5);
    }

    @Override
    public double getTopBorder() {
        return super.getY() - (getHeight() * 0.5);
    }

    @Override
    public double getBottomBorder() {
        return super.getY() + (super.getHeight() * 0.5);
    }

    @Override
    public void scale(double sx, double sy) {
        width *= sx;
        height *= sy;
    }

    @Override
    public void scale(double sf) {
        scale(sf, sf);
    }

    @Override
    public void setBounds(double x, double y, double width, double height) {
        this.width = width;
        this.height = height;
        setPosition(x, y);
    }

    @Override
    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean hitTest(double x, double y) {
        double normX = x - getX();
        double normY = y - getY();
        double radiusX = getWidth() * 0.5;
        double radiusY = getHeight() * 0.5;

        return (((normX * normX) / (radiusX * radiusX)) + ((normY * normY) / (radiusY * radiusY))) <= 1.0;
    }

}