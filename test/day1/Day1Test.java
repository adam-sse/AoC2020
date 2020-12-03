package day1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Day1Test {

	@Test
	public void example() {
		List<Integer> input = Arrays.asList(1721, 979, 366, 299, 675, 1456);
		
		assertEquals(241861950, Day1.findSumAndMultiply(input));
	}
	
}
