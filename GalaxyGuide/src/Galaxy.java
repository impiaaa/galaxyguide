import java.util.TreeSet;


/**
 * A galaxy that holds stars.
 *
 * @author Brandon Cox
 *         Created Feb 8, 2012.
 */
public class Galaxy {
	private TreeSet<Star> stars;

	/**
	 * Creates a new galaxy given a set of stars.
	 *
	 * @param stars
	 */
	public Galaxy(TreeSet<Star> stars) {
		this.stars = stars;
	}
	
	/**
	 * Returns the set of stars.
	 * @return Returns the stars.
	 */
	public TreeSet<Star> getStars() {
		return this.stars;
	}
	
}
