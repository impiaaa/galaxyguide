/**
 * Serves as a heuristic function for the A-Star search algorithm
 * 
 * @author Zhihao Ni
 *         Created Feb 15, 2012.
 */
public interface Heuristic {

	/**
	 * Returns a heuristic estimate of the cost from one star to the other.
	 *
	 * @param start
	 * @param goal
	 * @return double
	 */
	public double hfunction(Star start, Star goal);

}
