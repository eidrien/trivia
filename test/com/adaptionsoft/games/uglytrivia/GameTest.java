package com.adaptionsoft.games.uglytrivia;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class GameTest {

	@Test
	public void movingAPlayerChangesItsPlace(){
		Game game = new Game();
		game.add("Tester");
		game.roll(2);
		assertEquals(2, game.getCurrentPlayersPlace());
	}
	
	@Test
	public void movingAPlayerOverTheEndOfTheBoardMakesItContinueInTheBeginning(){
		Game game = new Game();
		game.add("Tester");
		game.roll(2);
		game.roll(10);
		game.roll(2);
		assertEquals(2, game.getCurrentPlayersPlace());
	}
}
