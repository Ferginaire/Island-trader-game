
/**
 * This class creates an upgrade object that can be applied to a Ship objectS
 * @author Fergus and Oliver
 *
 */
public class Upgrade {
	// Should we extend the ship class for this class? That could help possibly?
	String name;
	String description;
	int cost;
	int cargo;
	int hp;
	int resistance; 
	int sailSpeed;
	// Added a name variable 14th may
	
	/**
	 * Constructor
	 * @param tname
	 * @param tdescription
	 * @param tcost
	 * @param tcargo
	 * @param thp
	 * @param tresistance
	 * @param tsailSpeed
	 */
	public Upgrade(String tname, String tdescription, int tcost, int tcargo, int thp, int tresistance, int tsailSpeed) {
		name = tname;
		description = tdescription;
		cost = tcost;
		cargo = tcargo;
		hp = thp;
		resistance = tresistance;
		sailSpeed = tsailSpeed;
	}
	
	/**
	 * @return string representation of upgrades
	 */
	public String toString() {
		return name + ": " + description + ". Cost: " + cost;
	}
	
	/**
	 * 
	 * @return string representation for the sake of gui display
	 */
	public String guiToString() {
		return description + "\n" + "Cost: " + cost;
	}
	
	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
	public int getCost() {
		return cost;
	}
	
	/**
	 * 
	 * @return cargo increase amount
	 */
	public int getCargo() {
		return cargo;
	}
	
	/**
	 * 
	 * @return max hp increase amount
	 */
	public int getHp() {
		return hp;
	}
	
	/**
	 * 
	 * @return resistance increase amount
	 */
	public int getResistance() {
		return resistance;
	}
	
	/**
	 * 
	 * @return sail speed increase amount
	 */
	public int getSailSpeed() {
		return sailSpeed;
	}
}
