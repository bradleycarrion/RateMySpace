package objects;

import java.util.ArrayList;

import mainpackage.QueryManager;

public class House {
	public Address address;
	public int id = -1;
	
	public House(Address address) {
		this.address = address;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public ArrayList<Review> getReviews() {
		return QueryManager.getInstance().getHouseReviews(this);
		
	}	
}
