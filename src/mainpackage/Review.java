package mainpackage;

import java.util.ArrayList;


public abstract class Review {
	
	enum Flag {
		Inappropriate,
		Malicious
	}

	String Text;
	ArrayList<Flag> Flags = new ArrayList<Flag>();
	NumericalRating rating;
}
