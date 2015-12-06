package UserInterface;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import objects.House;

public class HouseSearch extends SearchScreen {

	private static final long serialVersionUID = 1L;
	private ArrayList<House> results = new ArrayList<House>();

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
	
	public HouseSearch(String search_phrase) {
		super("Search a House", search_phrase);
	}

}
