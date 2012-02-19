
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * A node for an Octree
 * @author guilfoej
 *
 */
public class octNode{

	private Octree octNode;
	/**
	 * The location of the upper front left hand corner.
	 */
	final Vector3D loc;
	/**
	 * The location of the lower back right hand corner.
	 */
	final Vector3D bnd;
	/**
	 *  The level of the node
	 */
	final int level;
	
	private octNode[] children = new octNode[8];
	private LinkedList<Star> list = new LinkedList<Star>();
	
	/**
	 * Creates an octree Node
	 * @param octree the containing octree
	 * @param level what level this node is
	 * @param x inital x
	 * @param y inital y
	 * @param z inital z
	 */
	public octNode(Octree octree, int level,double x, double y, double z){
		octNode = octree;
		double mySize = octNode.size/Math.pow(2, level);

		loc = new Vector3D(x,y,z);
		bnd = new Vector3D(x+mySize,y+mySize,z+mySize);
		this.level = level;
		if(this.level <= octNode.depth){
			//create subChildren at proper locations
			children[0] = new octNode(octNode, level+1,loc.x,loc.y,loc.z);
			children[1] = new octNode(octNode, level+1,loc.x+((bnd.x-loc.x)/2),loc.y,loc.z);
			children[2] = new octNode(octNode, level+1,loc.x,loc.y,loc.z+((bnd.z-loc.z)/2));
			children[3] = new octNode(octNode, level+1,loc.x+((bnd.x-loc.x)/2),loc.y,loc.z+((bnd.z-loc.z)/2));
			children[4] = new octNode(octNode, level+1,loc.x,loc.y+((bnd.y-loc.y)/2),loc.z);
			children[5] = new octNode(octNode, level+1,loc.x+((bnd.x-loc.x)/2),loc.y+((bnd.y-loc.y)/2),loc.z);
			children[6] = new octNode(octNode, level+1,loc.x,loc.y+((bnd.y-loc.y)/2),loc.z+((bnd.z-loc.z)/2));
			children[7] = new octNode(octNode, level+1,loc.x+((bnd.x-loc.x)/2),loc.y+((bnd.y-loc.y)/2),loc.z+((bnd.z-loc.z)/2));
		} else {
			children = null;
		}
	}
	

	/**
	 * Add a star to the node
	 * @param e the star to add
	 * @return true if successful false otherwise.
	 */
	public boolean add(Star e) {
		return list.add(e);
	}
	
	/**
	 * insert star into this subtree.
	 * @param e the star to insert
	 * @param level the level to insert to.
	 * @return true if successful, false otherwise.
	 */
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

	/**
	 * returns the subtree as a treeSet of Stars.
	 * @param tree the tree to insert to.
	 */
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
	
	/**
	 * returns the subtree as a treeSet of octNodes
	 * @param tree the tree to insert to.
	 */
	public void toTree2(ArrayList<octNode> tree) {
		if(children!= null){
			for(int i = 0; i< 8; i++){
				tree.add(children[i]);
				children[i].toTree2(tree);
			}
		}
	}
	
}