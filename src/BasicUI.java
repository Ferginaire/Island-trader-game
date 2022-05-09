import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This class is responsible for reading and running the game when it was in the command line stage. 
 * It contains a lot of the logic used to determine what happens in the game from the players choices.
 * This class also contains main.
 * @author Fergus and Oli
 *
 */
public class BasicUI {
	
	static Scanner sc = new Scanner(System.in);
	
	/**
	 * prints an invalid input message
	 */
	public static void invalidInput() {
		System.out.println("Sorry invalid input, Try again");
	}
	
	/**
	 * Prompts user to enter their name, returns the entered string, prompts an invalid input if not a string
	 * @return String name entered by the user
	 */
	public static String askName() {
		System.out.println("What is your name?\n");
		try {
			String name = sc.nextLine();
			return name;
		} catch (Exception e) {
			sc.next();
			invalidInput();
			String name = askName();
			return name;
		}
	}
	
	/**
	 * Prompts user to enter the amount of days to play, int between 30 and 50, prompts an error if not
	 * @param game the game object
	 * @return int days entered
	 */
	public static int askDays(GameEnvironment game) {
		System.out.println("How many days would you like to play? [30-50]\n");
		try {
			int days = sc.nextInt();
			if (days >= 30 && days <= 50) {
				game.setInitDays(days);
				return days;
			} else {
				invalidInput();
				days = askDays(game);
				return days;
			}
		} catch (Exception e) {
			sc.next();
			invalidInput();
			int days = askDays(game);
			return days;
		} 
	}
	
	/**
	 * Prompts user to pick their desired ship, returns a ship object equivalent to their choice 
	 * @param ships, the possible ships
	 * @return Ship, the ship the user chose
	 */
	public static Ship askShip(ArrayList<Ship> ships) {
		System.out.println("Which ship would you like?");
		System.out.println("1: "+ships.get(0).toBasicString());
		System.out.println("2: "+ships.get(1).toBasicString());
		System.out.println("3: "+ships.get(2).toBasicString());
		System.out.println("4: "+ships.get(3).toBasicString()+"\n");
		try {
			int i = sc.nextInt();
			if (i >= 1 && i <= 4) {
				return ships.get(i-1);
			} else {
				invalidInput();
				Ship ship = askShip(ships);
				return ship;
			}
		} catch (Exception e) {
			sc.next();
			invalidInput();
			Ship ship = askShip(ships);
			return ship;
		} 
	}
	
	/**
	 * Prints the inputed gold amount in current gold format
	 * @param gold player's gold
	 */
	public static void viewGold(int gold) {
		System.out.println("You have "+gold+" gold.\n");
	}
	
	/**
	 * Prints out the current inventory, inputed as a string along with current cargo space
	 * @param inventory players inventory
	 * @param maxCargo player's ship's max cargo
	 * @param currentCargo player's current cargo
	 */
	public static void viewInventory(String inventory, int maxCargo, int currentCargo) {
		System.out.println("Carrying Capacity: "+currentCargo+"/"+maxCargo);
		
		System.out.println(inventory);
	}
	
	/**
	 * Prints out your ships current variables
	 * @param ship player's ship 
	 * @param Hp player's hp
	 */
	public static void viewCurrentShip(Ship ship, int Hp) {
		System.out.println(ship.toFullString(Hp));
	}
	
	/**
	 * Displays repair UI, prompts to choose to spend gold to repair ship
	 * @param game the game environment object
	 */
	public static void displayRepair(GameEnvironment game) {
		int damage = (game.getCurrentShip().getMaxHp()-game.getCurrentHp());
		int cost  = damage*GameEnvironment.repairCost;
		if (damage == 0) {
			System.out.println("Nothing to repair");
		} else if (cost > game.getGold()) {
			System.out.println("You need "+cost+" gold to repair, please sell some items\n");
		} else {
			System.out.println("You repaired "+damage+" damage\nThis cost you "+cost+" Gold\n");
			game.repairShip();
		}
	}
	
