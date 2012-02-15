import java.util.Random;
import java.util.TreeSet;


public class GalaxyGenerator {
	private static final double STARS_PER_KILOPARSEC = 0.25;
	
	private Random rand;
	private Octree octree;
	public GalaxyGenerator(double size, int numStarTypes){
		super();
		rand = new Random();
		octree = new Octree(numStarTypes,size);
		
	}
	
	public GalaxyGenerator(int seed){
		rand = new Random(seed);
		
		octree = new Octree(4,rand.nextFloat()*50);
	}
	
	/**
	 * Generates new stars for the given galaxy parameters, 
	 * new stars will not be in the same positions as the old ones.
	 */
	public void genStars(){
		for(int i = 0; i < octree.depth; i++){
			for(int j = 0; j < ((Math.pow(8,i))*STARS_PER_KILOPARSEC*octree.size);j++){
				Vector3D<Double> pos = new Vector3D<Double>(rand.nextDouble()*octree.size,rand.nextDouble()*octree.size,rand.nextDouble()*octree.size);
				Star e = new Star((char)(i+65)+""+pos.toString());
				e.setRange(rand.nextDouble()*20+i*8);
				e.setPosition(pos);
				octree.insert(e, i);
			}
		}
		System.out.println(octree.depth);
	}
	
	public TreeSet<Star> getBinaryTree(){
		TreeSet<Star> tree = octree.toTreeSet();
		System.out.println(tree);
		return tree;
	}
}
