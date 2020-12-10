package day07;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import util.Util;

public class BagRuleCollection {

	private Map<String, BagRule> rules = new HashMap<>();
	
	public BagRuleCollection(Stream<BagRule> rules) {
		rules.forEach((rule) -> this.rules.put(rule.getOuterColor(), rule));
	}
	
	public BagRuleCollection(BagRule... rules) {
		this(Arrays.stream(rules));
	}
	
	public int canEventuallyContain(String wantedColor) {
		Set<BagRule> rulesThatCanContain = new HashSet<>();
		Set<String> colors = new HashSet<>();
		colors.add(wantedColor);

		int oldSize;
		do {
			oldSize = colors.size();
			
			Set<String> newColors = new HashSet<>(colors);
			for (String color : colors) {
				for (Map.Entry<String, BagRule> entry : this.rules.entrySet()) {
					if (entry.getValue().canContain(color)) {
						newColors.add(entry.getKey());
						rulesThatCanContain.add(entry.getValue());
					}
				}
			}
			colors = newColors;
			
		} while (oldSize != colors.size());
		
		return rulesThatCanContain.size();
	}
	
	public int mustContain(String color) {
		BagRule colorRule = rules.get(color);
		int mustContain = 0;
		
		for (String innerColor : colorRule.getInnerColors()) {
			mustContain += colorRule.getAmount(innerColor) * (1 + mustContain(innerColor));
		}
		
		return mustContain;
	}
	
	public static void main(String[] args) {
		BagRuleCollection collection = new BagRuleCollection(
				Util.readInputFile("/day07/input.txt").stream()
						.map((line) -> new BagRule(line)));
		System.out.println(collection.canEventuallyContain("shiny gold"));
		
		System.out.println(collection.mustContain("shiny gold"));
	}
	
}
