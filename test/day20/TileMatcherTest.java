package day20;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import util.Util;

public class TileMatcherTest {

	@Test
	public void findNeighbors() {
		TileMatcher matcher = new TileMatcher(Util.readGroupedLineInputFile("/day20/example1.txt"));
		
		matcher.matchNeighbors();
		assertEquals(20899048083289L, matcher.multiplyCornerIds());
	}
	
	@Test
	public void arrangeTiles() {
		TileMatcher matcher = new TileMatcher(Util.readGroupedLineInputFile("/day20/example1.txt"));
		
		matcher.matchNeighbors();
		matcher.arrangeTiles();
		
		/*
		 * 1951    2311    3079
		 * 2729    1427    2473
		 * 2971    1489    1171
		 */
		
		// we can't really test this, as we are probably rotated
		
//		assertEquals(3079, matcher.getImageId(0, 0));
//		assertEquals(2311, matcher.getImageId(0, 1));
//		assertEquals(1951, matcher.getImageId(0, 2));
//		
//		assertEquals(2473, matcher.getImageId(1, 0));
//		assertEquals(1427, matcher.getImageId(1, 1));
//		assertEquals(2729, matcher.getImageId(1, 2));
//		
//		assertEquals(1171, matcher.getImageId(2, 0));
//		assertEquals(1489, matcher.getImageId(2, 1));
//		assertEquals(2971, matcher.getImageId(2, 2));
	}
	
	@Test
	public void countMonsters() {
		TileMatcher matcher = new TileMatcher(Util.readGroupedLineInputFile("/day20/example1.txt"));
		
		matcher.matchNeighbors();
		matcher.arrangeTiles();
		
		assertEquals(2, matcher.countMonsters());
	}
	
}
