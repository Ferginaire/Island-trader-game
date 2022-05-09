import java.util.ArrayList;
import java.util.HashMap;
//import java.util.HashSet;
import java.util.Random;

/**
 * This class is responsible for creating all of the non gui objects in the game.
 * It also holds all the variables widely accessed variables with corresponding getter and setter methods.
 * Furthermore It also holds all the methods that are widely accessed throughout the game
 * @author Fergus and Oliver
 *
 */
public class GameEnvironment {
	ArrayList<Ship> ships = new ArrayList<Ship>();
	ArrayList<Island> islands = new ArrayList<Island>();
	ArrayList<Store> stores = new ArrayList<Store>();
	ArrayList<Item> items = new ArrayList<Item>();
	ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
	ArrayList<Route> routes = new ArrayList<Route>();
	int gold = 400;
	int daysLeft; // test
	String characterName;
	public static int repairCost = 3;
	HashMap<Item, Integer> inventory = new HashMap<Item, Integer>();
	int currentCargo;
	Ship currentShip;
	int currentHp;
	Island currentIsland;
	ArrayList<ArrayList<Route>> islandRoutes = new ArrayList<ArrayList<Route>>();
	double costPerCrewDay = 0.05;
	int mysteriousLiquidValue;
	int latestGold;
	int initDays;
	String from = "";
	
	/**
	 * 
	 * @return The list of available ships to choose from
	 */
	public ArrayList<Ship> getShips() {
		return ships;
	}
	
	/**
	 * Creates the available ships
	 */
	public void addShips() {
		Ship galley = new Ship("Galley", 100, 30, 100, 10, 10);
		ships.add(galley);
		Ship warship = new Ship("Warship", 80, 40, 125, 20, 5);
		ships.add(warship);
		Ship sailingship = new Ship("Sailing Ship", 30, 25, 50, 15, 15);
		ships.add(sailingship);
		Ship longship = new Ship("Longship", 20, 20, 20, 10, 20);
		ships.add(longship);
	}
	
	/**
	 * Creates the available Upgrades
	 */
	public void addUpgrades() {
		Upgrade cargo = new Upgrade("Cargo capacity upgrade", "Increases your max cargo\ncapacity by 10", 200, 10, 0, 0, 0);
		upgrades.add(cargo);
		Upgrade hp = new Upgrade("Hull durability upgrade", "Increases your max Hull\nPoints by 30", 200, 0, 30, 0, 0);
		upgrades.add(hp);
		Upgrade resistance = new Upgrade("Extra Cannons", "Cannons.\nPirates will have a\nharder time stealing from\nyou if you have these", 200, 0, 0, 4, 0);
		upgrades.add(resistance);
		Upgrade sailSpeed = new Upgrade("Faster sails", "Reduce the days needed\nto get from A to B", 200, 0, 0, 0, 4);
		upgrades.add(sailSpeed);
	}

