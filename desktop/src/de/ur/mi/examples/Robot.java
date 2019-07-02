package de.ur.mi.examples;

import de.ur.mi.graphicsapp.GraphicsApp;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Ellipse;
import de.ur.mi.graphics.Line;
import de.ur.mi.graphics.Rect;

public class Robot extends GraphicsApp {

    private static final long serialVersionUID = 1L;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    public void setup() {
        size(WIDTH, HEIGHT);
        background(Color.LIGHT_GRAY);
        drawRobot();
    }

    public void drawRobot() {
        drawHead();
        drawWheel();
        drawBody();
    }

    public void drawHead() {
        drawAntennae();
        drawHeadShape();
        drawFace();
        drawNeck();
    }

    public void drawAntennae() {
        Line antennaOne = new Line(WIDTH / 2 + 15, 180, 335, 190, Color.BLACK);
        Line antennaTwo = new Line(WIDTH / 2 + 15, 180, 300, 95, Color.BLACK);
        Line antennaThree = new Line(WIDTH / 2 + 15, 180, 220, 130, Color.BLACK);
        antennaOne.draw();
        antennaTwo.draw();
        antennaThree.draw();
    }

    public void drawHeadShape() {
        Ellipse head = new Ellipse(WIDTH / 2 + 20, 180, 100, 100, Color.BLACK);
        head.draw();
    }

    public void drawFace() {
        Ellipse iris = new Ellipse(295, 170, 25, 25, Color.WHITE);
        Ellipse pupil = new Ellipse(295, 170, 5, 5, Color.BLACK);
        Ellipse birthmarkOne = new Ellipse(295, 150, 6, 6, Color.CYAN);
        Ellipse birthmarkTwo = new Ellipse(295, 190, 5, 5, Color.CYAN);
        Ellipse birthmarkThree = new Ellipse(310, 185, 7, 7, Color.CYAN);
        iris.draw();
        pupil.draw();
        birthmarkOne.draw();
        birthmarkTwo.draw();
        birthmarkThree.draw();
    }

    public void drawNeck() {
        Line neckOne = new Line(WIDTH / 2 + 20, 230, WIDTH / 2 + 20, 270, Color.BLACK);
        Line neckTwo = new Line(WIDTH / 2 + 10, 229, WIDTH / 2 + 10, 270, Color.BLACK);
        Line neckThree = new Line(WIDTH / 2 + 28, 230, WIDTH / 2 + 28, 270, Color.BLACK);
        neckOne.draw();
        neckTwo.draw();
        neckThree.draw();
    }

    public void drawWheel() {
        Ellipse wheel = new Ellipse(WIDTH / 2, 400, 80, 80, Color.DARK_GRAY);
        wheel.draw();
    }

    public void drawBody() {
        Rect body = new Rect(WIDTH / 2 - 50, 270, 100, 130, Color.BLACK);
        body.draw();
        Rect shoulders = new Rect(WIDTH / 2 - 50, 295, 100, 7, Color.GRAY);
        shoulders.draw();
    }

}