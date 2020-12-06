package day4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PassportTest {

	@Test
	public void example() {
		assertEquals(2, Passport.read("/day4/testinput.txt")
				.filter((passport) -> passport.isValid())
				.count());
	}
	
	@Test
	public void exampleValid() {
		assertEquals(4, Passport.read("/day4/valid.txt")
				.filter((passport) -> passport.isValid())
				.count());
	}
	
	@Test
	public void exampleInvalid() {
		assertEquals(0, Passport.read("/day4/invalid.txt")
				.filter((passport) -> passport.isValid())
				.count());
	}
	
}
