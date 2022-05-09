import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

/**
 * This class displays a gui with information about what is being confirmed and a yes and no button.
 * if type == setup, yes will take them to the current island and confirm their choices for name, days and ship
 * 
 * if type == game over, yes will take them to the game over screen and no will take them back to the island gui
 * 
 * if type == dest0 cur1 or dest1 cur0 Then yes will take them to the destination route according the string type.
 * No will take them back to the travel gui
 * 
 * 
 * @author Fergus and Oliver
 *
 */
public class ConfirmationGui {

	private JFrame frame;
	private int dest;
	private int cur;
	/**
	 * Launch the application.
	 * @param game
	 */
	public void run(GameEnvironment game, String type, Route currentRoute) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfirmationGui window = new ConfirmationGui(game, type, currentRoute);
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

	public ConfirmationGui(GameEnvironment game, String type, Route currentRoute) {
		initialize(game, type, currentRoute);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param game
	 * @param type
	 * @param currentRoute
	 */
	private void initialize(GameEnvironment game, String type, Route currentRoute) {
		frame = new JFrame();
		frame.setBounds(450, 150, 637, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblConfirm = new JLabel("Confirm?");
		lblConfirm.setFont(new Font("Arial", Font.PLAIN, 30));
		lblConfirm.setBounds(227, 198, 141, 58);
		frame.getContentPane().add(lblConfirm);
		
		JButton btnNo = new JButton("No");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (type == "setup") {
					SetupGameGui askNameWindow = new SetupGameGui(game);
					askNameWindow.run(game);
					frame.dispose();
				} else if (type == "game over") {
					game.openDisplayIsland(game);
					frame.dispose();
				} else if ((type == "dest0 cur1") || (type == "dest1 cur0")) {
					game.openDisplayTravelGui(game);
					frame.dispose();
				}
			}
		});
		btnNo.setFont(new Font("Arial", Font.PLAIN, 30));
		btnNo.setBounds(130, 264, 149, 48);
		frame.getContentPane().add(btnNo);
		
		JButton btnYes = new JButton("Yes");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (type == "setup") {
					game.openDisplayIsland(game);
					frame.dispose();
				} else if (type == "game over") {
					game.openGameOverGui(game, "choice");
					frame.dispose();
				} else if ((type == "dest0 cur1") || (type == "dest1 cur0")) {
					if (type == "dest0 cur1") {
						dest = 0;
						cur = 1;
					} else {
						dest = 1;
						cur = 0;
					}
					// Random encounter time
					game.setLatestGold(game.getGold());
					game.setCurrentIsland(currentRoute.getRouteIslands().get(dest));
					game.pay(game.getTravelCost(game.currentShip, currentRoute, game.getCostPerCrewDay())); // Dock money
					game.setDays(game.getDaysLeft() - (currentRoute.getDays())); // Dock days
					RandomEncounter encounter = game.determineEncounter(currentRoute);
					
					if (encounter instanceof PirateEncounter) {
						game.openPirateEncounterGui(game, (PirateEncounter)encounter);
						frame.dispose();
					} else if (encounter instanceof WeatherEncounter) {
						game.openNonPirateEncounterGui(game, encounter);
						frame.dispose();
					} else if (encounter instanceof RescueSailors) {
						game.openNonPirateEncounterGui(game, encounter);
						frame.dispose();
					} else {
						game.openDisplayIsland(game);
						frame.dispose();
					}
					
				}
			}
		});
		btnYes.setFont(new Font("Arial", Font.PLAIN, 30));
		btnYes.setBounds(289, 264, 149, 48);
		frame.getContentPane().add(btnYes);
		
		JTextPane messageBox = new JTextPane();
		messageBox.setBackground(UIManager.getColor("ColorChooser.background"));
		messageBox.setEditable(false);
		messageBox.setFont(new Font("Arial", Font.PLAIN, 20));
		messageBox.setBounds(130, 43, 379, 163);
		if (type == "setup") {
			messageBox.setText("Your name is " + game.getCharacterName() + "\n\nYou want to play for " + game.getDaysLeft() + " days\n\nAnd you will captain a " + game.getCurrentShip().getName());
		} else if (type == "game over") {
			messageBox.setText("Are you sure you want \nto end your adventure?");
		} else if ((type == "dest0 cur1") || (type == "dest1 cur0")) {
			if (type == "dest0 cur1") {
				dest = 0;
				cur = 1;
			} else {
				dest = 1;
				cur = 0;
			}
			messageBox.setText("\n\nThe voyage from " + currentRoute.getRouteIslands().get(cur).getName() + " to " + currentRoute.getRouteIslands().get(dest).getName() + "\nwill cost " + (int)game.getTravelCost(game.getCurrentShip(), currentRoute, game.getCostPerCrewDay()) + " gold and take " + currentRoute.getDays()+ " days");
		}
		frame.getContentPane().add(messageBox);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}