package de.ur.mi.graphics;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import de.ur.mi.geom.Point;

/**
 * The Label class is a graphical object whose appearance consists of a text
 * String.
 */
public class Label extends GraphicsObject {

	public static final double DEFAULT_FONT_SIZE = 12;
	public static final double FONT_SCALE_FACTOR = 0.75;

	private String text;
	private double fontSize;

	BitmapFont font;

	/**
	 * Constructs a new label, positioned at the x and y coordinates with the
	 * specified text, font color and font size.
	 * 
	 * @param x
	 *            The x-position of the baseline in pixels
	 * @param y
	 *            The y-position of the baseline in pixels
	 * @param text
	 *            The text of the label
	 * @param fontColor
	 *            The color of the font
	 * @param fontSize
	 *            The size of the font
	 * 
	 */
	public Label(double x, double y, String text, Color fontColor,
                 double fontSize) {
		super(x, y, fontColor);

		font = new BitmapFont();
		this.text = text;
		this.fontSize = fontSize;
	}

	/**
	 * Constructs a new label, positioned at the x and y coordinates with the
	 * specified text and font color.
	 * 
	 * @param x
	 *            The x-position of the baseline in pixels
	 * @param y
	 *            The y-position of the baseline in pixels
	 * @param text
	 *            The text of the label
	 * @param fontColor
	 *            The color of the font
	 * 
	 */
	public Label(double x, double y, String text, Color fontColor) {
		this(x, y, text, fontColor, DEFAULT_FONT_SIZE);
	}

	/**
	 * Constructs a new label, positioned at the given point with the specified
	 * text and font color.
	 * 
	 * @param point
	 *            a Point representing the position of the baseline
	 * @param text
	 *            The text of the label
	 * @param fontColor
	 *            The color of the font
	 * 
	 */
	public Label(Point point, String text, Color fontColor) {
		this(point.getX(), point.getY(), text, fontColor);
	}

	/**
	 * Returns the font color of the label.
	 * 
	 * @return The font color of the label
	 * 
	 */
	public Color getFontColor() {
		return color;
	}

	/**
	 * Sets the font color of the label to the specified Color.
	 * 
	 * @param color
	 *            The color for the text
	 * 
	 */
	public void setFontColor(Color color) {
		this.color = color;
	}

	/**
	 * Returns the text of the label.
	 * 
	 * @return The text of the label
	 * 
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text of the label to the specified String.
	 * 
	 * @param text
	 *            The text which should be displayed
	 * 
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Returns the font size of the label.
	 * 
	 * @return The font size of the label
	 * 
	 */
	public double getFontSize() {
		return fontSize;
	}

	/**
	 * Sets the font size of the text in the label.
	 * 
	 * @param fontSize
	 *            The font size of the label in pixels
	 */
	public void setFontSize(double fontSize) {
		this.fontSize = fontSize;
	}

	@Override
	public void draw() {
		super.draw();
	}

	public void render(){
		app.text(font,text,(int)x,(int)y);
	}

	@Override
	public double getWidth() {
		//return app.textWidth(text);
		return 0; //TODO
	}

	@Override
	public double getHeight() {
		/*
		return (app.textAscent() * FONT_SCALE_FACTOR)
				+ (app.textDescent() * FONT_SCALE_FACTOR);

		 */

		return 0; //TODO
	}

	@Override
	public double getTopBorder() {
		return super.getY() - getHeight();
	}

	@Override
	public double getBottomBorder() {
		return super.getY();
	}

}