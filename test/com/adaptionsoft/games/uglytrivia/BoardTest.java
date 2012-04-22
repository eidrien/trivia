package com.adaptionsoft.games.uglytrivia;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class BoardTest {

	@Test
	public void verifyTheQuestionTypeAssociatedWithEachPlace(){
		Board board= new Board(12);
		assertEquals(QuestionDeck.POP, board.getPlaceCategory(0));
		assertEquals(QuestionDeck.SCIENCE, board.getPlaceCategory(1));
		assertEquals(QuestionDeck.SPORTS, board.getPlaceCategory(2));
		assertEquals(QuestionDeck.ROCK, board.getPlaceCategory(3));
		assertEquals(QuestionDeck.POP, board.getPlaceCategory(4));
		assertEquals(QuestionDeck.SCIENCE, board.getPlaceCategory(5));
		assertEquals(QuestionDeck.SPORTS, board.getPlaceCategory(6));
		assertEquals(QuestionDeck.ROCK, board.getPlaceCategory(7));
		assertEquals(QuestionDeck.POP, board.getPlaceCategory(8));
		assertEquals(QuestionDeck.SCIENCE, board.getPlaceCategory(9));
		assertEquals(QuestionDeck.SPORTS, board.getPlaceCategory(10));
		assertEquals(QuestionDeck.ROCK, board.getPlaceCategory(11));
	}
	
	@Test
	public void calculatesEndPlace(){
		Board board = new Board(12);
		assertEquals(3, board.getNextPosition(1, 2));
		assertEquals(1, board.getNextPosition(8, 5));
	}
}
