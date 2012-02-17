import java.util.ArrayList;



/**
 * A Star Test
 * 
 * @author Zhihao Ni
 *         Created Feb 13, 2012
 */
public class AStarTest {

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Star> Starlist = new ArrayList<Star>();
		Star A = new Star("A");
		Star B = new Star("B");
		Star C = new Star("C");
		Star D = new Star("D");
		Star E = new Star("E");
		
		A.setPosition(new Vector3D(3.0,4.0,5.0));
		B.setPosition(new Vector3D(14.0,5.0,6.0));
		C.setPosition(new Vector3D(1.0,3.0,17.0));
		D.setPosition(new Vector3D(11.0,24.0,36.0));
		E.setPosition(new Vector3D(15.0,4.0,5.0));
		
		A.addConnection(new Connection(B,cost(A,B)));
		A.addConnection(new Connection(C,cost(A,C)));
		B.addConnection(new Connection(A,cost(B,A)));
		B.addConnection(new Connection(E,cost(B,E)));
		B.addConnection(new Connection(C,cost(B,C)));
		C.addConnection(new Connection(A,cost(C,A)));
		C.addConnection(new Connection(B,cost(C,B)));
		C.addConnection(new Connection(D,cost(C,D)));
		D.addConnection(new Connection(C,cost(C,D)));
		D.addConnection(new Connection(E,cost(D,E)));
		E.addConnection(new Connection(B,cost(E,B)));
		E.addConnection(new Connection(D,cost(E,D)));
		
		System.out.println(cost(A,B)+"\n");
		System.out.println(cost(A,C)+"\n");
		System.out.println(cost(B,E)+"\n");
		System.out.println(cost(B,C)+"\n");
		System.out.println(cost(C,D)+"\n");
		System.out.println(cost(D,E)+"\n");
		
		
		
		Starlist.add(A);
		Starlist.add(B);
		Starlist.add(C);
		Starlist.add(D);
		Starlist.add(E);
		
		AStarSearch a = new AStarSearch(B,D,new AStarSearch.DistanceHeuristic());
		AStarSearch.State test = a.search();
		
		DijkstrasSearch d = new DijkstrasSearch(B,D);
		DijkstrasSearch.State test1 = d.search();
		
		if (test == null)
			System.out.println("No path");
		else
			System.out.println("path = " + test.pathToString() + " cost = "
					+ test.g_score());
		
		if (test1 == null)
			System.out.println("No path");
		else
			System.out.println("path = " + test1.pathToString() + " cost = "
					+ test1.g_score());


	}

	/**
	 * Returns the distance from one star to the other.
	 *
	 * @param a
	 * @param b
	 * @return double
	 */
	static double cost(Star a, Star b) {
		return Math.sqrt(Math.pow((a.getPosition().x - b.getPosition().x), 2)
				+ Math.pow((a.getPosition().y - b.getPosition().y), 2)
				+ Math.pow((a.getPosition().z - b.getPosition().z), 2));

	}

}
