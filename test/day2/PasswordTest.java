package day2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class PasswordTest {
	
	@Test
	public void example() {
		assertTrue(new Password("1-3 a: abcde").isValid());
		assertFalse(new Password("1-3 b: cdefg").isValid());
		assertFalse(new Password("2-9 c: ccccccccc").isValid());
	}

}
