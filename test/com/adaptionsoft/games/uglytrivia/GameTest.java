package com.adaptionsoft.games.uglytrivia;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;


public class GameTest {

	@Test
	public void movingAPlayerChangesItsPlace(){
		Game game = new Game(12);
		game.add("Tester");
		game.roll(2);
		assertEquals(2, game.currentPlayer.getPlace());
	}
	
	@Test
	public void movingAPlayerOverTheEndOfTheBoardMakesItContinueInTheBeginning(){
		Game game = new Game(12);
		game.add("Tester");
		game.roll(2);
		game.roll(10);
		game.roll(2);
		assertEquals(2, game.currentPlayer.getPlace());
	}
	
	@Test
	public void answeringCorrectlyAddsOneToTheCurrentPlayersPurse(){
		Game game = new Game(12);
		game.add("Tester");
		game.wasCorrectlyAnswered();
		assertEquals(1, game.currentPlayer.getPurse());
	}
	
	@Test
	public void playerGoesToPenaltyBoxWhenAnsweringIncorrectly(){
		Game game = new Game(12);
		game.add("Tester");
		game.wasIncorrectlyAnswered();
		assertTrue(game.currentPlayer.isInPenaltyBox());
	}
	
	@Test
	public void afterAPlayerAnswersTheCurrentPlayerChanges(){
		Game game = new Game(12);
		game.add("Tester1");
		game.add("Tester2");
		game.add("Tester3");
		game.wasCorrectlyAnswered();
		assertEquals("Tester2", game.currentPlayer.getName());
		game.wasIncorrectlyAnswered();
		assertEquals("Tester3", game.currentPlayer.getName());
		game.wasCorrectlyAnswered();
		assertEquals("Tester1", game.currentPlayer.getName());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void breaksAfterAskingMoreThan50QuestionsOfTheSameType(){
		Game game = new Game(12);
		game.add("Tester1");
		for(int i=0; i<51; i++){
			game.roll(0);
		}
	}
	
	@Test
	public void verifyTheQuestionTypeAssociatedWithEachPlace(){
		Game game = new Game(12);
		game.add("Tester1");
		assertEquals(QuestionDeck.POP, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(QuestionDeck.SCIENCE, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(QuestionDeck.SPORTS, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(QuestionDeck.ROCK, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(QuestionDeck.POP, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(QuestionDeck.SCIENCE, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(QuestionDeck.SPORTS, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(QuestionDeck.ROCK, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(QuestionDeck.POP, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(QuestionDeck.SCIENCE, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(QuestionDeck.SPORTS, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(QuestionDeck.ROCK, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(QuestionDeck.POP, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(QuestionDeck.SCIENCE, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(QuestionDeck.SPORTS, game.currentQuestionsCategory());
		game.roll(1);
		assertEquals(QuestionDeck.ROCK, game.currentQuestionsCategory());
		game.roll(1);
	}
	
	@Test
	public void playerWithSixCorrectAnswersWins(){
		Game game = new Game(12);
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
		Game game = new Game(12);
		game.add("Tester1");
		assertFalse(game.wasIncorrectlyAnswered());
	}
}
