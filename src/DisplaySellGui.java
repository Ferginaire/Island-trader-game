import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * This class creates a Gui that displays all the Items available to sell from this island's store.
 * It has four buttons with corresponding information boxes for cheap items,
 * four buttons with corresponding information boxes for expensive items,
 * and four buttons with corresponding information boxes for other items that can cycle up to display up to 5 pages worth.
 * Pressing these buttons will sell an item if the player still has some left
 * @author Fergus and Oliver
 *
 */
public class DisplaySellGui {

	private JFrame frame;
	private JTextArea boxCheap1;
	private JTextArea boxCheap2;
	private JTextArea boxCheap3;
	private JTextArea boxCheap4;
	private JTextArea boxExpenny1;
	private JTextArea boxExpenny2;
	private JTextArea boxExpenny3;
	private JTextArea boxOther1;
	private JTextArea boxOther2;
	private JTextArea boxOther3;
	private JTextArea boxOther4;
	private ArrayList<Item> cheapItems = new ArrayList<Item>();
	private ArrayList<Item> expennyItems = new ArrayList<Item>();
	private ArrayList<Item> otherItems = new ArrayList<Item>();
	private int page = 1;
	private int cheapItemSize;
	private int expennyItemSize;
	private int otherItemSize;
	private int itemSize;
	private boolean intialRunMarker = true;
	/**
	 * Launch the application.
	 * @param game
	 */
	public void run(GameEnvironment game) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplaySellGui window = new DisplaySellGui(game);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param game
	 */
	public DisplaySellGui(GameEnvironment game) {
		initialize(game);
	}

	/**
	 * Gets the cheap items from the current island's store and returns a new list of those items with adjusted prices
	 * @param game
	 * @return a new list of island's cheap items with decreased prices
	 */
	private ArrayList<Item> getCheapFromInvent(GameEnvironment game) {
		ArrayList<Item> cheapFromInvent = new ArrayList<Item>();
		for (Item titem : game.getCurrentIsland().getStore().getCheapItems()) {
			if (game.getInventory().get(titem) != null) {
				cheapFromInvent.add(titem);
			}
			
		}
		ArrayList<Item> adjusted = new ArrayList<Item>();
		for (Item item: cheapFromInvent) {
			Item titem = new Item(item.getName(),item.getDescription(),item.getSize(),(int) (item.getCost()*Item.unfavouredMult));
			adjusted.add(titem);
		}
		return adjusted;
	}
	
	/**
	 * Gets the expensive items from the current island's store and returns a new list of those items with adjusted prices
	 * @param game
	 * @return a new list of the island's expensive items with increased prices
	 */
	private ArrayList<Item> getExpennyFromInvent(GameEnvironment game) {
		ArrayList<Item> expennyFromInvent = new ArrayList<Item>();
		for (Item titem : game.getCurrentIsland().getStore().getExpensiveItems()) {
			if (game.getInventory().get(titem) != null) {
				expennyFromInvent.add(titem);
			}
		}
		ArrayList<Item> adjusted = new ArrayList<Item>();
		for (Item item: expennyFromInvent) {
			Item titem = new Item(item.getName(),item.getDescription(),item.getSize(),(int) (item.getCost()*Item.favouredMult));
			adjusted.add(titem);
		}
		return adjusted;
	}
	
	/**
	 * Gets the items which price doesn't need to change from the current island's store and returns a new list of those items
	 * @param game
	 * @return a new list of items
	 */
	private ArrayList<Item> getOtherItemsFromInvent(GameEnvironment game) {
		ArrayList<Item> otherItemsFromInvent = new ArrayList<Item>();
		for (Item titem : game.createSellable()) {
			if (game.getInventory().get(titem) != null) {
				otherItemsFromInvent.add(titem);
			}
			
		}
		//otherItemsFromInvent.add(game.getItems().get(29));
		return otherItemsFromInvent;
	}
	

