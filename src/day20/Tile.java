package day20;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Tile {

	public static final int SIZE = 10;
	
	private int id;
	
	private boolean[][] content;
	
	public Tile(List<String> text) {
		String firstLine = text.get(0);
		this.id = Integer.parseInt(firstLine.substring("Tile ".length(), "Tile ".length() + 4));
		
		content = new boolean[SIZE][SIZE];
		for (int i = 1; i <= SIZE; i++) {
			int j = 0;
			for (char ch : text.get(i).toCharArray()) {
				content[i - 1][j++] = ch == '.';	
			}
		}
	}
	
	public int getId() {
		return id;
	}
	
	public char getChar(int row, int column) {
		return content[row][column] ? '.' : '#';
	}
	
	private boolean getContent(Pos pos) {
		return content[pos.row] [pos.column];
	}
	
	private static class Pos {
		private int row;
		private int column;
		
		public Pos(int row, int column) {
			this.row = row;
			this.column = column;
		}
		
		@Override
		public String toString() {
			return "[" + row + ";" + column + "]";
		}
		
	}
	
	public enum Side {
		LEFT(  IntStream.range(0, SIZE).mapToObj((i) -> new Pos(i, 0))),
		RIGHT( IntStream.range(0, SIZE).mapToObj((i) -> new Pos(i, SIZE - 1))),
		TOP(   IntStream.range(0, SIZE).mapToObj((i) -> new Pos(0, i))),
		BOTTOM(IntStream.range(0, SIZE).mapToObj((i) -> new Pos(SIZE - 1, i))),
		LEFT_REV(  IntStream.range(0, SIZE).boxed().sorted(Collections.reverseOrder()).map((i) -> new Pos(i, 0))),
		RIGHT_REV( IntStream.range(0, SIZE).boxed().sorted(Collections.reverseOrder()).map((i) -> new Pos(i, SIZE - 1))),
		TOP_REV(   IntStream.range(0, SIZE).boxed().sorted(Collections.reverseOrder()).map((i) -> new Pos(0, i))),
		BOTTOM_REV(IntStream.range(0, SIZE).boxed().sorted(Collections.reverseOrder()).map((i) -> new Pos(SIZE - 1, i))),
		;
		
		private List<Pos> iterator;
		
		private Side(Stream<Pos> positions) {
			this.iterator = positions.collect(Collectors.toUnmodifiableList());
		}
		
		public Iterable<Pos> posIterator() {
			return iterator;
		}
		
	}
	
	public boolean someSideMatches(Tile other) {
		return getMatchingSide(other) != null;
	}
	
	public Side getMatchingSide(Tile other) {
		Side matchingSide = null;
		
		outer: for (Side thisSide : Side.values()) {
			for (Side otherSide : Side.values()) {
				if (matches(this, thisSide, other, otherSide)) {
					matchingSide = thisSide;
					break outer;
				}
			}
		}
		
		return matchingSide;
	}
	
	public Side getMatchingSide(Tile other, Side sideOther) {
		Side matchingSide = null;
		
		for (Side thisSide : Side.values()) {
			if (matches(this, thisSide, other, sideOther)) {
				matchingSide = thisSide;
				break;
			}
		}
		
		return matchingSide;
	}
	
	public static boolean matches(Tile t1, Side s1, Tile t2, Side s2) {
		boolean match = true;
		
		Iterator<Pos> it2 = s2.iterator.iterator();
		for (Pos p1 : s1.iterator) {
			Pos p2 = it2.next();
			if (t1.getContent(p1) != t2.getContent(p2)) {
				match = false;
				break;
			}
		}
		
		return match;
	}
	
	public void flipHorizontal() {
		boolean[][] newContent = new boolean[SIZE][SIZE];
		
		for (int row = 0; row < SIZE; row++) {
			for (int column = 0; column < SIZE; column ++) {
				newContent[row][column] = this.content[row][SIZE - 1 - column];
			}
		}

		this.content = newContent;
	}
	
	public void rotateLeft() {
		boolean[][] newContent = new boolean[SIZE][SIZE];
		
		for (int row = 0; row < SIZE; row++) {
			for (int column = 0; column < SIZE; column ++) {
				newContent[row][column] = this.content[column][SIZE - 1 - row];
			}
		}

		this.content = newContent;
	}
	
	public void rotateRight() {
		rotateLeft();
		rotateLeft();
		rotateLeft();
	}
	
	public void flipVertical() {
		rotateLeft();
		flipHorizontal();
		rotateRight();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < SIZE; row++) {
			for (int column = 0; column < SIZE; column++) {
				sb.append(content[row][column] ? '.' : '#');
			}
			sb.append('\n');
		}
		return sb.toString();
	}

	public void flipToMatchRightAndBottomIgnoreRev(Tile right, Tile bottom) {
		switch (getMatchingSide(right)) {
		case RIGHT:
		case RIGHT_REV:
			break;
			
		case LEFT:
		case LEFT_REV:
			flipHorizontal();
			break;
			
		case TOP:
		case TOP_REV:
			rotateRight();
			break;
			
		case BOTTOM:
		case BOTTOM_REV:
			rotateLeft();
			break;
		}
		
		// make sure to not "harm" right by the transformation here
		switch (getMatchingSide(bottom)) {
		case BOTTOM:
		case BOTTOM_REV:
			break;
			
		case TOP:
		case TOP_REV:
			flipVertical();
			break;
			
		case RIGHT:
		case RIGHT_REV:
		case LEFT:
		case LEFT_REV:
			throw new IllegalStateException("can't happen");
		}
	}
	
	public void flipToMatchLeftExact(Tile left) {
		switch (getMatchingSide(left, Side.RIGHT)) {
		case LEFT:
			break;
			
		case LEFT_REV:
			flipVertical();
			break;
			
		case RIGHT:
			flipHorizontal();
			break;
			
		case RIGHT_REV:
			flipHorizontal();
			flipVertical();
			break;
			
		case BOTTOM:
			rotateRight();
			break;
			
		case BOTTOM_REV:
			rotateRight();
			flipVertical();
			break;
			
		case TOP:
			rotateLeft();
			flipVertical();
			break;
			
		case TOP_REV:
			rotateLeft();
			break;
		}
		
		if (!matches(this, Side.LEFT, left, Side.RIGHT)) {
			throw new IllegalStateException();
		}
	}
	
	public void flipToMatchAboveExact(Tile above) {
		switch (getMatchingSide(above, Side.BOTTOM)) {
		case TOP:
			break;
		case TOP_REV:
			flipHorizontal();
			break;
			
		case BOTTOM:
			flipVertical();
			break;
		case BOTTOM_REV:
			flipHorizontal();
			flipVertical();
			break;
			
		case LEFT:
			rotateRight();
			flipHorizontal();
			break;
		case LEFT_REV:
			rotateRight();
			break;
			
		case RIGHT:
			rotateLeft();
			break;
		case RIGHT_REV:
			rotateLeft();
			flipHorizontal();
			break;
		}
		
		if (!matches(this, Side.TOP, above, Side.BOTTOM)) {
			throw new IllegalStateException();
		}
	}
	
}
