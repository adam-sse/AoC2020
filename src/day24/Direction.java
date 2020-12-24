package day24;

public enum Direction {

	// e, se, sw, w, nw, and ne
	
	EAST(1, 2, 0),
	SOUT_EAST(2, 1, -1),
	SOUT_WEST(2, -1, -1),
	WEST(1, -2, 0),
	NORTH_WEST(2, -1, 1),
	NORTH_EAST(2, 1, 1);
	
	private int numChars;
	
	private int diffX;
	
	private int diffY;
	
	private Direction(int numChars, int diffX, int diffY) {
		this.numChars = numChars;
		this.diffX = diffX;
		this.diffY = diffY;
	}
	
	public int getNumChars() {
		return numChars;
	}
	
	public int getDiffX() {
		return diffX;
	}
	
	public int getDiffY() {
		return diffY;
	}
	
	public static Direction fromString(String str) {
		if (str.startsWith("e")) {
			return EAST;
		} else if (str.startsWith("w")) {
			return WEST;
		} else {
			switch (str.substring(0, 2)) {
			case "se":
				return SOUT_EAST;
			case "sw":
				return SOUT_WEST;
			case "nw":
				return NORTH_WEST;
			case "ne":
				return NORTH_EAST;
			default:
				throw new IllegalArgumentException(str);
			}
		}
	}
	
	
}
