package de.ur.mi.examples.breakout;

import de.ur.mi.events.KeyEvent;

public class KeyHandler {
	
	private static final int KEY_CODE_LEFT = 21;
	private static final int KEY_CODE_RIGHT = 22;
	private static final int KEY_CODE_SPACE = 62;
	
	private boolean rightKeyDown;
	private boolean leftKeyDown;
	private boolean spaceKeyDown;
	
	public KeyHandler() {
		
	}
	
	public void keyPressed(KeyEvent event) {
		System.out.println("event");
		System.out.println(event.getKeyCode());
		switch (event.getKeyCode()) {
		case KEY_CODE_LEFT:
			leftKeyDown = true;
			break;
		case KEY_CODE_RIGHT:
			rightKeyDown = true;
			break;
		case KEY_CODE_SPACE:
			spaceKeyDown = true;
			break;
		}
	}

	public void keyReleased(KeyEvent event) {
		switch (event.getKeyCode()) {
		case KEY_CODE_LEFT:
			leftKeyDown = false;
			break;
		case KEY_CODE_RIGHT:
			rightKeyDown = false;
			break;
		case KEY_CODE_SPACE:
			spaceKeyDown = false;
			break;
		}
	}
	
	public boolean isLeftKeyDown() {
		return leftKeyDown;
	}
	
	public boolean isRightKeyDown() {
		return rightKeyDown;
	}
	
	public boolean isSpaceKeyDown() {
		return spaceKeyDown;
	}

}