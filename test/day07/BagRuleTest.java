package day07;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class BagRuleTest {

	@Test
	public void canContain1() {
		assertTrue(new BagRule("bright white bags contain 1 shiny gold bag.").canContain("shiny gold"));
	}
	
	@Test
	public void canContain2() {
		assertFalse(new BagRule("light red bags contain 1 bright white bag, 2 muted yellow bags.").canContain("shiny gold"));
	}
	
	
	@Test
	public void getAmount1() {
		assertEquals(1, new BagRule("bright white bags contain 1 shiny gold bag.").getAmount("shiny gold"));
	}
	
	@Test
	public void getAmount2() {
		assertEquals(2, new BagRule("light red bags contain 1 bright white bag, 2 muted yellow bags.").getAmount("muted yellow"));
	}
	
	@Test
	public void getInner1() {
		assertEquals(new HashSet<>(Arrays.asList("shiny gold")),
				new BagRule("bright white bags contain 1 shiny gold bag.").getInnerColors());
	}
	
	@Test
	public void getInner2() {
		assertEquals(new HashSet<>(Arrays.asList("bright white", "muted yellow")),
				new BagRule("light red bags contain 1 bright white bag, 2 muted yellow bags.").getInnerColors());
	}
	
	@Test
	public void getOuter1() {
		assertEquals("bright white",
				new BagRule("bright white bags contain 1 shiny gold bag.").getOuterColor());
	}
	
	@Test
	public void getOuter2() {
		assertEquals("light red",
				new BagRule("light red bags contain 1 bright white bag, 2 muted yellow bags.").getOuterColor());
	}
	
}
