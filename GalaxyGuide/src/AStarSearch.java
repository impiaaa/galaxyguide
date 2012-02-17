import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * This class contains A Star Search
 * 
 * @author Zhihao Ni
 *         Created Feb 13, 2012.
 */
public class AStarSearch {
	private Star goal;
	private State start_state;
	private HashSet<Star> closedset;
	private PriorityQueue<State> openset;
	private PriorityQueue<State> allroute;
	private LinkedList<State> allroute1;
	/**
	 * get all route
	 *
	 * @return linkedlist
	 */
	public LinkedList<State> getroute(){
		return this.allroute1;
	}

	/**
	 * The Heuristic for distance
	 * 
	 * @author Zhihao Ni
	 *         Created Feb 13, 2012.
	 */
	public static class DistanceHeuristic implements Heuristic {
		@Override
		public double hfunction(Star start, Star goal) {
			return Math.sqrt(Math.pow(
					(start.getPosition().x - goal.getPosition().x), 2)
					+ Math.pow((start.getPosition().y - goal.getPosition().y),
							2)
					+ Math.pow((start.getPosition().z - goal.getPosition().z),
							2));
		}
	}

	
	/**
	 * The heuristic for the i cant understand cost function
	 *
	 * @author niz.
	 *         Created Feb 17, 2012.
	 */
	public static class CHeuristic implements Heuristic {
		@Override
		public double hfunction(Star start, Star goal) {
			return Math.sqrt(Math.pow(
					(start.getPosition().x - goal.getPosition().x), 2)
					+ Math.pow((start.getPosition().y - goal.getPosition().y),
							2)
					+ Math.pow((start.getPosition().z - goal.getPosition().z),
							2))/Math.abs(start.getRange()-goal.getRange());
		}
	}
	
	
	/**
	 * The state class for search
	 * 
	 * @author Zhihao Ni
	 *         Created Feb 13, 2012.
	 */
	public class State implements Comparable<State> {

		/**
		 * problem specific variables
		 */
		private Star star;
		private LinkedList<Star> path;
		private double cost;
		private Heuristic heuristic;

		/**
		 * Constructor
		 * 
		 * @param curr
		 * @param prev_path
		 * @param new_cost
		 * @param h 
		 */
		public State(Star curr, LinkedList<Star> prev_path, double new_cost, Heuristic h) {
			this.star = curr;
			this.path = new LinkedList<Star>();
			this.path.addAll(prev_path);
			this.path.add(curr);
			this.cost = new_cost;
			this.heuristic = h;
			// Add things as needed
		}

		/**
		 * Return the cost thus far to reach this state
		 * 
		 * @return
		 */
		double g_score() {
			return this.cost;
		}

		/**
		 * returns the total cost to reach goal. This is cost thus far plus the
		 * heurisitc estimate
		 * 
		 * @return
		 */
		double f_score() {
			return g_score()
					+ this.heuristic
							.hfunction(this.star, AStarSearch.this.goal);
		}

		/**
		 * Add the next states the A* priority queue (os).
		 * 
		 * @param os
		 * 
		 * @param Q
		 */
		void next(PriorityQueue<State> os) {
			LinkedList<Connection> sat = this.star.getConnections();
			for (Connection c : sat) {
				if (c.getCost() != 0.0) {
					double new_cost = this.cost + c.getCost();
					State new_state = new State(c.getTarget(), this.path,
							new_cost,this.heuristic);
					os.add(new_state);
				}

			}
		}

		/**
		 * returns true iff this is the goal state
		 * 
		 * @return
		 */
		boolean isGoal() {
			return (this.star == AStarSearch.this.goal);
		}

		/**
		 * sorts by estimated cost (lowest cost first)
		 */
		@Override
		public int compareTo(State O) {
			double z = f_score();
			double oz = O.f_score();

			if (z < oz)
				return -1;
			if (z > oz)
				return 1;

			return 0;

		}

		@Override
		public String toString() {
			return "" + this.star;

		}

		/**
		 * sysout the path
		 * 
		 * @return string
		 */
		public String pathToString() {
			StringBuilder sb = new StringBuilder();
			Iterator<Star> pi = this.path.iterator();
			sb.append('[');
			sb.append("->");
			while (pi.hasNext()) {
				sb.append(pi.next().getName());
				sb.append("->");
			}
			sb.append(']');
			return sb.toString();
		}

		/**
		 * Returns the value of the field called 'path'.
		 * 
		 * @return Returns the path.
		 */
		public LinkedList<Star> getPath() {
			return this.path;
		}

		/**
		 * Sets the field called 'path' to the given value.
		 * 
		 * @param path
		 *            The path to set.
		 */
		public void setPath(LinkedList<Star> path) {
			this.path = path;
		}

		/**
		 * Returns the value of the field called 'heuristic'.
		 * 
		 * @return Returns the heuristic.
		 */
		public Heuristic getHeuristic() {
			return this.heuristic;
		}

		/**
		 * Sets the field called 'heuristic' to the given value.
		 * 
		 * @param heuristic
		 *            The heuristic to set.
		 */
		public void setHeuristic(Heuristic heuristic) {
			this.heuristic = heuristic;
		}
	}

	/**
	 * Constructor
	 * 
	 * @param st
	 * @param g
	 * @param h 
	 */
	public AStarSearch(Star st, Star g, Heuristic h) {
		this.goal = g;
		this.openset = new PriorityQueue<State>();
		this.closedset = new HashSet<Star>();
		this.start_state = new State(st, new LinkedList<Star>(), 0, h);
	}

	/**
	 * The Astar search
	 * 
	 * @param st
	 * @param g
	 * @return state
	 */
	public State search() {
		this.openset = new PriorityQueue<State>();
		this.openset.add(this.start_state);
		this.closedset = new HashSet<Star>();
		while (this.openset.peek() != null) {
			this.allroute = this.openset;
			State s = this.openset.poll();
			if (s.isGoal())
				return s;
			if (!this.closedset.contains(s.star)) {
				this.closedset.add(s.star);
				s.next(this.openset);
			}
		}
		
		while (this.allroute.peek() != null) {
			State s = this.allroute.poll();
			if (s.isGoal())
				this.allroute1.add(s);
			if (!this.closedset.contains(s.star)) {
				this.closedset.add(s.star);
				s.next(this.openset);
			}
		}
		return null;
	}
}