	/**
	 * Creates the available stores and assigns the items they buy/sell and which island they belong to
	 */
	public void addStores() {
		ArrayList<Item> itemsWithoutSpecials = new ArrayList<Item>();
		
		itemsWithoutSpecials.addAll(items);
		itemsWithoutSpecials.remove(29);
		itemsWithoutSpecials.remove(28);
		itemsWithoutSpecials.remove(27);
		itemsWithoutSpecials.remove(26);
		itemsWithoutSpecials.remove(25);
		ArrayList<Item> dathBasePriceItems = new ArrayList<Item>();
		dathBasePriceItems.addAll(itemsWithoutSpecials);
		ArrayList<Item> dathExpennyItems = new ArrayList<Item>();
		ArrayList<Item> dathCheapItems = new ArrayList<Item>();
		dathCheapItems.add(items.get(21)); //Mushrooms
		dathBasePriceItems.remove(21);
		dathExpennyItems.add(items.get(19)); //Spriggon sap
		dathBasePriceItems.remove(19);
		dathExpennyItems.add(items.get(18)); //Snowberries
		dathBasePriceItems.remove(18);
		dathExpennyItems.add(items.get(17)); //Blue butterfly wing
		dathBasePriceItems.remove(17);
		dathCheapItems.add(items.get(13)); //Jar of gas
		dathBasePriceItems.remove(13);
		dathCheapItems.add(items.get(10)); //Parchment
		dathBasePriceItems.remove(10);
		ArrayList<Item> dathItemsStoreWillSell = new ArrayList<Item>();
		dathBasePriceItems.add(items.get(29)); //Mysterious liquid // Specialty item
		dathItemsStoreWillSell.addAll(dathBasePriceItems);
		
		ArrayList<Item> dathPurchasedItems = new ArrayList<Item>();
		ArrayList<Item> dathItemsStoreWillBuy= new ArrayList<Item>();
		dathItemsStoreWillBuy.addAll(items);
		Store dathomirStore = new Store(dathItemsStoreWillSell, dathItemsStoreWillBuy, dathPurchasedItems, dathExpennyItems, dathCheapItems, upgrades);
		stores.add(dathomirStore);
		ArrayList<Item> hothBasePriceItems = new ArrayList<Item>();
		hothBasePriceItems.addAll(itemsWithoutSpecials);
		ArrayList<Item> hothExpennyItems = new ArrayList<Item>();
		ArrayList<Item> hothCheapItems = new ArrayList<Item>();
		hothCheapItems.add(items.get(20)); //Hoth herb
		hothBasePriceItems.remove(20);
		hothExpennyItems.add(items.get(25)); //Lava cookies
		hothCheapItems.add(items.get(18)); //Snowberries
		hothBasePriceItems.remove(18);
		hothExpennyItems.add(items.get(14)); //Oil
		hothBasePriceItems.remove(14);
		hothCheapItems.add(items.get(12)); //Fur
		hothBasePriceItems.remove(12);
		hothExpennyItems.add(items.get(0)); //Rum
		hothBasePriceItems.remove(0);
		
		ArrayList<Item> hothItemsStoreWillSell = new ArrayList<Item>();
		hothItemsStoreWillSell.addAll(hothBasePriceItems);
		hothCheapItems.add(items.get(26)); //Ice cakes //Specialty item 
		ArrayList<Item> hothPurchasedItems = new ArrayList<Item>();
		ArrayList<Item> hothItemsStoreWillBuy= new ArrayList<Item>();
		hothItemsStoreWillBuy.addAll(items);
		Store hothStore = new Store(hothItemsStoreWillSell, hothItemsStoreWillBuy, hothPurchasedItems, hothExpennyItems, hothCheapItems, upgrades);
		stores.add(hothStore);
		
		ArrayList<Item> nabooBasePriceItems = new ArrayList<Item>();
		nabooBasePriceItems.addAll(itemsWithoutSpecials);
		ArrayList<Item> nabooExpennyItems = new ArrayList<Item>();
		ArrayList<Item> nabooCheapItems = new ArrayList<Item>();
		nabooExpennyItems.add(items.get(24)); // Bigger fish
		nabooBasePriceItems.remove(24);
		nabooCheapItems.add(items.get(23)); // Big fish
		nabooBasePriceItems.remove(23);
		nabooCheapItems.add(items.get(17)); // Jar of butterfly wings
		nabooBasePriceItems.remove(17);
		nabooCheapItems.add(items.get(16)); //Sand
		nabooBasePriceItems.remove(16);
		nabooExpennyItems.add(items.get(3)); // Sugar
		nabooBasePriceItems.remove(3);
		nabooExpennyItems.add(items.get(1)); // Flour
		nabooBasePriceItems.remove(1);
		ArrayList<Item> nabooItemsStoreWillSell = new ArrayList<Item>();
		nabooItemsStoreWillSell.addAll(nabooBasePriceItems);
		nabooCheapItems.add(items.get(28)); //frogs //Specialty item
		ArrayList<Item> nabooPurchasedItems = new ArrayList<Item>();
		ArrayList<Item> nabooItemsStoreWillBuy= new ArrayList<Item>();
		nabooItemsStoreWillBuy.addAll(items);
		Store nabooStore = new Store(nabooItemsStoreWillSell, nabooItemsStoreWillBuy, nabooPurchasedItems, nabooExpennyItems, nabooCheapItems, upgrades);
		stores.add(nabooStore);
		ArrayList<Item> mustBasePriceItems = new ArrayList<Item>();
		mustBasePriceItems.addAll(itemsWithoutSpecials);
		ArrayList<Item> mustExpennyItems = new ArrayList<Item>();
		ArrayList<Item> mustCheapItems = new ArrayList<Item>();

		mustExpennyItems.add(items.get(26)); // Ice cakes
		mustExpennyItems.add(items.get(16)); // Sand barrel
		mustBasePriceItems.remove(16);
		mustCheapItems.add(items.get(14)); // Oil
		mustBasePriceItems.remove(14);
		mustCheapItems.add(items.get(11)); // Obsidian
		mustBasePriceItems.remove(11);
		mustCheapItems.add(items.get(5)); // Salt
		mustBasePriceItems.remove(5);
		mustExpennyItems.add(items.get(4)); // Gunpowder
		mustBasePriceItems.remove(4);
		ArrayList<Item> mustItemsStoreWillSell = new ArrayList<Item>();
		mustItemsStoreWillSell.addAll(mustBasePriceItems);
		mustCheapItems.add(items.get(25)); //Lava cookies //Specialty item
		ArrayList<Item> mustPurchasedItems = new ArrayList<Item>();
		ArrayList<Item> mustItemsStoreWillBuy= new ArrayList<Item>();
		mustItemsStoreWillBuy.addAll(items);
		Store mustafarStore = new Store(mustItemsStoreWillSell, mustItemsStoreWillBuy, mustPurchasedItems, mustExpennyItems, mustCheapItems, upgrades);
		stores.add(mustafarStore);
		ArrayList<Item> dagBasePriceItems = new ArrayList<Item>();
		dagBasePriceItems.addAll(itemsWithoutSpecials);
		ArrayList<Item> dagExpennyItems = new ArrayList<Item>();
		ArrayList<Item> dagCheapItems = new ArrayList<Item>();
		dagExpennyItems.add(items.get(21)); // Mushrooms
		dagBasePriceItems.remove(21);
		dagCheapItems.add(items.get(19)); // Spriggon sap
		dagBasePriceItems.remove(19);
		dagExpennyItems.add(items.get(7)); // Vegetables
		dagBasePriceItems.remove(7);
		dagCheapItems.add(items.get(3)); // Sugar
		dagBasePriceItems.remove(3);
		dagExpennyItems.add(items.get(2)); // Bread
		dagBasePriceItems.remove(2);
		dagCheapItems.add(items.get(0)); // Rum
		dagBasePriceItems.remove(0);	
		ArrayList<Item> dagItemsStoreWillSell = new ArrayList<Item>();
		dagItemsStoreWillSell.addAll(dagBasePriceItems);
		dagCheapItems.add(items.get(27)); // Sludge //Specialty item
		ArrayList<Item> dagPurchasedItems = new ArrayList<Item>();
		ArrayList<Item> dagItemsStoreWillBuy= new ArrayList<Item>();
		dagItemsStoreWillBuy.addAll(items);
		ArrayList<Item> dagUpdatedCheap = new ArrayList<Item>();
		ArrayList<Item> dagUpdatedExpenny = new ArrayList<Item>();
		dagUpdatedCheap.addAll(dagCheapItems);
		dagUpdatedExpenny.addAll(dagExpennyItems);
		Store dagobahStore = new Store(dagItemsStoreWillSell, dagItemsStoreWillBuy, dagPurchasedItems, dagExpennyItems, dagCheapItems, upgrades);
		stores.add(dagobahStore);
	}
	/**
	 * Creates each item and it's values
	 */
	public void addItems() {
		Item rum = new Item("Rum", "A casket of rum.\nPirates love this stuff", 4, 30); //0
		items.add(rum);
		Item flour = new Item("Flour" , "A sack of flour", 2, 10); //1
		items.add(flour);
		Item bread = new Item("Bread", "A sack of bread" , 2, 10); //2
		items.add(bread);
		Item sugar = new Item("Sugar", "A sack of sugar" , 2, 15); //3
		items.add(sugar);
		Item gunpowder = new Item("Gunpowder", "Keg of gunpowder" , 3, 35); //4
		items.add(gunpowder);
		Item salt = new Item("Salt", "Sack of salt", 2, 15); //5
		items.add(salt);
		Item spices = new Item("Spices", "Sacks of various spices", 4, 30); //6
		items.add(spices);
		Item vegetables = new Item("Vegetables", "Gotta have a\nbalanced diet!", 2, 10); //7
		items.add(vegetables);
		Item riceCakes = new Item("Rice cakes", "Cakes of rice", 1, 6);//8
		items.add(riceCakes);
		Item lumbar = new Item("Wooden planks", "Heavy but valuable", 6, 40); //9
		items.add(lumbar);
		Item parchment = new Item("Parchment", "Lumber, but lighter", 1, 6); //10
		items.add(parchment);
		Item obsidian = new Item("Obsidian", "This stuff takes ages\nto mine", 3, 25); //11
		items.add(obsidian);
		Item fur = new Item("Fur", "Keeps you warm ", 2, 13); //12
		items.add(fur);
		Item gasJar = new Item("Jar of gas", "Hold your breath", 1, 8); //13
		items.add(gasJar);
		Item oil = new Item("Oil", "Too bad our ship\ndoesn't run on this", 3, 25	); //14
		items.add(oil);
		Item emptyBucket = new Item("A random bucket", "Why does everyone\nlove this bucket so much?", 1, 10); //15
		items.add(emptyBucket);
		Item sand = new Item("Sand barrel", "Coarse, rough, irritating\nand it gets everywhere", 2, 14); //16
		items.add(sand);
		Item blueButterflyWings = new Item("Blue butterflies", "Amazing for witches", 1, 7); //17
		items.add(blueButterflyWings);
		Item snowBerries = new Item("SnowBerries", "Great for witches", 1, 6); //18
		items.add(snowBerries);
		Item spriggonSap = new Item("SpriggonSap", "good for witches", 1, 5); //19
		items.add(spriggonSap);
		Item hothHerb = new Item("Hoth herb", "Store away from fire,\nunless you want a good time", 2, 12); //20
		items.add(hothHerb);
		Item Shrooms = new Item("Mushrooms", "Take in moderation", 1, 5); //21
		items.add(Shrooms);
		Item chicken = new Item("Cured chicken", "How many slaps do you\nthink it'd take to cook this?", 1, 5); //22
		items.add(chicken);
		Item rope = new Item("Big fish", "Poodoo", 1, 5); //23
		items.add(rope);
		Item ink = new Item("Bigger fish", "There's always a\nbigger fish", 1, 5); //24
		items.add(ink);
		// Specialty items below
		Item lCookies = new Item("Lava cookies", "A speciality of Mustafar" , 1, 8); //25
		items.add(lCookies);
		Item iceCakes = new Item("Ice cakes", "A speciality of Hoth" , 1, 8); //26
		items.add(iceCakes);
		Item sludge = new Item("Sludge", "Swampy goo that\nhas healing properties", 2, 15); //27
		items.add(sludge);
		Item frogs = new Item("Live frogs", "Don't lick em", 1, 9); //28
		items.add(frogs);
		Item mysteriousLiquid = new Item("Mysterious liquid", "Every batch is\ndifferent!", 1, 0); //29 
		items.add(mysteriousLiquid);
	}	
	
