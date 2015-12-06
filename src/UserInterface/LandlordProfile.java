package UserInterface;

import java.awt.event.ActionEvent;

import objects.Landlord;

public class LandlordProfile extends ProfileScreen {
	Landlord landlord;

	public LandlordProfile(Landlord landlord) {
		super(landlord.name);
		this.landlord = landlord;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void populateReviews() {
		// TODO Auto-generated method stub
		
	}
	
	

}
