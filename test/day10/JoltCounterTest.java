package day10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class JoltCounterTest {

	@Test
	public void countExample1() {
		int[] adapters = {16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4};
		
		JoltCounter jc = new JoltCounter(adapters);
		assertEquals(7, jc.countDifferences(1));
		assertEquals(5, jc.countDifferences(3));
	}
	
	@Test
	public void countExample2() {
		int[] adapters = {28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3};
		
		JoltCounter jc = new JoltCounter(adapters);
		assertEquals(22, jc.countDifferences(1));
		assertEquals(10, jc.countDifferences(3));
	}
	
	@Test
	public void arrangementExample1() {
		int[] adapters = {16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4};
		
		JoltCounter jc = new JoltCounter(adapters);
		assertEquals(8, jc.countPossibleArrangements());
	}
	
	@Test
	public void arrangementExample2() {
		int[] adapters = {28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3};
		
		JoltCounter jc = new JoltCounter(adapters);
		assertEquals(19208, jc.countPossibleArrangements());
	}
	
}
