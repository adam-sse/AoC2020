package day11;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ConwaysGameOfSeatsTest {

	@Test
	public void example() {
		List<String> input = Arrays.asList(
				"L.LL.LL.LL",
				"LLLLLLL.LL",
				"L.L.L..L..",
				"LLLL.LL.LL",
				"L.LL.LL.LL",
				"L.LLLLL.LL",
				"..L.L.....",
				"LLLLLLLLLL",
				"L.LLLLLL.L",
				"L.LLLLL.LL"
			);
		
		ConwaysGameOfSeats cgos = new ConwaysGameOfSeats(input);
		cgos.runUntilStable();
		
		assertEquals(26, cgos.countOccupied());
	}
	
	@Test
	public void visibleNeighbors1() {
		List<String> input = Arrays.asList(
				".##.##.",
				"#.#.#.#",
				"##...##",
				"...L...",
				"##...##",
				"#.#.#.#",
				".##.##."
				);
		
		ConwaysGameOfSeats cgos = new ConwaysGameOfSeats(input);
		assertEquals(0, cgos.getNumNeighbors(3, 3));
	}
	
	@Test
	public void visibleNeighbors2() {
		List<String> input = Arrays.asList(
				".......#.",
				"...#.....",
				".#.......",
				".........",
				"..#L....#",
				"....#....",
				".........",
				"#........",
				"...#....."
				);
		
		ConwaysGameOfSeats cgos = new ConwaysGameOfSeats(input);
		assertEquals(8, cgos.getNumNeighbors(4, 3));
	}
	
	@Test
	public void visibleNeighbors3() {
		List<String> input = Arrays.asList(
				".............",
				".L.L.#.#.#.#.",
				"............."
				);
		
		ConwaysGameOfSeats cgos = new ConwaysGameOfSeats(input);
		assertEquals(0, cgos.getNumNeighbors(1, 1));
	}
	
}
