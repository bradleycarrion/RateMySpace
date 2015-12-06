package objects;

import java.util.ArrayList;

import mainpackage.*;

public class Landlord {
	public String name;
	public ArrayList<House> properties; // current properties
	public int id = -1;
	
	public Landlord(String name, ArrayList<House> properties) {
		this.name = name;
		this.properties = properties;
	}
	
	public void setID(int id) {
		this.id = id;
	}
}
