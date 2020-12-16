package day16;

public class Rule {
	
	private String name;
	
	private Range r1;
	
	private Range r2;
	
	public Rule(String name, Range r1, Range r2) {
		this.name = name;
		this.r1 = r1;
		this.r2 = r2;
	}

	public boolean valueValid(int value) {
		return r1.contains(value) || r2.contains(value);
	}
	
	public String getName() {
		return name;
	}
	
}