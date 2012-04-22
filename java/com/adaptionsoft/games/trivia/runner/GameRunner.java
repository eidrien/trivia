
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	private static boolean winner;

	public static void main(String[] args) {
		Random rand = new Random(12L);
		runTrivia(rand);
		
	}

	private static void runTrivia(Random rand) {
		Game aGame = new Game(12);
		
		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");
		
		do {
			
			aGame.roll(rand.nextInt(5) + 1);
			
			if (rand.nextInt(9) == 7) {
				winner = aGame.wasIncorrectlyAnswered();
			} else {
				winner = aGame.wasCorrectlyAnswered();
			}
			
			
			
		} while (!winner);
	}
}
