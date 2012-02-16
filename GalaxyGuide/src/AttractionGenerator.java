import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * TODO Put here a description of what this class does.
 *
 * @author localmgr.
 *         Created Feb 15, 2012.
 */
public class AttractionGenerator {
	
	private static ArrayList<String> attractionList;
	
	public static void compileAttractions(){
		
		
		File coolStuffList = new File("coolstuflist.txt");
		coolStuffList.setReadable(true);
		
		AttractionGenerator.attractionList = new ArrayList<String>();
		
		try {
			Scanner attractionScanner = new Scanner(coolStuffList);
			
			while(attractionScanner.hasNextLine()){
				AttractionGenerator.attractionList.add(attractionScanner.nextLine());
			}
			
		} catch (FileNotFoundException exception) {
			System.out.println("Error Loading File!");
		}
	}
	
	public static LinkedList<String> getAttractions(){
		
		LinkedList<String> attractionList = new LinkedList<String> ();
		
		int numAttractions = (int) (Math.random() * 3);
		
		for(int i = 0; i <= numAttractions; i++){
			int randomIndex = (int) (Math.round(Math.random() * (AttractionGenerator.attractionList.size() - 1)));
			
			attractionList.add(AttractionGenerator.attractionList.get(randomIndex));
		}
		
		return attractionList;
		
	}
	
}
