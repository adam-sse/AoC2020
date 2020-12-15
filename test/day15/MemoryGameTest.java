package day15;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MemoryGameTest {

	@Test
	public void example1() {
		MemoryGame mg = new MemoryGame("0,3,6");
		assertEquals(0, mg.getNumberAtTurn(4));
		assertEquals(3, mg.getNumberAtTurn(5));
		assertEquals(3, mg.getNumberAtTurn(6));
		assertEquals(1, mg.getNumberAtTurn(7));
		assertEquals(0, mg.getNumberAtTurn(8));
		assertEquals(4, mg.getNumberAtTurn(9));
		assertEquals(0, mg.getNumberAtTurn(10));
		
		assertEquals(175594, mg.getNumberAtTurn(30000000));
	}
	
	@Test
	public void example2() {
		MemoryGame mg = new MemoryGame("1,3,2");
		assertEquals(1, mg.getNumberAtTurn(2020));
		assertEquals(2578, mg.getNumberAtTurn(30000000));
	}
	
	@Test
	public void example3() {
		MemoryGame mg = new MemoryGame("2,1,3");
		assertEquals(10, mg.getNumberAtTurn(2020));
		assertEquals(3544142, mg.getNumberAtTurn(30000000));
	}
	
	@Test
	public void example4() {
		MemoryGame mg = new MemoryGame("1,2,3");
		assertEquals(27, mg.getNumberAtTurn(2020));
		assertEquals(261214, mg.getNumberAtTurn(30000000));
	}
	
	@Test
	public void example5() {
		MemoryGame mg = new MemoryGame("2,3,1");
		assertEquals(78, mg.getNumberAtTurn(2020));
		assertEquals(6895259, mg.getNumberAtTurn(30000000));
	}
	
	@Test
	public void example6() {
		MemoryGame mg = new MemoryGame("3,2,1");
		assertEquals(438, mg.getNumberAtTurn(2020));
		assertEquals(18, mg.getNumberAtTurn(30000000));
	}
	
	@Test
	public void example7() {
		MemoryGame mg = new MemoryGame("3,1,2");
		assertEquals(1836, mg.getNumberAtTurn(2020));
		assertEquals(362, mg.getNumberAtTurn(30000000));
	}
	
}
