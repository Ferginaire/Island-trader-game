import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

/**
 * This class displays the current island and 6 buttons.
 * View inventory will take you to the view inventory gui
 * View ship will take you to the view ship gui
 * Repair ship will fix any damage to your ship and reduce your gold by the cost
 * Go to shop will take you to the view shop gui
 * Travel will take you to the travel gui
 * End game will take you to the end game gui
 * 
 * @author Fergus and Oliver
 *
 */
public class DisplayIslandGui {

	private JFrame frame;
	private boolean repaired = false;

	/**
	 * Launch the application.
	 * @param game
	 */
	public static void run(GameEnvironment game) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayIslandGui window = new DisplayIslandGui(game);
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
	public DisplayIslandGui(GameEnvironment game) {
		initialize(game);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param game
	 */
	private void initialize(GameEnvironment game) {
		game.setFrom("Island");
		frame = new JFrame();
		frame.setBounds(450, 150, 637, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCurrentGold = new JLabel("Current gold: " + game.getGold());
		lblCurrentGold.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCurrentGold.setBounds(428, 55, 185, 33);
		frame.getContentPane().add(lblCurrentGold);
		
		JLabel reactiveLabel = new JLabel("");
		reactiveLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		reactiveLabel.setBounds(26, 92, 576, 32);
		frame.getContentPane().add(reactiveLabel);
		
		JLabel currentIslandLabel = new JLabel(game.getCurrentIsland().getName() + " Island");
		currentIslandLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		currentIslandLabel.setBounds(10, 10, 263, 46);
		frame.getContentPane().add(currentIslandLabel);
		
		JLabel whatDoLabel = new JLabel("What would you like to do?");
		whatDoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		whatDoLabel.setBounds(135, 134, 318, 33);
		frame.getContentPane().add(whatDoLabel);
		
		JButton btnNewButton = new JButton("   View \r\nInventory");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.openInventoryGui(game);
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.setBounds(10, 197, 263, 46);
		frame.getContentPane().add(btnNewButton);
		
		int damage = (game.getCurrentShip().getMaxHp()-game.getCurrentShip().getCurrentHp());
		int cost  = damage*GameEnvironment.repairCost;
		JButton btnRepairShip = new JButton("Repair ship (" + cost + " gold)");
		btnRepairShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (repaired == false) {
					if (damage == 0) {
						reactiveLabel.setText("Nothing to repair");
					} else if (cost > game.getGold()) {
						reactiveLabel.setText("You need "+cost+" gold to repair, please sell some items");
					} else {
						reactiveLabel.setText("You repaired "+damage+" damage. It cost you "+cost+" Gold");
						game.getCurrentShip().setCurrentHp(game.getCurrentShip().getMaxHp());
						game.setGold(game.getGold() - cost);
						btnRepairShip.setText("Ship repaired");
						lblCurrentGold.setText("Current gold: " + game.getGold());
						repaired = true;
					}
				}
			}
		});
		if (cost == 0) {
			btnRepairShip.setForeground(SystemColor.activeCaptionBorder);
		} else {
			btnRepairShip.setForeground(SystemColor.desktop);
		}
		btnRepairShip.setFont(new Font("Arial", Font.PLAIN, 20));
		btnRepairShip.setBounds(10, 278, 263, 46);
		frame.getContentPane().add(btnRepairShip);
		
		JButton btnTravel = new JButton("Travel");
		btnTravel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getCurrentShip().getCurrentHp() == game.getCurrentShip().getMaxHp()) {
					game.openDisplayTravelGui(game);
					frame.dispose();
				} else {
					reactiveLabel.setText("You must repair your ship to travel");
				}
				
			}
		});
		btnTravel.setFont(new Font("Arial", Font.PLAIN, 20));
		btnTravel.setBounds(10, 360, 263, 46);
		frame.getContentPane().add(btnTravel);
		
		JButton btnViewShip = new JButton("View ship");
		btnViewShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.openViewShip(game);
				frame.dispose();
			}
		});
		btnViewShip.setFont(new Font("Arial", Font.PLAIN, 20));
		btnViewShip.setBounds(350, 197, 263, 46);
		frame.getContentPane().add(btnViewShip);
		
		JButton btnGoToShop = new JButton("Go to shop");
		btnGoToShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.openDisplayStore(game);
				frame.dispose();
			}
		});
		btnGoToShop.setFont(new Font("Arial", Font.PLAIN, 20));
		btnGoToShop.setBounds(350, 278, 263, 46);
		frame.getContentPane().add(btnGoToShop);
		
		JButton btnEndGame = new JButton("End game");
		btnEndGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.openConfirmationGui(game, "game over", null);
				frame.dispose();
			}
		});
		btnEndGame.setFont(new Font("Arial", Font.PLAIN, 20));
		btnEndGame.setBounds(350, 360, 263, 46);
		frame.getContentPane().add(btnEndGame);
		
		JLabel daysLeft = new JLabel("Days left:          " + game.getDaysLeft());
		daysLeft.setFont(new Font("Arial", Font.PLAIN, 20));
		daysLeft.setBounds(428, 10, 185, 33);
		frame.getContentPane().add(daysLeft);
	}

}