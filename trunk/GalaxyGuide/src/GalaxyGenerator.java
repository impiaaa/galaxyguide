import java.util.Random;
import java.util.TreeSet;


public class GalaxyGenerator {
	private static final double STARS_PER_KILOPARSEC = 1;
	
	private Random rand;
	private Octree octree;
	public GalaxyGenerator(double size, int numStarTypes){
		super();
		rand = new Random();
		octree = new Octree(numStarTypes,size);
		
	}
	
	public GalaxyGenerator(int seed){
		System.out.println("booting");
		rand = new Random(seed);
		
		octree = new Octree(3,1024);
	}
	
	/**
	 * Generates new stars for the given galaxy parameters, 
	 * new stars will not be in the same positions as the old ones.
	 */
	public void genStars(){
		// Attraction Generator Stuff
		AttractionGenerator.compileAttractions();
		// Not Attraction Generator Stuff
		for(octNode j: octree){
			double nodeSize = octree.size/(Math.pow(2 , (double)j.level));
			System.out.println(nodeSize);
			for(int i = 0; i < 3; i++){
				Vector3D pos = new Vector3D((rand.nextDouble()*nodeSize)+j.loc.x,(rand.nextDouble()*nodeSize)+j.loc.y,(rand.nextDouble()*nodeSize)+j.loc.z);
				Star e = new Star((char)(j.level+65)+""+(char)(rand.nextInt(26)+97)+""+(char)(rand.nextInt(26)+97)+""+(char)(rand.nextInt(26)+97)+""+(char)(rand.nextInt(26)+97)+""+(char)(rand.nextInt(26)+97));
				e.setRange(1.5*nodeSize+(rand.nextDouble()-0.5)*(nodeSize/8));
				e.setPosition(pos);
				
				// Attraction Generator Stuff
				e.setAttractions(AttractionGenerator.getAttractions());
				e.setInterestLevel((int)(rand.nextDouble() * 10) + 1);
				// Not Attraction Generator Stuff
				
				octree.insert(e, j.level);
			}
		}
	}
	
	
	public TreeSet<Star> getBinaryTree(){
		TreeSet<Star> tree = octree.toTreeSet();
		System.out.println(tree);
		return tree;
	}
}
