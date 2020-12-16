package day16;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class TicketEvaluatorTest {

	@Test
	public void countInvalidExample() {
		TicketEvaluator evaluator = new TicketEvaluator(new Input("/day16/example1.txt"));
		assertEquals(71, evaluator.invalidTicketErrorRate());
	}
	
	@Test
	public void ruleIndexExample() {
		TicketEvaluator evaluator = new TicketEvaluator(new Input("/day16/example2.txt"));
		
		assertEquals(Arrays.asList(1, 0, 2), evaluator.determineRuleIndices());
	}
	
}
