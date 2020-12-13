package day13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BusStopTest {

	@Test
	public void earliestAfterExample() {
		BusStop bs = new BusStop("7,13,x,x,59,x,31,19");
		
		assertEquals(295, bs.getEarliestDepartureAfter(939));
	}
	
	@Test
	public void earliestTogetherExample1() {
		BusStop bs = new BusStop("7,13,x,x,59,x,31,19");
		assertEquals(1068781, bs.getEarliestSequenceStart());
	}
	
	@Test
	public void earliestTogetherExample2() {
		BusStop bs = new BusStop("17,x,13,19");
		assertEquals(3417, bs.getEarliestSequenceStart());
	}
	
	@Test
	public void earliestTogetherExample3() {
		BusStop bs = new BusStop("67,7,59,61");
		assertEquals(754018, bs.getEarliestSequenceStart());
	}
	
	@Test
	public void earliestTogetherExample4() {
		BusStop bs = new BusStop("67,x,7,59,61");
		assertEquals(779210, bs.getEarliestSequenceStart());
	}
	
	@Test
	public void earliestTogetherExample5() {
		BusStop bs = new BusStop("67,7,x,59,61");
		assertEquals(1261476, bs.getEarliestSequenceStart());
	}
	
	@Test
	public void earliestTogetherExample6() {
		BusStop bs = new BusStop("1789,37,47,1889");
		assertEquals(1202161486, bs.getEarliestSequenceStart());
	}
	
}
