package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class MainScreen extends JFrame {
	
	private static final long serialVersionUID = 1L;
	static final int DEFAULT_WIDTH = 500;
	static final int DEFAULT_HEIGHT = 300;
	
	//GUI Elements
	private JPanel main;
	private JToggleButton space;
	private JToggleButton landlord;
	private JButton search;

	public MainScreen() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initalizeGUI();
		this.addSubviews();
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	private void addSubviews() {
		this.addButton();
	}
	
	private void initalizeGUI() {
		main = new JPanel();
		main.setLayout(null);
		space = new JToggleButton("Find a House");
		space.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		landlord = new JToggleButton("Find a Landlord");
		landlord.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//Setting Size and Position
		int toggle_button_width = 100;
		int toggle_button_height = 50;
		
		int toggle_button1_positionX = DEFAULT_WIDTH/4;
		int toggle_button1_positionY = DEFAULT_HEIGHT/3;
		
		int toggle_button2_positionX = DEFAULT_WIDTH/2;
		int toggle_button2_positionY = DEFAULT_HEIGHT/3;
		
		//this.space.setSize(toggle_button_width, toggle_button_height);
		//this.space.setLocation(toggle_button1_positionX, toggle_button1_positionY);
		this.space.setBounds(toggle_button1_positionX, toggle_button1_positionY, toggle_button_width, toggle_button_height);
		this.landlord.setBounds(toggle_button2_positionX, toggle_button2_positionY, toggle_button_width, toggle_button_height);
		//this.landlord.setSize(toggle_button_width, toggle_button_height);
		//this.landlord.setLocation(toggle_button2_positionX, toggle_button2_positionY);

	}
	
	private void addButton() {
		this.add(main);
		this.main.add(this.space, BorderLayout.EAST);
		this.main.add(this.landlord, BorderLayout.WEST);
	}
}
