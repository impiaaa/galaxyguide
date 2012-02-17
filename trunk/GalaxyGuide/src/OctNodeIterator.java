import java.util.Iterator;
import java.util.Stack;


public class OctNodeIterator implements Iterator<octNode> {
	Octree tree;
	
	
	public OctNodeIterator(Octree tree){
		this.tree = tree;
	}
	@Override
	public boolean hasNext() {
		return false;
	}
	@Override
	public octNode next() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void remove() {
		//octrees do not allow removal of nodes.
		return;
		
	}
}
