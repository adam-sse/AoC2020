package day18;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {

	@Test
	public void simpleExample() {
		Expression e = Parser.parse("1 + 2 * 3 + 4 * 5 + 6");
		assertEquals(231, e.evaluate());
	}
	
	@Test
	public void parenExample() {
		Expression e = Parser.parse("1 + (2 * 3) + (4 * (5 + 6))");
		assertEquals(51, e.evaluate());
	}
	
	@Test
	public void example1() {
		Expression e = Parser.parse("2 * 3 + (4 * 5)");
		assertEquals(46, e.evaluate());
	}
	
	@Test
	public void example2() {
		Expression e = Parser.parse("5 + (8 * 3 + 9 + 3 * 4 * 3)");
		assertEquals(1445, e.evaluate());
	}
	
	@Test
	public void example3() {
		Expression e = Parser.parse("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))");
		assertEquals(669060, e.evaluate());
	}
	
	@Test
	public void example4() {
		Expression e = Parser.parse("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2");
		assertEquals(23340, e.evaluate());
	}
	
}
