package day15;

import java.util.HashMap;
import java.util.Map;

public class MemoryGame {

	private Map<Integer, Integer> lastTimeSaid;
	
	private int turn;
	
	private int lastNumber;
	
	public MemoryGame(String startingNumbers) {
		lastTimeSaid = new HashMap<>(4000000);
		
		String[] split = startingNumbers.split(",");
		lastNumber = Integer.parseInt(split[0]);
		turn = 1;
		
		for (int i = 1; i < split.length; i++) {
			lastTimeSaid.put(lastNumber, turn);
			turn++;
			lastNumber = Integer.parseInt(split[i]);
		}
	}
	
	private void nextNumber() {
		int currentNumber = 0;
		if (lastTimeSaid.containsKey(lastNumber)) {
			currentNumber = turn - lastTimeSaid.get(lastNumber);
		}
		
		lastTimeSaid.put(lastNumber, turn);
		
		turn++;
		lastNumber = currentNumber;
	}
	
	public int getNumberAtTurn(int turn) {
		while (this.turn < turn) {
			nextNumber();
		}
		
		return lastNumber;
	}
	
	public static void main(String[] args) {
		MemoryGame mg = new MemoryGame("19,20,14,0,9,1");
		System.out.println(mg.getNumberAtTurn(2020));
		System.out.println(mg.getNumberAtTurn(30000000));
	}
	
}
