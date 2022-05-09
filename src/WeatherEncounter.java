import java.util.Random;

/**
 * This class creates a Weather encounter object which is an extension of the class random encounter.
 * It has variable damage and delay as well as what it's inherited from RandomEncounter
 * @author Fergus and Oliver
 *
 */
public class WeatherEncounter extends RandomEncounter {
	
	private int damage;
	private int delay;
	
	/**
	 * Constructor, generates random damage and delay
	 */
	public WeatherEncounter() {
		Random random = new Random();
		damage = random.nextInt(15) + 1; // 1 to 15 damage
		delay = (15 - damage) / 3;
		if (delay < 2) {
			delay = 2;
		}
		this.setDescription("You've been hit by... You've been struck by... Lightning \nYou have taken " + damage + " damage and been delayed an extra "+ delay + " days");
		
	}
	
	/**
	 * 
	 * @return damage taken
	 */
	public int getDamage() {
		return damage / 2;
	}
	
	/**
	 * 
	 * @return delay from attack
	 */
	public int getDelay() {
		return delay;
	}
}