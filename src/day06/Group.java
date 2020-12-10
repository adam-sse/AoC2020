package day06;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Util;

public class Group {

	private Set<Character> allYesAnswers = new HashSet<>();
	
	public Group(List<String> lines) {
		allYesAnswers = lineToSet(lines.get(0));

		for (int i = 1; i < lines.size(); i++) {
			allYesAnswers.retainAll(lineToSet(lines.get(i)));
		}
	}
	
	private Set<Character> lineToSet(String line) {
		Set<Character> set = new HashSet<>();
		line.chars().forEach((ch) -> set.add((char) ch));
		return set;
	}
	
	public int getNumAllYesAnserws() {
		return allYesAnswers.size();
	}
	
	public static void main(String[] args) {
		int result = Util.readGroupedLineInputFile("/day06/input.txt").stream()
				.map((lineGroup) -> new Group(lineGroup))
				.map(Group::getNumAllYesAnserws)
				.mapToInt(Integer::intValue)
				.sum();
		
		System.out.println(result);
	}
	
}
