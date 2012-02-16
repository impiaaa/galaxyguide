import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeSet;


/**
 * TODO Put here a description of what this class does.
 *
 * @author localmgr.
 *         Created Feb 12, 2012.
 */
public class TripPlanner {

	private PriorityQueue<Trip> possibleTrips;
	private HashMap<String, PriorityQueue<Trip>> attractionMap;
	
	private Star startingPoint;
	private double maxCost;
	
	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 */
	public TripPlanner(){
		this.possibleTrips = new PriorityQueue<Trip> ();
	}
	
	public PriorityQueue<Trip> getPossibleTrips(){
		return this.possibleTrips;
	}
	
	public HashMap<String, PriorityQueue<Trip>> getAttractionMap(){
		return this.attractionMap;
	}
	
	
	public void compileTrips(Star startingPoint, double maxCost){
		
		this.attractionMap = new HashMap<String, PriorityQueue<Trip>>();
		this.startingPoint = startingPoint;
		this.maxCost = maxCost;
				
		LinkedList<Connection> tripSoFar = new LinkedList<Connection>();
		tripSoFar.add(new Connection(startingPoint, 0.));
		
		TreeSet<Star> starsPassed = new TreeSet<Star>();
		starsPassed.add(startingPoint);
		
		this.getTrips(tripSoFar, 0., 0, new LinkedList<String>(), startingPoint, starsPassed);
		
		Iterator<Trip> tripIterator = this.possibleTrips.iterator();
		
		int num = 1;
		
		while(tripIterator.hasNext()){
			Trip curTrip = tripIterator.next();
						
			curTrip.setName("Trip " + num);
			
			num++;
			
		}	
	}

	
	
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param startingPoint
	 * @param maxCost
	 * @return
	 */
	private void getTrips(LinkedList<Connection> tripSoFar, double costSoFar, int attractionSoFar, LinkedList<String> attractionsSoFar, Star curStar, TreeSet<Star> starsPassed) {
		// TODO Auto-generated method stub.
		
		if (!starsPassed.contains(curStar)){
			attractionSoFar = curStar.getInterestLevel() + attractionSoFar;
			attractionsSoFar.addAll(curStar.getAttractions());
		}
		
		starsPassed.add(curStar);
		
		if (curStar == this.startingPoint){
			// add this trip
			Trip tripToHere = new Trip(tripSoFar, costSoFar, attractionSoFar, attractionsSoFar);
			
			// We need to iterate through the attractions list here and add this new trip to all the correct hashMap entries.
			
			Iterator<String> attractionIterator = attractionsSoFar.iterator();
			
			while(attractionIterator.hasNext()){
				String curAttraction = attractionIterator.next();
				
				PriorityQueue<Trip> tripList;
				
				if (this.attractionMap.containsKey(curAttraction)){
					tripList = this.attractionMap.get(curAttraction);
				}
				else{
					tripList = new PriorityQueue<Trip>();
				}
				
				
				tripList.add(tripToHere);
				
				this.attractionMap.put(curAttraction, tripList);
			}
			
			this.possibleTrips.add(tripToHere);
		}
		
		Iterator<Connection> connectionIterator = curStar.getConnections().iterator();
		
		while(connectionIterator.hasNext()){
			Connection connector = connectionIterator.next();
			double connectionCost = connector.getCost();
						
			if (costSoFar + connectionCost < this.maxCost){
				
				Star connectingTo = connector.getTarget();
				
				LinkedList<Connection> newConnectionList = (LinkedList<Connection>) tripSoFar.clone();
				newConnectionList.add(connector);
				
				
				this.getTrips(newConnectionList, costSoFar + connectionCost, attractionSoFar, attractionsSoFar, connectingTo, (TreeSet<Star>)starsPassed.clone());
			}
			
		}
		
		// if the cost so far + the cost of the connection is less than the maximum cost, proceed
		
	}
	
}
