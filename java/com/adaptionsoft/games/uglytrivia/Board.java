package com.adaptionsoft.games.uglytrivia;

public class Board {

	public static final int POP = 0;
	public static final int SCIENCE = 1;
	public static final int SPORTS = 2;
	public static final int ROCK = 3;
	
	public static final int TYPES_OF_QUESTIONS = 4;
	
	public static final int BOARD_SIZE = 12;
	
	public int getNextPosition(int currentPlace, int roll) {
		int newPlace = currentPlace + roll;
		if (newPlace >= BOARD_SIZE) newPlace = newPlace - BOARD_SIZE;
		return newPlace;
	}

	public int getQuestionCategory(int place) {
		return place % TYPES_OF_QUESTIONS;
	}

}
