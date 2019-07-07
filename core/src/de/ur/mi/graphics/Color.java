package de.ur.mi.graphics;

public class Color {
    private static final long serialVersionUID = 1L;

    public static Color TRANSPARENT = new Color(0, 0, 0, 0);
    public static Color BLACK = new Color(0, 0, 0);
    public static Color BLUE = new Color(107, 229, 242);
    public static Color CYAN = new Color(51, 108, 115);
    public static Color DARK_GRAY = new Color(91, 100, 112);
    public static Color GRAY = new Color(140, 148, 161);
    public static Color GREEN = new Color(163, 217, 48);
    public static Color LIGHT_GRAY = new Color(189, 199, 214);
    public static Color MAGENTA = new Color(242, 41, 115);
    public static Color ORANGE = new Color(242, 131, 34);
    public static Color PINK = new Color(244, 115, 162);
    public static Color RED = new Color(242, 30, 27);
    public static Color WHITE = new Color(255, 255, 255);
    public static Color YELLOW = new Color(242, 235, 34);

    public int r;
    public int g;
    public int b;
    public int a;

    /**
     * Creates a new Color from the given rgb-values.
     *
     * @param red
     *            the red value of the new color (0-255)
     * @param green
     *            the green value of the new color (0-255)
     * @param blue
     *            the blue value of the new color (0-255)
     */
    public Color(int red, int green, int blue) {
        this(red, green, blue,0);
    }

    /**
     * Creates a new Color from the given rgba-values.
     *
     * @param red
     *            the red value of the new color (0-255)
     * @param green
     *            the green value of the new color (0-255)
     * @param blue
     *            the blue value of the new color (0-255)
     * @param alpha
     *            the transparency value of the new color (0-255)
     */
    public Color(int red, int green, int blue, int alpha) {
        r = red;
        g = green;
        b = blue;
        a = alpha;

    }

    /**
     * Creates a new color from the given int value.
     *
     * @param color the int value containing rgb values of a color
     */
    public Color(int color) {
        this(color,color,color,0);
    }

    /**
     * Returns the int value of the color.
     *
     * @return int value of a pixel color.
     */

    /*
    public int toInt() {
        return app.color(
                getRed(),
                getGreen(),
                getBlue());
    }
    */

    public com.badlogic.gdx.graphics.Color convertColor(){
        return new com.badlogic.gdx.graphics.Color(r/255f,g/255f,b/255f,a/255f);
    }

}