package day20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.Util;

public class TileMatcher {

	private Map<Tile, List<Tile>> neighbors;
	
	private List<Tile> tiles;
	
	private Tile[][] image;
	
	public TileMatcher(List<List<String>> tiles) {
		this.tiles = new ArrayList<>(tiles.size());
		for (List<String> tile : tiles) {
			this.tiles.add(new Tile(tile));
		}
	}
	
	public void matchNeighbors() {
		this.neighbors = new HashMap<>();
		
		for (Tile tile : tiles) {
			neighbors.put(tile, new LinkedList<>());
		}
		
		for (int i = 0; i < tiles.size(); i++) {
			Tile t1 = tiles.get(i);
			for (int j = i + 1; j < tiles.size(); j++) {
				Tile t2 = tiles.get(j);
				
				if (t1.someSideMatches(t2)) {
					neighbors.get(t1).add(t2);
					neighbors.get(t2).add(t1);
				}
			}
		}
	}
	
	public long multiplyCornerIds() {
		long result = 1;
		for (Map.Entry<Tile, List<Tile>> entry : neighbors.entrySet()) {
			if (entry.getValue().size() == 2) {
				result *= entry.getKey().getId();
			}
		}
		return result;
	}
	
	private void fillTopRow(Set<Integer> usedIds, int size) {
		for (int column = 2; column < size; column++) {
			Tile previous = image[0][column - 1];
			
			// find the correct neighbor
			Tile correctNeighbor = null;
			for (Tile n : this.neighbors.get(previous)) {
				int wantedNeighborsNeighbors = 3;
				if (column == size - 1) {
					wantedNeighborsNeighbors = 2;
				}
				if (!usedIds.contains(n.getId()) && this.neighbors.get(n).size() == wantedNeighborsNeighbors) {
					correctNeighbor = n;
					break;
				}
			}
			
			image[0][column] = correctNeighbor;
			usedIds.add(correctNeighbor.getId());
		}
	}
	
	private void fillFurtherRows(Set<Integer> usedIds, int size) {
		for (int row = 1; row < size; row++) {
			for (int column = 0; column < size; column++) {
				Tile above = image[row - 1][column];
				
				Tile correctNeighbor = null;
				for (Tile n : this.neighbors.get(above)) {
					if (!usedIds.contains(n.getId())) {
						correctNeighbor = n;
						break;
					}
				}
				
				image[row][column] = correctNeighbor;
				usedIds.add(correctNeighbor.getId());
			}
		}
	}
	
	public void arrangeTiles() {
		final int size = (int) Math.sqrt(tiles.size());
		image = new Tile[size][size];
		Set<Integer> usedIds = new HashSet<>();
		
		// take a random corner piece as [0;0]
		Tile firstCorner = this.neighbors.entrySet().stream().filter(e -> e.getValue().size() == 2).findAny().get().getKey();
		image[0][0] = firstCorner;
		usedIds.add(firstCorner.getId());
		
		// take one neighbor as the right piece
		image[0][1] = this.neighbors.get(firstCorner).get(0);
		usedIds.add(image[0][1].getId());
		
		fillTopRow(usedIds, size);
		fillFurtherRows(usedIds, size);
		
		// make sure that the tiles are rotated/flipped correctly so that they match their neighbors
		// start with the first corner
		firstCorner.flipToMatchRightAndBottomIgnoreRev(image[0][1], image[1][0]);
		
		// now the leftmost column, based on the tile above
		for (int row = 1; row < size; row++) {
			image[row][0].flipToMatchAboveExact(image[row - 1][0]);
		}
		
		// now the rest, based on the tile to the left
		for (int row = 0; row < size; row++) {
			for (int column = 1; column < size; column++) {
				image[row][column].flipToMatchLeftExact(image[row][column - 1]);
			}
		}
	}
	
	public int getImageTileSize() {
		return image.length;
	}
	
	public int getImageId(int row, int column) {
		return image[row][column].getId();
	}
	
	private char[][] getImage() {
		char[][] result = new char[image.length * (Tile.SIZE - 2)][image.length * (Tile.SIZE - 2)];
		int i = 0;
		int j = 0;
		
		for (int row = 0; row < image.length; row++) {
			
			for (int innerRow = 1; innerRow < Tile.SIZE - 1; innerRow++) {
				
				for (int column = 0; column  < image[row].length; column++) {
					Tile t = image[row][column];
					
					for (int innerColumn = 1; innerColumn < Tile.SIZE - 1; innerColumn++) {
						result[i][j++] = t.getChar(innerRow, innerColumn);
					}
					
				}
				
				i++;
				j = 0;
			}
		}
		
		return result;
	}
	
	public int countMonsters() {
		char[][] image = getImage();
		
		int result = countMonsters(image);
		
		while (result == 0) {
			if (Math.random() < 0.5) {
				image = rotate(image);
			} else {
				image = flip(image);
			}
			
			result = countMonsters(image);
		}
		
		return result;
	}
	
	private static char[][] flip(char[][] image) {
		int size = image.length;
		char[][] newImage = new char[size][size];
		
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column ++) {
				newImage[row][column] = image[row][size - 1 - column];
			}
		}

		return newImage;
	}
	
	private static char[][] rotate(char[][] image) {
		int size = image.length;
		char[][] newImage = new char[size][size];
		
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column ++) {
				newImage[row][column] = image[column][size - 1 - row];
			}
		}

		return newImage;
	}
	
	private static int countMonsters(char[][] image) {
		int sum = 0;
		for (int i = 0; i < image.length - 2; i++) {
			for (int j = 0; j < image[i].length - 19; j++) {
				/*
				 * 01234567890123456789
				 *0                  # 
				 *1#    ##    ##    ###
				 *2 #  #  #  #  #  #   
				 * 
				 */
				
				if (allHash(image,
						i + 1, j + 0,
						i + 2, j + 1,
						i + 2, j + 4,
						i + 1, j + 5,
						i + 1, j + 6,
						i + 2, j + 7,
						i + 2, j + 10,
						i + 1, j + 11,
						i + 1, j + 12,
						i + 2, j + 13,
						i + 2, j + 16,
						i + 1, j + 17,
						i + 0, j + 18,
						i + 1, j + 18,
						i + 1, j + 19
					)) {
					sum++;
				}
			}
		}
		return sum;
	}
	
	private static boolean allHash(char[][] image, int... indices) {
		boolean allHash = true;
		for (int i = 0; i < indices.length; i += 2) {
			if (image[indices[i]][indices[i + 1]] != '#') {
				allHash = false;
				break;
			}
		}
		return allHash;
	}
	
	private int countHash() {
		int count = 0;
		for (char[] line : getImage()) {
			for (char ch : line) {
				if (ch == '#') {
					count++;
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		TileMatcher matcher = new TileMatcher(Util.readGroupedLineInputFile("/day20/input.txt"));
		
		matcher.matchNeighbors();
		System.out.println(matcher.multiplyCornerIds());
		
		matcher.arrangeTiles();
		System.out.println(matcher.countHash() - (matcher.countMonsters() * 15));
	}
	
}
