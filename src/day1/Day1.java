package day1;

import java.util.List;
import java.util.NoSuchElementException;

import util.Util;

public class Day1 {

	static int findSumAndMultiply(List<Integer> input) {
		for (int i = 0; i < input.size(); i++) {
			for (int j = i + 1; j < input.size(); j++) {
				for (int k = j + 1; k < input.size(); k++) {
					if (input.get(i) + input.get(j) + input.get(k) == 2020) {
						return input.get(i) * input.get(j) * input.get(k);
					}
				}
			}
		}
		throw new NoSuchElementException();
	}
	
	public static void main(String[] args) {
		List<Integer> input = Util.readIntegerInputFile("/day1/input.txt");
		
		System.out.println(findSumAndMultiply(input));
	}
	
}
