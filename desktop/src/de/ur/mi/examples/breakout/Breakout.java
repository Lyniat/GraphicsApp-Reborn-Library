package de.ur.mi.examples.breakout;

import de.ur.mi.examples.breakout.states.GameState;
import de.ur.mi.examples.breakout.states.GameStateNew;
import de.ur.mi.graphicsapp.GraphicsApp;
import de.ur.mi.events.KeyEvent;
import de.ur.mi.graphics.Color;

public class Breakout extends GraphicsApp {
	
	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 396;
	private static final int HEIGHT = 600;
	private static final int BRICK_ROWS = 10;
	private static final int BRICKS_PER_ROW = 10;
	private static final int BRICK_GAP = 4;
	private static final int NUM_ROUNDS = 3;
	private static final double BRICK_SCORES = 5;

	private GameDisplay gameDisplay;
	private Bricks bricks;
	private Paddle paddle;
	private Ball ball;
	private SoundPlayer soundPlayer;
	private KeyHandler keyHandler;
	private GameState gameState;

	public void setup() {
		size(WIDTH, HEIGHT);
		background(Color.BLACK);

		bricks = new Bricks(0, 70, WIDTH);
		paddle = new Paddle(HEIGHT - 30, 0, WIDTH);
		ball = new Ball(0, WIDTH, HEIGHT, 0);
		gameDisplay = new GameDisplay(HEIGHT - 16, 0, NUM_ROUNDS);
		soundPlayer = new SoundPlayer();
		keyHandler = new KeyHandler();
		
		setState(new GameStateNew(this));
	}

	public void setState(GameState gameState) {
		this.gameState = gameState;
	}
	
	public void draw() {
		handleKeys();

		bricks.draw();
		paddle.draw();
		
		drawBall();

		gameDisplay.draw();
	}

	private void drawBall() {
		ball.handleCollisionWithPaddle(paddle);
		
		boolean brickWasRemoved = ball.handleCollisionWithBricks(bricks);
		if (brickWasRemoved) {
			gameDisplay.addScores(BRICK_SCORES);
			soundPlayer.playBrickRemovedSound();
		}
		
		if (ball.hitsBottomWall()) {
			gameState.loseRound(this);
		}
		
		ball.draw();
	}

	@Override
	public void keyPressed(KeyEvent event) {
		super.keyPressed(event);
		keyHandler.keyPressed(event);
	}

	@Override
	public void keyReleased(KeyEvent event) {
		super.keyReleased(event);
		keyHandler.keyReleased(event);
	}

	private void handleKeys() {
		if (keyHandler.isLeftKeyDown()) {
			paddle.moveLeft();
		} else if (keyHandler.isRightKeyDown()) {
			paddle.moveRight();
		} else if (keyHandler.isSpaceKeyDown()) {
			gameState.play(this);
		}
	}
	
	public void reset() {
		bricks.createRows(BRICK_ROWS, BRICKS_PER_ROW, BRICK_GAP);
		paddle.resetPosition();
		ball.resetPosition();
		gameDisplay.reset();
	}
	
	public void resetBall() {
		ball.resetPosition();
	}
	
	public void play() {
		ball.setRandomHeading();
	}
	
	public void removeOneRound() {
		gameDisplay.removeOneRound();
	}
	
	public int numberOfRemainingRounds() {
		return gameDisplay.numberOfRemainingRounds();
	}

}