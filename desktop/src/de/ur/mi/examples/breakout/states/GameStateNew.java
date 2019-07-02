package de.ur.mi.examples.breakout.states;


import de.ur.mi.examples.breakout.Breakout;

public class GameStateNew implements GameState {
	
	public GameStateNew(Breakout game) {
		game.reset();
	}
	
	@Override
	public void play(Breakout game) {
		game.setState(new GameStatePlaying(game));
	}

	@Override
	public void loseRound(Breakout game) {
		
	}

}