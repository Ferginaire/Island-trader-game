import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.SystemColor;

/**
 * This class displays two buttons Fight and Flee which open fight gui or flee gui respectively
 * @author Fergus and Oliver
 *
 */
public class FightOrFlightGui {

	private JFrame frame;

	/**
	 * Launch the application.
	 * @param game
	 * @param encounter
	 */
	public void run(GameEnvironment game, PirateEncounter encounter) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FightOrFlightGui window = new FightOrFlightGui(game, encounter);
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
	public FightOrFlightGui(GameEnvironment game, PirateEncounter encounter) {
		initialize(game, encounter);
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 * @param game
	 * @param encounter
	 */
	private void initialize(GameEnvironment game, PirateEncounter encounter) {
		frame = new JFrame();
		frame.setBounds(450, 150, 637, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnFlee = new JButton("Attempt to flee");
		btnFlee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.openFleeGui(game, encounter);
				frame.dispose();
			}
		});
		btnFlee.setFont(new Font("Arial", Font.PLAIN, 30));
		btnFlee.setBounds(318, 280, 295, 48);
		frame.getContentPane().add(btnFlee);
		
		JButton btnFight = new JButton("Attempt to fight");
		btnFight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.openFightGui(game, encounter);
				frame.dispose();
			}
		});
		btnFight.setFont(new Font("Arial", Font.PLAIN, 30));
		btnFight.setBounds(10, 280, 295, 48);
		frame.getContentPane().add(btnFight);
		
		JTextPane txtBox = new JTextPane();
		txtBox.setBackground(UIManager.getColor("ColorChooser.background"));
		txtBox.setEditable(false);
		txtBox.setFont(new Font("Arial", Font.PLAIN, 30));
		txtBox.setText("  Pirates are attacking!\nWhat do you want to do?");
		txtBox.setBounds(142, 10, 346, 78);
		frame.getContentPane().add(txtBox);
		
		JTextPane txtBox_1 = new JTextPane();
		txtBox_1.setText("If you fight you could plunder some booty yourself. But if you lose and they don't like what you have to offer, you'll be made to walk the plank!\nHowever if you flee, You will either take some damage, escape, or the pirates might catch up and board your ship!");
		txtBox_1.setFont(new Font("Arial", Font.PLAIN, 20));
		txtBox_1.setEditable(false);
		txtBox_1.setBackground(SystemColor.menu);
		txtBox_1.setBounds(79, 100, 455, 170);
		frame.getContentPane().add(txtBox_1);
		
		
	}

}
