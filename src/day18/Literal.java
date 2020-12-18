package day18;

public class Literal extends Expression {

	private int value;
	
	public Literal(int value) {
		this.value = value;
	}
	
	@Override
	public long evaluate() {
		return value;
	}
	
	@Override
	public String toString() {
		return Integer.toString(value);
	}
	
}
