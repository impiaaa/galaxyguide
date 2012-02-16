import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;


/**
 * TODO Put here a description of what this class does.
 *
 * @author Brandon Cox
 *         Created Feb 12, 2012.
 */
public class Trip implements Comparable<Trip> {

	private LinkedList<Star> connections;
	private double totalCost;
	private int totalAttraction;
	private LinkedList<String> attractions;
	private String name;

	
	public Trip(LinkedList<Connection> connections, double totalCost, int totalAttraction, LinkedList<String> attractions){
		
		LinkedList<Star> starList = new LinkedList<Star>();
		
		Iterator<Connection> connectionIterator = connections.iterator();
		
		while(connectionIterator.hasNext()){
			Connection con = connectionIterator.next();
			
			starList.add(con.getTarget());
			
		}
		
		this.connections = starList;
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
	public LinkedList<Star> getConnections() {
		return connections;
	}

	/**
	 * Sets the field called 'connections' to the given value.
	 * @param connections The connections to set.
	 */
	public void setConnections(LinkedList<Star> connections) {
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
	
	public void setName(String name){
		this.name = name;
	}
	
	public String toString(){
		
		return this.name + " (Cost = " + (int)this.totalCost + " Attraction = " + this.totalAttraction + " NumStars = " + this.connections.size() + " )";
		
	}
	
}
