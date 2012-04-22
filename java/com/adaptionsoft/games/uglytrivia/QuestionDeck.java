package com.adaptionsoft.games.uglytrivia;

import java.util.NoSuchElementException;

public class QuestionDeck {

	private int type;
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
	
	public QuestionDeck(int type) {
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
		return getQuestionTypeText(type) + " Question " + (nextQuestionId++);
	}

	public void askQuestion(){
		System.out.println("The category is " + getQuestionTypeText(type));
		System.out.println(getNext());		
	}
}
