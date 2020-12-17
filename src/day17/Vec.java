package day17;

import java.util.Iterator;
import java.util.Objects;

public class Vec {

	private final int x;
	
	private final int y;
	
	private final int z;
	
	private final int w;
	
	public Vec(int x, int y, int z, int w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public Iterable<Vec> iterateNeighbors() {
		return () -> new Iterator<Vec>() {

			private int i = 0;
			
			@Override
			public Vec next() {
				int i = this.i;
				
				int dx = i % 3 - 1;
				i /= 3;
				int dy = i % 3 - 1;
				i /= 3;
				int dz = i % 3 - 1;
				i /= 3;
				int dw = i - 1;
				
				this.i++;
				
				if (dx == 0 && dy == 0 && dz == 0 && dw == 0) {
					// current position is not a neighbor, use next one
					return next();
					
				} else {
					return new Vec(x + dx, y + dy, z + dz, w + dw);
				}
			}
			
			@Override
			public boolean hasNext() {
				return i <= 80;
			}
			
		};
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
	}
	
	public int getW() {
		return w;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + "," + z + "," + w + ")";
	}

	@Override
	public int hashCode() {
		return Objects.hash(w, x, y, z);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Vec)) {
			return false;
		}
		Vec other = (Vec) obj;
		return w == other.w && x == other.x && y == other.y && z == other.z;
	}
	
}
