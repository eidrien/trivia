package com.adaptionsoft.games.uglytrivia;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;


public class QuestionDeckTest {

	@Test
	public void contains50Questions(){
		QuestionDeck questions = new QuestionDeck(Board.ROCK);
		assertEquals(50, questions.size());
	}
	
	@Test
	public void everyNewQuestionIncrementsItsIdentifierInTheText(){
		QuestionDeck questions = new QuestionDeck(Board.ROCK);
		assertEquals("Rock Question 0", questions.getNext());
		assertEquals("Rock Question 1", questions.getNext());
		assertEquals("Rock Question 2", questions.getNext());
		assertEquals("Rock Question 3", questions.getNext());
		assertEquals("Rock Question 4", questions.getNext());
		assertEquals("Rock Question 5", questions.getNext());
	}
	
	@Test
	public void haveTheTypeAtTheBeginning(){
		QuestionDeck questions = new QuestionDeck(Board.ROCK);
		assertTrue(questions.getNext().startsWith("Rock"));
	}
	
	@Test(expected = NoSuchElementException.class)
	public void breaksWhenAskingMoreThan50Questions(){
		QuestionDeck questions = new QuestionDeck(Board.ROCK);
		for(int i=0; i<51; i++){
			questions.getNext();
		}
	}
}