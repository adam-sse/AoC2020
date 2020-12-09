package day9;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;

import util.Util;

public class CipherBreaker {

	private int range;
	
	public CipherBreaker(int range) {
		this.range = range;
	}
	
	public boolean isValid(long[] numbers, int index) {
		long targetSum = numbers[index];
		
		int rangeFrom = index - range;
		int rangeTo = index - 1;
		for (int i = rangeFrom; i <= rangeTo ; i++) {
			for (int j = i + 1; j <= rangeTo ; j++) {
				if (numbers[i] + numbers[j] == targetSum) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public int firstNonValidIndex(long[] numbers) {
		for (int i = range; i < numbers.length; i++) {
			if (!isValid(numbers, i)) {
				return i;
			}
		}
		throw new NoSuchElementException();
	}
	
	private static long sum(long[] numbers, int lower, int upper) {
		long sum = 0;
		for (int i = lower; i <= upper; i++) {
			sum += numbers[i];
		}
		return sum;
	}
	
	private static long extremum(long[] numbers, int lower, int upper, BiFunction<Long, Long, Boolean> comparator) {
		long extremum = numbers[lower];
		for (int i = lower + 1; i <= upper; i++) {
			if (comparator.apply(numbers[i], extremum)) {
				extremum = numbers[i];
			}
		}
		return extremum;
	}
	
	public long findContiguousSet(long[] numbers, int targetIndex) {
		for (int lower = 0; lower < targetIndex; lower++) {
			int upper = lower + 1;
			while (sum(numbers, lower, upper) < numbers[targetIndex]) {
				upper++;
			}
			if (sum(numbers, lower, upper) == numbers[targetIndex]) {
				return extremum(numbers, lower, upper, (l1, l2) -> l1 < l2)
						+ extremum(numbers, lower, upper, (l1, l2) -> l1 > l2);
			}
		}
		throw new NoSuchElementException();
	}
	
	public static void main(String[] args) {
		List<String> input = Util.readInputFile("/day9/input.txt");
		long[] arr = new long[input.size()];
		for (int i = 0; i < input.size(); i++) {
			arr[i] = Long.parseLong(input.get(i));
		}
		
		CipherBreaker cb = new CipherBreaker(25);
		int firstNonSum = cb.firstNonValidIndex(arr);
		System.out.println(arr[firstNonSum]);
		
		System.out.println(cb.findContiguousSet(arr, firstNonSum));
	}
	
}
