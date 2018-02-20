import java.util.Random;

public class Sort {

	private String[] unsortedArray;
	private int numOfBags = 0;
	private Weapon[] sortedArray;
	
	/**Basic constructor which takes a string array which is all items read from csv and number of bags. 
	 * 
	 * @param array
	 * @param n
	 */
	public Sort(String[] array, int n) {
		unsortedArray = array;
		numOfBags = n;
	}
	
	/**looks at elements and places the smaller element first.
	 * This compares name, followed by rarity, followed by current strength
	 * 
	 * 
	 * @param arr
	 * @param start
	 * @param mid
	 * @param end
	 */
	void merge(Weapon[] arr, int start, int mid, int end){
        int subArray1 = mid - start + 1;
        int subArray2 = end - mid;

        Weapon[] Left = new Weapon[subArray1];
        Weapon[] Right= new Weapon[subArray2];

        for (int i=0; i < subArray1; ++i)
            Left[i] = arr[start + i];

        for (int i=0; i < subArray2; ++i)
            Right[i] = arr[mid + 1 + i];



        int i = 0;
        int j = 0;

        int k = start;
        while (i < subArray1 && j < subArray2){
            if (Left[i].getName().compareTo(Right[j].getName()) < 0){// if name is less place left
            	arr[k] = Left[i];
            	i++;
            }else if(Left[i].getName().compareTo(Right[j].getName())== 0) {// if name is equal look at rarity
            	 if (Left[i].getRarity().compareTo(Right[j].getRarity()) < 0){// if rarity is less place left
            		 arr[k] = Left[i];
            		 i++;
            	 }else if(Left[i].getRarity().compareTo(Right[j].getRarity()) == 0) {// if name and rarity are equal then compare strength
            		 if(Left[i].getCurStr() <= Right[i].getCurStr()) {
            			 arr[k] = Left[i];
            			 i++;
            		 }else{
            			 arr[k] = Right[j];
            			 j++;
            		 		}

            	 }else{//rarity is greater place right
        			 arr[k] = Right[j];
        			 j++;
        		 		}
            }else{// name is greater place right
   			 arr[k] = Right[j];
   			 j++;
   		 		}


            k++;
        }

        while (i < subArray1){
            arr[k] = Left[i];
            i++;
            k++;
        }

        while (j < subArray2){
            arr[k] = Right[j];
            j++;
            k++;
        }
    }


	/** splits the array recursively and then merge them.
	 * 
	 * @param sortedArray
	 * @param start
	 * @param end
	 */
	private void sort(Weapon[] sortedArray, int start, int end){

        if (start < end){
            int mid = (start+end)/2;

            sort(sortedArray, start, mid);
            sort(sortedArray , mid+1, end);

            merge(sortedArray, start, mid, end);
        }
        
    }
	

	
	/**fills the array of random items then sends it to sort for sorting and returns the resulting array to main.
	 * 
	 * @return
	 */
	public Weapon[] fillArray() {
		Random rand = new Random();
		Weapon[] arrayToSort = new Weapon[numOfBags*20];
		String fields[] = new String[4];
		for(int i = 0; i<arrayToSort.length; i++) {
			int arrayIndex = rand.nextInt(unsortedArray.length);
			fields = unsortedArray[arrayIndex].split(",");//split the line into 4 fields. name, minstrength, maxstrength, rarity
			String name = fields[0];
			int minStrength = Integer.parseInt(fields[1]);
			int maxStrength = Integer.parseInt(fields[2]);
			String rarity = fields[3].trim();
			int currentStrength = rand.nextInt((maxStrength - minStrength) + 1) + minStrength; //randomly generate current strength
			arrayToSort[i] = new Weapon(name, minStrength, maxStrength, rarity, currentStrength); //creates the weapon and stores it array
		}
		sortedArray = arrayToSort;
		sort(sortedArray, 0, sortedArray.length-1); 
		return sortedArray;
	}
	

}
