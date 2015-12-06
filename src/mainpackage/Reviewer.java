package mainpackage;

import java.util.ArrayList;

import objects.Review;

public class Reviewer {
	
	private QueryManager dbManager;
	private int userID = -1; 
	
	enum SearchType {
		Address,
		LandLordName
	}
	
	Reviewer(int userID) {
		this.dbManager = QueryManager.getInstance();
		this.userID = userID;
	}
	
	public void sendReview(String reviewText) {
		dbManager.sendReview(reviewText, userID);
	}
	
	public void flagReview(Review review, Review.Flag flag) {
		String flagName;
		switch (flag) {
			case Inappropriate:
				flagName = "Inappropriate";
				break;
			case Malicious:
				flagName = "Malicious";
				break;
			default: 
				flagName = "Unkown";
				break;
		}
		
		//Send Query
		dbManager.flagReview(review.id, flagName);
	}
	
	/*
	public ArrayList<Review> getReviews() {
		
		return dbManager.getReviews();
	}
	
	public ArrayList<Review> getReviews(String searchTerm, SearchType searchType) {
		//TODO parse searchType into int
		
		//Send search query and return
		return dbManager.getReviews();
	} */
}
