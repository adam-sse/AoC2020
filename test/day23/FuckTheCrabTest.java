package day23;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FuckTheCrabTest {

	@Test
	public void example10Moves() {
		FuckTheCrab ftc = new FuckTheCrab("389125467", false);
		ftc.moves(10);
		assertEquals("92658374", ftc.cupsAfter1ToString());
	}
	
	@Test
	public void example100Moves() {
		FuckTheCrab ftc = new FuckTheCrab("389125467", false);
		ftc.moves(100);
		assertEquals("67384529", ftc.cupsAfter1ToString());
	}
	
	@Test
	public void example10000000MovesExtended() {
		FuckTheCrab ftc = new FuckTheCrab("389125467", true);
		ftc.moves(10000000);
		assertEquals(149245887792L, ftc.multiplyCupsAfter1());
	}
	
}
