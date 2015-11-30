package mainpackage;

import java.util.ArrayList;

public class Reviewer {
	
	private QueryManager dbManager;
	
	enum SearchType {
		Address,
		LandLordName
	}
	
	Reviewer() {
		dbManager = new QueryManager();
	}
	
	public boolean sendReview(Review review) {
		//TODO Parse review into query.
		
		//Send Query
		dbManager.sendReview();
		
		//TODO Report success
		return false;
	}
	
	public boolean reportReview(Review review, Review.Flag flag) {
		//TODO Parse review and flag into query
		//TODO Store flag in review for later use
		
		//Send Query
		dbManager.reportReview();
		
		//TODO Report success
		return false;
	}
	
	public ArrayList<Review> getReviews(String searchTerm, SearchType searchType) {
		//TODO parse searchType into int
		
		//Send search query and return
		return dbManager.getReviews();
	}
}
