package UserInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;


public abstract class SearchScreen extends JFrame implements ActionListener  {
	protected int cell_count;
	protected JTable infotable;
	protected JButton more;
	protected JScrollPane scroll;
	
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
    	
    	more = new JButton("More Info");
    	more.setBounds(((DEFAULT_WIDTH*3)/4)+60, 22, 100, 25);
    	more.addActionListener(this);
    	
    	TableModel dataModel = new AbstractTableModel() {
            public int getColumnCount() 
            { 
            	return 2; 
            }
            public int getRowCount() 
            { 
            	return 100;
            }
	       	 public Object getValueAt(int row, int col) 
	         { 
	         	return new Integer(row*col); 
	         }
    	};
    	
    	infotable = new JTable(dataModel);
    	infotable.setBounds(80,80,DEFAULT_WIDTH-80,DEFAULT_HEIGHT-30);
    	scroll = new JScrollPane();
    	scroll.setBounds(0,80,50,100);
    }
    
    private void addButtons() {
    	this.main.add(back);
    	this.main.add(search_button);
    	this.main.add(more);
    	this.main.add(scroll);
    }
    
    private void addTable() {
    	// add the table to the subview here
    	this.main.add(infotable);
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
			
		} if (e.getSource() == this.back) {
			// back button clicked
			MainScreen back = new MainScreen();
 	       	close();
		}
		
		if(e.getSource() == this.more)
		{
			
		}
		
		
	}
	
}
