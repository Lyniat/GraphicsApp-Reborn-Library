package de.ur.mi.graphics;

/**
 * Specifies the characteristics of a graphical object that supports the scale
 * method.
 */
public interface Scalable {

    /**
     * Scales the object in both dimensions using the scale factor sf.
     *
     * @param sf
     *            The factor used to scale all coordinates in both dimensions
     */
    public void scale(double sf);

    /**
     * Scales the object by the scale factors sx and sy.
     *
     * @param sx
     *            The factor used to scale all coordinates in the x direction
     * @param sy
     *            The factor used to scale all coordinates in the y direction
     */
    public void scale(double sx, double sy);

}
