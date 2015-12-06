package objects;

public class HouseReview extends Review {

	public House house;
	
	public HouseReview(House house, String text) {
		this.house = house;
		this.text = text;
	}
}
