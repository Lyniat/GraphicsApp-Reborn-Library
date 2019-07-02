package de.ur.mi.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import de.ur.mi.geom.Point;

/**
 * The Image class is a graphical object that displays an image.
 */
public class Image extends GraphicsObject implements Scalable, Resizable {

    private Texture img;
    private int[][] pixels;

    /**
     * Loads and creates an image positioned at the x and y coordinates and with
     * the specified width and height.
     *
     * @param x
     *            The x-position of the upper-left corner of the image
     * @param y
     *            The y-position of the upper-left corner of the image
     * @param width
     *            The scaled image width
     * @param height
     *            The scaled image height
     * @param src
     *            The name of the image which should be placed in the class
     *            folder of the project
     */
    public Image(double x, double y, int width, int height, String src) {
        super(x, y, width, height, Color.BLACK);

        img = new Texture(Gdx.files.internal(src));
        pixels = new int[img.getHeight()][img.getWidth()];
    }

    /**
     * Loads and creates an image positioned at the x and y coordinates.
     *
     * @param x
     *            The x-position of the upper-left corner of the image
     * @param y
     *            The y-position of the upper-left corner of the image
     * @param src
     *            The name of the image which should be placed in the class
     *            folder of the project
     */
    public Image(double x, double y, String src) {
        this(x, y, 0, 0, src);

        this.width = img.getWidth();
        this.height = img.getHeight();
    }

    /**
     * Loads and creates an image positioned at the given point.
     *
     * @param point
     *            a point representing the top-left corner of the Image
     * @param src
     *            The name of the image which should be placed in the class
     *            folder of the project
     */
    public Image(Point point, String src) {
        this(point.getX(), point.getY(), src);
    }

    /**
     * Loads and creates an image positioned at the given point and with the
     * specified width and height.
     *
     * @param point
     *            a point representing the top-left corner of the Image
     * @param width
     *            The scaled image width
     * @param height
     *            The scaled image height
     * @param src
     *            The name of the image which should be placed in the class
     *            folder of the project
     */
    public Image(Point point, int width, int height, String src) {
        this(point.getX(), point.getY(), width, height, src);
    }

    @Override
    public void draw() {
        super.draw();
        if(!app.rendering){
            return;
        }
        //app.image(img, (float) x, (float) y, (float) width, (float) height);
        app.spriteBatch.draw(img,(float)x,(float)(app.getHeight()-y-height),(float)width,(float)height);
    }

    /**
     * Returns a two-dimensional array containing the pixels of the image.
     *
     * @return A two-dimensional array containing the pixels of the image.
     */
    /*
    public int[][] getPixelArray() {
        img.loadPixels();

        for (int i = 0; i < img.height; i++) {
            for (int j = 0; j < img.width; j++) {
                pixels[i][j] = img.pixels[j + (i * img.width)];
            }
        }

        return pixels;
    }
    */

    /**
     * Sets the pixels of the image and redraws the image.
     *
     * @param //newPixelArr
     *            A two-dimensional array which contains the new pixels of the
     *            image
     */

    /*
    public void setPixelArray(int[][] newPixelArr) {
        img.loadPixels();

        for (int i = 0; i < newPixelArr.length; i++) {
            for (int j = 0; j < newPixelArr[i].length; j++) {
                img.pixels[j + (i * img.width)] = newPixelArr[i][j];
            }
        }

        img.updatePixels();
        draw();
    }

     */

    @Override
    public void scale(double sx, double sy) {
        this.width *= sx;
        this.height *= sy;
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

    //@Override
    public void setBounds(Rect bounds) {
        setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(),
                bounds.getHeight());
    }

    @Override
    public void setSize(double width, double height) {
        this.width = (int) width;
        this.height = (int) height;
    }

}
