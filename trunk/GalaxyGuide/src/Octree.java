import java.util.LinkedList;
import java.util.TreeSet;


/**
 * An Octree for random generation and connection.
 *
 * @author Eric Guillford
 *         Created Feb 16, 2012.
 */
public class Octree {
	
	/**
	 * Size of the galaxy or tree, in kiloparsecs.
	 */
	public double size;
	/**
	 * The depth of the galaxy or tree.
	 */
	public final int depth;
	private octNode root = null;
	

	/**
	 * creates an Octree with the given depth, 
	 * and with the default size of 10 in each dimension
	 * @param depth the number of levels the tree has
	 */
	public Octree(int depth){
		this.depth = depth;
		this.root = new octNode(0,0.0,0.0,0.0);
		this.size = 10;
	}
	
	/**
	 * Creates an octree according to the given parameters
	 * @param depth the number of levels the tree has
	 * @param size the size of one side of the cube,
	 *        in arbitrary units.
	 */
	public Octree(int depth, double size){
		this(depth);
		this.size = size;
	}
	
	
	/**
	 * Inserts a star into the octree.
	 * @param e the star to be inserted
	 * @param level how common the star is, rare stars get a lower level. In general, no levels should be skipped.
	 * @return returns true if insertion was successful, false otherwise. 
	 */
	public boolean insert(Star e, int level){
		if(level > this.depth) return false;
		if(e.getPosition().x > this.size) return false;
		if(e.getPosition().y > this.size) return false;
		if(e.getPosition().z > this.size) return false;
		
		if(level == 0) {
			return this.root.add(e);
		} else {
			return this.root.insert(e,level);
		}
	}
	
	private class octNode{
		final Vector3D loc;
		final Vector3D bnd;
		final int level;
		octNode[] children = new octNode[8];
		LinkedList<Star> list = new LinkedList<Star>();
		
		public octNode(int level,double x, double y, double z){
			double mySize = Octree.this.size/(2^level);
			this.loc = new Vector3D(x,y,z);
			this.bnd = new Vector3D(x+mySize,y+mySize,z+mySize);
			this.level = level;
			if(this.level <= Octree.this.depth){
				//create subChildren at proper locations
				this.children[0] = new octNode(level+1,this.loc.x,this.loc.y,this.loc.z);
				this.children[1] = new octNode(level+1,this.loc.x+((this.bnd.x-this.loc.x)/2),this.loc.y,this.loc.z);
				this.children[2] = new octNode(level+1,this.loc.x,this.loc.y,this.loc.z+((this.bnd.z-this.loc.z)/2));
				this.children[3] = new octNode(level+1,this.loc.x+((this.bnd.x-this.loc.x)/2),this.loc.y,this.loc.z+((this.bnd.z-this.loc.z)/2));
				this.children[4] = new octNode(level+1,this.loc.x,this.loc.y+((this.bnd.y-this.loc.y)/2),this.loc.z);
				this.children[5] = new octNode(level+1,this.loc.x+((this.bnd.x-this.loc.x)/2),this.loc.y+((this.bnd.y-this.loc.y)/2),this.loc.z);
				this.children[6] = new octNode(level+1,this.loc.x,this.loc.y+((this.bnd.y-this.loc.y)/2),this.loc.z+((this.bnd.z-this.loc.z)/2));
				this.children[7] = new octNode(level+1,this.loc.x+((this.bnd.x-this.loc.x)/2),this.loc.y+((this.bnd.y-this.loc.y)/2),this.loc.z+((this.bnd.z-this.loc.z)/2));
			} else {
				this.children = null;
			}
		}
		

		public boolean add(Star e) {
			this.list.add(e);
			return true;
		}

		public boolean insert(Star e, int level) {
			int node = 0;
			if(e.getPosition().y > this.loc.y+((this.bnd.y-this.loc.y)/2)) node+=4;
			if(e.getPosition().z > this.loc.z+((this.bnd.z-this.loc.z)/2)) node+=2;
			if(e.getPosition().x > this.loc.x+((this.bnd.x-this.loc.x)/2)) node+=1;
			if(level == this.level+1){
				return this.children[node].add(e);
			} else {
				return this.children[node].insert(e, level);
			}
		}


		public void toTree(TreeSet<Star> tree) {
			for(Star i : this.list){
				tree.add(i);
			}
			if(this.children!= null){
				for(int i = 0; i< 8; i++){
					this.children[i].toTree(tree);
				}
			}
		}
		
	}

	/**
	 * Returns a TreeSet of the stars in this octree.
	 *
	 * @return a TreeSet of the stars in this octree.
	 */
	public TreeSet<Star> toTreeSet() {
		TreeSet<Star> tree = new TreeSet<Star>();
		this.root.toTree(tree);
		return tree;
	}
	
}
