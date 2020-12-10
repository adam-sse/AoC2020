package day10;

import java.util.Arrays;
import java.util.List;

import util.Util;

public class JoltCounter {

	private int[] adapters;
	
	private int[] differences;
	
	public JoltCounter(int[] adapters) {
		Arrays.sort(adapters);
		this.adapters = adapters;
		
		differences = new int[adapters.length + 1];
		for (int i = 0; i < differences.length; i++) {
			differences[i] = getJolt(i) - getJolt(i - 1);
		}
	}
	
	private int getJolt(int index) {
		if (index == -1) {
			return 0;
		} else if (index == adapters.length) {
			return adapters[adapters.length - 1] + 3;
		} else {
			return adapters[index];
		}
	}
	
	public int countDifferences(int diff) {
		return (int) Arrays.stream(differences)
				.filter((n) -> n == diff)
				.count();
	}

	private static int factorial(int n) {
		int fac = 1;
		while (n > 0) {
			fac *= n;
			n--;
		}
		return fac;
	}
	
	@SuppressWarnings("unused")
	private static int binomial(int n, int k) {
		return factorial(n) / (factorial(k) * factorial(n - k));
	}
	
	public long countPossibleArrangements() {
		/*
		 * Two observations about the input data:
		 *   1) there are only differences of 1 and 3, never 2
		 *   2) there are at most 4 differences of 1 in a row
		 */
		
		long arrangements = 1;
		int numOnesInRow = 0;
		for (int i = 0; i < differences.length; i++) {
			if (differences[i] == 1) {
				numOnesInRow++;
				
			} else if (numOnesInRow > 0) {
		 		if (numOnesInRow == 2) {
		 			arrangements *= 2; // 2^1  -> remove none or one adapter
				} else if (numOnesInRow == 3) {
					arrangements *= 4; // 2^2  -> remove none, one, or two adapters
				} else if (numOnesInRow == 4) {
					arrangements *= 7; // 2^3 - 1  -> remove none, one, or two adapters; but not three
				}
		 		
		 		// factor can also be produced by using the binomial function:
//				int factor = 0;
//				for (int k = 0; k <= Math.min(numOnesInRow - 1, 2); k++) {
//					factor += binomial(numOnesInRow - 1, k);
//				}
//				arrangements *= factor;
				
				numOnesInRow = 0;
			}
		}
		
		return arrangements;
	}
	
	public static void main(String[] args) {
		List<Integer> input = Util.readIntegerInputFile("/day10/input.txt");
		int[] arr = new int[input.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = input.get(i);
		}
		
		JoltCounter jc = new JoltCounter(arr);
		System.out.println(jc.countDifferences(1) * jc.countDifferences(3));
		
		System.out.println(jc.countPossibleArrangements());
	}
	
}
