import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * This class contains DijkstrasSearch
 * 
 * @author Zhihao Ni 
 * Created Feb 13, 2012.
 */
public class DijkstrasSearch {
	private Star goal;
	private State start_state;
	private HashSet<Star> closedset;
	private PriorityQueue<State> openset;

	/**
	 * The state class for search
	 * 
	 * @author niz. Created Feb 13, 2012.
	 */
	public class State implements Comparable<State> {

		/**
		 * problem specific variables
		 */
		private Star star;
		private LinkedList<Star> path;
		private double cost;

		/**
		 * Constructor
		 * 
		 * @param curr
		 * @param prev_path
		 * @param new_cost
		 */
		public State(Star curr, LinkedList<Star> prev_path, double new_cost) {
			this.star = curr;
			this.path = new LinkedList<Star>();
			this.path.addAll(prev_path);
			this.path.add(curr);
			this.cost = new_cost;
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
		 * Add the next states the Dsomthing priority queue (os).
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
							new_cost);
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
			return (this.star == DijkstrasSearch.this.goal);
		}

		/**
		 * sorts by estimated cost (lowest cost first)
		 */
		@Override
		public int compareTo(State O) {
			double z = g_score();
			double oz = O.g_score();

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
	}

	/**
	 * Constructor
	 * 
	 * @param st
	 * @param g
	 */
	public DijkstrasSearch(Star st, Star g) {
		this.goal = g;
		this.openset = new PriorityQueue<State>();
		this.closedset = new HashSet<Star>();
		this.start_state = new State(st, new LinkedList<Star>(), 0);
	}

	/**
	 * The Dsonething search
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
			State s = this.openset.poll();
			if (s.isGoal())
				return s;
			if (!this.closedset.contains(s.star)) {
				this.closedset.add(s.star);
				s.next(this.openset);
			}
		}
		return null;
	}
}