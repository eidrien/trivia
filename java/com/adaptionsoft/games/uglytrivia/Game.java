package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Game {
	
	List<Player> players;
	QuestionDeck[] questions;
	Board board;
    
    Player currentPlayer;
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(int boardSize){
    	players = new ArrayList<Player>();
    	questions = new QuestionDeck[Board.TYPES_OF_PLACES];
       	for(int i=0;i<Board.TYPES_OF_PLACES;i++){
    		questions[i] = new QuestionDeck(i);
    	}
       	board = new Board(boardSize);
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
		System.out.println(currentPlayer.getName() + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (currentPlayer.isInPenaltyBox()) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
				movePlayerAndAskQuestion(roll);
			} else {
				System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
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
		int newPlace = board.getNextPosition(currentPlayer.getPlace(), roll);
		currentPlayer.moveTo(newPlace);
	}

	private void askQuestion() {
		questions[currentQuestionsCategory()].askQuestion();
	}
	
	int currentQuestionsCategory() {
		int currentPlayersPlace = currentPlayer.getPlace();
		return board.getPlaceCategory(currentPlayersPlace);
	}

	public boolean wasCorrectlyAnswered() {
		if (currentPlayer.isInPenaltyBox() && !isGettingOutOfPenaltyBox){
			nextPlayersTurn();
			return false;
		}
		
		currentPlayer.answeredCorrectly();
		boolean winner = currentPlayer.hasWon();
		nextPlayersTurn();
		return winner;
	}

	private void nextPlayersTurn() {
		int nextPlayerPosition = players.indexOf(currentPlayer) + 1;
		if (nextPlayerPosition == howManyPlayers()) nextPlayerPosition = 0;
		currentPlayer = players.get(nextPlayerPosition);
	}
	
	public boolean wasIncorrectlyAnswered(){
		currentPlayer.answeredIncorrectly();
		nextPlayersTurn();
		return false;
	}

}
