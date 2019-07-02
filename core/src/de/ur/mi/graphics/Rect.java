package de.ur.mi.graphics;

import de.ur.mi.geom.Point;

/**
 * The Rect class is a graphical object whose appearance consists of a
 * rectangular box.
 */

public class Rect extends GraphicsObject implements Scalable, Resizable {

    /**
     * Constructs a new rectangle with the specified width and height,
     * positioned at the x and y coordinates.
     *
     * @param x
     *            The x-position of the upper-left corner of the rectangle
     * @param y
     *            The y-position of the upper-left corner of the rectangle
     * @param width
     *            The width of the rectangle in pixels
     * @param height
     *            The height of the rectangle in pixels
     * @param color
     *            The background color for the rectangle
     */
    public Rect(double x, double y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    /**
     * Constructs a new rectangle with the specified width and height,
     * positioned at the the given point.
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
    public Rect(Point point, int width, int height, Color color) {
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
        app.shapeRenderer.rect((float)x,(float)(app.getHeight()-y-height),(float)width,(float)height);
    }

    @Override
    public void scale(double sx, double sy) {
        width *= sx;
        height *= sy;
    }

    @Override
    public final void scale(double sf) {
        scale(sf, sf);
    }

    @Override
    public void setBounds(double x, double y, double width, double height) {
        this.width = width;
        this.height = height;
        setPosition(x, y);
    }

    /*
    @Override
    public void setBounds(Rect bounds) {
        setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(),
                bounds.getHeight());
    }
    */

    @Override
    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

}
