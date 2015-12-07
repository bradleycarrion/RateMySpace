package UserInterface;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import mainpackage.QueryManager;
import objects.Landlord;
import objects.Review;

public class LandlordProfile extends ProfileScreen {
	Landlord landlord;
	ArrayList<Review> reviews;
	JButton review_button;

	public LandlordProfile(Landlord landlord) {
		super(landlord.name);
		this.landlord = landlord;
		review_button = new JButton("Review Me");
		review_button.setBounds(super.DEFAULT_WIDTH/2-50, super.DEFAULT_HEIGHT-75, 100, 50);
		review_button.addActionListener(this);
		super.main.add(review_button);
		populateReviews();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	protected void populateReviews() {
		reviews = QueryManager.getInstance().getLandlordReviews(landlord);
		System.out.println(reviews.size());
		if (reviews.size() == 0) {
			JOptionPane.showMessageDialog(this,
				    "There are no reviews on this Landlord",
				    "Inane error",
				    JOptionPane.ERROR_MESSAGE);
		} else {
			for (int i = 0; i < reviews.size(); ++i)
			{
				addReview(reviews.get(i));
			}
		}
	}
	
	private void addReview(Review review) {
		super.reviews_display.append(review.text + "\n\n\n");
	}
	
	

}
