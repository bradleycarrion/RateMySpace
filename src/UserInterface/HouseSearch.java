package UserInterface;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mainpackage.QueryManager;
import objects.Address;
import objects.House;

public class HouseSearch extends SearchScreen {

	private static final long serialVersionUID = 1L;
	private ArrayList<House> results = new ArrayList<House>();
	private JTable table;
	String[] colName = new String[] { "Results" };

	@Override
	protected void populate() {
		// Check if there were any results
		results = QueryManager.getInstance().getHouses(search_phrase);
		cell_count = results.size();
		System.out.println(search_phrase);
		
		if (cell_count == 0) {
			JOptionPane.showMessageDialog(this,
				    "Nothing matches your search criteria",
				    "Inane error",
				    JOptionPane.ERROR_MESSAGE);
		}
		
		Object[][] houses = new Object[results.size()][1];
		for (int i = 0; i < results.size(); ++i) {
			houses[i][0] = results.get(i).address.street;	
		}
		
		//JScrollPane scroll = new JScrollPane();
		//this.main.add(scroll);
		
		table = new JTable(houses, colName);
		table.setBounds(0, 80, super.DEFAULT_WIDTH, super.DEFAULT_HEIGHT);
		
		/*scroll.setViewportView(table);
		scroll.setBounds(0, 80, super.DEFAULT_WIDTH, super.DEFAULT_HEIGHT);*/
	}
	
	public HouseSearch(String search_phrase) {
		super("Search a House", search_phrase);
		populate();
		this.main.add(table);
	}
	
	 @Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == super.more) {
			 if (table.getSelectedRow() == -1) {
				 JOptionPane.showMessageDialog(this,
						    "You must select a cell first",
						    "Inane error",
						    JOptionPane.ERROR_MESSAGE);
			 } else {
				 HouseProfile profile = new HouseProfile(results.get(table.getSelectedRow()));
			 }
		 }
		 
		 // TODO Auto-generated method stub
		if (e.getSource() == super.search_button) {
			// search button clicked
			this.search_phrase = super.search.getText();
			this.populate();
			
		} if (e.getSource() == super.back) {
			// back button clicked
			MainScreen back = new MainScreen();
 	       	super.close();
		} 
	 }
}
