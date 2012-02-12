
/**
 * TODO Put here a description of what this class does.
 *
 * @author alvessr.
 *         Created Feb 2, 2012.
 */
public class Vector3D<T extends Number> {
	public T x;
	public T y;
	public T z;
	public Vector3D(T x, T y, T z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public String toString(){
		return ""+x+y+z;
	}
}
