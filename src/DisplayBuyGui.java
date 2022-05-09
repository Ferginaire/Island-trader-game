import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.util.ArrayList;

/**
 * This class displays a Gui with all the Items available to buy from this island's store.
 * It has four buttons with corresponding information boxes for cheap items,
 * four buttons with corresponding information boxes for expensive items,
 * and four buttons with corresponding information boxes for other items that can cycle up to display up to 5 pages worth.
 * Clicking a button will buy the item and add it to your inventory if the player has the cargo capacity and the gold
 * @author Fergus and Oliver
 *
 */
public class DisplayBuyGui {

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
	 
	/**
	 * Launch the application.
	 * @param game
	 */
	public void run(GameEnvironment game) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayBuyGui window = new DisplayBuyGui(game);
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
	public DisplayBuyGui(GameEnvironment game) {
		initialize(game);
	}

	/**
	 * This function take an arraylist of items and creates a new arraylist with the same items but adjusted down prices
	 * @param items that need their price reduced
	 * @return adjusted items with their new prices
	 */
	private ArrayList<Item> makeCheap(ArrayList<Item> items) {
		ArrayList<Item> adjusted = new ArrayList<Item>();
		for (Item item: items) {
			Item titem = new Item(item.getName(),item.getDescription(),item.getSize(),(int) (item.getCost()*Item.unfavouredMult));
			adjusted.add(titem);
		}
		return adjusted;
	}
	
	/**
	 * This function take an arraylist of items and creates a new arraylist with the same items but adjusted up prices
	 * @param items that need their price increased
	 * @return adjusted items with their new prices
	 */
	private ArrayList<Item> makeExpensive(ArrayList<Item> items) {
		ArrayList<Item> adjusted = new ArrayList<Item>();
		for (Item item: items) {
			Item titem = new Item(item.getName(),item.getDescription(),item.getSize(),(int) (item.getCost()*Item.favouredMult));
			adjusted.add(titem);
		}
		return adjusted;
	}
	
	
	/** 
	 * Buys one of the displayed items and adjusts the gold and cargo labels and displays a message confirming the purchase.
	 * If the cargo or gold requirements are not met, displays a message explaining why can't purchase.
	 * @param item being bought
	 * @param game
	 * @param reactiveLabel The label that dislpays the confirmation or explains why can't purchase.
	 * @param lblGold the label displaying players current gold
	 * @param lblCargo the label displaying players current cargo
	 */
	private void buyItem(Item item, GameEnvironment game, JLabel reactiveLabel, JLabel lblGold, JLabel lblCargo) {
		
		if (game.getCurrentCargo()+item.getSize() > game.getCurrentShip().getCargoSize()) {
			reactiveLabel.setText("Sorry, not enough cargo space");
		} else {
		if ((game.getGold() >= item.getCost())) {
			Item titem = game.getMatchingItem(item);
			if (game.getInventory().containsKey(titem)) {
				int count = game.getInventory().get(titem);
				game.getInventory().put(titem, count+1);
			} else {
				game.getInventory().put(titem, 1);
			}
			game.setCurrentCargo(game.getCurrentCargo()+item.getSize());
			game.pay(item.getCost());
			reactiveLabel.setText("You bought " + item.getName());
			lblGold.setText("Gold: " + game.getGold());
			lblCargo.setText("Cargo: " + game.getCurrentCargo() + "/" + game.getCurrentShip().getCargoSize());
		} else {
			reactiveLabel.setText("You don't have enough gold to buy that Item");
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
		
		JLabel reactiveLabel = new JLabel("Select an Item to buy");
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
		
		JLabel otherItemsLabel = new JLabel("Other Items (Page " + page + ")");
		otherItemsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		otherItemsLabel.setBounds(529, 61, 217, 25);
		frame.getContentPane().add(otherItemsLabel);
		
		JButton btnCheap1 = new JButton("New button\r\n");
		btnCheap1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = cheapItems.get(0);
				buyItem(item, game, reactiveLabel, lblGold, lblCargo);
			}
		});
		btnCheap1.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCheap1.setBounds(10, 96, 228, 33);
		frame.getContentPane().add(btnCheap1);
		
		boxCheap1 = new JTextArea();
		boxCheap1.setEditable(false);
		boxCheap1.setFont(new Font("Arial", Font.PLAIN, 14));
		boxCheap1.setBounds(10, 128, 228, 59);
		frame.getContentPane().add(boxCheap1);
		boxCheap1.setColumns(10);
		
