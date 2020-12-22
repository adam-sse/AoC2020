package day22;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import util.Util;

public class RecursiveCombatTest {

	@Test
	public void scoreExample() {
		RecursiveCombat combat = new RecursiveCombat(Util.readGroupedLineInputFile("/day22/example.txt"));
		combat.playUntilVictory();
		assertEquals(291, combat.calculateScore());
	}
	
	@Test
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	public void avoidInfinite() {
		RecursiveCombat combat = new RecursiveCombat(Util.readGroupedLineInputFile("/day22/infinite.txt"));
		assertTimeout(Duration.ofSeconds(2), combat::playUntilVictory);
	}
	
}
