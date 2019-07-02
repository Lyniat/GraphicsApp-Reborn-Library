package de.ur.mi.examples.breakout.states;

import de.ur.mi.examples.breakout.Breakout;

public class GameStateReady implements GameState {
	
	@Override
	public void play(Breakout game) {
		game.resetBall();
		game.setState(new GameStatePlaying(game));
	}

	@Override
	public void loseRound(Breakout game) {
		
	}

}