import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;

/**
 * This class displays the player's inventory. Next page can be pressed if the player's inventory is large
 * @author Fergus and Oliver
 *
 */
public class InventoryGui {

	private JFrame frame;
	private int page = 1;
	private int size = 0;
	private boolean nextVis = true;

	/**
	 * Launch the application.
	 * @param game
	 */
	public void run(GameEnvironment game) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryGui window = new InventoryGui(game);
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
	public InventoryGui(GameEnvironment game) {
		initialize(game);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param game
	 */
	private void initialize(GameEnvironment game) {
		size = game.inventory.size();
		
		frame = new JFrame();
		frame.setBounds(450, 150, 637, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getFrom() == "Store") {
					game.openDisplayStore(game);
					frame.dispose();
				} else {
					game.openDisplayIsland(game);
					frame.dispose();
				}
				
				
			}
		});
		backButton.setFont(new Font("Arial", Font.PLAIN, 20));
		backButton.setBounds(10, 10, 105, 41);
		frame.getContentPane().add(backButton);
		
		
		
		
		JLabel capacityLabel = new JLabel("Capacity: " + game.getCurrentCargo());
		capacityLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		capacityLabel.setBounds(457, 14, 156, 33);
		frame.getContentPane().add(capacityLabel);
		
		JLabel pageLabel = new JLabel("Page 1");
		pageLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		pageLabel.setBounds(259, 50, 125, 24);
		frame.getContentPane().add(pageLabel);
		
		JTextPane itemsBox = new JTextPane();
		itemsBox.setBackground(UIManager.getColor("ColorChooser.background"));
		itemsBox.setFont(new Font("Arial", Font.PLAIN, 17));
		itemsBox.setBounds(89, 84, 418, 341);
		itemsBox.setEditable(false);
		//itemsBox.setText("Yeet\nYeet\nYeet\nYeet\nYeet\nYeet\nYeet\nYeet\nYeet\nYeet\nYeet\nYeet\nYeet\nYeet"); // 14 max
		frame.getContentPane().add(itemsBox);
		
		JButton previousButton = new JButton("Prev");
		previousButton.setFont(new Font("Arial", Font.PLAIN, 20));
		previousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (page == 2) {
					page = 1;
					String str = "";
					ArrayList<Item> items = new ArrayList<Item>(game.inventory.keySet());
					for (int i = 0; ((i < 16) && (i < items.size())); i++) {
						str += items.get(i).getName()+ ": Quantity " + game.inventory.get(items.get(i)) + ", Base Price: "+items.get(i).getCost()+", Size: "+items.get(i).getSize()+"\n";
					}
					itemsBox.setText(str);
				}
				if (page == 1) {
					pageLabel.setText("Page 1");
				} else if (page == 2) {
					pageLabel.setText("Page 2");
				} 
				nextVis = true;
			}
		});
		previousButton.setBounds(10, 392, 78, 33);
		frame.getContentPane().add(previousButton);
		
		
		if (page == 1) {
			String str = "";
			if (game.inventory.isEmpty()) {
				str += "There are no items in your inventory";
				itemsBox.setText(str);
			} else {
			ArrayList<Item> items = new ArrayList<Item>(game.inventory.keySet());
				for (int i = 0; ((i < 16) && (i < items.size())); i++) {
					str += items.get(i).getName()+ ": Quantity " + game.inventory.get(items.get(i)) + ", Base Price: "+items.get(i).getCost()+", Size: "+items.get(i).getSize()+"\n";
				}
				itemsBox.setText(str);
		}
			
		
		
		
		JButton nextPageButton = new JButton("Next");
		nextPageButton.setFont(new Font("Arial", Font.PLAIN, 20));
		nextPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (page == 1) {
					page = 2;
					String str = "";
					ArrayList<Item> items = new ArrayList<Item>(game.inventory.keySet());
					for (int i = 16; ((i < 32) && (i < items.size())); i++) {
						str += items.get(i).getName()+ ": Quantity " + game.inventory.get(items.get(i)) + ", Base Price: "+items.get(i).getCost()+", Size: "+items.get(i).getSize()+"\n";
					}
					itemsBox.setText(str);
					
				} 
				if (page == 1) {
					pageLabel.setText("Page 1");
				} else if (page == 2) {
					pageLabel.setText("Page 2");
				} 
			}
		});
		nextPageButton.setBounds(509, 392, 104, 33);
		frame.getContentPane().add(nextPageButton);
		
		
		
		if (game.inventory.isEmpty() == true) {
			nextPageButton.setVisible(false);
			previousButton.setVisible(false);
		}
		
		
	}
}
}
