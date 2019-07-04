package de.ur.mi.examples.dungeonfighter;

import de.ur.mi.events.KeyEvent;

public class KeyHandler {
	
	private static final int KEY_CODE_LEFT = 21;
	private static final int KEY_CODE_RIGHT = 22;
	private static final int KEY_CODE_UP = 19;
	private static final int KEY_CODE_DOWN = 20;
	
	private boolean rightKeyDown;
	private boolean leftKeyDown;
	private boolean upKeyDown;
	private boolean downKeyDown;
	
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
		case KEY_CODE_UP:
			upKeyDown = true;
			break;
		case KEY_CODE_DOWN:
			downKeyDown = true;
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
			case KEY_CODE_UP:
				upKeyDown = false;
				break;
			case KEY_CODE_DOWN:
				downKeyDown = false;
				break;
		}
	}

	public boolean isLeftKeyDown() {
		return leftKeyDown;
	}

	public boolean isRightKeyDown() {
		return rightKeyDown;
	}

	public boolean isUpKeyDown() {
		return upKeyDown;
	}

	public boolean isDownKeyDown() {
		return downKeyDown;
	}

}