	/**
	 * Displays Island UI for the current Island. prompts user to enter an int for going to a different UI
	 * @param game the game environment object
	 */
	public static void displayIsland(GameEnvironment game) {
		String txt = "You are on "+game.getCurrentIsland().getName()+" island, you have "+game.getDaysLeft()+" days left. What would you like to do?\n";
		System.out.println(txt);
		String options = "1: View Gold\n2: View Inventory\n3: View Ship\n4: Repair Ship\n5: Go to shop\n6: Travel\n7: End journey";
		System.out.println(options);
		try {
			int i = sc.nextInt();
			if (i >= 1 && i <= 7) {
				switch (i) {
					case 1: 
						viewGold(game.gold);
						displayIsland(game);
					case 2:
						viewInventory(game.inventoryToString(), game.getCurrentShip().getCargoSize(), game.getCurrentCargo());
						displayIsland(game);
					case 3:
						viewCurrentShip(game.getCurrentShip(), game.getCurrentHp());
						displayIsland(game);
					case 4:
						displayRepair(game);
						displayIsland(game);
					case 5:
						displayStore(game);
					case 6:
						displayTravel(game);
					case 7:
						System.out.println("Are you sure you want to end the game?\n1: Yes\n2: No");
						i = sc.nextInt();
						if (i == 1) {
						game.setLatestGold(game.getGold());
						displayGameOver(game);
						} else if (i == 2) {
							displayIsland(game);
						} else {
							invalidInput();
							displayIsland(game);
						}
				}
			} else {
				invalidInput();
				displayIsland(game);
			}
		} catch (Exception e) {
			sc.next();
			invalidInput();
			displayIsland(game);
		} 
	}
	
	/**
	 * Displays store UI prompting user to buy sell or upgrade, or view some stats
	 * @param game the game environment object
	 */
	public static void displayStore(GameEnvironment game) {
		Island island = game.getCurrentIsland();
		String txt = "Welcome to the "+island.getName()+" island store. What would you like to do?\n";
		System.out.println(txt);
		String options = "1: View Gold\n2: View Inventory\n3: Upgrade Ship\n4: Buy Items\n5: Sell Items\n0: Go Back\n";
		System.out.println(options);
		try {
			int i = sc.nextInt();
			if (i >= 0 && i <= 5) {
				switch (i) {
					case 1: 
						viewGold(game.gold);
						displayStore(game);
						break;
					case 2:
						viewInventory(game.inventoryToString(), game.getCurrentShip().getCargoSize(), game.getCurrentCargo());
						displayStore(game);
						break;
					case 3:
						displayUpgrades(game);
						break;
					case 4:
						displayBuy(game);
						break;
					case 5:
						displaySell(game);
						break;
					case 0:
						displayIsland(game);
						break;
				}
			} else {
				invalidInput();
				displayStore(game);
			}
		} catch (Exception e) {
			sc.next();
			invalidInput();
			displayStore(game); 
		} 
	}
	
	/**
	 * Displays the shop section where user can buy goods
	 * @param game
	 */
	private static void displayBuy(GameEnvironment game) {
		Store store = game.getCurrentIsland().visitStore();
		ArrayList<Item> items = new ArrayList<Item>();
		items = store.getBuyableItems();
		String txt = "Hello "+game.getCharacterName()+". What would you like to buy?\n";
		System.out.println(txt);
		System.out.println("0: Go Back");
		System.out.println(store.buyableItemsToString());
		try {
			int i = sc.nextInt();
			if (i == 0) {
				displayIsland(game);
			} else if (i >= 1 && i <= items.size()) {
				Item item = items.get(i-1);
				if (game.getCurrentCargo()+item.getSize() > game.getCurrentShip().getCargoSize()) {
					System.out.println("Sorry, not enough cargo space");
					displayBuy(game);
				}
				if (confirmPurchase(game.getGold(),item.getCost())) {
					Item titem = game.getMatchingItem(item);
					if (game.getInventory().containsKey(titem)) {
						int count = game.getInventory().get(titem);
						game.getInventory().put(titem, count+1);
					} else {
						game.getInventory().put(titem, 1);
					}
					game.setCurrentCargo(game.getCurrentCargo()+item.getSize());
					game.pay(item.getCost());
					displayBuy(game);
				} else {
					displayBuy(game);
				}
			} else {
				invalidInput();
				displayBuy(game);
			}
		} catch (Exception e) {
			sc.next();
			invalidInput();
			displayBuy(game); 
		}
	}
	
