package day17;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class ConwayCubeTest {

	@Test
	public void example() {
		ConwayCube cube = new ConwayCube(Arrays.asList(
				".#.",
				"..#",
				"###"
		));
		
		for (int i = 0; i < 6; i++) {
			cube.oneTick();
		}
		assertEquals(848, cube.countActive());
	}
	
}
