package day22;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import util.Util;

public class CombatTest {

	@Test
	public void scoreExample() {
		Combat combat = new Combat(Util.readGroupedLineInputFile("/day22/example.txt"));
		combat.playUntilVictory();
		assertEquals(306, combat.calculateScore());
	}
	
}
