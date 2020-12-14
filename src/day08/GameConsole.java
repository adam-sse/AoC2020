package day08;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Util;
import virtual_machine.Instruction;
import virtual_machine.VirtualMachine;

public class GameConsole extends VirtualMachine {

	private int accumulator;
	
	private class Nop implements Instruction {

		private int value;
		
		public Nop(int value) {
			this.value = value;
		}
		
		@Override
		public void execute() {
		}
		
	}
	
	private class Acc implements Instruction {

		private int value;
		
		public Acc(int value) {
			this.value = value;
		}
		
		@Override
		public void execute() {
			accumulator += value;
		}
		
	}
	
	private class Jmp implements Instruction {
		
		private int value;
		
		public Jmp(int value) {
			this.value = value;
		}
		
		@Override
		public void execute() {
			jumpRel(value);
		}
		
	}
	
	
	public GameConsole(List<String> program) {
		for (String line : program) {
			int value = Integer.parseInt(line.substring(4));
			
			switch (line.substring(0, 3)) {
			case "nop":
				addInstruction(new Nop(value));
				break;
			case "acc":
				addInstruction(new Acc(value));
				break;
			case "jmp":
				addInstruction(new Jmp(value));
				break;
			}
		}
	}
	
	@Override
	public void reset() {
		super.reset();
		accumulator = 0;
	}
	
	boolean isLoop() {
		Set<Integer> visited = new HashSet<>();
		
		while (!isHalt()) {
			if (visited.contains(getIp())) {
				break;
			}
			visited.add(getIp());
			
			executeSingle();
		}
		
		return !isHalt();
	}
	
	private boolean switchInstruction(int index) {
		boolean switched = false;
		Instruction inst = getInstruction(index);
		
		if (inst instanceof Nop) {
			setInstruction(index, new Jmp(((Nop) inst).value));
			switched = true;
			
		} else if (inst instanceof Jmp) {
			setInstruction(index, new Nop(((Jmp) inst).value));
			switched = true;
		}
		
		return switched;
	}
	
	public int findNonLoopMutation() {
		for (int i = 0; i < getNumInstructions(); i++) {
			if (switchInstruction(i)) {
				if (!isLoop()) {
					break;
				}
				
				reset();
				
				switchInstruction(i);
			}
		}
		
		return this.accumulator;
	}
	
	public static void main(String[] args) {
		GameConsole gc = new GameConsole(Util.readInputFile("/day08/input.txt"));
		
		System.out.println(gc.findNonLoopMutation());
	}
	
}
