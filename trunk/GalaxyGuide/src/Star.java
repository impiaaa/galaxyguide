import java.util.LinkedList;

/**
 * This star class acts as a node for the A star pathfinding algorithm
 *
 * @author Brandon Cox, Spencer Alves, Eric Guilford
 *         Created Feb 2, 2012.
 */

public class Star implements Comparable<Star> {
	private String name;
	private Vector3D<Double> position;
	private LinkedList<Connection> connections;
	private int interestLevel;
	private LinkedList<String> attractions;
	private double range; 
	
	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param name
	 */
	public Star(String name){
		this.setName(name);
		this.setPosition(new Vector3D<Double>(0.,0.,0.));
		this.connections = new LinkedList<Connection>();
		this.attractions = new LinkedList<String>();
		
	}

	/**
	 * Returns the value of the field called 'name'.
	 * @return Returns the name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the field called 'name' to the given value.
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the value of the field called 'position'.
	 * @return Returns the position.
	 */
	public Vector3D<Double> getPosition() {
		return this.position;
	}

	/**
	 * Sets the field called 'position' to the given value.
	 * @param position The position to set.
	 */
	public void setPosition(Vector3D<Double> position) {
		this.position = position;
	}

	/**
	 * Returns the value of the field called 'interestLevel'.
	 * @return Returns the interestLevel.
	 */
	public int getInterestLevel() {
		return this.interestLevel;
	}

	/**
	 * Sets the field called 'interestLevel' to the given value.
	 * @param interestLevel The interestLevel to set.
	 */
	public void setInterestLevel(int interestLevel) {
		this.interestLevel = interestLevel;
	}
	
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param attraction
	 */
	public void addAttraction(String attraction){
		this.attractions.add(attraction);
	}
	
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param connection
	 */
	public void addConnection(Connection connection){
		this.connections.add(connection);
	}
	
	/**
	 * Returns the value of the field called 'connections'.
	 * @return Returns the connections.
	 */
	public LinkedList<Connection> getConnections() {
		return this.connections;
	}
	
	/**
	 * Returns the value of the field called 'attractions'.
	 * @return Returns the attractions.
	 */
	public LinkedList<String> getAttractions() {
		return this.attractions;
	}

	@Override
	public int compareTo(Star other) {
		return this.name.compareTo(other.name);
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public double getRange() {
		return this.range;
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param range
	 */
	public void setRange(double range) {
		this.range = range;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
}
