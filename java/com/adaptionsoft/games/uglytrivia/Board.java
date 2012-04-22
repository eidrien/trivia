package com.adaptionsoft.games.uglytrivia;

public class Board {

	public static final int TYPES_OF_PLACES = 4;
	
	public int size;
	
	public Board(int size){
		this.size = size;
	}
	
	public int getNextPosition(int currentPlace, int roll) {
		int newPlace = currentPlace + roll;
		if (newPlace >= size) newPlace = newPlace - size;
		return newPlace;
	}

	public int getQuestionCategory(int place) {
		return place % TYPES_OF_PLACES;
	}
	
}
