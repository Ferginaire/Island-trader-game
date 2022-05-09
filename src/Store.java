import java.util.ArrayList;

/**
 * This class creates a store object that has all of the items it buys, sells, and which should have their prices alters
 * @author Fergus and Oliver
 *
 */
public class Store {
	private ArrayList<Item> buyableItems;
	private ArrayList<Item> sellableItems;
	private ArrayList<Item> purchasedItems;
	private ArrayList<Item> expensiveItems;
	private ArrayList<Item> cheapItems;
	private ArrayList<Upgrade> upgrades;
	
	public Store() {
		
	}
	
	/**
	 * Constructor
	 * @param tbuyableItems
	 * @param tsellableItems
	 * @param tpurchasedItems
	 * @param texpensiveItems
	 * @param tcheapItems
	 * @param tupgrades
	 */
	public Store(ArrayList<Item> tbuyableItems, ArrayList<Item> tsellableItems, ArrayList<Item> tpurchasedItems, ArrayList<Item> texpensiveItems, ArrayList<Item> tcheapItems, ArrayList<Upgrade> tupgrades) {
		buyableItems = tbuyableItems;
		sellableItems = tsellableItems;
		purchasedItems = tpurchasedItems;
		expensiveItems = texpensiveItems;
		cheapItems = tcheapItems;
		upgrades = tupgrades;
		init();
	}
	
	/**
	 * Initializes store function
	 */
	public void init() {
		initBuyable();
	}
	
	/**
	 * 
	 * @return buyableItems
	 */
	public ArrayList<Item> getBuyableItems() {
		return buyableItems;
	}
	
	/**
	 * 
	 * @return sellableItems
	 */
	public ArrayList<Item> getSellableItems() {
		return sellableItems;
	}
	
	/**
	 * 
	 * @return purchased Items from this store
	 */
	public ArrayList<Item> getPurchasedItems() {
		return purchasedItems;
	}
	
	/**
	 * 
	 * @return List of items the store pays more than usual for
	 */
	public ArrayList<Item> getExpensiveItems() {
		return expensiveItems;
	}
	
	/**
	 * 
	 * @return List of items the store sells less than usual for
	 */
	public ArrayList<Item> getCheapItems() {
		return cheapItems;
	}
	
	/**
	 * 
	 * @return List of upgrades buyable
	 */
	public ArrayList<Upgrade> getUpgrades() {
		return upgrades;
	}
	
	/**
	 * Lowers gold by specified amount 
	 * @param choice
	 * @param game
	 * @return buyable items less specified purchase
	 */
	public Item buyItem(int choice, GameEnvironment game) {
		game.gold -= (buyableItems.get(choice)).getCost();
		buyableItems.remove(choice);
		return buyableItems.get(choice);
	}
	
	public void sellItem(int choice) {
		//(sellableItems.get(choice)).sellItem();
		//sellableItems.remove(choice);
	}
	
	/**
	 * 
	 * @return buyableItems
	 */
	public ArrayList<Item> viewBuyableItems() {
		return buyableItems;
	}
	
	/**
	 * 
	 * @return sellable Items
	 */
	public ArrayList<Item> viewSellableItems() {
		return sellableItems;
	}
	
	/**
	 * 
	 * @return Items you've bought from this store
	 */
	public ArrayList<Item> viewPurchasedGoods() {
		return purchasedItems;
	}
	
	/**
	 * Initializes a buyable list including item price increases/ decreases
	 */
	public void initBuyable() {
		for (Item item: expensiveItems) {
			buyableItems.remove(item);
			Item titem = new Item(item.getName(),item.getDescription(),item.getSize(),(int) (item.getCost()*Item.favouredMult));
			buyableItems.add(titem);
		}
		for (Item item: cheapItems) {
			buyableItems.remove(item);
			Item titem = new Item(item.getName(),item.getDescription(),item.getSize(),(int) (item.getCost()*Item.unfavouredMult));
			buyableItems.add(titem);
		}
	}
	
	
	/**
	 * 
	 * @return String representation of buyable items
	 */
	public String buyableItemsToString() {
		String str = "";
		for (int i=0;i<buyableItems.size();i++) {
			Item item = buyableItems.get(i);
			if (i == 20) {
				str += "Expensive Items------------------------------------------------\n";
			}
			if (i == 23) {
				str += "Cheap Items----------------------------------------------------\n";
			}
			str += (i+1)+": "+item.getName()+", \""+item.getDescription()+"\". Size: "+item.getSize()+". Price: "+item.getCost()+" Gold.\n";
		}
		return str;
	}
	
	/**
	 * String representation of sellable items
	 */
	public String sellableItemsToString() {
		String str = "";
		for (int i=0;i<sellableItems.size();i++) {
			Item item = sellableItems.get(i);
			if (i == 20) {
				str += "Expensive Items------------------------------------------------\n";
			}
			if (i == 23) {
				str += "Cheap Items----------------------------------------------------\n";
			}
			str += (i+1)+": "+item.getName()+", \""+item.getDescription()+"\". Size: "+item.getSize()+". Price: "+item.getCost()+" Gold.\n";
		}
		return str;
	}
}








