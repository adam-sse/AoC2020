package day24;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import util.Util;

public class FloorTilerTest {

	@Test
	public void countBlackTilesExample() {
		FloorTiler ft = new FloorTiler(Util.readInputFile("/day24/example.txt"));
		assertEquals(10, ft.getNumBlackTiles());
	}
	
	@Test
	public void daySimulationExample() {
		FloorTiler ft = new FloorTiler(Util.readInputFile("/day24/example.txt"));
		assertEquals(10, ft.getNumBlackTiles());
		
		Map<Integer, Integer> expected = new HashMap<>();
		expected.put(1, 15);
		expected.put(2, 12);
		expected.put(3, 25);
		expected.put(4, 14);
		expected.put(5, 23);
		expected.put(6, 28);
		expected.put(7, 41);
		expected.put(8, 37);
		expected.put(9, 49);
		expected.put(10, 37);
		
		expected.put(20, 132);
		expected.put(30, 259);
		expected.put(40, 406);
		expected.put(50, 566);
		expected.put(60, 788);
		expected.put(70, 1106);
		expected.put(80, 1373);
		expected.put(90, 1844);
		expected.put(100, 2208);

		for (int day = 1; day <= 100; day++) {
			ft.simulateDay();
			if (expected.containsKey(day)) {
				assertEquals(expected.get(day), ft.getNumBlackTiles(), "check day " + day);
			}
		}
	}
	
}
