package UserInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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
	private JPanel border;
	private JToggleButton space;
	private JToggleButton landlord;
	private JButton search;
	private JTextField searchText;
	//private JButton add;
	private ButtonGroup group;
	private Font title_font;
	private JButton title;
	private JButton add_house;

	public MainScreen() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initalizeGUI();
		this.addSubviews();
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setVisible(true);
		this.setResizable(false);
		this.setBackground(Color.MAGENTA);
	}
	
	private void addSubviews() {
		this.addButton();
		this.addTextBox();
	}
	
	private void initalizeGUI() {
		main = new JPanel();
		main.setLayout(null);
		
		// creation of the title
		title_font = new Font("Lucida Calligraphy", 0, 45);
		title = new JButton();
		//title.setFont(title_font);
		title.setBounds((DEFAULT_WIDTH/4)+20, 30, DEFAULT_WIDTH/2, 74);
		ImageIcon johnsTitle = new ImageIcon("title_image.png");
		title.setIcon(johnsTitle);
		
		
		// setting up the border for content
		border = new JPanel();
		border.setBounds((DEFAULT_WIDTH/4)-75, 110, (DEFAULT_WIDTH/2)+150, (DEFAULT_HEIGHT/2)+57);
		border.setOpaque(true);
		border.setBackground(new Color(0,0,255,0));
		border.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		
		space = new JToggleButton("Find a House");
		search = new JButton("Search");
		search.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		landlord = new JToggleButton("Find a Landlord");
		searchText = new JTextField();
		add_house = new JButton("Add House");
		add_house.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		group = new ButtonGroup();
		group.add(landlord);
		group.add(space);
		
		
		landlord.addActionListener(this);
		space.addActionListener(this);
		search.addActionListener(this);
		add_house.addActionListener(this);
		
		
		//Setting Size and Position
		int toggle_button_width = DEFAULT_WIDTH/4;
		int toggle_button_height = 50;
		
		int toggle_button1_positionX = DEFAULT_WIDTH/4;
		int toggle_button1_positionY = DEFAULT_HEIGHT/4;
		
		int toggle_button2_positionX = DEFAULT_WIDTH/2;
		int toggle_button2_positionY = DEFAULT_HEIGHT/4;
		
		int searchBar_positionX = DEFAULT_WIDTH/4-DEFAULT_WIDTH/16;
		int searchBar_positionY = DEFAULT_HEIGHT/2;

		int add_positionY = (DEFAULT_HEIGHT/2)+75;
		
		int search_positionX = DEFAULT_WIDTH/4+DEFAULT_WIDTH/2-toggle_button_width/4;
		int search_positionY = searchBar_positionY;

		this.space.setBounds(toggle_button1_positionX, toggle_button1_positionY, toggle_button_width, toggle_button_height+30);
		this.landlord.setBounds(toggle_button2_positionX, toggle_button2_positionY, toggle_button_width, toggle_button_height+30);
		this.searchText.setBounds(searchBar_positionX, searchBar_positionY, toggle_button_width*2, toggle_button_height/2);
		this.search.setBounds(search_positionX, search_positionY, toggle_button_width/2, toggle_button_height/2);
		this.add_house.setBounds(DEFAULT_WIDTH/2-75, add_positionY, 150, 60);
		
		//Tells what to do when a toggle button is selected
		this.landlord.addItemListener(new HandlerClass(0));
		this.space.addItemListener(new HandlerClass(1));
	}
	
	private class HandlerClass implements ItemListener{
		private int x = 0;
		
		public HandlerClass(int whatIsBeingSearched)
		{
			x = whatIsBeingSearched;
		}

		public void itemStateChanged(ItemEvent e) {
			
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(search == e.getSource())
		{
			//gets the string being searched
			String string = searchText.getText();
			System.out.println(string);
			
			//opens a new window based on which toggle is selected
			if (space.isSelected()) {
				HouseSearch search = new HouseSearch(searchText.getText());
				this.close();
			} else {
				LandlordSearch search = new LandlordSearch(searchText.getText());
				this.close();
			}
			
		} else if (add_house == e.getSource()) {
			// add house window
			AddHouse add = new AddHouse();
			
		}
		
		if(title == e.getSource())
		{
			//Admin Mode
		}
	}
	
	private void addButton() {
		this.add(main);
		this.main.add(this.space);
		this.main.add(this.landlord);
		this.main.add(this.search);
		this.main.add(this.title);
		this.main.add(this.border);
		this.main.add(this.add_house);
	}
	
	private void addTextBox()
	{
		this.main.add(this.searchText);
	}
	
	public void close()
	{   
		setVisible(false);
		dispose();
	}
}