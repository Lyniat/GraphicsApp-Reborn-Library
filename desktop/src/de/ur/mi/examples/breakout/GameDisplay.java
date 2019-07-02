package de.ur.mi.examples.breakout;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Compound;
import de.ur.mi.graphics.Label;
import de.ur.mi.graphics.Rect;

public class GameDisplay {
	
	private Label scoresLabel;
	private int scores;
	private Compound roundsBar;
	private int numRounds;
	private int yPos;
	
	public GameDisplay(int yPos, int scores, int numRounds) {
		this.yPos = yPos;
		this.scores = scores;
		this.numRounds = numRounds;
		
		setup();
	}
	
	private void setup() {
		roundsBar = new Compound(10, yPos + 4);
		scoresLabel = new Label(10, yPos, "", Color.YELLOW, 10);
		
		resetScores();
		resetRounds();
	}
	
	public void reset() {
		resetScores();
		resetRounds();
	}

	public void resetScores() {
		scores = 0;
	}
	
	public void resetRounds() {
		roundsBar.removeAll();

		for (int i = 0; i < numRounds; i++) {
			Rect rect = new Rect(i * 22, 0, 20, 6, Color.GREEN);
			roundsBar.addRelative(rect);
		}
	}

	// TODO: Add scores according to brick color
	public void addScores(double scores) {
		this.scores += scores;
	}

	public void removeOneRound() {
		if (roundsBar.objectCount() > 0) {
			roundsBar.remove(roundsBar.objectCount() - 1);
		}
	}
	
	public int numberOfRemainingRounds() {
		return roundsBar.objectCount();
	}
	
	public void draw() {
		roundsBar.draw();
		scoresLabel.setText("Scores: " + scores);
		scoresLabel.draw();
	}
	
}