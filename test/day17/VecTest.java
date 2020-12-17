package day17;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class VecTest {

	@Test
	public void iterateNeighbors() {
		Vec vec = new Vec(1, 1, 1, 1);
		
		Iterator<Vec> it = vec.iterateNeighbors().iterator();

		for (int w = 0; w <= 2; w++) {
			assertEquals(new Vec(0, 0, 0, w), it.next());
			assertEquals(new Vec(1, 0, 0, w), it.next());
			assertEquals(new Vec(2, 0, 0, w), it.next());
			
			assertEquals(new Vec(0, 1, 0, w), it.next());
			assertEquals(new Vec(1, 1, 0, w), it.next());
			assertEquals(new Vec(2, 1, 0, w), it.next());
			
			assertEquals(new Vec(0, 2, 0, w), it.next());
			assertEquals(new Vec(1, 2, 0, w), it.next());
			assertEquals(new Vec(2, 2, 0, w), it.next());
			
			
			assertEquals(new Vec(0, 0, 1, w), it.next());
			assertEquals(new Vec(1, 0, 1, w), it.next());
			assertEquals(new Vec(2, 0, 1, w), it.next());
			
			assertEquals(new Vec(0, 1, 1, w), it.next());
			if (w != 1) {
				assertEquals(new Vec(1, 1, 1, w), it.next());
			}
			assertEquals(new Vec(2, 1, 1, w), it.next());
			
			assertEquals(new Vec(0, 2, 1, w), it.next());
			assertEquals(new Vec(1, 2, 1, w), it.next());
			assertEquals(new Vec(2, 2, 1, w), it.next());
			
			
			assertEquals(new Vec(0, 0, 2, w), it.next());
			assertEquals(new Vec(1, 0, 2, w), it.next());
			assertEquals(new Vec(2, 0, 2, w), it.next());
			
			assertEquals(new Vec(0, 1, 2, w), it.next());
			assertEquals(new Vec(1, 1, 2, w), it.next());
			assertEquals(new Vec(2, 1, 2, w), it.next());
			
			assertEquals(new Vec(0, 2, 2, w), it.next());
			assertEquals(new Vec(1, 2, 2, w), it.next());
			assertEquals(new Vec(2, 2, 2, w), it.next());
		}
		
		assertFalse(it.hasNext());
	}
	
}
