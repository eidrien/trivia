package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Game {
	
	public static final int POP = 0;
	public static final int SCIENCE = 1;
	public static final int SPORTS = 2;
	public static final int ROCK = 3;
	
	public static final int BOARD_SIZE = 12;
	
	public static String getQuestionTypeText(int type){
		switch(type){
		case POP: return "Pop";
		case SCIENCE: return "Science";
		case SPORTS: return "Sports";
		case ROCK: return "Rock";
		}
		return null;
	}
	
	
	List<Player> players = new ArrayList<Player>();
	QuestionDeck[] questions = new QuestionDeck[4];
    
    Player currentPlayer;
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	for(int i=0;i<4;i++){
    		questions[i] = new QuestionDeck(getQuestionTypeText(i));
    	}
    }
    
	/**
	 * Game is playable with two or more players.
	 */
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
		
		Player newPlayer = new Player(playerName);
		if(players.isEmpty()){
			currentPlayer = newPlayer;
		}
		players.add(newPlayer);
		
		
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + howManyPlayers());
		return true;
	}
	
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		System.out.println(getCurrentPlayersName() + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (isCurrentPlayerInPenaltyBox()) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				System.out.println(getCurrentPlayersName() + " is getting out of the penalty box");
				movePlayerAndAskQuestion(roll);
			} else {
				System.out.println(getCurrentPlayersName() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
			}	
		} else {
			movePlayerAndAskQuestion(roll);
		}
	}

	void movePlayerAndAskQuestion(int roll) {
		moveCurrentPlayer(roll);
		askQuestion();
	}

	private void moveCurrentPlayer(int roll) {
		int newPlace = getCurrentPlayersPlace() + roll;
		if (newPlace >= BOARD_SIZE) newPlace = newPlace - BOARD_SIZE;
		currentPlayer.moveTo(newPlace);
		System.out.println(getCurrentPlayersName() 
				+ "'s new location is " 
				+ getCurrentPlayersPlace());
	}

	int getCurrentPlayersPlace() {
		return currentPlayer.getPlace();
	}

	boolean isCurrentPlayerInPenaltyBox() {
		return currentPlayer.isInPenaltyBox();
	}

	String getCurrentPlayersName() {
		return currentPlayer.getName();
	}

	private void askQuestion() {
		System.out.println("The category is " + getQuestionTypeText(currentQuestionsCategory()));
		System.out.println(questions[currentQuestionsCategory()].getNext());
	}
	
	// randomly return a category
	int currentQuestionsCategory() {
		int currentPlayersPlace = getCurrentPlayersPlace();
		return currentPlayersPlace % 4;
	}

	public boolean wasCorrectlyAnswered() {
		if (isCurrentPlayerInPenaltyBox() && !isGettingOutOfPenaltyBox){
			nextPlayersTurn();
			return true;
		}
		
		currentPlayerAnsweredCorrectly();
		boolean winner = didPlayerWin();
		nextPlayersTurn();
		return winner;
	}

	private void currentPlayerAnsweredCorrectly() {
		System.out.println("Answer was correct!!!!");
		incrementCurrentPlayersPurse();
		System.out.println(getCurrentPlayersName() 
				+ " now has "
				+ getCurrentPlayersPurse()
				+ " Gold Coins.");
	}

	private void incrementCurrentPlayersPurse() {
		currentPlayer.answeredCorrectly();
	}

	int getCurrentPlayersPurse() {
		return currentPlayer.getPurse();
	}

	private void nextPlayersTurn() {
		int nextPlayerPosition = players.indexOf(currentPlayer) + 1;
		if (nextPlayerPosition == howManyPlayers()) nextPlayerPosition = 0;
		currentPlayer = players.get(nextPlayerPosition);
	}
	
	public boolean wasIncorrectlyAnswered(){
		System.out.println("Question was incorrectly answered");
		System.out.println(getCurrentPlayersName()+ " was sent to the penalty box");
		currentPlayer.answeredIncorrectly();
		
		nextPlayersTurn();
		return true;
	}

	/**
	 * Player wins when its purse contains an amount different than 6.
	 */
	private boolean didPlayerWin() {
		return !(getCurrentPlayersPurse() == 6);
	}
}
