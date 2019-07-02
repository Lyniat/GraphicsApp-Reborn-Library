package de.ur.mi.examples;

import de.ur.mi.graphicsapp.GraphicsApp;
import de.ur.mi.geom.Point;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Ellipse;
import de.ur.mi.graphics.Triangle;


public class Face extends GraphicsApp {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    public void setup() {
        setupCanvas();
        drawSmiley();
    }

    private void setupCanvas() {
        size(WIDTH, HEIGHT);
        background(Color.PINK);
    }

    private void drawSmiley() {
        drawFace();
        drawMouth();
        drawLeftEye();
        drawRightEye();
        drawHat();
    }

    private void drawFace() {
        double x = WIDTH/2;
        double y = HEIGHT/2;
        int width = WIDTH/2;
        int height = HEIGHT/2;
        Ellipse face = new Ellipse(x, y, width, height, Color.YELLOW);
        face.draw();
    }

    private void drawHat(){
        Point p1 = new Point(WIDTH/4,HEIGHT / 5);
        Point p2 = new Point((WIDTH/4)*3,HEIGHT / 5);
        Point p3 = new Point(WIDTH/2,HEIGHT / 12);
        Triangle hat = new Triangle(p1,p2,p3,Color.CYAN);
        hat.draw();
    }

    private void drawMouth() {
        double x = WIDTH/2;
        double y = HEIGHT/1.5;
        int width = WIDTH/10;
        int height = HEIGHT/20;
        Ellipse mouth = new Ellipse(x, y, width,height, Color.BLACK);
        mouth.draw();
    }

    private void drawLeftEye() {
        double x = WIDTH/2 - WIDTH/10;
        double y = HEIGHT/2.5;
        int width = WIDTH/10;
        int height = HEIGHT/10;
        int pupilWidth = WIDTH/20;
        int pupilHeight= HEIGHT/20;
        Ellipse leftEye = new Ellipse(x, y, width, height, Color.BLACK);
        leftEye.draw();
        Ellipse leftIris = new Ellipse(x, y, pupilWidth, pupilHeight, Color.WHITE);
        leftIris.draw();
    }

    private void drawRightEye() {
        double x = WIDTH/2 + WIDTH/10;
        double y = HEIGHT/2.5;
        int width = WIDTH/10;
        int height = HEIGHT/10;
        int pupilWidth = WIDTH/20;
        int pupilHeight= HEIGHT/20;
        Ellipse rightEye = new Ellipse(x, y, width, height, Color.BLACK);
        rightEye.draw();
        Ellipse rightIris = new Ellipse(x, y, pupilWidth, pupilHeight, Color.WHITE);
        rightIris.draw();
    }



}