	/**
	 * Display sell section where user can sell goods they have in their inventory
	 * @param game
	 */
	private static void displaySell(GameEnvironment game) {
		ArrayList<Item> items = new ArrayList<Item>();
		//Show gold
		String txt = "Hello "+game.getCharacterName()+". What would you like to sell?\n";
		items.addAll(game.createSellable());
		System.out.println(txt);
		System.out.println("0: Go Back");
		System.out.println(game.sellableString());
		try {
			int i = sc.nextInt();
			if (i == 0) {
				displayStore(game);
			} else if (i >= 1 && i <= items.size()) {
				Item item = items.get(i-1);
				Item titem = game.getMatchingItem(item);
				if (game.getInventory().get(titem)==1) {
					game.getInventory().remove(titem);
				} else {
					int count = game.getInventory().get(titem);
					game.getInventory().put(titem, count-1);
				}
				game.setCurrentCargo(game.getCurrentCargo()-item.getSize());
				game.pay(item.getCost()*-1);
				displaySell(game);
			} else {
				invalidInput();
				displaySell(game);
			}
		} catch (Exception e) {
			sc.next();
			invalidInput();
			displaySell(game);
		}
	}
	
	/**
	 * Prompts user to confirm a purchase, returns true if accepted, else false
	 * @param currentGold player's current gold
	 * @param cost of purchase
	 * @return true on purchased confirmed
	 */
	public static boolean confirmPurchase(int currentGold, int cost) {
		if (currentGold >= cost) {
			System.out.println("Confirm Purchace?\n1: Yes\n2: No");
			int choice = sc.nextInt();
			if (choice == 1) {
				return true;
			} else {
				return false;
			}
		} else {
			System.out.println("Sorry, not enough gold");
			return false;
		}
	}
	
	/**
	 * Displays upgrade UI, prompting user of potential upgrades 
	 * @param game the game environment object
	 */
	public static void displayUpgrades(GameEnvironment game) {
		ArrayList<Upgrade> upgrades = game.getUpgrades(); 
		System.out.println("Which upgrade would you like?");
		System.out.println("1: "+upgrades.get(0).toString());
		System.out.println("2: "+upgrades.get(1).toString());
		System.out.println("3: "+upgrades.get(2).toString());
		System.out.println("4: "+upgrades.get(3).toString());
		System.out.println("5: Go Back");
		try {
			int i = sc.nextInt();
			if (i >= 1 && i <= 4) {
				if (confirmPurchase(game.getGold(),upgrades.get(i-1).getCost())) {
					game.getCurrentShip().addUpgrade(upgrades.get(i-1), game);
					game.pay(upgrades.get(i-1).getCost());
					displayUpgrades(game);
				} else {
					displayUpgrades(game);
				}
			} else if (i == 5) {
				displayStore(game);
			} else {
				invalidInput();
				displayUpgrades(game);
			}
		} catch (Exception e) {
			sc.next();
			invalidInput();
			displayStore(game);
		}
	}
	
