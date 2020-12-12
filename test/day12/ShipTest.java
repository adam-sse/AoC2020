package day12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class ShipTest {

	@Test
	public void example() {
		Ship ship = new Ship(Arrays.asList(
			"F10",
			"N3",
			"F7",
			"R90",
			"F11"
		));
		
		ship.run();
		assertEquals(286, ship.getDistanceManhattan());
	}
	
}
