package UserInterface;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import objects.Landlord;

public class LandlordProfile extends ProfileScreen {
	Landlord landlord;

	public LandlordProfile(Landlord landlord) {
		super(landlord.name);
		this.landlord = landlord;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	protected void populateReviews() {
		// TODO Auto-generated method stub
		
	}
	
	

}
