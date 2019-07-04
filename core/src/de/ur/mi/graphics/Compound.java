package de.ur.mi.graphics;

import de.ur.mi.geom.Point;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class defines a graphical object that consists of a collection of other
 * graphical objects. Once assembled, the internal objects can be manipulated as
 * a unit.
 */
public class Compound extends GraphicsObject {

    private ArrayList<GraphicsObject> objects;

    /**
     * Creates a new Compound object with no internal components at the given
     * position.
     *
     * @param x
     *            the x-position in pixels
     * @param y
     *            the y-position in pixels
     */
    public Compound(double x, double y) {
        super(x, y, Color.TRANSPARENT);

        objects = new ArrayList<GraphicsObject>();
    }

    /**
     * Creates a new Compound object with no internal components at the given
     * point object.
     *
     * @param point
     *            the point representing the position of the Compound
     */
    public Compound(Point point) {
        this(point.getX(), point.getY());
    }

    /**
     * Creates a new Compound object with no internal components at point 0,0.
     */
    public Compound() {
        this(0, 0);
    }

    /**
     * Draws the Compound and all added objects.
     */
    public void draw() {
        super.draw();
        if(!app.rendering) {
            for (int i = 0; i < objects.size(); i++) {
                app.addObject(objects.get(i));
            }
        }
        drawBackground();

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).draw();
        }
    }

    private void drawBackground() {
        if ((color == Color.TRANSPARENT) && (borderWeight == 0)) {
            return;
        }

        super.draw();

        /* TODO
        app.rect((float) getX(), (float) getY(), (float) getWidth(),
                (float) getHeight());
                */
    }

    /**
     * Adds a GraphicsObject to the Compound. The added object keeps its
     * absolute position.
     *
     * @param object
     *            the object to add
     */
    public void add(GraphicsObject object) {
        objects.add(object);
    }

    /**
     * Adds a GraphicsObject to the Compound. The added object is positioned
     * relative to the position of the Compound.
     *
     * @param object
     *            the object to add
     */
    public void addRelative(GraphicsObject object) {
        add(object);

        object.setPosition(getX() + object.getX(), getY() + object.getY());
    }

    /**
     * Returns the object at the specified index, numbering goes from back to
     * front (topmost object has highest index).
     *
     * @param index
     *            The index of the object to return
     * @return The object at the specified index
     */
    public GraphicsObject get(int index) {
        return objects.get(index);
    }

    /**
     * Returns the topmost graphical object that contains the point (x, y), or
     * null if no such object exists. The coordinates must be absolute values.
     *
     * @param x
     *            The x-coordinate of the point
     * @param y
     *            The y-coordinate of the point
     * @return The object at the specified location, or null if no such object
     *         exists
     */
    public GraphicsObject getObjectAt(double x, double y) {
        for (int i = objects.size() - 1; i >= 0; i--) {
            GraphicsObject object = objects.get(i);
            if (object.hitTest(x, y)) {
                return object;
            }
        }
        return null;
    }

    /**
     * Returns the topmost graphical object that contains the point (x, y), or
     * null if no such object exists. The coordinates must be absolute values.
     *
     * @param point
     *            The point being tested
     * @return The object at the specified location, or null if no such object
     *         exists
     */
    public GraphicsObject getObjectAt(Point point) {
        return getObjectAt(point.getX(), point.getY());
    }

    /**
     * Removes all objects from this Compound.
     */
    public void removeAll() {
        objects.clear();
    }

    /**
     * Removes an object from this Compound.
     *
     * @param object
     *            The object to remove
     */
    public void remove(GraphicsObject object) {
        objects.remove(object);
    }

    /**
     * Removes an object from this Compound.
     *
     * @param index
     *            The index of the object to remove
     */

    public void remove(int index) {
        objects.remove(index);
    }

    /**
     * Returns the number of objects.
     *
     * @return The number of objects in this Compound
     */
    public int objectCount() {
        return objects.size();
    }

    /**
     * Returns an Iterator that cycles through the elements within this Compound
     * in the default direction, from back to front.
     *
     * @return An Iterator cycling through the elements of the Compound from
     *         back to front
     */
    public Iterator<GraphicsObject> iterator() {
        return objects.iterator();
    }

    /**
     * Checks to see whether a point is "inside" the compound, which means that
     * it is inside one of the components.
     *
     * @param x
     *            The x-coordinate of the point being tested
     * @param y
     *            The y-coordinate of the point being tested
     * @return true if the point (x, y) is inside the compound, and false
     *         otherwise
     */
    public boolean contains(double x, double y) {
        return getObjectAt(x, y) != null;
    }

    @Override
    public double getWidth() {
        double maxRightBorder = Double.MIN_VALUE;

        for (GraphicsObject object : objects) {
            double rightBorder = object.getRightBorder();
            if (rightBorder > maxRightBorder) {
                maxRightBorder = rightBorder;
            }
        }

        return maxRightBorder - getX();
    }

    @Override
    public double getHeight() {
        double maxBottomBorder = Double.MIN_VALUE;

        for (GraphicsObject object : objects) {
            double bottomBorder = object.getBottomBorder();
            if (bottomBorder > maxBottomBorder) {
                maxBottomBorder = bottomBorder;
            }
        }

        return maxBottomBorder - getY();
    }

    @Override
    public void setX(double x) {
        double dx = x - getX();

        super.setX(x);

        for (GraphicsObject object : objects) {
            object.setX(object.getX() + dx);
        }
    }

    @Override
    public void setY(double y) {
        double dy = y - getY();

        super.setY(y);

        for (GraphicsObject object : objects) {
            object.setX(object.getY() + dy);
        }
    }

    @Override
    public void setPosition(double x, double y) {
        double dx = x - getX();
        double dy = y - getY();

        this.x = x;
        this.y = y;

        for (GraphicsObject object : objects) {
            object.setPosition(object.getX() + dx, object.getY() + dy);
        }
    }

    @Override
    public void setPosition(Point point) {
        setPosition(point.getX(), point.getY());
    }

    @Override
    public void move(double dx, double dy) {
        super.move(dx, dy);

        for (GraphicsObject object : objects) {
            object.move(dx, dy);
        }
    }

    @Override
    public boolean hitTest(double x, double y) {
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;

        for (GraphicsObject object : objects) {
            double objMinX = object.getLeftBorder();
            double objMaxX = object.getRightBorder();
            double objMinY = object.getTopBorder();
            double objMaxY = object.getBottomBorder();

            if (objMinX < minX) {
                minX = objMinX;
            }
            if (objMinX > maxX) {
                maxX = objMaxX;
            }
            if (objMinY < minY) {
                minY = objMinY;
            }
            if (objMaxY > maxY) {
                maxY = objMaxY;
            }
        }

        return ((x >= minX) && (x <= maxX) && (y >= minY) && (y <= maxY));
    }

}