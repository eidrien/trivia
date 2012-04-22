package com.adaptionsoft.games.uglytrivia;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

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
	
	@Test
	public void answeringCorrectlyAddsOneToTheCurrentPlayersPurse(){
		Game game = new Game();
		game.add("Tester");
		game.wasCorrectlyAnswered();
		assertEquals(1, game.getCurrentPlayersPurse());
	}
	
	@Test
	public void playerGoesToPenaltyBoxWhenAnsweringIncorrectly(){
		Game game = new Game();
		game.add("Tester");
		game.wasIncorrectlyAnswered();
		assertTrue(game.isCurrentPlayerInPenaltyBox());
	}
	
	@Test
	public void afterAPlayerAnswersTheCurrentPlayerChanges(){
		Game game = new Game();
		game.add("Tester1");
		game.add("Tester2");
		game.add("Tester3");
		game.wasCorrectlyAnswered();
		assertEquals("Tester2", game.getCurrentPlayersName());
		game.wasIncorrectlyAnswered();
		assertEquals("Tester3", game.getCurrentPlayersName());
		game.wasCorrectlyAnswered();
		assertEquals("Tester1", game.getCurrentPlayersName());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void breaksAfterAskingMoreThan50QuestionsOfTheSameType(){
		Game game = new Game();
		game.add("Tester1");
		for(int i=0; i<51; i++){
			game.roll(0);
		}
	}
	
	@Test
	public void verifyTheQuestionTypeAssociatedWithEachPlace(){
		Game game = new Game();
		game.add("Tester1");
		assertEquals(Game.POP, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(Game.SCIENCE, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(Game.SPORTS, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(Game.ROCK, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(Game.POP, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(Game.SCIENCE, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(Game.SPORTS, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(Game.ROCK, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(Game.POP, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(Game.SCIENCE, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(Game.SPORTS, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(Game.ROCK, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(Game.POP, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(Game.SCIENCE, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(Game.SPORTS, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(Game.ROCK, game.currentQuestionsCategory());
		game.roll(1);
	}
	
	@Test
	public void playerWithSixCorrectAnswersWins(){
		Game game = new Game();
		game.add("Tester1");
		assertFalse(game.wasCorrectlyAnswered());
		assertFalse(game.wasCorrectlyAnswered());
		assertFalse(game.wasCorrectlyAnswered());
		assertFalse(game.wasCorrectlyAnswered());
		assertFalse(game.wasCorrectlyAnswered());
		assertTrue(game.wasCorrectlyAnswered());
	}
	
	@Test
	public void playerAnsweringIncorrectlyIsntWinning(){
		Game game = new Game();
		game.add("Tester1");
		assertFalse(game.wasIncorrectlyAnswered());
	}
}
