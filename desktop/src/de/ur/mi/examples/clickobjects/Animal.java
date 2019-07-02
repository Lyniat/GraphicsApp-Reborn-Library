package de.ur.mi.examples.clickobjects;

import de.ur.mi.graphics.Image;

/**
 * Created by laurin on 05.12.16.
 */
public class Animal implements Clickable {

    private double distance;
    private Image image;

    public Animal(double xPos, double yPos, int width, int height, String src, double distance) {
        this.distance = distance;
        createImage(xPos, yPos, width, height, src);
    }

    private void createImage(double xPos, double yPos, int width, int height, String src) {
        image = new Image(xPos, yPos, width, height, src);
    }

    @Override
    public boolean wasClicked(double x, double y) {
        return image.hitTest(x, y);
    }

    @Override
    public void onClick() {
        moveDown();
    }

    private void moveDown() {
        image.move(0, distance);
    }

    public void draw() {
        image.draw();
    }
}
