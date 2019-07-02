package de.ur.mi.examples.breakout.states;

import de.ur.mi.examples.breakout.Breakout;

public interface GameState {
	
	public void play(Breakout game);
	public void loseRound(Breakout game);

}