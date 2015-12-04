package UserInterface;

import javax.swing.JOptionPane;

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
	
	protected LandlordSearch(String search_phrase) {
		super("Search a Landlord", search_phrase);
		// TODO Auto-generated constructor stub
	}
	
}
