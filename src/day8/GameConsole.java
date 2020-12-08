package day8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Util;

public class GameConsole {

	private int accumulator;
	
	private int ip;
	
	private boolean halt;
	
	private List<Instruction> instructions;
	
	private enum Operation {
		NOP,
		ACC,
		JMP,
	}
	
	private class Instruction {
		
		private Operation operation;
		
		private int argument;

		public Instruction(String operation, int argument) {
			this.operation = Operation.valueOf(operation.toUpperCase());
			this.argument = argument;
		}
		
		public void execute() {
			switch (operation) {
			case NOP:
				ip++;
				break;
				
			case ACC:
				accumulator += argument;
				ip++;
				break;
				
			case JMP:
				ip += argument;
				break;
			}
		}
		
	}
	
	public GameConsole(List<String> program) {
		this.instructions = new ArrayList<>(program.size());
		for (String line : program) {
			instructions.add(new Instruction(line.substring(0, 3), Integer.parseInt(line.substring(4))));
		}
	}
	
	public void reset() {
		ip = 0;
		halt = false;
		accumulator = 0;
	}
	
	private void executeOne() {
		instructions.get(ip).execute();
		if (ip == instructions.size()) {
			halt = true;
		}
	}
	
	boolean isLoop() {
		Set<Integer> visited = new HashSet<>();
		
		while (!halt) {
			if (visited.contains(ip)) {
				break;
			}
			visited.add(ip);
			
			executeOne();
		}
		
		return !halt;
	}
	
	private static boolean switchInstruction(Instruction inst) {
		boolean switched = false;
		
		if (inst.operation == Operation.NOP) {
			inst.operation = Operation.JMP;
			switched = true;
			
		} else if (inst.operation == Operation.JMP) {
			inst.operation = Operation.NOP;
			switched = true;
		}
		
		return switched;
	}
	
	public int findNonLoopMutation() {
		for (int i = 0; i < instructions.size(); i++) {
			Instruction inst = instructions.get(i);
			
			if (switchInstruction(inst)) {
				if (!isLoop()) {
					break;
				}
				
				reset();
				
				switchInstruction(inst);
			}
		}
		
		return this.accumulator;
	}
	
	public static void main(String[] args) {
		GameConsole gc = new GameConsole(Util.readInputFile("/day8/input.txt"));
		
		System.out.println(gc.findNonLoopMutation());
	}
	
}
