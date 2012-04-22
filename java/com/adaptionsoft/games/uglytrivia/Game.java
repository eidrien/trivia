package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Game {
	
	List<Player> players;
	QuestionDeck[] questions;
	Board board;
    
    Player currentPlayer;
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	players = new ArrayList<Player>();
    	questions = new QuestionDeck[Board.TYPES_OF_QUESTIONS];
       	for(int i=0;i<Board.TYPES_OF_QUESTIONS;i++){
    		questions[i] = new QuestionDeck(i);
    	}
       	board = new Board();
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
		int newPlace = board.getNextPosition(getCurrentPlayersPlace(), roll);
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
		questions[currentQuestionsCategory()].askQuestion();
	}
	
	// randomly return a category
	int currentQuestionsCategory() {
		int currentPlayersPlace = getCurrentPlayersPlace();
		return currentPlayersPlace % Board.TYPES_OF_QUESTIONS;
	}

	public boolean wasCorrectlyAnswered() {
		if (isCurrentPlayerInPenaltyBox() && !isGettingOutOfPenaltyBox){
			nextPlayersTurn();
			return false;
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
		return false;
	}

	/**
	 * Player wins when its purse contains an amount different than 6.
	 */
	private boolean didPlayerWin() {
		return getCurrentPlayersPurse() == 6;
	}
}