		JButton btnCheap2 = new JButton("New button\r\n");
		btnCheap2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = cheapItems.get(1);
				buyItem(item, game, reactiveLabel, lblGold, lblCargo);
			}
		});
		btnCheap2.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCheap2.setBounds(10, 197, 228, 33);
		frame.getContentPane().add(btnCheap2);
		
		boxCheap2 = new JTextArea();
		boxCheap2.setEditable(false);
		boxCheap2.setFont(new Font("Arial", Font.PLAIN, 14));
		boxCheap2.setColumns(10);
		boxCheap2.setBounds(10, 229, 228, 59);
		frame.getContentPane().add(boxCheap2);
		
		JButton btnCheap3 = new JButton("New button\r\n");
		btnCheap3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = cheapItems.get(2);
				buyItem(item, game, reactiveLabel, lblGold, lblCargo);
			}
		});
		btnCheap3.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCheap3.setBounds(10, 298, 228, 33);
		frame.getContentPane().add(btnCheap3);
		
		boxCheap3 = new JTextArea();
		boxCheap3.setEditable(false);
		boxCheap3.setFont(new Font("Arial", Font.PLAIN, 14));
		boxCheap3.setColumns(10);
		boxCheap3.setBounds(10, 329, 228, 59);
		frame.getContentPane().add(boxCheap3);
		
		JButton btnCheap4 = new JButton("");
		btnCheap4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cheapItems.size() == 4) {
				Item item = cheapItems.get(3);
				buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				}
			}
		});
		btnCheap4.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCheap4.setBounds(10, 398, 228, 33);
		frame.getContentPane().add(btnCheap4);
		
		boxCheap4 = new JTextArea();
		boxCheap4.setEditable(false);
		boxCheap4.setFont(new Font("Arial", Font.PLAIN, 14));
		boxCheap4.setColumns(10);
		boxCheap4.setBounds(10, 430, 228, 59);
		frame.getContentPane().add(boxCheap4);
		
		JButton btnExpenny1 = new JButton("New button\r\n");
		btnExpenny1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = expennyItems.get(0);
				buyItem(item, game, reactiveLabel, lblGold, lblCargo);
			}
		});
		btnExpenny1.setFont(new Font("Arial", Font.PLAIN, 14));
		btnExpenny1.setBounds(260, 96, 228, 33);
		frame.getContentPane().add(btnExpenny1);
		
		JButton btnOther1 = new JButton("New button\r\n");
		btnOther1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (page == 1) {
					Item item = otherItems.get(0);
					buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				} else if (page == 2) {
					Item item = otherItems.get(4);
					buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				} else if (page == 3) {
					Item item = otherItems.get(8);
					buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				} else if (page == 4) {
					Item item = otherItems.get(12);
					buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				} else if (page == 5) {
					Item item = otherItems.get(16);
					buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				}
			}
		});
		btnOther1.setFont(new Font("Arial", Font.PLAIN, 14));
		btnOther1.setBounds(513, 96, 233, 33);
		frame.getContentPane().add(btnOther1);
		
		JButton btnExpenny2 = new JButton("New button\r\n");
		btnExpenny2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = expennyItems.get(1);
				buyItem(item, game, reactiveLabel, lblGold, lblCargo);
			}
		});
		btnExpenny2.setFont(new Font("Arial", Font.PLAIN, 14));
		btnExpenny2.setBounds(260, 197, 228, 33);
		frame.getContentPane().add(btnExpenny2);
		
		JButton btnExpenny3 = new JButton("New button\r\n");
		btnExpenny3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = expennyItems.get(2);
				buyItem(item, game, reactiveLabel, lblGold, lblCargo);
			}
		});
		btnExpenny3.setFont(new Font("Arial", Font.PLAIN, 14));
		btnExpenny3.setBounds(260, 298, 228, 33);
		frame.getContentPane().add(btnExpenny3);
		
		JButton btnOther2 = new JButton("New button\r\n");
		btnOther2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (page == 1) {
					Item item = otherItems.get(1);
					buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				} else if (page == 2) {
					Item item = otherItems.get(5);
					buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				} else if (page == 3) {
					Item item = otherItems.get(9);
					buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				} else if (page == 4) {
					Item item = otherItems.get(13);
					buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				} else if (page == 5) {
					Item item = otherItems.get(17);
					buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				}
			}
		});
		btnOther2.setFont(new Font("Arial", Font.PLAIN, 14));
		btnOther2.setBounds(513, 197, 233, 33);
		frame.getContentPane().add(btnOther2);
		
		JButton btnOther3 = new JButton("New button\r\n");
		btnOther3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (page == 1) {
					Item item = otherItems.get(2);
					buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				} else if (page == 2) {
					Item item = otherItems.get(6);
					buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				} else if (page == 3) {
					Item item = otherItems.get(10);
					buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				} else if (page == 4) {
					Item item = otherItems.get(14);
					buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				} else if (page == 5) {
					Item item = otherItems.get(18);
					buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				}
			}
		});
		btnOther3.setFont(new Font("Arial", Font.PLAIN, 14));
		btnOther3.setBounds(513, 298, 233, 33);
		frame.getContentPane().add(btnOther3);
		
		JButton btnOther4 = new JButton("New button\r\n");
		btnOther4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (page == 1) {
					Item item = otherItems.get(3);
					buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				} else if (page == 2) {
					Item item = otherItems.get(7);
					buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				} else if (page == 3) {
					Item item = otherItems.get(11);
					buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				} else if (page == 4) {
					Item item = otherItems.get(15);
					buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				} else if (page == 5) {
					Item item = otherItems.get(19);
					buyItem(item, game, reactiveLabel, lblGold, lblCargo);
				}
			}
		});
		btnOther4.setFont(new Font("Arial", Font.PLAIN, 14));
		btnOther4.setBounds(513, 398, 233, 33);
		frame.getContentPane().add(btnOther4);
		
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
		cheapItems = game.getCurrentIsland().getStore().getCheapItems();
		cheapItems = makeCheap(cheapItems);
		btnCheap1.setText(cheapItems.get(0).getName());
		btnCheap2.setText(cheapItems.get(1).getName());
		btnCheap3.setText(cheapItems.get(2).getName());
		if (cheapItems.size() == 4) {
		btnCheap4.setText(cheapItems.get(3).getName());
		}
		boxCheap1.setText(cheapItems.get(0).getDescription() + "\nCost: "+cheapItems.get(0).getCost() +"      Size: "+ cheapItems.get(0).getSize());
		boxCheap2.setText(cheapItems.get(1).getDescription() + "\nCost: "+cheapItems.get(1).getCost() +"      Size: "+ cheapItems.get(1).getSize());
		boxCheap3.setText(cheapItems.get(2).getDescription() + "\nCost: "+cheapItems.get(2).getCost() +"      Size: "+ cheapItems.get(2).getSize());
		if (cheapItems.size() == 4) {
			boxCheap4.setText(cheapItems.get(3).getDescription() + "\nCost: "+cheapItems.get(3).getCost() +"      Size: "+ cheapItems.get(3).getSize());
			}
		
		expennyItems = game.getCurrentIsland().getStore().getExpensiveItems();
		expennyItems = makeExpensive(expennyItems);
		btnExpenny1.setText(expennyItems.get(0).getName());
		btnExpenny2.setText(expennyItems.get(1).getName());
		btnExpenny3.setText(expennyItems.get(2).getName());
		boxExpenny1.setText(expennyItems.get(0).getDescription() + "\nCost: "+expennyItems.get(0).getCost() +"      Size: "+ expennyItems.get(0).getSize());
		boxExpenny2.setText(expennyItems.get(1).getDescription() + "\nCost: "+expennyItems.get(1).getCost() +"      Size: "+ expennyItems.get(1).getSize());
		boxExpenny3.setText(expennyItems.get(2).getDescription() + "\nCost: "+expennyItems.get(2).getCost() +"      Size: "+ expennyItems.get(2).getSize());
		otherItems = game.getCurrentIsland().getStore().getBuyableItems();
		btnOther1.setText(otherItems.get(0).getName());
		btnOther2.setText(otherItems.get(1).getName());
		btnOther3.setText(otherItems.get(2).getName());
		btnOther4.setText(otherItems.get(3).getName());
		boxOther1.setText(otherItems.get(0).getDescription() + "\nCost: "+otherItems.get(0).getCost() +"      Size: "+ otherItems.get(0).getSize());
		boxOther2.setText(otherItems.get(1).getDescription() + "\nCost: "+otherItems.get(1).getCost() +"      Size: "+ otherItems.get(1).getSize());
		boxOther3.setText(otherItems.get(2).getDescription() + "\nCost: "+otherItems.get(2).getCost() +"      Size: "+ otherItems.get(2).getSize());
		boxOther4.setText(otherItems.get(3).getDescription() + "\nCost: "+otherItems.get(3).getCost() +"      Size: "+ otherItems.get(3).getSize());
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (page == 1) {
					page = 2;
					otherItemsLabel.setText("Other Items (Page " + page + ")");
					btnOther1.setText(otherItems.get(4).getName());
					btnOther2.setText(otherItems.get(5).getName());
					btnOther3.setText(otherItems.get(6).getName());
					btnOther4.setText(otherItems.get(7).getName());
					boxOther1.setText(otherItems.get(4).getDescription() + "\nCost: "+otherItems.get(4).getCost() +"      Size: "+ otherItems.get(4).getSize());
					boxOther2.setText(otherItems.get(5).getDescription() + "\nCost: "+otherItems.get(5).getCost() +"      Size: "+ otherItems.get(5).getSize());
					boxOther3.setText(otherItems.get(6).getDescription() + "\nCost: "+otherItems.get(6).getCost() +"      Size: "+ otherItems.get(6).getSize());
					boxOther4.setText(otherItems.get(7).getDescription() + "\nCost: "+otherItems.get(7).getCost() +"      Size: "+ otherItems.get(7).getSize());
				} else if (page == 2) {
					page = 3;
					otherItemsLabel.setText("Other Items (Page " + page + ")");
					btnOther1.setText(otherItems.get(8).getName());
					btnOther2.setText(otherItems.get(9).getName());
					btnOther3.setText(otherItems.get(10).getName());
					btnOther4.setText(otherItems.get(11).getName());
					boxOther1.setText(otherItems.get(8).getDescription() + "\nCost: "+otherItems.get(8).getCost() +"      Size: "+ otherItems.get(8).getSize());
					boxOther2.setText(otherItems.get(9).getDescription() + "\nCost: "+otherItems.get(9).getCost() +"      Size: "+ otherItems.get(9).getSize());
					boxOther3.setText(otherItems.get(10).getDescription() + "\nCost: "+otherItems.get(10).getCost() +"      Size: "+ otherItems.get(10).getSize());
					boxOther4.setText(otherItems.get(11).getDescription() + "\nCost: "+otherItems.get(11).getCost() +"      Size: "+ otherItems.get(11).getSize());
				} else if (page == 3) {
					page = 4;
					otherItemsLabel.setText("Other Items (Page " + page + ")");
					btnOther1.setText(otherItems.get(12).getName());
					btnOther2.setText(otherItems.get(13).getName());
					btnOther3.setText(otherItems.get(14).getName());
					btnOther4.setText(otherItems.get(15).getName());
					boxOther1.setText(otherItems.get(12).getDescription() + "\nCost: "+otherItems.get(12).getCost() +"      Size: "+ otherItems.get(12).getSize());
					boxOther2.setText(otherItems.get(13).getDescription() + "\nCost: "+otherItems.get(13).getCost() +"      Size: "+ otherItems.get(13).getSize());
					boxOther3.setText(otherItems.get(14).getDescription() + "\nCost: "+otherItems.get(14).getCost() +"      Size: "+ otherItems.get(14).getSize());
					boxOther4.setText(otherItems.get(15).getDescription() + "\nCost: "+otherItems.get(15).getCost() +"      Size: "+ otherItems.get(15).getSize());
				} else if (page == 4) {
					page = 5;
					otherItemsLabel.setText("Other Items (Page " + page + ")");
					btnOther1.setText(otherItems.get(16).getName());
					btnOther2.setText(otherItems.get(17).getName());
					btnOther3.setText(otherItems.get(18).getName());
					if (otherItems.size() >= 20) {
						btnOther4.setText(otherItems.get(19).getName());
					} else {
						btnOther4.setText("");
					}
					boxOther1.setText(otherItems.get(16).getDescription() + "\nCost: "+otherItems.get(16).getCost() +"      Size: "+ otherItems.get(16).getSize());
					boxOther2.setText(otherItems.get(17).getDescription() + "\nCost: "+otherItems.get(17).getCost() +"      Size: "+ otherItems.get(17).getSize());
					boxOther3.setText(otherItems.get(18).getDescription() + "\nCost: "+otherItems.get(18).getCost() +"      Size: "+ otherItems.get(18).getSize());
					if (otherItems.size() >= 20) {
						boxOther4.setText(otherItems.get(19).getDescription() + "\nCost: "+otherItems.get(19).getCost() +"      Size: "+ otherItems.get(19).getSize());
					} else {
						boxOther4.setText("");
					}
					
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
					otherItemsLabel.setText("Other Items (Page " + page + ")");
					btnOther1.setText(otherItems.get(12).getName());
					btnOther2.setText(otherItems.get(13).getName());
					btnOther3.setText(otherItems.get(14).getName());
					btnOther4.setText(otherItems.get(15).getName());
					boxOther1.setText(otherItems.get(12).getDescription() + "\nCost: "+otherItems.get(12).getCost() +"      Size: "+ otherItems.get(12).getSize());
					boxOther2.setText(otherItems.get(13).getDescription() + "\nCost: "+otherItems.get(13).getCost() +"      Size: "+ otherItems.get(13).getSize());
					boxOther3.setText(otherItems.get(14).getDescription() + "\nCost: "+otherItems.get(14).getCost() +"      Size: "+ otherItems.get(14).getSize());
					boxOther4.setText(otherItems.get(15).getDescription() + "\nCost: "+otherItems.get(15).getCost() +"      Size: "+ otherItems.get(15).getSize());
				} else if (page == 4) {
					page = 3;
					otherItemsLabel.setText("Other Items (Page " + page + ")");
					btnOther1.setText(otherItems.get(8).getName());
					btnOther2.setText(otherItems.get(9).getName());
					btnOther3.setText(otherItems.get(10).getName());
					btnOther4.setText(otherItems.get(11).getName());
					boxOther1.setText(otherItems.get(8).getDescription() + "\nCost: "+otherItems.get(8).getCost() +"      Size: "+ otherItems.get(8).getSize());
					boxOther2.setText(otherItems.get(9).getDescription() + "\nCost: "+otherItems.get(9).getCost() +"      Size: "+ otherItems.get(9).getSize());
					boxOther3.setText(otherItems.get(10).getDescription() + "\nCost: "+otherItems.get(10).getCost() +"      Size: "+ otherItems.get(10).getSize());
					boxOther4.setText(otherItems.get(11).getDescription() + "\nCost: "+otherItems.get(11).getCost() +"      Size: "+ otherItems.get(11).getSize());
				} else if (page == 3) {
					page = 2;
					otherItemsLabel.setText("Other Items (Page " + page + ")");
					btnOther1.setText(otherItems.get(4).getName());
					btnOther2.setText(otherItems.get(5).getName());
					btnOther3.setText(otherItems.get(6).getName());
					btnOther4.setText(otherItems.get(7).getName());
					boxOther1.setText(otherItems.get(4).getDescription() + "\nCost: "+otherItems.get(4).getCost() +"      Size: "+ otherItems.get(4).getSize());
					boxOther2.setText(otherItems.get(5).getDescription() + "\nCost: "+otherItems.get(5).getCost() +"      Size: "+ otherItems.get(5).getSize());
					boxOther3.setText(otherItems.get(6).getDescription() + "\nCost: "+otherItems.get(6).getCost() +"      Size: "+ otherItems.get(6).getSize());
					boxOther4.setText(otherItems.get(7).getDescription() + "\nCost: "+otherItems.get(7).getCost() +"      Size: "+ otherItems.get(7).getSize());
				} else if (page == 2) {
					page = 1;
					otherItemsLabel.setText("Other Items (Page " + page + ")");
					btnOther1.setText(otherItems.get(0).getName());
					btnOther2.setText(otherItems.get(1).getName());
					btnOther3.setText(otherItems.get(2).getName());
					btnOther4.setText(otherItems.get(3).getName());
					boxOther1.setText(otherItems.get(0).getDescription() + "\nCost: "+otherItems.get(0).getCost() +"      Size: "+ otherItems.get(0).getSize());
					boxOther2.setText(otherItems.get(1).getDescription() + "\nCost: "+otherItems.get(1).getCost() +"      Size: "+ otherItems.get(1).getSize());
					boxOther3.setText(otherItems.get(2).getDescription() + "\nCost: "+otherItems.get(2).getCost() +"      Size: "+ otherItems.get(2).getSize());
					boxOther4.setText(otherItems.get(3).getDescription() + "\nCost: "+otherItems.get(3).getCost() +"      Size: "+ otherItems.get(3).getSize());
				}
			}
		});
		btnPrev.setFont(new Font("Arial", Font.PLAIN, 20));
		btnPrev.setBounds(513, 499, 121, 25);
		frame.getContentPane().add(btnPrev);
	}
}