	/**
	 * Prints a string representation of the items a store sells. Adjusts their price and orders them according to the island
	 * Was used for the command line program
	 */
	public String sellableString() {
		String str = "";
		ArrayList<Item> items = new ArrayList<Item>(inventory.keySet());
		int i = 1;
		str += "*Key: \n^ Selling for more than usual\nv Selling for less than usual\n";
		for (Item item : items) {
			str += i+": "+item.getName()+", Quantity: "+inventory.get(item)+", Size: "+item.getSize()+", Price: ";
			if (containsItem(currentIsland.visitStore().getExpensiveItems(),item)) {
				str += ((int)(item.getCost()*Item.favouredMult))+"^\n";
			} else if (containsItem(currentIsland.visitStore().getCheapItems(), item)) {
				str += ((int)(item.getCost()*Item.unfavouredMult))+"v\n";
			} else {
				str += item.getCost()+"\n";
			}
			i++;
		}
		return str;
	}
	
	/**
	 * Creates a list of Items to be used when selling, based on the current island and your current inventory. Also implements any favoured or 
	 * unfavoured preference the store has towards particular items
	 * @return Item list which contains all items you can sell to the store, including their adjusted prices
	 */
	public ArrayList<Item> createSellable() {
		ArrayList<Item> items = new ArrayList<Item>(inventory.keySet());
		for(int i=0;i<items.size();i++) {
			Item item = items.get(i);
			if (containsItem(currentIsland.visitStore().getExpensiveItems(), item)) {
				items.remove(i);
				Item titem = new Item(item.getName(),item.getDescription(),item.getSize(),(int) (item.getCost()*Item.favouredMult));
				items.add(i, titem);
			} else if (containsItem(currentIsland.visitStore().getCheapItems(), item)) {
				items.remove(i);
				Item titem = new Item(item.getName(),item.getDescription(),item.getSize(),(int) (item.getCost()*Item.unfavouredMult));
				items.add(i, titem);
			}
		}
		return items;
	}
	
