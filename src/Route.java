import java.util.Random;
import java.util.ArrayList;

/**
 * This class creates a route object that stores two islands and a base travel days
 * @author Fergus and Oliver
 *
 */
public class Route {
	
	private int days;
	private int risk;
	private ArrayList<Island> islands;

	
	/**
	 * Constructor
	 * @param tdays
	 * @param trisk
	 * @param tislands
	 */
	public Route(int tdays, int trisk, ArrayList<Island> tislands) {
		days = tdays;
		risk = trisk;
		islands = tislands; 

	}	
	
	/**
	 * Sets days as input
	 * @param tdays
	 */
	public void setDays(int tdays) {
		days = tdays;
	}
	
	/**
	 * 
	 * @return days
	 */
	public int getDays() {
		return days;
	}
	
	/**
	 * 
	 * @return risk of current route
	 */
	public int getRisk() {
		return risk;
	}
	
	/**
	 * 
	 * @return list of Islands involved in route (2)
	 */
	public ArrayList<Island> getRouteIslands() {
		return islands;
	}
	
	
	
	
	
	// Returns A string with the island we're on to the other island
	public String toString(Island currentIsland) {
		String str = "";
		if (currentIsland == islands.get(0)) {
			str += "From " + (islands.get(0)).getName() + " to " + (islands.get(1)).getName() + ". \nThis will take " + days + " days";
		} else {
			str += "From " + (islands.get(1)).getName() + " to " + (islands.get(0)).getName() + ". \nThis will take " + days + " days";
		}
		return str;
	}
	public String getName(Island currentIsland) {
		if (currentIsland == islands.get(0)) {
			return islands.get(1).getName();
		} else {
			return islands.get(0).getName();
		}
	}

	
	
	
	
	
	
	
	
}
