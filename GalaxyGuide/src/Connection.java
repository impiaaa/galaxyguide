
/**
 * TODO Put here a description of what this class does.
 *
 * @author localmgr.
 *         Created Feb 8, 2012.
 */

public class Connection {
	private Star target;
	private double cost;
	private double distance;

	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param target
	 * @param cost
	 */
	public Connection(Star target, double cost) {
		this.target = target;
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		return "Connection [target=" + this.target.getName() + ", cost=" + this.cost
				+ "]";
	}

	/**
	 * Returns the value of the field called 'target'.
	 * @return Returns the target.
	 */
	public Star getTarget() {
		return this.target;
	}
	
	/**
	 * Sets the field called 'target' to the given value.
	 * @param target The target to set.
	 */
	public void setTarget(Star target) {
		this.target = target;
	}
	
	/**
	 * Returns the value of the field called 'cost'.
	 * @return Returns the cost.
	 */
	public double getCost() {
		return this.cost;
	}
	
	/**
	 * Sets the field called 'cost' to the given value.
	 * @param cost The cost to set.
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
}
