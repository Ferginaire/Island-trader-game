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
 * This class displays a pirate encounter where the player fights the pirates.
 * each player gets rolled 4 dice and adds their resistance modifier. The highest total wins. If the pirates win. They plunder the player.
 * If the player wins, they plunder the pirates.
 * displays game over if they lose and don't have enough value to satiate the pirates. Otherwise displays island gui
 * @author Fergus and oliver
 *
 */
public class FightGui {

	private JFrame frame;
	private int page = 1;
	private String str;
	private boolean stole;
	/**
	 * Launch the application.
	 * @param game
	 * @param encounter
	 */
	public void run(GameEnvironment game, PirateEncounter encounter) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FightGui window = new FightGui(game, encounter);
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
	 * @param encounter
	 */
	public FightGui(GameEnvironment game, PirateEncounter encounter) {
		initialize(game, encounter);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param game
	 * @param encounter - The variables of this particular pirate encounter
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
		explainBox.setBounds(83, 10, 443, 156);
		explainBox.setText("You and the pirates will both roll four 6 sided dice and you each add your ship's resistance modifier.\n         Whoever rolls the highest is faster");
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
		int playerResMod = (game.getCurrentShip().getResistance() - 10) / 2;
		int playerSum = playerRolls.get(0) + playerRolls.get(1) + playerRolls.get(2) + playerRolls.get(3) + playerResMod; 
		
		pirateRolls.add(game.rollD6());
		pirateRolls.add(game.rollD6());
		pirateRolls.add(game.rollD6());
		pirateRolls.add(game.rollD6());
		int pirateResMod = (encounter.getResistance() - 10) / 2;
		int pirateSum = pirateRolls.get(0) + pirateRolls.get(1) + pirateRolls.get(2) + pirateRolls.get(3) + pirateResMod;
		str = "Your total is " + playerSum + " after adding your ship's resistance modifier " + playerResMod + ". The pirates' total is " + pirateSum + " after adding their resitance modifier " + pirateResMod + ".\n";
		
		
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
					if (playerSum > pirateSum) {
						game.setGold(game.getGold() + encounter.getStealAmount());
						reactiveBox.setText("You won the fight! You got " + encounter.getStealAmount() + " gold and you and your crew got away unharmed!");
				} else if (playerSum < pirateSum) {
					str+= "The pirates won and boarded your ship!\n";
					reactiveBox.setText(str); 
					if (encounter.getStealAmount() > game.getGold()) { // If they take all your money and still want more
						if (game.stealFromItems((encounter.getStealAmount()))) { // If they take your money and are satisfied with your items
							stole = true;
							reactiveBox.setText(str + "The pirates stole all your money and some of your items! They were happy with their plunder and let you sail away");
						} else { // They take your money and aren't satisfied with your items
							stole = false;
							reactiveBox.setText(str + "The pirates weren't satisfied with your booty and forced you and your crew to walk the plank!");
						}
					} else { // They take some money and are happy with that
						game.setGold(game.getGold() - encounter.getStealAmount());
//						reactiveBox.setFont(new Font("Arial", Font.PLAIN, 14));
						reactiveBox.setText("The pirates were satisfied with the amount of gold you had to offer. They let you leave with your items and your lives.\nYou lost " + encounter.getStealAmount() + " gold.");
					}
				} else {// Rolled the same
					reactiveBox.setText("You and the pirates are an even match! You agree to part ways maturely to avoid needless bloodshed.");
					 // Could give player the chance to let them go or risk another attack teehee
				}
			} else if (page == 11) {
				if (playerSum > pirateSum) {// won
					game.openDisplayIsland(game);
					frame.dispose();
				} else if (playerSum < pirateSum) { 
					if (encounter.getStealAmount() > game.getGold()) { // If they take all your money and still want more
						if (stole) { // If they take your money and are satisfied with your items
							game.openDisplayIsland(game);
							frame.dispose();
						} else { // They take your money and aren't satisfied with your items
							game.openGameOverGui(game, "choice");
							frame.dispose();
						}
					} else { // They take some money and are happy with that
						game.openDisplayIsland(game);
						frame.dispose();
					}
				} else {// Rolled the same
					game.openDisplayIsland(game);
					frame.dispose(); // Could give player the chance to let them go or risk another attack teehee
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