	/**
	 * Returns True if the item is in items
	 * @param items The items which may contain item
	 * @param item the item that will be searched for in items
	 * @return True If item is in items, else False
	 */
	public boolean containsItem(ArrayList<Item> items, Item item) {
		for (Item i: items) {
			if (i.getName() == item.getName()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Changes the value of the item Mysterious liquid. This gets called every time you enter a new island
	 */
	public void changeMLiquidValue() {
		Random r = new Random();
		int value = 0;
		for (int i = 0;i<8;i++) {
			value += r.nextInt(8) + 1;
		}
		value *= r.nextInt(2)+1;
		mysteriousLiquidValue = value;
		ArrayList<Item> nitems = new ArrayList<Item>(inventory.keySet());
		for (Item item: nitems) {
			if (item.getName() == "Mysterious liquid") {
				item.setCost(value);
			}
		}
		items.get(29).setCost(value);
	}
	
	/**
	 * Adds all the routes and assigns which islands they go between
	 */
	public void addRoutes() {
		ArrayList<Island> dathHothIslands = new ArrayList<Island>();
		dathHothIslands.add(islands.get(0)); // Dath
		dathHothIslands.add(islands.get(1));// Hoth
		Route dathHoth= new Route(10, 10, dathHothIslands);					//0
		routes.add(dathHoth);
		ArrayList<Island> dathDagIslands = new ArrayList<Island>();
		dathDagIslands.add(islands.get(0)); // Dath
		dathDagIslands.add(islands.get(4));// Dagobah
		Route dathDag= new Route(14, 10, dathDagIslands);					//1
		routes.add(dathDag);
		ArrayList<Island> dathNabIslands = new ArrayList<Island>();
		dathNabIslands.add(islands.get(0)); // Dath
		dathNabIslands.add(islands.get(2));// Nab
		Route dathNab= new Route(8, 10, dathNabIslands);					//2
		routes.add(dathNab);
		ArrayList<Island> dathMusIslands = new ArrayList<Island>();
		dathMusIslands.add(islands.get(0)); // Dath
		dathMusIslands.add(islands.get(3));// Mustafar
		Route dathMus= new Route(20, 10, dathMusIslands);					//3
		routes.add(dathMus);
		
		ArrayList<Island> hothNabIslands = new ArrayList<Island>();
		hothNabIslands.add(islands.get(1)); // Hoth
		hothNabIslands.add(islands.get(2));// Nab
		Route hothNab= new Route(14, 10, hothNabIslands);					//4
		routes.add(hothNab);
		ArrayList<Island> hothDagIslands = new ArrayList<Island>();
		hothDagIslands.add(islands.get(1)); // Hoth
		hothDagIslands.add(islands.get(4));// Dagobah
		Route hothDag= new Route(9, 10, hothDagIslands);					//5
		routes.add(hothDag);
		ArrayList<Island> hothMusIslands = new ArrayList<Island>();
		hothMusIslands.add(islands.get(1)); // Hoth
		hothMusIslands.add(islands.get(3));// Mustafar
		Route hothMus= new Route(23, 10, hothMusIslands);					//6
		routes.add(hothMus);
		
		ArrayList<Island> nabDagIslands = new ArrayList<Island>();
		nabDagIslands.add(islands.get(2)); // Naboo
		nabDagIslands.add(islands.get(4));// Dagobah
		Route nabDag= new Route(11, 10, nabDagIslands);						//7
		routes.add(nabDag);
		ArrayList<Island> nabMusIslands = new ArrayList<Island>();
		nabMusIslands.add(islands.get(2)); // Naboo
		nabMusIslands.add(islands.get(3));// Mustafar
		Route nabMus= new Route(15, 10, nabMusIslands);						//8
		routes.add(nabMus);
		
		ArrayList<Island> musDagIslands = new ArrayList<Island>();
		musDagIslands.add(islands.get(3)); // Mustafar
		musDagIslands.add(islands.get(4));// Dagobah
		Route musDag= new Route(16, 10, musDagIslands);						//9
		routes.add(musDag);
	}
	
	/**
	 * Adds the islands and assigns them each a store
	 */
	public void addIslands() {
		Island dathomir = new Island("Dathomir", stores.get(0));
		islands.add(dathomir);
		
		Island Hoth = new Island("Hoth", stores.get(1));
		islands.add(Hoth);
		
		Island naboo = new Island("Naboo", stores.get(2));
		islands.add(naboo);
		
		Island mustafar = new Island("Mustafar", stores.get(3));
		islands.add(mustafar);
		
		Island dagobah = new Island("Dagobah", stores.get(4));
		islands.add(dagobah);
	}
	
	/**
	 * Assigns a list of available routes to each island
	 */
	public void setRoutes() {
		ArrayList<Route> dathRoutes = new ArrayList<Route>();
		dathRoutes.add(routes.get(0));
		dathRoutes.add(routes.get(1));
		dathRoutes.add(routes.get(2));
		dathRoutes.add(routes.get(3));
		islands.get(0).setRoutes(dathRoutes);
		islandRoutes.add(dathRoutes);
		
		ArrayList<Route> hothRoutes = new ArrayList<Route>();
		hothRoutes.add(routes.get(0));
		hothRoutes.add(routes.get(4));
		hothRoutes.add(routes.get(5));
		hothRoutes.add(routes.get(6));
		islands.get(1).setRoutes(hothRoutes);
		islandRoutes.add(hothRoutes);
		
		ArrayList<Route> nabRoutes = new ArrayList<Route>();
		nabRoutes.add(routes.get(2));
		nabRoutes.add(routes.get(4));
		nabRoutes.add(routes.get(7));
		nabRoutes.add(routes.get(8));
		islands.get(2).setRoutes(nabRoutes);
		islandRoutes.add(nabRoutes);
		
		ArrayList<Route> musRoutes = new ArrayList<Route>();
		musRoutes.add(routes.get(3));
		musRoutes.add(routes.get(6));
		musRoutes.add(routes.get(8));
		musRoutes.add(routes.get(9));
		islands.get(3).setRoutes(musRoutes);
		islandRoutes.add(musRoutes);
		
		ArrayList<Route> dagRoutes = new ArrayList<Route>();
		dagRoutes.add(routes.get(1));
		dagRoutes.add(routes.get(5));
		dagRoutes.add(routes.get(7));
		dagRoutes.add(routes.get(9));
		islands.get(4).setRoutes(dagRoutes);
		islandRoutes.add(dagRoutes);
		
	}
	
	/**
	 * 
	 * @param ship The ship used to travel - Relevant because ship's crew is taken into equation
	 * @param route	The route that will be takes
	 * @param crewCostPerDay the cost per crew member per day
	 * @return cost to travel
	 */
	public double getTravelCost(Ship ship, Route route, double crewCostPerDay) {
		double cost = ship.getCrew() * crewCostPerDay * route.getDays();
		return cost;
	}
	
	/**
	 * Returns a 'clean' copy of the item, one with the original base price and item id
	 * @param item which has been modified
	 * @return An unmodified item, the equivalent of the inputed item
	 */
	public Item getMatchingItem(Item item) {
		for (Item titem: items) {
			if (titem.getName() == item.getName()) {
				return titem;
			}
		}
		return item; //Should never be called
	}

	/**
	 * Reduces players gold by a number cost
	 * @param cost 
	 */
	public void pay(int cost) {
		gold -= cost;
	}

	/**
	 * Reduces players gold by a number cost. This one works for double cost
	 * @param cost
	 */
	public void pay(double cost) {
		gold -= cost;
	}
	
	/**
	 * Determines whether a random encounter occurs, and if so, which encounter. 5% chance of encounter per day. equal chance to be pirate, sailors or weather encounter.
	 * @param route	is needed to get the risk from the route. We didn't end up adding extra routes so all routes have a risk of 10
	 * @return The random encounter generated. 
	 */
	public RandomEncounter determineEncounter(Route route) {

		Random random = new Random();
		for (int i=0; i < route.getDays(); i++) {
			
			int randomNumber = random.nextInt(100) + 1; // Done like this so we don't get 0
			if (randomNumber <= (route.getRisk() * 0.5)) {
				
				randomNumber = random.nextInt(100 - 1) + 1;
				if (randomNumber < 34) {
					WeatherEncounter encounter = new WeatherEncounter();
					return encounter;
				} else if (randomNumber < 67) {
					PirateEncounter encounter = new PirateEncounter(getCurrentShip());
					return encounter;
				} else {
					RescueSailors encounter = new RescueSailors();
					return encounter;
				}
				// changing the if cases above
			}
		}
		return new RandomEncounter();
		
	}
	
	/**
	 * Rolls a 6 sided dice. Produces a random number between 1 and 6
	 * @return value between 1 and 6
	 */
	public int rollD6() {
		Random random = new Random();
		int value = random.nextInt(6) + 1;
		return value;
	}
	
	/**
	 * Returns true if there is an island the player can travel to. ie There's a route.getDays() that is less than or equal to days left
	 * @param routes that we can use
	 * @return if if there is an island the player can travel to
	 */
	public boolean canTravel(ArrayList<Route> routes) { 
		
		boolean marker = false;
		int i = 0;
		while ((i < routes.size()) && (marker == false)) {
			if (routes.get(i).getDays() <= getDaysLeft()) {
				marker = true;
			}
			i++;
		}
		return marker;
	}
	
	/**
	 * Restores the ship to maximum Hp and reduces player gold by the cost to repair
	 */
	public void repairShip() {
		int damage = (getCurrentShip().getMaxHp()-getCurrentShip().getCurrentHp());
		int cost  = damage*repairCost;
		setGold(getGold() - cost);
		getCurrentShip().setCurrentHp(getCurrentShip().getMaxHp());
	}
	
	//Getters v
	/**
	 * 
	 * @return stores
	 */
	public ArrayList<Store> getStores() {
		return stores;
	}
	
	/**
	 * 
	 * @return items
	 */
	public ArrayList<Item> getItems() {
		return items;
	}
	
	/**
	 * 
	 * @return islands
	 */
	public ArrayList<Island> getIslands() {
		return islands;
	}
	
	/**
	 * Returns the routes that a correspond to a particular island
	 * @param num determines which island's routes are returned
	 * @return islandRoutes
	 */
	public ArrayList<Route> getIslandRoutes(int num) {
		return islandRoutes.get(num);
	}
	
	/**
	 * 
	 * @return daysLeft
	 */
	public int getDaysLeft() {
		return daysLeft; 
	}
	
	/**
	 * 
	 * @return inventory
	 */
	public HashMap<Item, Integer> getInventory() {
		return inventory;
	}
	
	/**
	 * 
	 * @return currentCargo
	 */
	public int getCurrentCargo() {
		return currentCargo;
	}
	
	/**
	 * 
	 * @return currentShip
	 */
	public Ship getCurrentShip() {
		return currentShip;
	}
	
	/**
	 * 
	 * @return player's ship's Hp
	 */
	public int getCurrentHp() {
		return getCurrentShip().getCurrentHp();
	}
	
	/**
	 * 
	 * @return currentIsland
	 */
	public Island getCurrentIsland() {
		return currentIsland;
	}
	
	/**
	 * 
	 * @return gold 
	 */
	public int getGold() {
		return gold;
	}
	
	/**
	 * 
	 * @return routes - A list of all routes
	 */
	public ArrayList<Route> getRoutes() {
		return routes;
	}
	
	/**
	 * 
	 * @return the cost of travel per crew per day
	 */
	public double getCostPerCrewDay() {
		return costPerCrewDay;
	}
	
	/**
	 * 
	 * @return characterName
	 */
	public String getCharacterName() {
		return characterName;
	}
	
	/**
	 * 
	 * @return upgrades
	 */
	public ArrayList<Upgrade> getUpgrades() {
		return upgrades;
	}
	
	/**
	 * 
	 * @return Gold before pirates stole it all. This way if they lose to pirates they will still have a decent score
	 */
	public int getLatestGold() {
		return latestGold;
	}
	
	/**
	 * 
	 * @return initial days selected at the start of the journey
	 */
	public int getInitDays() {
		return initDays;
	}
	
	/**
	 * 
	 * @return returns
	 */
	public String getFrom() {
		return from;
	}
	
	

	//Setters v
	
	/**
	 * sets days to the inputted tdays
	 * @param tdays 
	 */
	public void setInitDays(int tdays) {
		initDays = tdays;
	}
	
	/**
	 * sets latest gold to the inputted tgold
	 * @param tgold
	 */
	public void setLatestGold(int tgold) {
		latestGold = tgold;
	}
	
	/**
	 * Sets gold to tgold
	 * @param tgold
	 */
	public void setGold(int tgold) {
		gold = tgold;
	}
	
	/**
	 * Sets current ship to tship
	 * @param tship
	 */
	public void setCurrentShip(Ship tship) {
		currentShip = tship;
	}
	
	/**
	 * Sets daysLeft to tdays
	 * @param tdays
	 */
	public void setDays(int tdays) {
		daysLeft = tdays;
	}
	
	/**
	 * Sets characterName to tcharacterName
	 * @param tcharacterName
	 */
	public void setCharacterName(String tcharacterName) {
		characterName = tcharacterName;
	}
	
	/**
	 * Sets CurrentIsland to island
	 * @param island 
	 */
	public void setCurrentIsland(Island island) {
		currentIsland = island;
		this.changeMLiquidValue();
	}
	
	/**
	 * Sets currentShip's hp to thp
	 * @param Hp
	 */
	public void setCurrentHp(int tHp) {
		getCurrentShip().setCurrentHp(tHp);
	}

	/**
	 * Sets cargo to tcargo
	 * @param tcargo
	 */
	public void setCurrentCargo(int tcargo) {
		currentCargo = tcargo;
	}
	
	/**
	 * Sets from to tfrom
	 * @param tfrom
	 */
	public void setFrom(String tfrom) {
		from = tfrom;
	}
	
	/**
	 * Returns a string representation of each item in inventory including their quantity. Was used in the command line implementation
	 * @return a string representation of each item in inventory
	 */
	public String inventoryToString() {
		String str = "";
		if (inventory.isEmpty()) {
			str += "There are no items in your inventory\n";
			return str;
		} else {
		
			ArrayList<Item> titems = new ArrayList<Item>(inventory.keySet());
			for (Item item : titems) {
				str += item.getName()+ ": Quantity " + inventory.get(item) + ", Base Price: "+item.getCost()+", Size: "+item.getSize()+"\n";
			}
			return str;
		}
	}
	
	
	
	/**
	 * Returns true if the pirates steal amount gets satified by the player gold or the value of players items. Does remove items as they steal them.
	 * @param stealAmount The amount of gold the pirates will steal
	 * @return	Returns true if the items value is more than the pirates steal amount. In other words, If the pirates are happy with how much they steal, return true
	 */
	public boolean stealFromItems(int stealAmount) {
		if (gold >= stealAmount) {
			gold -= stealAmount;
			return true;
		} else {
			stealAmount -= gold;
			gold = 0;
		}
		int i = 0;
		ArrayList<Item> titems = new ArrayList<Item>(inventory.keySet());
		while ((stealAmount > 0) && (!inventory.isEmpty())) {
			Item item = titems.get(i);
			Item titem = getMatchingItem(item);
			if (inventory.get(titem)==1) {
				inventory.remove(titem);
				i++;
			} else {
				int count = inventory.get(titem);
				inventory.put(titem, count-1);
			}
			stealAmount -= titem.getCost();
			if (i >= items.size()) {
				return false;
			}
		}
		if (stealAmount <= 0) {
			return true;
		} else if (inventory.isEmpty()) {
			return false;
		}
		return false; //how did we get here :)
	}
	
	/**
	 * Adjusts the available route's days given the ship speed
	 * @param routes the available routes
	 * @param ship - necessary because we need the ship speed
	 */
	public void updateRouteDays(ArrayList<Route> routes, Ship ship) {
		for (int i = 0;i < 4; i++) {
			if ((routes.get(i).getDays() - ((ship.getSailSpeed() - 10) / 2)) <= 5) {
				routes.get(i).setDays(5);
			} else {
				routes.get(i).setDays(routes.get(i).getDays() - ((ship.getSailSpeed() - 10) / 2));
			}
		}
	}
	
	/**
	 * Opens the askShipGui gui
	 * @param game
	 */
	public void openAskShipGui(GameEnvironment game) {
		AskShipGui shipGui = new AskShipGui(game);
		shipGui.run(game);
	}
	
	/**
	 * Opens the DisplayIslandGui gui
	 * @param game
	 */
	public void openDisplayIsland(GameEnvironment game) {
		DisplayIslandGui islandGui = new DisplayIslandGui(game);
		islandGui.run(game);
	}
	
	/**
	 * Opens the InventoryGui gui
	 * @param game
	 */
	public void openInventoryGui(GameEnvironment game) {
		InventoryGui inventoryGui = new InventoryGui(game);
		inventoryGui.run(game);
	}
	
	/**
	 * Opens the ViewShipGui gui
	 * @param game
	 */
	public void openViewShip(GameEnvironment game) {
		ViewShipGui gui = new ViewShipGui(game);
		gui.run(game);
	}
	
	/**
	 * Opens the ConfirmationGui gui
	 * @param game
	 * @param type - needed to decide what to confirm. Eg confirm travel, confirm Inital setup
	 * @param currentRoute - Needed for confirm travel
	 */
	public void openConfirmationGui(GameEnvironment game, String type, Route currentRoute) {
		ConfirmationGui gui = new ConfirmationGui(game, type, currentRoute);
		gui.run(game, type, currentRoute);
	}
	
	/**
	 * Opens the DisplayTravelGui gui
	 * @param game
	 */
	public void openDisplayTravelGui(GameEnvironment game) {
		DisplayTravelGui gui = new DisplayTravelGui(game);
		gui.run(game);
	}
	
	/**
	 * Opens the FightOrFlightGui gui with the variables from encounter
	 * @param game
	 * @param encounter 
	 */
	public void openPirateEncounterGui(GameEnvironment game, PirateEncounter encounter) {
		FightOrFlightGui gui = new FightOrFlightGui(game, encounter);
		gui.run(game, encounter);
	}
	
	/**
	 * Opens the NonPirateEncounterGui gui with the variables from encounter
	 * @param game
	 * @param encounter
	 */
	public void openNonPirateEncounterGui(GameEnvironment game, RandomEncounter encounter) {
		NonPirateEncounterGui gui = new NonPirateEncounterGui(game, encounter);
		gui.run(game, encounter);
	}
	
	/**
	 * Opens the GameOverGui gui with the type being how game over was reached. eg pirates, choice by ending game
	 * @param game
	 * @param type
	 */
	public void openGameOverGui(GameEnvironment game, String type) {
		GameOverGui gui = new GameOverGui(game, type);
		gui.run(game, type);
	}
	
	/**
	 * Opens the FleeGui gui with the variables from encounter
	 * @param game
	 * @param encounter
	 */
	public void openFleeGui(GameEnvironment game, PirateEncounter encounter) {
		FleeGui gui = new FleeGui(game, encounter);
		gui.run(game, encounter);
	}
	
	/**
	 * Opens the FightGui gui with the variables from encounter
	 * @param game
	 * @param encounter
	 */
	public void openFightGui(GameEnvironment game, PirateEncounter encounter) {
		FightGui gui = new FightGui(game, encounter);
		gui.run(game, encounter);
	}
	
	/**
	 * Opens the DisplayStoreGui gui
	 * @param game
	 */
	public void openDisplayStore(GameEnvironment game) {
		DisplayStoreGui gui = new DisplayStoreGui(game);
		gui.run(game);
	}
	
	/**
	 * Opens the DisplaBuyUpgradesGuiyStoreGui gui
	 * @param game
	 */
	public void openBuyUpgradesGui(GameEnvironment game) {
		BuyUpgradesGui gui = new BuyUpgradesGui(game);
		gui.run(game);
	}
	
	/**
	 * Opens the DisplayBuyGui gui
	 * @param game
	 */
	public void openDisplayBuyGui(GameEnvironment game) {
		DisplayBuyGui gui = new DisplayBuyGui(game);
		gui.run(game);
	}
	
	/**
	 * Opens the DisplaySellGui gui
	 * @param game
	 */
	public void openDisplaySellGui(GameEnvironment game) {
		DisplaySellGui gui = new DisplaySellGui(game);
		gui.run(game);
	}
	
	/**
	 * Creates all the instances of upgrades, items, ships, stores, islands, routes, assigns those routes to islands, and sets the current island to Dathomir
	 */
	public void initialiseGame() {
		
		this.addUpgrades();
		this.addItems();
		this.addShips();
		this.addStores();
		this.addIslands();
		this.addRoutes();
		this.setRoutes();
		this.changeMLiquidValue();
		currentIsland = islands.get(0);
	}
}
