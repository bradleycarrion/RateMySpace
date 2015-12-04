package UserInterface;

import java.util.ArrayList;

public abstract class SearchScreen {
	protected int cell_count;
	protected ArrayList<String> generic_titles = new ArrayList<String>();   // holds generic titles for each cell
	protected ArrayList<Integer> generic_rating = new ArrayList<Integer>();	// holds generic rating for each cell
	
	// override this with something that takes in 
	// Objects for either a house or a Landlord
	protected abstract void populate();
	
	// abstract constructor
    SearchScreen() {
    	cell_count = 0;
    }
	
}
