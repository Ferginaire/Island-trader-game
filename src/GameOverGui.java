import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.UIManager;

/**
 * This class displays the game over screen with the players information and final score
 * @author Fergus and Oliver
 *
 */
public class GameOverGui {

	private JFrame frame;

	/**
	 * Launch the application.
	 * @param game
	 * @param type
	 */
	public static void run(GameEnvironment game, String type) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOverGui window = new GameOverGui(game, type);
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
	 * @param type
	 */
	public GameOverGui(GameEnvironment game, String type) {
		initialize(game, type);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param game
	 * @param type
	 */
	private void initialize(GameEnvironment game, String type) {
		frame = new JFrame();
		frame.setBounds(450, 150, 637, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		
		JTextPane messageBox = new JTextPane();
		messageBox.setBackground(UIManager.getColor("ColorChooser.background"));
		messageBox.setFont(new Font("Arial", Font.PLAIN, 20));
		messageBox.setBounds(117, 44, 381, 307);
		frame.getContentPane().add(messageBox);
		
		if (type == "choice") {
			int daysPlayed = game.getInitDays() - (game.getDaysLeft());
			if (game.getInitDays() == game.getDaysLeft()) {
				daysPlayed = 0;
			}
			int netProfit = game.getGold() - 400;
			int finalScore = ((5 * game.getGold()) + 20 * daysPlayed) - 2000;
			messageBox.setText("Your adventure has come to an end " + game.getCharacterName() + "\nInitial days: " + game.getInitDays() + "\nActual days played " + daysPlayed + "\nNet profit: " + netProfit + "\nFinal Score: " + finalScore);
			messageBox.setFont(new Font("Arial", Font.PLAIN, 30));
		}
		
		
	}
}
