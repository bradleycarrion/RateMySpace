package objects;

public class Address {
	public String street;
	public String city;
	public String state;
	public String zip;
	
	public Address(String street, String city, String state, String zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public String asString() {
		return street + ", " + city + ", " + state + ", " + zip;
	}

}
