package day4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import util.Util;

public class PassportTest {

	@Test
	public void example() {
		List<Passport> passports = Passport.read(Util.readInputFile("/day4/testinput.txt"));
		
		assertEquals(2, passports.stream()
				.filter((passport) -> passport.isValid())
				.count());
	}
	
	@Test
	public void exampleValid() {
		List<Passport> passports = Passport.read(Util.readInputFile("/day4/valid.txt"));
		
		assertEquals(4, passports.stream()
				.filter((passport) -> passport.isValid())
				.count());
	}
	
	@Test
	public void exampleInvalid() {
		List<Passport> passports = Passport.read(Util.readInputFile("/day4/invalid.txt"));
		
		assertEquals(0, passports.stream()
				.filter((passport) -> passport.isValid())
				.count());
	}
	
}
