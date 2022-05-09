import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 * This class creates A gui Which displays four buttons with ship upgrades on them that have information boxes under them.
 * Clicking one of the buttons will apply the upgrade to the ship and reduce the gold by it's price
 * @author Fergus and Oliver
 *
 */
public class BuyUpgradesGui {

	private JFrame frame;

	/**
	 * Launch the application.
	 * @param game
	 */
	public void run(GameEnvironment game) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyUpgradesGui window = new BuyUpgradesGui(game);
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
	public BuyUpgradesGui(GameEnvironment game) {
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
				game.openDisplayStore(game);
				frame.dispose();
			}
		});
		frame.getContentPane().setLayout(null);
		backButton.setFont(new Font("Arial", Font.PLAIN, 20));
		backButton.setBounds(10, 10, 105, 41);
		frame.getContentPane().add(backButton);
		
		JLabel reactiveLabel = new JLabel("");
		reactiveLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		reactiveLabel.setBounds(125, 17, 457, 21);
		frame.getContentPane().add(reactiveLabel);
		
		JTextArea cargoInfoBox = new JTextArea();
		cargoInfoBox.setBackground(UIManager.getColor("Button.background"));
		cargoInfoBox.setEditable(false);
		cargoInfoBox.setFont(new Font("Arial", Font.PLAIN, 16));
		cargoInfoBox.setText(game.getUpgrades().get(0).guiToString());
		cargoInfoBox.setBounds(40, 129, 244, 105);
		frame.getContentPane().add(cargoInfoBox);
		
		JTextArea resistanceInfoBox = new JTextArea();
		resistanceInfoBox.setEditable(false);
		resistanceInfoBox.setBackground(UIManager.getColor("Button.background"));
		resistanceInfoBox.setText(game.getUpgrades().get(2).guiToString());
		resistanceInfoBox.setFont(new Font("Arial", Font.PLAIN, 16));
		resistanceInfoBox.setBounds(40, 303, 244, 105);
		frame.getContentPane().add(resistanceInfoBox);
		
		JTextArea hpInfoBox = new JTextArea();
		hpInfoBox.setEditable(false);
		hpInfoBox.setBackground(UIManager.getColor("Button.background"));
		hpInfoBox.setFont(new Font("Arial", Font.PLAIN, 16));
		hpInfoBox.setText(game.getUpgrades().get(1).guiToString());
		hpInfoBox.setBounds(338, 129, 244, 105);
		frame.getContentPane().add(hpInfoBox);
		
		JTextArea sailSpeedInfoBox = new JTextArea();
		sailSpeedInfoBox.setEditable(false);
		sailSpeedInfoBox.setBackground(UIManager.getColor("Button.background"));
		sailSpeedInfoBox.setText(game.getUpgrades().get(3).guiToString());
		sailSpeedInfoBox.setFont(new Font("Arial", Font.PLAIN, 16));
		sailSpeedInfoBox.setBounds(338, 303, 244, 105);
		frame.getContentPane().add(sailSpeedInfoBox);
		
		JLabel selectUpgradeLabel = new JLabel("Select an upgrade to buy");
		selectUpgradeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		selectUpgradeLabel.setBounds(176, 48, 303, 21);
		frame.getContentPane().add(selectUpgradeLabel);
		
		JButton selectCargoButton = new JButton("Cargo");
		selectCargoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getUpgrades().get(0).getCost() <= game.getGold()) {
					game.getCurrentShip().addUpgrade(game.upgrades.get(0), game);
					game.pay(game.upgrades.get(0).getCost());
					reactiveLabel.setText("You bought a Cargo upgrade");
				} else {
					reactiveLabel.setText("Not enough gold");
				}
			}
		});
		if (game.getUpgrades().get(0).getCost() > game.getGold()) {
			selectCargoButton.setForeground(SystemColor.activeCaptionBorder);
		} else {
			selectCargoButton.setForeground(SystemColor.desktop);
		}
		selectCargoButton.setFont(new Font("Arial", Font.PLAIN, 30));
		selectCargoButton.setBounds(49, 79, 224, 40);
		frame.getContentPane().add(selectCargoButton);
		
		JButton selectHpButton = new JButton("Hp");
		selectHpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getUpgrades().get(1).getCost() <= game.getGold()) {
					game.getCurrentShip().addUpgrade(game.upgrades.get(1), game);
					game.pay(game.upgrades.get(1).getCost());
					reactiveLabel.setText("You bought an Hp upgrade");
				} else {
					reactiveLabel.setText("Not enough gold");
				}
			}
		});
		if (game.getUpgrades().get(1).getCost() > game.getGold()) {
			selectHpButton.setForeground(SystemColor.activeCaptionBorder);
		} else {
			selectHpButton.setForeground(SystemColor.desktop);
		}
		selectHpButton.setFont(new Font("Arial", Font.PLAIN, 30));
		selectHpButton.setBounds(358, 79, 224, 40);
		frame.getContentPane().add(selectHpButton);
		
		JButton selectSailSpeedButton = new JButton("Sail Speed");
		selectSailSpeedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getUpgrades().get(3).getCost() <= game.getGold()) {
					game.getCurrentShip().addUpgrade(game.upgrades.get(3), game);
					game.pay(game.upgrades.get(3).getCost());
					reactiveLabel.setText("You bought a Sail Speed upgrade");
				} else {
					reactiveLabel.setText("Not enough gold");
				}
			}
		});
		if (game.getUpgrades().get(2).getCost() > game.getGold()) {
			selectSailSpeedButton.setForeground(SystemColor.activeCaptionBorder);
		} else {
			selectSailSpeedButton.setForeground(SystemColor.desktop);
		}
		selectSailSpeedButton.setFont(new Font("Arial", Font.PLAIN, 30));
		selectSailSpeedButton.setBounds(358, 258, 224, 40);
		frame.getContentPane().add(selectSailSpeedButton);
		
		JButton selectResistanceButton = new JButton("Resistance");
		selectResistanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getUpgrades().get(2).getCost() <= game.getGold()) {
					game.getCurrentShip().addUpgrade(game.upgrades.get(2), game);
					game.pay(game.upgrades.get(2).getCost());
					reactiveLabel.setText("You bought a Resistance upgrade");
				} else {
					reactiveLabel.setText("Not enough gold");
				}
			}
		});
		if (game.getUpgrades().get(3).getCost() > game.getGold()) {
			selectResistanceButton.setForeground(SystemColor.activeCaptionBorder);
		} else {
			selectResistanceButton.setForeground(SystemColor.desktop);
		}
		selectResistanceButton.setFont(new Font("Arial", Font.PLAIN, 30));
		selectResistanceButton.setBounds(49, 258, 224, 40);
		frame.getContentPane().add(selectResistanceButton);
	}
}
