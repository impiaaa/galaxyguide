import java.util.TreeSet;


/**
 * TODO Put here a description of what this class does.
 *
 * @author localmgr.
 *         Created Feb 8, 2012.
 */
public class Galaxy {
	private TreeSet<Star> stars;

	public Galaxy(TreeSet<Star> stars) {
		this.stars = stars;
	}
	
	/**
	 * Returns the value of the field called 'stars'.
	 * @return Returns the stars.
	 */
	public TreeSet<Star> getStars() {
		return this.stars;
	}
	
}
