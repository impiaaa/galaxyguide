import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;


/**
 * TODO Put here a description of what this class does.
 *
 * @author localmgr.
 *         Created Feb 12, 2012.
 */
public class Trip implements Comparable<Trip> {

	private LinkedList<Connection> connections;
	private double totalCost;
	private int totalAttraction;
	private LinkedList<String> attractions;

	
	public Trip(LinkedList<Connection> connections, double totalCost, int totalAttraction, LinkedList<String> attractions){
		
		this.connections = connections;
		this.totalAttraction = totalAttraction;
		this.totalCost = totalCost;
		this.attractions = attractions;
		
	}
	
	@Override
	public int compareTo(Trip o) {
		
		if (this.totalAttraction > o.getTotalAttraction()){
			return 1;
		}
		else if (this.totalAttraction < o.getTotalAttraction()){
			return -1;
		}
		
		return 0;
	}

	/**
	 * Returns the value of the field called 'totalAttraction'.
	 * @return Returns the totalAttraction.
	 */
	public int getTotalAttraction() {
		return this.totalAttraction;
	}

	/**
	 * Sets the field called 'totalAttraction' to the given value.
	 * @param totalAttraction The totalAttraction to set.
	 */
	public void setTotalAttraction(int totalAttraction) {
		this.totalAttraction = totalAttraction;
	}

	/**
	 * Returns the value of the field called 'totalCost'.
	 * @return Returns the totalCost.
	 */
	public double getTotalCost() {
		return totalCost;
	}

	/**
	 * Sets the field called 'totalCost' to the given value.
	 * @param totalCost The totalCost to set.
	 */
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	/**
	 * Returns the value of the field called 'connections'.
	 * @return Returns the connections.
	 */
	public LinkedList<Connection> getConnections() {
		return connections;
	}

	/**
	 * Sets the field called 'connections' to the given value.
	 * @param connections The connections to set.
	 */
	public void setConnections(LinkedList<Connection> connections) {
		this.connections = connections;
	}

	/**
	 * Returns the value of the field called 'attractions'.
	 * @return Returns the attractions.
	 */
	public LinkedList<String> getAttractions() {
		return attractions;
	}

	/**
	 * Sets the field called 'attractions' to the given value.
	 * @param attractions The attractions to set.
	 */
	public void setAttractions(LinkedList<String> attractions) {
		this.attractions = attractions;
	}
}
