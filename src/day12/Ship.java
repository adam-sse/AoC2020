package day12;

import java.util.List;

import util.Util;
import virtual_machine.InstructionFactory;
import virtual_machine.VirtualMachine;

public class Ship extends VirtualMachine {

	private Vec pos = new Vec(0, 0);
	
	private Vec waypoint = new Vec(10, 1);
	
	public Ship(List<String> program) {
		InstructionFactory factory = new InstructionFactory();
		
		factory.registerInstruction("N(?<amount>[0-9]+)", m -> {
			int amount = Integer.parseInt(m.group("amount"));
			return () -> {
				waypoint = waypoint.add(new Vec(0, amount));
			};
		});
		factory.registerInstruction("S(?<amount>[0-9]+)", m -> {
			int amount = Integer.parseInt(m.group("amount"));
			return () -> {
				waypoint = waypoint.add(new Vec(0, -amount));
			};
		});
		factory.registerInstruction("E(?<amount>[0-9]+)", m -> {
			int amount = Integer.parseInt(m.group("amount"));
			return () -> {
				waypoint = waypoint.add(new Vec(amount, 0));
			};
		});
		factory.registerInstruction("W(?<amount>[0-9]+)", m -> {
			int amount = Integer.parseInt(m.group("amount"));
			return () -> {
				waypoint = waypoint.add(new Vec(-amount, 0));
			};
		});
		factory.registerInstruction("L(?<amount>[0-9]+)", m -> {
			int amount = Integer.parseInt(m.group("amount"));
			return () -> {
				int a = amount;
				while (a > 0) {
					waypoint = waypoint.rotateLeft();
					a -= 90;
				}
			};
		});
		factory.registerInstruction("R(?<amount>[0-9]+)", m -> {
			int amount = Integer.parseInt(m.group("amount"));
			return () -> {
				int a = amount;
				while (a > 0) {
					waypoint = waypoint.rotateRight();
					a -= 90;
				}
			};
		});
		factory.registerInstruction("F(?<amount>[0-9]+)", m -> {
			int amount = Integer.parseInt(m.group("amount"));
			return () -> {
				pos = pos.add(waypoint.scale(amount));
			};
		});
		
		factory.parseInstructions(program, this);
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
