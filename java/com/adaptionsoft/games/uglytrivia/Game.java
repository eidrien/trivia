package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Game {
	
	public static final String POP = "Pop";
	public static final String SCIENCE = "Science";
	public static final String SPORTS = "Sports";
	public static final String ROCK = "Rock";
	
	
    List<String> playerNames = new ArrayList<String>();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];

    Deque<String> popQuestions = new LinkedList<String>();
    Deque<String> scienceQuestions = new LinkedList<String>();
    Deque<String> sportsQuestions = new LinkedList<String>();
    Deque<String> rockQuestions = new LinkedList<String>();
    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast(createPopQuestion(i));
			scienceQuestions.addLast(createScienceQuestion(i));
			sportsQuestions.addLast(createSportsQuestion(i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
    }

	private String createSportsQuestion(int i) {
		return createQuestion(SPORTS, i);
	}

	private String createScienceQuestion(int i) {
		return createQuestion(SCIENCE, i);
	}

	private String createPopQuestion(int i) {
		return createQuestion(POP, i);
	}

	public String createRockQuestion(int i){
		return createQuestion(ROCK, i);
	}
	
	public String createQuestion(String type, int index){
		return type + " Question " + index;
	}

	/**
	 * Game is playable with two or more players.
	 */
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
		
		
	    playerNames.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + playerNames.size());
		return true;
	}
	
	public int howManyPlayers() {
		return playerNames.size();
	}

	public void roll(int roll) {
		System.out.println(getCurrentPlayersName() + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (isCurrentPlayerInPenaltyBox()) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				System.out.println(getCurrentPlayersName() + " is getting out of the penalty box");
				moveCurrentPlayer(roll);
				
				System.out.println(getCurrentPlayersName() 
						+ "'s new location is " 
						+ getCurrentPlayersPlace());
				System.out.println("The category is " + currentQuestionsCategory());
				askQuestion();
			} else {
				System.out.println(getCurrentPlayersName() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			moveCurrentPlayer(roll);
			
			System.out.println(getCurrentPlayersName() 
					+ "'s new location is " 
					+ getCurrentPlayersPlace());
			System.out.println("The category is " + currentQuestionsCategory());
			askQuestion();
		}
		
	}

	private void moveCurrentPlayer(int roll) {
		places[currentPlayer] = getCurrentPlayersPlace() + roll;
		if (getCurrentPlayersPlace() > 11) places[currentPlayer] = getCurrentPlayersPlace() - 12;
	}

	private int getCurrentPlayersPlace() {
		return places[currentPlayer];
	}

	private boolean isCurrentPlayerInPenaltyBox() {
		return inPenaltyBox[currentPlayer];
	}

	private String getCurrentPlayersName() {
		return playerNames.get(currentPlayer);
	}

	private void askQuestion() {
		if (currentQuestionsCategory() == POP)
			System.out.println(popQuestions.removeFirst());
		if (currentQuestionsCategory() == SCIENCE)
			System.out.println(scienceQuestions.removeFirst());
		if (currentQuestionsCategory() == SPORTS)
			System.out.println(sportsQuestions.removeFirst());
		if (currentQuestionsCategory() == ROCK)
			System.out.println(rockQuestions.removeFirst());		
	}
	
	// randomly return a category
	private String currentQuestionsCategory() {
		int currentPlayersPlace = getCurrentPlayersPlace();
		if (currentPlayersPlace == 0) return POP;
		if (currentPlayersPlace == 4) return POP;
		if (currentPlayersPlace == 8) return POP;
		if (currentPlayersPlace == 1) return SCIENCE;
		if (currentPlayersPlace == 5) return SCIENCE;
		if (currentPlayersPlace == 9) return SCIENCE;
		if (currentPlayersPlace == 2) return SPORTS;
		if (currentPlayersPlace == 6) return SPORTS;
		if (currentPlayersPlace == 10) return SPORTS;
		return ROCK;
	}

	public boolean wasCorrectlyAnswered() {
		if (isCurrentPlayerInPenaltyBox()){
			if (isGettingOutOfPenaltyBox) {
				currentPlayerAnsweredCorrectly();
				
				boolean winner = didPlayerWin();
				nextPlayersTurn();
				
				return winner;
			} else {
				nextPlayersTurn();
				return true;
			}
			
			
			
		} else {
		
			currentPlayerAnsweredCorrectly();
			
			boolean winner = didPlayerWin();
			nextPlayersTurn();
			
			return winner;
		}
	}

	private void currentPlayerAnsweredCorrectly() {
		System.out.println("Answer was correct!!!!");
		purses[currentPlayer]++;
		System.out.println(getCurrentPlayersName() 
				+ " now has "
				+ purses[currentPlayer]
				+ " Gold Coins.");
	}

	private void nextPlayersTurn() {
		currentPlayer++;
		if (currentPlayer == playerNames.size()) currentPlayer = 0;
	}
	
	public boolean currentPlayerAnsweredIncorrectly(){
		System.out.println("Question was incorrectly answered");
		System.out.println(getCurrentPlayersName()+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		nextPlayersTurn();
		return true;
	}

	/**
	 * Player wins when its purse contains an amount different than 6.
	 */
	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
