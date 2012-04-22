package com.adaptionsoft.games.trivia.runner;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;


public class GameRunnerTest{

	@Test
	public void runWithoutChanging(){
		OutputStreamAccumulator outputStreamAccumulator = new OutputStreamAccumulator();
		System.setOut(new PrintStream(outputStreamAccumulator)); 
		GameRunner.main(null);
		assertEquals(0, outputStreamAccumulator.accumulatedValue);
	}
	
	class OutputStreamAccumulator extends OutputStream {

		int accumulatedValue;
		
		@Override
		public void write(int value) throws IOException {
			accumulatedValue += value;
		}
		
	}
}
