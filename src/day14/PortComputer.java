package day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.Util;
import virtual_machine.Instruction;
import virtual_machine.VirtualMachine;

public class PortComputer extends VirtualMachine {

	private class Mask implements Instruction {

		private String arg;
		
		public Mask(String line) {
			this.arg = line.substring("mask = ".length());
		}
		
		@Override
		public void execute() {
			currentMask = new Integer[currentMask.length];
			
			for (int i = 0; i < currentMask.length; i++) {
				switch (arg.charAt(i)) {
				case '1':
					currentMask[i] = 1;
					break;
				case '0':
					currentMask[i] = 0;
					break;
				case 'X':
					break;
				}
			}
		}
		
	}
	
	private static final Pattern MEM_REGEX = Pattern.compile("mem\\[(?<address>[0-9]+)\\] = (?<value>[0-9]+)");
	
	private class Mem implements Instruction {

		private long address;
		
		private long value;
		
		public Mem(String line) {
			Matcher m = MEM_REGEX.matcher(line);
			if (!m.matches()) {
				throw new IllegalArgumentException();
			}
			this.address = Long.parseLong(m.group("address"));
			this.value = Long.parseLong(m.group("value"));
		}

		@Override
		public void execute() {
			setMemory(address, value);
		}
		
	}
	
	private Integer[] currentMask;
	
	private Map<Long, Long> memory;
	
	
	public PortComputer(List<String> program) {
		currentMask = new Integer[36];
		memory = new HashMap<>(1024);
		
		for (String line : program) {
			if (line.startsWith("mask = ")) {
				addInstruction(new Mask(line));
			} else {
				addInstruction(new Mem(line));
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
		Integer[] decodedAddress = new Integer[currentMask.length];
		
		for (int i = 0; i < currentMask.length; i++) {
			if (currentMask[i] == null) {
				decodedAddress[i] = null;
			} else if (currentMask[i] == 1) {
				decodedAddress[i] = 1;
			} else {
				decodedAddress[i] = (address & (1L << (currentMask.length - i - 1))) == 0L ? 0 : 1;
			}
		}
		
		setMemory(decodedAddress, 0, value);
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
