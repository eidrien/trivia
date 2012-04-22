package com.adaptionsoft.games.uglytrivia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;


public class PlayerTest {

	private Player player;
	
	@Before
	public void setup(){
		player = new Player("Tester");
	}
	
	@Test
	public void newPlayerIsOutOfPenaltyBox(){
		assertFalse(player.isInPenaltyBox());
	}
	
	@Test
	public void newPlayerStartsAtTheBeginningOfTheBoard(){
		assertEquals(0, player.getPlace());
	}
	
	@Test
	public void newPlayerStartsWithAnEmptyPurse(){
		assertEquals(0, player.getPurse());
	}
	
	@Test
	public void newPlayerRemembersItsName(){
		assertEquals("Tester", player.getName());
	}
	
	@Test
	public void movingThePlayerChangesItsPlace(){
		player.moveTo(5);
		assertEquals(5, player.getPlace());
	}
	
	@Test
	public void incrementsThePlayersPurseWhenAnswersCorrectly(){
		player.answeredCorrectly();
		assertEquals(1, player.getPurse());
	}
}
