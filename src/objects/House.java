package objects;

import java.util.ArrayList;

import mainpackage.*;

public class House {
	public Address address;
	public int id = -1;
	
	public House(Address address) {
		this.address = address;
	}
	
	public void setID(int id) {
		this.id = id;
	}
}
