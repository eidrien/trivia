package com.adaptionsoft.games.uglytrivia;

public class Player {

	private String name;
	private int place;
	private int purse;
	private boolean inPenaltyBox;


	public Player(String name) {
		this.name = name;
	}

	public boolean isInPenaltyBox() {
		return inPenaltyBox;
	}

	public String getName(){
		return name;
	}

	public int getPlace() {
		return place;
	}

	public int getPurse() {
		return purse;
	}

	public void moveTo(int newPlace) {
		this.place = newPlace;
	}

	public void answeredCorrectly() {
		this.purse++;
	}

}
