package day5;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.Util;

public class BoardingPass {

	private static final Pattern LINE_REGEX = Pattern.compile("(?<rows>[FB]{7})(?<columns>[LR]{3})");
	
	private char[] rows;
	
	private char[] columns;
	
	public BoardingPass(String line) {
		Matcher m = LINE_REGEX.matcher(line);
		if (m.matches()) {
			this.rows = m.group("rows").toCharArray();
			this.columns = m.group("columns").toCharArray();
			
		} else {
			throw new IllegalArgumentException("Invalid BoardingPass line: " + line);
		}
	}
	
	private static int binaryPartition(char[] steps, char lower, char higher) {
		int value = 0;
		
		for (int i = 0; i < steps.length; i++) {
			value <<= 1;
			if (steps[i] == higher) {
				value += 1;
			}
		}
		
		return value;
	}
	
	int getRow() {
		return binaryPartition(rows, 'F', 'B');
	}
	
	int getColumn() {
		return binaryPartition(columns, 'L', 'R');
	}
	
	public int getSeatId() {
		return getRow() * 8 + getColumn();
	}
	
	public static void main(String[] args) {
		List<BoardingPass> passes = new ArrayList<>(800);
		
		for (String line : Util.readInputFile("/day5/input.txt")) {
			passes.add(new BoardingPass(line));
		}
		
		System.out.println("max ID: " + passes.stream()
				.map(BoardingPass::getSeatId)
				.mapToInt(Integer::intValue)
				.max()
				.getAsInt());
		
		boolean[] takenIds = new boolean[128 * 8];
		for (BoardingPass bp : passes) {
			takenIds[bp.getSeatId()] = true;
		}
		
		for (int i = 1; i < takenIds.length - 1; i++) {
			if (!takenIds[i] && takenIds[i - 1] && takenIds[i + 1]) {
				System.out.println(i);
			}
		}
	}
	
}
