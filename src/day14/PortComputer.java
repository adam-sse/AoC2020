package day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.Util;

public class PortComputer {

	private Integer[] mask;
	
	private Map<Long, Long> memory;
	
	private List<Instruction> instructions;
	
	private enum Operation {
		MASK, MEM,
	}
	
	private class Instruction {
		
		private Operation operation;
		
		private long arg1;
		
		private long arg2;
		
		private String argS;

		public Instruction(Operation operation, long arg1, long arg2) {
			this.operation = operation;
			this.arg1 = arg1;
			this.arg2 = arg2;
		}
		
		public Instruction(Operation operation, String argS) {
			this.operation = operation;
			this.argS = argS;
		}
		
		public void execute() {
			switch (operation) {
			case MEM:
				setMemory(arg1, arg2);
				break;
			case MASK:
				setMask(argS);
				break;
			}
		}
		
		@Override
		public String toString() {
			return operation.name();
		}
		
	}
	
	private static final Pattern MEM_REGEX = Pattern.compile("mem\\[(?<address>[0-9]+)\\] = (?<value>[0-9]+)");
	
	public PortComputer(List<String> program) {
		mask = new Integer[36];
		memory = new HashMap<>(1024);
		instructions = new ArrayList<>(program.size());
		
		for (String line : program) {
			if (line.startsWith("mask = ")) {
				instructions.add(new Instruction(Operation.MASK, line.substring("mask = ".length())));
			} else {
				Matcher m = MEM_REGEX.matcher(line);
				if (m.matches()) {
					instructions.add(new Instruction(Operation.MEM,
							Long.parseLong(m.group("address")), Long.parseLong(m.group("value"))));
				} else {
					System.err.println(line);
				}
			}
		}
	}
	
	private void setMask(String maskStr) {
		mask = new Integer[mask.length];
		
		for (int i = 0; i < mask.length; i++) {
			switch (maskStr.charAt(i)) {
			case '1':
				mask[i] = 1;
				break;
			case '0':
				mask[i] = 0;
				break;
			case 'X':
				break;
			}
		}
	}
	
	private void setMemory(Integer[] address, int i, long value) {
		if (i == address.length) {
			
			long a = 0;
			for (int j = 0; j < address.length; j++) {
				a <<= 1;
				if (address[j] == 1) {
					a |= 1;
				}
			}
			this.memory.put(a, value);
			
		} else {
			if (address[i] == null) {
				address[i] = 1;
				setMemory(address, i + 1, value);
				address[i] = 0;
				setMemory(address, i + 1, value);
				address[i] = null;
			} else {
				setMemory(address, i + 1, value);
			}
		}
	}
	
	private void setMemory(long address, long value) {
		Integer[] decodedAddress = new Integer[mask.length];
		
		for (int i = 0; i < mask.length; i++) {
			if (mask[i] == null) {
				decodedAddress[i] = null;
			} else if (mask[i] == 1) {
				decodedAddress[i] = 1;
			} else {
				decodedAddress[i] = (address & (1L << (mask.length - i - 1))) == 0L ? 0 : 1;
			}
		}
		
		setMemory(decodedAddress, 0, value);
	}
	
	public void run() {
		for (Instruction inst : instructions) {
			inst.execute();
		}
	}
	
	public long sumMemory() {
		long sum = 0;
		
		for (Map.Entry<Long, Long> entry : memory.entrySet()) {
			sum += entry.getValue();
		}
		
		return sum;
	}
	
	public static void main(String[] args) {
		PortComputer pc = new PortComputer(Util.readInputFile("/day14/input.txt"));
		pc.run();
		System.out.println(pc.sumMemory());
	}
	
}
