package de.ur.mi.examples.breakout;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Ellipse;
import de.ur.mi.util.RandomGenerator;

public class Ball {
	
	private static final double SPEED = 5;
	private static final double KICKER_FACTOR = 1.25;
	private static final int KICKER_INTERVAL = 7;
	
	private Ellipse ball;
	private int topConstraint;
	private int rightConstraint;
	private int bottomConstraint;
	private int leftConstraint;
	private double speedX;
	private double speedY;

	public Ball(int topConstraint, int rightConstraint, int bottomConstraint, int leftConstraint) {
		this.topConstraint = topConstraint;
		this.rightConstraint = rightConstraint;
		this.bottomConstraint = bottomConstraint;
		this.leftConstraint = leftConstraint;
		
		setup();
	}
	
	private void setup() {
		ball = new Ellipse(0, 0, 10, 10, Color.WHITE);

		resetPosition();
	}

	public void resetPosition() {
		speedX = 0;
		speedY = 0;

		centerPosition();
	}
	
	private void centerPosition() {
		ball.setPosition(
				leftConstraint + ((rightConstraint - leftConstraint) * 0.5), 
				topConstraint + ((bottomConstraint - topConstraint) * 0.5));
	}
	
	public void setRandomHeading() {
		RandomGenerator randomGenerator = RandomGenerator.getInstance();
		
		speedX = randomGenerator.nextDouble(1.0, 3.0);
		if (randomGenerator.nextBoolean(0.5)) {
			speedX = -speedX;
		}
		
		speedY = SPEED;
	}
	
	public boolean handleCollisionWithPaddle(Paddle paddle) {
		boolean collidesWithPaddle = paddle.collidesWith(ball);
		
		if (collidesWithPaddle) {
			reverseVerticalMovement();
			paddle.putOnTopBorder(ball);
			increaseHitCount(paddle);
		}
		
		return collidesWithPaddle;
	}

	private void increaseHitCount(Paddle paddle) {
		paddle.increaseHitCount();
		addKickerIfNeeded(paddle.hitCount());		
	}
	
	private void addKickerIfNeeded(int hitCount) {
		if (hitCount % KICKER_INTERVAL == 0) {
			speedX *= KICKER_FACTOR;
		}
	}
	
	public boolean handleCollisionWithBricks(Bricks bricks) {
		boolean brickWasRemoved = bricks.removeBrickOnCollisionWith(ball);
		
		if (brickWasRemoved) {
			reverseVerticalMovement();
		}
		
		return brickWasRemoved;
	}

	public void draw() {
		if (hitsHorizontalWalls()) {
			reverseHorizontalMovement();
		}
		if (hitsTopWall()) {
			reverseVerticalMovement();
		}
		
		// TODO: Handle hitting different parts of the paddle

		ball.move(speedX, speedY);
		ball.draw();
	}

	private boolean hitsHorizontalWalls() {
		return (ball.getLeftBorder() <= leftConstraint) || (ball.getRightBorder() >= rightConstraint);
	}

	private boolean hitsTopWall() {
		return ball.getTopBorder() <= 0;
	}

	public boolean hitsBottomWall() {
		return ball.getBottomBorder() >= bottomConstraint;
	}
	
	private void reverseVerticalMovement() {
		speedY *= -1;
	}
	
	private void reverseHorizontalMovement() {
		speedX *= -1;
	}
	
}