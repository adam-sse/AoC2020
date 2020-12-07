package day7;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BagRuleCollectionTest {

	@Test
	public void canEventuallyContainExample() {
		BagRule[] rules = {
				new BagRule("light red bags contain 1 bright white bag, 2 muted yellow bags."),
				new BagRule("dark orange bags contain 3 bright white bags, 4 muted yellow bags."),
				new BagRule("bright white bags contain 1 shiny gold bag."),
				new BagRule("muted yellow bags contain 2 shiny gold bags, 9 faded blue bags."),
				new BagRule("shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags."),
				new BagRule("dark olive bags contain 3 faded blue bags, 4 dotted black bags."),
				new BagRule("vibrant plum bags contain 5 faded blue bags, 6 dotted black bags."),
				new BagRule("faded blue bags contain no other bags."),
				new BagRule("dotted black bags contain no other bags."),
		};
		
		BagRuleCollection collection = new BagRuleCollection(rules);
		assertEquals(4, collection.canEventuallyContain("shiny gold"));
	}
	
	@Test
	public void mustContainExample() {
		BagRule[] rules = {
				new BagRule("shiny gold bags contain 2 dark red bags."),
				new BagRule("dark red bags contain 2 dark orange bags."),
				new BagRule("dark orange bags contain 2 dark yellow bags."),
				new BagRule("dark yellow bags contain 2 dark green bags."),
				new BagRule("dark green bags contain 2 dark blue bags."),
				new BagRule("dark blue bags contain 2 dark violet bags."),
				new BagRule("dark violet bags contain no other bags."),
		};
		
		BagRuleCollection collection = new BagRuleCollection(rules);
		assertEquals(126, collection.mustContain("shiny gold"));
	}
	
}