	/**
	 * Sells item if it's in player's inventory. Displays a message confirming the sell
	 * @param item to sell
	 * @param game
	 * @param reactiveLabel - To display confirmation or 'You don't have any more of those'
	 * @param lblGold gold label to be updated after purchase
	 * @param lblCargo - cargo label to be updated after the purchase
	 */
	private void sellItem(Item item, GameEnvironment game, JLabel reactiveLabel, JLabel lblGold, JLabel lblCargo) {
		
		Item titem = game.getMatchingItem(item);
		
		if (game.getInventory().containsKey(titem)) {
			if (game.getInventory().get(titem) != 0) {
				int count = game.getInventory().get(titem);
				if (count == 1) {
					game.getInventory().remove(titem);
					game.setGold(game.getGold() + titem.getCost());
				} else {
					game.getInventory().put(titem, count-1);
					game.setGold(game.getGold() + titem.getCost());
				}
			}
			game.setCurrentCargo(game.getCurrentCargo() - item.getSize());
			reactiveLabel.setText("You sold " + item.getName());
			lblGold.setText("Gold: " + game.getGold());
			lblCargo.setText("Cargo: " + game.getCurrentCargo() + "/" + game.getCurrentShip().getCargoSize());
		} else {
			reactiveLabel.setText("You don't have any more of those");
		}
		
	}
	/**
	 * This function refreshes the display so that the text boxes show the correct quantity of items after each purchase
	 * @param page
	 * @param items
	 * @param btnOther1
	 * @param btnOther2
	 * @param btnOther3
	 * @param btnOther4
	 * @param game
	 */
	private void updateOtherDisplay(int page, ArrayList<Item> items, JButton btnOther1, JButton btnOther2, JButton btnOther3, JButton btnOther4, GameEnvironment game) {
		itemSize = items.size();
		if (page == 1) {
			if (itemSize >= 1) {
				btnOther1.setText(items.get(0).getName());
				boxOther1.setText(items.get(0).getDescription() + "\nCost: "+items.get(0).getCost() +"      Size: "+ items.get(0).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(items.get(0))));
			}
			if (itemSize >= 2) {
				btnOther2.setText(items.get(1).getName());
				boxOther2.setText(items.get(1).getDescription() + "\nCost: "+items.get(1).getCost() +"      Size: "+ items.get(1).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(items.get(1))));
			}
			if (itemSize >= 3) {
				btnOther3.setText(items.get(2).getName());
				boxOther3.setText(items.get(2).getDescription() + "\nCost: "+items.get(2).getCost() +"      Size: "+ items.get(2).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(items.get(2))));
			}
			if (itemSize >= 4) {
				btnOther4.setText(items.get(3).getName());
				boxOther4.setText(items.get(3).getDescription() + "\nCost: "+items.get(3).getCost() +"      Size: "+ items.get(3).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(items.get(3))));
			}
		} else if (page == 2) {
			if (itemSize >= 5) {
				btnOther1.setText(items.get(4).getName());
				boxOther1.setText(items.get(4).getDescription() + "\nCost: "+items.get(4).getCost() +"      Size: "+ items.get(4).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(items.get(4))));
			} 
			if (itemSize >= 6) {
				btnOther2.setText(items.get(5).getName());
				boxOther2.setText(items.get(5).getDescription() + "\nCost: "+items.get(5).getCost() +"      Size: "+ items.get(5).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(items.get(5))));
			} else {
				btnOther2.setText("");
				boxOther2.setText("");
			}
			if (itemSize >= 7) {
				btnOther3.setText(items.get(6).getName());
				boxOther3.setText(items.get(6).getDescription() + "\nCost: "+items.get(6).getCost() +"      Size: "+ items.get(6).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(items.get(6))));
			} else {
				btnOther3.setText("");
				boxOther3.setText("");
			}
			if (itemSize >= 8) {
				btnOther4.setText(items.get(7).getName());
				boxOther4.setText(items.get(7).getDescription() + "\nCost: "+items.get(7).getCost() +"      Size: "+ items.get(7).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(items.get(7))));
			} else {
				btnOther4.setText("");
				boxOther4.setText("");
			}
		} else if (page == 3) {
			if (itemSize >= 9) {
				btnOther1.setText(items.get(8).getName());
				boxOther1.setText(items.get(8).getDescription() + "\nCost: "+items.get(8).getCost() +"      Size: "+ items.get(8).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(items.get(8))));
			} else {
				btnOther1.setText("");
				boxOther1.setText("");
			}
			if (itemSize >= 10) {
				btnOther2.setText(items.get(9).getName());
				boxOther2.setText(items.get(9).getDescription() + "\nCost: "+items.get(9).getCost() +"      Size: "+ items.get(9).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(items.get(9))));
			} else {
				btnOther2.setText("");
				boxOther2.setText("");
			}
			if (itemSize >= 11) {
				btnOther3.setText(items.get(10).getName());
				boxOther3.setText(items.get(10).getDescription() + "\nCost: "+items.get(10).getCost() +"      Size: "+ items.get(10).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(items.get(10))));
			} else {
				btnOther3.setText("");
				boxOther3.setText("");
			}
			if (itemSize >= 12) {
				btnOther4.setText(items.get(11).getName());
				boxOther4.setText(items.get(11).getDescription() + "\nCost: "+items.get(11).getCost() +"      Size: "+ items.get(11).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(items.get(11))));
			} else {
				btnOther4.setText("");
				boxOther4.setText("");
			}
		} else if (page == 4) {
			if (itemSize >= 13) {
				btnOther1.setText(items.get(12).getName());
				boxOther1.setText(items.get(12).getDescription() + "\nCost: "+items.get(12).getCost() +"      Size: "+ items.get(12).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(items.get(12))));
			}
			if (itemSize >= 14) {
				btnOther2.setText(items.get(13).getName());
				boxOther2.setText(items.get(13).getDescription() + "\nCost: "+items.get(13).getCost() +"      Size: "+ items.get(13).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(items.get(13))));
			} else {
				btnOther2.setText("");
				boxOther2.setText("");
			}
			if (itemSize >= 15) {
				btnOther3.setText(items.get(14).getName());
				boxOther3.setText(items.get(14).getDescription() + "\nCost: "+items.get(14).getCost() +"      Size: "+ items.get(14).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(items.get(14))));
			} else {
				btnOther3.setText("");
				boxOther3.setText("");
			}
			if (itemSize >= 16) {
				btnOther4.setText(items.get(15).getName());
				boxOther4.setText(items.get(15).getDescription() + "\nCost: "+items.get(15).getCost() +"      Size: "+ items.get(15).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(items.get(15))));
			} else {
				btnOther4.setText("");
				boxOther4.setText("");
			}
		} else if (page == 5) {
			if (itemSize >= 17) {
				btnOther1.setText(items.get(16).getName());
				boxOther1.setText(items.get(16).getDescription() + "\nCost: "+items.get(16).getCost() +"      Size: "+ items.get(16).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(items.get(16))));
			}
			if (itemSize >= 18) {
				btnOther2.setText(items.get(17).getName());
				boxOther2.setText(items.get(17).getDescription() + "\nCost: "+items.get(17).getCost() +"      Size: "+ items.get(17).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(items.get(17))));
			} else {
				btnOther2.setText("");
				boxOther2.setText("");
			}
			if (itemSize >= 19) {
				btnOther3.setText(items.get(18).getName());
				boxOther3.setText(items.get(18).getDescription() + "\nCost: "+items.get(18).getCost() +"      Size: "+ items.get(18).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(items.get(18))));
			} else {
				btnOther3.setText("");
				boxOther3.setText("");
			}
			if (itemSize >= 20) {
				btnOther4.setText(items.get(19).getName());
				boxOther4.setText(items.get(19).getDescription() + "\nCost: "+items.get(19).getCost() +"      Size: "+ items.get(19).getSize() + "      Quantity: " +game.getInventory().get(items.get(19)));
			} else {
				btnOther4.setText("");
				boxOther4.setText("");
			}
		}
	
		
		
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @param game
	 */
	private void initialize(GameEnvironment game) {
		frame = new JFrame();
		frame.setBounds(450, 150, 770, 656);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if (intialRunMarker) {
			otherItems = getOtherItemsFromInvent(game);
			cheapItems = getCheapFromInvent(game);
			expennyItems = getExpennyFromInvent(game);
			intialRunMarker = false;
		}
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.openDisplayStore(game);
				frame.dispose();
			}
		});
		frame.getContentPane().setLayout(null);
		backButton.setFont(new Font("Arial", Font.PLAIN, 20));
		backButton.setBounds(10, 10, 105, 41);
		frame.getContentPane().add(backButton);
		
		
		JLabel reactiveLabel = new JLabel("Select Items to sell");
		reactiveLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		reactiveLabel.setBounds(135, 10, 445, 41);
		frame.getContentPane().add(reactiveLabel);
		
		JLabel lblGold = new JLabel("Gold: " + game.getGold());
		lblGold.setFont(new Font("Arial", Font.PLAIN, 16));
		lblGold.setBounds(619, 10, 127, 25);
		frame.getContentPane().add(lblGold);
		
		JLabel lblCargo = new JLabel("Cargo: " + game.getCurrentCargo() + "/" + game.getCurrentShip().getCargoSize());
		lblCargo.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCargo.setBounds(619, 33, 127, 25);
		frame.getContentPane().add(lblCargo);
		
		JLabel cheapItemsLabel = new JLabel("Cheap Items");
		cheapItemsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		cheapItemsLabel.setBounds(38, 61, 105, 25);
		frame.getContentPane().add(cheapItemsLabel);
		
		JLabel expensiveItemsLabel = new JLabel("Expensive Items");
		expensiveItemsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		expensiveItemsLabel.setBounds(285, 61, 148, 25);
		frame.getContentPane().add(expensiveItemsLabel);
		
		JLabel otherItemsLabel = new JLabel("Other Items (Page 1)");
		otherItemsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		otherItemsLabel.setBounds(529, 61, 217, 25);
		frame.getContentPane().add(otherItemsLabel);
		
		boxCheap1 = new JTextArea();
		boxCheap1.setEditable(false);
		boxCheap1.setFont(new Font("Arial", Font.PLAIN, 14));
		boxCheap1.setBounds(10, 128, 228, 59);
		frame.getContentPane().add(boxCheap1);
		boxCheap1.setColumns(10);
		
		JButton btnCheap1 = new JButton("");
		btnCheap1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cheapItems.size() >= 1) {
				Item item = cheapItems.get(0);
				sellItem(item, game, reactiveLabel, lblGold, lblCargo);
				btnCheap1.setText(cheapItems.get(0).getName());
				boxCheap1.setText(cheapItems.get(0).getDescription() + "\nCost: "+cheapItems.get(0).getCost() +"      Size: "+ cheapItems.get(0).getSize() + "      Quantity: " +game.getInventory().get(cheapItems.get(0)));
				}
			}
		});
		btnCheap1.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCheap1.setBounds(10, 96, 228, 33);
		frame.getContentPane().add(btnCheap1);
		
		
		
		
		boxCheap2 = new JTextArea();
		boxCheap2.setEditable(false);
		boxCheap2.setFont(new Font("Arial", Font.PLAIN, 14));
		boxCheap2.setColumns(10);
		boxCheap2.setBounds(10, 229, 228, 59);
		frame.getContentPane().add(boxCheap2);
		
		JButton btnCheap2 = new JButton("");
		btnCheap2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cheapItems.size() >= 2) {
				Item item = cheapItems.get(1);
				sellItem(item, game, reactiveLabel, lblGold, lblCargo);
				btnCheap2.setText(cheapItems.get(1).getName());
				boxCheap2.setText(cheapItems.get(1).getDescription() + "\nCost: "+cheapItems.get(1).getCost() +"      Size: "+ cheapItems.get(1).getSize() + "      Quantity: " +game.getInventory().get(cheapItems.get(1)));
//				cheapItems = removeZeroItems(game, cheapItems, cheapItems.get(1));
				}
			}
		});
		btnCheap2.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCheap2.setBounds(10, 197, 228, 33);
		frame.getContentPane().add(btnCheap2);
		
		boxCheap3 = new JTextArea();
		boxCheap3.setEditable(false);
		boxCheap3.setFont(new Font("Arial", Font.PLAIN, 14));
		boxCheap3.setColumns(10);
		boxCheap3.setBounds(10, 329, 228, 59);
		frame.getContentPane().add(boxCheap3);
		
		JButton btnCheap3 = new JButton("");
		btnCheap3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cheapItems.size() >= 3) {
				Item item = cheapItems.get(2);
				sellItem(item, game, reactiveLabel, lblGold, lblCargo);
				btnCheap3.setText(cheapItems.get(2).getName());
				boxCheap3.setText(cheapItems.get(2).getDescription() + "\nCost: "+cheapItems.get(2).getCost() +"      Size: "+ cheapItems.get(2).getSize() + "      Quantity: " +game.getInventory().get(cheapItems.get(2)));
//				cheapItems = removeZeroItems(game, cheapItems, cheapItems.get(2));
				}
			}
		});
		btnCheap3.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCheap3.setBounds(10, 298, 228, 33);
		frame.getContentPane().add(btnCheap3);
		

		boxCheap4 = new JTextArea();
		boxCheap4.setEditable(false);
		boxCheap4.setFont(new Font("Arial", Font.PLAIN, 14));
		boxCheap4.setColumns(10);
		boxCheap4.setBounds(10, 430, 228, 59);
		frame.getContentPane().add(boxCheap4);
		
		JButton btnCheap4 = new JButton("");
		btnCheap4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cheapItems.size() >= 4) {
				Item item = cheapItems.get(3);
				sellItem(item, game, reactiveLabel, lblGold, lblCargo);
				btnCheap4.setText(cheapItems.get(3).getName());
				boxCheap4.setText(cheapItems.get(3).getDescription() + "\nCost: "+cheapItems.get(3).getCost() +"      Size: "+ cheapItems.get(3).getSize() + "      Quantity: " +game.getInventory().get(cheapItems.get(3)));
				}
			}
		});
		btnCheap4.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCheap4.setBounds(10, 398, 228, 33);
		frame.getContentPane().add(btnCheap4);
		
		boxExpenny1 = new JTextArea();
		boxExpenny1.setEditable(false);
		boxExpenny1.setFont(new Font("Arial", Font.PLAIN, 14));
		boxExpenny1.setColumns(10);
		boxExpenny1.setBounds(260, 128, 228, 59);
		frame.getContentPane().add(boxExpenny1);
		
		boxExpenny2 = new JTextArea();
		boxExpenny2.setEditable(false);
		boxExpenny2.setFont(new Font("Arial", Font.PLAIN, 14));
		boxExpenny2.setColumns(10);
		boxExpenny2.setBounds(260, 229, 228, 59);
		frame.getContentPane().add(boxExpenny2);
		
		boxExpenny3 = new JTextArea();
		boxExpenny3.setEditable(false);
		boxExpenny3.setFont(new Font("Arial", Font.PLAIN, 14));
		boxExpenny3.setColumns(10);
		boxExpenny3.setBounds(260, 329, 228, 59);
		frame.getContentPane().add(boxExpenny3);
		
		JButton btnExpenny1 = new JButton("");
		btnExpenny1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (expennyItems.size() >= 1) {
				Item item = expennyItems.get(0);
				sellItem(item, game, reactiveLabel, lblGold, lblCargo);
				btnExpenny1.setText(expennyItems.get(0).getName());
				boxExpenny1.setText(expennyItems.get(0).getDescription() + "\nCost: "+expennyItems.get(0).getCost() +"      Size: "+ expennyItems.get(0).getSize() + "      Quantity: " +game.getInventory().get(expennyItems.get(0)));
				}
			}
		});
		btnExpenny1.setFont(new Font("Arial", Font.PLAIN, 14));
		btnExpenny1.setBounds(260, 96, 228, 33);
		frame.getContentPane().add(btnExpenny1);
		
		boxOther1 = new JTextArea();
		boxOther1.setEditable(false);
		boxOther1.setFont(new Font("Arial", Font.PLAIN, 14));
		boxOther1.setColumns(10);
		boxOther1.setBounds(513, 128, 233, 59);
		frame.getContentPane().add(boxOther1);
		
		boxOther2 = new JTextArea();
		boxOther2.setEditable(false);
		boxOther2.setFont(new Font("Arial", Font.PLAIN, 14));
		boxOther2.setColumns(10);
		boxOther2.setBounds(513, 229, 233, 59);
		frame.getContentPane().add(boxOther2);
		
		boxOther3 = new JTextArea();
		boxOther3.setEditable(false);
		boxOther3.setFont(new Font("Arial", Font.PLAIN, 14));
		boxOther3.setColumns(10);
		boxOther3.setBounds(513, 329, 233, 59);
		frame.getContentPane().add(boxOther3);
		
		boxOther4 = new JTextArea();
		boxOther4.setEditable(false);
		boxOther4.setFont(new Font("Arial", Font.PLAIN, 14));
		boxOther4.setColumns(10);
		boxOther4.setBounds(513, 430, 233, 59);
		frame.getContentPane().add(boxOther4);
		
		JButton btnOther1 = new JButton("");
		btnOther1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (page == 1) {
					Item item = otherItems.get(0);
					sellItem(item, game, reactiveLabel, lblGold, lblCargo);
					btnOther1.setText(otherItems.get(0).getName());
					boxOther1.setText(otherItems.get(0).getDescription() + "\nCost: "+otherItems.get(0).getCost() +"      Size: "+ otherItems.get(0).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(otherItems.get(0))));
				} else if (page == 2) {
					Item item = otherItems.get(4);
					sellItem(item, game, reactiveLabel, lblGold, lblCargo);
					btnOther1.setText(otherItems.get(4).getName());
					boxOther1.setText(otherItems.get(4).getDescription() + "\nCost: "+otherItems.get(4).getCost() +"      Size: "+ otherItems.get(4).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(otherItems.get(4))));
				} else if (page == 3) {
					Item item = otherItems.get(8);
					sellItem(item, game, reactiveLabel, lblGold, lblCargo);
					btnOther1.setText(otherItems.get(8).getName());
					boxOther1.setText(otherItems.get(8).getDescription() + "\nCost: "+otherItems.get(8).getCost() +"      Size: "+ otherItems.get(8).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(otherItems.get(8))));
				} else if (page == 4) {
					Item item = otherItems.get(12);
					sellItem(item, game, reactiveLabel, lblGold, lblCargo);
					btnOther1.setText(otherItems.get(12).getName());
					boxOther1.setText(otherItems.get(12).getDescription() + "\nCost: "+otherItems.get(12).getCost() +"      Size: "+ otherItems.get(12).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(otherItems.get(12))));
				} else if (page == 5) {
					Item item = otherItems.get(16);
					sellItem(item, game, reactiveLabel, lblGold, lblCargo);
					btnOther1.setText(otherItems.get(16).getName());
					boxOther1.setText(otherItems.get(16).getDescription() + "\nCost: "+otherItems.get(16).getCost() +"      Size: "+ otherItems.get(16).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(otherItems.get(16))));
				}
			}
		});
		btnOther1.setFont(new Font("Arial", Font.PLAIN, 14));
		btnOther1.setBounds(513, 96, 233, 33);
		frame.getContentPane().add(btnOther1);
		
		
		
		JButton btnExpenny2 = new JButton("");
		btnExpenny2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (expennyItems.size() >= 2) {
				Item item = expennyItems.get(1);
				sellItem(item, game, reactiveLabel, lblGold, lblCargo);
				btnExpenny2.setText(expennyItems.get(1).getName());
				boxExpenny2.setText(expennyItems.get(1).getDescription() + "\nCost: "+expennyItems.get(1).getCost() +"      Size: "+ expennyItems.get(1).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(expennyItems.get(1))));
				}	
			}
		});
		btnExpenny2.setFont(new Font("Arial", Font.PLAIN, 14));
		btnExpenny2.setBounds(260, 197, 228, 33);
		frame.getContentPane().add(btnExpenny2);
		
		JButton btnExpenny3 = new JButton("");
		btnExpenny3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (expennyItems.size() >= 3) {
				Item item = expennyItems.get(2);
				sellItem(item, game, reactiveLabel, lblGold, lblCargo);
				btnExpenny3.setText(expennyItems.get(2).getName());
				boxExpenny3.setText(expennyItems.get(2).getDescription() + "\nCost: "+expennyItems.get(2).getCost() +"      Size: "+ expennyItems.get(2).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(expennyItems.get(2))));
				}
			}
		});
		btnExpenny3.setFont(new Font("Arial", Font.PLAIN, 14));
		btnExpenny3.setBounds(260, 298, 228, 33);
		frame.getContentPane().add(btnExpenny3);
		
		JButton btnOther2 = new JButton("");
		btnOther2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (page == 1) {
					Item item = otherItems.get(1);
					sellItem(item, game, reactiveLabel, lblGold, lblCargo);
					btnOther2.setText(otherItems.get(1).getName());
					boxOther2.setText(otherItems.get(1).getDescription() + "\nCost: "+otherItems.get(1).getCost() +"      Size: "+ otherItems.get(1).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(otherItems.get(1))));
				} else if (page == 2) {
					Item item = otherItems.get(5);
					sellItem(item, game, reactiveLabel, lblGold, lblCargo);
					btnOther2.setText(otherItems.get(5).getName());
					boxOther2.setText(otherItems.get(5).getDescription() + "\nCost: "+otherItems.get(5).getCost() +"      Size: "+ otherItems.get(5).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(otherItems.get(5))));
				} else if (page == 3) {
					Item item = otherItems.get(9);
					sellItem(item, game, reactiveLabel, lblGold, lblCargo);
					btnOther2.setText(otherItems.get(9).getName());
					boxOther2.setText(otherItems.get(9).getDescription() + "\nCost: "+otherItems.get(9).getCost() +"      Size: "+ otherItems.get(9).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(otherItems.get(9))));
				} else if (page == 4) {
					Item item = otherItems.get(13);
					sellItem(item, game, reactiveLabel, lblGold, lblCargo);
					btnOther2.setText(otherItems.get(13).getName());
					boxOther2.setText(otherItems.get(13).getDescription() + "\nCost: "+otherItems.get(13).getCost() +"      Size: "+ otherItems.get(13).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(otherItems.get(13))));
				} else if (page == 5) {
					Item item = otherItems.get(17);
					sellItem(item, game, reactiveLabel, lblGold, lblCargo);
					btnOther2.setText(otherItems.get(17).getName());
					boxOther2.setText(otherItems.get(17).getDescription() + "\nCost: "+otherItems.get(17).getCost() +"      Size: "+ otherItems.get(17).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(otherItems.get(17))));
				}
			}
		});
		btnOther2.setFont(new Font("Arial", Font.PLAIN, 14));
		btnOther2.setBounds(513, 197, 233, 33);
		frame.getContentPane().add(btnOther2);
		
		JButton btnOther3 = new JButton("");
		btnOther3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (page == 1) {
					Item item = otherItems.get(2);
					sellItem(item, game, reactiveLabel, lblGold, lblCargo);
					btnOther3.setText(otherItems.get(2).getName());
					boxOther3.setText(otherItems.get(2).getDescription() + "\nCost: "+otherItems.get(2).getCost() +"      Size: "+ otherItems.get(2).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(otherItems.get(2))));
				} else if (page == 2) {
					Item item = otherItems.get(6);
					sellItem(item, game, reactiveLabel, lblGold, lblCargo);
					btnOther3.setText(otherItems.get(6).getName());
					boxOther3.setText(otherItems.get(6).getDescription() + "\nCost: "+otherItems.get(6).getCost() +"      Size: "+ otherItems.get(6).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(otherItems.get(6))));
				} else if (page == 3) {
					Item item = otherItems.get(10);
					sellItem(item, game, reactiveLabel, lblGold, lblCargo);
					btnOther3.setText(otherItems.get(10).getName());
					boxOther3.setText(otherItems.get(10).getDescription() + "\nCost: "+otherItems.get(10).getCost() +"      Size: "+ otherItems.get(10).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(otherItems.get(10))));
				} else if (page == 4) {
					Item item = otherItems.get(14);
					sellItem(item, game, reactiveLabel, lblGold, lblCargo);
					btnOther3.setText(otherItems.get(4).getName());
					boxOther3.setText(otherItems.get(4).getDescription() + "\nCost: "+otherItems.get(4).getCost() +"      Size: "+ otherItems.get(4).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(otherItems.get(4))));
				} else if (page == 5) {
					Item item = otherItems.get(18);
					sellItem(item, game, reactiveLabel, lblGold, lblCargo);
					btnOther3.setText(otherItems.get(18).getName());
					boxOther3.setText(otherItems.get(18).getDescription() + "\nCost: "+otherItems.get(18).getCost() +"      Size: "+ otherItems.get(18).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(otherItems.get(18))));
				}
			}
		});
		btnOther3.setFont(new Font("Arial", Font.PLAIN, 14));
		btnOther3.setBounds(513, 298, 233, 33);
		frame.getContentPane().add(btnOther3);
		
		JButton btnOther4 = new JButton("");
		btnOther4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (page == 1) {
					Item item = otherItems.get(3);
					sellItem(item, game, reactiveLabel, lblGold, lblCargo);
					btnOther4.setText(otherItems.get(3).getName());
					boxOther4.setText(otherItems.get(3).getDescription() + "\nCost: "+otherItems.get(3).getCost() +"      Size: "+ otherItems.get(3).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(otherItems.get(3))));
				} else if (page == 2) {
					Item item = otherItems.get(7);
					sellItem(item, game, reactiveLabel, lblGold, lblCargo);
					btnOther4.setText(otherItems.get(7).getName());
					boxOther4.setText(otherItems.get(7).getDescription() + "\nCost: "+otherItems.get(7).getCost() +"      Size: "+ otherItems.get(7).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(otherItems.get(7))));
				} else if (page == 3) {
					Item item = otherItems.get(11);
					sellItem(item, game, reactiveLabel, lblGold, lblCargo);
					btnOther4.setText(otherItems.get(11).getName());
					boxOther4.setText(otherItems.get(11).getDescription() + "\nCost: "+otherItems.get(11).getCost() +"      Size: "+ otherItems.get(11).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(otherItems.get(11))));
				} else if (page == 4) {
					Item item = otherItems.get(15);
					sellItem(item, game, reactiveLabel, lblGold, lblCargo);
					btnOther4.setText(otherItems.get(15).getName());
					boxOther4.setText(otherItems.get(15).getDescription() + "\nCost: "+otherItems.get(15).getCost() +"      Size: "+ otherItems.get(15).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(otherItems.get(15))));
				}
			}
		});
		btnOther4.setFont(new Font("Arial", Font.PLAIN, 14));
		btnOther4.setBounds(513, 398, 233, 33);
		frame.getContentPane().add(btnOther4);
		
		
		
		
		
		
		
		cheapItemSize = cheapItems.size();
		if (cheapItemSize >= 1) {
			btnCheap1.setText(cheapItems.get(0).getName());
			boxCheap1.setText(cheapItems.get(0).getDescription() + "\nCost: "+cheapItems.get(0).getCost() +"      Size: "+ cheapItems.get(0).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(cheapItems.get(0))));
		}
		if (cheapItemSize >= 2) {
			btnCheap2.setText(cheapItems.get(1).getName());
			boxCheap2.setText(cheapItems.get(1).getDescription() + "\nCost: "+cheapItems.get(1).getCost() +"      Size: "+ cheapItems.get(1).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(cheapItems.get(1))));
		}
		if (cheapItemSize >= 3) {
			btnCheap3.setText(cheapItems.get(2).getName());
			boxCheap3.setText(cheapItems.get(2).getDescription() + "\nCost: "+cheapItems.get(2).getCost() +"      Size: "+ cheapItems.get(2).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(cheapItems.get(2))));
		}
		if (cheapItemSize == 4) {
			btnCheap4.setText(cheapItems.get(3).getName());
			boxCheap4.setText(cheapItems.get(3).getDescription() + "\nCost: "+cheapItems.get(3).getCost() +"      Size: "+ cheapItems.get(3).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(cheapItems.get(3))));
		}
		

		expennyItemSize = expennyItems.size();
		if (expennyItemSize >= 1) {
			btnExpenny1.setText(expennyItems.get(0).getName());
			boxExpenny1.setText(expennyItems.get(0).getDescription() + "\nCost: "+expennyItems.get(0).getCost() +"      Size: "+ expennyItems.get(0).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(expennyItems.get(0))));
		}
		if (expennyItemSize >= 2) {
			btnExpenny2.setText(expennyItems.get(1).getName());
			boxExpenny2.setText(expennyItems.get(1).getDescription() + "\nCost: "+expennyItems.get(1).getCost() +"      Size: "+ expennyItems.get(1).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(expennyItems.get(1))));
		}
		if (expennyItemSize >= 3) {
			btnExpenny3.setText(expennyItems.get(2).getName());
			boxExpenny3.setText(expennyItems.get(2).getDescription() + "\nCost: "+expennyItems.get(2).getCost() +"      Size: "+ expennyItems.get(2).getSize() + "      Quantity: " +game.getInventory().get(game.getMatchingItem(expennyItems.get(2))));
		}
		
		
		
		otherItemSize = otherItems.size();
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((page == 1) && (otherItemSize > 4)) {
					page = 2;
					otherItemsLabel.setText("Other Items (Page "+page+")");
					updateOtherDisplay(page, otherItems, btnOther1, btnOther2, btnOther3, btnOther4, game);
				} else if ((page == 2) && (otherItemSize > 8)) {
					page = 3;
					otherItemsLabel.setText("Other Items (Page "+page+")");
					updateOtherDisplay(page, otherItems, btnOther1, btnOther2, btnOther3, btnOther4, game);
				} else if ((page == 3) && (otherItemSize > 12)) {
					page = 4;
					otherItemsLabel.setText("Other Items (Page "+page+")");
					updateOtherDisplay(page, otherItems, btnOther1, btnOther2, btnOther3, btnOther4, game);
				} else if (page == 4) {
					page = 5;
					otherItemsLabel.setText("Other Items (Page "+page+")");
					updateOtherDisplay(page, otherItems, btnOther1, btnOther2, btnOther3, btnOther4, game);
				}
			}
		});
		btnNext.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNext.setBounds(630, 499, 116, 25);
		frame.getContentPane().add(btnNext);
		
		JButton btnPrev = new JButton("Prev");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (page == 5) {
					page = 4;
					otherItemsLabel.setText("Other Items (Page "+page+")");
					updateOtherDisplay(page, otherItems, btnOther1, btnOther2, btnOther3, btnOther4, game);
				} else if (page == 4) {
					page = 3;
					otherItemsLabel.setText("Other Items (Page "+page+")");
					updateOtherDisplay(page, otherItems, btnOther1, btnOther2, btnOther3, btnOther4, game);
				} else if (page == 3) {
					page = 2;
					otherItemsLabel.setText("Other Items (Page "+page+")");
					updateOtherDisplay(page, otherItems, btnOther1, btnOther2, btnOther3, btnOther4, game);
				} else if (page == 2) {
					page = 1;
					otherItemsLabel.setText("Other Items (Page "+page+")");
					updateOtherDisplay(page, otherItems, btnOther1, btnOther2, btnOther3, btnOther4, game);
				}
			}
		});
		btnPrev.setFont(new Font("Arial", Font.PLAIN, 20));
		btnPrev.setBounds(513, 499, 121, 25);
		frame.getContentPane().add(btnPrev);
		
		updateOtherDisplay(page, otherItems, btnOther1, btnOther2, btnOther3, btnOther4, game);
	}

}
