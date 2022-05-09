import java.util.ArrayList;

/**
 * This class creates an Island object which includes the island's store and the island's name
 * @author Fergus and Oliver
 *
 */
public class Island {
	
	String name;
	Store store;
	ArrayList<Route> routes;
	
	/**
	 * Construct requires a name and Store object
	 * @param tname
	 * @param tstore
	 */
	public Island(String tname, Store tstore) {
		name = tname;
		store = tstore;
	}
	
	/**
	 * 
	 * @return name of Island String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return name of Island Routes
	 */
	public ArrayList<Route> getRoutes() {
		return routes;
	}
	
	/**
	 * 
	 * @return islands Store object
	 */
	public Store visitStore() {
		return store;
	}
	
	/**
	 * returns the ith Route in Routes
	 * @param i
	 * @return Route
	 */
	public Route takeRoute(int i) {
		return routes.get(i);
	}
	
	/**
	 * Sets Islands routes using troutes
	 * @param troutess
	 */
	public void setRoutes(ArrayList<Route> troutes) {
		routes = troutes;
	}
	
	/**
	 * 
	 * @return islands Store object
	 */
	public Store getStore() {
		return store;
	}
}
