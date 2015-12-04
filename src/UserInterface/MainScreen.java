package UserInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class MainScreen extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	static final int DEFAULT_WIDTH = 800;
	static final int DEFAULT_HEIGHT = 500;
	
	//GUI Elements
	private JPanel main;
	private JToggleButton space;
	private JToggleButton landlord;
	private JButton search;
	private JTextField searchText;
	private JButton add;
	private ButtonGroup group;

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
		this.addTextBox();
	}
	
	private void initalizeGUI() {
		main = new JPanel();
		main.setLayout(null);
		space = new JToggleButton("Find a House");
		space.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		landlord = new JToggleButton("Find a Landlord");
		landlord.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		searchText = new JTextField();
		search = new JButton("Search");
		add = new JButton("Add");
		group = new ButtonGroup();
		group.add(landlord);
		group.add(space);
		landlord.addActionListener(this);
		space.addActionListener(this);
		search.addActionListener(this);
		add.addActionListener(this);
		
		
		//Setting Size and Position
		int toggle_button_width = DEFAULT_WIDTH/4;
		int toggle_button_height = 50;
		
		int toggle_button1_positionX = DEFAULT_WIDTH/4;
		int toggle_button1_positionY = DEFAULT_HEIGHT/4;
		
		int toggle_button2_positionX = DEFAULT_WIDTH/2;
		int toggle_button2_positionY = DEFAULT_HEIGHT/4;
		
		int toggle_searchBar_positionX = DEFAULT_WIDTH/4-DEFAULT_WIDTH/16;
		int toggle_searchBar_positionY = DEFAULT_HEIGHT/2;
		
		int toggle_add_positionX = DEFAULT_WIDTH*3/8;
		int toggle_add_positionY = (DEFAULT_HEIGHT/2)+toggle_button_height;
		
		int toggle_search_positionX = DEFAULT_WIDTH/4+DEFAULT_WIDTH/2-toggle_button_width/4;
		int toggle_search_positionY = toggle_searchBar_positionY;
		
		//this.space.setSize(toggle_button_width, toggle_button_height);
		//this.space.setLocation(toggle_button1_positionX, toggle_button1_positionY);
		this.space.setBounds(toggle_button1_positionX, toggle_button1_positionY, toggle_button_width, toggle_button_height);
		this.landlord.setBounds(toggle_button2_positionX, toggle_button2_positionY, toggle_button_width, toggle_button_height);
		//this.landlord.setSize(toggle_button_width, toggle_button_height);
		//this.landlord.setLocation(toggle_button2_positionX, toggle_button2_positionY);
		this.searchText.setBounds(toggle_searchBar_positionX, toggle_searchBar_positionY, toggle_button_width*2, toggle_button_height/2);
		this.add.setBounds(toggle_add_positionX, toggle_add_positionY, toggle_button_width, toggle_button_height);
		this.search.setBounds(toggle_search_positionX, toggle_search_positionY, toggle_button_width/2, toggle_button_height/2);

	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(landlord == e.getSource())
		{
			
		}
		if(space == e.getSource())
		{
			
		}
		if(search == e.getSource())
		{
			//gets the string being searched
			String string = searchText.getText();
			System.out.println(string);
			
		}
		if(add == e.getSource())
		{
			
		}
	}
	
	private void addButton() {
		this.add(main);
		this.main.add(this.space);
		this.main.add(this.landlord);
		this.main.add(this.search);
		this.main.add(this.add);
	}
	
	private void addTextBox()
	{
		this.main.add(this.searchText);
	}
}