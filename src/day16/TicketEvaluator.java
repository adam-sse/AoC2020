package day16;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicketEvaluator {

	private static final Pattern RULE_PATTERN = Pattern.compile(
			"(?<name>[a-z ]+): (?<n1>[0-9]+)-(?<n2>[0-9]+) or (?<n3>[0-9]+)-(?<n4>[0-9]+)");
	
	private final int ticketLength;
	
	private List<Rule> rules;
	
	private Ticket myTicket;
	
	private List<Ticket> nearbyTickets;
	
	public TicketEvaluator(Input input) {
		rules = new ArrayList<>(input.getRules().size());
		
		for (String line : input.getRules()) {
			Matcher m = RULE_PATTERN.matcher(line);
			m.matches();
			rules.add(new Rule(m.group("name"),
					new Range(Integer.parseInt(m.group("n1")), Integer.parseInt(m.group("n2"))),
					new Range(Integer.parseInt(m.group("n3")), Integer.parseInt(m.group("n4")))));
		}
		
		myTicket = new Ticket(input.getMyTicket());
		ticketLength = myTicket.getNumValues();
		
		nearbyTickets = new ArrayList<>(input.getNearbyTickets().size());
		for (String line : input.getNearbyTickets()) {
			nearbyTickets.add(new Ticket(line));
		}
	}
	
	private boolean oneRuleMatches(int value) {
		boolean oneRuleMatches = false;
		for (Rule rule : rules) {
			if (rule.valueValid(value)) {
				oneRuleMatches = true;
				break;
			}
		}
		return oneRuleMatches;
	}
	
	public int invalidTicketErrorRate() {
		int invalidSum = 0;
		
		for (Ticket nearbyTicket : nearbyTickets) {
			for (Integer value : nearbyTicket) {
				if (!oneRuleMatches(value)) {
					invalidSum += value;
				}
			}
		}
		
		return invalidSum;
	}
	
	public void filterInvalidTickets() {
		List<Ticket> validTickets = new ArrayList<>(nearbyTickets.size());
		
		for (Ticket nearbyTicket : nearbyTickets) {
			boolean ticketValid = true;
			
			for (Integer value : nearbyTicket) {
				if (!oneRuleMatches(value)) {
					ticketValid = false;
					break;
				}
			}
			
			if (ticketValid) {
				validTickets.add(nearbyTicket);
			}
		}
		
		this.nearbyTickets = validTickets;
	}
	
	private boolean matchesAll(Rule rule, int index) {
		boolean matches = true;
		
		for (Ticket nearbyTicket : nearbyTickets) {
			if (!rule.valueValid(nearbyTicket.getValue(index))) {
				matches = false;
				break;
			}
		}
		
		return matches;
	}
	
	public List<Integer> determineRuleIndices() {
		List<Integer> ruleIndices = new ArrayList<>(rules.size());
		for (int i = 0; i < rules.size(); i++) {
			ruleIndices.add(null);
		}

		while (ruleIndices.contains(null)) {
			for (int ruleIndex = 0; ruleIndex < rules.size(); ruleIndex++) {
				Rule rule = rules.get(ruleIndex);
				
				Set<Integer> possibleIndices = new HashSet<>(ticketLength);
				for (int index = 0; index < ticketLength; index++) {
					if (!ruleIndices.contains(index) && matchesAll(rule, index)) {
						possibleIndices.add(index);
					}
				}
				
				if (possibleIndices.size() == 1) {
					ruleIndices.set(ruleIndex, possibleIndices.iterator().next());
				}
			}
		}
		
		return ruleIndices;
	}
	
	public long multiplyRulesStartingWith(String prefix, List<Integer> indices) {
		long result = 1;
		
		for (int i = 0; i < rules.size(); i++) {
			Rule rule = rules.get(i);
			if (rule.getName().startsWith(prefix)) {
				result *= myTicket.getValue(indices.get(i));
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		TicketEvaluator evaluator = new TicketEvaluator(new Input("/day16/input.txt"));
		System.out.println(evaluator.invalidTicketErrorRate());
		
		evaluator.filterInvalidTickets();
		List<Integer> indices = evaluator.determineRuleIndices();
		System.out.println(indices);
		System.out.println(evaluator.multiplyRulesStartingWith("departure", indices));
	}
	
}
