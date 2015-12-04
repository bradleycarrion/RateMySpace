package UserInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public abstract class SearchScreen extends JFrame implements ActionListener  {
	protected int cell_count;
	protected ArrayList<String> generic_titles = new ArrayList<String>();   // holds generic titles for each cell
	protected ArrayList<Integer> generic_rating = new ArrayList<Integer>();	// holds generic rating for each cell
	
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 500;
	
	protected String search_phrase;
	private String title;
	private JButton back;
	private JButton search_button;
	private JPanel main;
	private JTextField search;
	
	// override this with something that takes in 
	// Objects for either a house or a Landlord
	protected abstract void populate();
	
	// abstract constructor
    protected SearchScreen(String title, String search_phrase) {
    	super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.search_phrase = search_phrase;
		this.title = title;
		this.initializeGUI();
		this.addSubviews();
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setVisible(true);
		this.setResizable(false);
		this.populate();
		
		
    	cell_count = 0;
    }
    
    private void close()
	{
		setVisible(false);
		dispose();
	}
    
    private void addSubviews() {
    	this.addButtons();
    	this.addTable();
    	this.addSearch();
	}
    
    public void initializeGUI() {
    	
    	// the main panel get's created and added
    	main = new JPanel();
		main.setLayout(null);
		this.add(main);
		
    	
    	// Back button will close current window and open up a new main window
    	back = new JButton("Back");
    	back.setBounds(5, 5, 60, 40);
    	back.addActionListener(this);
    	
    	search = new JTextField();
    	search.setBounds((DEFAULT_WIDTH/4)-50, 20, (DEFAULT_WIDTH/2), 30);
    	System.out.println(this.title);
    	search.setText(this.title);
    	
    	search_button = new JButton("Search");
    	search_button.setBounds(((DEFAULT_WIDTH*3)/4)-50, 22, 100, 25);
    	search_button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	search_button.addActionListener(this);

    }
    
    private void addButtons() {
    	this.main.add(back);
    	this.main.add(search_button);
    }
    
    private void addTable() {
    	// add the table to the subview here
    }
    
    private void addSearch() {
    	// add the search bar attributes to the subview here
    	this.main.add(search);
    }
    
    @Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.search_button) {
			// search button clicked
			this.search_phrase = this.search.getText();
			this.populate();
			
		} else if (e.getSource() == this.back) {
			// back button clicked
			MainScreen back = new MainScreen();
 	       	close();
		}
		
	}
	
}
