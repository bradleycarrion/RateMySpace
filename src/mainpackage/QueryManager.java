package mainpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Address;
import objects.House;
import objects.Landlord;


public class QueryManager {
	
	private static QueryManager singleton;
	private Connection conn;
	private Boolean debug = true;
	
	public static void main (String args[]) {
		QueryManager manager = new QueryManager();
		test(manager);
	}
	
	public static void test(QueryManager manager) {
		testAddHouse(manager);
	}
	
	public static void testAddHouse(QueryManager manager) {
		System.out.println("Testing Adding a House to a database...");
		
		//House
		Address address = new Address("Joe", "Yes", "no", 8888);
		House house = new House(address);
		
		//Landlord 
		Landlord lord = new Landlord("Brad", null);
		
		manager.addHouse(house, lord);
		
		System.out.println(" -------------------------- ");
	}
	
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
	
	
	
	/*
	 * @desc sends the review to the MYSQL database
	 */
	public void sendReview(String review, int id) {
		String query = " Insert into Reviews (UserID, Review) values (?, ?)";
		
		try {
			//Create object that represents query from the string version of the query
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			//Populate object with data parameters
			preparedStmt.setString(1, Integer.toString(id)); //Goes to UserID
			preparedStmt.setString(2, review); // Goes to Review
			
			//Send the query
			preparedStmt.execute();
			
			//Print query if we're in debug mode
			if (debug) {
				System.out.println(preparedStmt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void sendHouseReview(String review, House house) {
		
		//String query = "Insert into Reviews "
	}
	
	
	
	public void flagReview(int reviewID, String name) {
		String query = "Insert into Flag (Name, ReviewID) values (?, ?)";
		
		try {
			//Create object that represents query from the string version of the query
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			//Populate object with data parameters
			preparedStmt.setString(1, name); //Goes to name
			preparedStmt.setString(2, Integer.toString(reviewID)); // Goes to ReviewID
			
			//Send the query
			preparedStmt.execute();
			
			//Commit
			conn.commit();
			
			//Print query if we're in debug mode
			if (debug) {
				System.out.println(preparedStmt);
			}
			
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
	 * @desc Constructor that automagically calls a connection so we can do stuff. 
	 */
	private QueryManager() {
		connect("jdbc:mysql://localhost/landlord", "app", "password");
	}
	
	
	private static QueryManager getInstance() {
		if (singleton ==  null)
			singleton = new QueryManager();
		return singleton;
	}
	
}
