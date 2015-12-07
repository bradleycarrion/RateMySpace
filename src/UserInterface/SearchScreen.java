package UserInterface;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;


public abstract class SearchScreen extends JFrame implements ActionListener  {
	protected int cell_count;
	
	protected static final int DEFAULT_WIDTH = 800;
	protected static final int DEFAULT_HEIGHT = 500;
	
	protected String search_phrase;
	protected String title;
	protected JButton back;
	protected JButton search_button;
	protected JPanel main;
	protected JTextField search;
	protected JButton more;
	
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
		
		
    	cell_count = 0;
    }
    
    protected void close()
	{
		setVisible(false);
		dispose();
	}
    
    private void addSubviews() {
    	this.addButtons();
    	this.addSearch();
	}
    
    @SuppressWarnings("deprecation")
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
    	
    	more = new JButton("More Info");
    	more.setBounds(((DEFAULT_WIDTH*3)/4)+60, 22, 100, 25);
    	more.addActionListener(this);
 
    }
    
    private void addButtons() {
    	this.main.add(back);
    	this.main.add(search_button);
    	this.main.add(more);
    }
    
    private void addSearch() {
    	// add the search bar attributes to the subview here
    	this.main.add(search);
    }
   
}
