import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.LinkedList;

public class AStarSearch {
	Star start;
	Star goal;
	State start_state;
	// the set of star already been evaluated
	HashSet<Star> closedset;
	// the set of tentative star to be evaluated, initially contain
	PriorityQueue<State> openset;
	// the path of navigated star;
	LinkedList<Star> path;

	double cost;

	/**
	 * TODO Put here a description of what this class does.
	 * 
	 * @author niz. Created Feb 13, 2012.
	 */
	public class State implements Comparable<State> {

		/**
		 * problem specific variables
		 */
		Star star;
		LinkedList<Star> path;
		double cost;

		/**
		 * Constructor
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
		 * Return the heurisitc value
		 * 
		 * @return
		 */
		double h_score() {
			return Math.sqrt(Math.pow(
					(this.star.getPosition().x - AStarSearch.this.goal
							.getPosition().x), 2)
					+ Math.pow(
							(this.star.getPosition().y - AStarSearch.this.goal
									.getPosition().y), 2)
					+ Math.pow(
							(this.star.getPosition().z - AStarSearch.this.goal
									.getPosition().z), 2));

		}

		/**
		 * returns the total cost to reach goal. This is cost thus far plus the
		 * heurisitc estimate
		 * 
		 * @return
		 */
		double f_score() {
			return g_score() + h_score();
		}

		/**
		 * Add the next states the priority queue (os).
		 * 
		 * @param os
		 * 
		 * @param Q
		 */
		void next(PriorityQueue<State> os) {
			// Add next hops from this state to queue Q
			//

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
		public String pathToString(){
			StringBuilder sb= new StringBuilder();
			Iterator<Star> pi = this.path.iterator();
			sb.append('[');
			sb.append("->");
			while (pi.hasNext()){
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
	public AStarSearch(Star st, Star g) {
		this.start = st;
		this.goal = g;
		this.openset = new PriorityQueue<State>();
		this.closedset = new HashSet<Star>();
		this.path = new LinkedList<Star>();
		this.cost = 0;
		this.start_state = new State(st, this.path, this.cost);
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