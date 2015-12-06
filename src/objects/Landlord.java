package objects;

import java.util.ArrayList;

import mainpackage.*;

public class Landlord {
	public String name;
	public int id = -1;
	
	public Landlord(String name) {
		this.name = name;
	}
	
	public void setID(int id) {
		this.id = id;
	}
}
