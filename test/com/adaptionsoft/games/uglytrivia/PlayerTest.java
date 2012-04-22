package com.adaptionsoft.games.uglytrivia;

import static org.junit.Assert.*;

import org.junit.Test;


public class PlayerTest {

	@Test
	public void newPlayerIsOutOfPenaltyBox(){
		Player player = new Player("Tester");
		assertFalse(player.isInPenaltyBox());
	}
	
	@Test
	public void newPlayerStartsAtTheBeginningOfTheBoard(){
		Player player = new Player("Tester");
		assertEquals(0, player.getPlace());
	}
	
	@Test
	public void newPlayerStartsWithAnEmptyPurse(){
		Player player = new Player("Tester");
		assertEquals(0, player.getPurse());
	}
}
