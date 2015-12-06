package objects;


public class LandlordReview extends Review {
	
	public Landlord landlord;
	
	public LandlordReview(Landlord landlord, String text) {
		this.landlord = landlord;
		this.text = text;
	}
}
