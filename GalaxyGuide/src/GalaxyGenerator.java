import java.util.Random;
import java.util.TreeSet;


/**
 * Creates a randomly generated galaxy
 *
 * @author Eric Guillford
 *         Created Feb 16, 2012.
 */
public class GalaxyGenerator {
	private static final double STARS_PER_KILOPARSEC = 0.001;
	
	private Random rand;
	private Octree octree;
	
	/**
	 * Creates a galaxy given a size and number of star types.
	 *
	 * @param size
	 * @param numStarTypes
	 */
	public GalaxyGenerator(double size, int numStarTypes){
		super();
		this.rand = new Random();
		this.octree = new Octree(numStarTypes,size);
		
	}
	
	/**
	 * Creates a galaxy given a random seed.
	 *
	 * @param seed
	 */
	public GalaxyGenerator(int seed){
		this.rand = new Random(seed);
		
		this.octree = new Octree(4,1024);
	}
	
	/**
	 * Generates new stars for the given galaxy parameters, 
	 * new stars will not be in the same positions as the old ones.
	 */
	public void genStars(){
		// Attraction Generator Stuff
		AttractionGenerator.compileAttractions();
		// Not Attraction Generator Stuff
		
		for(int i = 0; i < this.octree.depth; i++){
			for(int j = 0; j < ((Math.pow(8,i))*STARS_PER_KILOPARSEC*this.octree.size);j++){
				Vector3D pos = new Vector3D(this.rand.nextDouble()*this.octree.size,this.rand.nextDouble()*this.octree.size,this.rand.nextDouble()*this.octree.size);
				Star e = new Star((char)(i+65)+""+pos.toString());
				e.setRange(50*(5-i)+this.rand.nextDouble()*3);
				e.setPosition(pos);
				
				// Attraction Generator Stuff
				e.setAttractions(AttractionGenerator.getAttractions());
				e.setInterestLevel((int)(this.rand.nextDouble() * 10) + 1);
				// Not Attraction Generator Stuff
				
				this.octree.insert(e, i);
			}
		}
	}
	
	/**
	 * Returns this galaxy's stars in a tree set.
	 *
	 * @return A TreeSet of all the stars in the galaxy
	 */
	public TreeSet<Star> getBinaryTree(){
		TreeSet<Star> tree = this.octree.toTreeSet();
		System.out.println(tree);
		return tree;
	}
}
