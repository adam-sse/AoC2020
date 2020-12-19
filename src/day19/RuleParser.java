package day19;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.Util;

public class RuleParser {

	private Map<Integer, String> ruleStrings;
	
	private Map<Integer, Rule> rules;
	
	private Set<Integer> currentlyParsing;
	
	public RuleParser(List<String> rules) {
		this.ruleStrings = new HashMap<>(rules.size());
		this.rules = new HashMap<>(rules.size() * 2);
		this.currentlyParsing = new HashSet<>(rules.size());
		
		for (String line : rules) {
			String[] parts = line.split(": ");
			ruleStrings.put(Integer.parseInt(parts[0]), parts[1]);
		}
	}
	
	public Rule getRule(int index) {
		Rule rule = rules.get(index);
		if (rule == null) {
			if (currentlyParsing.contains(index)) {
				rule = new RecursiveRule(index, rules);
				
			} else {
				currentlyParsing.add(index);
				rule = parseRule(ruleStrings.get(index));
				rules.put(index, rule);
				currentlyParsing.remove(index);
			}
		}
		return rule;
	}
	
	private Rule parseRule(String line) {
		Rule result;
		if (line.startsWith("\"")) {
			result = new LiteralRule(line.charAt(1));
			
		} else if (line.indexOf('|') != -1) {
			String[] parts = line.split("\\|");
			result = new OrRule(parseRule(parts[0].trim()), parseRule(parts[1].trim()));
			
		} else {
			List<Rule> references = new LinkedList<>();
			for (String part : line.split(" ")) {
				references.add(getRule(Integer.parseInt(part)));
			}
			result = new SequenceRule(references);
		}
		return result;
	}
	
	public static void main(String[] args) {
		for (int i = 1; i <= 2; i++) {
			List<List<String>> input = Util.readGroupedLineInputFile("/day19/input-part" + i + ".txt");
			
			RuleParser parser = new RuleParser(input.get(0));
			Rule zero = parser.getRule(0);
			
			int matching = 0;
			for (String line : input.get(1)) {
				if (zero.canMatch(line)) {
					matching++;
				}
			}
			System.out.println("Part " + i + ": " + matching);
		}
	}
	
}
