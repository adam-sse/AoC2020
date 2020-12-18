package day18;

public abstract class Operator extends Expression {

	private Expression left;
	
	private Expression right;
	
	public Operator(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public long evaluate() {
		return evaluate(left.evaluate(), right.evaluate());
	}
	
	@Override
	public String toString() {
		return "(" + left.toString() + ") " + getOperatorChar() + " (" + right.toString() + ")";
	}
	
	protected abstract long evaluate(long left, long right);
	
	protected abstract char getOperatorChar();
	
}
