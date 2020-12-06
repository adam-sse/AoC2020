package day6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class GroupTest {

	@Test
	public void example1() {
		assertEquals(3, new Group(Arrays.asList("abc")).getNumAllYesAnserws());
	}
	
	@Test
	public void example2() {
		assertEquals(0, new Group(Arrays.asList("a", "b", "c")).getNumAllYesAnserws());
	}
	
	@Test
	public void example3() {
		assertEquals(1, new Group(Arrays.asList("ab", "ac")).getNumAllYesAnserws());
	}
	
	@Test
	public void example4() {
		assertEquals(1, new Group(Arrays.asList("a", "a", "a", "a")).getNumAllYesAnserws());
	}
	
	@Test
	public void example5() {
		assertEquals(1, new Group(Arrays.asList("b")).getNumAllYesAnserws());
	}
	
}
