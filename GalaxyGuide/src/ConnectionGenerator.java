import java.util.PriorityQueue;
import java.util.TreeSet;


/**
 * Generates connections given a set of stars
 *
 * @author Eric Guillford
 *         Created Feb 16, 2012.
 */
public class ConnectionGenerator {
	
	/**
	 * Creates a new connection generator
	 *
	 */
	public ConnectionGenerator(){
		
	}
	
	public void generateConnectionTime(TreeSet<Star> tree){
		for(Star i : tree){
			for(Star j : tree) {
				if(j.equals(i)) continue;
				if(j.getName().charAt(0) > i.getName().charAt(0)+1) continue;
				double distance = Math.sqrt((j.getPosition().x-i.getPosition().x)*(j.getPosition().x-i.getPosition().x)
						                   +(j.getPosition().y-i.getPosition().y)*(j.getPosition().y-i.getPosition().y)
						                   +(j.getPosition().z-i.getPosition().z)*(j.getPosition().z-i.getPosition().z));
				if(distance <= j.getRange()) {
					double cost = distance / Math.abs(i.getRange() - j.getRange());
					Connection cont = new Connection(j,cost);
					i.addConnection(cont);
				}
			}
		}
	}
	
	
/*	public void generateConnectionSpeed(TreeSet<Star> tree){
		for(Star i : tree){
			for(Star j : tree) {
				if(j.equals(i)) continue;
				if(j.getName().charAt(0) > i.getName().charAt(0)+1) continue;
				double distance = Math.sqrt((j.getPosition().x-i.getPosition().x)*(j.getPosition().x-i.getPosition().x)
						                   +(j.getPosition().y-i.getPosition().y)*(j.getPosition().y-i.getPosition().y)
						                   +(j.getPosition().z-i.getPosition().z)*(j.getPosition().z-i.getPosition().z));
				if(distance <= j.getRange()) {
					double cost = distance*20;
					Connection cont = new Connection(j,cost);
					i.addConnection(cont);
				}
			}
		}
	}
*/	
	/**
	 * Generates connections given a set of stars.
	 *
	 * @param tree
	 */
	public void generateConnectionRange(TreeSet<Star> tree){
		for(Star i:tree){
			/*
			int desiredConnections = (int) ((Math.random() * 4) + 2);
			int curConnections = i.getConnections().size();
			int connectionsToFind = desiredConnections - curConnections;
			if(connectionsToFind > 0){
				PriorityQueue<Connection> starsToConnectTo = new PriorityQueue<Connection>();
				
				for(Star j:tree){
					
					// Find all the connections to other nodes in the tree
					
					if(j.equals(i)) continue;
					double distance = Math.sqrt((j.getPosition().x-i.getPosition().x)*(j.getPosition().x-i.getPosition().x)
							                   +(j.getPosition().y-i.getPosition().y)*(j.getPosition().y-i.getPosition().y)
							                   +(j.getPosition().z-i.getPosition().z)*(j.getPosition().z-i.getPosition().z));
					
					
					double time = distance;
					Connection cont = new Connection(j,time);
					
					// The priority queue will sort out the ones with the lowest distance
					
					starsToConnectTo.add(cont);
					
				}
				
				// We now need to add the connections, mutually.
				
				for(int n = 0; n < connectionsToFind; n++){
					Connection conn = starsToConnectTo.poll();
					
					i.addConnection(conn);
					
					conn.getTarget().addConnection(new Connection(i, conn.getCost()));
					
				}
				
			}
			*/
			
			for(Star j:tree){
				if(j.equals(i)) continue;
				if(j.getName().charAt(0) > i.getName().charAt(0)+1) continue;
				double distance = Math.sqrt((j.getPosition().x-i.getPosition().x)*(j.getPosition().x-i.getPosition().x)
						                   +(j.getPosition().y-i.getPosition().y)*(j.getPosition().y-i.getPosition().y)
						                   +(j.getPosition().z-i.getPosition().z)*(j.getPosition().z-i.getPosition().z));
				if(distance <= j.getRange()) {
					double cost = distance;
					Connection cont = new Connection(j,cost);
					i.addConnection(cont);
				}
			}
			
		}
	}

}
