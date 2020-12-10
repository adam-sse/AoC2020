package day03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import util.Util;

public class RoutePlannerTest {

	@Test
	public void example() {
		Map map = new Map(Util.readInputFile("/day03/testinput.txt"));
		
		assertEquals(2, RoutePlanner.checkWithSlope(map, 1, 1));
		assertEquals(7, RoutePlanner.checkWithSlope(map, 1, 3));
		assertEquals(3, RoutePlanner.checkWithSlope(map, 1, 5));
		assertEquals(4, RoutePlanner.checkWithSlope(map, 1, 7));
		assertEquals(2, RoutePlanner.checkWithSlope(map, 2, 1));
	}
	
}
