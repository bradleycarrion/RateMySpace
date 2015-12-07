package UserInterface;

import java.awt.event.ActionEvent;

import mainpackage.QueryManager;
import objects.House;
import objects.HouseReview;
import objects.Review;

public class HouseReviewScreen extends ReviewScreen {
	private House house;
	
	public HouseReviewScreen(House house) {
		super();
		this.house = house;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == super.submit_review) {
			QueryManager.getInstance().sendHouseReview(new HouseReview(this.house, super.review.getText()));
			super.close();
		}
	}

}
