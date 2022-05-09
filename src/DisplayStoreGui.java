import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * This class displays a store gui with four buttons
 * View inventory will take the player to the view inventory gui
 * Upgrade ship will take the player to the upgrades gui
 * Buy items will take the player to the display buy gui
 * Sell items will take the player to the display sell gui
 * @author Fergus and Oliver
 *
 */
public class DisplayStoreGui {

	private JFrame frame;

	/**
	 * Launch the application.
	 * @param game
	 */
	public void run(GameEnvironment game) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayStoreGui window = new DisplayStoreGui(game);
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
	 * 
	 */
	public DisplayStoreGui(GameEnvironment game) {
		initialize(game);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param game
	 */
	private void initialize(GameEnvironment game) {
		game.setFrom("Store");
		frame = new JFrame();
		frame.setBounds(450, 150, 637, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.openDisplayIsland(game);
				frame.dispose();
			}
		});
		frame.getContentPane().setLayout(null);
		backButton.setFont(new Font("Arial", Font.PLAIN, 20));
		backButton.setBounds(10, 10, 105, 41);
		frame.getContentPane().add(backButton);
		
		JLabel welcomeLabel = new JLabel("Welcome to " + game.getCurrentIsland().getName() + " island store");
		welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		welcomeLabel.setBounds(133, 74, 337, 52);
		frame.getContentPane().add(welcomeLabel);
		
		JLabel goldLabel = new JLabel("Current Gold: " + game.getGold());
		goldLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		goldLabel.setBounds(417, 10, 196, 32);
		frame.getContentPane().add(goldLabel);
		
		JButton btnViewInventory = new JButton("View Inventory");
		btnViewInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.openInventoryGui(game);
				frame.dispose();
			}
		});
		btnViewInventory.setFont(new Font("Arial", Font.PLAIN, 20));
		btnViewInventory.setBounds(87, 192, 189, 100);
		frame.getContentPane().add(btnViewInventory);
		
		JButton btnUpgrades = new JButton("Upgrade Ship");
		btnUpgrades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.openBuyUpgradesGui(game);
				frame.dispose();
			}
		});
		btnUpgrades.setFont(new Font("Arial", Font.PLAIN, 20));
		btnUpgrades.setBounds(323, 192, 189, 100);
		frame.getContentPane().add(btnUpgrades);
		
		JButton btnBuyItems = new JButton("Buy Items");
		btnBuyItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.openDisplayBuyGui(game);
				frame.dispose();
			}
		});
		btnBuyItems.setFont(new Font("Arial", Font.PLAIN, 20));
		btnBuyItems.setBounds(87, 329, 189, 100);
		frame.getContentPane().add(btnBuyItems);
		
		JButton btnSellItems = new JButton("Sell Items");
		btnSellItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.openDisplaySellGui(game);
				frame.dispose();
			}
		});
		btnSellItems.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSellItems.setBounds(323, 329, 189, 100);
		frame.getContentPane().add(btnSellItems);
		
		
		
		
		
		
		
	}
}
