package day11;

import java.util.List;

import util.Util;

public class ConwaysGameOfSeats {

	public enum State {
		FLOOR('.'),
		EMPTY('L'),
		OCCUPIED('#');

		private char ch;
		
		private State(char ch) {
			this.ch = ch;
		}
		
		@Override
		public String toString() {
			return Character.toString(ch);
		}
		
	}
	
	private State[][] map;
	
	public ConwaysGameOfSeats(List<String> lines) {
		map = new State[lines.size()][lines.get(0).length()];
		
		int i = 0;
		for (String line : lines) {
			int j = 0;
			for (char c : line.toCharArray()) {
				switch (c) {
				case '.':
					map[i][j++] = State.FLOOR;
					break;
				case '#':
					map[i][j++] = State.OCCUPIED;
					break;
				case 'L':
					map[i][j++] = State.EMPTY;
					break;
				}
			}
			i++;
		}
	}
	
	public void print() {
		for (State[] row : map) {
			for (State seat : row) {
				System.out.print(seat);
			}
			System.out.println();
		}
	}
	
	private int occupiedSeatInDirection(int startI, int startJ, int incI, int incJ) {
		int currentI = startI + incI;
		int currentJ = startJ + incJ;
		
		State found = State.FLOOR;
		while (currentI >= 0 && currentI < map.length && currentJ >= 0 && currentJ < map[currentI].length
				&& found == State.FLOOR) {
			
			found = map[currentI][currentJ];
			
			currentI += incI;
			currentJ += incJ;
		}
		
		return found == State.OCCUPIED ? 1 : 0;
	}
	
	int getNumNeighbors(int i, int j) {
		int numNeighbors = 0;
		
		// Part 1:
//		for (int ii = i - 1; ii <= i + 1; ii++) {
//			for (int jj = j - 1; jj <= j + 1; jj++) {
//				if ((ii != i || jj != j) && ii >= 0 && ii < map.length && jj >= 0 && jj < map[ii].length) {
//					if (map[ii][jj] == State.OCCUPIED) {
//						numNeighbors++;
//					}
//				}
//			}
//		}
		
		// Part 2:
		for (int incI = -1; incI <= 1; incI++) {
			for (int incJ = -1; incJ <= 1; incJ++) {
				if (incI != 0 || incJ != 0) {
					numNeighbors += occupiedSeatInDirection(i, j, incI, incJ);
				}
			}
		}
		
		return numNeighbors;
	}
	
	public boolean iteration() {
		boolean changed = false;
		
		State[][] nextState = new State[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				
				State currentState = map[i][j];
				if (currentState == State.EMPTY && getNumNeighbors(i, j) == 0) {
					nextState[i][j] = State.OCCUPIED;
					changed = true;
					
				} else if (currentState == State.OCCUPIED && getNumNeighbors(i, j) >= 5) {
					nextState[i][j] = State.EMPTY;
					changed = true;
					
				} else {
					nextState[i][j] = currentState;
				}
			}
		}
		
		map = nextState;
		
		return changed;
	}
	
	public void runUntilStable() {
		boolean changed;
		do {
			changed = iteration();
		} while (changed);
	}
	
	public int countOccupied() {
		int count = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == State.OCCUPIED) {
					count++;
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		ConwaysGameOfSeats cgos = new ConwaysGameOfSeats(Util.readInputFile("/day11/input.txt"));
		cgos.runUntilStable();
		System.out.println(cgos.countOccupied());
	}
	
}
