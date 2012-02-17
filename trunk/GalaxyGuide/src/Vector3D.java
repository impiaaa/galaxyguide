
/**
 * Stores a point in 3d space, or a vector in 3d space.
 *
 * @author alvessr.
 *         Created Feb 2, 2012.
 */
public class Vector3D {
	/**
	 * The x coordinate of the vector.
	 */
	public double x;
	/**
	 * The y coordinate of the vector.
	 */
	public double y;
	/**
	 * The z coordinate of the vector.
	 */
	public double z;
	/**
	 * Creates a new vector given the x, y and z coordinates.
	 *
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public String toString(){
		return ":"+(int)this.x+":"+(int)this.y+":"+(int)this.z;
	}
}
