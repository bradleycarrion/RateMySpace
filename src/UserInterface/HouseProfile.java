package UserInterface;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import mainpackage.QueryManager;
import objects.Review;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import objects.House;

public class HouseProfile extends ProfileScreen {
	private House house;
	private JTextArea reviews_display;
	private ArrayList<Review> reviews = new ArrayList<Review>(); 
	private JButton review_button;
	
	public HouseProfile(House house) {
		super(house.address.asString());
		this.house = house;
		review_button = new JButton("Review Me");
		review_button.setBounds(super.DEFAULT_WIDTH/2-50, super.DEFAULT_HEIGHT-75, 100, 50);
		review_button.addActionListener(this);
		super.main.add(review_button);
		populateReviews();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == review_button) {
			HouseReviewScreen review = new HouseReviewScreen(this.house);	// make this take in a house obj	
		}
	}

	@Override
	protected void populateReviews() {
		reviews = QueryManager.getInstance().getHouseReviews(house);
		System.out.println(reviews.size());
		if (reviews.size() == 0) {
			JOptionPane.showMessageDialog(this,
				    "There are no reviews on this House",
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
