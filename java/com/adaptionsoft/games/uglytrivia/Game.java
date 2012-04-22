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
    int[] highscores= new int[6];

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
	
	public boolean remove(String playerName) {
	  playerNames.remove(howManyPlayers());
	  return true;
	}
	
	public int howManyPlayers() {
		return playerNames.size();
	}

	public void roll(int roll) {
		System.out.println(getCurrentPlayersName() + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				System.out.println(getCurrentPlayersName() + " is getting out of the penalty box");
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
				
				System.out.println(getCurrentPlayersName() 
						+ "'s new location is " 
						+ places[currentPlayer]);
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(getCurrentPlayersName() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
			
			System.out.println(getCurrentPlayersName() 
					+ "'s new location is " 
					+ places[currentPlayer]);
			System.out.println("The category is " + currentCategory());
			askQuestion();
		}
		
	}

	private String getCurrentPlayersName() {
		return playerNames.get(currentPlayer);
	}

	private void askQuestion() {
		if (currentCategory() == POP)
			System.out.println(popQuestions.removeFirst());
		if (currentCategory() == SCIENCE)
			System.out.println(scienceQuestions.removeFirst());
		if (currentCategory() == SPORTS)
			System.out.println(sportsQuestions.removeFirst());
		if (currentCategory() == ROCK)
			System.out.println(rockQuestions.removeFirst());		
	}
	
	// randomly return a category
	private String currentCategory() {
		if (places[currentPlayer] == 0) return POP;
		if (places[currentPlayer] == 4) return POP;
		if (places[currentPlayer] == 8) return POP;
		if (places[currentPlayer] == 1) return SCIENCE;
		if (places[currentPlayer] == 5) return SCIENCE;
		if (places[currentPlayer] == 9) return SCIENCE;
		if (places[currentPlayer] == 2) return SPORTS;
		if (places[currentPlayer] == 6) return SPORTS;
		if (places[currentPlayer] == 10) return SPORTS;
		return ROCK;
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[currentPlayer]++;
				System.out.println(getCurrentPlayersName() 
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == playerNames.size()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == playerNames.size()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {
		
			System.out.println("Answer was corrent!!!!");
			purses[currentPlayer]++;
			System.out.println(getCurrentPlayersName() 
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == playerNames.size()) currentPlayer = 0;
			
			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(getCurrentPlayersName()+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == playerNames.size()) currentPlayer = 0;
		return true;
	}

	/**
	 * Tells if the last player won.
	 */
	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
