package objects;

import java.util.ArrayList;

import mainpackage.*;

public class Landlord {
	public String name;
	public ArrayList<House> properties; // current properties
	public ArrayList<Review> reviews;   // current reviews
	
	public Landlord(String name, ArrayList<House> properties) {
		this.name = name;
		this.properties = properties;
	}
}
