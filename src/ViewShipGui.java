import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.SystemColor;

/**
 * This class displays information about the player's ship
 * @author Fergus and Oliver
 *
 */
public class ViewShipGui {

	private JFrame frame;

	/**
	 * Launch the application.
	 * @param game
	 */
	public void run(GameEnvironment game) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {	
					ViewShipGui window = new ViewShipGui(game);
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
	public ViewShipGui(GameEnvironment game) {
		initialize(game);
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
		
		JTextArea shipInfoBox = new JTextArea();
		shipInfoBox.setEditable(false);
		shipInfoBox.setBackground(SystemColor.menu);
		shipInfoBox.setFont(new Font("Arial", Font.PLAIN, 24));
		shipInfoBox.setBounds(178, 183, 316, 246);
		shipInfoBox.setText(game.getCurrentShip().toFullString(game.getCurrentHp()));
		frame.getContentPane().add(shipInfoBox);
		
		JLabel shipNameLabel = new JLabel(game.getCurrentShip().getName());
		shipNameLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		shipNameLabel.setBounds(208, 115, 185, 32);
		frame.getContentPane().add(shipNameLabel);
		
		
		
		
		
		
		
		
		
		
	}
}
