package de.ur.mi.graphics;

/**
 * Specifies the characteristics of a graphical object that supports the setSize
 * and setBounds methods.
 */
public interface Resizable {

    /**
     * Changes the size of this object to the specified width and height.
     *
     * @param width
     *            The new width of the object
     * @param height
     *            The new height of the object
     */
    public void setSize(double width, double height);

    /**
     * Changes the bounds of this object to the specified values.
     *
     * @param x
     *            The new x-coordinate for the object
     * @param y
     *            The new y-coordinate for the object
     * @param width
     *            The new width of the object
     * @param height
     *            The new height of the object
     */
    public void setBounds(double x, double y, double width, double height);

    /**
     * Changes the bounds of this object to the values of the given Rect.
     *
     * @param bounds
     *            A Rect specifying the new bounds
     */
    //public void setBounds(Rect bounds); //TODO: implement

}