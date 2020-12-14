package virtual_machine;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionFactory {

	private static class Rule {
		
		private Pattern regex;
		
		Function<Matcher, Instruction> factory;

		public Rule(String regex, Function<Matcher, Instruction> factory) {
			this.regex = Pattern.compile(regex);
			this.factory = factory;
		}
		
	}
	
	private List<Rule> rules = new LinkedList<>();
	
	public void registerInstruction(String regex, Function<Matcher, Instruction> factory) {
		rules.add(new Rule(regex, factory));
	}
	
	public void parseInstructions(List<String> instructions, VirtualMachine vm) {
		for (String instruction : instructions) {
			vm.addInstruction(parseInstruction(instruction));
		}
	}
	
	public Instruction parseInstruction(String instruction) throws IllegalArgumentException {
		for (Rule rule : rules) {
			Matcher m = rule.regex.matcher(instruction);
			if (m.matches()) {
				return rule.factory.apply(m);
			}
		}
		throw new IllegalArgumentException("No regex matches " + instruction);
	}
	
}
