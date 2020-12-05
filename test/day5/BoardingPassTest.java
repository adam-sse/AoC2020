package day5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BoardingPassTest {

	@Test
	public void example1() {
		BoardingPass pb = new BoardingPass("FBFBBFFRLR");
		assertEquals(44, pb.getRow());
		assertEquals(5, pb.getColumn());
		assertEquals(357, pb.getSeatId());
	}
	
	@Test
	public void example2() {
		BoardingPass pb = new BoardingPass("BFFFBBFRRR");
		assertEquals(70, pb.getRow());
		assertEquals(7, pb.getColumn());
		assertEquals(567, pb.getSeatId());
	}
	
	@Test
	public void example3() {
		BoardingPass pb = new BoardingPass("FFFBBBFRRR");
		assertEquals(14, pb.getRow());
		assertEquals(7, pb.getColumn());
		assertEquals(119, pb.getSeatId());
	}
	
	@Test
	public void example4() {
		BoardingPass pb = new BoardingPass("BBFFBBFRLL");
		assertEquals(102, pb.getRow());
		assertEquals(4, pb.getColumn());
		assertEquals(820, pb.getSeatId());
	}
	
}
