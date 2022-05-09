import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;

/**
 * 
 * This class displays a pirate encounter where the player flees from the pirates.
 * Both parties get to rolled 4 dice but the players adds their sail speed modifier. The highest total wins. If the pirates win. They damage the ship.
 * If they win by more than 6 then they board and plunder
 * If the player wins, they get away.
 * displays game over if they lose by more than 6 and don't have enough value to satiate the pirates. Otherwise displays island gui
 * @author Fergus and Oliver
 *
 */
public class FleeGui {

	private JFrame frame;
	private int page = 1;
	private String str;
	private boolean stole;
	/**
	 * Launch the application.
	 * @param encounter
	 * @param game
	 */
	public void run(GameEnvironment game, PirateEncounter encounter) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FleeGui window = new FleeGui(game, encounter);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param encounter
	 * @param game
	 */
	public FleeGui(GameEnvironment game, PirateEncounter encounter) {
		initialize(game, encounter);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param encounter
	 * @param game
	 */
	private void initialize(GameEnvironment game, PirateEncounter encounter) {
		frame = new JFrame();
		frame.setBounds(450, 150, 637, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane explainBox = new JTextPane();
		explainBox.setBackground(UIManager.getColor("ColorChooser.background"));
		explainBox.setEditable(false);
		explainBox.setFont(new Font("Arial", Font.PLAIN, 20));
		explainBox.setBounds(83, 54, 443, 112);
		explainBox.setText("You and the pirates will both roll four 6 sided dice but you get to add your ship's sail speed modifier.\n         Whoever rolls the highest is faster");
		frame.getContentPane().add(explainBox);
		
		JTextPane playerRollsBox = new JTextPane();
		playerRollsBox.setBackground(UIManager.getColor("ColorChooser.background"));
		playerRollsBox.setEditable(false);
		playerRollsBox.setFont(new Font("Arial", Font.PLAIN, 20));
		playerRollsBox.setBounds(202, 200, 73, 154);
		frame.getContentPane().add(playerRollsBox);
		
		JTextPane pirateRollsBox = new JTextPane();
		pirateRollsBox.setBackground(UIManager.getColor("ColorChooser.background"));
		pirateRollsBox.setEditable(false);
		pirateRollsBox.setFont(new Font("Arial", Font.PLAIN, 20));
		pirateRollsBox.setBounds(353, 200, 73, 154);
		frame.getContentPane().add(pirateRollsBox);
		
		JLabel lblYourRolls = new JLabel("Your rolls");
		lblYourRolls.setFont(new Font("Arial", Font.PLAIN, 20));
		lblYourRolls.setBounds(176, 167, 114, 36);
		frame.getContentPane().add(lblYourRolls);
		
		JLabel lblPirateRolls = new JLabel("Pirates' rolls\r\n");
		lblPirateRolls.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPirateRolls.setBounds(313, 167, 149, 36);
		frame.getContentPane().add(lblPirateRolls);
		
		JTextPane reactiveBox = new JTextPane();
		reactiveBox.setBackground(UIManager.getColor("ColorChooser.background"));
		reactiveBox.setEditable(false);
		reactiveBox.setFont(new Font("Arial", Font.PLAIN, 20));
		reactiveBox.setBounds(112, 312, 397, 167);
		frame.getContentPane().add(reactiveBox);
		
		ArrayList<Integer> playerRolls = new ArrayList<Integer>();
		ArrayList<Integer> pirateRolls = new ArrayList<Integer>();
		playerRolls.add(game.rollD6());
		playerRolls.add(game.rollD6());
		playerRolls.add(game.rollD6());
		playerRolls.add(game.rollD6());
		int speedMod = (game.getCurrentShip().getSailSpeed() - 10) / 2;
		int playerSum = playerRolls.get(0) + playerRolls.get(1) + playerRolls.get(2) + playerRolls.get(3) + speedMod; 
		
		pirateRolls.add(game.rollD6());
		pirateRolls.add(game.rollD6());
		pirateRolls.add(game.rollD6());
		pirateRolls.add(game.rollD6());
		int pirateSum = pirateRolls.get(0) + pirateRolls.get(1) + pirateRolls.get(2) + pirateRolls.get(3);
		String str = "Your total is " + playerSum + " after adding your ship's speed modifier " + speedMod + ". The pirates' total is " + pirateSum + ".\n";
		
		
		JButton btnNewButton = new JButton("Continue");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (page == 1) {
					playerRollsBox.setText(playerRolls.get(0) + "\n");
				} else if (page == 2) {
					pirateRollsBox.setText(pirateRolls.get(0) + "\n");
				} else if (page == 3) {
					playerRollsBox.setText(playerRolls.get(0) + "\n" + playerRolls.get(1) + "\n");
				} else if (page == 4) {
					pirateRollsBox.setText(pirateRolls.get(0) + "\n" + pirateRolls.get(1) + "\n");
				} else if (page == 5) {
					playerRollsBox.setText(playerRolls.get(0) + "\n" + playerRolls.get(1) + "\n" + playerRolls.get(2) + "\n");
				} else if (page == 6) {
					pirateRollsBox.setText(pirateRolls.get(0) + "\n" + pirateRolls.get(1) + "\n" + pirateRolls.get(2) + "\n");
				} else if (page == 7) {
					playerRollsBox.setText(playerRolls.get(0) + "\n" + playerRolls.get(1) + "\n" + playerRolls.get(2) + "\n" + playerRolls.get(3));
				} else if (page == 8) {
					pirateRollsBox.setText(pirateRolls.get(0) + "\n" + pirateRolls.get(1) + "\n" + pirateRolls.get(2) + "\n" + pirateRolls.get(3));
				} else if (page == 9) {
					
					reactiveBox.setText(str);
				} else if (page == 10) {
					if (playerSum >= pirateSum) {
						reactiveBox.setText(str + "The pirates couldn't gain on you! You got away unharmed!");
					} else if ((pirateSum - playerSum) <= 6) { // If player lost by 6 or less
						reactiveBox.setText(str + "The pirates caught up to you! They damaged your ship but you still managed to escape!");
						game.getCurrentShip().takeDamage(encounter.getDamage());
					} else { // If player lost by more than 6
						reactiveBox.setText(str + "The pirates caught up to you and boarded your ship!"); 
						if (encounter.getStealAmount() > game.getGold()) { // If they take all your money and still want more
							if (game.stealFromItems(encounter.getStealAmount())) { // If they take your money and are satisfied with your items
								stole = true;
								reactiveBox.setText(str + "The pirates stole all your money and some of your items!\nThey were happy with their plunder and let you sail away");
							} else { // They take your money and aren't satisfied with your items
								stole = false;
								reactiveBox.setText(str + "The pirates weren't satisfied with your booty and \nforced you and your crew to walk the plank!");
							}
						} else { // They take some money and are happy with that
							reactiveBox.setText(str + "The pirates were satisfied with the amount of gold you had \nto offer. They let you leave with your items and your lives.");
							game.setGold(game.getGold() - encounter.getStealAmount());
						}
					}
				}  else if (page == 11) {
					if (playerSum >= pirateSum) {
						game.openDisplayIsland(game);
						frame.dispose();
					} else if ((pirateSum - playerSum) <= 6) { // If player lost by 6 or less
						game.openDisplayIsland(game);
						frame.dispose();
					} else { // If player lost by more than 6
						if (encounter.getStealAmount() > game.getGold()) { // If they take all your money and still want more
							if (stole) { // If they take your money and are satisfied with your items
								game.openDisplayIsland(game);
								frame.dispose();
							} else { // They take your money and aren't satisfied with your items
								game.openGameOverGui(game, "Pirates");
								frame.dispose();
							}
						} else { // They take some money and are happy with that
							game.openDisplayIsland(game);
							frame.dispose();
						}
					}
				}
				page++;
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.setBounds(488, 254, 125, 59);
		frame.getContentPane().add(btnNewButton);
		
		
		
		
		
		
		
	}

}
