package com.adaptionsoft.games.uglytrivia;

import java.util.NoSuchElementException;

public class QuestionDeck {

	public static final int POP = 0;
	public static final int SCIENCE = 1;
	public static final int SPORTS = 2;
	public static final int ROCK = 3;
	
	private int type;
	private int nextQuestionId;
	
	public static String getQuestionTypeText(int type){
		switch(type){
		case POP: return "Pop";
		case SCIENCE: return "Science";
		case SPORTS: return "Sports";
		case ROCK: return "Rock";
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
