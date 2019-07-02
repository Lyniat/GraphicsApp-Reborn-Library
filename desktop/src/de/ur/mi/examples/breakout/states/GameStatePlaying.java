package de.ur.mi.examples.breakout.states;

import de.ur.mi.examples.breakout.Breakout;

public class GameStatePlaying implements GameState {
	
	public GameStatePlaying(Breakout game) {
		game.play();
	}
	
	@Override
	public void play(Breakout game) {

	}

	@Override
	public void loseRound(Breakout game) {		
		if (game.numberOfRemainingRounds() > 0) {
			game.removeOneRound();
			game.setState(new GameStateReady());
		} else { 
			game.setState(new GameStateNew(game));
		}
	}

}