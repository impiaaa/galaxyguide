import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * Loads all possible attractions from a text file, then gives them out randomly.
 *
 * @author Brandon Cox
 *         Created Feb 15, 2012.
 */
public class AttractionGenerator {
	
	private static ArrayList<String> attractionList;
	
	/**
	 * Loads all possible attractions from a text file.
	 *
	 */
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
	
	/**
	 * Returns a random list of attractions.
	 *
	 * @return a random list of attractions
	 */
	public static LinkedList<String> getAttractions(){
		
		LinkedList<String> attractionList = new LinkedList<String> ();
		
		int numAttractions = (int) (Math.random() * 2);
		
		for(int i = 0; i <= numAttractions; i++){
			int randomIndex = (int) (Math.round(Math.random() * (AttractionGenerator.attractionList.size() - 1)));
			
			attractionList.add(AttractionGenerator.attractionList.get(randomIndex));
		}
		
		return attractionList;
		
	}
	
}
