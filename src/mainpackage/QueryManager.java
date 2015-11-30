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
	public void sendReview() {
		String query = " insert into Reviews (thingone, thingtwo, thingthree)"
		        + " values (?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, "Something");
			preparedStmt.setString(2, "Something2");
			preparedStmt.setString(3, "Something3");
			preparedStmt.setString(4, "Something4");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void reportReview() {
		
	}
	
	public ArrayList<Review> getReviews() {
		return null;
	}
	
	/*
	 * @desc Constructor that automagically calls a connection so we can do stuff. 
	 */
	QueryManager() {
		connect("jdbc:sqlserver:://<hostname>", "<user>", "<password>");
	}
}
