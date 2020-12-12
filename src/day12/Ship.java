package day12;

import java.util.ArrayList;
import java.util.List;

import util.Util;

public class Ship {
	
	private Vec pos = new Vec(0, 0);
	
	private Vec waypoint = new Vec(10, 1);
	
	private List<Instruction> instructions;
	
	private enum Operation {
		N,
		S,
		E,
		W,
		L,
		R,
		F,
	}
	
	private class Instruction {
		
		private Operation operation;
		
		private int argument;

		public Instruction(char operation, int argument) {
			this.operation = Operation.valueOf(Character.toString(operation));
			this.argument = argument;
		}
		
		public void execute() {
			int argument = this.argument;
			switch (operation) {
			case N:
				waypoint = waypoint.add(new Vec(0, argument));
				break;
			case S:
				waypoint = waypoint.add(new Vec(0, -argument));
				break;
			case E:
				waypoint = waypoint.add(new Vec(argument, 0));
				break;
			case W:
				waypoint = waypoint.add(new Vec(-argument, 0));
				break;
			
			case L:
				while (argument > 0) {
					waypoint = waypoint.rotateLeft();
					argument -= 90;
				}
				break;
				
			case R:
				while (argument > 0) {
					waypoint = waypoint.rotateRight();
					argument -= 90;
				}
				break;
				
			case F:
				pos = pos.add(waypoint.scale(argument));
				break;
			}
		}
		
		@Override
		public String toString() {
			return operation.name() + argument;
		}
		
	}
	
	public Ship(List<String> program) {
		this.instructions = new ArrayList<>(program.size());
		for (String line : program) {
			this.instructions.add(new Instruction(Character.toUpperCase(line.charAt(0)), Integer.parseInt(line.substring(1))));
		}
	}
	
	
	public void run() {
		for (Instruction inst : instructions) {
			inst.execute();
		}
	}
	
	public int getDistanceManhattan() {
		return pos.manhattan();
	}
	
	public static void main(String[] args) {
		Ship ship = new Ship(Util.readInputFile("/day12/input.txt"));
		ship.run();
		System.out.println(ship.getDistanceManhattan());
	}
	
}
