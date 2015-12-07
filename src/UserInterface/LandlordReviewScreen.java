package UserInterface;

import java.awt.event.ActionEvent;

import mainpackage.QueryManager;
import objects.Landlord;
import objects.LandlordReview;

public class LandlordReviewScreen extends ReviewScreen {

	
private Landlord landlord;
	
	public LandlordReviewScreen(Landlord landlord) {
		super();
		this.landlord = landlord;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == super.submit_review) {
			QueryManager.getInstance().sendLandlordReview(new LandlordReview(this.landlord, super.review.getText()));
			super.close();
		}
	}
}
