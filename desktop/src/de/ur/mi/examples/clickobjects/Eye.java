package de.ur.mi.examples.clickobjects;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Ellipse;

/**
 * Created by laurin on 05.12.16.
 */
public class Eye implements Clickable {

    private int width;
    private int height;
    private double xPos;
    private double yPos;
    private Color color;

    private Ellipse outerEllipse;
    private Ellipse innerEllipse;
    private Ellipse closedEye;
    private Color OUTER_COLOR = Color.WHITE;
    private Color CLOSED_COLOR = Color.GRAY;
    private boolean isClosed = false;

    public Eye(double xPos, double yPos, int width, int height, Color color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.color = color;
        createOuterEllipse();
        createInnerEllipse();
        createClosedEye();
    }

    private void createClosedEye() {
        closedEye = new Ellipse(xPos, yPos, width, height / 2, CLOSED_COLOR);
    }

    private void createOuterEllipse() {
        outerEllipse = new Ellipse(xPos, yPos, width, height / 2, OUTER_COLOR);
    }

    private void createInnerEllipse() {
        innerEllipse = new Ellipse(xPos, yPos, width / 4, height / 4, color);
    }

    public void draw() {
        if (!isClosed) {
            drawOpenEye();
        } else {
            drawClosedEye();
        }
    }

    private void drawClosedEye() {
        closedEye.draw();
    }

    private void drawOpenEye() {
        outerEllipse.draw();
        innerEllipse.draw();
    }

    @Override
    public boolean wasClicked(double x, double y) {
        return outerEllipse.hitTest(x, y);
    }

    @Override
    public void onClick() {
        changeEyeStatus();
    }


    private void changeEyeStatus() {
        if (isClosed) {
            isClosed = false;
        } else {
            isClosed = true;
        }
    }
}
