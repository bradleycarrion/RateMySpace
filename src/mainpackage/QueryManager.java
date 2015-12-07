package mainpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Address;
import objects.House;
import objects.HouseReview;
import objects.Landlord;
import objects.LandlordReview;
import objects.Review;


public class QueryManager {
	
	private static QueryManager singleton;
	private Connection conn;
	private Boolean debug = true;
	private int userID = 1; //default userID
	
	
	// ---------------- Unit Tests ------------------- //
	
	public static void main (String args[]) {
		QueryManager manager = new QueryManager();
		test(manager);
	}
	
	public static void test(QueryManager manager) {
		testAddHouse(manager);
		testReviewHouse(manager);
		testLandlordReview(manager);
		testSearchHouses(manager);
		testSearchLandlords(manager);
		testGetHouseReviews(manager);
		testGetLandlordReviews(manager);
	}
	
	private static void testAddHouse(QueryManager manager) {
		System.out.println("...Testing Adding a House to a database");
		
		//House
		Address address = new Address("Joe", "Yes", "no", "8888");
		House house = new House(address);
		
		//Landlord 
		Landlord lord = new Landlord("Brad");
		
		manager.addHouse(house, lord);
		
		System.out.println("----------------------------------");
	}
	
	private static void testReviewHouse(QueryManager manager) {
		System.out.println("...Testing Adding a House review");
		
		//House
		Address address = new Address("Joe", "Yes", "no", "8888");
		House house = new House(address);
		house.setID(5);
		
		//Review 
		String text = "OMG BEST HOSUE EVA AND ITS LIKE SO TOTS MEGOATS AWESOME";
		HouseReview review = new HouseReview(house, text);
		manager.sendHouseReview(review);
		
		System.out.println("----------------------------------");
	}
	
	private static void testLandlordReview(QueryManager manager) {
		System.out.println("...Testing Adding a Landlord review");
		
		//Landlord
		Landlord landlord = new Landlord("Joe");
		landlord.setID(1);
		
		//Review 
		String text = "OMG BEST DUDESZZZZ EVA AND ITS LIKE SO TOTS MEGOATS AWESOME";
		LandlordReview review = new LandlordReview(landlord, text);
		manager.sendLandlordReview(review);
		
		System.out.println("----------------------------------");
	}
	
	private static void testSearchHouses(QueryManager manager) {
		System.out.println("...Testing searching for houses");
		
		ArrayList<House> houses = manager.getHouses("Joe street");
		
		for (int i = 0; i < houses.size(); i++) {
			System.out.println(houses.get(i).address.street);
		}
		
		System.out.println("----------------------------------");
	}
	
	private static void testSearchLandlords(QueryManager manager) {
		System.out.println("...Testing searching landlords");
		
		ArrayList<Landlord> lords = manager.getLandlords("Brad mchmilaln");
		
		for (int i = 0; i < lords.size(); i++) {
			System.out.println(lords.get(i).name);
		}
		
		System.out.println("----------------------------------");
	}
	
	private static void testGetHouseReviews(QueryManager manager) {
		System.out.println("...Testing getting house reviews");
		
		//House
		Address address = new Address("Joe", "Yes", "no", "8888");
		House house = new House(address);
		house.setID(5);
		
		ArrayList<Review> reviews = manager.getHouseReviews(house);
		
		for (int i = 0; i < reviews.size(); i++) {
			System.out.println(reviews.get(i).text);
		}
		
		System.out.println("----------------------------------");
	}
	
	private static void testGetLandlordReviews(QueryManager manager) {
		System.out.println("...Testing getting landlord reviews");
		
		//Landlord
		Landlord landlord = new Landlord("Joe");
		landlord.setID(1);
				
		
		ArrayList<Review> reviews = manager.getLandlordReviews(landlord);
		
		for (int i = 0; i < reviews.size(); i++) {
			System.out.println(reviews.get(i).text);
		}
		
		System.out.println("----------------------------------");
	}
	
	// ------------------- Connections -------------------- //
	
	/*
	 * @desc Connects to the database with magic and stuff. 
	 * @param connectionString  some weird thing java wants. Example "jdbc:sqlserver:://hostname"
	 * @param userID            the userID for the mysql instance
	 * @param password          the password for the mysql instance
	 */
	private void connect(String connectionString, String userID, String password) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conn = DriverManager.getConnection(connectionString, userID, password);
			
