package objects;

public class Address {
	public String street;
	public String city;
	public String state;
	public int zip;
	
	public Address(String street, String city, String state, int zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public String toString() {
		return street + ", " + city + ", " + state + ", " + Integer.toString(zip);
	}
}