	/**
	 * Displays Travel UI prompting user to select a route to take to another island
	 * @param game the game environment object
	 */
	public static void displayTravel(GameEnvironment game) {
		
		if (game.getCurrentShip().getCurrentHp() == game.getCurrentShip().getMaxHp()) {
			ArrayList<Route> routes = game.getCurrentIsland().getRoutes();
			System.out.println("Where would you like to travel? You have " + game.getDaysLeft() + " days left\n");
			game.updateRouteDays(routes, game.getCurrentShip());
			
			System.out.println("1: "+routes.get(0).toString(game.getCurrentIsland()) + " and cost you " + (int)game.getTravelCost(game.currentShip, routes.get(0), game.getCostPerCrewDay()) + " gold.");
			System.out.println("2: "+routes.get(1).toString(game.getCurrentIsland()) + " and cost you " + (int)game.getTravelCost(game.currentShip, routes.get(1), game.getCostPerCrewDay()) + " gold.");
			System.out.println("3: "+routes.get(2).toString(game.getCurrentIsland()) + " and cost you " + (int)game.getTravelCost(game.currentShip, routes.get(2), game.getCostPerCrewDay()) + " gold.");
			System.out.println("4: "+routes.get(3).toString(game.getCurrentIsland()) + " and cost you " + (int)game.getTravelCost(game.currentShip, routes.get(3), game.getCostPerCrewDay()) + " gold.");
			System.out.println("0: Go Back");
			
			
			int dest;
			int cur;
			try {
				int i = sc.nextInt();
				if (i >= 1 && i <= 4) {
					game.setLatestGold(game.getGold());
					Route currentRoute = routes.get(i-1);
					if (game.getTravelCost(game.currentShip, routes.get(i-1), game.getCostPerCrewDay()) > game.getGold()) {// Check whether they got enough gold to pay crew
						System.out.println("You don't have enough money to pay your crew for a trip that long");
						displayTravel(game);
					} else if (routes.get(i-1).getDays() > game.getDaysLeft()) { //Check whether they have the days remaining
						System.out.println("There aren't enough days remaining to make it to this Island");
						displayTravel(game);
					} else {
						
						if (currentRoute.getRouteIslands().get(0) == game.getCurrentIsland()) {
							dest = 1;
							cur = 0;
						} else {
						dest = 0;
						cur = 1;
						}
						System.out.println("The voyage from " + currentRoute.getRouteIslands().get(cur).getName() + " to " + currentRoute.getRouteIslands().get(dest).getName() + " will cost " + (int)game.getTravelCost(game.currentShip, routes.get(i-1), game.getCostPerCrewDay()) + " gold.");
						System.out.println("Set sail?");
						System.out.println("0: No");
						System.out.println("1: Yes");
						int choice = sc.nextInt();
						if (choice == 1) {
							game.setCurrentIsland(currentRoute.getRouteIslands().get(dest));
							game.pay(game.getTravelCost(game.currentShip, routes.get(i-1), game.getCostPerCrewDay())); // Dock money
							game.setDays(game.getDaysLeft() - (routes.get(i-1).getDays())); // Dock days

							RandomEncounter encounter = game.determineEncounter(currentRoute);
							if (encounter instanceof PirateEncounter) {
								displayPirates(game, (PirateEncounter)encounter);
							} else if (encounter instanceof WeatherEncounter) {
								System.out.println(encounter.getDescription());
								game.setCurrentHp(game.getCurrentHp() - ((WeatherEncounter)encounter).getDamage());
								game.setDays(game.getDaysLeft() - ((WeatherEncounter)encounter).getDelay());
							} else if (encounter instanceof RescueSailors) { // Rescue sailors encounter
								System.out.println(((RescueSailors)encounter).getDescription());
								game.setGold(game.getGold() + ((RescueSailors)encounter).getReward());
							}
							System.out.println("You travelled to " + currentRoute.getRouteIslands().get(dest).getName());
							displayIsland(game);
						} else if (choice == 0) {
							displayTravel(game);
						} else {
							invalidInput();
							displayTravel(game);
						}
					}
				} else if (i == 0) {
					displayIsland(game);
				}
			} catch (Exception e) {
				sc.next();
				invalidInput();
				displayTravel(game);
			}
		} else {
			System.out.println("Ship must be repaired before you can set sail.");
			displayIsland(game);
		}
		displayIsland(game);
	}
	
	/**
	 * Displays a pirate encounter, selecting between fleeing and fighting
	 * @param game the game environment object
	 * @param encounter of pirates
	 */
	public static void displayPirates(GameEnvironment game, PirateEncounter encounter) {
		
		System.out.println("Pirates are attacking!");
		System.out.println("What do you want to do?");
		System.out.println("0: Attempt to flee. If they catch up to you, your ship will take damage.");
		System.out.println("1: Attempt to fight. If you lose the fight, you may be made to walk the plank. \nHowever if you win then you will be able to plunder some booty");
		//failed flee is just damage unless you fail by more than 6
		
		try {
			int i = sc.nextInt();
			if (i == 0) {
				displayFlee(game, encounter);
			} else if (i == 1) {
				displayFight(game, encounter);
			} else {
				invalidInput();
				displayPirates(game, encounter);
			}
		} catch (Exception e) {
			sc.next();
			invalidInput();
			displayPirates(game, encounter);
		}	
	}
	
