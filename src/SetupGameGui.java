import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class creates a gui that prompts player to input name and days into two input boxes. 
 * Prints an error message if either is invalid
 * @author Fergus and Oliver	
 *
 */
public class SetupGameGui {

	private JFrame frame;
	private JTextField nameBox;
	private JTextField daysInputBox;

	/**
	 * Launch the application.
	 * @param game
	 */
	public void run(GameEnvironment game) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetupGameGui window = new SetupGameGui(game);
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
	public SetupGameGui(GameEnvironment game) {
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
		
		JLabel askNameString = new JLabel("What is your name?");
		askNameString.setBounds(240, 80, 172, 30);
		frame.getContentPane().add(askNameString);
		
		nameBox = new JTextField();
		nameBox.setBounds(203, 137, 172, 19);
		frame.getContentPane().add(nameBox);
		nameBox.setColumns(10);
		
		
		
		JLabel daysLabel = new JLabel("How many days would you like to play? [30-50]");
		daysLabel.setBounds(149, 238, 330, 13);
		frame.getContentPane().add(daysLabel);
		
		daysInputBox = new JTextField();
		daysInputBox.setBounds(240, 276, 96, 19);
		frame.getContentPane().add(daysInputBox);
		daysInputBox.setColumns(10);
		
		JLabel invalidInputLabel = new JLabel("");
		invalidInputLabel.setBounds(224, 329, 172, 13);
		frame.getContentPane().add(invalidInputLabel);
		
		JButton nextButton = new JButton("Continue");
		nextButton.addActionListener(new ActionListener() {
			boolean ready = false;
			public void actionPerformed(ActionEvent e) {
				if (nameBox.getText().isBlank() != true) {
					game.setCharacterName(nameBox.getText());
					ready = true;
				} else {
					ready = false;
					invalidInputLabel.setText("Invalid Name, try again");
				}
				String text = daysInputBox.getText();
				try {
					int nums = Integer.parseInt(text);
					if (nums >= 30 && nums <= 50) {
						game.setInitDays(nums); 
						game.setDays(nums);
						if (ready == true) {
							game.openAskShipGui(game);
							frame.dispose();
						}
					} else {
						invalidInputLabel.setText("Invalid Days, try again");
					}
				} catch (NumberFormatException ee) {
					invalidInputLabel.setText("Invalid Days, try again");
				}
				
			}
		});
		nextButton.setBounds(474, 415, 114, 21);
		frame.getContentPane().add(nextButton);
	}
}
