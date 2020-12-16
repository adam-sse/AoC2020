package day16;

public class Range {
	
	private int min;
	
	private int max;

	public Range(int min, int max) {
		this.min = min;
		this.max = max;
	}
	
	public boolean contains(int value) {
		return min <= value && max >= value;
	}
	
}
