package day24;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {

	private int x;
	
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void move(Direction direction) {
		this.x += direction.getDiffX();
		this.y += direction.getDiffY();
	}
	
	public Iterable<Position> iterateNeighbors() {
		List<Position> neighbors = new ArrayList<>(6);
		
		for (Direction dir : Direction.values()) {
			Position neighbor = new Position(this.x, this.y);
			neighbor.move(dir);
			neighbors.add(neighbor);
		}
		
		return neighbors;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Position)) {
			return false;
		}
		Position other = (Position) obj;
		return x == other.x && y == other.y;
	}
	
	@Override
	public String toString() {
		return "(" + x + ";" + y + ")";
	}
	
}
