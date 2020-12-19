package day19;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class RuleTest {

	@Test
	public void example1() {
		LiteralRule a = new LiteralRule('a');
		LiteralRule b = new LiteralRule('b');
		
		OrRule or = new OrRule(new SequenceRule(a, b), new SequenceRule(b, a));
		
		SequenceRule zero = new SequenceRule(a, or);
		
		assertTrue(zero.canMatch("aab"));
		assertTrue(zero.canMatch("aba"));
		
		assertFalse(zero.canMatch("ac"));
	}
	
}
