package com.adaptionsoft.games.uglytrivia;

import java.util.NoSuchElementException;

public class QuestionDeck {

	private String type;
	private int nextQuestionId;
	
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
