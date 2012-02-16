import java.util.Random;
import java.util.TreeSet;


public class GalaxyGenerator {
	private static final double STARS_PER_KILOPARSEC = 0.001;
	
	private Random rand;
	private Octree octree;
	public GalaxyGenerator(double size, int numStarTypes){
		super();
		rand = new Random();
		octree = new Octree(numStarTypes,size);
		
	}
	
	public GalaxyGenerator(int seed){
		rand = new Random(seed);
		
		octree = new Octree(4,1024);
	}
	
	/**
	 * Generates new stars for the given galaxy parameters, 
	 * new stars will not be in the same positions as the old ones.
	 */
	public void genStars(){
		
		AttractionGenerator.compileAttractions();
		
		for(int i = 0; i < octree.depth; i++){
			for(int j = 0; j < ((Math.pow(8,i))*STARS_PER_KILOPARSEC*octree.size);j++){
				Vector3D<Double> pos = new Vector3D<Double>(rand.nextDouble()*octree.size,rand.nextDouble()*octree.size,rand.nextDouble()*octree.size);
				Star e = new Star((char)(i+65)+""+pos.toString());
				e.setRange((rand.nextDouble()+7)*5/(i*8)+20);
				e.setPosition(pos);
				
				// Attraction Generator Stuff
				
				e.setAttractions(AttractionGenerator.getAttractions());
				e.setInterestLevel((int)((Math.random() * 10) + 1));
				
				// Not Attraction Generator Stuff
				
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
