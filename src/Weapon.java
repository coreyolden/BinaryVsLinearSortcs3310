
 

public class Weapon {
	
	//These are all the fields contained in a weapon
	private String name;
	private int minStrength = 0;
	private int maxStrength = 0;
	private String rarity;
	private int currentStrength = 0;
	
	/**Just a constructor that will take all assigned fields and save them to private variables.
	 * 
	 * @param nameParam
	 * @param minStrParam
	 * @param maxStrParam
	 * @param rareparam
	 * @param curStrParam
	 */
	public Weapon(String nameParam, int minStrParam, int maxStrParam, String rareparam, int curStrParam) {
		name = nameParam;
		minStrength = minStrParam;
		maxStrength = maxStrParam;
		rarity = rareparam;
		currentStrength = curStrParam;
	}
	
	/**just return the requested variable.
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**just return the requested variable.
	 * 
	 * @return
	 */
	public String getRarity() {
		return rarity;
	}	
	
	/**just return the requested variable.
	 * 
	 * @return
	 */
	public int getCurStr() {
		return currentStrength;
	}
	
	
}