			//If we got here, we should be good and connected, otherwise we'd be in the catch blocks.
			System.out.println("...Connected to SQL Server");
		} catch (ClassNotFoundException e) {
			System.out.println("!!! - Error: Something went wrong trying to connect to the SQL server.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("!!! - Error: An SQLException happened trying to connect to the SQL server.");
			e.printStackTrace();
		}
	}
	
	// ------------------- Publicly accessible queries ------------------------ //
	
	/*
	 * @desc adds a review of a house to the database.
	 */
	public void sendHouseReview(HouseReview review) {
		if (review.house.id == -1) {
			System.out.println("sendHouseReview was fed with an idless house! OH NO! ERROR ERROR. BAD. BAD.");
			return;
		}
		
		String query = "Insert into HouseReview (UserID, HouseID, Review) values (?, ?, ?)";
		
		try {
			//Create Object
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			//Populate object with data parameters
			preparedStmt.setInt(1, userID);
			preparedStmt.setInt(2, review.house.id);
			preparedStmt.setString(3, review.text);
			
			//Print query if we're in debug mode
			if (debug) {
				System.out.println(preparedStmt);
			}
			
			//Send the query
			preparedStmt.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * @desc adds a review of a landlord to the database.
	 */
	public void sendLandlordReview(LandlordReview review) {
		if (review.landlord.id == -1) {
			System.out.println("sendLandlordReview was fed with an id-less landlord!!! BADDD");
			return;
		}
		
		String query = "Insert into LandlordReview(UserID, LandlordID, Review) VALUES (?, ?, ?)";
		
		try {
			//Create Object 
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			//Populate with data parameters
			preparedStmt.setInt(1, userID);
			preparedStmt.setInt(2, review.landlord.id);
			preparedStmt.setString(3, review.text);
	
			//Print query if we're in debug mode
			if (debug) {
				System.out.println(preparedStmt);
			}
			
			//Send the query
			preparedStmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<House> getHouses(String streetAddress) {
		String[] allWords = streetAddress.split(" ");
		ArrayList<String> keyWords = selectKeyWords(allWords); //Grab all the keywords (not numbers basically)
		
		//If we found any keywords, continue the search; else return nothing.
		if (keyWords.size() > 0) {
			
			//Build the query 
			String query = "SELECT * FROM House JOIN Address ON House.AddressID = Address.AddressID WHERE Address.Street LIKE ?";
			
			//Expand our query for any key words we find
			for (int i = 1; i < keyWords.size(); i++) {
				query += "OR Address.Street LIKE ? ";
			}
			
			try {
				//Create our query 
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				
				//Populate our query
				for (int i = 0; i < keyWords.size(); i++) {
					preparedStmt.setString(i+1, "%" + keyWords.get(i) + "%");
				}
				
				//Print query if we're in debug mode
				if (debug) {
					System.out.println(preparedStmt);
				}
				
				ResultSet results = preparedStmt.executeQuery();
				return parseHousesFromResults(results);
			
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			//Print query if we're in debug mode
			if (debug) {
				System.out.println("No key words were found");
			}
		}
		
		return null;
	}
	
	private ArrayList<House> parseHousesFromResults(ResultSet results) {
		if (results == null) {
			return null;
		}
		
		ArrayList<House> houses = new ArrayList<House>();
		
		try {
			while(results.next()) {
				System.out.println(("Happened"));
				Address address = new Address(results.getString("Street"), results.getString("City"), results.getString("State"), results.getString("Zip"));
				
				House house = new House(address);
				house.setID(results.getInt("HouseID"));
				houses.add(house);
			}
			
			return houses;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private ArrayList<String> selectKeyWords(String[] words) {
		ArrayList<String> returnable = new ArrayList<String>();
		
		for (int i = 0; i < words.length; i++) {
			returnable.add(words[i]);
		}
		
		return returnable;
	}
	
	public ArrayList<Landlord> getLandlords(String searchText) {
		String[] allWords = searchText.split(" ");
		ArrayList<String> keyWords = selectKeyWords(allWords); //Grab all the keywords (not numbers basically)
		
		//If we found any keywords, continue the search; else return nothing.
		if (keyWords.size() > 0) {
			
			//Build the query 
			String query = "SELECT * FROM Landlord WHERE LordName LIKE ?";
			
			//Expand our query for any key words we find
			for (int i = 1; i < keyWords.size(); i++) {
				query += "OR LordName LIKE ? ";
			}
			
			try {
				//Create our query 
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				
				//Populate our query
				for (int i = 0; i < keyWords.size(); i++) {
					preparedStmt.setString(i+1, "%" + keyWords.get(i) + "%");
				}
				
				//Print query if we're in debug mode
				if (debug) {
					System.out.println(preparedStmt);
				}
				
				ResultSet results = preparedStmt.executeQuery();
				return parseLandlordsFromResults(results);
			
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			//Print query if we're in debug mode
			if (debug) {
				System.out.println("No key words were found");
			}
		}
		
		return null;
	}
	
	private ArrayList<Landlord> parseLandlordsFromResults(ResultSet results) {
		if (results == null) {
			return null;
		}
		
		ArrayList<Landlord> lords = new ArrayList<Landlord>();
		
		try {
			while(results.next()) {
				Landlord lord = new Landlord(results.getString("LordName"));
				lord.setID(results.getInt("LandlordID"));
				lords.add(lord);
			}
			
			return lords;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Review> getHouseReviews(House house) {
		String query = "SELECT * FROM HouseReview JOIN House ON HouseReview.HouseID = House.HouseID JOIN Address ON Address.AddressID = House.AddressID";
		
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			ResultSet results = preparedStmt.executeQuery();
			return parseReviewsFromResults(results);
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private ArrayList<Review> parseReviewsFromResults(ResultSet results) {
		ArrayList<Review> reviews = new ArrayList<Review>();
		try {
			while(results.next()) {
				Review review = new Review(results.getString("Review"));
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reviews;
	}
	
	public ArrayList<Review> getLandlordReviews(Landlord landlord) {
		String query = "SELECT * FROM LandlordReview JOIN Landlord ON Landlord.LandlordID = LandlordReview.LandlordID";
		
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			ResultSet results = preparedStmt.executeQuery();
			return parseReviewsFromResults(results);
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/*
	 * @desc adds a house to the database with a landlord
	 */
	public void addHouse(House house, Landlord landlord) {
		try {
			
			// ------------ Address Query ------------- // 
			addressQuery(house);
			
			// ------------ House Query --------------- //
			houseQuery();
			
			// ------------ Landlord Query --------------- //
			landlordQuery(landlord);
			
			// ------------ Update House With Landlord Query ---------- //
			updateHouseWithLandlordQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// --------------- SUBQUERIES ----------------- //
	// -- Specific to addHouse. Probably not a   -- //
	// -- good idea to use anywhere else.        -- //
	// --------------- SUBQUERIES ----------------- //
	
	/* !! SUBQUERY !!
	 *  @desc Updates the house with the landlord. Oh god please work. 
	 *  @warning should really only be used in addHouse() 
	 */
	private void updateHouseWithLandlordQuery() throws SQLException {
		String updateHouseQuery = "UPDATE House SET LandlordID = (SELECT LandlordID FROM Landlord ORDER BY LandlordID DESC LIMIT 1) ORDER BY HouseID DESC LIMIT 1;";
		
		//Create Object
		PreparedStatement updateHouseStmt = conn.prepareStatement(updateHouseQuery);
		
		//Send the Query
		updateHouseStmt.execute();
	}
	
	
	/* !! SUBQUERY !!
	 *  @desc Checks if landlord exists, add if the landlord does not.
	 */
	private void landlordQuery(Landlord landlord) throws SQLException {
		String name = landlord.name;
		String landlordQuery = "INSERT INTO Landlord (LordName) SELECT '" 
		+ name + "' FROM Landlord WHERE NOT EXISTS ( SELECT LordName FROM Landlord WHERE LordName = '"
		+ name + "' ) LIMIT 1;"; //Really ugly query that adds name if the name doesn't already exist. 
		
		//Create Object
		PreparedStatement landlordStmt = conn.prepareStatement(landlordQuery);
		
		//Send the query
		landlordStmt.execute();
		
		//Print query if we're in debug mode
		if (debug) {
			System.out.println(landlordStmt);
		}
	}
	
	/* !! SUBQUERY !!
	 *  @desc adds the house, assuming an address was just entered. 
	 *  @warning Basically should only be used in addHouse
	 */
	private void houseQuery() throws SQLException {
		String houseQuery = "INSERT INTO House (AddressID) VALUES (LAST_INSERT_ID())";
		
		//Create object
		PreparedStatement houseStmt = conn.prepareStatement(houseQuery);
		
		//Send query
		houseStmt.execute();
		
		//Print query if we're in debug mode
		if (debug) {
			System.out.println(houseStmt);
		}
	}
	
	
	/* !! SUBQUERY !!
	 * @desc adds the address to the database
	 */
	private void addressQuery(House house) throws SQLException {
		String addressQuery = "INSERT INTO Address (Street, City, State, Zip) VALUES (?, ?, ?, ?)";
		
		//Create object that represents query from the string version of the query
		PreparedStatement preparedStmt = conn.prepareStatement(addressQuery);
		
		//Populate object with data parameters 
		preparedStmt.setString(1, house.address.street);
		preparedStmt.setString(2, house.address.city);
		preparedStmt.setString(3, house.address.state);
		preparedStmt.setString(4, house.address.zip);
		
		//Send the query
		preparedStmt.execute();
		
		//Print query if we're in debug mode
		if (debug) {
			System.out.print(preparedStmt);
		}
	}
	
	// --------------- End SUBQUERIES --------------- //
	
	
	
	// ----------------- Constructor and Singleton ------------- //
	
	/*
	 * @desc Constructor that automagically calls a connection so we can do stuff. 
	 */
	private QueryManager() {
		connect("jdbc:mysql://localhost/landlord", "app", "password");
	}
	
	public static QueryManager getInstance() {
		if (singleton ==  null)
			singleton = new QueryManager();
		return singleton;
	}
	
}
