package day9;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CipherBreakerTest {

	@Test
	public void isValidExample1() {
		CipherBreaker cb = new CipherBreaker(25);
		
		long[] numbers = {
				23, 4, 3, 17, 12, 6, 22, 21, 2, 5, 11, 9, 10, 14, 1, 20, 13, 8, 25, 19, 15, 16, 7, 24, 18, // 1-25 random order
				26,
		};
		
		int lastIndex = numbers.length - 1;
		
		assertTrue(cb.isValid(numbers, lastIndex));
		
		numbers[lastIndex] = 49;
		assertTrue(cb.isValid(numbers, lastIndex));
		
		numbers[lastIndex] = 100;
		assertFalse(cb.isValid(numbers, lastIndex));
		
		numbers[lastIndex] = 50;
		assertFalse(cb.isValid(numbers, lastIndex));
	}
	
	@Test
	public void isValidExample2() {
		CipherBreaker cb = new CipherBreaker(25);
		
		long[] numbers = {
				20,
				23, 4, 3, 17, 12, 6, 22, 21, 2, 5, 11, 9, 10, 14, 1, 13, 8, 25, 19, 15, 16, 7, 24, 18, // 1-19 & 21-25 random order
				45,
				26,
		};
		
		int lastIndex = numbers.length - 1;
		
		assertTrue(cb.isValid(numbers, lastIndex));
		
		numbers[lastIndex] = 65;
		assertFalse(cb.isValid(numbers, lastIndex));
		
		numbers[lastIndex] = 64;
		assertTrue(cb.isValid(numbers, lastIndex));
		
		numbers[lastIndex] = 66;
		assertTrue(cb.isValid(numbers, lastIndex));
	}
	
	@Test
	public void isValidExample3() {
		CipherBreaker cb = new CipherBreaker(5);
		
		long[] numbers = {
				35, 20, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576 
		};
		
		for (int i = 5; i < numbers.length; i++) {
			if (i == 14) { // 127
				assertFalse(cb.isValid(numbers, i));
			} else {
				assertTrue(cb.isValid(numbers, i));
			}
		}
	}
	
	@Test
	public void firstNonValidExample() {
		CipherBreaker cb = new CipherBreaker(5);
		
		long[] numbers = {
				35, 20, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576
		};
		
		assertEquals(127, numbers[cb.firstNonValidIndex(numbers)]);
	}
	
	@Test
	public void contiguousSetExample() {
		CipherBreaker cb = new CipherBreaker(0);
		
		long[] numbers = {
				35, 20, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576
		};
		
		assertEquals(62, cb.findContiguousSet(numbers, 14 /* 127 */));
	}
	
}
