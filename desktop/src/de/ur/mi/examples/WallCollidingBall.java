package de.ur.mi.examples;

import de.ur.mi.graphicsapp.GraphicsApp;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Ellipse;

public class WallCollidingBall extends GraphicsApp {

    private static final long serialVersionUID = 1L;

    private static final int BALL_DIAMETER = 30;
    private static final Color BALL_COLOR = Color.MAGENTA;
    private static final int BALL_ORIGIN_X = 100;
    private static final int BALL_ORIGIN_Y = 20;
    private static final Color BG_COLOR = Color.GREEN;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    private Ellipse ball;
    private int ballSpeedX = 1;
    private int ballSpeedY = 1;

    public void setup() {
        setupCanvas();
        setupBall();
    }

    private void setupCanvas() {
        size(WIDTH, HEIGHT);
    }

    private void setupBall() {
        ball = new Ellipse(
                BALL_ORIGIN_X,
                BALL_ORIGIN_Y,
                BALL_DIAMETER,
                BALL_DIAMETER,
                BALL_COLOR);
    }

    public void draw() {
        background(BG_COLOR);

        updateBall();
    }

    private void updateBall() {
        checkCollisions();

        ball.move(ballSpeedX, ballSpeedY);
        ball.draw();
    }

    private void checkCollisions() {
        double ballX = ball.getX();
        double ballY = ball.getY();
        double ballRadius = ball.getWidth() / 2;

        if ((ballX + ballRadius > getWidth()) || (ballX - ballRadius < 0)) {
            ballSpeedX *= -1;
        }

        if ((ballY + ballRadius > getHeight()) || (ballY - ballRadius < 0)) {
            ballSpeedY *= -1;
        }
    }

}