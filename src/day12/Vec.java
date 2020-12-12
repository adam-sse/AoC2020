package day12;

/**
 * An immutable 2-dimensional vector.
 * 
 * @author Adam
 */
public class Vec {

	/**
	 * The zero vector.
	 */
	public static Vec ZERO = new Vec(0, 0);
	
	private final int x;
	
	private final int y;
	
	/**
	 * Creates a new vector.
	 * 
	 * @param x The x component.
	 * @param y The y component.
	 */
	public Vec(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the first component of this vector.
	 * 
	 * @return The x component.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns the second component of this vector.
	 * 
	 * @return The y component.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Adds two vectors.
	 * 
	 * @param other The other vector to add to this one.
	 * 
	 * @return The sum of the two vectors.
	 */
	public Vec add(Vec other) {
		return new Vec(this.x + other.x, this.y + other.y);
	}
	
	/**
	 * Scales this vector by a scalar.
	 * 
	 * @param scalar The scaling factor.
	 * 
	 * @return The scaled vector.
	 */
	public Vec scale(int scalar) {
		return new Vec(this.x * scalar, this.y * scalar);
	}
	
	/**
	 * Rotates left by 90 degrees.
	 * 
	 * @return The rotated vector.
	 */
	public Vec rotateLeft() {
		return new Vec(-y, x);
	}
	
	/**
	 * Rotates right by 90 degrees.
	 * 
	 * @return The rotated vector.
	 */
	public Vec rotateRight() {
		return new Vec(y, -x);
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	/**
	 * Calculates the manhattan distance from the origin.
	 * 
	 * @return The distance to the origin.
	 */
	public int manhattan() {
		return Math.abs(x) + Math.abs(y);
	}
	
}
