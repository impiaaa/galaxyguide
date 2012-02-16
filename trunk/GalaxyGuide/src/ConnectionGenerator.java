import java.util.TreeSet;


public class ConnectionGenerator {
	
	public ConnectionGenerator(){
		
	}
	
	public void generateConnectionRange(TreeSet<Star> tree){
		for(Star i:tree){
			for(Star j:tree){
				if(j.getName().charAt(0) < i.getName().charAt(0)-1) continue;
				double distance = Math.sqrt((j.getPosition().x-i.getPosition().x)*(j.getPosition().x-i.getPosition().x)
						                   +(j.getPosition().y-i.getPosition().y)*(j.getPosition().y-i.getPosition().y)
						                   +(j.getPosition().z-i.getPosition().z)*(j.getPosition().z-i.getPosition().z));
				if(distance <= j.getRange()) {
					double time = distance;
					Connection cont = new Connection(j,time);
					i.addConnection(cont);
				}
			}
		}
	}

}
