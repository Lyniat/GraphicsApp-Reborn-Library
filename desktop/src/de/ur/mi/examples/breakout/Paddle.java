package de.ur.mi.examples.breakout;

import de.ur.mi.graphics.GraphicsObject;
import de.ur.mi.graphics.Image;

public class Paddle {

	private static final String PADDLE_IMAGE = "paddle.png";
	private static final double PADDLE_SPEED = 5;
	
	private Image paddle;
	private int yPos;
	private int leftConstraint;
	private int rightConstraint;
	private int hitCount;
	
	public Paddle(int yPos, int leftConstraint, int rightConstraint) {
		this.yPos = yPos;
		this.leftConstraint = leftConstraint;
		this.rightConstraint = rightConstraint;
		
		setup();
	}
	
	private void setup() {
		paddle = new Image(0, 0, PADDLE_IMAGE);
	}

	public void resetPosition() {
		paddle.setPosition(
				rightConstraint * 0.5 - paddle.getWidth() * 0.5, 
				yPos - paddle.getHeight());

		this.hitCount  = 0;
	}
	
	public void moveLeft() {
		paddle.move(-PADDLE_SPEED, 0);
		constrainPaddlePosition();
	}
	
	public void moveRight() {
		System.out.println("move right");
		paddle.move(PADDLE_SPEED, 0);
		constrainPaddlePosition();
	}

	public void draw() {
		paddle.draw();
	}
	
	private void constrainPaddlePosition() {
		if (paddle.getLeftBorder() <= leftConstraint) {
			paddle.setX(leftConstraint);
		}
		if (paddle.getRightBorder () >= rightConstraint) {
			paddle.setX(rightConstraint - paddle.getWidth());
		}
	}
	
	public boolean collidesWith(GraphicsObject object) {
		return (
				(object.getBottomBorder() >= paddle.getTopBorder()) &&
				(object.getBottomBorder() <= paddle.getBottomBorder()) &&
				(object.getRightBorder() >= paddle.getLeftBorder()) && 
				(object.getLeftBorder() <= paddle.getRightBorder()));
	}
	
	public void putOnTopBorder(GraphicsObject object) {
		object.setPosition(
				object.getX(), 
				paddle.getTopBorder() - (object.getHeight() * 0.5));
	}
	
	public void increaseHitCount() {
		hitCount++;
	}

	public int hitCount() {
		return hitCount;
	}
	
}