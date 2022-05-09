import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class displays four buttons with the names of islands that the player can travel to. 
 * Each button has an information box under it with a description of the route and costs
 * Clicking a button will open the confirmation gui
 * @author Fergus and Oliver
 *
 */
public class DisplayTravelGui {

	private JFrame frame;
	private int dest;
	private int cur;
	private int islandNum;

	/**
	 * Launch the application.
	 * @param game
	 */
	public void run(GameEnvironment game) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayTravelGui window = new DisplayTravelGui(game);
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
	public DisplayTravelGui(GameEnvironment game) {
		initialize(game);
	}

	private boolean checkCanTravel(GameEnvironment game, int islandNum, JLabel reactiveLabel, ArrayList<Route> routes) {
		if (game.getTravelCost(game.getCurrentShip(), routes.get(islandNum), game.getCostPerCrewDay()) > game.getGold()) {// Check whether they got enough gold to pay crew
			reactiveLabel.setText("You don't have enough money to \npay your crew for a trip that long");
			return false;
		} else if (routes.get(islandNum).getDays() > game.getDaysLeft()) { //Check whether they have the days remaining
			reactiveLabel.setText("There aren't enough days remaining\n to make it to this Island");
			return false;
		} else {
			return true;
			
		}
		
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
		
		JLabel lblWhereGo = new JLabel("Where would you like to travel?");
		lblWhereGo.setFont(new Font("Arial", Font.PLAIN, 26));
		lblWhereGo.setBounds(117, 45, 431, 53);
		frame.getContentPane().add(lblWhereGo);
		
		JLabel reactiveLabel = new JLabel("");
		reactiveLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		reactiveLabel.setBounds(119, 10, 362, 46);
		frame.getContentPane().add(reactiveLabel);
		
		ArrayList<Route> routes = game.getCurrentIsland().getRoutes();
		game.updateRouteDays(routes, game.getCurrentShip());
		
		
		
		JButton btnIsland0 = new JButton("New button");
		btnIsland0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				islandNum = 0;
				if (checkCanTravel(game, islandNum, reactiveLabel, routes)) {
					Route currentRoute = routes.get(islandNum);
					if (currentRoute.getRouteIslands().get(0) == game.getCurrentIsland()) {
						dest = 1;
						cur = 0;
						game.openConfirmationGui(game, "dest1 cur0", currentRoute);
						frame.dispose();
					} else {
						dest = 0;
						cur = 1;
						game.openConfirmationGui(game, "dest0 cur1", currentRoute);
						frame.dispose();
					}
					
				}
			}
		});
		btnIsland0.setFont(new Font("Arial", Font.PLAIN, 20));
		btnIsland0.setBounds(32, 98, 266, 53);
		frame.getContentPane().add(btnIsland0);
		
		JButton btnIsland1 = new JButton("New button");
		btnIsland1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				islandNum = 1;
				if (checkCanTravel(game, islandNum, reactiveLabel, routes)) {
					Route currentRoute = routes.get(islandNum);
					if (currentRoute.getRouteIslands().get(0) == game.getCurrentIsland()) {
						dest = 1;
						cur = 0;
						game.openConfirmationGui(game, "dest1 cur0", currentRoute);
						frame.dispose();
					} else {
						dest = 0;
						cur = 1;
						game.openConfirmationGui(game, "dest0 cur1", currentRoute);
						frame.dispose();
					}
					
				}
			}
		});
		btnIsland1.setFont(new Font("Arial", Font.PLAIN, 20));
		btnIsland1.setBounds(308, 98, 266, 53);
		frame.getContentPane().add(btnIsland1);
		
		JButton btnIsland2 = new JButton("New button");
		btnIsland2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				islandNum = 2;
				if (checkCanTravel(game, islandNum, reactiveLabel, routes)) {
					Route currentRoute = routes.get(islandNum);
					if (currentRoute.getRouteIslands().get(0) == game.getCurrentIsland()) {
						dest = 1;
						cur = 0;
						game.openConfirmationGui(game, "dest1 cur0", currentRoute);
						frame.dispose();
					} else {
						dest = 0;
						cur = 1;
						game.openConfirmationGui(game, "dest0 cur1", currentRoute);
						frame.dispose();
					}
					
				}
			}
		});
		btnIsland2.setFont(new Font("Arial", Font.PLAIN, 20));
		btnIsland2.setBounds(32, 283, 266, 53);
		frame.getContentPane().add(btnIsland2);
		
		JButton btnIsland3 = new JButton("New button");
		btnIsland3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				islandNum = 3;
				if (checkCanTravel(game, islandNum, reactiveLabel, routes)) {
					Route currentRoute = routes.get(islandNum);
					if (currentRoute.getRouteIslands().get(0) == game.getCurrentIsland()) {
						dest = 1;
						cur = 0;
						game.openConfirmationGui(game, "dest1 cur0", currentRoute);
						frame.dispose();
					} else {
						dest = 0;
						cur = 1;
						game.openConfirmationGui(game, "dest0 cur1", currentRoute);
						frame.dispose();
					}
					
				}
			}
		});
		btnIsland3.setFont(new Font("Arial", Font.PLAIN, 20));
		btnIsland3.setBounds(308, 283, 266, 53);
		frame.getContentPane().add(btnIsland3);
		
		JTextArea txtBox0 = new JTextArea();
		txtBox0.setText("This will cost you : 20 gold\r\nAnd take 7 days");
		txtBox0.setFont(new Font("Arial", Font.PLAIN, 20));
		txtBox0.setBounds(32, 161, 266, 92);
		txtBox0.setText(routes.get(0).toString(game.getCurrentIsland()) + "\nAnd cost you " + (int)game.getTravelCost(game.currentShip, routes.get(0), game.getCostPerCrewDay()) + " gold.");
		frame.getContentPane().add(txtBox0);
		
		JTextArea txtBox1 = new JTextArea();
		txtBox1.setText("This will cost you : 20 gold\r\nAnd take 7 days");
		txtBox1.setFont(new Font("Arial", Font.PLAIN, 20));
		txtBox1.setBounds(308, 161, 266, 92);
		txtBox1.setText(routes.get(1).toString(game.getCurrentIsland()) + "\nAnd cost you " + (int)game.getTravelCost(game.currentShip, routes.get(1), game.getCostPerCrewDay()) + " gold.");
		frame.getContentPane().add(txtBox1);
		
		JTextArea txtBox2 = new JTextArea();
		txtBox2.setText("This will cost you : 20 gold\r\nAnd take 7 days");
		txtBox2.setFont(new Font("Arial", Font.PLAIN, 20));
		txtBox2.setBounds(32, 346, 266, 92);
		txtBox2.setText(routes.get(2).toString(game.getCurrentIsland()) + "\nAnd cost you " + (int)game.getTravelCost(game.currentShip, routes.get(2), game.getCostPerCrewDay()) + " gold.");
		frame.getContentPane().add(txtBox2);
		
		JTextArea txtBox3 = new JTextArea();
		txtBox3.setText("This will cost you : 20 gold\r\nAnd take 7 days");
		txtBox3.setFont(new Font("Arial", Font.PLAIN, 20));
		txtBox3.setBounds(308, 346, 266, 92);
		txtBox3.setText(routes.get(3).toString(game.getCurrentIsland()) + "\nAnd cost you " + (int)game.getTravelCost(game.currentShip, routes.get(3), game.getCostPerCrewDay()) + " gold.");
		frame.getContentPane().add(txtBox3);
		
		btnIsland0.setText(routes.get(0).getName(game.getCurrentIsland()));
		btnIsland1.setText(routes.get(1).getName(game.getCurrentIsland()));
		btnIsland2.setText(routes.get(2).getName(game.getCurrentIsland()));
		btnIsland3.setText(routes.get(3).getName(game.getCurrentIsland()));
		
		JLabel lblDaysLeft = new JLabel("Days left");
		lblDaysLeft.setFont(new Font("Arial", Font.PLAIN, 20));
		lblDaysLeft.setBounds(491, 10, 122, 41);
		frame.getContentPane().add(lblDaysLeft);
		lblDaysLeft.setText("Days left: " + game.getDaysLeft());
		
	}
}
