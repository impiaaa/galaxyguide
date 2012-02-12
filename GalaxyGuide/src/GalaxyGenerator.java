import java.util.Random;


public class GalaxyGenerator {
	private static final double STARS_PER_KILOPARSEC = 1;
	
	Random rand;
	Octree octree;
	public GalaxyGenerator(double size, int numStarTypes){
		super();
		rand = new Random();
		octree = new Octree(numStarTypes,size);
		
	}
	
	public GalaxyGenerator(int seed){
		rand = new Random(seed);
		
		octree = new Octree(rand.nextInt(20),rand.nextFloat()*50);
	}
	
	/**
	 * Generates new stars for the given galaxy parameters, 
	 * new stars will not be in the same positions as the old ones.
	 */
	public void genStars(){
		for(int i = 0; i < octree.depth; i++){
			for(int j = 0; j < ((8^i)*STARS_PER_KILOPARSEC*octree.size);j++){
				Vector3D<Double> pos = new Vector3D<Double>(rand.nextDouble()*octree.size,rand.nextDouble()*octree.size,rand.nextDouble()*octree.size);
				Star e = new Star((char)(i+65)+""+pos.toString());
				e.setRange(rand.nextDouble()*20+i*8);
				e.setPosition(pos);
				octree.insert(e, i);
			}
		}
	}
}
