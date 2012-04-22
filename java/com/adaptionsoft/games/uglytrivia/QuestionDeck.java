package com.adaptionsoft.games.uglytrivia;

import java.util.NoSuchElementException;

public class QuestionDeck {

	private String type;
	private int nextQuestionId;
	
	public static String getQuestionTypeText(int type){
		switch(type){
		case Board.POP: return "Pop";
		case Board.SCIENCE: return "Science";
		case Board.SPORTS: return "Sports";
		case Board.ROCK: return "Rock";
		}
		return null;
	}
	
	public QuestionDeck(String type) {
		this.type = type;
		this.nextQuestionId = 0;
	}

	public int size() {
		return 50;
	}

	public String getNext() {
		if(nextQuestionId >= size()){
			throw new NoSuchElementException();
		}
		return type + " Question " + (nextQuestionId++);
	}

}
