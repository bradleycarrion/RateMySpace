package mainpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


public class QueryManager {
	
	private Connection conn;
	
	/*
	 * @desc Connects to the database with magic and stuff. 
	 * @param connectionString  some weird thing java wants. Example "jdbc:sqlserver:://hostname"
	 * @param userID            the userID for the mysql instance
	 * @param password          the password for the mysql instance
	 */
	private void connect(String connectionString, String userID, String password) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(connectionString, userID, password);
			
			//If we got here, we should be good and connected, otherwise we'd be in the catch blocks.
			System.out.print("...Connected to SQL Server");
		} catch (ClassNotFoundException e) {
			System.out.print("!!! - Error: Something went wrong trying to connect to the SQL server.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.print("!!! - Error: An SQLException happened trying to connect to the SQL server.");
			e.printStackTrace();
		}
	}
	
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void flagReview(int reviewID, String name) {
		String query = "Insert into Flag (Name, ReviewID) values(?, ?)";
		
		try {
			//Create object that represents query from the string version of the query
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			//Populate object with data parameters
			preparedStmt.setString(1, name); //Goes to name
			preparedStmt.setString(2, Integer.toString(reviewID)); // Goes to ReviewID
			
			//Send the query
			preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Review> getReviews() {
		return null;
	}
	/*
	public ArrayList<Review> getSpaces() {
		
	}*/
	
	/*
	 * @desc Constructor that automagically calls a connection so we can do stuff. 
	 */
	QueryManager() {
		connect("jdbc:sqlserver:://<hostname>", "<user>", "<password>");
	}
}
