
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;


public class Octree implements Iterable<octNode> {
	
	public double size; //size of the galaxy/tree, in kiloparsecs
	public final int    depth;
	public octNode root = null;

	
	/**
	 * Creates an octree according to the given parameters
	 * @param depth the number of levels the tree has
	 * @param size the size of one side of the cube,
	 *        in arbitrary units.
	 */
	public Octree(int depth, double size){
		this.depth = depth;
		this.size = size;
		root = new octNode(this, 0,0.0,0.0,0.0);
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
	
	/**
	 * @return the contents of the octree as a treeSet, with the stars sorted by name.
	 */
	public TreeSet<Star> toTreeSet() {
		TreeSet<Star> tree = new TreeSet<Star>();
		root.toTree(tree);
		return tree;
	}
	
	/**
	 * Returns a list of octree nodes, which can be iterated through.
	 * @return
	 */
	public ArrayList<octNode> toList() {
		ArrayList<octNode> tree = new ArrayList<octNode>();
		tree.add(root);
		root.toTree2(tree);
		return tree;
	}

	@Override
	public Iterator<octNode> iterator() {
		return this.toList().iterator();
	}
	
}
