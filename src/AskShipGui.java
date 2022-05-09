import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class creates A gui Which displays four buttons with ship names on them that have information boxes under them.
 * Clicking one of the buttons will prompt a confirmation gui to open
 * @author Fergus and Oliver
 *
 */
public class AskShipGui {

	private JFrame frame;

	/**
	 * Launch the application.
	 * @param game
	 */
	public static void run(GameEnvironment game) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AskShipGui window = new AskShipGui(game);
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
	public AskShipGui(GameEnvironment game) {
		initialize(game);
	}

	private void nextWindow(GameEnvironment game) {
		game.openConfirmationGui(game, "setup", null);
		frame.dispose();
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 * @param game
	 */
	private void initialize(GameEnvironment game) {
		frame = new JFrame();
		frame.setBounds(450, 150, 637, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea galleyInfoBox = new JTextArea();
		galleyInfoBox.setBackground(UIManager.getColor("Button.background"));
		galleyInfoBox.setEditable(false);
		galleyInfoBox.setFont(new Font("Arial", Font.PLAIN, 16));
		galleyInfoBox.setText("            Max HP:        100\r\n            Resistance:   10\r\n            Sail speed:    10\r\n            Cargo size:    30\r\n            Crew size:     100");
		galleyInfoBox.setBounds(40, 129, 244, 105);
		frame.getContentPane().add(galleyInfoBox);
		
		JTextArea sailingShipInfoBox = new JTextArea();
		sailingShipInfoBox.setEditable(false);
		sailingShipInfoBox.setBackground(UIManager.getColor("Button.background"));
		sailingShipInfoBox.setText("            Max HP:        50\r\n            Resistance:   15\r\n            Sail speed:    15\r\n            Cargo size:    25\r\n            Crew size:     30");
		sailingShipInfoBox.setFont(new Font("Arial", Font.PLAIN, 16));
		sailingShipInfoBox.setBounds(40, 303, 244, 105);
		frame.getContentPane().add(sailingShipInfoBox);
		
		JTextArea warshipInfoBox = new JTextArea();
		warshipInfoBox.setEditable(false);
		warshipInfoBox.setBackground(UIManager.getColor("Button.background"));
		warshipInfoBox.setFont(new Font("Arial", Font.PLAIN, 16));
		warshipInfoBox.setText("            Max HP:        125\r\n            Resistance:   20\r\n            Sail speed:    5\r\n            Cargo size:    40\r\n            Crew size:     80");
		warshipInfoBox.setBounds(338, 129, 244, 105);
		frame.getContentPane().add(warshipInfoBox);
		
		JTextArea longshipInfoBox = new JTextArea();
		longshipInfoBox.setEditable(false);
		longshipInfoBox.setBackground(UIManager.getColor("Button.background"));
		longshipInfoBox.setText("            Max HP:        20\r\n            Resistance:   10\r\n            Sail speed:    20\r\n            Cargo size:    20\r\n            Crew size:     20");
		longshipInfoBox.setFont(new Font("Arial", Font.PLAIN, 16));
		longshipInfoBox.setBounds(338, 303, 244, 105);
		frame.getContentPane().add(longshipInfoBox);
		
		JLabel selectShipLabel = new JLabel("Select a ship to captain");
		selectShipLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		selectShipLabel.setBounds(201, 33, 210, 21);
		frame.getContentPane().add(selectShipLabel);
		
		JButton selectGalleyButton = new JButton("Galley");
		selectGalleyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setCurrentShip(game.getShips().get(0));
				game.setCurrentHp(game.getCurrentShip().getMaxHp());
				nextWindow(game);
			}
		});
		selectGalleyButton.setFont(new Font("Arial", Font.PLAIN, 30));
		selectGalleyButton.setBounds(90, 79, 147, 40);
		frame.getContentPane().add(selectGalleyButton);
		
		JButton selectWarshipButton = new JButton("Warship");
		selectWarshipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setCurrentShip(game.getShips().get(1));
				game.setCurrentHp(game.getCurrentShip().getMaxHp());
				nextWindow(game);
			}
		});
		selectWarshipButton.setFont(new Font("Arial", Font.PLAIN, 30));
		selectWarshipButton.setBounds(375, 79, 167, 40);
		frame.getContentPane().add(selectWarshipButton);
		
		JButton selectLongshipButton = new JButton("Longship");
		selectLongshipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setCurrentShip(game.getShips().get(3));
				game.setCurrentHp(game.getCurrentShip().getMaxHp());
				nextWindow(game);
			}
		});
		selectLongshipButton.setFont(new Font("Arial", Font.PLAIN, 30));
		selectLongshipButton.setBounds(375, 258, 179, 40);
		frame.getContentPane().add(selectLongshipButton);
		
		JButton selectSailingShipButton = new JButton("Sailing Ship");
		selectSailingShipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setCurrentShip(game.getShips().get(2));
				game.setCurrentHp(game.getCurrentShip().getMaxHp());
				nextWindow(game);
			}
		});
		selectSailingShipButton.setFont(new Font("Arial", Font.PLAIN, 30));
		selectSailingShipButton.setBounds(50, 258, 223, 40);
		frame.getContentPane().add(selectSailingShipButton);
	}
}
