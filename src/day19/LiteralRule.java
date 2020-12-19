package day19;

import java.util.ArrayList;
import java.util.List;

public class LiteralRule extends Rule {

	private char literal;
	
	public LiteralRule(char literal) {
		this.literal = literal;
	}

	@Override
	public List<String> match(String message) {
		// either we match the first char, or we don't match
		// matching -> return the message shortened by one character
		// not matching -> return empty array
		List<String> result = new ArrayList<>(1);
		if (message.length() > 0 && message.charAt(0) == literal) {
			result.add(message.substring(1));
		}
		return result;
	}

}
