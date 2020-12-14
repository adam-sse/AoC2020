package virtual_machine;

import java.util.ArrayList;
import java.util.List;

public class VirtualMachine {

	private List<Instruction> instructions;
	
	private int ip;
	
	private boolean halt;
	
	private boolean jumped;
	
	public VirtualMachine() {
		this.instructions = new ArrayList<>();
	}
	
	public final void addInstruction(Instruction instruction) {
		this.instructions.add(instruction);
	}
	
	public final void setInstruction(int index, Instruction instruction) {
		this.instructions.set(index, instruction);
	}
	
	public final Instruction getInstruction(int index) {
		return this.instructions.get(index);
	}
	
	public final int getNumInstructions() {
		return instructions.size();
	}
	
	
	public final boolean isHalt() {
		return halt;
	}
	
	public final int getIp() {
		return ip;
	}
	
	public void reset() {
		ip = 0;
		halt = false;
	}
	
	public final void jumpAbs(int instruction) {
		ip = instruction;
		jumped = true;
	}
	
	public final void jumpRel(int relative) {
		ip += relative;
		jumped = true;
	}
	
	public final void executeSingle() {
		if (!isHalt()) {
			Instruction inst = this.instructions.get(ip);
			inst.execute();
			
			if (!jumped) {
				ip++;
			}
			jumped = false;
			
			if (ip >= instructions.size()) {
				halt = true;
			}
		}
	}
	
	public final void run() {
		while (!isHalt()) {
			executeSingle();
		}
	}
	
}
