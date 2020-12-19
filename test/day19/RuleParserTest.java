package day19;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class RuleParserTest {

	@Test
	public void example1() {
		RuleParser parser = new RuleParser(Arrays.asList(
				"0: 1 2",
				"1: \"a\"",
				"2: 1 3 | 3 1",
				"3: \"b\""
				));
		
		Rule zero = parser.getRule(0);
		assertTrue(zero.canMatch("aab"));
		assertTrue(zero.canMatch("aba"));
		
		assertFalse(zero.canMatch("ac"));
	}
	
	@Test
	public void example2() {
		RuleParser parser = new RuleParser(Arrays.asList(
				"0: 4 1 5",
				"1: 2 3 | 3 2",
				"2: 4 4 | 5 5",
				"3: 4 5 | 5 4",
				"4: \"a\"",
				"5: \"b\""
				));
		
		Rule zero = parser.getRule(0);
		
		assertTrue(zero.canMatch("aaaabb"));
		assertTrue(zero.canMatch("aaabab"));
		assertTrue(zero.canMatch("abbabb"));
		assertTrue(zero.canMatch("abbbab"));
		assertTrue(zero.canMatch("aabaab"));
		assertTrue(zero.canMatch("aabbbb"));
		assertTrue(zero.canMatch("abaaab"));
		assertTrue(zero.canMatch("ababbb"));
		assertTrue(zero.canMatch("ababbb"));
		
		assertFalse(zero.canMatch("abbbb"));
		assertFalse(zero.canMatch("ac"));
	}
	
	@Test
	public void example3() {
		RuleParser parser = new RuleParser(Arrays.asList(
				"42: 9 14 | 10 1",
				"9: 14 27 | 1 26",
				"10: 23 14 | 28 1",
				"1: \"a\"",
				"11: 42 31",
				"5: 1 14 | 15 1",
				"19: 14 1 | 14 14",
				"12: 24 14 | 19 1",
				"16: 15 1 | 14 14",
				"31: 14 17 | 1 13",
				"6: 14 14 | 1 14",
				"2: 1 24 | 14 4",
				"0: 8 11",
				"13: 14 3 | 1 12",
				"15: 1 | 14",
				"17: 14 2 | 1 7",
				"23: 25 1 | 22 14",
				"28: 16 1",
				"4: 1 1",
				"20: 14 14 | 1 15",
				"3: 5 14 | 16 1",
				"27: 1 6 | 14 18",
				"14: \"b\"",
				"21: 14 1 | 1 14",
				"25: 1 1 | 1 14",
				"22: 14 14",
				"8: 42",
				"26: 14 22 | 1 20",
				"18: 15 15",
				"7: 14 5 | 1 21",
				"24: 14 1"
				));
		
		Rule zero = parser.getRule(0);
		
		assertFalse(zero.canMatch("abbbbbabbbaaaababbaabbbbabababbbabbbbbbabaaaa"));
		assertTrue(zero.canMatch("bbabbbbaabaabba"));
		assertFalse(zero.canMatch("babbbbaabbbbbabbbbbbaabaaabaaa"));
		assertFalse(zero.canMatch("aaabbbbbbaaaabaababaabababbabaaabbababababaaa"));
		assertFalse(zero.canMatch("bbbbbbbaaaabbbbaaabbabaaa"));
		assertFalse(zero.canMatch("bbbababbbbaaaaaaaabbababaaababaabab"));
		assertTrue(zero.canMatch("ababaaaaaabaaab"));
		assertTrue(zero.canMatch("ababaaaaabbbaba"));
		assertFalse(zero.canMatch("baabbaaaabbaaaababbaababb"));
		assertFalse(zero.canMatch("abbbbabbbbaaaababbbbbbaaaababb"));
		assertFalse(zero.canMatch("aaaaabbaabaaaaababaa"));
		assertFalse(zero.canMatch("aaaabbaaaabbaaa"));
		assertFalse(zero.canMatch("aaaabbaabbaaaaaaabbbabbbaaabbaabaaa"));
		assertFalse(zero.canMatch("babaaabbbaaabaababbaabababaaab"));
		assertFalse(zero.canMatch("aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba"));
	}
	
	@Test
	public void example3Replaced() {
		RuleParser parser = new RuleParser(Arrays.asList(
				"42: 9 14 | 10 1",
				"9: 14 27 | 1 26",
				"10: 23 14 | 28 1",
				"1: \"a\"",
				"11: 42 31 | 42 11 31",
				"5: 1 14 | 15 1",
				"19: 14 1 | 14 14",
				"12: 24 14 | 19 1",
				"16: 15 1 | 14 14",
				"31: 14 17 | 1 13",
				"6: 14 14 | 1 14",
				"2: 1 24 | 14 4",
				"0: 8 11",
				"13: 14 3 | 1 12",
				"15: 1 | 14",
				"17: 14 2 | 1 7",
				"23: 25 1 | 22 14",
				"28: 16 1",
				"4: 1 1",
				"20: 14 14 | 1 15",
				"3: 5 14 | 16 1",
				"27: 1 6 | 14 18",
				"14: \"b\"",
				"21: 14 1 | 1 14",
				"25: 1 1 | 1 14",
				"22: 14 14",
				"8: 42 | 42 8",
				"26: 14 22 | 1 20",
				"18: 15 15",
				"7: 14 5 | 1 21",
				"24: 14 1"
				));
		
		Rule zero = parser.getRule(0);
		
		assertFalse(zero.canMatch("abbbbbabbbaaaababbaabbbbabababbbabbbbbbabaaaa"));
		assertTrue(zero.canMatch("bbabbbbaabaabba"));
		assertTrue(zero.canMatch("babbbbaabbbbbabbbbbbaabaaabaaa"));
		assertTrue(zero.canMatch("aaabbbbbbaaaabaababaabababbabaaabbababababaaa"));
		assertTrue(zero.canMatch("bbbbbbbaaaabbbbaaabbabaaa"));
		assertTrue(zero.canMatch("bbbababbbbaaaaaaaabbababaaababaabab"));
		assertTrue(zero.canMatch("ababaaaaaabaaab"));
		assertTrue(zero.canMatch("ababaaaaabbbaba"));
		assertTrue(zero.canMatch("baabbaaaabbaaaababbaababb"));
		assertTrue(zero.canMatch("abbbbabbbbaaaababbbbbbaaaababb"));
		assertTrue(zero.canMatch("aaaaabbaabaaaaababaa"));
		assertFalse(zero.canMatch("aaaabbaaaabbaaa"));
		assertTrue(zero.canMatch("aaaabbaabbaaaaaaabbbabbbaaabbaabaaa"));
		assertFalse(zero.canMatch("babaaabbbaaabaababbaabababaaab"));
		assertTrue(zero.canMatch("aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba"));
	}
	
	@Test
	public void ownRecursiveRule() {
		RuleParser parser = new RuleParser(Arrays.asList(
				"0: 2 3",
				"1: \"a\"",
				"2: \"b\"",
				"3: 1 | 1 3"
				));
		
		Rule zero = parser.getRule(0);
		
		assertTrue(zero.canMatch("ba"));
		assertTrue(zero.canMatch("baa"));
	}
	
}
