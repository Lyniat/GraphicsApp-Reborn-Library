package de.ur.mi.examples;
/*
 * File: BouncingBall.java
 *
 * This program draws a bouncing ball on the screen.
 *
 */

import de.ur.mi.graphicsapp.GraphicsApp;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Ellipse;

public class BouncingBall extends GraphicsApp {

    /* Private constants */
    private static final int CANVAS_HEIGHT = 400;
    private static final int CANVAS_WIDTH = 1000;
    private static final int FRAME_RATE = 60;
    private static final Color BACKGROUND_COLOR = Color.BLUE;
    private static final int BALL_RADIUS = 15;
    private static final int BALL_DIAMETER = 2 * BALL_RADIUS;
    private static final double SPEED_REDUCTION_BOUNCE = 0.9;
    private static final double X_SPEED = 1;
    private static final double INITIAL_YSPEED = 0;
    private static final double GRAVITY = 0.15;

    /* Private instance variables */
    private Ellipse ball; /* The ball object */
    private double dx; /* Velocity delta in the x direction */
    private double dy; /* Velocity delta in the y direction */

    public void setup() {
        size(CANVAS_WIDTH,CANVAS_HEIGHT);
        setupCanvas();
        setupBall();
    }

    private void setupBall() {
        ball = new Ellipse(100, 100, BALL_DIAMETER, BALL_DIAMETER, Color.RED);
        // Set the initial speed for the ball
        dx = X_SPEED;
        dy = INITIAL_YSPEED;
    }

    public void draw() {
        drawBackground();
        if (ball.getX() + BALL_RADIUS < getWidth()) {
            moveBall();
            checkForCollision();
        }
    }

    // Update, move and draw the ball
    private void moveBall() {
        System.out.println("moving");
        dy += GRAVITY;
        ball.move(dx, dy);
        ball.draw();
    }

    /*
     * Determine if collision with floor, update velocities and location as
     * appropriate.
     */
    private void checkForCollision() {
        // if ball hits the floor
        if (ball.getY() + BALL_RADIUS > getHeight()) {
            // reverse y-direction to bounce upwards and reduce speed
            dy = -dy * SPEED_REDUCTION_BOUNCE;

            // assume bounce will move ball an amount above the
            // floor equal to the amount it would have dropped
            // below the floor.
            double diff = ball.getY() - (getHeight() - BALL_RADIUS);
            ball.move(0, -2 * diff);
            ball.draw();
        }
    }

    private void setupCanvas() {
        //size(CANVAS_WIDTH, CANVAS_HEIGHT);
        //frameRate(FRAME_RATE);
        //smooth();
    }

    private void drawBackground() {
        background(BACKGROUND_COLOR);
    }
}
