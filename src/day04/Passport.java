package day04;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import util.Util;

public class Passport {
	
	/*
    byr (Birth Year)
    iyr (Issue Year)
    eyr (Expiration Year)
    hgt (Height)
    hcl (Hair Color)
    ecl (Eye Color)
    pid (Passport ID)
    cid (Country ID)
	 */

	private Map<String, String> values = new HashMap<>();
	
	public Passport(List<String> lines) {
		lines.forEach(this::readLine);
	}
	
	private void readLine(String line) {
		String[] parts = line.split(" ");
		
		for (String part : parts) {
			String[] keyValue = part.split(":");
			this.values.put(keyValue[0], keyValue[1]);
		}
	}
	
	private boolean byrValid() {
		String value = values.get("byr");
		if (value == null) return false;
		
		int i = Integer.parseInt(value);
		return i >= 1920 && i <= 2002; 
	}
	
	private boolean iyrValid() {
		String value = values.get("iyr");
		if (value == null) return false;
		
		int i = Integer.parseInt(value);
		return i >= 2010 && i <= 2020; 
	}
	
	private boolean eyrValid() {
		String value = values.get("eyr");
		if (value == null) return false;
		
		int i = Integer.parseInt(value);
		return i >= 2020 && i <= 2030; 
	}
	
	private boolean hgtValid() {
		String value = values.get("hgt");
		if (value == null) return false;
		
		boolean valid = false;
		if (value.endsWith("cm")) {
			int number = Integer.parseInt(value.substring(0, value.length() - 2));
			valid = number >= 150 && number <= 193;
			
		} else if (value.endsWith("in")) {
			int number = Integer.parseInt(value.substring(0, value.length() - 2));
			valid = number >= 59 && number <= 76;
		}
		
		return valid; 
	}
	
	private boolean hclValid() {
		String value = values.get("hcl");
		if (value == null) return false;
		
		return value.matches("#[0-9a-f]{6}");
	}
	
	private boolean eclValid() {
		String value = values.get("ecl");
		if (value == null) return false;
		
		return value.matches("amb|blu|brn|gry|grn|hzl|oth");
	}
	
	private boolean pidValid() {
		String value = values.get("pid");
		if (value == null) return false;
		
		return value.matches("[0-9]{9}");
	}
	
	public boolean isValid() {
		return byrValid() && iyrValid() && eyrValid() && hgtValid() && hclValid() && eclValid() && pidValid();
	}
	
	static Stream<Passport> read(String resource) {
		return Util.readGroupedLineInputFile(resource).stream()
				.map((lineGroup) -> new Passport(lineGroup));
	}
	
	public static void main(String[] args) {
		long result = read("/day04/input.txt")
				.filter(Passport::isValid)
				.count();
		
		System.out.println(result);
	}
	
}
