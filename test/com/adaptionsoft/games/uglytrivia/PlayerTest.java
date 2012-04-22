package com.adaptionsoft.games.uglytrivia;

import static org.junit.Assert.*;

import org.junit.Test;


public class PlayerTest {

	@Test
	public void newPlayerIsOutOfPenaltyBox(){
		Player player = new Player("Tester");
		assertFalse(player.isInPenaltyBox());
	}
}
