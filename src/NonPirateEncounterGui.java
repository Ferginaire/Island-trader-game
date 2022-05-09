import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

/**
 * This class displays a message describing what happened on their journey. alters values based on what the encounter was 
 * @author Fergus and Oliver
 *
 */
public class NonPirateEncounterGui {

	private JFrame frame;

	/**
	 * Launch the application.
	 * @param game
	 * @param encounter
	 */
	public void run(GameEnvironment game, RandomEncounter encounter) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NonPirateEncounterGui window = new NonPirateEncounterGui(game, encounter);
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
	public NonPirateEncounterGui(GameEnvironment game, RandomEncounter encounter) {
		initialize(game, encounter);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param game
	 * @param encounter 
	 */
	private void initialize(GameEnvironment game, RandomEncounter encounter) {
		frame = new JFrame();
		frame.setBounds(450, 150, 637, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane textArea = new JTextPane();
		textArea.setBackground(UIManager.getColor("ColorChooser.background"));
		textArea.setText(encounter.getDescription());
		textArea.setFont(new Font("Arial", Font.PLAIN, 20));
		textArea.setEditable(false);
		textArea.setBounds(84, 66, 442, 238);
		frame.getContentPane().add(textArea);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.openDisplayIsland(game);
				frame.dispose();
			}
		});
		btnContinue.setFont(new Font("Arial", Font.PLAIN, 20));
		btnContinue.setBounds(349, 330, 181, 50);
		frame.getContentPane().add(btnContinue);
		
		if (encounter instanceof WeatherEncounter) {
			game.setCurrentHp(game.getCurrentShip().getCurrentHp() - ((WeatherEncounter)encounter).getDamage());
			game.setDays(game.getDaysLeft() - ((WeatherEncounter)encounter).getDelay());
		} else if (encounter instanceof RescueSailors) { // Rescue sailors encounter
			game.setGold(game.getGold() + ((RescueSailors)encounter).getReward());
		}
		
		
	}

}
