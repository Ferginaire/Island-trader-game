import java.util.Random;

/**
 * This class creates a PirateEncounter object which constructs a pirate 
 * encounter with semi random variables
 * @author Fergus and Oliver
 *
 */
public class PirateEncounter extends RandomEncounter {
	
	private int stealAmount;
	private int damage;
	private int resistance;
	
	/**
	 * Constructor
	 * @param ship
	 */
	public PirateEncounter(Ship ship) { 
		Random random = new Random();
		stealAmount = random.nextInt(100) + 100; // 100 to 200 gold
		damage = random.nextInt(10) + 10; // 10 to 20 damage // To be divided by resistance
		resistance = random.nextInt(8) + 14; // 14 to 22
	}
	
	/**
	 * 
	 * @return resistance 
	 */
	public int getResistance() {
		return resistance;
	}
	
	/**
	 * 
	 * @return damage
	 */
	public int getDamage() {
		return damage; 
	}
	
	/**
	 * 
	 * @return amount the pirates will try to steal
	 */
	public int getStealAmount() {
		return stealAmount;
	}
}