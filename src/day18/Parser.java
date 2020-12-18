package day18;

import java.util.ArrayList;
import java.util.List;

import util.Util;

public class Parser {

	public static Expression parse(String line) {
		return convert(tokenize(line));
	}
	
	private static Expression convert(List<Token> tokens) {
		if (tokens.size() == 1) {
			return new Literal(((LiteralToken) tokens.get(0)).value);
		}
		
		// if we are fully enclosed by parenthesis, remove them
		if (tokens.get(0) instanceof ParenToken && tokens.get(tokens.size() - 1) instanceof ParenToken) {
			// check if 0 is opening for size() - 1
			boolean wholeExpressionInParenthesis = true;
			int depth = 1;
			for (int i = 1; i < tokens.size() - 1; i++) {
				Token token = tokens.get(i);
				if (token instanceof ParenToken) {
					depth += ((ParenToken) token).open ? 1 : -1;
				}
				if (depth == 0) {
					wholeExpressionInParenthesis = false;
					break;
				}
			}
			
			if (wholeExpressionInParenthesis) {
				return convert(tokens.subList(1, tokens.size() - 1));
			}
		}
		
		// find operator from the right that has the lowest precedence and is not nested
		int depth = 0;
		int operatorIndex = -1;
		int operatorPrecedence = Integer.MAX_VALUE;
		for (int i = tokens.size() - 1; i >= 0; i--) {
			Token token = tokens.get(i);
			if (depth == 0 && token instanceof OperatorToken) {
				OperatorToken op = (OperatorToken) token;
				if (op.precedence < operatorPrecedence) {
					operatorIndex = i;
					operatorPrecedence = op.precedence;
				}
			}
			if (token instanceof ParenToken) {
				depth += ((ParenToken) token).open ? -1 : 1;
			}
		}

		// parse left and right of the operator
		Expression left = convert(tokens.subList(0, operatorIndex));
		Expression right = convert(tokens.subList(operatorIndex + 1, tokens.size()));
		
		// create operator
		OperatorToken op = (OperatorToken) tokens.get(operatorIndex);
		if (op == OperatorToken.MUL) {
			return new Multipliciation(left, right);
		} else if (op == OperatorToken.ADD) {
			return new Addition(left, right);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	private static List<Token> tokenize(String line) {
		List<Token> result = new ArrayList<>(line.length());
		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			
			if (ch >= '0' && ch <= '9') {
				result.add(new LiteralToken(ch - '0'));
				
			} else if (ch == '*' || ch == '+') {
				result.add(OperatorToken.get(ch));
				
			} else if (ch == '(' || ch == ')') {
				result.add(ParenToken.get(ch));
			}
			
		}
		return result;
	}
	
	private static interface Token {
		
	}
	
	private static class LiteralToken implements Token {
		
		private int value;
		
		public LiteralToken(int value) {
			this.value = value;
		}
		
	}
	
	private enum OperatorToken implements Token {
		
		ADD(2),
		MUL(1);
		
		private int precedence;
		
		private OperatorToken(int precedence) {
			this.precedence = precedence;
		}
		
		public static OperatorToken get(char op) {
			switch (op) {
			case '+':
				return ADD;
			case '*':
				return MUL;
			default:
				throw new IllegalArgumentException();
			}
		}
		
	}
	
	private enum ParenToken implements Token {
		OPEN(true),
		CLOSE(false);
		
		private boolean open;
		
		private ParenToken(boolean open) {
			this.open = open;
		}
		
		public static ParenToken get(char ch) {
			switch (ch) {
			case '(':
				return OPEN;
			case ')':
				return CLOSE;
			default:
				throw new IllegalArgumentException();
			}
		}
	}
	
	public static void main(String[] args) {
		long sum = 0;
		for (String line : Util.readInputFile("/day18/input.txt")) {
			sum += Parser.parse(line).evaluate();
		}
		System.out.println(sum);
	}
	
}
