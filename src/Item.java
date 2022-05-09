
/**
 * This class creates an Item object which has a price, name, description and a size
 * @author Fergus and Oliver
 *
 */
public class Item {
	public static double favouredMult = 1.5;
	public static double unfavouredMult = 0.5;
	String name;
	String description;
	int size;
	int cost;
	int adjCost;
	boolean hasSold;
	int soldPrice;
	Island soldIsland;
	
	/**
	 * Constructor
	 * @param tname
	 * @param tdescription
	 * @param tsize
	 * @param tprice
	 */
	public Item (String tname, String tdescription, int tsize, int tprice) {
		name = tname;
		description = tdescription;
		size = tsize;
		cost = tprice;
	}
	
	/**
	 * 
	 * @return Item name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return item description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * The amount of cargo space item takes up
	 * @return item size
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * 
	 * @return cost of item
	 */
	public int getCost() {
		return cost;
	}	
	
	/**
	 * 
	 * @return true if Item has been sold
	 */
	public boolean getHasSold() {
		return hasSold;
	}
	
	/**
	 * 
	 * @return price item sold for
	 */
	public int getSoldPrice() {
		return soldPrice;
	}
	
	/**
	 * 
	 * @return Island Item bought from
	 */
	public Island getSoldIsland() {
		return soldIsland;
	}
	
	/**
	 * setsItem as sold
	 */
	public void setItemSold() {
		hasSold = true;
		soldPrice = cost;
		// Increase gold?
		// If we have a buy item function here, then maybe we should also have a sell item function here?
		// setSoldIsland(yeet.getCurrentIsland()) implement when gameEvironment is up and we know how to call currentIsland?
	}
	
	/**
	 * Sets island sold
	 * @param island
	 */
	public void setSoldIsland(Island island) {
		soldIsland = island;
	}
	
	/**
	 * Multiplies the price by the favouredMultiplier (1.5x)
	 */
	public void increasePrice() {
		cost *= favouredMult;
	}
	
	/**
	 * Multiplies the price by the unfavouredMultiplier (0.5x)
	 */
	public void decreasePrice() {
		cost *= unfavouredMult;
	}
	
	/**
	 * Sets the item cost to the specified value
	 * @param tvalue
	 */
	public void setCost(int tvalue) {
		cost = tvalue;
	}
}
