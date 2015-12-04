package objects;

import java.util.ArrayList;

import mainpackage.*;

public class House {
	public Address address;
	public ArrayList<Review> reviews;   // current reviews
	public NumericalRating overall_ratings;
	
	public House(Address address, ArrayList<Review> reviews) {
		this.address = address;
		this.reviews = reviews;	
		//this.overall_ratings.ratings = reviews;
	}
}
