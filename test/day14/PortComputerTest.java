package day14;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class PortComputerTest {

	@Test
	public void example() {
		PortComputer pc = new PortComputer(Arrays.asList(
				"mask = 000000000000000000000000000000X1001X",
				"mem[42] = 100",
				"mask = 00000000000000000000000000000000X0XX",
				"mem[26] = 1"
				));
		
		pc.run();
		assertEquals(208, pc.sumMemory());
	}
	
}
