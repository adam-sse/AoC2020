package day19;

import java.util.List;

public abstract class Rule {

	/**
	 * Tries to match the given message.
	 * 
	 * @param message The message to match.
	 * 
	 * @return A list of possible "leftovers" after matching the message. If this is empty,
	 * 		   the message does not match at all. If this contains one String, it is the
	 * 		   substring of message after the part that was matched (and thus "consumed")
	 * 		   by this rule.
	 *         If this contains multiple Strings, then there are multiple possible matches
	 *         by this rule. Each "leftover" is one element in the array.
	 */
	protected abstract List<String> match(String message);
	
	public boolean canMatch(String message) {
		return match(message).contains("");
	}
	
}
