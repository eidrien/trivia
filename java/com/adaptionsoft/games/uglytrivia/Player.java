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
		System.out.println(getName() 
				+ "'s new location is " 
				+ getPlace());
	}

	public void answeredCorrectly() {
		this.purse++;
		System.out.println("Answer was correct!!!!");
		System.out.println(getName() 
				+ " now has "
				+ getPurse()
				+ " Gold Coins.");
	}

	public void answeredIncorrectly() {
		System.out.println("Question was incorrectly answered");
		System.out.println(getName()+ " was sent to the penalty box");
		this.inPenaltyBox = true;
	}

	public boolean hasWon() {
		return getPurse() == 6;
	}

}
