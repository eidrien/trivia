package com.adaptionsoft.games.uglytrivia;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class BoardTest {

	@Test
	public void verifyTheQuestionTypeAssociatedWithEachPlace(){
		Board board= new Board(12);
		assertEquals(QuestionDeck.POP, board.getQuestionCategory(0));
		assertEquals(QuestionDeck.SCIENCE, board.getQuestionCategory(1));
		assertEquals(QuestionDeck.SPORTS, board.getQuestionCategory(2));
		assertEquals(QuestionDeck.ROCK, board.getQuestionCategory(3));
		assertEquals(QuestionDeck.POP, board.getQuestionCategory(4));
		assertEquals(QuestionDeck.SCIENCE, board.getQuestionCategory(5));
		assertEquals(QuestionDeck.SPORTS, board.getQuestionCategory(6));
		assertEquals(QuestionDeck.ROCK, board.getQuestionCategory(7));
		assertEquals(QuestionDeck.POP, board.getQuestionCategory(8));
		assertEquals(QuestionDeck.SCIENCE, board.getQuestionCategory(9));
		assertEquals(QuestionDeck.SPORTS, board.getQuestionCategory(10));
		assertEquals(QuestionDeck.ROCK, board.getQuestionCategory(11));
	}
	
	@Test
	public void calculatesEndPlace(){
		Board board = new Board(12);
		assertEquals(3, board.getNextPosition(1, 2));
		assertEquals(1, board.getNextPosition(8, 5));
	}
}
