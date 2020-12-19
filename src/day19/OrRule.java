package day19;

import java.util.LinkedList;
import java.util.List;

public class OrRule extends Rule {

	private Rule left;
	
	private Rule right;
	
	public OrRule(Rule left, Rule right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public List<String> match(String message) {
		List<String> result = new LinkedList<>();
		
		// the result of this match is all possible matches of the left side
		// combined with all possible matches of the right side
		result.addAll(left.match(message));
		result.addAll(right.match(message));
		
		return result;
	}

}
