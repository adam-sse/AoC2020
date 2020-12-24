package day24;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Util;

public class FloorTiler {

	private Set<Position> blackTiles;
	
	public FloorTiler(List<String> initialDirections) {
		blackTiles = calcFlippedTiles(initialDirections);
	}
	
	private static Set<Position> calcFlippedTiles(List<String> directions) {
		Set<Position> blackTiles = new HashSet<>(directions.size());
		
		for (String line : directions) {
			Position pos = new Position(0, 0);
			
			while (!line.isEmpty()) {
				Direction dir = Direction.fromString(line);
				line = line.substring(dir.getNumChars());
				pos.move(dir);
			}
			
			if (blackTiles.contains(pos)) {
				blackTiles.remove(pos);
			} else {
				blackTiles.add(pos);
			}
		}
		
		return blackTiles;
	}
	
	public int getNumBlackTiles() {
		return blackTiles.size();
	}
	
	public void simulateDay() {
		Set<Position> newBlackTiles = new HashSet<>(blackTiles.size());
		
		Set<Position> neighborsOfBlackTiles = new HashSet<>(blackTiles.size() * 6);
		
		for (Position blackTile : blackTiles) {
			int numBlackNeighbors = 0;
			for (Position neighbor : blackTile.iterateNeighbors()) {
				if (blackTiles.contains(neighbor)) {
					numBlackNeighbors++;
				} else {
					neighborsOfBlackTiles.add(neighbor);
				}
			}
			
			if (numBlackNeighbors != 0 && numBlackNeighbors <= 2) {
				newBlackTiles.add(blackTile);
			}
		}
		
		for (Position whiteTile : neighborsOfBlackTiles) {
			int numBlackNeighbors = 0;
			for (Position neighbor : whiteTile.iterateNeighbors()) {
				if (blackTiles.contains(neighbor)) {
					numBlackNeighbors++;
				}
			}
			
			if (numBlackNeighbors == 2) {
				newBlackTiles.add(whiteTile);
			}
		}
		
		this.blackTiles = newBlackTiles;
	}
	
	public static void main(String[] args) {
		FloorTiler ft = new FloorTiler(Util.readInputFile("/day24/input.txt"));
		System.out.println(ft.getNumBlackTiles());
		
		for (int i = 0; i < 100; i++) {
			ft.simulateDay();
		}
		System.out.println(ft.getNumBlackTiles());
	}
	
}
