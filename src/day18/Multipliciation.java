package day18;

public class Multipliciation extends Operator {

	public Multipliciation(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	protected long evaluate(long left, long right) {
		return left * right;
	}

	@Override
	protected char getOperatorChar() {
		return '*';
	}

}
