package mainpackage;

import java.util.ArrayList;


public class NumericalRating {

	public ArrayList<Rating> ratings = new ArrayList<Rating>();
	
	/*
	 * 	@desc Calculates the total value of the ratings
	 */
	public int getTotal() {
		int total = 0;
		for (int i = 0; i < ratings.size(); i++) {
			total += ratings.get(i).value;
		}
		
		return total;
	}
	
	/*
	 * @desc Calculates the average value of the ratings.
	 */
	public int getAverage() {
		if (ratings.size() <= 0) {
			return 0; //No ratings
		}
		
		return getTotal()/ratings.size();
	}
	
	/*
	 * 	@desc gets a rating by it's name. 
	 *  @return Rating 
	 *  @param name the name of the rating you want
	 *  @warning it will return null if it doesn't find anything
	 */
	public Rating getRating(String name) {
		Rating rating = null;
		for (int i = 0; i < ratings.size(); i++) {
			if (ratings.get(i).name == name) {
				rating = ratings.get(i);
			}
		}
		
		return rating;
	}
}
