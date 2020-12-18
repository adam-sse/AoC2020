package day18;

public class Addition extends Operator {

	public Addition(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	protected long evaluate(long left, long right) {
		return left + right;
	}

	@Override
	protected char getOperatorChar() {
		return '+';
	}

}
