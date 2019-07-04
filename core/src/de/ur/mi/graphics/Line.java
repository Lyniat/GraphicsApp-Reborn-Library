package de.ur.mi.graphics;

import de.ur.mi.geom.Point;

/**
 * The Line class is a graphical object whose appearance consists of a line
 * segment.
 */
public class Line extends GraphicsObject implements Scalable {

    private double xEndPoint;
    private double yEndPoint;

    private double size;

    /**
     * Constructs a new line from x and y to x2 and y2.
     *
     * @param x
     *            The x-position of the first point
     * @param y
     *            The y-position of the first point
     * @param x2
     *            The x-position of the end point
     * @param y2
     *            The y-position of the end point
     * @param color
     *            The stroke color for the line
     */

    public Line(double x, double y, double x2, double y2, Color color) {
        super(x, y, color);

        this.borderColor = color;
        this.xEndPoint = x2;
        this.yEndPoint = y2;

        size = 1;
    }

    /**
     * Constructs a new line from the given start point to given the end point.
     *
     * @param start
     *            the start point of the Line
     * @param end
     *            the end point of the Line
     * @param color
     *            The stroke color for the line
     */

    public Line(Point start, Point end, Color color) {
        this(start.getX(), start.getY(), end.getX(), end.getY(), color);
    }

    @Override
    public void draw() {
        super.draw();
        if(!app.rendering){
            return;
        }
        //app.ellipse((float) x, (float) y, (float) width, (float) height);
        app.shapeRenderer.setColor(color.convertColor());
        app.shapeRenderer.rectLine((float) x,(float)(app.getHeight()-y), (float) xEndPoint, (float) (app.getHeight()-yEndPoint),(int)size);

    }

    /**
     * Returns the x-position of the start point.
     *
     * @return The x-position of the start point
     */
    public double getStartpointX() {
        return super.getX();
    }

    /**
     * Returns the y-position of the start point.
     *
     * @return The y-position of the start point
     */
    public double getStartpointY() {
        return super.getY();
    }

    /**
     * Returns the x-position of the end point.
     *
     * @return The x-position of the end point
     */
    public double getEndpointX() {
        return xEndPoint;
    }

    /**
     * Returns the y-position of the end point.
     *
     * @return The y-position of the end point
     */
    public double getEndpointY() {
        return yEndPoint;
    }

    /**
     * Sets the x-position and y-position of the start point.
     *
     * @param xStartPoint
     *            The new x-position of the start point
     * @param yStartPoint
     *            The new y-position of the start point
     */
    public void setStartPoint(double xStartPoint, double yStartPoint) {
        super.setPosition(xStartPoint, yStartPoint);
    }

    /**
     * Sets the x-position and y-position of the start point to the given point.
     *
     * @param start
     *            a point representing the new start position of the line
     */
    public void setStartPoint(Point start) {
        setStartPoint(start.getX(), start.getY());
    }

    /**
     * Sets the x-position and y-position of the end point.
     *
     * @param xEndPoint
     *            The new x-position of the end point
     * @param yEndPoint
     *            The new y-position of the end point
     */
    public void setEndPoint(double xEndPoint, double yEndPoint) {
        this.xEndPoint = xEndPoint;
        this.yEndPoint = yEndPoint;
    }

    /**
     * Sets the x-position and y-position of the end point to the given point.
     *
     * @param end
     *            a point representing the new end position of the line
     */
    public void setEndPoint(Point end) {
        setEndPoint(end.getX(), end.getY());
    }

    /**
     * Sets the color of the line.
     *
     * @param newColor
     *            The new color of the line
     */
    public void setColor(Color newColor) {
        super.setColor(newColor);
        setBorderColor(newColor);
    }

    @Override
    public void move(double dx, double dy) {
        setStartPoint(super.getX() + dx, super.getX() + dy);
        setEndPoint(xEndPoint + dx, yEndPoint + dy);
    }

    @Override
    public void setPosition(double x, double y) {
        if (x < super.getX()) {
            if (super.getX() < xEndPoint) {
                xEndPoint = x + (xEndPoint - super.getX());
                super.setX(x);
            } else {
                super.setX(x + (super.getX() - xEndPoint));
                xEndPoint = x;
            }
        } else {
            if (super.getX() < xEndPoint) {
                xEndPoint = x + (xEndPoint - super.getX());
                super.setX(x);
            } else {
                super.setX(x + (super.getX() - xEndPoint));
                xEndPoint = x;
            }
        }

        if (y < super.getY()) {
            if (super.getY() < yEndPoint) {
                yEndPoint = y + (yEndPoint - super.getY());
                super.setY(y);
            } else {
                super.setY(y + (super.getY() - yEndPoint));
                yEndPoint = y;
            }
        } else {
            if (super.getY() < yEndPoint) {
                yEndPoint = y + (yEndPoint - super.getY());
                super.setY(y);
            } else {
                super.setY(y + (super.getY() - yEndPoint));
                yEndPoint = y;
            }
        }
    }

    @Override
    public void setPosition(Point p) {
        this.setPosition(p.getX(), p.getY());
    }

    /**
     * Moves the line to an absolute position.
     *
     * @param toStartPointX
     *            The new x-position of the start point
     * @param toStartPointY
     *            The new y-position of the start point
     * @param toEndPointX
     *            The new x-position of the end point
     * @param toEndPointY
     *            The new y-position of the end point
     */
    public void setDimension(double toStartPointX, double toStartPointY,
                             double toEndPointX, double toEndPointY) {

        setStartPoint(toStartPointX, toStartPointY);
        setEndPoint(toEndPointX, toEndPointY);
    }

    @Override
    public double getRightBorder() {
        return Math.max(super.getX(), xEndPoint);
    }

    @Override
    public double getLeftBorder() {
        return Math.min(super.getX(), xEndPoint);
    }

    @Override
    public double getTopBorder() {
        return Math.min(super.getY(), yEndPoint);
    }

    @Override
    public double getBottomBorder() {
        return Math.max(super.getY(), yEndPoint);
    }

    @Override
    public void scale(double sx, double sy) {
        xEndPoint *= sx;
        yEndPoint *= sy;
    }

    @Override
    public final void scale(double sf) {
        scale(sf, sf);
    }

    public void setSize(double size){
        this.size = size;
    }

}
