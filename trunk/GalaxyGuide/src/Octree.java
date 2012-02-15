import java.util.LinkedList;
import java.util.TreeSet;


public class Octree {
	
	public double size; //size of the galaxy/tree, in kiloparsecs
	public final int    depth;
	private octNode root = null;
	

	/**
	 * creates an Octree with the given depth, 
	 * and with the default size of 10 in each dimension
	 * @param depth the number of levels the tree has
	 */
	public Octree(int depth){
		this.depth = depth;
		root = new octNode(0,0.0,0.0,0.0);
		size = 10;
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
		if(e.getPosition().x > size) return false;
		if(e.getPosition().y > size) return false;
		if(e.getPosition().z > size) return false;
		
		if(level == 0) {
			return root.add(e);
		} else {
			return root.insert(e,level);
		}
	}
	
	private class octNode{
		final Vector3D<Double> loc;
		final Vector3D<Double> bnd;
		final int level;
		octNode[] children = new octNode[8];
		LinkedList<Star> list = new LinkedList<Star>();
		
		public octNode(int level,double x, double y, double z){
			double mySize = size/(2^level);
			loc = new Vector3D<Double>(x,y,z);
			bnd = new Vector3D<Double>(x+mySize,y+mySize,z+mySize);
			this.level = level;
			if(this.level <= Octree.this.depth){
				//create subChildren at proper locations
				children[0] = new octNode(level+1,loc.x,loc.y,loc.z);
				children[1] = new octNode(level+1,loc.x+((bnd.x-loc.x)/2),loc.y,loc.z);
				children[2] = new octNode(level+1,loc.x,loc.y,loc.z+((bnd.z-loc.z)/2));
				children[3] = new octNode(level+1,loc.x+((bnd.x-loc.x)/2),loc.y,loc.z+((bnd.z-loc.z)/2));
				children[4] = new octNode(level+1,loc.x,loc.y+((bnd.y-loc.y)/2),loc.z);
				children[5] = new octNode(level+1,loc.x+((bnd.x-loc.x)/2),loc.y+((bnd.y-loc.y)/2),loc.z);
				children[6] = new octNode(level+1,loc.x,loc.y+((bnd.y-loc.y)/2),loc.z+((bnd.z-loc.z)/2));
				children[7] = new octNode(level+1,loc.x+((bnd.x-loc.x)/2),loc.y+((bnd.y-loc.y)/2),loc.z+((bnd.z-loc.z)/2));
			} else {
				children = null;
			}
		}
		

		public boolean add(Star e) {
			list.add(e);
			return true;
		}

		public boolean insert(Star e, int level) {
			int node = 0;
			if(e.getPosition().y > loc.y+((bnd.y-loc.y)/2)) node+=4;
			if(e.getPosition().z > loc.z+((bnd.z-loc.z)/2)) node+=2;
			if(e.getPosition().x > loc.x+((bnd.x-loc.x)/2)) node+=1;
			if(level == this.level+1){
				return children[node].add(e);
			} else {
				return children[node].insert(e, level);
			}
		}


		public void toTree(TreeSet<Star> tree) {
			for(Star i : list){
				tree.add(i);
			}
			if(children!= null){
				for(int i = 0; i< 8; i++){
					children[i].toTree(tree);
				}
			}
		}
		
	}

	public TreeSet<Star> toTreeSet() {
		TreeSet<Star> tree = new TreeSet<Star>();
		root.toTree(tree);
		return tree;
	}
	
}
