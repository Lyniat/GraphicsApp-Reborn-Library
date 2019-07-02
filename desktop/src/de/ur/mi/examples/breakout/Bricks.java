package de.ur.mi.examples.breakout;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Compound;
import de.ur.mi.graphics.GraphicsObject;
import de.ur.mi.graphics.Rect;

public class Bricks {
	
	private Compound bricks;
	private int xPos;
	private int yPos;
	private int width;
	
	public Bricks(int xPos, int yPos, int width) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		
		setup();
	}
	
	private void setup() {
		bricks = new Compound(xPos, yPos);
	}

	public void createRows(int rows, int bricksPerRow, int gap) {
		bricks.removeAll();

		int brickWidth = (width - (bricksPerRow - 1) * gap) / bricksPerRow;

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < bricksPerRow; col++) {
				bricks.addRelative(createBrickAt(
						row, 
						col, 
						brickWidth, 
						8,
						gap));
			}
		}
	}

	private GraphicsObject createBrickAt(int row, int col, int width, int height, int gap) {
		return new Rect(
				col * (width + gap), 
				row * (height + gap), 
				width, 
				height,
				brickColorForRow(row));
	}
	
	private Color brickColorForRow(int row) {
		switch (row) {
		case 0:
		case 1:
			return Color.RED;
		case 2:
			return Color.ORANGE;
		case 3:
			return Color.YELLOW;
		case 4:
			return Color.GREEN;
		case 5:
		default:
			return Color.CYAN;
		}
	}

	public void draw() {
		bricks.draw();
	}

	public boolean removeBrickOnCollisionWith(GraphicsObject object) {
		GraphicsObject collidingBrick = null;
	
		if (collidingBrick == null) {
			collidingBrick = bricks.getObjectAt(object.getLeftBorder(), object.getTopBorder());
		}
		if (collidingBrick == null) {
			collidingBrick = bricks.getObjectAt(object.getRightBorder(), object.getTopBorder());
		}
		if (collidingBrick == null) {
			collidingBrick = bricks.getObjectAt(object.getLeftBorder(), object.getBottomBorder());
		}
		if (collidingBrick == null) {
			collidingBrick = bricks.getObjectAt(object.getRightBorder(), object.getBottomBorder());
		}
		
		if (collidingBrick != null) {
			bricks.remove(collidingBrick);
		}
		
		return collidingBrick != null;
	}
	
	public int numberOfRemainingBricks() {
		return bricks.objectCount();
	}

}