	/**
	 * Displays the mini-game of fleeing from pirates, where each side rolls a die multiple times and shows the result
	 * @param game  the game environment object
	 * @param encounter of pirates
	 */
	public static void displayFlee(GameEnvironment game, PirateEncounter encounter) {
		System.out.println("You and the pirates will both roll four 6 sided dice but you get to add your ship's sailSpeed modifier.\nWhoever rolls the highest is faster");
		System.out.println("1: Continue");
		ArrayList<Integer> playerRolls = new ArrayList<Integer>();
		ArrayList<Integer> pirateRolls = new ArrayList<Integer>();
		
		int i = sc.nextInt();
		if (i == 1) {
			System.out.println("Your rolls:");
			playerRolls.add(game.rollD6());
			playerRolls.add(game.rollD6());
			playerRolls.add(game.rollD6());
			playerRolls.add(game.rollD6());
			int speedMod = (game.getCurrentShip().getSailSpeed() - 10) / 2;
			System.out.println(playerRolls.get(0) + " " + playerRolls.get(1) + " " + playerRolls.get(2) + " " + playerRolls.get(3) + " + your ship speed modifier " + speedMod);
			int playerSum = playerRolls.get(0) + playerRolls.get(1) + playerRolls.get(2) + playerRolls.get(3) + speedMod; 
			System.out.println("Your total is " + playerSum);
			System.out.println("");
			System.out.println("Pirate's rolls:");
			pirateRolls.add(game.rollD6());
			pirateRolls.add(game.rollD6());
			pirateRolls.add(game.rollD6());
			pirateRolls.add(game.rollD6());
			System.out.println(pirateRolls.get(0) + " " + pirateRolls.get(1) + " " + pirateRolls.get(2) + " " + pirateRolls.get(3));
			int pirateSum = pirateRolls.get(0) + pirateRolls.get(1) + pirateRolls.get(2) + pirateRolls.get(3);
			System.out.println("The pirates' total is " + pirateSum);
			System.out.println("");
			if (playerSum >= pirateSum) {
				System.out.println("The pirates couldn't gain on you! You got away unharmed!");
				
			} else if ((pirateSum - playerSum) <= 6) { // If player lost by 6 or less
				System.out.println("The pirates caught up to you! They damaged your ship but you still managed to escape!");
				game.getCurrentShip().takeDamage(encounter.getDamage());
			} else { // If player lost by more than 6
				System.out.println("The pirates caught up to you and boarded your ship!"); 
				if (encounter.getStealAmount() > game.getGold()) { // If they take all your money and still want more
					if (game.stealFromItems(encounter.getStealAmount())) { // If they take your money and are satisfied with your items
						System.out.println("The pirates stole all your money and some of your items! They were happy with their plunder and let you sail away");
						displayIsland(game);
					} else { // They take your money and aren't satisfied with your items
						System.out.println("The pirates weren't satisfied with your booty and forced you and your crew to walk the plank!");
						displayGameOver(game);
					}
				} else { // They take some money and are happy with that
					System.out.println("The pirates were satisfied with the amount of gold you had to offer. They let you leave with your items and your lives.");
					game.setGold(game.getGold() - encounter.getStealAmount());
					displayIsland(game);
				}
				
			}
		} else {
			invalidInput();
			displayFlee(game, encounter);
		}
	}
	
