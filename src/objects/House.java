package objects;

import java.util.ArrayList;

import mainpackage.*;

public class House {
	public String address;
	public ArrayList<Review> reviews;   // current reviews
	public NumericalRating overall_ratings;
	
	public House(String address, ArrayList<Review> reviews) {
		this.address = address;
		this.reviews = reviews;	
		this.overall_ratings.ratings = reviews;
	}
}
