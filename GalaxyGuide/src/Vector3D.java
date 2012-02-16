
/**
 * TODO Put here a description of what this class does.
 *
 * @author alvessr.
 *         Created Feb 2, 2012.
 */
public class Vector3D {
	public double x;
	public double y;
	public double z;
	public Vector3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public String toString(){
		return ":"+(int)x+":"+(int)y+":"+(int)z;
	}
}
