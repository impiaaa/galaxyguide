import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;


public class octNode{
	/**
	 * 
	 */
	private Octree octNode;
	final Vector3D loc;
	final Vector3D bnd;
	final int level;
	octNode[] children = new octNode[8];
	LinkedList<Star> list = new LinkedList<Star>();
	
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
	
	public void toTree2(ArrayList<octNode> tree) {
		if(children!= null){
			for(int i = 0; i< 8; i++){
				tree.add(children[i]);
				children[i].toTree2(tree);
			}
		}
	}
	
}