package day24;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PosititionTest {

	@Test
	public void moveBackToOriginExample() {
		Position p = new Position(0, 0);
		
		// nw w sw e e
		p.move(Direction.NORTH_WEST);
		p.move(Direction.WEST);
		p.move(Direction.SOUT_WEST);
		p.move(Direction.EAST);
		p.move(Direction.EAST);
		
		assertEquals(new Position(0, 0), p);
	}
	
}
