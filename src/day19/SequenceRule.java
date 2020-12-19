package day19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SequenceRule extends Rule {

	private List<Rule> rules;
	
	public SequenceRule(Rule... rules) {
		this.rules = new ArrayList<>(Arrays.asList(rules));
	}
	
	public SequenceRule(List<Rule> rules) {
		this.rules = new ArrayList<>(rules);
	}

	@Override
	public List<String> match(String message) {
		// we start with the original message as the only possible "match"
		List<String> possibleMessages = new LinkedList<>();
		possibleMessages.add(message);
		
		for (int i = 0; !possibleMessages.isEmpty() && i < rules.size(); i++) {
			List<String> nextPossibleMessages = new LinkedList<>();
			
			// collect all possible matches for the current rule in the sequence
			for (String possibleMessage : possibleMessages) {
				nextPossibleMessages.addAll(rules.get(i).match(possibleMessage));
			}
			
			// after we are done with the current rule in the sequence, we use it's
			// possible "leftovers" for the next rule(s)
			possibleMessages = nextPossibleMessages;
		}
		
		// here, possibleMessage is empty (i.e. this sequence rule does not match) or contains
		// all possible "leftovers" after all rules in this sequence have matched
		return possibleMessages;
	}

}
