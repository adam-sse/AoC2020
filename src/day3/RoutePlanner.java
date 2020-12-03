package day3;

import util.Util;

public class RoutePlanner {

	static int checkWithSlope(Map map, int rowIncrement, int columnIncrement) {
		int row = 0;
		int column = 0;
		int numTrees = 0;
		do {
			if (map.treeAt(row, column)) {
				numTrees++;
			}
			
			row += rowIncrement;
			column += columnIncrement;
		} while (row < map.getNumRows());
		
		return numTrees;
	}
	
	public static void main(String[] args) {
		Map map = new Map(Util.readInputFile("/day3/input.txt"));
		
		System.out.println("1,1 " + checkWithSlope(map, 1, 1));
		System.out.println("1,3 " + checkWithSlope(map, 1, 3));
		System.out.println("1,5 " + checkWithSlope(map, 1, 5));
		System.out.println("1,7 " + checkWithSlope(map, 1, 7));
		System.out.println("2,1 " + checkWithSlope(map, 2, 1));
		
		System.out.println(checkWithSlope(map, 1, 1)
				* checkWithSlope(map, 1, 3)
				* checkWithSlope(map, 1, 5)
				* checkWithSlope(map, 1, 7)
				* checkWithSlope(map, 2, 1));
	}
	
}
