package day3;

import java.util.List;

public class Map {

	private boolean[][] treemap;
	
	private int width;
	
	public Map(List<String> input) {
		width = input.get(0).length();
		treemap = new boolean[input.size()][width];
		
		for (int row = 0; row < input.size(); row++) {
			String line = input.get(row);
			for (int column = 0; column < width; column++) {
				if (line.charAt(column) == '#') {
					treemap[row][column] = true;
				}
			}
		}
	}
	
	public int getNumRows() {
		return treemap.length;
	}
	
	public boolean treeAt(int row, int column) {
		return treemap[row][column % width];
	}
	
}
