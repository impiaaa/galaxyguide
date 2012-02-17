
/**
 * A connection class that holds a target star, accompanied by a cost.
 *
 * @author Brandon Cox
 *         Created Feb 8, 2012.
 */

public class Connection implements Comparable<Connection> {
	private Star target;
	private double cost;

	/**
	 * Creates a new connection given a target star and a cost.
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

	@Override
	public int compareTo(Connection o) {
		
		double otherCost = o.getCost();
		
		if(this.cost > otherCost){
			return 1;
		}
		else if (this.cost < otherCost){
			return -1;
		}
		
		return 0;
	}
}
