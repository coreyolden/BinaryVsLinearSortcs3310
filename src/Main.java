import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.math.*;

public class Main {

	/**This is just the main points that handles reading the files, setting up an array of items
	 * and then calling all needed methods/classes that handle everything else.
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
public static void main(String[] args) throws FileNotFoundException {
		int numOfBags = 10; //This is my N.
		int numOfItems = getNumOfItems(); //This is used to get number of items if a different data set is used.
		String[] arrayOfItems = new String[numOfItems];
		Scanner scanToKeep = new Scanner(new File("A1_items.txt")); // set up scanner and delimiter
		scanToKeep.useDelimiter("\n");
		scanToKeep.next(); //get rid of the first line which is just category names.
		int itemTotal = 0;
		while(scanToKeep.hasNext()) {
			arrayOfItems[itemTotal] = scanToKeep.next();//read the csv and fill an array of all items from the csv.
			itemTotal++;
		}		
		linearSearch(arrayOfItems, numOfBags, numOfItems);// deals with creating the linear search, filling the array, and searching.
		
		//deals with creating the sort, sending it to search, and searching.
		binarySearch(arrayOfItems, numOfBags, numOfItems);
				
	}
	
	
	/**Reads the csv and counts lines which are returned to the main function.
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	public static int getNumOfItems() throws FileNotFoundException {
		Scanner scanToCount = new Scanner(new File("A1_items.txt"));
		scanToCount.useDelimiter("\n");
		//Get the number of lines in the file
		int numOfLines=0;
		scanToCount.next(); //get rid of the first line which is just category names.
		while(scanToCount.hasNext()) {
			scanToCount.next();
			numOfLines++;
		}
		scanToCount.close();
		return numOfLines;
	}
	
	/**creates a LinearSearch object and calls the methods to fill the array and print the bag content.
	 * runs searches in a for loop to gather 8 searches and then prints average time.
	 * @param arrayOfItems
	 * @param numOfBags
	 * @param numOfItems
	 */
	public static void linearSearch(String[] arrayOfItems, int numOfBags, int numOfItems) {

		// Construct a linearSearch with the parameter being bags, rarity, and name then run the search
		LinearSearch ls = new LinearSearch(arrayOfItems, numOfBags);
		ls.fillArray();
		ls.printBags();
		long linearSearchTime = 0;
		
		//Select a random number and search for it. do this 5 times calculating the average.
		for(int i =0; i<8; i++) {
			Random rand = new Random();
			int arrayIndex = rand.nextInt(numOfItems);
			String[] searchFields = new String[4];
			searchFields = arrayOfItems[arrayIndex].split(",");
		long singleSearch =ls.search(searchFields[3].trim(), searchFields[0]);
		linearSearchTime = linearSearchTime + singleSearch;
		
		}
		System.out.println("Average	search	time: "+(linearSearchTime/5)+ " nanoseconds for 8 searches");
		
	}
	/**creates a BinaryrSearch object and calls the methods to fill the array and print the bag content.
	 * runs searches in a for loop to gather 8 searches and then prints average time.
	 * @param arrayOfItems
	 * @param numOfBags
	 * @param numOfItems
	 */
	public static void binarySearch(String[] arrayForSorting, int numOfBags, int numOfItems) {
		Sort sort = new Sort(arrayForSorting, numOfBags);
		Weapon[] sortedArray = sort.fillArray();
		
		long binarySearchTime = 0;
		BinarySearch bs = new BinarySearch(sortedArray, numOfBags);
		bs.printBags();
		
		//Select a random number and search for it. do this 5 times calculating the average.
		for(int i =0; i<8; i++) {
			Random rand = new Random();
			int arrayIndex = rand.nextInt(numOfItems);
			String[] searchFields = new String[4];
			searchFields = arrayForSorting[arrayIndex].split(",");
		long singleSearch =bs.search(searchFields[3].trim(), searchFields[0]);
		binarySearchTime = binarySearchTime + singleSearch;
		
		}
		System.out.println("Average	search	time: "+(binarySearchTime/5)+ " nanoseconds for 8 searches");
		
	}
	

}
