import java.util.Random;

public class LinearSearch {
	Weapon[][] bags;
	int numOfBags;
	String[] input;
	
	/** just a constructor that takes the array of all the lines from the csv and number of bags
	 * and assigns them to global variables
	 * @param inputString
	 * @param numberOfBags
	 */
	public LinearSearch(String[] inputString, int numberOfBags) {
		input = inputString;
		numOfBags = numberOfBags;
	}
	
	/**Just a simple linear search that uses nested for loops to search through for the rarity and name of the item main provides
	 * 
	 * @param rarity
	 * @param toFind
	 * @return
	 */
	public long search(String rarity, String name) {
		
		//These are variables used to keep track of if the item was found and if so which bag and slot it was in.
		boolean found = false;
		int foundBag = 0;
		int foundSlot = 0;
		long totalTime = 0;
		long startTime = System.nanoTime();// start saving the time
		for(int i = 0; i<numOfBags; i++) {
			for(int j = 0; j<bags[i].length; j++) {
					if((bags[i][j].getRarity().equals(rarity))&&(bags[i][j].getName().equals(name))) {
						foundBag = i;
						foundSlot = j;
						found = true;
						break;}	//when you find the item stop looking for more
			}
		}
		totalTime = System.nanoTime() - startTime; //get current time minus time at start
		
			System.out.println("\nSearching for "+rarity+" "+name+"...");
			if(found == false) {
				System.out.println("Not found.\nSingle search time: "+totalTime+" nanoseconds.");	
			}
			else {	
				System.out.println("Found in bag "+(foundBag+1)+", Slot "+
						(foundSlot+1)+", Strength: "+bags[foundBag][foundSlot].getCurStr()+
						"\nSingle search time: "+totalTime+" nanoseconds.");
			}
			return totalTime;
		
		}
	
	/**print the first 5 elements in the bag if there are 8 or less bags.
	 * 
	 */
	public void printBags() {
		System.out.println("N = "+numOfBags+"\nBags before sorting:");
		if(numOfBags<=8) {
				for(int i = 0; i<numOfBags; i++) {
					System.out.println("Bag "+(i+1)+":");
					for(int j = 0; j<5; j++) {
						try {
							System.out.println("\t"+bags[i][j].getRarity()+" "+bags[i][j].getName()+", "+bags[i][j].getCurStr());
						}catch (NullPointerException e) {} //Don't do anything if It's null. This is because the final bag might not be full.
					}
				}
		}
	}
	
	/**takes the array of strings provided and randomly selects items. splits them and creates objects of them. places those objects
	 * in a 2d array of [bags][slots].
	 */
	public void fillArray(){
		int numOfItems = input.length;	
		Random rand = new Random();
		String fields[] = new String[4]; //each line will be broken into 4 fields. name, minstrength, maxstrength, and rarity
		bags = new Weapon[numOfBags][20]; 
		for(int i = 0; i<numOfBags; i++) {
			for(int j = 0; j<20; j++) {
				int arrayIndex = rand.nextInt(numOfItems);
				fields = input[arrayIndex].split(","); //split each line based on comma and use it to fill fields
				String name = fields[0];
				int minStrength = Integer.parseInt(fields[1]);
				int maxStrength = Integer.parseInt(fields[2]);
				String rarity = fields[3].trim();
				int currentStrength = rand.nextInt((maxStrength - minStrength) + 1) + minStrength; //calculate current strength
				bags[i][j] = new Weapon(name, minStrength, maxStrength, rarity, currentStrength); //create item and put it in bags
				}
		}
		
	}
	
}
