package UserInterface;

import java.awt.event.ActionEvent;

import objects.House;

public class HouseProfile extends ProfileScreen {
	private House house;
	
	public HouseProfile(House house) {
		super(house.address.toString());
		this.house = house;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void populateReviews() {
		// TODO Auto-generated method stub
		
	}

}
