import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeSet;


/**
 * A brute-force generator for a network of stars that creates possible trips between the stars that come back to the given starting star.
 *
 * @author Brandon Cox
 *         Created Feb 12, 2012.
 */
public class TripPlanner {

	private PriorityQueue<Trip> possibleTrips;
	private HashMap<String, PriorityQueue<Trip>> attractionMap;
	
	private Star startingPoint;
	private double maxCost;
	private double startTime;
	
	/**
	 * Creates and initializes a new trip planner.
	 *
	 */
	public TripPlanner(){
		this.possibleTrips = new PriorityQueue<Trip> ();
	}
	
	/**
	 * Returns all possible trips starting at the given star through the galaxy.
	 *
	 * @return A priorityqueue ordered by the most attractive to least attractive trip.
	 */
	public LinkedList<Trip> getPossibleTrips(){
		
		LinkedList<Trip> tripList = new LinkedList<Trip>();
		
		while(this.possibleTrips.size() > 0){
			tripList.add(this.possibleTrips.poll());
		}
		
		return tripList;
	}
	
	/**
	 * Returns a hashmap of all possible trips that is indexed by the different attractions on planets
	 *
	 * @return a hashmap of all possible trips
	 */
	public HashMap<String, LinkedList<Trip>> getAttractionMap(){
		
		HashMap<String, LinkedList<Trip>> mapList = new HashMap<String, LinkedList<Trip>>();
		
		Iterator<Entry<String, PriorityQueue<Trip>>> queueIterator = this.attractionMap.entrySet().iterator();
		
		while(queueIterator.hasNext()){
			Entry<String, PriorityQueue<Trip>> ent = queueIterator.next();
			
			LinkedList<Trip> tripList = new LinkedList<Trip>();
			PriorityQueue<Trip> tripPile = ent.getValue();
			
			while(tripPile.size() > 0){
				tripList.add(tripPile.poll());
			}
			
			mapList.put(ent.getKey(), tripList);
		}
		
		
		
		return mapList;
	}
	
	/**
	 * Compiles all the possible trips starting from the given star, given the maximum cost allowed.
	 *
	 * @param startingPoint
	 * @param maxCost
	 */
	public void compileTrips(Star startingPoint, double maxCost){
		
		this.attractionMap = new HashMap<String, PriorityQueue<Trip>>();
		this.startingPoint = startingPoint;
		this.maxCost = maxCost;
						
		LinkedList<Connection> tripSoFar = new LinkedList<Connection>();
		tripSoFar.add(new Connection(startingPoint, 0.));
		
		TreeSet<Star> starsPassed = new TreeSet<Star>();
		starsPassed.add(startingPoint);
		
		this.startTime = System.currentTimeMillis();
		
		this.getTrips(tripSoFar, 0., 0, new TreeSet<String>(), startingPoint, starsPassed);
		
		Iterator<Trip> tripIterator = this.possibleTrips.iterator();
		
		int num = 1;
		
		while(tripIterator.hasNext()){
			Trip curTrip = tripIterator.next();
						
			curTrip.setName("Trip " + num);
						
			num++;
			
		}	
	}

	
	
	/**
	 * Recursive, brute-force method of finding all possible trips.
	 *
	 * @param startingPoint
	 * @param maxCost
	 */
	@SuppressWarnings("unchecked")
	private void getTrips(LinkedList<Connection> tripSoFar, double costSoFar, int attractionSoFar, TreeSet<String> attractionsSoFar, Star curStar, TreeSet<Star> starsPassed) {		
		
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
			
			if(System.currentTimeMillis() > this.startTime + 5000){
				return;
			}
			
			if (costSoFar + connectionCost < this.maxCost){
				
				Star connectingTo = connector.getTarget();
				
				LinkedList<Connection> newConnectionList = (LinkedList<Connection>) tripSoFar.clone();
				newConnectionList.add(connector);
				
				this.getTrips(newConnectionList, costSoFar + connectionCost, attractionSoFar, (TreeSet<String>)(attractionsSoFar.clone()), connectingTo, (TreeSet<Star>)starsPassed.clone());
			}
			
		}
				
	}
	
}
