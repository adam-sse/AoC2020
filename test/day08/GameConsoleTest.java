package day08;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class GameConsoleTest {

	@Test
	public void loopExample() {
		GameConsole console = new GameConsole(Arrays.asList(
				"nop +0",
				"acc +1",
				"jmp +4",
				"acc +3",
				"jmp -3",
				"acc -99",
				"acc +1",
				"jmp -4",
				"acc +6"));
		
		assertTrue(console.isLoop());
	}
	
	@Test
	public void findNonLoopExample() {
		GameConsole console = new GameConsole(Arrays.asList(
				"nop +0",
				"acc +1",
				"jmp +4",
				"acc +3",
				"jmp -3",
				"acc -99",
				"acc +1",
				"jmp -4",
				"acc +6"));
		
		assertEquals(8, console.findNonLoopMutation());
	}
	
}
