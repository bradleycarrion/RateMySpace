package UserInterface;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import objects.Address;
import objects.House;
import objects.Landlord;

public class LandlordSearch extends SearchScreen {

	private static final long serialVersionUID = 1L;

	@Override
	protected void populate() {
		// Check if there were any results
				if (cell_count == 0) {
					JOptionPane.showMessageDialog(this,
						    "Nothing matches your search criteria",
						    "Inane error",
						    JOptionPane.ERROR_MESSAGE);
				}
	}
	
	public LandlordSearch(String search_phrase) {
		super("Search a Landlord", search_phrase);
		populate();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == super.more) {
			 LandlordProfile profile = new LandlordProfile(new Landlord("Name"));
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
