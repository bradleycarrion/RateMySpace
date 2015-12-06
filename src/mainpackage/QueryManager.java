package mainpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Address;
import objects.House;
import objects.HouseReview;
import objects.Landlord;
import objects.LandlordReview;


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
	}
	
	private static void testAddHouse(QueryManager manager) {
		System.out.println("...Testing Adding a House to a database");
		
		//House
		Address address = new Address("Joe", "Yes", "no", 8888);
		House house = new House(address);
		
		//Landlord 
		Landlord lord = new Landlord("Brad");
		
		manager.addHouse(house, lord);
		
		System.out.println("----------------------------------");
	}
	
	private static void testReviewHouse(QueryManager manager) {
		System.out.println("...Testing Adding a House review");
		
		//House
		Address address = new Address("Joe", "Yes", "no", 8888);
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
	
	public ArrayList<House> getSpaces(String searchKey) {
		//String query = "Select ";
		
		return null;
	}
	
	public ArrayList<Landlord> getLandlords() {
		return null;
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
		preparedStmt.setString(4, Integer.toString(house.address.zip));
		
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
