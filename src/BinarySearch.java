
public class BinarySearch {
	private Weapon[] arr;
	private int numOfBags = 0;
	
	/**Basic constructor that takes an array of weapons representing bags and an integer that says the number of bags
	 * @param bags
	 * @param n
	 */
	public BinarySearch(Weapon[] bags, int n) { 
		arr = bags;
		numOfBags = n;
	}
	
	/**The sorted array is a global and thus does not need to be passed as a parameter. The main method will call this and send in
	 * the rarity and the name of the item to be searched for.
	 * @param rarity
	 * @param name
	 * @return
	 */
	long search(String rarity, String name){
		boolean found = false;
		int foundindex = 0; //used for returning the bag and slot the item was found in.
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        long startTime = System.nanoTime();// start timer at beginning of the search
        while (left <= right){
            mid = left + (right-left)/2;
            if (arr[mid].getName().compareTo(name)== 0) {
            	if(arr[mid].getRarity().compareTo(rarity)==0) {
            		found = true;
            		foundindex = mid;
            		break;
            	}
            }    
            if (arr[mid].getName().compareTo(name) < 0) {
                left = mid + 1;
            }else
                right = mid - 1;
            }//This is the end of the search.
 
        long finishTime = System.nanoTime()-startTime; 
        System.out.println("\nSearching for "+rarity+" "+name+"...");
        if(!found) {
        	System.out.println("Not found\nSingle search time: "+finishTime+" nanoseconds.");
        }else {
        	System.out.println("Found in bag "+((mid/20)+1)+", Slot "+
					((mid%20)+1)+", Strength: "+arr[mid].getCurStr()+
					"\nSingle search time: "+finishTime+" nanoseconds.");
        }
        return finishTime; // return the time in nanoseconds to calculate the average.
    }
		
	/**print the first 5 elements in the bag if there are 8 or less bags.
	 * 
	 */
	public void printBags() {
		System.out.println("\n\nN = "+numOfBags+"\nBags after sorting:");
		if(numOfBags<=8) {
		int counter = 0;// increment by 20 for each new bag since they're 20 items long each
		for(int i = 0; i<numOfBags; i++) {
			System.out.println("Bag "+(i+1)+":");
			for(int j = 0; j<5; j++) {
				// (j+counter) is used here so that it prints the proper bag 0-5 for bag one 20-25 for bag two and so on.
				System.out.println("\t"+arr[j+counter].getRarity()+" "+arr[j+counter].getName()+", "+arr[j+counter].getCurStr());
			}
			counter +=20;
			}
	}
	}
}
