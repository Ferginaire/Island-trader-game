import java.util.ArrayList;

/**
 * This class creates a ship object which is used to store variables such as
 * currentHealth, cargo, crew size and resistance
 * @author Fergus and Oliver
 *
 */
public class Ship {
	
	String name;
	int crew;
	int cargoSize;
	int currentCargo;
	int maxHp;
	int currentHp;
	int resistance;
	ArrayList<Upgrade> upgrades;
	int sailSpeed;
	
	/**
	 * Constructor
	 * @param tname
	 * @param tcrew
	 * @param tcargoSize
	 * @param tmaxHp
	 * @param tresistance
	 * @param tsailSpeed
	 */
	public Ship(String tname, int tcrew, int tcargoSize, int tmaxHp, int tresistance, int tsailSpeed) {
		name = tname;
		crew = tcrew;
		cargoSize = tcargoSize;
		maxHp = tmaxHp;
		currentHp = maxHp;
		resistance = tresistance;
		sailSpeed = tsailSpeed;
		upgrades = new ArrayList<Upgrade>();
	}
	
	/**
	 * 
	 * @return String comprised of all variables relevant to ship selection 
	 */
	public String toBasicString() {
		return name+" ("+crew+" Crew, "+cargoSize+" Cargo, "+maxHp+" Hp, "+resistance+" resistance "+sailSpeed+" Sail Speed)";
	}
	
	/**
	 * 
	 * @return String comprised of all current variables of your ship
	 */
	public String toFullString(int currentHp) {
		return "Crew: "+crew+"\nMax cargo: "+cargoSize+"\nHp: "+currentHp+"/"+maxHp+"\nResistance: "+resistance+"\nSail Speed: "+sailSpeed+"\n";
	}
	
	/**
	 * 
	 * @return name of ship
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return number of crew members
	 */
	public int getCrew() {
		return crew;
	}
	
	/**
	 * 
	 * @return cargo size
	 */
	public int getCargoSize() {
		return cargoSize;
	}
	
	/**
	 * 
	 * @return maximum hull points
	 */
	public int getMaxHp() {
		return maxHp;
	}
	
	/**
	 * 
	 * @return current hull points
	 */
	public int getCurrentHp() {
		return currentHp;
	}
	
	/**
	 * 
	 * @return resistance of ship
	 */
	public int getResistance() {
		return resistance;
	}
	
	/**
	 * 
	 * @return sail speed of ship
	 */
	public int getSailSpeed() {
		return sailSpeed;
	}
	
	/**
	 * Adds an upgrade to your ship, improving one of its stats
	 * @param u
	 * @param game
	 */
	public void addUpgrade(Upgrade u, GameEnvironment game) {
		cargoSize += u.getCargo();
		maxHp += u.getHp();
		game.setCurrentHp(currentHp + u.getHp());
		resistance += u.getResistance();
		sailSpeed += u.getSailSpeed();
		upgrades.add(u);
	}
	
	/**
	 * Sets current HP to max
	 * @param value
	 */
	public void repair(int value) {
		currentHp = maxHp;
	}
	
	/**
	 * Lowers current hp by damage amount
	 * @param damage
	 */
	public void takeDamage(int damage) {
		currentHp -= damage;
	}
	
	/**
	 * 
	 * @return true if currentHp is more than 0
	 */
	public boolean isAlive() {
		if (currentHp <= 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * setsCurrentHp to input amount
	 * @param hp
	 */
	public void setCurrentHp(int hp) {
		currentHp = hp;
	}
}
