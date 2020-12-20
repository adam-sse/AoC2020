package day20;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class TileTest {

	@Test
	public void someSideMatches() {
		Tile tile1951 = new Tile(Arrays.asList(
				"Tile 1951:",
				"#.##...##.",
				"#.####...#",
				".....#..##",
				"#...######",
				".##.#....#",
				".###.#####",
				"###.##.##.",
				".###....#.",
				"..#.#..#.#",
				"#...##.#.."
				));
		
		Tile tile2311 = new Tile(Arrays.asList(
				"Tile 2311:",
				"..##.#..#.",
				"##..#.....",
				"#...##..#.",
				"####.#...#",
				"##.##.###.",
				"##...#.###",
				".#.#.#..##",
				"..#....#..",
				"###...#.#.",
				"..###..###"
				));
		
		Tile tile1427 = new Tile(Arrays.asList(
				"Tile 1427:",
				"###.##.#..",
				".#..#.##..",
				".#.##.#..#",
				"#.#.#.##.#",
				"....#...##",
				"...##..##.",
				"...#.#####",
				".#.####.#.",
				"..#..###.#",
				"..##.#..#."
				));
		
		Tile tile3079 = new Tile(Arrays.asList(
				"Tile 3079:",
				"#.#.#####.",
				".#..######",
				"..#.......",
				"######....",
				"####.#..#.",
				".#...#.##.",
				"#.#####.##",
				"..#.###...",
				"..#.......",
				"..#.###..."
				));
		
		assertTrue(tile1951.someSideMatches(tile2311));
		assertTrue(tile2311.someSideMatches(tile1951));
		
		assertFalse(tile1951.someSideMatches(tile1427));
		assertFalse(tile1427.someSideMatches(tile1951));
		
		assertTrue(tile2311.someSideMatches(tile1427));
		assertTrue(tile1427.someSideMatches(tile2311));
		
		assertTrue(tile3079.someSideMatches(tile2311));
		assertTrue(tile2311.someSideMatches(tile3079));
	}
	
	@Test
	public void flipHorizontal() {
		Tile tile1951 = new Tile(Arrays.asList(
				"Tile 1951:",
				"#.##...##.",
				"#.####...#",
				".....#..##",
				"#...######",
				".##.#....#",
				".###.#####",
				"###.##.##.",
				".###....#.",
				"..#.#..#.#",
				"#...##.#.."
				));
		
		tile1951.flipHorizontal();
		
		assertEquals(
				  ".##...##.#\n"
				+ "#...####.#\n"
				+ "##..#.....\n"
				+ "######...#\n"
				+ "#....#.##.\n"
				+ "#####.###.\n"
				+ ".##.##.###\n"
				+ ".#....###.\n"
				+ "#.#..#.#..\n"
				+ "..#.##...#\n"
				, tile1951.toString());
	}
	
	@Test
	public void rotateLeft() {
		Tile tile1951 = new Tile(Arrays.asList(
				"Tile 1951:",
				"#.##...##.",
				"#.####...#",
				".....#..##",
				"#...######",
				".##.#....#",
				".###.#####",
				"###.##.##.",
				".###....#.",
				"..#.#..#.#",
				"#...##.#.."
				));
		
		tile1951.rotateLeft();
		
		assertEquals(
				  ".#####..#.\n"
				+ "#.##.###..\n"
				+ "#..#.##.##\n"
				+ "...#.#....\n"
				+ ".###.##..#\n"
				+ ".#.##.#.##\n"
				+ "##...#.#..\n"
				+ "##..#####.\n"
				+ "....####..\n"
				+ "##.#..#..#\n"
				, tile1951.toString());
	}
	
}
