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
		return false;
	}
}
