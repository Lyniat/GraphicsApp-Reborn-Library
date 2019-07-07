package de.ur.mi.graphics;

import de.ur.mi.geom.Point;

/**
 * The Rect class is a graphical object whose appearance consists of a
 * rectangular box.
 */

public class Triangle extends GraphicsObject implements Scalable, Resizable {

    private int v1x;
    private int v1y;
    private int v2x;
    private int v2y;

    public Triangle(int x, int y, Point v1, Point v2, Color color){
        this(x,y,(int)v1.getX(),(int)v1.getY(),(int)v2.getX(),(int)v2.getY(),color);
    }

    public Triangle(Point v0, Point v1, Point v2, Color color){
        this((int)v0.getX(),(int)v0.getY(),(int)v1.getX(),(int)v1.getY(),(int)v2.getX(),(int)v2.getY(),color);
    }


    /**
     * Constructs a new rectangle with the specified width and height,
     * positioned at the x and y coordinates.
     *
     * @param x        The x-position of the upper-left corner of the rectangle
     * @param y        The y-position of the upper-left corner of the rectangle
     * @param //width  The width of the rectangle in pixels
     * @param //height The height of the rectangle in pixels
     * @param color    The background color for the rectangle
     */

    public Triangle(int x, int y, int v1x, int v1y, int v2x, int v2y, Color color) {

        super(x, y, color);

        this.x = x;
        this.y = y;
        this.v1x = v1x;
        this.v1y = v1y;
        this.v2x = v2x;
        this.v2y = v2y;


        double top = y;

        if (v1y < top) {
            top = v1y;
        }

        if (v2y < top) {
            top = v2y;
        }

        double bottom = y;

        if (v1y > bottom) {
            bottom = v1y;
        }

        if (v2y > bottom) {
            bottom = v2y;
        }

        double left = x;

        if (v1x < left) {
            left = v1x;
        }

        if (v2x < left) {
            left = v2x;
        }

        double right = x;

        if (v1x > right) {
            right = v1x;
        }

        if (v2x > right) {
            right = v2x;
        }

        height = bottom - top;
        width = right - left;

    }

    @Override
    public void draw() {
        super.draw();
    }

    public void render(){
        app.shapeRenderer.setColor(color.convertColor());
        app.shapeRenderer.triangle((float) x, (float) (app.getHeight() - y - height),
                (float) v1x, (float) (app.getHeight() - v1y - height),
                (float) v2x, (float) (app.getHeight() - v2y - height));
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