	/**
	 * Displays the mini-game of fighting pirates, where each side rolls a die multiple times and shows the result
	 * @param game the game environment object
	 * @param encounter of pirates
	 */
	public static void displayFight(GameEnvironment game, PirateEncounter encounter) {
		System.out.println("You and the pirates will both roll four 6 sided dice but you get to add your ship's resistance modifier.\nWhoever rolls the highest is wins the fight!");
		System.out.println("1: Continue");
		ArrayList<Integer> playerRolls = new ArrayList<Integer>();
		ArrayList<Integer> pirateRolls = new ArrayList<Integer>();
		int i = sc.nextInt();
		if (i == 1) {
			System.out.println("Your rolls:");
			playerRolls.add(game.rollD6());
			playerRolls.add(game.rollD6());
			playerRolls.add(game.rollD6());
			playerRolls.add(game.rollD6());
			int playerResMod = (game.getCurrentShip().getResistance() - 10) / 2;
			System.out.println(playerRolls.get(0) + " " + playerRolls.get(1) + " " + playerRolls.get(2) + " " + playerRolls.get(3) + " + your resistance modifier " + playerResMod);
			int playerSum = playerRolls.get(0) + playerRolls.get(1) + playerRolls.get(2) + playerRolls.get(3) + playerResMod; 
			System.out.println("Your total is " + playerSum);
			System.out.println("");
			System.out.println("Pirate's rolls:");
			pirateRolls.add(game.rollD6());
			pirateRolls.add(game.rollD6());
			pirateRolls.add(game.rollD6());
			pirateRolls.add(game.rollD6());
			int pirateResMod = (encounter.getResistance() - 10) / 2;
			System.out.println(pirateRolls.get(0) + " " + pirateRolls.get(1) + " " + pirateRolls.get(2) + " " + pirateRolls.get(3) + " + the pirates' resistance modifier " + pirateResMod);
			int pirateSum = pirateRolls.get(0) + pirateRolls.get(1) + pirateRolls.get(2) + pirateRolls.get(3) + pirateResMod;
			System.out.println("The pirates' total is " + pirateSum);
			System.out.println("");
			if (playerSum > pirateSum) {
				System.out.println("You won the fight! You got " + encounter.getStealAmount() + " gold and you and your crew got away unharmed!");
				game.setGold(game.getGold() + encounter.getStealAmount());
			} else if (playerSum < pirateSum) {
				System.out.println("The pirates won and boarded your ship!"); 
				if (encounter.getStealAmount() > game.getGold()) { // If they take all your money and still want more
					if (game.stealFromItems((encounter.getStealAmount()))) { // If they take your money and are satisfied with your items
						System.out.println("The pirates stole all your money and some of your items! They were happy with their plunder and let you sail away");
						displayIsland(game);
					} else { // They take your money and aren't satisfied with your items
						System.out.println("The pirates weren't satisfied with your booty and forced you and your crew to walk the plank!");
						displayGameOver(game);
					}
				} else { // They take some money and are happy with that
					System.out.println("The pirates were satisfied with the amount of gold you had to offer. They let you leave with your items and your lives.\nYou lost " + encounter.getStealAmount() + " gold.");
					game.setGold(game.getGold() - encounter.getStealAmount());
					displayIsland(game);
				}
			} else {// Rolled the same
				System.out.println("You and the pirates are an even match! You agree to part ways maturely to avoid needless bloodshed.");
				displayIsland(game); // Could give player the chance to let them go or risk another attack teehee
			}
		}
		
		
	}
	
	/**
	 * Displays the final game over screen, where it gives the player a score based on how they preformed
	 * @param game the game environment object
	 */
	public static void displayGameOver(GameEnvironment game) {
		System.out.println("Your journey has come to an end.");
		System.out.println("You were able to amass " + game.getLatestGold());
		int score = (5 * game.getGold()) + 10 * ((game.getInitDays() - game.getDaysLeft()) * 10);
		System.out.println("Your final score is " + score);
		System.exit(1);
	}
	
	/**
	 * Main
	 * @param args Main function parameter
	 */
	public static void main(String[] args) {		
		GameEnvironment game = new GameEnvironment();
		game.initialiseGame();
		

		//game.setCharacterName(askName());
		//game.setDays(askDays(game));
		game.setCurrentIsland(game.getIslands().get(0));
		SetupGameGui askNameWindow = new SetupGameGui(game);
		askNameWindow.run(game);
//		game.setCurrentShip(askShip(game.getShips()));	
//		game.setCharacterName("Dev");
//		game.setDays(50);
//		game.setCurrentShip(game.getShips().get(0));
//		game.setCurrentHp(game.getCurrentShip().getMaxHp());
//		displayIsland(game);

	}
}
