import java.util.Random;

/**
 * This class creates a RescueSailors ecnounter object which is an extension of the class random encounter.
 * It has a variable reward as well as what it's inherited from RandomEncounter
 * @author Fergus and Oliver
 *
 */
public class RescueSailors extends RandomEncounter {
	
	private int reward;
	
	/**
	 * Constructor, sets random gold amount for reward and creates a description
	 */
	public RescueSailors() {
		Random random = new Random();
		reward = random.nextInt(20) + 20; // 20 to 40 gold
		this.setDescription("You found marooned sailors. \nThey reward you with " + reward + " gold.");
	}
	
	/**
	 * 
	 * @return reward
	 */
	public int getReward() {
		return reward;
	}
	
}