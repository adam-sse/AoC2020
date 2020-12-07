package day7;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BagRule {

	private static final Pattern RULE_REGEX = Pattern.compile("^(?<outerColor>[a-z]+ [a-z]+) bags contain (?<innerBags>.+)\\.$");
	
	private static final Pattern INNER_REGEX = Pattern.compile("(?<amount>[0-9]+) (?<color>[a-z]+ [a-z]+) bag(s)?(, )?");
	
	private String outerColor;
	
	private Map<String, Integer> innerColors = new HashMap<>();
	
	public BagRule(String line) {
		Matcher m = RULE_REGEX.matcher(line);
		if (m.matches()) {
			this.outerColor = m.group("outerColor");
			String innerBags = m.group("innerBags");
			Matcher innerM = INNER_REGEX.matcher(innerBags);
			while (innerM.find()) {
				this.innerColors.put(innerM.group("color"), Integer.parseInt(innerM.group("amount")));
			}
			
		} else {
			throw new IllegalArgumentException("No match: " + line);
		}
	}
	
	public boolean canContain(String color) {
		return innerColors.containsKey(color);
	}
	
	public int getAmount(String color) {
		return innerColors.get(color);
	}
	
	public Set<String> getInnerColors() {
		return new HashSet<>(innerColors.keySet());
	}
	
	public String getOuterColor() {
		return outerColor;
	}
	
}
